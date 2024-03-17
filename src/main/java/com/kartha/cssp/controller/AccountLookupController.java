package com.kartha.cssp.controller;

import com.kartha.cssp.request.AccountLookupRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.AccountLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("account")
public class AccountLookupController {

    AccountLookupService accountLookupService;

    @Autowired
    public AccountLookupController(AccountLookupService accountLookupService) {
        this.accountLookupService = accountLookupService;
    }

    @PostMapping(path = "/validateAccountSSN/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateAccountSsn(@RequestBody ValidateAccountSSNRequest validateAccountRequest,
                                             @PathVariable String accountNumber) {

        CsspServiceResponse csspServiceResponse = accountLookupService.validateAccountSSN(validateAccountRequest);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }


    @PostMapping(path = "/accountLookUp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity accountLookup(@RequestBody AccountLookupRequest accountLookupRequest) throws Exception {

        CsspListServiceResponse csspListServiceResponse = accountLookupService.matchNGetAccountInfo(accountLookupRequest);
        if(Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

}
