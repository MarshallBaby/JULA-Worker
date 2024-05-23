package org.marshallbaby.julaworker;

import org.marshallbaby.julaworker.service.TaskReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JulaWorkerApplication {

    public static void main(String[] args) {

        SpringApplication.run(JulaWorkerApplication.class, args)
                .getBean(TaskReceiver.class)
                .start();
    }

}
