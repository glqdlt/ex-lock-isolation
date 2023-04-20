package com.glqdlt.ex.lockisolation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
public class LockIsolationApplication {

    public static void main(String[] args) {
        SpringApplication.run(LockIsolationApplication.class, args);
    }

}
