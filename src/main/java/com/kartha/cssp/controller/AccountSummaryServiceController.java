package com.kartha.cssp.controller;

import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.AccountSummaryService;
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
public class AccountSummaryServiceController {

    AccountSummaryService accountSummaryService;

    @Autowired
    public AccountSummaryServiceController(AccountSummaryService accountSummaryService) {
        this.accountSummaryService = accountSummaryService;
    }

    @GetMapping(path = "/accountSummary/{accountNumber}/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccountSummary(@PathVariable String accountNumber, @PathVariable String userId) throws Exception {

        CsspServiceResponse csspServiceResponse = accountSummaryService.getAccountSummary(accountNumber, userId);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

}
