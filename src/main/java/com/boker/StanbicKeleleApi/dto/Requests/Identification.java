package com.boker.StanbicKeleleApi.dto.Requests;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties
public class Identification{
	@JsonProperty("mobileNumber")
	private String mobileNumber;

//	@JsonProperty("recipientBankCode")
//	private String recipientBankCode;
//
//	@JsonProperty("recipientBankAcctNo")
//	private String recipientBankAcctNo;

}