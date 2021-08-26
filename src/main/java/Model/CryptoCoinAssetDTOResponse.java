package Model;

import java.math.BigDecimal;

public class CryptoCoinAssetDTOResponse {
	
	private String id;
	private String rank;
	private String symbol;
	private String name;
	private BigDecimal supply;
	private BigDecimal maxSupply;
	private BigDecimal marketCapUsd;
	private BigDecimal volumeUsd24Hr;
	private BigDecimal priceUsd;
	private BigDecimal changePercent24Hr;
	private BigDecimal vwap24Hr;
	private String explorer;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getSupply() {
		return supply;
	}
	public void setSupply(BigDecimal supply) {
		this.supply = supply;
	}
	public BigDecimal getMaxSupply() {
		return maxSupply;
	}
	public void setMaxSupply(BigDecimal maxSupply) {
		this.maxSupply = maxSupply;
	}
	public BigDecimal getMarketCapUsd() {
		return marketCapUsd;
	}
	public void setMarketCapUsd(BigDecimal marketCapUsd) {
		this.marketCapUsd = marketCapUsd;
	}
	public BigDecimal getVolumeUsd24Hr() {
		return volumeUsd24Hr;
	}
	public void setVolumeUsd24Hr(BigDecimal volumeUsd24Hr) {
		this.volumeUsd24Hr = volumeUsd24Hr;
	}
	public BigDecimal getPriceUsd() {
		return priceUsd;
	}
	public void setPriceUsd(BigDecimal priceUsd) {
		this.priceUsd = priceUsd;
	}
	public BigDecimal getChangePercent24Hr() {
		return changePercent24Hr;
	}
	public void setChangePercent24Hr(BigDecimal changePercent24Hr) {
		this.changePercent24Hr = changePercent24Hr;
	}
	
	public BigDecimal getVwap24Hr() {
		return vwap24Hr;
	}
	public void setVwap24Hr(BigDecimal vwap24Hr) {
		this.vwap24Hr = vwap24Hr;
	}
	public String getExplorer() {
		return explorer;
	}
	public void setExplorer(String explorer) {
		this.explorer = explorer;
	}
		
}
