package com.kartha.cssp.service;

import com.kartha.cssp.dao.ServiceOrderDAO;
import com.kartha.cssp.data.ServiceOrderData;
import com.kartha.cssp.data.ServiceRequestData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.ServiceRequestBody;
import com.kartha.cssp.request.TransferRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.CsspServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
public class ServiceOrderImpl implements ServiceOrder {

    CsspServiceResponse<ServiceOrderData> csspServiceOrderData;
    ServiceOrderDAO serviceOrderDAO;

    @Autowired
    public ServiceOrderImpl(CsspServiceResponse<ServiceOrderData> csspServiceOrderData,
                                  ServiceOrderDAO serviceOrderDAO) {
        this.csspServiceOrderData = csspServiceOrderData;
        this.serviceOrderDAO = serviceOrderDAO;
    }

    public CsspServiceResponse createServiceOrder(TransferRequest transferRequest, String connectDisconnectTransfer) throws CSSPServiceException {
        try {

            this.csspServiceOrderData.setData(serviceOrderDAO.createServiceOrder(transferRequest, connectDisconnectTransfer));

        } catch (CSSPServiceException e) {
            log.error("Exception in CreateServiceOrder ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.SERVICE_ORDER_FAILED.equalsIgnoreCase(e.getErrorCode())) {
                this.csspServiceOrderData.setMessage(new Messages(CSSPConstants.SERVICE_ORDER_FAILED,
                        CSSPConstants.FAILED,CSSPConstants.SERVICE_ORDER_FAILED_MSG));
            } else {
                this.csspServiceOrderData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e1) {
            this.csspServiceOrderData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e1.getMessage()));
        }
        return csspServiceOrderData;
    }

    public CsspServiceResponse createServiceRequest(ServiceRequestBody serviceRequestBody) throws CSSPServiceException {
        CsspServiceResponse<ServiceOrderData> csspServiceOrderData = new CsspServiceResponse<>();
        try {
            if (serviceOrderDAO.createServiceRequest(serviceRequestBody)) {
                csspServiceOrderData.setMessage(new Messages(CSSPConstants.SUCCESS,
                        CSSPConstants.SUCCESS,"Service Request Created Successfully"));
            } else {
                csspServiceOrderData.setMessage(new Messages(CSSPConstants.FAILED,
                        CSSPConstants.FAILED,"Service Request Creation Failed"));
            }
        } catch (CSSPServiceException e) {
            log.error("Exception in CreateServiceRequest ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.SERVICE_ORDER_FAILED.equalsIgnoreCase(e.getErrorCode())) {
                csspServiceOrderData.setMessage(new Messages(CSSPConstants.SERVICE_ORDER_FAILED,
                        CSSPConstants.FAILED,CSSPConstants.SERVICE_ORDER_FAILED_MSG));
            } else {
                csspServiceOrderData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e1) {
            csspServiceOrderData.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e1.getMessage()));
        }
        return csspServiceOrderData;
    }

    public CsspListServiceResponse getServiceRequests() throws CSSPServiceException {
        CsspListServiceResponse<ServiceRequestData> csspListServiceResponse = new CsspListServiceResponse<>();
        try {
            csspListServiceResponse.setData(serviceOrderDAO.getServiceRequests());
        } catch (CSSPServiceException e) {
            log.error("Exception in GetServiceRequests ", e);
            if (Objects.nonNull(e.getErrorCode()) &&
                    CSSPConstants.SERVICE_ORDER_FAILED.equalsIgnoreCase(e.getErrorCode())) {
                csspListServiceResponse.setMessage(new Messages(CSSPConstants.SERVICE_ORDER_FAILED,
                        CSSPConstants.FAILED,CSSPConstants.SERVICE_ORDER_FAILED_MSG));
            } else {
                csspListServiceResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                        CSSPConstants.FAILED,e.getMessage()));
            }
        } catch (Exception e1) {
            csspListServiceResponse.setMessage(new Messages(CSSPConstants.ERROR_CODE,
                    CSSPConstants.FAILED,e1.getMessage()));
        }
        return csspListServiceResponse;
    }


}
