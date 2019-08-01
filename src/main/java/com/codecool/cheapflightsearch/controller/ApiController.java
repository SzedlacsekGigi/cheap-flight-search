package com.codecool.cheapflightsearch.controller;

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

    private HashMap<String, String> countryCode = new HashMap<>();
    private HashMap<String, String> createCountryCodeMap(){
        countryCode.put("Budapest", "BUD");
        countryCode.put("Graz", "GRZ");
        countryCode.put("Yerevan Zvartnots", "EVN");
        countryCode.put("Baku", "GYD");
        countryCode.put("Innsbruck", "INN");
        countryCode.put("Klagenfurt", "KLU");
        countryCode.put("Linz", "LNZ");
        countryCode.put("Salzburg", "SZG");
        countryCode.put("Vienna", "VIE");
        countryCode.put("Minsk", "MSQ");
        countryCode.put("Antwerp", "ANR");
        countryCode.put("Brussels", "BRU");
        countryCode.put("Charleroi", "CRL");
        countryCode.put("Liege", "LGG");
        countryCode.put("Ostend Bruges", "OST");
        countryCode.put("Sarajevo", "SJJ");
        countryCode.put("Tuzla", "TZL");
        countryCode.put("Burgas", "BOJ");
        countryCode.put("Sofia", "SOF");
        countryCode.put("Varna", "VAR");
        countryCode.put("Dubrovnik", "DBV");
        countryCode.put("Pula", "PUY");
        countryCode.put("Split", "SPU");
        countryCode.put("Zadar", "ZAD");
        countryCode.put("Zagreb", "ZAG");
        countryCode.put("Larnaca", "LCA");
        countryCode.put("Paphos", "PFO");
        countryCode.put("Brno", "BRQ");
        countryCode.put("Prague", "PRG");
        countryCode.put("Aalborg", "AAL");
        countryCode.put("Aarhus", "AAR");
        countryCode.put("Billund", "BLL");
        countryCode.put("Copenhagen", "CPH");
        countryCode.put("Tallinn", "TLL");
        countryCode.put("Helsinki Vantaa", "HEL");
        countryCode.put("Oulu", "OUL");
        countryCode.put("Rovaniemi", "RVN");
        countryCode.put("Tampere", "TMP");
        countryCode.put("Turku", "TKU");
        countryCode.put("Vaasa", "VAA");
        countryCode.put("Ajaccio", "AJA");
        countryCode.put("Basel", "BSL");
        countryCode.put("Mulhouse", "MLH");
        countryCode.put("Bastia", "BIA");
        countryCode.put("Bergerac", "EGC");
        countryCode.put("Biarritz", "BIQ");
        countryCode.put("Bordeaux", "BOD");
        countryCode.put("Brest Bretagne", "BES");
        countryCode.put("Figari South Corsica ", "FSC");
        countryCode.put("Lille", "LIL");
        countryCode.put("Lyon-Saint Exupéry", "LYS");
        countryCode.put("Marseille", "MRS");
        countryCode.put("Montpellier", "MPL");
        countryCode.put("Nantes", "NTE");
        countryCode.put("Nice", "NCE");
        countryCode.put("Paris Beauvais", "BVA");
        countryCode.put("Paris Charles de Gaulle", "CDG");
        countryCode.put("Paris Orly", "ORY");
        countryCode.put("Strasbourg", "SXB");
        countryCode.put("Toulon-Hyères", "TLN");
        countryCode.put("Toulouse Blagnac", "TLS");
        countryCode.put("Tbilisi", "TBS");
        return countryCode;
    }



    private List<LinkedHashMap<String, String>> getFromToPrice(String url) {

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(this.accessToken);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

        ResponseEntity<FlightData> response = template.exchange(url, HttpMethod.GET, entity, FlightData.class);

        dataManipulator.createMapFromData(response);
        List<LinkedHashMap<String, String>> listToDisplay = new ArrayList<>(dataManipulator.getListOfFlightResult());
        dataManipulator.getListOfFlightResult().clear();
        return listToDisplay;
    }

    private String createURL(String from, String to, String date) {
        createCountryCodeMap();
        String goodFrom = countryCode.get(from);
        System.out.println(goodFrom);
        String goodTo = countryCode.get(to);
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

    @CrossOrigin //nem kell api key a front olvalról
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

