package com.kartha.cssp.controller;

import com.kartha.cssp.data.AccountSSNData;
import com.kartha.cssp.request.AddRemoveAccountRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.request.SendEmailRequest;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.AccountService;
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
public class AccountServiceController {

    AccountService accountService;

    @Autowired
    public AccountServiceController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/verifyAddAccount/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getVerifyAddAccount(@PathVariable String accountNumber,
                                     @RequestHeader(value="X-Id-Number") String ssnNumber,
                                              @RequestHeader(value="X-userId") String userId) throws Exception {
        ValidateAccountSSNRequest validateAccountRequest = new ValidateAccountSSNRequest();
        if(Objects.nonNull(userId)) {
            validateAccountRequest.setUserId(userId);
        } else {
            validateAccountRequest.setUserId("a1@kartha.com");
        }

        AccountSSNData accountSSNData = new AccountSSNData();
        accountSSNData.setAccountNumber(accountNumber);
        accountSSNData.setSsn(ssnNumber);
        validateAccountRequest.setData(accountSSNData);
        CsspServiceResponse csspServiceResponse = accountService.validateAccountSSN(validateAccountRequest);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }

        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/getAccount/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccount(@PathVariable String accountNumber) throws Exception {

        CsspServiceResponse csspServiceResponse = accountService.getAccount(accountNumber);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    /*
     * This method signature with /userRegistration used in foundational service
     */
    @PostMapping(path = "/addRemoveAccount/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity addRemoveAccount(@RequestBody AddRemoveAccountRequest addRemoveAccountRequest,
                                           @PathVariable String accountNumber) {

        CsspServiceResponse csspServiceResponse = accountService.addRemoveAccount(addRemoveAccountRequest);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }
    /*
     * This method signature with /Send Email used in foundational service
     */
    @PostMapping(path = "/sendEmail/{emailId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity sendEmail(@RequestBody SendEmailRequest sendEmailRequest,
                                           @PathVariable String emailId) {

        CsspServiceResponse csspServiceResponse = accountService.sendEmail(sendEmailRequest);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/getBill/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getBill(@PathVariable String accountNumber) throws Exception {

        CsspServiceResponse csspServiceResponse = accountService.getBill(accountNumber);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

}
