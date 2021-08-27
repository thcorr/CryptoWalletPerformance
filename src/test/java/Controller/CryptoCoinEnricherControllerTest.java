package Controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Model.CryptoCoinExcelEntry;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class CryptoCoinEnricherControllerTest {


	private static CryptoCoinEnricherController cryptoCoinEnricherController;

	@BeforeAll
	public static void setup() {
		cryptoCoinEnricherController = new CryptoCoinEnricherController();
	}

	@Test
	@DisplayName("Test enrichment with three or less assets")
	public void testEnrichmentWithThreeOrLessAssets() {

		CryptoCoinExcelEntry crypto1 = new CryptoCoinExcelEntry();
		crypto1.setPrice(new BigDecimal(1.0));
		crypto1.setQuantity(new BigDecimal(1.0));
		crypto1.setSymbol("BTC");

		CryptoCoinExcelEntry crypto2 = new CryptoCoinExcelEntry();
		crypto2.setPrice(new BigDecimal(1.0));
		crypto2.setQuantity(new BigDecimal(1.0));
		crypto2.setSymbol("ETH");

		List<CryptoCoinExcelEntry> input = List.of(crypto1, crypto2);
		
		var output = cryptoCoinEnricherController.bulkEnrichCryptoCoinsFromInput(input);
	
		assertTrue(output.stream().anyMatch(item -> "BTC".equals(item.getSymbol())));
		assertTrue(output.stream().findFirst().get().isFullyEnriched());
		assertTrue(output.stream().count() == 2);

	}
	
	@Test
	@DisplayName("Test enrichment with four or more assets (multithreading)")
	public void testEnrichmentWithFourOrMoreAssets() {

		CryptoCoinExcelEntry crypto1 = new CryptoCoinExcelEntry();
		crypto1.setPrice(new BigDecimal(1.0));
		crypto1.setQuantity(new BigDecimal(1.0));
		crypto1.setSymbol("BTC");

		CryptoCoinExcelEntry crypto2 = new CryptoCoinExcelEntry();
		crypto2.setPrice(new BigDecimal(1.0));
		crypto2.setQuantity(new BigDecimal(1.0));
		crypto2.setSymbol("ETH");

		CryptoCoinExcelEntry crypto3 = new CryptoCoinExcelEntry();
		crypto3.setPrice(new BigDecimal(1.0));
		crypto3.setQuantity(new BigDecimal(1.0));
		crypto3.setSymbol("EOS");

		CryptoCoinExcelEntry crypto4 = new CryptoCoinExcelEntry();
		crypto4.setPrice(new BigDecimal(1.0));
		crypto4.setQuantity(new BigDecimal(1.0));
		crypto4.setSymbol("ADA");

		
		List<CryptoCoinExcelEntry> input = List.of(crypto1, crypto2, crypto3, crypto4);
		
		var output = cryptoCoinEnricherController.bulkEnrichCryptoCoinsFromInput(input);
	
		assertTrue(output.stream().anyMatch(item -> "BTC".equals(item.getSymbol())));
		assertTrue(output.stream().findFirst().get().isFullyEnriched());
		assertTrue(output.stream().count() == 4);

	}

	@Test
	@DisplayName("Test enrichment with bad Symbol")
	public void testEnrichmentWithBadSymbol() {

		CryptoCoinExcelEntry crypto1 = new CryptoCoinExcelEntry();
		crypto1.setPrice(new BigDecimal(1.0));
		crypto1.setQuantity(new BigDecimal(1.0));
		crypto1.setSymbol("ZZZ");
		List<CryptoCoinExcelEntry> input = List.of(crypto1);
		
		var output = cryptoCoinEnricherController.bulkEnrichCryptoCoinsFromInput(input);
	
		boolean isBadSymbolCruptoFullyEnriched = output.stream().map(n -> n.isFullyEnriched()).findFirst().orElse(null);
		
		assertFalse(isBadSymbolCruptoFullyEnriched);

	}

}
