package Service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exceptions.ApiInternalClientException;
import Exceptions.ApiServerException;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


public class FetchAssetPriceServiceTest {

	@Test
	@DisplayName("Fetch price of coin with bad id")
	public void testFetchPriceOfCoinWithBadId() throws IOException, InterruptedException, ApiInternalClientException, ApiServerException {

		String badId = "THISIDDOESNOTEXISTOBVIOUSLY";
		Exception exception = assertThrows(ApiInternalClientException.class, () -> {
			FetchAssetPriceService.fetchPriceById(badId);
	    });
		
		String expectedMessage = "unable to find asset";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));

	}
	
}
