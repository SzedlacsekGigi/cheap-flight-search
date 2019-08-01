package com.codecool.cheapflightsearch.controller;

import com.codecool.cheapflightsearch.model.AirplaneData;
import com.codecool.cheapflightsearch.model.Carriers;
import com.codecool.cheapflightsearch.model.FlightData;
import com.codecool.cheapflightsearch.service.DataManipulator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class ApiController {

    private String accessToken;
    private DataManipulator dataManipulator = new DataManipulator();

    private List<LinkedHashMap<String, String>> getFromToPrice(String url) {
        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        try {
            ResponseEntity<FlightData> response = template.exchange(url, HttpMethod.GET, entity, FlightData.class);
            dataManipulator.createMapFromData(response);
            List<LinkedHashMap<String, String>> listToDisplay = new ArrayList<>(dataManipulator.getListOfFlightResult());
            dataManipulator.getListOfFlightResult().clear();
            return listToDisplay;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private String createURL(String from, String to, String date) {
        String goodFrom = DataManipulator.countryCode.get(from.toLowerCase());
        String goodTo = DataManipulator.countryCode.get(to.toLowerCase());
        return "https://test.api.amadeus.com/v1/shopping/flight-offers?origin=" +
                goodFrom +
                "&destination=" +
                goodTo +
                "&departureDate=" +
                date + "&adults=1&nonStop=true&max=50";
    }

    private void sendAuthorisationPostRequest() throws IOException {

        String url = "https://test.api.amadeus.com/v1/security/oauth2/token";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        String urlParameters = "grant_type=client_credentials&client_id=otaAKsRpDC0aOjAGM0buqAdNquOoHGk1&client_secret=Jk5bAfom5UIcG6a2";
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        ObjectMapper mapper = new ObjectMapper();
        AuthObject authObject = mapper.readValue(response.toString(), AuthObject.class);
        this.accessToken = authObject.accessToken;

    }

    @CrossOrigin //nem kell api key a front oldalról
    @GetMapping(value = "/getfromtoprice/{from}/{to}/{date}", produces = "application/json")
    public List<LinkedHashMap<String, String>> getFromToPriceWithDate(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("date") String date) throws Exception {
        String goodURL = createURL(from, to, date);
        sendAuthorisationPostRequest();
        try {
            return getFromToPrice(goodURL);
        } catch (Exception e) {
            return getFromToPrice(goodURL);
        }
    }


}

