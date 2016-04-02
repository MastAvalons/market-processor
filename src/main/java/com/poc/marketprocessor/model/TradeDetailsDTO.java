package com.poc.marketprocessor.model;

import java.io.Serializable;

public class TradeDetailsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String originatingCountry;

    private Long quantity;
    
    public TradeDetailsDTO(String originatingCountry, Long quantity){
    	this.originatingCountry = originatingCountry;
    	this.quantity = quantity;
    }

    public String getOriginatingCountry() {
		return originatingCountry;
	}

	public void setOriginatingCountry(String originatingCountry, Long quantity) {
		this.originatingCountry = originatingCountry;
		this.quantity = quantity;
	}


	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
    public String toString() {
        return "TradeDetails{" + "originatingCountry=" + originatingCountry + ", quantity=" + quantity + '}';
    }
	

}
