package org.marshallbaby.julaworker.config;

import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.service.ChatService;
import org.marshallbaby.julaworker.service.TaskRunner;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TaskRunnableConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TaskRunner taskRunnable(
            JulaConnectorClient julaConnectorClient,
            ChatService chatService
    ) {

        return new TaskRunner(julaConnectorClient, chatService);
    }

}
