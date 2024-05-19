package org.marshallbaby.julaworker.client;

import lombok.RequiredArgsConstructor;
import org.marshallbaby.julaworker.domain.Task;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RequiredArgsConstructor
public class JulaConnectorClientImpl implements JulaConnectorClient {

    private static final String TASK_URL = "/api/task";

    private final RestTemplate julaConnectorRestTemplate;

    @Override
    public Optional<Task> getWaitingTask() {

        try {

            return Optional.ofNullable(julaConnectorRestTemplate.getForObject(TASK_URL, Task.class));

        } catch (HttpStatusCodeException hsce) {

            if (hsce.getStatusCode() == HttpStatus.NOT_FOUND) {

                return Optional.empty();
            }

            throw hsce;
        }
    }

    @Override
    public void updateTask(Task task) {

        julaConnectorRestTemplate.put(TASK_URL, task);
    }
}
