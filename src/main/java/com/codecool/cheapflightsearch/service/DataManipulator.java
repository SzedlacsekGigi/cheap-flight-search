package com.codecool.cheapflightsearch.service;

import com.codecool.cheapflightsearch.model.FlightData;
import org.springframework.http.ResponseEntity;

import java.time.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class DataManipulator {

    List<LinkedHashMap<String, String>> listOfFlightResult = new ArrayList<>();


    public void createMapFromData(ResponseEntity<FlightData> response) {
        for (int i = 0; i < response.getBody().getData().size(); i++) {
            LinkedHashMap<String, String> flightResult = new LinkedHashMap<String, String>();
            flightResult.put("departure", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getDeparture().getIataCode());
            flightResult.put("departure_time", convertStringtoDateString(response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getDeparture().getAt()));
            flightResult.put("arrival", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getArrival().getIataCode());
            flightResult.put("arrival_time", convertStringtoDateString(response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getDeparture().getAt()));
            flightResult.put("price", response.getBody().getData().get(i).getOfferItems().get(0).getPrice().getTotal());
            listOfFlightResult.add(flightResult);
        }
    }

    public List<LinkedHashMap<String, String>> getListOfFlightResult() {
        return listOfFlightResult;
    }

    private String convertStringtoDateString(String dateString){
        Instant instant = Instant.parse(dateString);
        LocalDateTime departure_time = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        return departure_time.toLocalTime().toString();
    }




}
