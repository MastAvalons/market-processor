package com.poc.marketprocessor.model;

import java.io.Serializable;
import java.util.List;

import com.poc.marketprocessor.DTOUtils;
import com.poc.marketprocessor.entity.Trade;

public class TradeDashboardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Trade> trades;
    
    private List<TradeDetailsDTO> tradesDetails;
    
    private Long quantityTransaction;
    
    public TradeDashboardDTO(){
    	
    }
    
    public TradeDashboardDTO(List<Trade> trades,
			List<TradeDetailsDTO> tradesDetails, Long quantityTransaction) {
		super();
		this.trades = trades;
		this.tradesDetails = tradesDetails;
		this.quantityTransaction = quantityTransaction;
	}

	public List<Trade> getTrades() {
		return trades;
	}

	public void setTrades(List<Trade> trades) {
		this.trades = trades;
	}

	public List<TradeDetailsDTO> getTradesDetails() {
		return tradesDetails;
	}

	public void setTradesDetails(List<TradeDetailsDTO> tradesDetails) {
		this.tradesDetails = tradesDetails;
	}


    public Long getQuantityTransaction() {
		return quantityTransaction;
	}

	public void setQuantityTransaction(Long quantityTransaction) {
		this.quantityTransaction = quantityTransaction;
	}



    public TradeDashboardDTO toEntity() {
        return DTOUtils.map(this, TradeDashboardDTO.class);
    }

}
