package com.kartha.cssp.service;

import com.kartha.cssp.dao.AccountServiceDAO;
import com.kartha.cssp.dao.ProgramsServiceDAO;
import com.kartha.cssp.data.*;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.ViewBill;
import com.kartha.cssp.request.AddRemoveAccountRequest;
import com.kartha.cssp.request.EnrollUnenrollRequest;
import com.kartha.cssp.request.SendEmailRequest;
import com.kartha.cssp.request.ValidateAccountSSNRequest;
import com.kartha.cssp.response.*;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    AccountServiceDAO accountServiceDAO;
    ProgramsServiceDAO programsServiceDAO;

    @Autowired
    public AccountServiceImpl(AccountServiceDAO accountServiceDAO, ProgramsServiceDAO programsServiceDAO) {
        this.accountServiceDAO = accountServiceDAO;
        this.programsServiceDAO = programsServiceDAO;
    }

    public CsspServiceResponse validateAccountSSN(ValidateAccountSSNRequest validateAccountRequest) throws CSSPServiceException {
        CsspServiceResponse<ValidateAccountSSNResponse> csspValidateAccountData = new CsspServiceResponse<ValidateAccountSSNResponse>();
        ValidateAccountSSNResponse validateAccountSSNResponse = new ValidateAccountSSNResponse();
        try {

            AccountData accountData = accountServiceDAO.validateAccountSSN(validateAccountRequest);

            populateValidateAccountResponse(validateAccountSSNResponse, accountData);

            csspValidateAccountData.setData(validateAccountSSNResponse);

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in validateAccountSSN ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    (CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode()) ||
                            CSSPConstants.ACCOUNT_SSN_NOT_MATCH_ERROR.equalsIgnoreCase(e.getErrorCode()))) {
                csspValidateAccountData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,CSSPConstants.FAILED,
                        CSSPConstants.ACCOUNT_SSN_MATCH_ERROR_MSG));
            } else {
                csspValidateAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in validateAccountSSN ", e);
            csspValidateAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspValidateAccountData;
    }

    public void populateValidateAccountResponse(ValidateAccountSSNResponse validateAccountSSNResponse,
                                                AccountData accountData) {
        validateAccountSSNResponse.setValidateEmail(true);
        validateAccountSSNResponse.setSecurityEnabled(false);
        validateAccountSSNResponse.setValidAccountSSN(true);
        validateAccountSSNResponse.setAccountNum(accountData.getAccountNumber());
        validateAccountSSNResponse.setSsn(accountData.getSsn());

        Predicate<AddressData> checkPremiseAddress = addressData ->
                "PREMISE".equalsIgnoreCase(addressData.getAddressType());

        List<AddressData> addressDataList = accountData.getAddress();
        addressDataList.stream().filter(checkPremiseAddress)
                .forEach(addressData -> {
                    validateAccountSSNResponse.setServiceAddress(addressData.getHouseNo()+" "+
                                                                 addressData.getStreetName()+" "+
                                                                 addressData.getAptNo()+" "+
                                                                 addressData.getCity()+" "+
                                                                 addressData.getState()+" "+
                                                                 addressData.getZip());
                });

        Predicate<ProgramsData> emailBillData = programsData ->
                "EMAILBILL".equalsIgnoreCase(programsData.getProgramType());

        List<ProgramsData> programsDataList = accountData.getProgramsData();

        if(Objects.nonNull(programsDataList)) {
            programsDataList.stream().filter(emailBillData)
                    .forEach(programsData -> {
                        validateAccountSSNResponse.setEbillEligible(programsData.isEligibleFlag());
                        validateAccountSSNResponse.setEbillStatus(programsData.getEnrolled());
                    });
        }

        Predicate<DigitalCommunicationData> digitalCommunicationDataPredicate = digitalCommunicationData ->
                ("email_id".equalsIgnoreCase(digitalCommunicationData.getCommunicationType())
                && "1".equals(digitalCommunicationData.getPriority()));

        List<DigitalCommunicationData> digitalCommunicationDataList = accountData.getDigitalCommunicationInfo();

        if(Objects.nonNull(digitalCommunicationDataList)) {
            digitalCommunicationDataList.stream().filter(digitalCommunicationDataPredicate)
                    .forEach(digitalCommunicationData -> {
                        validateAccountSSNResponse.setEmail(digitalCommunicationData.getCommunicateTo());
                    });

        }
        validateAccountSSNResponse.setPinVerificationRequired(true);

    }


    public CsspServiceResponse getAccount(String accountNumber) throws CSSPServiceException {
        CsspServiceResponse<AccountData> csspAccountData = new CsspServiceResponse<AccountData>();
        try {

            csspAccountData.setData(accountServiceDAO.getAccount(accountNumber));

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in getAccount ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspAccountData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,CSSPConstants.FAILED,
                        CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            } else {
                csspAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in getAccount ", e);
            csspAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspAccountData;
    }

    public CsspServiceResponse addRemoveAccount(AddRemoveAccountRequest addRemoveAccountRequest) throws CSSPServiceException {
        CsspServiceResponse<String> csspGenericResponse = new CsspServiceResponse<String>();
        try {

            String returnString = accountServiceDAO.addRemoveAccount(addRemoveAccountRequest);
            csspGenericResponse.setData(returnString);

            if("SUCCESS".equalsIgnoreCase(returnString)) {
                if ("ADD".equalsIgnoreCase(addRemoveAccountRequest.getAddDeleteIndicator())) {
                    EnrollUnenrollRequest enrollUnenrollRequest = new EnrollUnenrollRequest();
                    enrollUnenrollRequest.setEnrollUnenrollFlag(addRemoveAccountRequest.getEbillEnroll());
                    enrollUnenrollRequest.setAccountNumber(addRemoveAccountRequest.getAccountNumber());
                    enrollUnenrollRequest.setEmail(addRemoveAccountRequest.getEmailId());
                    this.programsServiceDAO.updateEMB(enrollUnenrollRequest);
                }
            }



        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in addremove account ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspGenericResponse.setMessage(new Messages(CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR,CSSPConstants.FAILED,
                        CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR_MSG));
            } else if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.USER_ACCOUNT_EXISTS_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspGenericResponse.setMessage(new Messages(CSSPConstants.USER_ACCOUNT_EXISTS_ERROR,CSSPConstants.FAILED,
                        CSSPConstants.USER_ACCOUNT_EXISTS_ERROR_MSG));
            } else {
                csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in addremoveAccount ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspGenericResponse;
    }


    public CsspServiceResponse sendEmail(SendEmailRequest sendEmailRequest) throws CSSPServiceException {
        CsspServiceResponse<String> csspGenericResponse = new CsspServiceResponse<String>();
        try {

            String returnString = accountServiceDAO.sendEmail(sendEmailRequest);

            if("SUCCESS".equalsIgnoreCase(returnString)) {

            }

            csspGenericResponse.setData(returnString);

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in Send Email account ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        } catch (Exception e) {
            log.error("Exception in sendEmail ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspGenericResponse;
    }

    public CsspServiceResponse getBill(String accountNumber) throws CSSPServiceException {
        CsspServiceResponse<ViewBill> csspViewBill = new CsspServiceResponse<ViewBill>();
        try {

            csspViewBill.setData(accountServiceDAO.getBill(accountNumber));

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in getBill ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspViewBill.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,CSSPConstants.FAILED,
                        CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            } else {
                csspViewBill.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in getBill ", e);
            csspViewBill.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspViewBill;
    }

}