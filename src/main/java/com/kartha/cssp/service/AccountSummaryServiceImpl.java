package com.kartha.cssp.service;

import com.kartha.cssp.dao.AccountSummaryServiceDAO;
import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.ErrorMessages;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class AccountSummaryServiceImpl implements AccountSummaryService {

    AccountSummaryServiceDAO accountSummaryServiceDAO;

    @Autowired
    public AccountSummaryServiceImpl(AccountSummaryServiceDAO accountSummaryServiceDAO) {
        this.accountSummaryServiceDAO = accountSummaryServiceDAO;
    }

    public CsspServiceResponse getAccountSummary(String accountNumber, String userId) throws CSSPServiceException {
        CsspServiceResponse<AccountData> csspAccountData = new CsspServiceResponse<AccountData>();
        try {

            csspAccountData.setData(accountSummaryServiceDAO.getAccountSummary(accountNumber,userId));

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in getAccount ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspAccountData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_ERROR,
                        CSSPConstants.FAILED,CSSPConstants.ACCOUNT_NOT_FOUND_MSG));
            } else {
                csspAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in getAccount ", e);
            csspAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspAccountData;
    }
    
    public AccountData getAccountSummaryList(String accountNumber) {
        AccountData accountSummary = null;
        try {
            accountSummary = accountSummaryServiceDAO.getAccountSummaryLite(accountNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accountSummary;
    }
}
