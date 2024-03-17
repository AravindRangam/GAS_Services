package com.kartha.cssp.controller;

import com.kartha.cssp.data.LanderListData;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.AccountLanderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("account")
public class AccountLanderServiceController {

    AccountLanderService accountLanderService;

    @Autowired
    public AccountLanderServiceController(AccountLanderService accountLanderService) {
        this.accountLanderService = accountLanderService;
    }

    @GetMapping(path = "/{userId}/accountList", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getUserIdAccountList(@PathVariable String userId) throws Exception {

        CsspServiceResponse csspServiceResponse = accountLanderService.getAccountList(userId, false);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}/getDefaultAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getDefaultAccount(@PathVariable String userId) throws Exception {

        CsspServiceResponse csspServiceResponse = accountLanderService.getAccountList(userId,true);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/{accountNumber}/getUsers", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccountUserIds(@PathVariable String accountNumber) throws Exception {

        CsspListServiceResponse<LanderListData> csspServiceResponse = accountLanderService.getAccountUser(accountNumber);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

}
