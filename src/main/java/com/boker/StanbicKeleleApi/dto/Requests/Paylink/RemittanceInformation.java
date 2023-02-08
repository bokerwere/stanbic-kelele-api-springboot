package com.boker.StanbicKeleleApi.dto.Requests.Paylink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemittanceInformation{

	@JsonProperty("type")
	private String type;

	@JsonProperty("content")
	private String content;
}