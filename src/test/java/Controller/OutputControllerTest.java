package Controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Model.CryptoCoinEnriched;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


public class OutputControllerTest {

	private static OutputController outputController;

	@BeforeAll
	public static void setup() {
		outputController = new OutputController();
	}

	
	@Test
	@DisplayName("Test Cryptos which Failed to Enrich are being accuratelly tracked for output purposes")
	public void testInformWhichCryptosFailedToEnrich() {

		CryptoCoinEnriched crypto1 = new CryptoCoinEnriched();
		crypto1.setCurrentPriceFromApi(new BigDecimal(1));
		crypto1.setFullyEnriched(true);
		crypto1.setId("bitcoin");
		crypto1.setOriginalPriceFromInput(new BigDecimal(1));
		crypto1.setQuantity(new BigDecimal(1));
		crypto1.setSymbol("BTC");

		CryptoCoinEnriched crypto2 = new CryptoCoinEnriched();
		crypto2.setCurrentPriceFromApi(new BigDecimal(1));
		crypto2.setFullyEnriched(false);
		crypto2.setId("ethereal");
		crypto2.setOriginalPriceFromInput(new BigDecimal(1));
		crypto2.setQuantity(new BigDecimal(1));
		crypto2.setSymbol("ETH");


		List<CryptoCoinEnriched> input = List.of(crypto1, crypto2);
			
		var output = outputController.informWhichCryptosFailedToEnrich(input);

		assertTrue(output.stream().count()==1);
		

	}

	
	@Test
	@DisplayName("Assess get total based on crypto Coin input for output production")
	public void testGetTotalFromInputCoinList() {

		CryptoCoinEnriched crypto1 = new CryptoCoinEnriched();
		crypto1.setCurrentPriceFromApi(new BigDecimal(1));
		crypto1.setFullyEnriched(true);
		crypto1.setId("bitcoin");
		crypto1.setOriginalPriceFromInput(new BigDecimal(1));
		crypto1.setQuantity(new BigDecimal(1));
		crypto1.setSymbol("BTC");

		CryptoCoinEnriched crypto2 = new CryptoCoinEnriched();
		crypto2.setCurrentPriceFromApi(new BigDecimal(1));
		crypto2.setFullyEnriched(false);
		crypto2.setId("ethereal");
		crypto2.setOriginalPriceFromInput(new BigDecimal(1));
		crypto2.setQuantity(new BigDecimal(1));
		crypto2.setSymbol("ETH");


		List<CryptoCoinEnriched> input = List.of(crypto1, crypto2);
			
		var output = outputController.getTotal(input);

		assertEquals(new BigDecimal(1.00).setScale(2, RoundingMode.HALF_UP),output);
		
	}
	
	
	@Test
	@DisplayName("Assess best asset fetch for output production")
	public void testGetBestAsset() {

		CryptoCoinEnriched crypto1 = new CryptoCoinEnriched();
		crypto1.setCurrentPriceFromApi(new BigDecimal(1));
		crypto1.setFullyEnriched(true);
		crypto1.setId("bitcoin");
		crypto1.setOriginalPriceFromInput(new BigDecimal(1));
		crypto1.setQuantity(new BigDecimal(1));
		crypto1.setSymbol("BTC");

		CryptoCoinEnriched crypto2 = new CryptoCoinEnriched();
		crypto2.setCurrentPriceFromApi(new BigDecimal(200));
		crypto2.setFullyEnriched(true);
		crypto2.setId("ethereal");
		crypto2.setOriginalPriceFromInput(new BigDecimal(1));
		crypto2.setQuantity(new BigDecimal(1));
		crypto2.setSymbol("ETH");


		List<CryptoCoinEnriched> input = List.of(crypto1, crypto2);
			
		var output = outputController.getBestAsset(input);
		List<String> expectedResult = List.of("ETH", new BigDecimal(200.00).setScale(2, RoundingMode.HALF_UP).toString());
	
		assertEquals(expectedResult,output);
		
	}
	
	
	@Test
	@DisplayName("Assess worst asset fetch for output production")
	public void testGetWorstAsset() {

		CryptoCoinEnriched crypto1 = new CryptoCoinEnriched();
		crypto1.setCurrentPriceFromApi(new BigDecimal(1));
		crypto1.setFullyEnriched(true);
		crypto1.setId("bitcoin");
		crypto1.setOriginalPriceFromInput(new BigDecimal(1));
		crypto1.setQuantity(new BigDecimal(1));
		crypto1.setSymbol("BTC");

		CryptoCoinEnriched crypto2 = new CryptoCoinEnriched();
		crypto2.setCurrentPriceFromApi(new BigDecimal(200));
		crypto2.setFullyEnriched(true);
		crypto2.setId("ethereal");
		crypto2.setOriginalPriceFromInput(new BigDecimal(1));
		crypto2.setQuantity(new BigDecimal(1));
		crypto2.setSymbol("ETH");


		List<CryptoCoinEnriched> input = List.of(crypto1, crypto2);
			
		var output = outputController.getWorstAsset(input);
		List<String> expectedResult = List.of("BTC", new BigDecimal(1.00).setScale(2, RoundingMode.HALF_UP).toString());
	
		assertEquals(expectedResult,output);
		
	}
	
	
	
	

}
