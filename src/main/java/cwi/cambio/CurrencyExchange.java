package cwi.cambio;

import java.math.BigDecimal;
import java.util.Date;

public class CurrencyExchange {
	private Date date;
	private Currency currency;
	private BigDecimal buyingRate;
	private BigDecimal sellingRate;
	private BigDecimal buyingParity;
	private BigDecimal sellingParity;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public BigDecimal getBuyingRate() {
		return buyingRate;
	}

	public void setBuyingRate(BigDecimal buyingRate) {
		this.buyingRate = buyingRate;
	}

	public BigDecimal getSellingRate() {
		return sellingRate;
	}

	public void setSellingRate(BigDecimal sellingRate) {
		this.sellingRate = sellingRate;
	}

	public BigDecimal getBuyingParity() {
		return buyingParity;
	}

	public void setBuyingParity(BigDecimal buyingParity) {
		this.buyingParity = buyingParity;
	}

	public BigDecimal getSellingParity() {
		return sellingParity;
	}

	public void setSellingParity(BigDecimal sellingParity) {
		this.sellingParity = sellingParity;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

}
