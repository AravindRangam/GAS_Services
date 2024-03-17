package com.kartha.cssp.controller;

import com.kartha.cssp.request.OutageServiceRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.OutageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("outages")
public class OutagesServiceController {

    OutageService outageService;

    @Autowired
    public OutagesServiceController(OutageService outageService) {
        this.outageService = outageService;
    }

    @PostMapping(path = "/validateOutageAddress", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity validateOutageInfo(@RequestBody OutageServiceRequest outageServiceRequest)
    {
        CsspListServiceResponse csspServiceResponse = this.outageService.validateOutageAddress(outageServiceRequest);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/saveOutageInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity saveOutageDetails(@RequestBody OutageServiceRequest outageServiceRequest)
    {
        CsspServiceResponse csspServiceResponse = this.outageService.saveOutageDetails(outageServiceRequest);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }
}
