package com.kartha.cssp.controller;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kartha.cssp.request.CreateEmergencyRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.EmergencyNotificationsService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("notifications")
public class EmergencyNontificationsController {

    EmergencyNotificationsService emergencyNotificationsService;

    public EmergencyNontificationsController(EmergencyNotificationsService emergencyNotificationsService) {
        this.emergencyNotificationsService = emergencyNotificationsService;
    }

    @PostMapping(path = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createNotification(@RequestBody CreateEmergencyRequest createEmergencyRequest) {

        CsspServiceResponse csspServiceResponse = emergencyNotificationsService
                .createNotification(createEmergencyRequest);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateNotification(@RequestBody CreateEmergencyRequest createEmergencyRequest) {

        CsspServiceResponse csspServiceResponse = emergencyNotificationsService
                .updateNotification(createEmergencyRequest);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deleteNotification(@PathVariable String id) {

        CsspServiceResponse csspServiceResponse = emergencyNotificationsService.deleteNotification(id);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @GetMapping(path="/getNotifications", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAllNotifications(@RequestParam(value = "all", required = false) String includeDeleted) {
        CsspListServiceResponse csspServiceResponse = emergencyNotificationsService.getNotifications(includeDeleted);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

}
