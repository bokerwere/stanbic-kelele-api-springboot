package com.boker.StanbicKeleleApi.dto.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties
@AllArgsConstructor
@NoArgsConstructor
public class STKPushPaymentRequest{

	@JsonProperty("txnNarrative")
	private String txnNarrative;

	@JsonProperty("amount")
	private String amount;

	@JsonProperty("corporateNumber")
	private String corporateNumber;

	@JsonProperty("mobileNumber")
	private String mobileNumber;

	@JsonProperty("dbsReferenceId")
	private String dbsReferenceId;

	@JsonProperty("billAccountRef")
	private String billAccountRef;

	@JsonProperty("bankReferenceId")
	private String bankReferenceId;
}