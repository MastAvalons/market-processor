package com.poc.marketprocessor.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.poc.marketprocessor.entity.Trade;
import com.poc.marketprocessor.model.TradeDetailsDTO;

public interface TradeRepository extends JpaRepository<Trade, Long>,//
        JpaSpecificationExecutor<Trade>{
	
	String QUERY_FIND_LAST_TRADES = "SELECT t FROM Trade t ORDER BY t.id DESC "; 
	
	String QUERY_FIND_TRADE_BY_ORIGINATING_COUNTRY = "SELECT new com.poc.marketprocessor.model.TradeDetailsDTO(t.originatingCountry, COUNT(t) as total_quantity) FROM Trade t GROUP BY t.originatingCountry ORDER BY total_quantity DESC ";
	
	@Query(value = QUERY_FIND_TRADE_BY_ORIGINATING_COUNTRY)
	List<TradeDetailsDTO> findOriginatingCountryTrades();
	
	@Query(value = QUERY_FIND_LAST_TRADES)
	Page<Trade> findLastTrades(Pageable page);  
 
}
