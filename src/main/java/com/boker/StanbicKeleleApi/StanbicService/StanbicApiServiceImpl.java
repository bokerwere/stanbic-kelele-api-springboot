package com.boker.StanbicKeleleApi.StanbicService;

import com.boker.StanbicKeleleApi.config.StanbicApiConfigurations;
import com.boker.StanbicKeleleApi.dto.Requests.MobilePaymentRequest;
import com.boker.StanbicKeleleApi.dto.Requests.Paylink.PesaLinkPaymentRequest;
import com.boker.StanbicKeleleApi.dto.Requests.STKPushPaymentRequest;
import com.boker.StanbicKeleleApi.dto.Response.AccessTokenResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class StanbicApiServiceImpl implements StanbicApiService {
    private final StanbicApiConfigurations stanbicApiConfiguration;
    private final ObjectMapper mapper;
    String token = "AAIgNDMzY2EyZTgyODlmNjQ1OGJiYTMwYmI0NDA3MzMyNWMLGtrtoENYqmNkMoR-e-uNzcXZjzXNsS-UOGQQHBcBASjmJp-PSw70eoEYvJEZVh-sN-x1dH0d7eKlTZqpYj5rdsUQnvJSdUCnXZYzGF26SL9qvIeW2xqOFESSmPrMZHM";
    private RestTemplate restTemplate = new RestTemplate();

    @SneakyThrows
    @PostConstruct
    @Override
    public AccessTokenResponse getToken() {
        //String encodedCedentials = Helper.toBase64(String.format("%s:%s", consumerKey, consumerSecret));
        System.out.println("====");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", stanbicApiConfiguration.getClientId());
        map.add("client_secret", stanbicApiConfiguration.getClientSecret());
        map.add("grant_type", "client_credentials");
        map.add("scope", "payments");
        log.info("map{}", mapper.writeValueAsString(map));
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        String uri = "https://api.connect.stanbicbank.co.ke/api/sandbox/auth/oauth2/token";
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.POST, entity, JsonNode.class);
        int statusCodeValue = response.getStatusCodeValue();
        log.info("statusCode:{}", response.getStatusCodeValue());
        log.info("statusCodeValue:{}", response.getStatusCode());
        if (statusCodeValue == 200 || statusCodeValue == 201) {
            JsonNode jsonNode = response.getBody();
            log.info("responseBody{}", mapper.writeValueAsString(jsonNode));
            AccessTokenResponse accessTokenResponse = mapper.treeToValue(jsonNode, AccessTokenResponse.class);
            return accessTokenResponse;

        }

        return null;

    }

    @Override
    public JsonNode STKPushTransaction(STKPushPaymentRequest paymentRequest) {
        AccessTokenResponse token = getToken();
        STKPushPaymentRequest pushPaymentRequest = new STKPushPaymentRequest();
        pushPaymentRequest.setAmount(paymentRequest.getAmount());
        pushPaymentRequest.setDbsReferenceId(paymentRequest.getDbsReferenceId());
        pushPaymentRequest.setBankReferenceId(paymentRequest.getBankReferenceId());
        pushPaymentRequest.setBillAccountRef(paymentRequest.getBillAccountRef());
        pushPaymentRequest.setMobileNumber(paymentRequest.getMobileNumber());
        pushPaymentRequest.setCorporateNumber(paymentRequest.getCorporateNumber());
        pushPaymentRequest.setTxnNarrative(paymentRequest.getTxnNarrative());

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<STKPushPaymentRequest> entity = new HttpEntity<>(pushPaymentRequest, headers);
        String uri = "https://api.connect.stanbicbank.co.ke/api/sandbox/mpesa-checkout";
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.POST, entity, JsonNode.class);
        log.info("status :{}", response.getStatusCode());
        log.info("statusCode :{}", response.getStatusCodeValue());
        try {
            int statusCodeValue = response.getStatusCodeValue();

            log.info("status {}", statusCodeValue);
            log.info("body {}", response.getBody());

            if (statusCodeValue == 200 || statusCodeValue == 201) {
                JsonNode responseBody = response.getBody();
                return responseBody;
            }
        } catch (RestClientResponseException e) {
            String errorResponseBody = e.getResponseBodyAsString();
            log.error("status {}", e.getRawStatusCode());
            log.error("body {}", errorResponseBody);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public JsonNode mobilePayment(MobilePaymentRequest mobilePaymentRequest) {

        AccessTokenResponse token = getToken();
        MobilePaymentRequest paymentRequest = new MobilePaymentRequest();

        paymentRequest.setCallBackUrl(mobilePaymentRequest.getCallBackUrl());
        paymentRequest.setRequestedExecutionDate(mobilePaymentRequest.getRequestedExecutionDate());
        paymentRequest.setDbsReferenceId(mobilePaymentRequest.getDbsReferenceId());
        paymentRequest.setTxnNarrative(mobilePaymentRequest.getTxnNarrative());

        //TransferTransactionInformation request
        paymentRequest.setTransferTransactionInformation(mobilePaymentRequest.getTransferTransactionInformation());
        paymentRequest.getTransferTransactionInformation().getMobileMoneyMno().setName(mobilePaymentRequest.getTransferTransactionInformation().getMobileMoneyMno().getName());
        paymentRequest.getTransferTransactionInformation().getInstructedAmount().setAmount(mobilePaymentRequest.getTransferTransactionInformation().getInstructedAmount().getAmount());
        paymentRequest.getTransferTransactionInformation().getInstructedAmount().setCurrencyCode(mobilePaymentRequest.getTransferTransactionInformation().getInstructedAmount().getCurrencyCode());
        paymentRequest.getTransferTransactionInformation().getRemittanceInformation().setContent(mobilePaymentRequest.getTransferTransactionInformation().getRemittanceInformation().getContent());
        paymentRequest.getTransferTransactionInformation().getRemittanceInformation().setType(mobilePaymentRequest.getTransferTransactionInformation().getRemittanceInformation().getType());
        //counterpart
        paymentRequest.getTransferTransactionInformation().getCounterparty().setName(mobilePaymentRequest.getTransferTransactionInformation().getCounterparty().getName());
        paymentRequest.getTransferTransactionInformation().getCounterparty().setMobileNumber(mobilePaymentRequest.getTransferTransactionInformation().getCounterparty().getMobileNumber());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().setAddressLine1(mobilePaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getAddressLine1());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().setAddressLine2(mobilePaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getAddressLine2());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().setCountry(mobilePaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getCountry());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().setPostCode(mobilePaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getPostCode());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().setTown(mobilePaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getTown());

        paymentRequest.setOriginatorAccount(mobilePaymentRequest.getOriginatorAccount());
        paymentRequest.getOriginatorAccount().getIdentification().setMobileNumber(mobilePaymentRequest.getOriginatorAccount().getIdentification().getMobileNumber());
        // paymentRequest.getOriginatorAccount().getIdentification().setRecipientBankAcctNo(mobilePaymentRequest.getOriginatorAccount().getIdentification().getRecipientBankAcctNo());
        log.info("requestBody {}", mapper.writeValueAsString(paymentRequest));
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MobilePaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);
        String uri = "https://api.connect.stanbicbank.co.ke/api/sandbox/mobile-payments";
        ResponseEntity<JsonNode> response = restTemplate.exchange(uri, HttpMethod.POST, entity, JsonNode.class);
        log.info("status :{}", response.getStatusCode());
        log.info("statusCode :{}", response.getStatusCodeValue());
        try {
            int statusCodeValue = response.getStatusCodeValue();

            log.info("status {}", statusCodeValue);
            log.info("response_body {}", response.getBody());

            if (statusCodeValue == 200 || statusCodeValue == 201) {
                JsonNode responseBody = response.getBody();
                return responseBody;
            }
        } catch (RestClientResponseException e) {
            String errorResponseBody = e.getResponseBodyAsString();
            log.error("status {}", e.getRawStatusCode());
            log.error("error_body {}", errorResponseBody);
        }
        return null;
    }

    @SneakyThrows
    @Override
    public JsonNode pesaLinkPayment(PesaLinkPaymentRequest pesaLinkPaymentRequest) {
        AccessTokenResponse token = getToken();
        PesaLinkPaymentRequest paymentRequest = new PesaLinkPaymentRequest();
        paymentRequest.setTransferTransactionInformation(pesaLinkPaymentRequest.getTransferTransactionInformation());
        //originator account
        paymentRequest.setOriginatorAccount(pesaLinkPaymentRequest.getOriginatorAccount());

        paymentRequest.getTransferTransactionInformation().getCounterparty()
                .setName(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterparty().getName());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress()
                .setAddressLine(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getAddressLine());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress()
                .setTown(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getTown());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress()
                .setPostCode(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getPostCode());
        paymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress()
                .setCountry(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterparty().getPostalAddress().getCountry());
        //remittanceInformation
        paymentRequest.getTransferTransactionInformation().getRemittanceInformation()
                .setContent(pesaLinkPaymentRequest.getTransferTransactionInformation().getRemittanceInformation().getContent());
        paymentRequest.getTransferTransactionInformation().getRemittanceInformation()
                .setType(pesaLinkPaymentRequest.getTransferTransactionInformation().getRemittanceInformation().getType());
        //counter part
        //paymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification().setMobileNumber(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification().getMobileNumber());
        paymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification()
                .setRecipientBankAcctNo(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification().getRecipientBankAcctNo());
        paymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification()
                .setRecipientBankCode(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification().getRecipientBankCode());

        //paymentRequest.getOriginatorAccount().getIdentification().setRecipientBankCode(pesaLinkPaymentRequest.getOriginatorAccount().getIdentification().getRecipientBankCode());
        paymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification()
                .setRecipientBankAcctNo(pesaLinkPaymentRequest.getTransferTransactionInformation().getCounterpartyAccount().getIdentification().getRecipientBankAcctNo());

        // paymentRequest.getOriginatorAccount().getIdentification().setRecipientBankCode(pesaLinkPaymentRequest.getOriginatorAccount().getIdentification().getRecipientBankCode());
        paymentRequest.getOriginatorAccount().getIdentification().setMobileNumber(pesaLinkPaymentRequest.getOriginatorAccount().getIdentification().getMobileNumber());
        paymentRequest.setRequestedExecutionDate(pesaLinkPaymentRequest.getRequestedExecutionDate());
        paymentRequest.setCallBackUrl(pesaLinkPaymentRequest.getCallBackUrl());
        paymentRequest.setTxnNarrative(pesaLinkPaymentRequest.getTxnNarrative());
        paymentRequest.setDbsReferenceId(pesaLinkPaymentRequest.getDbsReferenceId());
        paymentRequest.setSendMoneyTo(pesaLinkPaymentRequest.getSendMoneyTo());

        log.info("payment{}", mapper.writeValueAsString(paymentRequest));

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token.getAccessToken());
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<PesaLinkPaymentRequest> entity = new HttpEntity<>(paymentRequest, headers);
        String url = "https://api.connect.stanbicbank.co.ke/api/sandbox/pesalink-payments";
        UriComponents buider = UriComponentsBuilder.fromHttpUrl(url).build();
        ResponseEntity<JsonNode> response = restTemplate.exchange(buider.toString(), HttpMethod.POST, entity, JsonNode.class);
        log.info(" status {}", response.getStatusCode());
        log.info(" statusValueCode {}", response.getStatusCode());
        log.info(" response {}", response.getBody());
        int statusValue = response.getStatusCodeValue();
        try {
            if (statusValue == 200 || statusValue == 201) {
                JsonNode jsonNode = response.getBody();
                return jsonNode;
            }
        } catch (RestClientResponseException exception) {
            log.error("erro_code{}", exception.getRawStatusCode());
            log.error("erro_message{}", exception.getResponseBodyAsString());
        }
        return null;
    }


}


