package com.boker.StanbicKeleleApi.StanbicService;

import com.boker.StanbicKeleleApi.dto.Requests.MobilePaymentRequest;
import com.boker.StanbicKeleleApi.dto.Requests.Paylink.PesaLinkPaymentRequest;
import com.boker.StanbicKeleleApi.dto.Requests.STKPushPaymentRequest;
import com.boker.StanbicKeleleApi.dto.Response.AccessTokenResponse;
import com.fasterxml.jackson.databind.JsonNode;

public interface StanbicApiService {
    AccessTokenResponse getToken();
    JsonNode STKPushTransaction(STKPushPaymentRequest pushPaymentRequest);
    JsonNode mobilePayment(MobilePaymentRequest mobilePaymentRequest);
    JsonNode pesaLinkPayment(PesaLinkPaymentRequest pesaLinkPaymentRequest);

}
