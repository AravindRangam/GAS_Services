package com.kartha.cssp.dao;

import com.kartha.cssp.data.BankData;
import com.kartha.cssp.data.DigitalCommunicationData;
import com.kartha.cssp.data.ProgramsData;
import com.kartha.cssp.exception.CSSPServiceException;
import com.kartha.cssp.model.BankDetailsInfo;
import com.kartha.cssp.model.ProgramsModel;
import com.kartha.cssp.request.BankDetailsRequest;
import com.kartha.cssp.request.ContactInfoRequest;
import com.kartha.cssp.request.EnrollUnenrollRequest;
import com.kartha.cssp.utils.CSSPConstants;
import com.kartha.cssp.utils.CommonUtils;
import com.kartha.cssp.utils.EmailServiceUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Repository
public class ProgramsServiceDAOImpl implements ProgramsServiceDAO {

    MongoTemplate mongodbTemplate;
    private String enrollFlag = "Y";
    private String unenrollFlag = "N";
    private String budgetBill_literal = "BUDGETBILL";
    private String flatBill_literal = "FLATBILL";
    private String emb_literal = "EMAILBILL";
    private String autopay_literal = "AUTOPAY";
    private String payNow_literal = "PAYNOW";
    private String paymentExtension_literal = "PAYMENTARRANGEMENT";
    AccountSummaryServiceDAO accountSummaryServiceDAO;
    EmailServiceUtil emailServiceUtil;

     @Autowired
    public ProgramsServiceDAOImpl(MongoTemplate mongodbTemplate,
                                  AccountSummaryServiceDAO accountSummaryServiceDAO,
                                  EmailServiceUtil emailServiceUtil) {
        this.mongodbTemplate = mongodbTemplate;
        this.accountSummaryServiceDAO = accountSummaryServiceDAO;
        this.emailServiceUtil = emailServiceUtil;
    }

    public List<ProgramsData> updateEMB(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(enrollUnenrollRequest.getAccountNumber()));

        //Program Info
        List<ProgramsModel> programsList = this.mongodbTemplate.find(query, ProgramsModel.class);

        ProgramsModel programsModel = programsList.get(0);

        if (Objects.nonNull(programsModel)) {
            List<ProgramsData> programsDataList = programsModel.getProgramInfo();
            if (Objects.nonNull(programsDataList)) {
                programsDataList.stream().filter(Objects::nonNull).filter(programInfo -> emb_literal.equalsIgnoreCase(programInfo.getProgramType())).forEach(programInfo -> {
                    /*if (programInfo.getEnrolled().equalsIgnoreCase(enrollUnenrollRequest.getEnrollUnenrollFlag())) {
                        throw new CSSPServiceException("account.Enrollment.Status", "is the same.");
                    } else {*/
                        if (enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(enrollFlag) ||
                                enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(unenrollFlag)) {
                            programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                            programInfo.setEnrolled(enrollUnenrollRequest.getEnrollUnenrollFlag().toUpperCase());
                        } else {
                            throw new CSSPServiceException("account.Enrollment.Status", "Invalid Value.");
                        }
                    //}
                });
            }
            programsModel = this.mongodbTemplate.save(programsModel);

            try {
                emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.getAccountSummaryLite(enrollUnenrollRequest.getAccountNumber()), CSSPConstants.EMAIL_ENROLL_EMB);
            } catch (Exception e) {
                e.printStackTrace();
            }



        } else {
            throw new CSSPServiceException("account.not.found");
        }
        return programsModel.getProgramInfo();
    }

    public List<ProgramsData> updateBudgetBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(enrollUnenrollRequest.getAccountNumber()));

        //Program Info
        List<ProgramsModel> programsList = this.mongodbTemplate.find(query, ProgramsModel.class);

        ProgramsModel programsModel = programsList.get(0);

        if (Objects.nonNull(programsModel)) {
            List<ProgramsData> programsDataList = programsModel.getProgramInfo();
            if (Objects.nonNull(programsDataList)) {
                programsDataList.stream().filter(Objects::nonNull).
                        forEach(programInfo -> {

                        if(budgetBill_literal.equalsIgnoreCase(programInfo.getProgramType())) {
                            if (enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(enrollFlag) ||
                                    enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(unenrollFlag)) {
                                programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                                programInfo.setEnrolled(enrollUnenrollRequest.getEnrollUnenrollFlag().toUpperCase());
                            } else {
                                throw new CSSPServiceException("account.Enrollment.Status", "Invalid Value.");
                            }
                        }

                        if(emb_literal.equalsIgnoreCase(programInfo.getProgramType())) {
                            if(enrollUnenrollRequest.isEbillEnrolled()) {
                                programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                                programInfo.setEnrolled(enrollUnenrollRequest.getEnrollUnenrollFlag().toUpperCase());
                            }
                        }

                });
            }
            programsModel = this.mongodbTemplate.save(programsModel);

            try {
                emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.getAccountSummaryLite(enrollUnenrollRequest.getAccountNumber()), CSSPConstants.EMAIL_ENROLL_BB);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new CSSPServiceException("account.not.found");
        }
        return programsModel.getProgramInfo();
    }


    public List<ProgramsData> updateFlatBill(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {
        ProgramsModel programsModel = null;
        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(enrollUnenrollRequest.getAccountNumber()));
        //Program Info
        List<ProgramsModel> programsList = this.mongodbTemplate.find(query, ProgramsModel.class);

        if (Objects.nonNull(programsList)) {
            programsModel = programsList.get(0);

            List<ProgramsData> programsDataList = programsModel.getProgramInfo();
            if (Objects.nonNull(programsDataList)) {
                programsDataList.stream().filter(Objects::nonNull).filter(programInfo ->
                        flatBill_literal.equalsIgnoreCase(programInfo.getProgramType())).forEach(programInfo -> {
                    if (!enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(enrollFlag) &&
                            !enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(unenrollFlag)) {
                        throw new CSSPServiceException("account.Enrollment.Status", "Invalid Value.");
                    } else {
                        programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                        programInfo.setEnrolled(enrollUnenrollRequest.getEnrollUnenrollFlag().toUpperCase());
                    }
                });
            }
            programsModel = this.mongodbTemplate.save(programsModel);

            try {
                emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.getAccountSummaryLite(enrollUnenrollRequest.getAccountNumber()), CSSPConstants.EMAIL_ENROLL_FLAT_BILL);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new CSSPServiceException("account.not.found");
        }

        return programsModel.getProgramInfo();
    }


    public List<BankData> updateAutoPay(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(bankDetailsRequest.getAccountNumber()));
        //Bank Info

        List<BankDetailsInfo> bankDetailsInfoList = this.mongodbTemplate.find(query, BankDetailsInfo.class);

        if (!bankDetailsInfoList.isEmpty()) {
            BankDetailsInfo bankDetailsInfo = bankDetailsInfoList.get(0);

            if (Objects.nonNull(bankDetailsInfo)) {

                List<BankData> bankDataList = bankDetailsInfo.getBank_info();

                if (Objects.nonNull(bankDataList)) {
                    bankDataList.stream().filter(Objects::nonNull).filter(bankData -> autopay_literal.equalsIgnoreCase(bankData.getPaymentType())).forEach(bankData -> {
                        bankData.setBankAccountDescription(bankDetailsRequest.getData().getBankAccountDescription());
                        bankData.setAccountHolderFirstName(bankDetailsRequest.getData().getAccountHolderFirstName());
                        bankData.setAccountHolderLastName(bankDetailsRequest.getData().getAccountHolderLastName());
                        bankData.setAccountType(bankDetailsRequest.getData().getAccountType());
                        bankData.setBankAccountNo(bankDetailsRequest.getData().getBankAccountNo());
                        bankData.setRoutingTransitNo(bankDetailsRequest.getData().getRoutingTransitNo());
                        bankData.setCheckingOrSavings(bankDetailsRequest.getData().getCheckingOrSavings());
                        bankData.setPayBeforeDueDate(bankDetailsRequest.getData().getPayBeforeDueDate());
                        if (!bankDetailsRequest.getEnrollUnenrollFlag().isEmpty()) {
                            if (!bankDetailsRequest.getEnrollUnenrollFlag().equalsIgnoreCase(enrollFlag) && !bankDetailsRequest.getEnrollUnenrollFlag().equalsIgnoreCase(unenrollFlag)) {
                                throw new CSSPServiceException("account.Enrollment.Status", "Invalid Value.");
                            } else {
                                Query programsQuery = new Query();
                                programsQuery.addCriteria(Criteria.where("accountNumber").is(bankDetailsRequest.getAccountNumber()));

                                //Program Info
                                List<ProgramsModel> programsList = this.mongodbTemplate.find(query, ProgramsModel.class);
                                ProgramsModel programsModel = programsList.get(0);
                                if (Objects.nonNull(programsModel)) {
                                    List<ProgramsData> programsDataList = programsModel.getProgramInfo();
                                    if (Objects.nonNull(programsDataList)) {
                                        programsDataList.stream().filter(Objects::nonNull).filter(programInfo -> autopay_literal.equalsIgnoreCase(programInfo.getProgramType())).forEach(programInfo -> {
                                            programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                                            programInfo.setEnrolled(bankDetailsRequest.getEnrollUnenrollFlag().toUpperCase());
                                        });
                                    }
                                    programsModel = this.mongodbTemplate.save(programsModel);

                                    try {
                                        emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.getAccountSummaryLite(bankDetailsRequest.getAccountNumber()), CSSPConstants.EMAIL_ENROLL_AUTO_PAY);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    throw new CSSPServiceException("account.not.found.in.Program.Table");
                                }

                            }
                        }
                    });
                } else {
                    throw new CSSPServiceException("bank_info.Table not consistent found");
                }
                bankDetailsInfo = this.mongodbTemplate.save(bankDetailsInfo);
            } else {
                throw new CSSPServiceException("bank_info.account.not.found");
            }
            return bankDetailsInfo.getBank_info();
        } else {
            throw new CSSPServiceException("account.not.found");
        }
    }

    public List<BankData> updatePayNow(BankDetailsRequest bankDetailsRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(bankDetailsRequest.getAccountNumber()));
        //Bank Info

        List<BankDetailsInfo> bankDetailsInfoList = this.mongodbTemplate.find(query, BankDetailsInfo.class);

        if (!bankDetailsInfoList.isEmpty()) {
            BankDetailsInfo bankDetailsInfo = bankDetailsInfoList.get(0);

            if (Objects.nonNull(bankDetailsInfo)) {

                List<BankData> bankDataList = bankDetailsInfo.getBank_info();

                if (Objects.nonNull(bankDataList)) {
                    bankDataList.stream().filter(Objects::nonNull).filter(bankData -> payNow_literal.equalsIgnoreCase(bankData.getPaymentType())).forEach(bankData -> {
                        bankData.setBankAccountDescription(bankDetailsRequest.getData().getBankAccountDescription());
                        bankData.setAccountHolderFirstName(bankDetailsRequest.getData().getAccountHolderFirstName());
                        bankData.setAccountHolderLastName(bankDetailsRequest.getData().getAccountHolderLastName());
                        bankData.setAccountType(bankDetailsRequest.getData().getAccountType());
                        bankData.setBankAccountNo(bankDetailsRequest.getData().getBankAccountNo());
                        bankData.setRoutingTransitNo(bankDetailsRequest.getData().getRoutingTransitNo());
                        bankData.setCheckingOrSavings(bankDetailsRequest.getData().getCheckingOrSavings());
                        bankData.setPayBeforeDueDate(bankDetailsRequest.getData().getPayBeforeDueDate());

                        if (!bankDetailsRequest.getEnrollUnenrollFlag().isEmpty()) {
                            if (!bankDetailsRequest.getEnrollUnenrollFlag().equalsIgnoreCase(enrollFlag) && !bankDetailsRequest.getEnrollUnenrollFlag().equalsIgnoreCase(unenrollFlag)) {
                                throw new CSSPServiceException("account.Enrollment.Status", "Invalid Value.");
                            } else {
                                Query programsQuery = new Query();
                                programsQuery.addCriteria(Criteria.where("accountNumber").is(bankDetailsRequest.getAccountNumber()));

                                //Program Info
                                List<ProgramsModel> programsList = this.mongodbTemplate.find(query, ProgramsModel.class);
                                ProgramsModel programsModel = programsList.get(0);
                                if (Objects.nonNull(programsModel)) {
                                    List<ProgramsData> programsDataList = programsModel.getProgramInfo();
                                    if (Objects.nonNull(programsDataList)) {
                                        programsDataList.stream().filter(Objects::nonNull).filter(programInfo -> payNow_literal.equalsIgnoreCase(programInfo.getProgramType())).forEach(programInfo -> {
                                            programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                                            programInfo.setEnrolled(bankDetailsRequest.getEnrollUnenrollFlag().toUpperCase());
                                        });
                                    }
                                    programsModel = this.mongodbTemplate.save(programsModel);
                                    try {
                                        emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.getAccountSummaryLite(bankDetailsRequest.getAccountNumber()), CSSPConstants.EMAIL_ENROLL_ONLINE_PAYMENT);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }

                                } else {
                                    throw new CSSPServiceException("account.not.found.in.Program.Table");
                                }

                            }
                        }
                    });
                } else {
                    throw new CSSPServiceException("bank_info.Table not consistent found");
                }
                bankDetailsInfo = this.mongodbTemplate.save(bankDetailsInfo);
            } else {
                throw new CSSPServiceException("bank_info.account.not.found");
            }
            return bankDetailsInfo.getBank_info();
        } else {
            throw new CSSPServiceException("account.not.found");
        }
    }


    public List<ProgramsData> updatePayExtend(EnrollUnenrollRequest enrollUnenrollRequest) throws CSSPServiceException {

        Query query = new Query();
        query.addCriteria(Criteria.where("accountNumber").is(enrollUnenrollRequest.getAccountNumber()));

        //Program Info
        List<ProgramsModel> programsList = this.mongodbTemplate.find(query, ProgramsModel.class);

        ProgramsModel programsModel = programsList.get(0);

        if (Objects.nonNull(programsModel)) {
            List<ProgramsData> programsDataList = programsModel.getProgramInfo();
            if (Objects.nonNull(programsDataList)) {
                programsDataList.stream().filter(Objects::nonNull).
                        forEach(programInfo -> {

                            if(paymentExtension_literal.equalsIgnoreCase(programInfo.getProgramType())) {
                                if (enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(enrollFlag) ||
                                        enrollUnenrollRequest.getEnrollUnenrollFlag().equalsIgnoreCase(unenrollFlag)) {
                                    programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                                    programInfo.setEnrolled(enrollUnenrollRequest.getEnrollUnenrollFlag().toUpperCase());
                                } else {
                                    throw new CSSPServiceException("account.Enrollment.Status", "Invalid Value.");
                                }
                            }

                            if(paymentExtension_literal.equalsIgnoreCase(programInfo.getProgramType())) {
                                if(enrollUnenrollRequest.isEbillEnrolled()) {
                                    programInfo.setUpdatedTs(CommonUtils.getCurrentTimeStamp());
                                    programInfo.setEnrolled(enrollUnenrollRequest.getEnrollUnenrollFlag().toUpperCase());
                                }
                            }

                        });
            }
            programsModel = this.mongodbTemplate.save(programsModel);

            try {
                emailServiceUtil.sendEmailRequest(accountSummaryServiceDAO.getAccountSummaryLite(enrollUnenrollRequest.getAccountNumber()), CSSPConstants.EMAIL_ENROLL_PAY_EXTEND);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            throw new CSSPServiceException("account.not.found");
        }
        return programsModel.getProgramInfo();
    }
}