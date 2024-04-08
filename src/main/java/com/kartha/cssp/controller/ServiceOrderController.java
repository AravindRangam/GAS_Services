package com.kartha.cssp.controller;

import com.kartha.cssp.request.ServiceRequestBody;
import com.kartha.cssp.request.TransferRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.ServiceOrder;
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
@RequestMapping("serviceorder")
public class ServiceOrderController {

    ServiceOrder serviceOrder;

    @Autowired
    public ServiceOrderController(ServiceOrder serviceOrder) {
        this.serviceOrder = serviceOrder;
    }

    @PostMapping(path = "/{connectDisconnectTransfer}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createServiceOrder(@RequestBody TransferRequest transferRequest,
                                             @PathVariable String connectDisconnectTransfer)
    {

        CsspServiceResponse csspServiceResponse = serviceOrder.createServiceOrder(transferRequest, connectDisconnectTransfer);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/serviceRequest", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createServiceRequest(@RequestBody ServiceRequestBody requestBody)
    {
        CsspServiceResponse csspServiceResponse = serviceOrder.createServiceRequest(requestBody);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path = "/serviceRequests", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getServiceRequests()
    {
        CsspListServiceResponse csspServiceResponse = serviceOrder.getServiceRequests();
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

}
