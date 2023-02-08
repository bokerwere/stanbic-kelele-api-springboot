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
public class MobilePaymentRequest{

	@JsonProperty("txnNarrative")
	private String txnNarrative;

	@JsonProperty("requestedExecutionDate")
	private String requestedExecutionDate;

	@JsonProperty("transferTransactionInformation")
	private TransferTransactionInformation transferTransactionInformation;

	@JsonProperty("callBackUrl")
	private String callBackUrl;

	@JsonProperty("originatorAccount")
	private OriginatorAccount originatorAccount;

	@JsonProperty("dbsReferenceId")
	private String dbsReferenceId;
}