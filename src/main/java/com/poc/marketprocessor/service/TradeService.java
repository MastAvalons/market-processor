package com.poc.marketprocessor.service;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.poc.marketprocessor.DTOUtils;
import com.poc.marketprocessor.entity.Trade;
import com.poc.marketprocessor.model.TradeDTO;
import com.poc.marketprocessor.model.TradeDashboardDTO;
import com.poc.marketprocessor.repository.TradeRepository;

@Service
@Transactional
public class TradeService {

	private TradeRepository repository;

	@Inject
	public TradeService(TradeRepository repository) {
		this.repository = repository;
	}

	public Trade saveTrade(TradeDTO form) {
		Trade trade = DTOUtils.map(form, Trade.class);
		return repository.save(trade);
	}

	public TradeDashboardDTO getTradeDashboard(Integer tradesRowCount){ 
    	TradeDashboardDTO dashboardDTO = new TradeDashboardDTO();
    	
    	dashboardDTO.setQuantityTransaction(repository.count());
    	
    	dashboardDTO.setTrades(repository.findLastTrades(
    			new PageRequest(0, tradesRowCount)).getContent());
    	
    	dashboardDTO.setTradesDetails(repository.findOriginatingCountryTrades());
    	
    	return dashboardDTO;
	}

}
