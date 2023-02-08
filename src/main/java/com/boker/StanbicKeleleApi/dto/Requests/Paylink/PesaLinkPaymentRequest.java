package com.boker.StanbicKeleleApi.dto.Requests.Paylink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class PesaLinkPaymentRequest {

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

	@JsonProperty("sendMoneyTo")
	private String sendMoneyTo;

	@JsonProperty("dbsReferenceId")
	private String dbsReferenceId;

}