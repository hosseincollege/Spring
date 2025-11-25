package com.smartbms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
public class BackendJavaApplication {

    private static final Logger logger = LoggerFactory.getLogger(BackendJavaApplication.class);

    public static void main(String[] args) {
        logger.info("✅ BackendJava Application starting...");
        SpringApplication.run(BackendJavaApplication.class, args);
        logger.info("✅ BackendJava Application started successfully!");
        logger.info("Listening on http://localhost:8080  ← check this port!");
    }
}
