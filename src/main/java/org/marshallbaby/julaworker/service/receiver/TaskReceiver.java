package org.marshallbaby.julaworker.service.receiver;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.marshallbaby.julaworker.client.JulaConnectorClient;
import org.marshallbaby.julaworker.domain.Task;
import org.marshallbaby.julaworker.service.TaskRunnable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskReceiver implements ApplicationContextAware {

    @Value("${jula.connector.poll.timeout}")
    private Long timeout;

    @Value("${jula.worker.capacity}")
    private Integer capacity;

    private final ThreadPoolExecutor julaThreadPoolExecutor;
    private final JulaConnectorClient julaConnectorClient;

    private ApplicationContext applicationContext;

    @SneakyThrows
    public void start() {

        while (true) {


            if (julaThreadPoolExecutor.getActiveCount() >= capacity) {
                log.debug("Worker is busy. Waiting...");
            }
            {
                fetchAndPushTask();
            }

            Thread.sleep(timeout);
        }

    }

    private void fetchAndPushTask() {

        Optional<Task> task = julaConnectorClient.getWaitingTask();

        if (task.isPresent()) {

            log.info("Received task: [{}].", task.get().getTaskId());

            TaskRunnable taskRunnable = applicationContext.getBean(TaskRunnable.class);
            taskRunnable.setTask(task.get());

            julaThreadPoolExecutor.submit(taskRunnable);

        } else {

            log.debug("No waiting task found.");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
