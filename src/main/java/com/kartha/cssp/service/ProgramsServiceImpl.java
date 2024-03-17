package com.kartha.cssp.service;

import com.kartha.cssp.dao.ContactInfoServiceDAO;
import com.kartha.cssp.dao.ProgramsServiceDAO;
import com.kartha.cssp.data.BankData;
import com.kartha.cssp.data.DigitalCommunicationData;
import com.kartha.cssp.data.ProgramsData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.request.BankDetailsRequest;
import com.kartha.cssp.request.ContactInfoRequest;
import com.kartha.cssp.request.EnrollUnenrollRequest;
import com.kartha.cssp.response.CsspListServiceResponse;
import com.kartha.cssp.response.Messages;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class ProgramsServiceImpl implements ProgramsService {

    ProgramsServiceDAO programsServiceDAO;
    ContactInfoServiceDAO contactInfoServiceDAO;

    @Autowired
    public ProgramsServiceImpl(ProgramsServiceDAO programsServiceDAO,
                               ContactInfoServiceDAO contactInfoServiceDAO) {
        this.programsServiceDAO = programsServiceDAO;
        this.contactInfoServiceDAO = contactInfoServiceDAO;
    }

    public CsspListServiceResponse updateEMB(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {
        CsspListServiceResponse<ProgramsData> csspProgramsDataList = new CsspListServiceResponse<ProgramsData>();
        try {

            csspProgramsDataList.setData(programsServiceDAO.updateEMB(enrollUnenrollRequest));

            if(Objects.nonNull(enrollUnenrollRequest.getEmail())) {
                ContactInfoRequest contactInfoRequest = new ContactInfoRequest();
                contactInfoRequest.setAccountNumber(enrollUnenrollRequest.getAccountNumber());

                List<DigitalCommunicationData> communicationDataList = new ArrayList<>();
                DigitalCommunicationData digitalCommunicationData = new DigitalCommunicationData();
                digitalCommunicationData.setCommunicationType("email_id");
                digitalCommunicationData.setCommunicateTo(enrollUnenrollRequest.getEmail());
                digitalCommunicationData.setPriority("1");
                digitalCommunicationData.setUpdatedCommTs(CommonUtils.getCurrentTimeStamp());
                communicationDataList.add(digitalCommunicationData);

                contactInfoRequest.setData(communicationDataList);

                this.contactInfoServiceDAO.updateEmail(contactInfoRequest);
            }

        } catch (Exception e) {
            log.error("Exception in EMB Enroll/DeEnroll ", e);
            csspProgramsDataList.setMessage(new Messages(CSSPConstants.UPDATE_EMB_FAILED_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.UPDATE_EMB_FAILED_ERROR_MSG));
        }
        return csspProgramsDataList;
    }

    public CsspListServiceResponse updateBudgetBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {
        CsspListServiceResponse<ProgramsData> csspProgramsDataList = new CsspListServiceResponse<ProgramsData>();
        try {

            csspProgramsDataList.setData(programsServiceDAO.updateBudgetBill(enrollUnenrollRequest));

            ContactInfoRequest contactInfoRequest = new ContactInfoRequest();
            contactInfoRequest.setAccountNumber(enrollUnenrollRequest.getAccountNumber());

            List<DigitalCommunicationData> communicationDataList = new ArrayList<>();
            DigitalCommunicationData digitalCommunicationData = new DigitalCommunicationData();
            digitalCommunicationData.setCommunicationType("email_id");
            digitalCommunicationData.setCommunicateTo(enrollUnenrollRequest.getEmail());
            digitalCommunicationData.setPriority("1");
            digitalCommunicationData.setUpdatedCommTs(CommonUtils.getCurrentTimeStamp());
            communicationDataList.add(digitalCommunicationData);

            contactInfoRequest.setData(communicationDataList);

            this.contactInfoServiceDAO.updateEmail(contactInfoRequest);

        } catch (Exception e) {
            log.error("Exception in BudgetBill Enroll/DeEnroll ", e);
            csspProgramsDataList.setMessage(new Messages(CSSPConstants.UPDATE_BB_FAILED_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.UPDATE_BB_FAILED_ERROR_MSG));
        }
        return csspProgramsDataList;
    }


    public CsspListServiceResponse updateFlatBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {
        CsspListServiceResponse<ProgramsData> csspProgramsDataList = new CsspListServiceResponse<ProgramsData>();
        try {

            csspProgramsDataList.setData(programsServiceDAO.updateFlatBill(enrollUnenrollRequest));

        } catch (Exception e) {
            log.error("Exception in FlatBill Enroll/DeEnroll ", e);
            csspProgramsDataList.setMessage(new Messages(CSSPConstants.UPDATE_FLATBILL_FAILED_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.UPDATE_FLATBILL_FAILED_ERROR_MSG));
        }
        return csspProgramsDataList;
    }

    public CsspListServiceResponse updateAutoPay(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException {
        CsspListServiceResponse<BankData> csspBankDataList = new  CsspListServiceResponse<BankData>();
        try {
            csspBankDataList.setData(programsServiceDAO.updateAutoPay(bankDetailsRequest));

        } catch (Exception e) {
            log.error("Exception in AutoPay Enroll/DeEnroll ", e);
            csspBankDataList.setMessage(new Messages(CSSPConstants.UPDATE_AUTOPAY_FAILED_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.UPDATE_AUTOPAY_FAILED_ERROR_MSG));
        }
        return csspBankDataList;
    }

    public CsspListServiceResponse updatePayNow(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException {
        CsspListServiceResponse<BankData> csspBankDataList = new  CsspListServiceResponse<BankData>();
        try {

            csspBankDataList.setData(programsServiceDAO.updatePayNow(bankDetailsRequest));

        } catch (Exception e) {
            log.error("Exception in PayNow bankchanges ", e);
            csspBankDataList.setMessage(new Messages(CSSPConstants.PAYMENT_BANK_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.PAYMENT_BANK_ERROR_MSG));
        }
        return csspBankDataList;
    }


    public CsspListServiceResponse updatePayExtend(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {
        CsspListServiceResponse<ProgramsData> csspProgramsDataList = new CsspListServiceResponse<ProgramsData>();
        try {

            csspProgramsDataList.setData(programsServiceDAO.updatePayExtend(enrollUnenrollRequest));

        } catch (Exception e) {
            log.error("Exception in Payment Extension ", e);
            csspProgramsDataList.setMessage(new Messages(CSSPConstants.UPDATE_PAYEXT_FAILED_ERROR,
                    CSSPConstants.FAILED,CSSPConstants.UPDATE_PAYEXT_FAILED_ERROR_MSG));
        }
        return csspProgramsDataList;

    }
}
