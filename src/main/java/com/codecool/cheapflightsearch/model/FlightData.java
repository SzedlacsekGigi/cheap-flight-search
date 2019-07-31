
package com.codecool.cheapflightsearch.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data"
})
public class FlightData {

    @JsonProperty("data")
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public FlightData() {
    }

    /**
     * 
     * @param data
     */
    public FlightData(List<Datum> data) {
        super();
        this.data = data;
    }

    @JsonProperty("data")
    public List<Datum> getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(List<Datum> data) {
        this.data = data;
    }

}
