package com.poc.marketprocessor.test;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.poc.marketprocessor.config.AppConfig;
import com.poc.marketprocessor.config.DataJpaConfig;
import com.poc.marketprocessor.config.DataSourceConfig;
import com.poc.marketprocessor.config.Jackson2ObjectMapperConfig;
import com.poc.marketprocessor.config.JmsConfig;
import com.poc.marketprocessor.config.JpaConfig;
import com.poc.marketprocessor.config.SocketIOConfig;
import com.poc.marketprocessor.config.WebConfig;
import com.poc.marketprocessor.entity.Trade;
import com.poc.marketprocessor.model.TradeDTO;
import com.poc.marketprocessor.model.TradeDashboardDTO;
import com.poc.marketprocessor.model.TradeDetailsDTO;
import com.poc.marketprocessor.repository.TradeRepository;
import com.poc.marketprocessor.service.TradeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class,
		Jackson2ObjectMapperConfig.class, DataSourceConfig.class,
		JpaConfig.class, DataJpaConfig.class, WebConfig.class, JmsConfig.class, SocketIOConfig.class }) 
@WebAppConfiguration
public class TradeServiceTest {

    @Inject TradeService tradeService;
    
    @Inject TradeRepository tradeRepository;
    
    public TradeServiceTest() {
    	
    }


    @Test
    public void testSaveTrade() {
        TradeDTO form = new TradeDTO();

        form.setUserId(new Long(10));
        form.setAmountBuy(new BigDecimal(2));
        form.setOriginatingCountry("BR");
        form.setAmountSell(new BigDecimal(50));

        Trade trade = tradeService.saveTrade(form);
        assertNotNull(trade.getId());
    }
    
    @Test
    public void testListTradeByQuantity() {
    	Integer quantity = 10;
        List<Trade> list = tradeRepository.findLastTrades(
    			new PageRequest(0, quantity)).getContent();
        assertNotNull(list);
    }
    
    @Test
    public void testFindTopTradeByOriginatingCountry(){
    	List<TradeDetailsDTO> result = tradeRepository.findOriginatingCountryTrades();
    	assertNotNull(result);
    }

    @Test
    public void testGetTotalOfTrades(){
    	Long result = tradeRepository.count();
    	assertNotNull(result);
    } 
     
    @Test
    public void testGetTradeDashboard(){
    	TradeDashboardDTO dashboardDTO = tradeService.getTradeDashboard(10);
    	assertNotNull(dashboardDTO);
    }
   

}
