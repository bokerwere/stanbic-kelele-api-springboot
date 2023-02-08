package com.boker.StanbicKeleleApi.dto.Requests.Paylink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OriginatorAccount{
	@JsonProperty("identification")
	private Identification identification;
}