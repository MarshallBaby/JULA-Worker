package org.marshallbaby.julaworker.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.domain.Task;
import org.marshallbaby.julaworker.service.ai.AiEngineService;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class TaskRunnable implements Runnable {

    private final JulaConnectorClient julaConnectorClient;
    private final AiEngineService aiEngineService;

    @Setter
    private Task task = null;

    @Override
    public void run() {

        validateTaskIsNotNull();
        log.info("Running task: [{}].", task.getTaskId());

        aiEngineService.executeTask(task);
        task.setRequestPayload(null);
        julaConnectorClient.updateTask(task);
        log.info("Task complete: [{}].", task.getTaskId());
    }

    private void validateTaskIsNotNull() {

        if (Objects.isNull(task)) {
            throw new IllegalStateException("Task must not be null.");
        }
    }

}
