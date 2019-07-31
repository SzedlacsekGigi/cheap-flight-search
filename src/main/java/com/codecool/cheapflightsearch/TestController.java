package com.codecool.cheapflightsearch;

import com.amadeus.resources.FlightOffer;
import com.codecool.cheapflightsearch.model.FlightData;
import com.codecool.cheapflightsearch.model.OfferItem;
import com.codecool.cheapflightsearch.service.DataController;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class TestController {

    private String accessToken;
    private DataController dataController = new DataController();


    public List<HashMap<String, String>> getFromToPrice(String from, String to, String date) throws JSONException {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<FlightData> response = template.exchange("https://test.api.amadeus.com/v1/shopping/flight-offers?origin=" +
                from +
                "&destination=" +
                to +
                "&departureDate=" +
                date + "&adults=1&nonStop=true&max=50", HttpMethod.GET, entity, FlightData.class);

        dataController.createHashmapFromData(response);
        List<HashMap<String, String>> listToDisplay = new ArrayList<>(dataController.getListOfFlightResult());
        dataController.getListOfFlightResult().clear();
        return listToDisplay;
    }

    private void sendPost() throws Exception {

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
//
//        int responseCode = con.getResponseCode();
//        System.out.println("\nSending 'POST' request to URL : " + url);
//        System.out.println("Post parameters : " + urlParameters);
//        System.out.println("Response Code : " + responseCode);

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

    @GetMapping(value = "/getfromtoprice/{from}/{to}/{date}", produces = "application/json")
    public List<HashMap<String, String>> testApi2(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("date") String date) throws Exception {
        try {
            return getFromToPrice(from, to, date);
        } catch (Exception e) {
            sendPost();
            return getFromToPrice(from, to, date);
        }
    }

}

