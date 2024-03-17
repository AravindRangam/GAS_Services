package com.kartha.cssp.service;

import com.kartha.cssp.dao.OutageServiceDAO;
import com.kartha.cssp.data.AccountData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.OutageServiceRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
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
public class OutageServiceImpl implements OutageService {

    OutageServiceDAO outageServiceDAO;

    @Autowired
    public OutageServiceImpl(OutageServiceDAO outageServiceDAO) {
        this.outageServiceDAO = outageServiceDAO;
    }


    public CsspListServiceResponse validateOutageAddress(OutageServiceRequest outageServiceRequest)
            throws CSSPServiceException {
        CsspListServiceResponse<AccountData> csspAccountData = new CsspListServiceResponse<AccountData>();
        try {

            csspAccountData.setData(outageServiceDAO.validateOutageAddress(outageServiceRequest));

        } catch (CSSPServiceException e) {
            log.error("CSSPServiceException in validateOutageAddress ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.ACCOUNT_NOT_FOUND_ERROR.equalsIgnoreCase(e.getErrorCode())) {
                csspAccountData.setMessage(new Messages(CSSPConstants.ACCOUNT_NOT_FOUND_MSG,
                        CSSPConstants.FAILED,e.getMessage()));
            } else {
                csspAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e) {
            log.error("Exception in validateOutageAddress ", e);
            csspAccountData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspAccountData;
    }

    public CsspServiceResponse saveOutageDetails(OutageServiceRequest outageServiceRequest)
            throws CSSPServiceException {
        CsspServiceResponse<String> csspGenericResponse = new CsspServiceResponse<String>();
        try {

            csspGenericResponse.setData(outageServiceDAO.saveOutageDetails(outageServiceRequest));

        } catch (Exception e) {
            log.error("Exception in saveOutageDetails ", e);
            csspGenericResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e.getMessage()));
        }
        return csspGenericResponse;
    }

}
