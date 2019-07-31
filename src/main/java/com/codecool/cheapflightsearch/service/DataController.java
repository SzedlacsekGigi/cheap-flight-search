package com.codecool.cheapflightsearch.service;

import com.codecool.cheapflightsearch.model.FlightData;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataController {

    List<HashMap<String, String>> listOfFlightResult = new ArrayList<>();


    public void createHashmapFromData(ResponseEntity<FlightData> response) {
        for (int i = 0; i < response.getBody().getData().size(); i++) {
            HashMap<String, String> flightResult = new HashMap<String, String>();
            flightResult.put("departure", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getDeparture().getIataCode());
            flightResult.put("departure_time", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getDeparture().getAt());
            flightResult.put("arrival", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getArrival().getIataCode());
            flightResult.put("arrival_time", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getArrival().getAt());
            flightResult.put("price", response.getBody().getData().get(i).getOfferItems().get(0).getPrice().getTotal());
            listOfFlightResult.add(flightResult);
        }
    }

    public List<HashMap<String, String>> getListOfFlightResult() {
        return listOfFlightResult;
    }
}
