package com.kartha.cssp.service;

import com.kartha.cssp.dao.AccountLanderServiceDAO;
import com.kartha.cssp.data.*;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.response.AccountLanderResponse;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.ErrorMessages;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Slf4j
@Service
public class AccountLanderServiceImpl implements AccountLanderService {

        AccountLanderServiceDAO accountLanderServiceDAO;

        @Autowired
        public AccountLanderServiceImpl(AccountLanderServiceDAO accountLanderServiceDAO) {
            this.accountLanderServiceDAO = accountLanderServiceDAO;
        }

        public CsspServiceResponse getAccountList(String userId, Boolean getOnlyDefaultFlag) throws CSSPServiceException {
            CsspServiceResponse<AccountLanderResponse> csspLanderListData = new CsspServiceResponse<AccountLanderResponse>();
            AccountLanderResponse accountLanderResponse = new AccountLanderResponse();
            try {
                LanderListData landerListData = accountLanderServiceDAO.getAccountList(userId, getOnlyDefaultFlag);
                populateAccountLanderResponse(accountLanderResponse, landerListData);
                csspLanderListData.setData(accountLanderResponse);

            } catch (CSSPServiceException e) {
                log.error("CSSPServiceException in getList ", e);
                if (Objects.nonNull(e.getErrorCode()) &&
                        CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                    csspLanderListData.setMessage(new Messages(CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR,
                            CSSPConstants.FAILED,CSSPConstants.USER_ACCOUNT_NOT_FOUND_ERROR_MSG));
                } else if (Objects.nonNull(e.getErrorCode()) &&
                        CSSPConstants.USER_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                    csspLanderListData.setMessage(new Messages(CSSPConstants.USER_NOT_FOUND_ERROR,
                            CSSPConstants.FAILED,CSSPConstants.USER_NOT_FOUND_ERROR_MSG));
                } else {
                    csspLanderListData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                            CSSPConstants.FAILED,e.getMessage()));
                }
            } catch (Exception e) {
                log.error("Exception in getList ", e);
                csspLanderListData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
            return csspLanderListData;
        }

        public void populateAccountLanderResponse(AccountLanderResponse accountLanderResponse,
                                                  LanderListData landerListData) {
            accountLanderResponse.setCode("00");
            accountLanderResponse.setDefaultAccount(landerListData.getDefaultAccountNumber());

            List<AccountLanderListData> accountLanderListDataList =
                    new ArrayList<AccountLanderListData>();

            List<AccountData> accountDataList = landerListData.getAccountData();

            if(Objects.nonNull(accountDataList)) {
                accountDataList.stream().filter(Objects::nonNull)
                        .forEach(accountData -> {
                            AccountLanderListData accountLanderListData =
                                    new AccountLanderListData();
                            accountLanderListData.setAccountBalance(accountData.getContractInfoData().getTotalAmountDue());
                            accountLanderListData.setAccountNumber(accountData.getAccountNumber());
                            accountLanderListData.setDefaultAccount(landerListData.getDefaultAccountNumber());
                            accountLanderListData.setAccountStatus("ACTIVE");
                            accountLanderListData.setPinVerificationRequired(false);
                            accountLanderListData.setWholeName(accountData.getFirstName()+" "+accountData.getLastName());
                            accountLanderListData.setDueDate(accountData.getContractInfoData().getDueDate());

                            InstallationInfo installationInfo = accountData.getInstallationInfo();
                            StringBuilder serviceTypes = new StringBuilder();
                            if(Objects.nonNull(installationInfo)) {
                                List<InstallationInfoData> installationInfoDataList = installationInfo.getInstallationInfoData();
                                if(Objects.nonNull(installationInfoDataList)) {
                                    installationInfoDataList.stream().filter(Objects::nonNull)
                                            .forEach(installationInfoData -> {
                                                serviceTypes.append(installationInfoData.getInstallationTypeDesc()).append(",");
                                            });
                                }
                            }

                            // remove last comma from serviceTypes string
                            if(serviceTypes.length() > 0) {
                                serviceTypes.deleteCharAt(serviceTypes.length()-1);
                            }
                            accountLanderListData.setServiceTypes(serviceTypes.toString());


                            List<AddressData> addressDataList = accountData.getAddress();
                            Predicate<AddressData> addressDataPredicate = addressData -> "PREMISE".equalsIgnoreCase(addressData.getAddressType());
                            if(Objects.nonNull(addressDataList)) {
                                addressDataList.stream().filter(Objects::nonNull)
                                        .filter(addressDataPredicate)
                                        .forEach(addressData -> {
                                            Addresses addresses = new Addresses();
                                            addresses.setHouseNumber1(addressData.getHouseNo());
                                            addresses.setStreet(addressData.getStreetName());
                                            addresses.setCity(addressData.getCity());
                                            addresses.setRegion(addressData.getState());
                                            addresses.setZipCode(addressData.getZip());

                                            accountLanderListData.setServiceAddressLine1(addressData.getHouseNo()+" "+addressData.getStreetName());
                                            accountLanderListData.setServiceAddressLine2(addressData.getCity()+" "+addressData.getState()+" "+addressData.getZip());

                                        });
                            }

                            accountLanderListDataList.add(accountLanderListData);
                        });

                accountLanderResponse.setAccounts(accountLanderListDataList);
            }

        }

        public CsspListServiceResponse<LanderListData> getAccountUser(String accountNumber) throws CSSPServiceException {
            CsspListServiceResponse<LanderListData> csspLanderListData = new CsspListServiceResponse<LanderListData>();
            try {
                List<LanderListData> landerListData = accountLanderServiceDAO.getAccountUser(accountNumber);
                csspLanderListData.setData(landerListData);

            } catch (CSSPServiceException e) {
                log.error("CSSPServiceException in getAccountUserId ", e);
                if (Objects.nonNull(e.getErrorCode()) &&
                        CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                    csspLanderListData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                            CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
                } else {
                    csspLanderListData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                            CSSPConstants.FAILED,e.getMessage()));
                }
            } catch (Exception e) {
                log.error("Exception in getAccountUserId ", e);
                csspLanderListData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
            return csspLanderListData;
        }
}
