package Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.ApiInternalClientException;
import Exceptions.ApiServerException;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;



public class FetchAssetsServiceTest {
	
	@Test
	@DisplayName("Fetch crypto coin ID of Symbol that does not exit")
	public void testFetchOfCryptoIdForSymbolDoesNotExist() throws IOException, InterruptedException, ApiInternalClientException, ApiServerException {
		
		String badSymbol = "THISSYMBOLDOESNOTEXISTATALL";
		String returnId = FetchAssetsService.fetchIdBySymbol(badSymbol);
		
		assertEquals("ID_NOT_FOUND", returnId);

	}
	
	
}
