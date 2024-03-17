package com.kartha.cssp.service;

import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.ContactInfoRequest;
import com.kartha.cssp.request.MailingAddressRequest;
import com.kartha.cssp.request.PreferencesDBRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;

public interface ContactInfoService {

    CsspListServiceResponse updatePhone(ContactInfoRequest contactInfoRequest) throws CSSPServiceException;

    CsspListServiceResponse getPhone(String accountNumber) throws CSSPServiceException;

    CsspListServiceResponse updateEmail(ContactInfoRequest contactInfoRequest) throws CSSPServiceException;

    CsspListServiceResponse getEmail(String accountNumber) throws CSSPServiceException;

    CsspListServiceResponse updateMailingAddress(MailingAddressRequest mailingAddressRequest) throws CSSPServiceException;

    CsspListServiceResponse getMailingAddress(String accountNumber) throws CSSPServiceException;

    CsspServiceResponse getAccountPreferences(String accountNumber) throws CSSPServiceException;

    CsspServiceResponse updatePreferencesDB(PreferencesDBRequest preferencesData, String accountNumber) throws CSSPServiceException;;
}
