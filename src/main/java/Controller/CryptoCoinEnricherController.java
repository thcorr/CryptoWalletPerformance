package Controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

import Model.CryptoCoinEnriched;
import Model.CryptoCoinExcelEntry;
import Service.FetchAssetPriceService;
import Service.FetchAssetsService;

public class CryptoCoinEnricherController {

	public CryptoCoinEnriched enrichCryptoCoinsByInput(CryptoCoinExcelEntry cryptoCoinEntry) {

		var tempCryptoCoinEnriched = new CryptoCoinEnriched();

		tempCryptoCoinEnriched.setSymbol(cryptoCoinEntry.getSymbol());
		tempCryptoCoinEnriched.setOriginalPriceFromInput(cryptoCoinEntry.getPrice());
		tempCryptoCoinEnriched.setQuantity(cryptoCoinEntry.getQuantity());

		try {
			tempCryptoCoinEnriched.setId(FetchAssetsService.fetchIdBySymbol(cryptoCoinEntry.getSymbol()));
			tempCryptoCoinEnriched.setCurrentPriceFromApi(FetchAssetPriceService.fetchPriceById(tempCryptoCoinEnriched.getId()));

			if(tempCryptoCoinEnriched.getId() != "ID_NOT_FOUND" && tempCryptoCoinEnriched.getCurrentPriceFromApi() != new BigDecimal(-1) ) {

				tempCryptoCoinEnriched.setFullyEnriched(true);

			}

			else {
				tempCryptoCoinEnriched.setFullyEnriched(false);
			}


		}catch (Exception e) {
			System.out.println("ID: " + tempCryptoCoinEnriched.getSymbol() + " - error - " + e.getMessage());
			tempCryptoCoinEnriched.setFullyEnriched(false);
		} 

		return tempCryptoCoinEnriched;

	}

	public List<CryptoCoinEnriched> bulkEnrichCryptoCoinsFromInput(List<CryptoCoinExcelEntry> listOfCryptoCoinsFromInput) {
		List<CryptoCoinEnriched> listOfEnrichedCryptoCoins =  Collections.synchronizedList(new ArrayList<>());


		if(listOfCryptoCoinsFromInput.size() > 3) {

			ForkJoinPool customThreadPool = new ForkJoinPool(3);
			
			try {
				customThreadPool.submit(() -> listOfCryptoCoinsFromInput.parallelStream().forEach(n->{ listOfEnrichedCryptoCoins.add(enrichCryptoCoinsByInput(n));})).get();
			} catch (InterruptedException e) {
				//log would go here
				e.printStackTrace();
			} catch (ExecutionException e) {
				//log would go here
				e.printStackTrace();
			}

			return listOfEnrichedCryptoCoins;
		}
		else{
			for(var entry: listOfCryptoCoinsFromInput) {
				listOfEnrichedCryptoCoins.add(enrichCryptoCoinsByInput(entry));
			}
		}
		return listOfEnrichedCryptoCoins;

	}

}
