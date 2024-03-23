package com.kartha.cssp.utils;

public class CSSPConstants {

    public static final String SUCCESS = "SUCCESS";
    public static final String FAILED = "FAILED";
    public static final String ERROR_CODE = "server.error";

    public static final String VALIDATION_SUCCESS_MSG = "Validation Successful.";

    //user account
    public static final String USER_ACCOUNT_NOT_FOUND_ERROR = "user.account.not.found.error";
    public static final String USER_ACCOUNT_NOT_FOUND_ERROR_MSG = "Invalid account combination.";
    public static final String USER_NOT_FOUND_ERROR = "user.not.found.error";
    public static final String USER_NOT_FOUND_ERROR_MSG = "Invalid User Id.";

    public static final String USER_ACCOUNT_EXISTS_ERROR =  "user.account.exists.error";
    public static final String USER_ACCOUNT_EXISTS_ERROR_MSG =  "User Account combination exist.";

    //payment
    public static final String PAYMENT_DETAILS_NOT_EXIST= "Payment details does not exist.";
    public static final String PAYMENT_SUBMIT_ERROR = "pyament.submit.error";
    public static final String PAYMENT_SUBMIT_ERROR_MSG = "payment submit was not success.";

    public static final String PAYMENT_BANK_ERROR = "pyament.program.enroll.error";
    public static final String PAYMENT_BANK_ERROR_MSG = "update bank details failed.";

    //account not found
    public static final String ACCOUNT_NOT_FOUND_ERROR = "account.notFound.error";
    public static final String ACCOUNT_NOT_FOUND_MSG = "Account not found.";

    public static final String PREFERENCES_NOT_FOUND_ERROR = "Preferences not found.";
    public static final String PREFERENCES_NOT_FOUND_MSG = "Preferences not found.";

    //validate account ssn
    public static final String ACCOUNT_SSN_NOT_MATCH_ERROR = "account.ssn.error";
    public static final String ACCOUNT_SSN_MATCH_ERROR_MSG = "Invalid account combination.";

    //Registration
    public static final String VALIDATE_USER_ID_ERROR = "userid.validate.error";
    public static final String VALIDATE_USER_ID = "userid.validate";
    public static final String USER_ACCOUNT_REGISTRATION = "user.create.successful";
    public static final String USER_ACCOUNT_REGISTRATION_EXISTS = "user.account.registered.error";

    // Admin users
    public static final String ALL_ADMIN_USERS = "all.admin.users";

    //update password
    public static final String UPDATE_PASSWORD_SUCCESS = "update.pwd.success";
    public static final String RESET_PASSWORD_SUCCESS = "reset.pwd.success";

    //forgot uid
    public static final String FORGOT_UID_SUCCESS = "retriee.uid.success";

    //forgot password validation
    public static final String FORGOT_PWD_VALIDATION_SUCCESS = "forgotpwd.validation.success";

    //Service Order
    public static final String SERVICE_ORDER_FAILED = "service.order.error";
    public static final String SERVICE_ORDER_FAILED_MSG = "Invalid Service order request.";

    //Programs
    public static final String UPDATE_AUTOPAY_FAILED_ERROR = "autopay.update.error";
    public static final String UPDATE_AUTOPAY_FAILED_ERROR_MSG = "Autopay error";

    public static final String UPDATE_FLATBILL_FAILED_ERROR = "update.flatbill.error";
    public static final String UPDATE_FLATBILL_FAILED_ERROR_MSG = "Flatbill error";

    public static final String UPDATE_BB_FAILED_ERROR = "update.BB.error";
    public static final String UPDATE_BB_FAILED_ERROR_MSG = "Budgetbill error";

    public static final String UPDATE_EMB_FAILED_ERROR = "update.EMB.error";
    public static final String UPDATE_EMB_FAILED_ERROR_MSG = "EMB error";

    public static final String UPDATE_PAYEXT_FAILED_ERROR = "update.PaymentExtension.error";
    public static final String UPDATE_PAYEXT_FAILED_ERROR_MSG = "Payment Extension error";

    //EMAIL
    public static final String EMAIL_REGISTRATION = "REGISTRATION";
    public static final String EMAIL_UPDATE_PHONE_NUMBER = "UPDATE_PHONE_NUMBER";
    public static final String EMAIL_UPDATE_EMAIL_NUMBER = "UPDATE_EMAIL";
    public static final String EMAIL_UPDATE_MAILING_ADDRESS = "UPDATE_MAILING_ADDRESS";
    public static final String EMAIL_UPDATED_PREFERENCES = "UPDATED_PREFERENCES";
    public static final String EMAIL_MAKE_PAYMENT = "EMAIL_MAKE_PAYMENT";
    public static final String EMAIL_ENROLL_EMB = "EMAIL_ENROLL_EMB";
    public static final String EMAIL_ENROLL_BB = "EMAIL_ENROLL_BB";
    public static final String EMAIL_ENROLL_FLAT_BILL = "EMAIL_ENROLL_FLAT_BILL";
    public static final String EMAIL_ENROLL_AUTO_PAY = "EMAIL_ENROLL_AUTO_PAY";
    public static final String EMAIL_ENROLL_PAY_EXTEND = "EMAIL_ENROLL_PAY_EXTEND";
    public static final String EMAIL_ENROLL_ONLINE_PAYMENT = "EMAIL_ENROLL_ONLINE_PAYMENT";

}
