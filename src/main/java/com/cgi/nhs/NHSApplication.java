package com.cgi.nhs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by rabia on 02/07/17.
 */
@Slf4j
@SpringBootApplication
public class NHSApplication {
    public static void main(String[] args){
        log.info("In Main");
        SpringApplication.run(NHSApplication.class,args);

    }
}
