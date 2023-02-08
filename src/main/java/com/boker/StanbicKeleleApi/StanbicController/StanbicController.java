package com.boker.StanbicKeleleApi.StanbicController;

import com.boker.StanbicKeleleApi.StanbicService.StanbicApiService;
import com.boker.StanbicKeleleApi.dto.Requests.MobilePaymentRequest;
import com.boker.StanbicKeleleApi.dto.Requests.Paylink.PesaLinkPaymentRequest;
import com.boker.StanbicKeleleApi.dto.Requests.STKPushPaymentRequest;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/")
@RequiredArgsConstructor
public class StanbicController {
    private final StanbicApiService stanbicApiService;

    @PostMapping(value = "/mpesa-checkout")
    public ResponseEntity<JsonNode> STKPushTransaction(@RequestBody STKPushPaymentRequest paymentRequest) {
        return ResponseEntity.ok(stanbicApiService.STKPushTransaction(paymentRequest));
    }

    @PostMapping(value = "/mpesa-payment")
    public ResponseEntity<JsonNode> MobileTransaction(@RequestBody MobilePaymentRequest paymentRequest) {
        return ResponseEntity.ok(stanbicApiService.mobilePayment(paymentRequest));
    }

    @PostMapping(value = "/pesaLink-payment")
    public ResponseEntity<JsonNode> pesaLinkTransaction(@RequestBody PesaLinkPaymentRequest pesaLinkPaymentRequest) {
        return ResponseEntity.ok(stanbicApiService.pesaLinkPayment(pesaLinkPaymentRequest));
    }

}
