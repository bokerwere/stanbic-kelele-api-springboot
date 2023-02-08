package com.boker.StanbicKeleleApi.dto.Requests.Paylink;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Counterparty{

	@JsonProperty("postalAddress")
	private PostalAddress postalAddress;
	@JsonProperty("name")
	private String name;


}