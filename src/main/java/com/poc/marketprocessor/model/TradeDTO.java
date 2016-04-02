package com.poc.marketprocessor.model;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;


public class TradeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    private Long userId;
    
    @NotNull(message = "The field 'currencyFrom' is required")
    private String currencyFrom;
    
    @NotNull(message = "The field 'currencyTo' is required")
    private String currencyTo;
    
    @NotNull(message = "The field 'amountSell' is required")
    private BigDecimal amountSell;
    
    @NotNull(message = "The field 'amountBuy' is required")
    private BigDecimal amountBuy;
    
    @NotNull(message = "The field 'rate' is required")
    private Double rate;
    
    @NotNull(message = "The field 'timePlaced' is required") 
    @JsonFormat(shape=Shape.STRING, pattern="dd-MMM-yy HH:mm:ss")     
    private Date timePlaced;
    
    @NotNull(message = "The field 'originatingCountry' is required")
    private String originatingCountry;
    

    public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getCurrencyFrom() {
		return currencyFrom;
	}

	public void setCurrencyFrom(String currencyFrom) {
		this.currencyFrom = currencyFrom;
	}

	public String getCurrencyTo() {
		return currencyTo;
	}

	public void setCurrencyTo(String currencyTo) {
		this.currencyTo = currencyTo;
	}

	public Date getTimePlaced() {
		return timePlaced;
	}

	public void setTimePlaced(Date timePlaced) {
		this.timePlaced = timePlaced;
	}

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public BigDecimal getAmountSell() {
		return amountSell;
	}

	public void setAmountSell(BigDecimal amountSell) {
		this.amountSell = amountSell;
	}

	public BigDecimal getAmountBuy() {
		return amountBuy;
	}

	public void setAmountBuy(BigDecimal amountBuy) {
		this.amountBuy = amountBuy;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	@Override
    public String toString() {
        return "TradeForm{ userId=" +userId +",currencyFrom=" +currencyFrom + ", currencyTo=" + currencyTo +", "
        		+ "amountSell=" +amountSell+",amountBuy=" + amountBuy + ",rate=" + rate + ", timePlaced=" + timePlaced + ", originatingCountry="+ originatingCountry+ '}';
    }
}
