package com.project.Callyia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.project.Callyia.repository")
public class CallyiaApplication {
    public static void main(String[] args) {
        SpringApplication.run(CallyiaApplication.class, args);
        System.out.println("http://localhost:8080/Callyia");
    }


}
