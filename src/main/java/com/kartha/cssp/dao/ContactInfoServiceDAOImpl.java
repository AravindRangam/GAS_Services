package com.kartha.cssp.dao;

import com.kartha.cssp.data.AddressData;
import com.kartha.cssp.data.DigitalCommunicationData;
import com.kartha.cssp.data.PhoneOptionsTextEnabledOptInData;
import com.kartha.cssp.data.PreferencesData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.model.CommunicationPreferences;
import com.kartha.cssp.model.WhiteListPhones;
import com.kartha.cssp.request.ContactInfoRequest;
import com.kartha.cssp.request.MailingAddressRequest;
import com.kartha.cssp.request.PreferencesDBRequest;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.CommonUtils;
import com.kartha.cssp.utils.EmailServiceUtil;
import com.kartha.cssp.utils.SMSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class ContactInfoServiceDAOImpl implements ContactInfoServiceDAO {

    MongoTemplate mongodbTemplate;

    AccountSummaryServiceDAO accountSummaryServiceDAO;

    EmailServiceUtil emailServiceUtil;

    @Autowired
    public ContactInfoServiceDAOImpl(MongoTemplate mongodbTemplate, EmailServiceUtil emailServiceUtil, AccountSummaryServiceDAO accountSummaryServiceDAO) {
        this.mongodbTemplate = mongodbTemplate;
        this.accountSummaryServiceDAO = accountSummaryServiceDAO;
        this.emailServiceUtil = emailServiceUtil;
    }

    public List<DigitalCommunicationData> updatePhone(ContactInfoRequest digitalCommunicationRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(digitalCommunicationRequest.getAccountNumber()));
        List<Account> accountList = this.mongodbTemplate.find(query, Account.class);
        Account account;

        if (Objects.nonNull(accountList)
                && !accountList.isEmpty()) {

            account = accountList.get(0);

            if (Objects.nonNull(account)) {

                List<DigitalCommunicationData> digitalCommunicationDataList = account.getDigitalCommunicationInfo();

                List<DigitalCommunicationData> digitalCommunicationDataRequestList = digitalCommunicationRequest.getData();

                digitalCommunicationDataList.stream().filter(Objects::nonNull)
                        .forEach(digitalCommunicationData -> {
                            if (digitalCommunicationData.getCommunicationType()
                                    .equalsIgnoreCase("phone_no")) {

                                digitalCommunicationDataRequestList.stream().filter(Objects::nonNull)
                                        .forEach(digitalCommunicationReqData -> {

                                            if (digitalCommunicationData.getPriority()
                                                    .equalsIgnoreCase(digitalCommunicationReqData.getPriority())) {
                                                digitalCommunicationData.setCommunicateTo(digitalCommunicationReqData.getCommunicateTo());
                                                digitalCommunicationData.setUpdatedCommTs(CommonUtils.getCurrentTimeStamp());
                                            }

                                        });

                            }
                        });

                account = this.mongodbTemplate.save(account);

                try {
                    emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.
                            getAccountSummaryLite(account.getAccountNumber()), CSSPConstants.EMAIL_UPDATE_PHONE_NUMBER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }

        return account.getDigitalCommunicationInfo();
    }

    public List<DigitalCommunicationData> updateEmail(ContactInfoRequest digitalCommunicationRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(digitalCommunicationRequest.getAccountNumber()));
        List<Account> accountList = this.mongodbTemplate.find(query, Account.class);
        Account account;

        if (Objects.nonNull(accountList)
                && !accountList.isEmpty()) {

            account = accountList.get(0);

            if (Objects.nonNull(account)) {

                List<DigitalCommunicationData> digitalCommunicationDataList = account.getDigitalCommunicationInfo();

                List<DigitalCommunicationData> digitalCommunicationDataRequestList = digitalCommunicationRequest.getData();

                digitalCommunicationDataList.stream().filter(Objects::nonNull)
                        .forEach(digitalCommunicationData -> {
                            if (digitalCommunicationData.getCommunicationType()
                                    .equalsIgnoreCase("email_id")) {

                                digitalCommunicationDataRequestList.stream().filter(Objects::nonNull)
                                        .forEach(digitalCommunicationReqData -> {

                                            if (digitalCommunicationData.getPriority()
                                                    .equalsIgnoreCase(digitalCommunicationReqData.getPriority())) {
                                                digitalCommunicationData.setCommunicateTo(digitalCommunicationReqData.getCommunicateTo().toUpperCase());
                                                digitalCommunicationData.setUpdatedCommTs(CommonUtils.getCurrentTimeStamp());
                                            }

                                        });

                            }
                        });

                account = this.mongodbTemplate.save(account);

                try {
                        emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.
                                getAccountSummaryLite(account.getAccountNumber()), CSSPConstants.EMAIL_UPDATE_EMAIL_NUMBER);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        } else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }

        return account.getDigitalCommunicationInfo();
    }


    public List<AddressData> updateMailingAddress(MailingAddressRequest mailingAddressRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(mailingAddressRequest.getAccountNumber()));
        List<Account> accountList = this.mongodbTemplate.find(query, Account.class);
        Account account = null;

        if (Objects.nonNull(accountList)
                && !accountList.isEmpty()) {

            account = accountList.get(0);

            if (Objects.nonNull(account)) {

                List<AddressData> addressDataList = account.getAddress();

                AddressData mailingAddressRequestData = mailingAddressRequest.getData();

                for (AddressData addressData : addressDataList) {
                    if (addressData != null) {
                        if (addressData.getAddressType().equalsIgnoreCase("MAILING")) {
                            addressData.setAptNo(mailingAddressRequestData.getAptNo());
                            addressData.setHouseNo(mailingAddressRequestData.getHouseNo());
                            addressData.setStreetName(mailingAddressRequestData.getStreetName());
                            addressData.setCity(mailingAddressRequestData.getCity());
                            addressData.setState(mailingAddressRequestData.getState());
                            addressData.setCountry(mailingAddressRequestData.getCountry());
                            addressData.setZip(mailingAddressRequestData.getZip());
                            addressData.setUpdatedAddTs(CommonUtils.getCurrentTimeStamp());
                        }
                        account = this.mongodbTemplate.save(account);

                        try {
                            emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.
                                    getAccountSummaryLite(account.getAccountNumber()), CSSPConstants.EMAIL_UPDATE_MAILING_ADDRESS);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } else {
                throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
            }
        }

        return account.getAddress();
    }

    public PreferencesData getAccountPreferences(String accountNumber) throws CSSPServiceException {
        PreferencesData preferencesData = new PreferencesData();
        Query query = new Query();
        System.out.println("before query runs" + accountNumber);


        query.addCriteria(new Criteria().andOperator(Criteria.where("accountNumber").is(accountNumber),
                    (Criteria.where("preferenceLevel").is("account_id"))));

        CommunicationPreferences communicationPreferences = this.mongodbTemplate.findOne(query, CommunicationPreferences.class);

        if (Objects.nonNull(communicationPreferences)) {
            preferencesData.setAccountNumber(communicationPreferences.getAccountNumber());
            preferencesData.setCompanyCode(communicationPreferences.getCompanyCode());
            preferencesData.setLineOfBusiness(communicationPreferences.getLineOfBusiness());
            preferencesData.setPreferenceLevel(communicationPreferences.getPreferenceLevel());
            preferencesData.setPhoneOptionsTextEnabledOptIn(communicationPreferences.getPhoneOptionsTextEnabledOptIn());
            preferencesData.setEmailProgramInfo(communicationPreferences.getEmailProgramInfo());

        } else {
            throw new CSSPServiceException(CSSPConstants.PREFERENCES_NOT_FOUND_ERROR, CSSPConstants.PREFERENCES_NOT_FOUND_MSG);
        }
        return preferencesData;
    }

    public PreferencesData updatePreferencesDB(PreferencesDBRequest preferencesData , String accountNumber) throws CSSPServiceException {
        PreferencesData returnPreferencesData = new PreferencesData();
        Query query = new Query();
        System.out.println("before query runs" + accountNumber);
        System.out.println("pref data input acct" + preferencesData.getAccountNumber());

        if (accountNumber.equalsIgnoreCase(preferencesData.getAccountNumber())) {

            query.addCriteria(new Criteria().andOperator(Criteria.where("accountNumber").is(accountNumber),
                    (Criteria.where("preferenceLevel").is("account_id"))));

            CommunicationPreferences communicationPreferences = this.mongodbTemplate.findOne(query, CommunicationPreferences.class);


            if (Objects.nonNull(communicationPreferences)) {

                communicationPreferences.setPhoneOptionsTextEnabledOptIn(preferencesData.getPhoneOptionsTextEnabledOptIn());
                communicationPreferences.setEmailProgramInfo(preferencesData.getEmailProgramInfo());

                communicationPreferences = this.mongodbTemplate.save(communicationPreferences);

                returnPreferencesData.setAccountNumber(communicationPreferences.getAccountNumber());
                returnPreferencesData.setCompanyCode(communicationPreferences.getCompanyCode());
                returnPreferencesData.setLineOfBusiness(communicationPreferences.getLineOfBusiness());
                returnPreferencesData.setPreferenceLevel(communicationPreferences.getPreferenceLevel());
                returnPreferencesData.setPhoneOptionsTextEnabledOptIn(communicationPreferences.getPhoneOptionsTextEnabledOptIn());
                returnPreferencesData.setEmailProgramInfo(communicationPreferences.getEmailProgramInfo());
                try {
                    emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.
                            getAccountSummaryLite(accountNumber), CSSPConstants.EMAIL_UPDATED_PREFERENCES);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            } else {
                throw new CSSPServiceException(CSSPConstants.PREFERENCES_NOT_FOUND_ERROR, CSSPConstants.PREFERENCES_NOT_FOUND_MSG);
            }
            return returnPreferencesData;
        } else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }
    }

    public void sendSMSForWhiteListedPhonesOnly(List<PhoneOptionsTextEnabledOptInData> phoneOptionsTextEnabledOptIn, String phoneNumber) throws Exception {

        List<WhiteListPhones> whiteListPhonesList = this.mongodbTemplate.findAll(WhiteListPhones.class);

        for(PhoneOptionsTextEnabledOptInData phoneOptionsTextEnabledOptInData: phoneOptionsTextEnabledOptIn) {
            if(Objects.nonNull(phoneOptionsTextEnabledOptInData.isTextEnabled())) {
                whiteListPhonesList.stream().filter(Objects::nonNull)
                        .forEach(whiteNum -> {
                            if(whiteNum.getPhoneNo().equalsIgnoreCase(phoneNumber)) {
                                try {
                                    SMSClient.sendSMS(phoneNumber);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
            }
        }
    }
}