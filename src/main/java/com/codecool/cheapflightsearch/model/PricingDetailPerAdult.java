
package com.codecool.cheapflightsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "travelClass",
    "fareClass",
    "availability",
    "fareBasis"
})
public class PricingDetailPerAdult {

    @JsonProperty("travelClass")
    private String travelClass;
    @JsonProperty("fareClass")
    private String fareClass;
    @JsonProperty("availability")
    private Integer availability;
    @JsonProperty("fareBasis")
    private String fareBasis;

    /**
     * No args constructor for use in serialization
     * 
     */
    public PricingDetailPerAdult() {
    }

    /**
     * 
     * @param travelClass
     * @param fareBasis
     * @param fareClass
     * @param availability
     */
    public PricingDetailPerAdult(String travelClass, String fareClass, Integer availability, String fareBasis) {
        super();
        this.travelClass = travelClass;
        this.fareClass = fareClass;
        this.availability = availability;
        this.fareBasis = fareBasis;
    }

    @JsonProperty("travelClass")
    public String getTravelClass() {
        return travelClass;
    }

    @JsonProperty("travelClass")
    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;
    }

    @JsonProperty("fareClass")
    public String getFareClass() {
        return fareClass;
    }

    @JsonProperty("fareClass")
    public void setFareClass(String fareClass) {
        this.fareClass = fareClass;
    }

    @JsonProperty("availability")
    public Integer getAvailability() {
        return availability;
    }

    @JsonProperty("availability")
    public void setAvailability(Integer availability) {
        this.availability = availability;
    }

    @JsonProperty("fareBasis")
    public String getFareBasis() {
        return fareBasis;
    }

    @JsonProperty("fareBasis")
    public void setFareBasis(String fareBasis) {
        this.fareBasis = fareBasis;
    }

}
