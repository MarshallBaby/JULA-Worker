package org.marshallbaby.julaworker.client;

import org.marshallbaby.julaworker.domain.Task;

import java.util.Optional;

public interface JulaConnectorClient {

    Optional<Task> getWaitingTask();

}
