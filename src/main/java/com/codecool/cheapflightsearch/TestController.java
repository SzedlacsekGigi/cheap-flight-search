package com.codecool.cheapflightsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

@RestController
@RequestMapping
public class TestController {

    private String accessToken;


    public String getFromToPrice(String from, String to, int numberOfOptions) throws JSONException {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<String> response = template.exchange("https://test.api.amadeus.com/v1/shopping/flight-offers?origin=" +
                from +
                "&destination=" +
                to +
                "&departureDate=2019-10-01&adults=1&nonStop=true&max=" + numberOfOptions, HttpMethod.GET, entity, String.class);

        return response.getBody();
    }

    @GetMapping(value = "/getfromtoprice/{from}/{to}/{numberOfOptions}", produces = "application/json")
    public String testApi2(@PathVariable("from") String from, @PathVariable("to") String to, @PathVariable("numberOfOptions") int numberOfOptions) throws Exception {
        try {
            return getFromToPrice(from, to, numberOfOptions);
        } catch (Exception e) {
            sendPost();
            return getFromToPrice(from, to, numberOfOptions);
        }
    }

    private void sendPost() throws Exception {

        String url = "https://test.api.amadeus.com/v1/security/oauth2/token";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        String urlParameters = "grant_type=client_credentials&client_id=otaAKsRpDC0aOjAGM0buqAdNquOoHGk1&client_secret=Jk5bAfom5UIcG6a2";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

        ObjectMapper mapper = new ObjectMapper();
        AuthObject authObject = mapper.readValue(response.toString(), AuthObject.class);
        this.accessToken = authObject.accessToken;

    }
}

