package Controller;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Model.CryptoCoinEnriched;

public class OutputController {

	public List<String> informWhichCryptosFailedToEnrich(List<CryptoCoinEnriched> listOfEnrichedCryptos) {

		List<String> tempListOfFailures = new ArrayList<>();
		tempListOfFailures = listOfEnrichedCryptos.stream().filter(n -> n.isFullyEnriched() == false).map(n -> n.getSymbol()).collect(Collectors.toList());
		return tempListOfFailures;
	
	}

	public BigDecimal getTotal(List<CryptoCoinEnriched> listOfEnrichedCryptos) {

		return listOfEnrichedCryptos.stream().filter(n -> n.isFullyEnriched() == true).map(n -> n.getCurrentPriceFromApi().multiply(n.getQuantity())).reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.HALF_UP);
	}

	public List<String> getBestAsset(List<CryptoCoinEnriched> listOfEnrichedCryptos) {

		List<CryptoCoinEnriched> filteredEnrichedList = listOfEnrichedCryptos.stream().filter(n -> n.isFullyEnriched() == true).collect(Collectors.toList());
		String bestAsset = "";
		BigDecimal bestPerformance = new BigDecimal(Integer.MIN_VALUE);

		for(var entry : filteredEnrichedList) {
			BigDecimal currPriceApi = entry.getCurrentPriceFromApi();
			BigDecimal originalPriceInput = entry.getOriginalPriceFromInput();
			BigDecimal tempPerformance = BigDecimal.ZERO;

			if (currPriceApi != BigDecimal.ZERO && originalPriceInput!= BigDecimal.ZERO ) {
				tempPerformance = currPriceApi.divide(originalPriceInput, MathContext.DECIMAL128);
			}

			if(tempPerformance.compareTo(bestPerformance) > 0) {
				bestAsset = entry.getSymbol();
				bestPerformance = tempPerformance;
			}
		}
		
		bestPerformance = bestPerformance.setScale(2, RoundingMode.HALF_UP);
		List<String> resultList = List.of(bestAsset, bestPerformance.toString());
		return resultList;
	}

	public List<String> getWorstAsset(List<CryptoCoinEnriched> listOfEnrichedCryptos) {

		List<CryptoCoinEnriched> filteredEnrichedList = listOfEnrichedCryptos.stream().filter(n -> n.isFullyEnriched() == true).collect(Collectors.toList());
		String worstAsset = "";
		BigDecimal worstPerformance = new BigDecimal(Integer.MAX_VALUE);

		for(var entry : filteredEnrichedList) {
			BigDecimal currPriceApi = entry.getCurrentPriceFromApi();
			BigDecimal originalPriceInput = entry.getOriginalPriceFromInput();
			BigDecimal tempPerformance = BigDecimal.ZERO;

			if (currPriceApi != BigDecimal.ZERO && originalPriceInput!= BigDecimal.ZERO ) {

				tempPerformance = currPriceApi.divide(originalPriceInput, MathContext.DECIMAL128);
			}

			if(tempPerformance.compareTo(worstPerformance) < 0) {
				worstAsset = entry.getSymbol();
				worstPerformance = tempPerformance;
			}

		}

		worstPerformance = worstPerformance.setScale(2, RoundingMode.HALF_UP);
		List<String> resultList = List.of(worstAsset, worstPerformance.toString());
		return resultList;
	}

	public void resultOutput(List<CryptoCoinEnriched> listOfEnrichedCryptos) {

		List<String> failedEnrichment = informWhichCryptosFailedToEnrich(listOfEnrichedCryptos);
		
		if(failedEnrichment.size() > 0) {
			System.out.println("================================================================================================");
			System.out.println("The following symbols were not processed due to errors in the data fetching from the API. Check if the API is Busy or if the supplied values are correct");
			System.out.println(failedEnrichment);
			
		}
		
		if(failedEnrichment.size() == listOfEnrichedCryptos.size()) {
			return;
		}
		
		
		BigDecimal total = getTotal(listOfEnrichedCryptos);
		List<String> bestAsset = getBestAsset(listOfEnrichedCryptos);
		List<String> worstAsset = getWorstAsset(listOfEnrichedCryptos);

		System.out.println("================================================================================================");
		System.out.println("Total=" + total.toString() + ",best_asset=" + bestAsset.get(0) + ",best_performance=" + bestAsset.get(1) + ",worst_asset=" + worstAsset.get(0) + ",worst performance=" + worstAsset.get(1));
		System.out.println("================================================================================================");
	}

}
