package com.codecool.cheapflightsearch.service;

import com.codecool.cheapflightsearch.model.FlightData;
import org.springframework.http.ResponseEntity;

import java.time.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class DataManipulator {

    public static HashMap<String, String> countryCode;
        static {
            countryCode = new HashMap<>();
            countryCode.put("budapest", "BUD");
            countryCode.put("amsterdam", "AMS");
            countryCode.put("vienna", "VIE");
            countryCode.put("rome", "CIA");
            countryCode.put("paris", "ORY");
            countryCode.put("london", "LHR");
            countryCode.put("charleroi", "CRL");
            countryCode.put("zagreb", "ZAG");
            countryCode.put("tirana", "TIA");
            countryCode.put("copenhagen", "CPH");
            countryCode.put("riga", "RIX");
            countryCode.put("prague", "PRG");
            countryCode.put("warsaw", "WAW");
            countryCode.put("lisbon", "LIS");
            countryCode.put("tallin", "TLL");
            countryCode.put("helsinki", "HEL");
            countryCode.put("moscow", "VKO");
            countryCode.put("oslo", "OSL");
            countryCode.put("berlin", "TXL");
            countryCode.put("stockholm", "BMA");
            countryCode.put("madrid", "MAD");
        }

    private List<LinkedHashMap<String, String>> listOfFlightResult = new ArrayList<>();

    public void createMapFromData(ResponseEntity<FlightData> response) {
        for (int i = 0; i < response.getBody().getData().size(); i++) {
            LinkedHashMap<String, String> flightResult = new LinkedHashMap<String, String>();
            flightResult.put("departure", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getDeparture().getIataCode());
            flightResult.put("departure_time", convertStringToDateString(response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getDeparture().getAt()));
            flightResult.put("arrival", response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getArrival().getIataCode());
            flightResult.put("arrival_time", convertStringToDateString(response.getBody().getData().get(i).getOfferItems().get(0).getServices().get(0).getSegments().get(0).getFlightSegment().getArrival().getAt()));
            flightResult.put("price", response.getBody().getData().get(i).getOfferItems().get(0).getPrice().getTotal());
            listOfFlightResult.add(flightResult);
        }
    }

    public List<LinkedHashMap<String, String>> getListOfFlightResult() {
        return listOfFlightResult;
    }

    private String convertStringToDateString(String dateString) {
        Instant instant = Instant.parse(dateString);
        LocalDateTime departure_time = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        return departure_time.toLocalTime().toString();
    }


}
