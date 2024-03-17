package com.kartha.cssp.service;

import com.kartha.cssp.dao.AccountServiceDAO;
import com.kartha.cssp.dao.ContactInfoServiceDAO;
import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.data.AddressData;
import com.kartha.cssp.data.DigitalCommunicationData;
import com.kartha.cssp.data.PreferencesData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.ContactInfoRequest;
import com.kartha.cssp.request.MailingAddressRequest;
import com.kartha.cssp.request.PreferencesDBRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ContactInfoServiceImpl implements ContactInfoService {

    ContactInfoServiceDAO contactInfoServiceDAO;
    AccountServiceDAO accountServiceDAO;

    @Autowired
    public ContactInfoServiceImpl(ContactInfoServiceDAO contactInfoServiceDAO,
                                  AccountServiceDAO accountServiceDAO) {
        this.contactInfoServiceDAO = contactInfoServiceDAO;
        this.accountServiceDAO = accountServiceDAO;
    }

    public CsspListServiceResponse updatePhone(ContactInfoRequest contactInfoRequest) throws CSSPServiceException {
        CsspListServiceResponse<DigitalCommunicationData> csspDigitalCommunicationDataList = new CsspListServiceResponse<DigitalCommunicationData>();
        try {

            csspDigitalCommunicationDataList.setData(contactInfoServiceDAO.updatePhone(contactInfoRequest));

        } catch (CSSPServiceException e) {
            log.error("Exception in getAccountPreferences ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            }  else {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        }  catch (Exception e) {
            log.error("Exception in updatePreferencesDB ", e);
            csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspDigitalCommunicationDataList;
    }

    public CsspListServiceResponse getPhone(String accountNumber) throws CSSPServiceException {
        CsspListServiceResponse<DigitalCommunicationData> csspDigitalCommunicationDataList = new CsspListServiceResponse<DigitalCommunicationData>();
        try {
            AccountData accountData = accountServiceDAO.getAccount(accountNumber);
            List<DigitalCommunicationData> digitalCommunicationDataList = accountData.getDigitalCommunicationInfo();
            csspDigitalCommunicationDataList.setData(digitalCommunicationDataList);
        } catch (CSSPServiceException e) {
            log.error("Exception in getAccountPreferences ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            }  else {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        }  catch (Exception e) {
            log.error("Exception in updatePreferencesDB ", e);
            csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspDigitalCommunicationDataList;
    }

    public CsspListServiceResponse updateEmail(ContactInfoRequest contactInfoRequest) throws CSSPServiceException {
        CsspListServiceResponse<DigitalCommunicationData> csspDigitalCommunicationDataList = new CsspListServiceResponse<DigitalCommunicationData>();
        try {

            csspDigitalCommunicationDataList.setData(contactInfoServiceDAO.updateEmail(contactInfoRequest));

        } catch (CSSPServiceException e) {
            log.error("Exception in getAccountPreferences ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            }  else {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        }  catch (Exception e) {
            log.error("Exception in updatePreferencesDB ", e);
            csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspDigitalCommunicationDataList;
    }
    public CsspListServiceResponse getEmail(String accountNumber) throws CSSPServiceException {
        CsspListServiceResponse<DigitalCommunicationData> csspDigitalCommunicationDataList = new CsspListServiceResponse<DigitalCommunicationData>();
        try {
            AccountData accountData = accountServiceDAO.getAccount(accountNumber);
            List<DigitalCommunicationData> digitalCommunicationDataList = accountData.getDigitalCommunicationInfo();
            csspDigitalCommunicationDataList.setData(digitalCommunicationDataList);
        } catch (CSSPServiceException e) {
            log.error("Exception in getAccountPreferences ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            }  else {
                csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        }  catch (Exception e) {
            log.error("Exception in updatePreferencesDB ", e);
            csspDigitalCommunicationDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspDigitalCommunicationDataList;
    }

    public CsspListServiceResponse updateMailingAddress(MailingAddressRequest mailingAddressRequest) throws CSSPServiceException {
        CsspListServiceResponse<AddressData> csspAddressDataList = new CsspListServiceResponse<AddressData>();
        try {

            csspAddressDataList.setData(contactInfoServiceDAO.updateMailingAddress(mailingAddressRequest));

        } catch (CSSPServiceException e) {
            log.error("Exception in getAccountPreferences ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspAddressDataList.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            }  else {
                csspAddressDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        }  catch (Exception e) {
            log.error("Exception in updatePreferencesDB ", e);
            csspAddressDataList.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspAddressDataList;
    }


    public CsspListServiceResponse getMailingAddress(String accountNumber) throws CSSPServiceException {
        CsspListServiceResponse<AddressData> csspAddressDataList = new CsspListServiceResponse<AddressData>();
        try {
            AccountData accountData = accountServiceDAO.getAccount(accountNumber);
            List<AddressData> AddressDataList = accountData.getAddress();
            csspAddressDataList.setData(AddressDataList);
        } catch (Exception e) {
            log.error("Exception in getMailingAddress ", e);
            throw new CSSPServiceException(e.getMessage(), CSSPConstants.ERROR_CODE);
        }
        return csspAddressDataList;
    }

    public CsspServiceResponse getAccountPreferences(String accountNumber) throws CSSPServiceException {
        CsspServiceResponse<PreferencesData> csspPreferencesData = new CsspServiceResponse<PreferencesData>();
        try {
            PreferencesData preferencesData = contactInfoServiceDAO.getAccountPreferences(accountNumber);
            csspPreferencesData.setData(preferencesData);
        } catch (CSSPServiceException e) {
            log.error("Exception in getAccountPreferences ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.PREFERENCES_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspPreferencesData.setMessage(new Messages(CSSPConstants.PREFERENCES_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.PREFERENCES_NOT_FOUND_MSG));
            }  else {
                csspPreferencesData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        }  catch (Exception e) {
            log.error("Exception in updatePreferencesDB ", e);
            csspPreferencesData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspPreferencesData;
    }

    public CsspServiceResponse updatePreferencesDB(PreferencesDBRequest preferencesData, String accountNumber) throws CSSPServiceException {
        CsspServiceResponse<PreferencesData> csspPreferencesData = new CsspServiceResponse<PreferencesData>();
        try {
            PreferencesData preferencesDataUpdated = contactInfoServiceDAO.updatePreferencesDB(preferencesData, accountNumber);
            csspPreferencesData.setData(preferencesDataUpdated);
        } catch (CSSPServiceException e) {
            log.error("Exception in Preference DB Updates ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.PREFERENCES_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspPreferencesData.setMessage(new Messages(CSSPConstants.PREFERENCES_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.PREFERENCES_NOT_FOUND_MSG));
            }  else {
                csspPreferencesData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in updatePreferencesDB ", e);
            csspPreferencesData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspPreferencesData;
    }

}
