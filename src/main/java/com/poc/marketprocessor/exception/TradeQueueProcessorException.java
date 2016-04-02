package com.poc.marketprocessor.exception;


public class TradeQueueProcessorException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public TradeQueueProcessorException(Exception e) {
		super(e);
	}
 

}
