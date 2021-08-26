package Model;

import java.math.BigDecimal;

public class CryptoCoinEnriched {

	private String symbol;
	private String id;
	private BigDecimal quantity;
	private BigDecimal originalPriceFromInput;
	private BigDecimal currentPriceFromApi;
	private boolean isFullyEnriched;
	
	public boolean isFullyEnriched() {
		return isFullyEnriched;
	}
	public void setFullyEnriched(boolean isFullyEnriched) {
		this.isFullyEnriched = isFullyEnriched;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getQuantity() {
		return quantity;
	}
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getOriginalPriceFromInput() {
		return originalPriceFromInput;
	}
	public void setOriginalPriceFromInput(BigDecimal originalPriceFromInput) {
		this.originalPriceFromInput = originalPriceFromInput;
	}
	public BigDecimal getCurrentPriceFromApi() {
		return currentPriceFromApi;
	}
	public void setCurrentPriceFromApi(BigDecimal currentPriceFromApi) {
		this.currentPriceFromApi = currentPriceFromApi;
	}
	
	
}
