package com.boker.StanbicKeleleApi.dto.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AccessTokenResponse{

	@JsonProperty("access_token")
	private String accessToken;

	@JsonProperty("scope")
	private String scope;

	@JsonProperty("consented_on")
	private int consentedOn;

	@JsonProperty("token_type")
	private String tokenType;

	@JsonProperty("expires_in")
	private int expiresIn;
}