package org.marshallbaby.julaworker.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.domain.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskReceiver {

    @Value("${jula.connector.poll.timeout}")
    private Long timeout;

    @Value("${jula.worker.capacity}")
    private Integer capacity;

    private final ThreadPoolExecutor julaThreadPoolExecutor;
    private final JulaConnectorClient julaConnectorClient;

    @SneakyThrows
    public void start() {

        while (true) {


            if (julaThreadPoolExecutor.getActiveCount() >= capacity) {

                log.debug("Worker is busy. Skipping ");
                return;
            }

            fetchAndPushTask();
            Thread.sleep(timeout);
        }

    }

    private void fetchAndPushTask() {

        Optional<Task> task = julaConnectorClient.getWaitingTask();

        if (task.isPresent()) {

            julaThreadPoolExecutor.submit(() -> {

                log.info("EXEC: [{}].", task.get().getTaskId());
            });

        } else {

            log.info("No waiting task found.");
        }
    }

}
