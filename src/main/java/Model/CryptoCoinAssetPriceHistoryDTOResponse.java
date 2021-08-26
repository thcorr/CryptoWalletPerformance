package Model;

import java.math.BigDecimal;

public class CryptoCoinAssetPriceHistoryDTOResponse {

	private BigDecimal priceUsd;
	private String time;
	private String date;
	
	public BigDecimal getPriceUsd() {
		return priceUsd;
	}
	public void setPriceUsd(BigDecimal priceUsd) {
		this.priceUsd = priceUsd;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
}
