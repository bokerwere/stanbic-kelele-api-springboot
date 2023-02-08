package com.boker.StanbicKeleleApi.dto.Requests.Paylink;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransferTransactionInformation{

	@JsonProperty("remittanceInformation")
	private RemittanceInformation remittanceInformation;

	@JsonProperty("counterpartyAccount")
	private CounterpartyAccount counterpartyAccount;

	@JsonProperty("counterparty")
	private Counterparty counterparty;
	@JsonProperty("instructedAmount")
	private InstructedAmount instructedAmount;

	@JsonProperty("endToEndIdentification")
	private String endToEndIdentification;



}