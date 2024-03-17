package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountEmailData;
import com.kartha.cssp.data.AccountEmailUIDData;
import com.kartha.cssp.data.UserAccountInfoData;
import com.kartha.cssp.data.ValidateUserData;
import com.kartha.cssp.data.UserIdAndBase64;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.Account;
import com.kartha.cssp.model.UserManagement;
import com.kartha.cssp.model.UserRegistrationInfo;
import com.kartha.cssp.request.*;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.CommonUtils;
import com.kartha.cssp.utils.EmailServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;


@Slf4j
@Repository
public class RegistrationDAOImpl implements RegistrationDAO {

    MongoTemplate mongodbTemplate;

    CognitoDAOImpl cognitoDAOImpl;

    AccountSummaryServiceDAO accountSummaryServiceDAO;

    EmailServiceUtil emailServiceUtil;

    @Autowired
    public RegistrationDAOImpl(MongoTemplate mongodbTemplate,
                               CognitoDAOImpl cognitoDAOImpl,
                               AccountSummaryServiceDAO accountSummaryServiceDAO,
                               EmailServiceUtil emailServiceUtil) {
        this.mongodbTemplate = mongodbTemplate;
        this.cognitoDAOImpl = cognitoDAOImpl;
        this.accountSummaryServiceDAO = accountSummaryServiceDAO;
        this.emailServiceUtil = emailServiceUtil;
    }

    @Override
    public ValidateUserData validateUserId(String userId) throws Exception {
        ValidateUserData validateUserData = new ValidateUserData();
        validateUserData.setUserId(userId);
        validateUserData.setAvailable(false);
        validateUserData.setTemp(false);

        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId.toUpperCase()));
        List<UserRegistrationInfo> userRegistrationInfoList = mongodbTemplate.find(query, UserRegistrationInfo.class);

        if (Objects.nonNull(userRegistrationInfoList)
                && !userRegistrationInfoList.isEmpty()) {
            validateUserData.setRegistered(true);
        } else {
            validateUserData.setRegistered(false);
            validateUserData.setAvailable(true);
        }


        /*
        This is retrieve all the values without filter

        List<UserRegistrationInfo> userRegistrationInfoList = mongodbTemplate.findAll(UserRegistrationInfo.class);
        */

        /*
        Another approach without class ... in this we need to go with key value pairs..

        MongoCollection<Document> collection = mongodbTemplate.getCollection("user_registration");

        FindIterable<Document> documents = collection.find();

        log.info(" User_Registration getDb 2::..."+documents);

        MongoCursor<Document> mongoCursor = documents.iterator();

        log.info(" User_Registration getDb 3::..."+mongoCursor.hasNext());

        while(mongoCursor.hasNext()) {
            Document document = mongoCursor.next();
            log.info(".........."+document.getString("userId"));
        }
        */

        return validateUserData;
    }

    public boolean createUser(CreateUserRequest createUserRequest) throws Exception {
        Query query = new Query();

        query.addCriteria(new Criteria().andOperator(
                Criteria.where("userId").is(createUserRequest.getUserId().toUpperCase()),
                Criteria.where("userAccountInfo.accountNumber").is(createUserRequest.getAccountNumber())
        ));

        UserManagement userManagement = mongodbTemplate.findOne(query, UserManagement.class);

        if (Objects.nonNull(userManagement)) {
            throw new CSSPServiceException(CSSPConstants.USER_ACCOUNT_REGISTRATION_EXISTS, "This user id is alread exists.");
        } else {
            //Registration FLOW
            userManagement = new UserManagement();
            addAccountToUserManagement(userManagement, createUserRequest);
        }

        this.mongodbTemplate.save(userManagement);

        emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.
                getAccountSummaryLite(createUserRequest.getAccountNumber()), CSSPConstants.EMAIL_REGISTRATION);

        return true;
    }

    public UserManagement addAccountToUserManagement(UserManagement userManagement,
                                                     CreateUserRequest createUserRequest) throws CSSPServiceException {
        Query accountQuery = new Query();

        accountQuery.addCriteria(Criteria.where("accountNumber").is(createUserRequest.getAccountNumber()));

        Account accountInfo = mongodbTemplate.findOne(accountQuery, Account.class);

        if (Objects.isNull(accountInfo)) {
            throw new CSSPServiceException("ACCOUNT_NOT_FOUND_ERROR", "Account not found.");
        } else {

            String uuid = this.cognitoDAOImpl.createUserInCognito(createUserRequest);

            userManagement.setUuid(uuid);

            List<UserAccountInfoData> userAccountList = new ArrayList<>();
            UserAccountInfoData userAccountInfoData = new UserAccountInfoData();

            userManagement.setUserId(createUserRequest.getUserId().toUpperCase());
            userManagement.setUserCreatedTS(CommonUtils.getCurrentTimeStamp());
            userManagement.setUserLastUpdatedTS(CommonUtils.getCurrentTimeStamp());
            userManagement.setDefaultAccountNumber(createUserRequest.getAccountNumber());
            userManagement.setUserRole(createUserRequest.getUserRole());

            userAccountInfoData.setAccountNumber(createUserRequest.getAccountNumber());
            userAccountInfoData.setNickName("");
            userAccountInfoData.setRowCreatedTs(CommonUtils.getCurrentTimeStamp());
            userAccountInfoData.setRowUpdatedTS(CommonUtils.getCurrentTimeStamp());
            userAccountInfoData.setRowDeletedInd("N");
            userAccountInfoData.setUserAccountRelation("");
            userAccountInfoData.setAccountAddedFrom(createUserRequest.getAddedFrom());
            userAccountInfoData.setAccountAddedBy(createUserRequest.getUserId().toUpperCase());
            userAccountInfoData.setDefaultAccount("Y");
            userAccountInfoData.setDefaultAccountChangeTS(CommonUtils.getCurrentTimeStamp());
            userAccountList.add(userAccountInfoData);

            userManagement.setUserAccountInfo(userAccountList);
        }
        return userManagement;
    }

    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) throws CSSPServiceException {

        this.cognitoDAOImpl.updateUserPasswordInCognito(updatePasswordRequest);
    }


    public List<AccountEmailData> forgotPass(ForgotPassRequest forgotPassRequest) throws CSSPServiceException {
        AccountEmailData accountEmailData = new AccountEmailData();
        List<AccountEmailData> accountEmailDataList = new ArrayList<AccountEmailData>();
        Query userQuery = new Query();
        userQuery.addCriteria(new Criteria().andOperator(Criteria.where("userId").is(forgotPassRequest.getUserId().toUpperCase())));
        UserManagement userManagementRow = this.mongodbTemplate.findOne(userQuery, UserManagement.class);

        if (!Objects.nonNull(userManagementRow)) {
            throw new CSSPServiceException("ACCOUNT_USERID_NOT_FOUND_ERROR", "No Users registered for account");
        }
        List<UserAccountInfoData> userAccountInfoDataRows = userManagementRow.getUserAccountInfo();
        userAccountInfoDataRows.stream().filter(Objects::nonNull)
                .forEach(userAccountInfoDataEachRow -> {
                            if (userAccountInfoDataEachRow.getRowDeletedInd().equalsIgnoreCase("N")) {
                                Query query = new Query();
                                query.addCriteria(Criteria.where("accountNumber").is(userAccountInfoDataEachRow.getAccountNumber()));
                                Account accountList = mongodbTemplate.findOne(query, Account.class);
                                if (Objects.nonNull(accountList)) {
                                    if (CommonUtils.validateLast4Digits(accountList.getSsnTaxid(), forgotPassRequest.getLast4SSN())) {
                                        accountEmailData.setAccountNumber(accountList.getAccountNumber());
                                        accountEmailData.setDigitalCommunicationData(accountList.getDigitalCommunicationInfo());
                                        accountEmailDataList.add(accountEmailData);
                                    }
                                }
                            }
                        }
                );
        return accountEmailDataList;
    }

    public void updateUserId(UpdateUserIdRequest updateUserIdRequest, String userId) throws CSSPServiceException {

        Query userManagementQuery = new Query();

        /*userManagementQuery.addCriteria(
                new Criteria().andOperator(Criteria.where("userId").is(toUpperCase(updateUserIdRequest.getUserId())),
                        Criteria.where("uuid").is(updateUserIdRequest.getUuid())));*/

        userManagementQuery.addCriteria(Criteria.where("userId").is(StringUtils.upperCase(updateUserIdRequest.getUserId())));

        UserManagement userManagementRow = this.mongodbTemplate.findOne(userManagementQuery, UserManagement.class);

        if (Objects.isNull(userManagementRow)) {
            throw new CSSPServiceException(CSSPConstants.USER_NOT_FOUND_ERROR, CSSPConstants.USER_NOT_FOUND_ERROR_MSG);
        } else {
            String uuid = this.cognitoDAOImpl.updateUserInCognito(updateUserIdRequest);

            userManagementRow.setUserId(StringUtils.upperCase(updateUserIdRequest.getNewUserId()));
            userManagementRow.setUuid(uuid);

            this.mongodbTemplate.save(userManagementRow);
        }

    }

    public AccountEmailUIDData forgotUID(ForgotUIDRequest forgotUIDRequest) throws CSSPServiceException {
        AccountEmailUIDData accountEmailUIDData = new AccountEmailUIDData();
        Query query = new Query();
        int i = 0;
        List<UserIdAndBase64> userIdAndBase64List = new ArrayList<UserIdAndBase64>();

        query.addCriteria(Criteria.where("accountNumber").is(forgotUIDRequest.getAccountNumber()));
        Account accountList = mongodbTemplate.findOne(query, Account.class);

        if (Objects.nonNull(accountList)) {
            if (CommonUtils.validateLast4Digits(accountList.getSsnTaxid(), forgotUIDRequest.getLast4SSN())) {
                accountEmailUIDData.setAccountNumber(accountList.getAccountNumber());
                accountEmailUIDData.setDigitalCommunicationData(accountList.getDigitalCommunicationInfo());

                Query userQuery = new Query();
                userQuery.addCriteria(new Criteria().andOperator(Criteria.where("userAccountInfo.accountNumber").is(forgotUIDRequest.getAccountNumber()),
                        Criteria.where("userAccountInfo.rowDeletedInd").is("N")));
                List<UserManagement> userManagementAllRows = this.mongodbTemplate.find(userQuery, UserManagement.class);

                if (!Objects.nonNull(userManagementAllRows)) {
                    throw new CSSPServiceException("ACCOUNT_USERID_NOT_FOUND_ERROR", "No Users registered for account");
                }
                userManagementAllRows.stream().filter(Objects::nonNull)
                        .forEach(userManagementUIDObject -> {
                            List<UserAccountInfoData> userAccountInfoDataRows = userManagementUIDObject.getUserAccountInfo();
                            userAccountInfoDataRows.stream().filter(Objects::nonNull)
                                    .forEach(userAccountInfoDataEachRow -> {
                                                if (userAccountInfoDataEachRow.getRowDeletedInd().equalsIgnoreCase("N") &&
                                                        userAccountInfoDataEachRow.getAccountNumber().equalsIgnoreCase(forgotUIDRequest.getAccountNumber())) {

                                                    UserIdAndBase64 userIdAndBase64 = new UserIdAndBase64();

                                                    userIdAndBase64.setUserIdMasked(userManagementUIDObject.getUserId().replaceAll("(?<=.{2}).(?=[^@]*?.@)", "*"));
                                                    userIdAndBase64.setUserIdBase64(Base64.getEncoder().encodeToString(userManagementUIDObject.getUserId().getBytes()));

                                                    userIdAndBase64List.add(userIdAndBase64);
                                                  }
                                            }
                                    );
                        });
                accountEmailUIDData.setUserIdAndBase64(userIdAndBase64List);
            } else {
                throw new CSSPServiceException(CSSPConstants.ACCOUNT_SSN_NOT_MATCH_ERROR, CSSPConstants.ACCOUNT_SSN_MATCH_ERROR_MSG);
            }
        } else {
            throw new CSSPServiceException(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR, CSSPConstants.ACCOUNT_NOT_FOUND_MSG);
        }
        return accountEmailUIDData;
    }
}
