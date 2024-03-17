package com.kartha.cssp.service;

import com.kartha.cssp.dao.AccountLookupDAO;
import com.kartha.cssp.dao.AccountServiceDAO;
import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.AccountLookUpData;
import com.kartha.cssp.data.AddressData;
import com.kartha.cssp.data.ProgramsData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.AccountLookupRequest;
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
public class AccountLookupServiceImpl implements AccountLookupService {

    AccountLookupDAO accountLookupDAO;
    AccountServiceDAO accountServiceDAO;

    @Autowired
    public AccountLookupServiceImpl(AccountLookupDAO accountLookupDAO,
                                    AccountServiceDAO accountServiceDAO) {
        this.accountLookupDAO = accountLookupDAO;
        this.accountServiceDAO = accountServiceDAO;
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
                csspValidateAccountData.setMessage(new Messages(e.getErrorCode(),CSSPConstants.FAILED,
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

        programsDataList.stream().filter(emailBillData)
                .forEach(programsData -> {
                    validateAccountSSNResponse.setEbillEligible(programsData.isEligibleFlag());
                    validateAccountSSNResponse.setEbillStatus(programsData.getEnrolled());
                });



        validateAccountSSNResponse.setPinVerificationRequired(true);

    }

    public CsspListServiceResponse matchNGetAccountInfo(AccountLookupRequest accountLookupRequest) throws CSSPServiceException {
        CsspListServiceResponse<AccountLookUpData> csspAccountLookUpData = new CsspListServiceResponse<AccountLookUpData>();
        try {
            csspAccountLookUpData.setData(accountLookupDAO.matchNGetAccountInfo(accountLookupRequest));
        } catch (Exception e) {
            log.error("Exception in Account Lookup ",e);
            csspAccountLookUpData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
        }

        return csspAccountLookUpData;
    }

}
