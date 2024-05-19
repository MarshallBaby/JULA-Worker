package org.marshallbaby.julaworker.config;

import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.client.JulaConnectorClientImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

    @Bean
    public RestTemplate julaConnectorRestTemplate(@Value("${jula.connector.url}") String julaConnectorUrl) {

        return new RestTemplateBuilder()
                .rootUri(julaConnectorUrl)
                .build();
    }


    @Bean
    public JulaConnectorClient julaConnectorClient(RestTemplate julaConnectorRestTemplate) {

        return new JulaConnectorClientImpl(julaConnectorRestTemplate);
    }

}
