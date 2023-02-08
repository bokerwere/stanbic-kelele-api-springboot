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
public class TransferTransactionInformation {

    @JsonProperty("mobileMoneyMno")
    private MobileMoneyMno mobileMoneyMno;

    @JsonProperty("remittanceInformation")
    private RemittanceInformation remittanceInformation;

    @JsonProperty("counterparty")
    private Counterparty counterparty;

    @JsonProperty("instructedAmount")
    private InstructedAmount instructedAmount;

    @JsonProperty("endToEndIdentification")
    private String endToEndIdentification;

}