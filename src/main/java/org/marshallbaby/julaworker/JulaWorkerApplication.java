package org.marshallbaby.julaworker;

import org.marshallbaby.julaworker.service.TaskReceiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class JulaWorkerApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(JulaWorkerApplication.class, args);

        TaskReceiver taskReceiver = applicationContext.getBean(TaskReceiver.class);
        taskReceiver.start();
    }

}
