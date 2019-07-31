
package com.codecool.cheapflightsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "iataCode",
    "terminal",
    "at"
})
public class Departure {

    @JsonProperty("iataCode")
    private String iataCode;
    @JsonProperty("terminal")
    private String terminal;
    @JsonProperty("at")
    private String at;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Departure() {
    }

    /**
     * 
     * @param terminal
     * @param iataCode
     * @param at
     */
    public Departure(String iataCode, String terminal, String at) {
        super();
        this.iataCode = iataCode;
        this.terminal = terminal;
        this.at = at;
    }

    @JsonProperty("iataCode")
    public String getIataCode() {
        return iataCode;
    }

    @JsonProperty("iataCode")
    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    @JsonProperty("terminal")
    public String getTerminal() {
        return terminal;
    }

    @JsonProperty("terminal")
    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @JsonProperty("at")
    public String getAt() {
        return at;
    }

    @JsonProperty("at")
    public void setAt(String at) {
        this.at = at;
    }

}
