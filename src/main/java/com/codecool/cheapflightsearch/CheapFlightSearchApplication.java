package com.codecool.cheapflightsearch;

import com.codecool.cheapflightsearch.controller.ApiController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheapFlightSearchApplication {

    @Autowired
    ApiController apiController;


    public static void main(String[] args) {

        SpringApplication.run(CheapFlightSearchApplication.class, args);

    }

}

