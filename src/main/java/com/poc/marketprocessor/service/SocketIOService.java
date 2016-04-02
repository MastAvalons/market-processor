package com.poc.marketprocessor.service;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.ClientOperations;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

@Service
public class SocketIOService {
	
	@Inject
	private SocketIOServer socketIO;
	
	@Inject
	private TradeService tradeService;
	
	/**
	 * Listener that receives a dashboard request from a single client
	 */
	@PostConstruct
	public void dashboardTradedRequestListener(){
		socketIO.addEventListener("request-dashboard-trade",  Object.class, new DataListener<Object>() {
            @Override
            public void onData(SocketIOClient client, Object data, AckRequest ackRequest) {
            	sendDashboardTrade(client);
                
            }
        });
	} 
	
	/**
	 * Broadcast to all clients the dashboard trade update
	 * 
	 */
	public void broadcastDashboardTrade(){
		sendDashboardTrade(socketIO.getBroadcastOperations());
	}
	
	/**
	 * Send the Dashboard to the client
	 * 
	 * @param clientOperations - Can be a single client or broadcast to all client
	 */
	private void sendDashboardTrade(ClientOperations clientOperations){
		clientOperations.sendEvent("dashboard-trade", 
				tradeService.getTradeDashboard(15));
	}

}
