package com.kartha.cssp.controller;

import com.kartha.cssp.request.MakePaymentRequest;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("account")
public class PaymentServiceController {

    PaymentService paymentService;

    @Autowired
    public PaymentServiceController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/paynow/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CsspServiceResponse> payNowBankDetails(@PathVariable String accountNumber) throws Exception {

        CsspServiceResponse csspServiceResponse = paymentService.retrieveBankDetails(accountNumber,"PAYNOW");
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/makepaymentpaynow/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePayNow(@RequestBody MakePaymentRequest makePaymentRequest, @PathVariable String accountNumber) {

        CsspServiceResponse csspListServiceResponse = paymentService.makePaymentPayNow(makePaymentRequest,  accountNumber);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

}
