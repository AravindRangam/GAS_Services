package com.kartha.cssp.dao;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;
import com.kartha.cssp.config.AppConfig;
import com.kartha.cssp.request.CreateUserRequest;
import com.kartha.cssp.request.UpdatePasswordRequest;
import com.kartha.cssp.request.UpdateUserIdRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class CognitoDAOImpl {

    private AppConfig appConfig;

    public CognitoDAOImpl(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public AWSCognitoIdentityProvider getAmazonCognitoIdentityClient() {
        ClasspathPropertiesFileCredentialsProvider propertiesFileCredentialsProvider =
                new ClasspathPropertiesFileCredentialsProvider();

        return AWSCognitoIdentityProviderClientBuilder.standard()
                .withCredentials(propertiesFileCredentialsProvider)
                .withRegion(appConfig.getRegionName())
                .build();

    }

    @Transactional
    public String createUserInCognito(CreateUserRequest createUserRequest) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        List<AttributeType> attributeTypeList = new ArrayList<>();

        attributeTypeList.add(new AttributeType()
                .withName("email")
                .withValue(createUserRequest.getUserId()));

        SignUpRequest signUpRequest = new SignUpRequest();
        signUpRequest.setPassword(createUserRequest.getConfirmPassword());
        signUpRequest.setUsername(createUserRequest.getUserId());
        signUpRequest.setUserAttributes(attributeTypeList);
        signUpRequest.setClientId("2848dl9lpbsnq493qn3emgaa9k");

        SignUpResult signUpResult = cognitoClient.signUp(signUpRequest);

        AdminConfirmSignUpRequest adminConfirmSignUpRequest = new AdminConfirmSignUpRequest();
        adminConfirmSignUpRequest.withUserPoolId(appConfig.getUserPoolId())
                .withUsername(createUserRequest.getUserId());

        AdminConfirmSignUpResult adminConfirmSignUpResult = cognitoClient.adminConfirmSignUp(adminConfirmSignUpRequest);

        return signUpResult.getUserSub();
    }

    public String updateUserInCognito(UpdateUserIdRequest updateUserIdRequest) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUserId(updateUserIdRequest.getNewUserId());
        createUserRequest.setConfirmPassword(updateUserIdRequest.getPassword());
        String uuid = createUserInCognito(createUserRequest);

        AdminDeleteUserRequest adminDeleteUserRequest = new AdminDeleteUserRequest();
        adminDeleteUserRequest.setUserPoolId(appConfig.getUserPoolId());
        adminDeleteUserRequest.setUsername(updateUserIdRequest.getUserId());
        cognitoClient.adminDeleteUser(adminDeleteUserRequest);

        return uuid;
    }

    @Transactional
    public void updateUserPasswordInCognito(UpdatePasswordRequest updatePasswordRequest) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
        AdminSetUserPasswordRequest adminSetUserPasswordRequest = new AdminSetUserPasswordRequest();
        adminSetUserPasswordRequest.setPassword(updatePasswordRequest.getConfirmPassword());
        adminSetUserPasswordRequest.setPermanent(true);
        adminSetUserPasswordRequest.setUsername(updatePasswordRequest.getUserId());
        adminSetUserPasswordRequest.setUserPoolId(appConfig.getUserPoolId());
        cognitoClient.adminSetUserPassword(adminSetUserPasswordRequest);
    }

    @Transactional
    public void DisableUserInCognito(String userId) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
        AdminDisableUserRequest adminDisableUserRequest = new AdminDisableUserRequest();
        adminDisableUserRequest.setUserPoolId(appConfig.getUserPoolId());
        adminDisableUserRequest.setUsername(userId);
        cognitoClient.adminDisableUser(adminDisableUserRequest);
    }

    @Transactional
    public void EnableUserInCognito(String userId) {
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();
        AdminEnableUserRequest adminEnableUserRequest = new AdminEnableUserRequest();
        adminEnableUserRequest.setUserPoolId(appConfig.getUserPoolId());
        adminEnableUserRequest.setUsername(userId);
        cognitoClient.adminEnableUser(adminEnableUserRequest);
    }

    /*
    This below code is needed for verification
    public  SpringSecurityUser signIn(AuthenticationRequestauthenticationRequest){
        AuthenticationResultType authenticationResult = null;
        AWSCognitoIdentityProvider cognitoClient = getAmazonCognitoIdentityClient();

        final Map<String, String>authParams = new HashMap<>();
        authParams.put(USERNAME, authenticationRequest.getUsername());
        authParams.put(PASS_WORD, authenticationRequest.getPassword());

        final AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest();
        authRequest.withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .withClientId(cognitoConfig.getClientId())
                .withUserPoolId(cognitoConfig.getUserPoolId())
        withAuthParameters(authParams);

        AdminInitiateAuthResult result = cognitoClient.adminInitiateAuth(authRequest);

        //Has a Challenge
        if(StringUtils.isNotBlank(result.getChallengeName())) {
//If the challenge is required new Password validates if it has the new password variable.
            if(NEW_PASS_WORD_REQUIRED.equals(result.getChallengeName())){
                if(null == authenticationRequest.getNewPassword()) {
                    throw new CognitoException(messages.get(USER_MUST_PROVIDE_A_NEW_PASS_WORD), CognitoException.USER_MUST_CHANGE_PASS_WORD_EXCEPTION_CODE, result.getChallengeName());
                }else{
                    //we still need the username
                    final Map<String, String> challengeResponses = new HashMap<>();
                    challengeResponses.put(USERNAME, authenticationRequest.getUsername());
                    challengeResponses.put(PASS_WORD, authenticationRequest.getPassword());
                    //add the new password to the params map
                    challengeResponses.put(NEW_PASS_WORD, authenticationRequest.getNewPassword());
                    //populate the challenge response
                    final AdminRespondToAuthChallengeRequest request =
                            new AdminRespondToAuthChallengeRequest();
                    request.withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                            .withChallengeResponses(challengeResponses)
                            .withClientId(cognitoConfig.getClientId())
                            .withUserPoolId(cognitoConfig.getPoolId())
                            .withSession(result.getSession());

                    AdminRespondToAuthChallengeResult resultChallenge =
                            cognitoClient.adminRespondToAuthChallenge(request);
                    authenticationResult = resultChallenge.getAuthenticationResult();
                }
            }else{
                //has another challenge
                throw new CognitoException(result.getChallengeName(),
                        CognitoException.USER_MUST_DO_ANOTHER_CHALLENGE, result.getChallengeName());
            }
        }else{
            //Doesn't have a challenge
            authenticationResult = result.getAuthenticationResult();
        }
        cognitoClient.shutdown();
        return userAuthenticated;
    }*/

}
