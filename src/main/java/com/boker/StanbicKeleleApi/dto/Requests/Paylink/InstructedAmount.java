package com.boker.StanbicKeleleApi.dto.Requests.Paylink;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructedAmount{

	@JsonProperty("amount")
	private String amount;
	@JsonProperty("currencyCode")
	private String currencyCode;

}