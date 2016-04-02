package com.poc.marketprocessor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import com.poc.marketprocessor.DTOUtils;

@Configuration
@ComponentScan(
    basePackageClasses = {DTOUtils.class},
    excludeFilters = {
        @Filter(
            type = FilterType.ANNOTATION,
            value = {
                RestController.class,
                ControllerAdvice.class,
                Configuration.class
            }
        )
    }
)
@PropertySource(value="classpath:/app.properties", ignoreResourceNotFound=true )
@PropertySource(value = "classpath:/database.properties", ignoreResourceNotFound = true)
public class AppConfig {

}
