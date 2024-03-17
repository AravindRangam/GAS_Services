package com.kartha.cssp.controller;

import com.kartha.cssp.request.ForgotPassRequest;
import com.kartha.cssp.request.ForgotUIDRequest;
import com.kartha.cssp.request.UpdatePasswordRequest;
import com.kartha.cssp.request.UpdateUserIdRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
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
public class ForgotUserIdPasswordController {

    RegistrationService registrationService;

    @Autowired
    public ForgotUserIdPasswordController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping(path = "/{userId}/updatePassword", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePassword(@RequestBody UpdatePasswordRequest updatePasswordRequest,
                                     @PathVariable String userId) throws Exception {

        CsspServiceResponse csspServiceResponse = registrationService.updatePassword(updatePasswordRequest);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages(CSSPConstants.UPDATE_PASSWORD_SUCCESS,
                    CSSPConstants.SUCCESS,"update password is successful."));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    /*
     * This method FORGOT USERID used in foundational service- Input - AccountNO and SSN Last4, gives out userid's and accounts emailid
     */
    @PostMapping(path = "/forgotUID", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity forgotUID(@RequestBody ForgotUIDRequest forgotUIDRequest) {

        CsspServiceResponse csspServiceResponse = registrationService.forgotUID(forgotUIDRequest);
        if (Objects.nonNull(csspServiceResponse.getData())) {
            csspServiceResponse.setMessage(new Messages(CSSPConstants.FORGOT_UID_SUCCESS,
                    CSSPConstants.SUCCESS,"retrieve used ids successful."));
        }
        return new ResponseEntity<>(csspServiceResponse, HttpStatus.OK);
    }

    /*
     * This method FORGOTPassword used in foundational service- Input - UserID and SSN Last4, gives out userid's and accounts emailid
     */
    @PostMapping(path = "/forgotPass", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity forgotPass(@RequestBody ForgotPassRequest forgotPassRequest) {

        CsspListServiceResponse csspListServiceResponse = registrationService.forgotPass(forgotPassRequest);
        if (Objects.nonNull(csspListServiceResponse.getData())) {
            csspListServiceResponse.setMessage(new Messages(CSSPConstants.FORGOT_PWD_VALIDATION_SUCCESS,
                    CSSPConstants.SUCCESS,"forgot password validation is successful."));
        }
        return new ResponseEntity<>(csspListServiceResponse, HttpStatus.OK);
    }


}
