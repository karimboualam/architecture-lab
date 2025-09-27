// src/main/java/com/architecturelab/hexagonal/App.java
package com.architecturelab.hexagonal;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableBatchProcessing
@EnableScheduling // 👈 planning des exports auto
public class App {
  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }
}
