package org.marshallbaby.julaworker.service.ai;

import lombok.RequiredArgsConstructor;
import org.marshallbaby.julaworker.domain.Task;
import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiEngineService {

    private final ChatClient chatClient;

    public void executeTask(Task task) {

        String response = chatClient.call(task.getRequestPayload());
        task.setResponsePayload(response);
    }

}
