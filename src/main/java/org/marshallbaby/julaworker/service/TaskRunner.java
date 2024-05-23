package org.marshallbaby.julaworker.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.domain.Task;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
public class TaskRunner implements Runnable {

    private final JulaConnectorClient julaConnectorClient;
    private final ChatService chatService;

    @Setter
    private Task task = null;

    @Override
    public void run() {

        validateTaskIsNotNull();
        log.info("Running task: [{}].", task.getTaskId());

        chatService.executeTask(task);
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
