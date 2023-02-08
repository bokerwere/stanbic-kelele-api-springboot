package com.boker.StanbicKeleleApi.dto.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties
public class PostalAddress{

	@JsonProperty("country")
	private String country;

	@JsonProperty("town")
	private String town;

	@JsonProperty("addressLine1")
	private String addressLine1;

	@JsonProperty("addressLine2")
	private String addressLine2;

	@JsonProperty("postCode")
	private String postCode;
}