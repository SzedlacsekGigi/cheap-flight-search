
package com.codecool.cheapflightsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "total",
    "totalTaxes"
})
public class Price {

    @JsonProperty("total")
    private String total;
    @JsonProperty("totalTaxes")
    private String totalTaxes;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Price() {
    }

    /**
     * 
     * @param total
     * @param totalTaxes
     */
    public Price(String total, String totalTaxes) {
        super();
        this.total = total;
        this.totalTaxes = totalTaxes;
    }

    @JsonProperty("total")
    public String getTotal() {
        return total;
    }

    @JsonProperty("total")
    public void setTotal(String total) {
        this.total = total;
    }

    @JsonProperty("totalTaxes")
    public String getTotalTaxes() {
        return totalTaxes;
    }

    @JsonProperty("totalTaxes")
    public void setTotalTaxes(String totalTaxes) {
        this.totalTaxes = totalTaxes;
    }

}
