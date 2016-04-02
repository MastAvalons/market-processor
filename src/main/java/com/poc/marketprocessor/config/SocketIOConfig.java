package com.poc.marketprocessor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.corundumstudio.socketio.SocketIOServer;

@Configuration
public class SocketIOConfig {
	
	private static final String HOSTNAME = "localhost";

	@Bean 
	public SocketIOServer socketIOServer() {
		
		com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(HOSTNAME);
        config.setPort(9092);
        config.setMaxFramePayloadLength(1024 * 1024);
        config.setMaxHttpContentLength(1024 * 1024);

        final SocketIOServer server = new SocketIOServer(config);
        
        server.start();
        
		return server;
	}

}
