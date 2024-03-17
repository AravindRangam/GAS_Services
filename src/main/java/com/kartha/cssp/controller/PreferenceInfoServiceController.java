package com.kartha.cssp.controller;

import com.kartha.cssp.request.UpdateUserIdRequest;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.ContactInfoService;
import com.kartha.cssp.service.PreferenceService;
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
public class PreferenceInfoServiceController {

    @Autowired
    PreferenceService preferenceService;

    @PostMapping(path = "/{userId}/updateUserId", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateUserID(@RequestBody UpdateUserIdRequest updateUserIdRequest,
                                       @PathVariable String userId) throws Exception {

        CsspServiceResponse csspServiceResponse = preferenceService.updateUserId(updateUserIdRequest, userId);
        if(Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages("SUCCESS"));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

}
