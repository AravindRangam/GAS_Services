package com.kartha.cssp.controller;

import com.kartha.cssp.request.BankDetailsRequest;
import com.kartha.cssp.request.EnrollUnenrollRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.ProgramsService;
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
public class ProgramsServiceController {

    ProgramsService programsService;

    @Autowired
    public ProgramsServiceController(ProgramsService programsService) {
        this.programsService = programsService;
    }

    @PostMapping(path = "/embEnrollUnenroll/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity embEnrollUnenroll(@RequestBody EnrollUnenrollRequest enrollUnenrollRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = programsService.updateEMB(enrollUnenrollRequest);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/bbEnrollUnenroll/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bbEnrollUnenroll(@RequestBody EnrollUnenrollRequest enrollUnenrollRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = programsService.updateBudgetBill(enrollUnenrollRequest);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }
    @PostMapping(path = "/fbEnrollUnenroll/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity fbEnrollUnenroll(@RequestBody EnrollUnenrollRequest enrollUnenrollRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = programsService.updateFlatBill(enrollUnenrollRequest);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/autopayEnrollUnenroll/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateAutoPay(@RequestBody BankDetailsRequest bankDetailsRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = programsService.updateAutoPay(bankDetailsRequest);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/updatePayNow/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePayNow(@RequestBody BankDetailsRequest bankDetailsRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = programsService.updatePayNow(bankDetailsRequest);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }


    @PostMapping(path = "/payExtendEnrollUnenroll/{accountNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity payExtendEnrollUnenroll(@RequestBody EnrollUnenrollRequest enrollUnenrollRequest, @PathVariable String accountNumber) {

        CsspListServiceResponse csspListServiceResponse = programsService.updatePayExtend(enrollUnenrollRequest);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }

}
