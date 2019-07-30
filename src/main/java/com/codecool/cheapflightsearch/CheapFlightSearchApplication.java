package com.codecool.cheapflightsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheapFlightSearchApplication {

    @Autowired
    TestController testController;


    public static void main(String[] args) {

        SpringApplication.run(CheapFlightSearchApplication.class, args);

    }

}

