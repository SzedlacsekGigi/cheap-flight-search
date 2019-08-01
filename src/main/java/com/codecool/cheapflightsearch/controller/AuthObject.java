package com.codecool.cheapflightsearch.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "type",
        "username",
        "application_name",
        "client_id",
        "token_type",
        "access_token",
        "expires_in",
        "state",
        "scope"
})
public class AuthObject {

    @JsonProperty("type")
    public String type;
    @JsonProperty("username")
    public String username;
    @JsonProperty("application_name")
    public String applicationName;
    @JsonProperty("client_id")
    public String clientId;
    @JsonProperty("token_type")
    public String tokenType;
    @JsonProperty("access_token")
    public String accessToken;
    @JsonProperty("expires_in")
    public Integer expiresIn;
    @JsonProperty("state")
    public String state;
    @JsonProperty("scope")
    public String scope;

}
