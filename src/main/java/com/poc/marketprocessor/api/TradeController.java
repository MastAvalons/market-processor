package com.poc.marketprocessor.api;

import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.poc.marketprocessor.model.ResponseMessage;
import com.poc.marketprocessor.model.TradeDTO;
import com.poc.marketprocessor.service.TradeService;

@RestController
public class TradeController {

	private static final String SUCCESS = "Success";

	@Inject
	JmsTemplate template;

	@Inject
	TradeService service;

	@RequestMapping(value = "/api/trades", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ResponseMessage> createTrade(@Valid @RequestBody TradeDTO dto) {
		template.send(new MessageCreator() { 
 
			@Override
			public Message createMessage(Session session) throws JMSException {

				return session.createObjectMessage(dto);
			}
		});

		return new ResponseEntity<>(ResponseMessage.success(SUCCESS),
				HttpStatus.CREATED);
	} 

}
