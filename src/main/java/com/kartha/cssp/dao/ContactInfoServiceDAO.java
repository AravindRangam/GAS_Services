package com.kartha.cssp.dao;

import com.kartha.cssp.data.AddressData;
import com.kartha.cssp.data.DigitalCommunicationData;
import com.kartha.cssp.data.PreferencesData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.ContactInfoRequest;
import com.kartha.cssp.request.MailingAddressRequest;
import com.kartha.cssp.request.PreferencesDBRequest;

import java.util.List;

public interface ContactInfoServiceDAO {

    List<DigitalCommunicationData> updatePhone(ContactInfoRequest digitalCommunication) throws CSSPServiceException;

    List<DigitalCommunicationData> updateEmail(ContactInfoRequest digitalCommunication) throws CSSPServiceException;

    List<AddressData> updateMailingAddress(MailingAddressRequest mailingAddressRequest) throws CSSPServiceException;

    PreferencesData getAccountPreferences(String accountNumber) throws CSSPServiceException;

    PreferencesData updatePreferencesDB(PreferencesDBRequest preferencesData, String accountNumber) throws CSSPServiceException;
}
