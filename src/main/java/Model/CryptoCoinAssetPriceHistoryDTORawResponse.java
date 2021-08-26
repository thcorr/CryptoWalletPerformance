package Model;

import java.util.List;

public class CryptoCoinAssetPriceHistoryDTORawResponse {
	
	private List<CryptoCoinAssetPriceHistoryDTOResponse> data;
	
	private String timestamp;

	public List<CryptoCoinAssetPriceHistoryDTOResponse> getData() {
		return data;
	}

	public void setData(List<CryptoCoinAssetPriceHistoryDTOResponse> data) {
		this.data = data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
