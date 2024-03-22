package com.kartha.cssp.dao;

import com.kartha.cssp.data.AccountEmailData;
import com.kartha.cssp.data.AccountEmailUIDData;
import com.kartha.cssp.data.ValidateUserData;
import com.kartha.cssp.model.UserManagement;
import com.kartha.cssp.request.*;

import java.util.List;

public interface RegistrationDAO {

    ValidateUserData validateUserId(String userId) throws Exception;

    boolean createUser(CreateUserRequest createUserRequest) throws Exception;

    void updatePassword(UpdatePasswordRequest updatePasswordRequest) throws Exception;

    AccountEmailUIDData forgotUID(ForgotUIDRequest forgotUIDRequest) throws Exception;

    List<AccountEmailData> forgotPass(ForgotPassRequest forgotPassRequest) throws Exception;

    void updateUserId(UpdateUserIdRequest updateUserIdRequest, String userId) throws Exception;

    List<UserManagement> allAdminUsers() throws Exception;

    UserManagement getAdminUser(String userId) throws Exception;
}
