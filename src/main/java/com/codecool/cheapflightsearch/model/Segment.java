
package com.codecool.cheapflightsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "flightSegment",
    "pricingDetailPerAdult"
})
public class Segment {

    @JsonProperty("flightSegment")
    private FlightSegment flightSegment;
    @JsonProperty("pricingDetailPerAdult")
    private PricingDetailPerAdult pricingDetailPerAdult;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Segment() {
    }

    /**
     * 
     * @param pricingDetailPerAdult
     * @param flightSegment
     */
    public Segment(FlightSegment flightSegment, PricingDetailPerAdult pricingDetailPerAdult) {
        super();
        this.flightSegment = flightSegment;
        this.pricingDetailPerAdult = pricingDetailPerAdult;
    }

    @JsonProperty("flightSegment")
    public FlightSegment getFlightSegment() {
        return flightSegment;
    }

    @JsonProperty("flightSegment")
    public void setFlightSegment(FlightSegment flightSegment) {
        this.flightSegment = flightSegment;
    }

    @JsonProperty("pricingDetailPerAdult")
    public PricingDetailPerAdult getPricingDetailPerAdult() {
        return pricingDetailPerAdult;
    }

    @JsonProperty("pricingDetailPerAdult")
    public void setPricingDetailPerAdult(PricingDetailPerAdult pricingDetailPerAdult) {
        this.pricingDetailPerAdult = pricingDetailPerAdult;
    }

}
