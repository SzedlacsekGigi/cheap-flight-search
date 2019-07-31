
package com.codecool.cheapflightsearch.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "services",
    "price",
    "pricePerAdult"
})
public class OfferItem {

    @JsonProperty("services")
    private List<Service> services = null;
    @JsonProperty("price")
    private Price price;
    @JsonProperty("pricePerAdult")
    private PricePerAdult pricePerAdult;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OfferItem() {
    }

    /**
     * 
     * @param services
     * @param price
     * @param pricePerAdult
     */
    public OfferItem(List<Service> services, Price price, PricePerAdult pricePerAdult) {
        super();
        this.services = services;
        this.price = price;
        this.pricePerAdult = pricePerAdult;
    }

    @JsonProperty("services")
    public List<Service> getServices() {
        return services;
    }

    @JsonProperty("services")
    public void setServices(List<Service> services) {
        this.services = services;
    }

    @JsonProperty("price")
    public Price getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(Price price) {
        this.price = price;
    }

    @JsonProperty("pricePerAdult")
    public PricePerAdult getPricePerAdult() {
        return pricePerAdult;
    }

    @JsonProperty("pricePerAdult")
    public void setPricePerAdult(PricePerAdult pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

}
