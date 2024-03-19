package com.kartha.cssp.dao;

import java.util.List;

import com.kartha.cssp.data.LanderListData;

public interface  AccountLanderServiceDAO {

    LanderListData getAccountList(String userId, Boolean getOnlyDefaultFlag) throws Exception;
    List<LanderListData> getAccountUser(String accountNumber, String email) throws Exception;

}
