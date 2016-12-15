package com.aeon;

import com.aeon.config.ResponseDataProcessor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;

@SpringBootApplication
@ComponentScan(basePackages = {"com.aeon", "com.auth0.spring.security.api"})
@EnableAutoConfiguration
@PropertySources({
		@PropertySource("classpath:application.properties"),
		@PropertySource("classpath:auth0.properties")
})
public class GbfResourceApiApplication {
        
    @Bean
    public HibernateJpaSessionFactoryBean sessionFactory() {
        return new HibernateJpaSessionFactoryBean();
    }
    
    @Bean
    public Gson gsonBuilder() {
        return new GsonBuilder().create();
    }
    
    @Bean
    public ResponseDataProcessor responseProcessor()  {
        return new ResponseDataProcessor();
    }

    public static void main(String[] args) {
        SpringApplication.run(GbfResourceApiApplication.class, args);
    }
}
