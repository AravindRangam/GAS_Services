package com.kartha.cssp.controller;

import com.kartha.cssp.request.ContactInfoRequest;
import com.kartha.cssp.request.MailingAddressRequest;
import com.kartha.cssp.request.PreferencesDBRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.ContactInfoService;
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
public class ContactInfoServiceController {

    ContactInfoService contactInfoService;

    @Autowired
    public ContactInfoServiceController(ContactInfoService contactInfoService) {
        this.contactInfoService = contactInfoService;
    }

    @PostMapping(path = "/updatePhone/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePhone(@RequestBody ContactInfoRequest contactInfoRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = contactInfoService.updatePhone(contactInfoRequest);
        if(Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/phone/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getPhone(@PathVariable String accountNumber) throws Exception {

        CsspListServiceResponse csspListServiceResponse = contactInfoService.getPhone(accountNumber);
        if(Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/updateEmail/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEmail(@RequestBody ContactInfoRequest contactInfoRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = contactInfoService.updateEmail(contactInfoRequest);
        if(Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/email/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getEmail(@PathVariable String accountNumber) throws Exception {

        CsspListServiceResponse csspListServiceResponse = contactInfoService.getEmail(accountNumber);
        if(Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/updateMailingAddress/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateMailingAddress(@RequestBody MailingAddressRequest mailingAddressRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = contactInfoService.updateMailingAddress(mailingAddressRequest);
        if(Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/mailingAddress/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getMailingAddress(@PathVariable String accountNumber) throws Exception {

        CsspListServiceResponse csspListServiceResponse = contactInfoService.getMailingAddress(accountNumber);
        if(Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }
        @GetMapping(path = "/accountPreferences/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAccountPreferences(@PathVariable String accountNumber) throws Exception {

        CsspServiceResponse csspServiceResponse = contactInfoService.getAccountPreferences(accountNumber);

        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }


    @PostMapping(path = "/updateAccountPreferences/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAccountPreferences(@RequestBody PreferencesDBRequest preferencesData, @PathVariable String accountNumber) {

        CsspServiceResponse csspServiceResponse = contactInfoService.updatePreferencesDB(preferencesData, accountNumber);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

}
