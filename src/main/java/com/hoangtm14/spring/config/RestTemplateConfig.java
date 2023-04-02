package com.hoangtm14.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${rest.client.connection-timeout}")
    private int connectionTimeOut;
    @Value("${rest.client.read-timeout}")
    private int readTimeOut;

    private SimpleClientHttpRequestFactory factory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(this.connectionTimeOut);
        clientHttpRequestFactory.setReadTimeout(this.readTimeOut);
        return clientHttpRequestFactory;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(factory());
    }

}
