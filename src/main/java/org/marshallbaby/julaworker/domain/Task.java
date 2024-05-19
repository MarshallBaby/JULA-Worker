package org.marshallbaby.julaworker.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    private UUID taskId;
    private String requestPayload;
    private String responsePayload;

}
