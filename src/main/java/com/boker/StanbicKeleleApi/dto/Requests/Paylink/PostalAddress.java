package com.boker.StanbicKeleleApi.dto.Requests.Paylink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostalAddress {

    @JsonProperty("country")
    private String country;

    @JsonProperty("town")
    private String town;

    @JsonProperty("postCode")
    private String postCode;

    @JsonProperty("addressLine")
    private String addressLine;


}