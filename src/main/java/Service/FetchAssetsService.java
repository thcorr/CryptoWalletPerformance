package Service;

import java.io.IOException;
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
import Model.CryptoCoinAssetDTOResponse;
import Model.CryptoCoinAssetDTORawResponse;

public class FetchAssetsService {

	private static final String GET_API_URL = "https://api.coincap.io/v2/assets/";
	private static final HttpClient httpClient = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();


	public static String fetchIdBySymbol(String symbol) throws IOException, InterruptedException, ApiInternalClientException, ApiServerException {
		HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(GET_API_URL+ "?search=" + symbol)).build();


		int responseCode = 0;
		int retryCounter = 0;
		boolean isFetchSuccess = false;
		String responseBody =  "";

		while(!isFetchSuccess && retryCounter <= 5) {
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


		if(responseCode>= 400 && responseCode <= 499) {
			throw new ApiInternalClientException(responseCode, "FetchAssetService - retried: " + (retryCounter - 1) + " times - " + responseBody);
		}

		else if(responseCode>=500 && responseCode <= 505) {
			throw new ApiServerException(responseCode, "FetchAssetService - retried: " + (retryCounter - 1) + " times - " + responseBody);
		}


		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

		CryptoCoinAssetDTORawResponse cryptoCoinAssetDTORawResponse = mapper.readValue(responseBody, new TypeReference<CryptoCoinAssetDTORawResponse>() {});
		List<CryptoCoinAssetDTOResponse> CryptoCoinAssetDTOResponseList = cryptoCoinAssetDTORawResponse.getData();

		for(var cryptoCoinEntry : CryptoCoinAssetDTOResponseList) {
			if(cryptoCoinEntry.getSymbol().equalsIgnoreCase(symbol)) {
				return cryptoCoinEntry.getId();
			}
		}


		return "ID_NOT_FOUND";

	}

}
