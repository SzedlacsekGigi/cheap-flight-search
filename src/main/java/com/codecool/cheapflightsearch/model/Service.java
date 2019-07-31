
package com.codecool.cheapflightsearch.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "segments"
})
public class Service {

    @JsonProperty("segments")
    private List<Segment> segments = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Service() {
    }

    /**
     * 
     * @param segments
     */
    public Service(List<Segment> segments) {
        super();
        this.segments = segments;
    }

    @JsonProperty("segments")
    public List<Segment> getSegments() {
        return segments;
    }

    @JsonProperty("segments")
    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

}
