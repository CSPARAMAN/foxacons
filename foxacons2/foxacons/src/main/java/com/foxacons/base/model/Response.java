package com.foxacons.base.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class Response {
	
    @JsonProperty 
	private int statuscode;
    @JsonProperty
    private String msg;
    @JsonProperty
    private String discription;
    
    private String token;
   
}
 