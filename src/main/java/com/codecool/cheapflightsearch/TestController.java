package com.codecool.cheapflightsearch;

import com.codecool.cheapflightsearch.model.FlightData;
import com.codecool.cheapflightsearch.service.DataController;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class TestController {

    private String accessToken;
    private DataController dataController = new DataController();

    private HashMap<String, String> countryCode = new HashMap<>();
    private HashMap<String, String> createCountryCodeMap(){
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
        return countryCode;
    }



    private List<LinkedHashMap<String, String>> getFromToPrice(String url) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<FlightData> response = template.exchange(url, HttpMethod.GET, entity, FlightData.class);

        dataController.createMapFromData(response);
        List<LinkedHashMap<String, String>> listToDisplay = new ArrayList<>(dataController.getListOfFlightResult());
        dataController.getListOfFlightResult().clear();
        return listToDisplay;
    }

    private String createURL(String from, String to, String date) {
        createCountryCodeMap();
        String goodFrom = countryCode.get(from.toLowerCase());
        System.out.println(goodFrom);
        String goodTo = countryCode.get(to.toLowerCase());
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

    @CrossOrigin
    @GetMapping(value = "/getfromtoprice/{from}/{to}/{date}", produces = "application/json")
    public List<LinkedHashMap<String, String>> getFromToPriceWithDate(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("date") String date) throws Exception {
        String goodURL = createURL(from, to, date);
        try {
            return getFromToPrice(goodURL);
        } catch (Exception e) {
            sendAuthorisationPostRequest();
            return getFromToPrice(goodURL);
        }
    }

}

