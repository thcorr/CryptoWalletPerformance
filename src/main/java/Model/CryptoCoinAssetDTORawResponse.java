package Model;

import java.math.BigDecimal;
import java.util.List;

public class CryptoCoinAssetDTORawResponse {
	
	private List<CryptoCoinAssetDTOResponse> data;
	
	private String timestamp;

	public List<CryptoCoinAssetDTOResponse> getData() {
		return data;
	}

	public void setData(List<CryptoCoinAssetDTOResponse> data) {
		this.data = data;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

}
