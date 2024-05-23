package org.marshallbaby.julaworker.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.marshallbaby.julaworker.domain.Task;
import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatService {

    @Value("classpath:/prompt/system-message.st")
    private Resource systemMessageStringTemplate;

    private final ChatClient chatClient;
    private Message systemMessage;

    @PostConstruct
    private void init() {
        this.systemMessage = new SystemPromptTemplate(systemMessageStringTemplate).createMessage();
    }

    public void executeTask(Task task) {

        Prompt prompt = buildPrompt(task.getRequestPayload());
        ChatResponse response = chatClient.call(prompt);
        String responsePayload = response.getResult().getOutput().getContent();
        task.setResponsePayload(responsePayload);
    }

    private Prompt buildPrompt(String requestPayload) {

        UserMessage userMessage = new UserMessage(requestPayload);
        return new Prompt(List.of(userMessage, systemMessage));
    }
}
