package com.boker.StanbicKeleleApi.dto.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class RemittanceInformation {

    @JsonProperty("type")
    private String type;

    @JsonProperty("content")
    private String content;
}