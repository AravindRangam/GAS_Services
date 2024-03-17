package com.kartha.cssp.controller;

import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("account")
public class AutoPayController {

    PaymentService paymentService;

    @Autowired
    public AutoPayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping(path = "/autopay/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CsspServiceResponse> payNowBankDetails(@PathVariable String accountNumber) throws Exception {

        CsspServiceResponse csspServiceResponse = paymentService.retrieveBankDetails(accountNumber,"AUTOPAY");
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }
    
}
