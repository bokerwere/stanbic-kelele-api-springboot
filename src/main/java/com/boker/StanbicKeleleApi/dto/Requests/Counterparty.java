package com.boker.StanbicKeleleApi.dto.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties
public class Counterparty {

    @JsonProperty("postalAddress")
    private PostalAddress postalAddress;

    @JsonProperty("mobileNumber")
    private String mobileNumber;

    @JsonProperty("name")
    private String name;


}