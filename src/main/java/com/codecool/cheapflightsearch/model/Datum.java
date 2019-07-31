
package com.codecool.cheapflightsearch.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "id",
    "offerItems"
})
public class Datum {

    @JsonProperty("type")
    private String type;
    @JsonProperty("id")
    private String id;
    @JsonProperty("offerItems")
    private List<OfferItem> offerItems = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Datum() {
    }

    /**
     * 
     * @param id
     * @param offerItems
     * @param type
     */
    public Datum(String type, String id, List<OfferItem> offerItems) {
        super();
        this.type = type;
        this.id = id;
        this.offerItems = offerItems;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("offerItems")
    public List<OfferItem> getOfferItems() {
        return offerItems;
    }

    @JsonProperty("offerItems")
    public void setOfferItems(List<OfferItem> offerItems) {
        this.offerItems = offerItems;
    }

}
