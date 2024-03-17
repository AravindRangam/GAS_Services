package com.kartha.cssp.controller;

import com.kartha.cssp.request.CreateUserRequest;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.service.RegistrationService;
import com.kartha.cssp.utils.CSSPConstants;
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
@RequestMapping("user")
public class RegistrationController {

    RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping(path = "/{userId}/validateUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CsspServiceResponse> validateUserId(@PathVariable String userId) throws Exception {

        CsspServiceResponse csspServiceResponse = registrationService.validateUserId(userId);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages(CSSPConstants.VALIDATE_USER_ID,
                    CSSPConstants.SUCCESS,"user id validation is successful."));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    @PostMapping(path = "/{userId}/createUser", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createUser(@RequestBody CreateUserRequest createUserRequest,
                                     @PathVariable String userId) throws Exception {

        CsspServiceResponse csspServiceResponse = registrationService.createUser(createUserRequest);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages(CSSPConstants.USER_ACCOUNT_REGISTRATION,
                    CSSPConstants.SUCCESS,"user id creation is successful."));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }
}
