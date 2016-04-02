package com.poc.marketprocessor.jms;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.stereotype.Component;

import com.poc.marketprocessor.exception.TradeQueueProcessorException;
import com.poc.marketprocessor.model.TradeDTO;
import com.poc.marketprocessor.service.SocketIOService;
import com.poc.marketprocessor.service.TradeService;

@Component
public class TradeQueue implements MessageListener {
	
	public static final String QUEUE_NAME = "trade";
	
	@Inject
	private TradeService tradeService;
	
	@Inject
	private SocketIOService socketIOService;
	
    @Override
    public void onMessage(Message message) {
		try {
			TradeDTO dto = (TradeDTO) ((ObjectMessage)message).getObject();
			
			 
			//Save the new trade 
			tradeService.saveTrade(dto); 
			
			//Broadcast new trade to all clients
			socketIOService.broadcastDashboardTrade();
		} catch (JMSException e) {
			throw new TradeQueueProcessorException(e);
		}
   
    }
}