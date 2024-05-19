package org.marshallbaby.julaworker.config;

import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.service.TaskRunnable;
import org.marshallbaby.julaworker.service.ai.AiEngineService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class TaskRunnableConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public TaskRunnable taskRunnable(
            JulaConnectorClient julaConnectorClient,
            AiEngineService aiEngineService
    ) {

        return new TaskRunnable(julaConnectorClient, aiEngineService);
    }

}
