package com.codecool.cheapflightsearch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlightController {

    @GetMapping("/")
    public String hello() {
        return "Hello";
    }

}
