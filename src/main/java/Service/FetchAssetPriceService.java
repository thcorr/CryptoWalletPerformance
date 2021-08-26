package Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import Exceptions.ApiInternalClientException;
import Exceptions.ApiServerException;
import Model.CryptoCoinAssetDTORawResponse;
import Model.CryptoCoinAssetDTOResponse;
import Model.CryptoCoinAssetPriceHistoryDTORawResponse;
import Model.CryptoCoinAssetPriceHistoryDTOResponse;

public class FetchAssetPriceService {

	private static final String GET_API_URL = "https://api.coincap.io/v2/assets/";
	private static final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
	
	
	public static BigDecimal fetchPriceById(String id) throws IOException, InterruptedException, ApiInternalClientException, ApiServerException {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(GET_API_URL+ id + "/history?interval=d1&start=1617753600000&end=1617753601000")).build();
		

		int responseCode = 0;
		int retryCounter = 0;
		boolean isFetchSuccess = false;
		String responseBody =  "";
		
		while(!isFetchSuccess && retryCounter<= 5) {
			
			HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			responseCode = response.statusCode();
			responseBody = response.body();
			
			if (responseCode != 200) {
				retryCounter++;
			}
			
			else {
				isFetchSuccess = true;
			}
			
		}
		
		if(responseCode>=400 && responseCode <=499) {
			throw new ApiInternalClientException(responseCode, "FetchAssetPriceService - retried " + retryCounter + " times "  + responseBody);
		}
		
		else if(responseCode>=500 && responseCode <= 505) {
			throw new ApiServerException(responseCode, "FetchAssetPriceService - retried " + retryCounter + " times "  + responseBody);
		}
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		
		CryptoCoinAssetPriceHistoryDTORawResponse cryptoCoinAssetPriceHistoryDTORawResponse = mapper.readValue(responseBody, new TypeReference<CryptoCoinAssetPriceHistoryDTORawResponse>() {});
		List<CryptoCoinAssetPriceHistoryDTOResponse> cryptoCoinAssetPriceHistoryDTOResponseList = cryptoCoinAssetPriceHistoryDTORawResponse.getData();
				
		if (cryptoCoinAssetPriceHistoryDTOResponseList != null) {
			return cryptoCoinAssetPriceHistoryDTOResponseList.get(0).getPriceUsd();
		}
		
		return new BigDecimal(-1);
		
	}
	
}
