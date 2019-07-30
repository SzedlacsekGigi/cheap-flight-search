package com.codecool.cheapflightsearch.model;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class Flight {

    @NotEmpty
    private String origin;
    private String destination;
    private Date departureDate;
    private Date returnDate;
    private int airlineCode;
    private int price;

    public Flight(String origin, String destination, Date departureDate, Date returnDate, int airlineCode, int price){
        this.origin = origin;
        this.destination = destination;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.airlineCode = airlineCode;
        this.price = price;
    }

    public Flight(){
    }



}
