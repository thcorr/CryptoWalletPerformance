package Model;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class CryptoCoinExcelEntry {

	@CsvBindByName(column = "symbol")
	private String symbol;
	
	@CsvBindByName(column = "quantity")
	private BigDecimal quantity;
	
	@CsvBindByName(column = "price")
	private BigDecimal price;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
}
