
package com.codecool.cheapflightsearch.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "code"
})
public class Aircraft {

    @JsonProperty("code")
    private String code;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Aircraft() {
    }

    /**
     * 
     * @param code
     */
    public Aircraft(String code) {
        super();
        this.code = code;
    }

    @JsonProperty("code")
    public String getCode() {
        return code;
    }

    @JsonProperty("code")
    public void setCode(String code) {
        this.code = code;
    }

}
