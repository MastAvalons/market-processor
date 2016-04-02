package com.poc.marketprocessor.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.math.BigDecimal;
import java.util.Date;

import javax.inject.Inject;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.marketprocessor.config.AppConfig;
import com.poc.marketprocessor.config.DataJpaConfig;
import com.poc.marketprocessor.config.DataSourceConfig;
import com.poc.marketprocessor.config.Jackson2ObjectMapperConfig;
import com.poc.marketprocessor.config.JmsConfig;
import com.poc.marketprocessor.config.JpaConfig;
import com.poc.marketprocessor.config.SocketIOConfig;
import com.poc.marketprocessor.config.WebConfig;
import com.poc.marketprocessor.model.TradeDTO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class,
		Jackson2ObjectMapperConfig.class, DataSourceConfig.class,
		JpaConfig.class, DataJpaConfig.class, WebConfig.class, JmsConfig.class, SocketIOConfig.class })
@WebAppConfiguration
public class TradeControllerTest {

	@Inject
	WebApplicationContext wac;

	@Inject
	ObjectMapper objectMapper;

	private MockMvc mvc;

	@Before
	public void setup() {
		mvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void testNewTradeRequiredFieldsValidation() throws Exception {
		//Post a Blank JSON
		mvc.perform(
				post("/api/trades").contentType(MediaType.APPLICATION_JSON)
						.content("{}"))
				.andExpect(status().isBadRequest());
	}
	
	@Test
	public void testNewTradeSuccess() throws Exception {
		TradeDTO dto = new TradeDTO();
		dto.setUserId(new Long(123456));
		dto.setCurrencyFrom("EUR");
		dto.setCurrencyTo("GBP");
		dto.setAmountBuy(new BigDecimal(747.10));
		dto.setAmountSell(new BigDecimal(1000));
		dto.setRate(new Double(0.7471));
		dto.setTimePlaced(new Date());
		dto.setOriginatingCountry("FR");
		
		mvc.perform(
				post("/api/trades").contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated()); 

	}

}
