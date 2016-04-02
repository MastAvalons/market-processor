package com.poc.marketprocessor.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.poc.marketprocessor"})
public class DataJpaConfig {


}
