package com.poc.marketprocessor.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "trades")
@JsonIgnoreProperties("trade")
public class Trade implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "userId")
    private Long userId;

    @Column(name = "currencyFrom")
    private String currencyFrom;
    
    @Column(name = "currencyTo")
    private String currencyTo;
    
    @Column(name = "amountSell")
    private BigDecimal amountSell;
    
    @Column(name = "amountBuy")
    private BigDecimal amountBuy;

    @Column(name = "rate")
    private Double rate;
    
    @Column(name = "originatingCountry")
    private String originatingCountry;

    @Column(name = "timePlaced")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timePlaced;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getOriginatingCountry() {
		return originatingCountry;
	}

	public void setOriginatingCountry(String originatingCountry) {
		this.originatingCountry = originatingCountry;
	}

	public Date getTimePlaced() {
		return timePlaced;
	}

	public void setTimePlaced(Date timePlaced) {
		this.timePlaced = timePlaced;
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
 
	@Override
    public String toString() {
        return "TradeForm{ userId=" +userId +",currencyFrom=" +currencyFrom + ", currencyTo=" + currencyTo +", "
        		+ "amountSell=" +amountSell+",amountBuy=" + amountBuy + ",rate=" + rate + ", timePlaced=" + timePlaced + ", originatingCountry="+ originatingCountry+ '}';
    }

}
