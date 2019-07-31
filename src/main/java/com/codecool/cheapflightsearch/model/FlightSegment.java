
package com.codecool.cheapflightsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "departure",
    "arrival",
    "carrierCode",
    "number",
    "aircraft",
    "operating",
    "duration"
})
public class FlightSegment {

    @JsonProperty("departure")
    private Departure departure;
    @JsonProperty("arrival")
    private Arrival arrival;
    @JsonProperty("carrierCode")
    private String carrierCode;
    @JsonProperty("number")
    private String number;
    @JsonProperty("aircraft")
    private Aircraft aircraft;
    @JsonProperty("operating")
    private Operating operating;
    @JsonProperty("duration")
    private String duration;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FlightSegment() {
    }

    /**
     * 
     * @param duration
     * @param operating
     * @param aircraft
     * @param arrival
     * @param departure
     * @param number
     * @param carrierCode
     */
    public FlightSegment(Departure departure, Arrival arrival, String carrierCode, String number, Aircraft aircraft, Operating operating, String duration) {
        super();
        this.departure = departure;
        this.arrival = arrival;
        this.carrierCode = carrierCode;
        this.number = number;
        this.aircraft = aircraft;
        this.operating = operating;
        this.duration = duration;
    }

    @JsonProperty("departure")
    public Departure getDeparture() {
        return departure;
    }

    @JsonProperty("departure")
    public void setDeparture(Departure departure) {
        this.departure = departure;
    }

    @JsonProperty("arrival")
    public Arrival getArrival() {
        return arrival;
    }

    @JsonProperty("arrival")
    public void setArrival(Arrival arrival) {
        this.arrival = arrival;
    }

    @JsonProperty("carrierCode")
    public String getCarrierCode() {
        return carrierCode;
    }

    @JsonProperty("carrierCode")
    public void setCarrierCode(String carrierCode) {
        this.carrierCode = carrierCode;
    }

    @JsonProperty("number")
    public String getNumber() {
        return number;
    }

    @JsonProperty("number")
    public void setNumber(String number) {
        this.number = number;
    }

    @JsonProperty("aircraft")
    public Aircraft getAircraft() {
        return aircraft;
    }

    @JsonProperty("aircraft")
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }

    @JsonProperty("operating")
    public Operating getOperating() {
        return operating;
    }

    @JsonProperty("operating")
    public void setOperating(Operating operating) {
        this.operating = operating;
    }

    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }

}
