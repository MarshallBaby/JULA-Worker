package org.marshallbaby.julaworker.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.domain.Task;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskReceiver {

    @Value("${jula.connector.poll.timeout}")
    private Long timeout;

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;
    private final JulaConnectorClient julaConnectorClient;
    private final ApplicationContext applicationContext;

    @SneakyThrows
    public void start() {

        while (true) {
            fetchAndPushTask();
            TimeUnit.MICROSECONDS.sleep(timeout);
        }

    }

    private void fetchAndPushTask() {

        Optional<Task> task = julaConnectorClient.getWaitingTask();

        if (task.isPresent()) {

            log.info("Received task: [{}].", task.get().getTaskId());

            TaskRunner taskRunner = applicationContext.getBean(TaskRunner.class);
            taskRunner.setTask(task.get());

            threadPoolTaskExecutor.submit(taskRunner);

        } else {

            log.debug("No waiting task found.");
        }
    }

}
