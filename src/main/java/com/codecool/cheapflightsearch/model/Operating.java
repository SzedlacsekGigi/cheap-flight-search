
package com.codecool.cheapflightsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "carrierCode",
    "number"
})
public class Operating {

    @JsonProperty("carrierCode")
    private String carrierCode;
    @JsonProperty("number")
    private String number;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Operating() {
    }

    /**
     * 
     * @param number
     * @param carrierCode
     */
    public Operating(String carrierCode, String number) {
        super();
        this.carrierCode = carrierCode;
        this.number = number;
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

}
