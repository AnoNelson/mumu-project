package gov.rw.brd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by hp on 4/10/2021.
 */
@Entity
public class LoanRequest {
    @Id
    private String requestId = UUID.randomUUID().toString();
    @OneToOne
    @JoinColumn(name = "loanee_id", referencedColumnName = "loaneeId")
    private Loanee loanee;
    @Transient
    private MultipartFile requestLetter;
    private String requestLetterName;
    @Transient
    private MultipartFile businessPlan;
    private String businessPlanName;
    @Transient
    private MultipartFile bankStatement;
    private String bankStatementName;
    @Transient
    private MultipartFile landDocuments;
    private String landDocumentsName;
    private BigDecimal loanAmount;
    private String hasCreditComitteeAproved;
    @JsonFormat(pattern = "DD-MM-YYYY")
    private LocalDateTime creditCommitteeAprovalDate;
    private String loanCommitteeType;
    private String hasLoanOfficerApproved;
    private LocalDateTime loanOfficerAprovalDate;
    private String hasRiskApproved;
    private LocalDateTime riskAprovalDate;
    private String hasLegalApproved;
    private LocalDateTime legalAprovalDate;
    private String hasNotificationLetterSent;
    private LocalDateTime notificationLetterSentDate;
    private LocalDateTime requestDate;
    private String declineReason;
    private String status;
    @PrePersist
    private void beforeSave(){
        if(loanAmount.compareTo(new BigDecimal("1000000000")) <=0){
            loanCommitteeType = "Credit committee";
        }else if(loanAmount.compareTo(new BigDecimal("3000000000")) <=0){
            loanCommitteeType = "Board Credit committee";
        }else{
            loanCommitteeType = "Full board Credit committee";
        }
        requestDate =LocalDateTime.now();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Loanee getLoanee() {
        return loanee;
    }

    public void setLoanee(Loanee loanee) {
        this.loanee = loanee;
    }

    public MultipartFile getRequestLetter() {
        return requestLetter;
    }

    public void setRequestLetter(MultipartFile requestLetter) {
        this.requestLetter = requestLetter;
    }

    public String getRequestLetterName() {
        return requestLetterName;
    }

    public void setRequestLetterName(String requestLetterName) {
        this.requestLetterName = requestLetterName;
    }

    public MultipartFile getBusinessPlan() {
        return businessPlan;
    }

    public void setBusinessPlan(MultipartFile businessPlan) {
        this.businessPlan = businessPlan;
    }

    public String getBusinessPlanName() {
        return businessPlanName;
    }

    public void setBusinessPlanName(String businessPlanName) {
        this.businessPlanName = businessPlanName;
    }

    public MultipartFile getBankStatement() {
        return bankStatement;
    }

    public void setBankStatement(MultipartFile bankStatement) {
        this.bankStatement = bankStatement;
    }

    public String getBankStatementName() {
        return bankStatementName;
    }

    public void setBankStatementName(String bankStatementName) {
        this.bankStatementName = bankStatementName;
    }

    public MultipartFile getLandDocuments() {
        return landDocuments;
    }

    public void setLandDocuments(MultipartFile landDocuments) {
        this.landDocuments = landDocuments;
    }

    public String getLandDocumentsName() {
        return landDocumentsName;
    }

    public void setLandDocumentsName(String landDocumentsName) {
        this.landDocumentsName = landDocumentsName;
    }

    public BigDecimal getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(BigDecimal loanAmount) {
        this.loanAmount = loanAmount;
    }

    public String getHasCreditComitteeAproved() {
        return hasCreditComitteeAproved;
    }

    public void setHasCreditComitteeAproved(String hasCreditComitteeAproved) {
        this.hasCreditComitteeAproved = hasCreditComitteeAproved;
    }

    public LocalDateTime getCreditCommitteeAprovalDate() {
        return creditCommitteeAprovalDate;
    }

    public void setCreditCommitteeAprovalDate(LocalDateTime creditCommitteeAprovalDate) {
        this.creditCommitteeAprovalDate = creditCommitteeAprovalDate;
    }


    public String getLoanCommitteeType() {
        return loanCommitteeType;
    }

    public void setLoanCommitteeType(String loanCommitteeType) {
        this.loanCommitteeType = loanCommitteeType;
    }

    public String getHasLoanOfficerApproved() {
        return hasLoanOfficerApproved;
    }

    public void setHasLoanOfficerApproved(String hasLoanOfficerApproved) {
        this.hasLoanOfficerApproved = hasLoanOfficerApproved;
    }

    public LocalDateTime getLoanOfficerAprovalDate() {
        return loanOfficerAprovalDate;
    }

    public void setLoanOfficerAprovalDate(LocalDateTime loanOfficerAprovalDate) {
        this.loanOfficerAprovalDate = loanOfficerAprovalDate;
    }


    public String getHasRiskApproved() {
        return hasRiskApproved;
    }

    public void setHasRiskApproved(String hasRiskApproved) {
        this.hasRiskApproved = hasRiskApproved;
    }

    public LocalDateTime getRiskAprovalDate() {
        return riskAprovalDate;
    }

    public void setRiskAprovalDate(LocalDateTime riskAprovalDate) {
        this.riskAprovalDate = riskAprovalDate;
    }

    public String getHasNotificationLetterSent() {
        return hasNotificationLetterSent;
    }

    public void setHasNotificationLetterSent(String hasNotificationLetterSent) {
        this.hasNotificationLetterSent = hasNotificationLetterSent;
    }

    public LocalDateTime getNotificationLetterSentDate() {
        return notificationLetterSentDate;
    }

    public void setNotificationLetterSentDate(LocalDateTime notificationLetterSentDate) {
        this.notificationLetterSentDate = notificationLetterSentDate;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHasLegalApproved() {
        return hasLegalApproved;
    }

    public void setHasLegalApproved(String hasLegalApproved) {
        this.hasLegalApproved = hasLegalApproved;
    }

    public LocalDateTime getLegalAprovalDate() {
        return legalAprovalDate;
    }

    public void setLegalAprovalDate(LocalDateTime legalAprovalDate) {
        this.legalAprovalDate = legalAprovalDate;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "requestId='" + requestId + '\'' +
                ", requestLetter=" + requestLetter +
                ", requestLetterName='" + requestLetterName + '\'' +
                ", businessPlan=" + businessPlan +
                ", businessPlanName='" + businessPlanName + '\'' +
                ", bankStatement=" + bankStatement +
                ", bankStatementName='" + bankStatementName + '\'' +
                ", landDocuments=" + landDocuments +
                ", landDocumentsName='" + landDocumentsName + '\'' +
                ", loanAmount=" + loanAmount +
                ", hasCreditComitteeAproved=" + hasCreditComitteeAproved +
                ", creditCommitteeAprovalDate=" + creditCommitteeAprovalDate +
                ", loanCommitteeType='" + loanCommitteeType + '\'' +
                ", hasLoanOfficerApproved=" + hasLoanOfficerApproved +
                ", loanOfficerAprovalDate=" + loanOfficerAprovalDate +
                ", hasRiskApproved=" + hasRiskApproved +
                ", riskAprovalDate=" + riskAprovalDate +
                ", hasNotificationLetterSent=" + hasNotificationLetterSent +
                ", notificationLetterSentDate=" + notificationLetterSentDate +
                ", requestDate=" + requestDate +
                '}';
    }


    public String returnCurrentLevel(LoanRequest loanRequest){
//        System.out.println("DATA----"+loanRequest.getHasCreditComitteeAproved());
        if(loanRequest.getHasCreditComitteeAproved()==null || loanRequest.getHasCreditComitteeAproved().equalsIgnoreCase("0")){
            return "Credit Commitee";
        }
        if(loanRequest.getHasLoanOfficerApproved()==null || loanRequest.getHasLoanOfficerApproved().equalsIgnoreCase("0")) {
            return "officer of loan";
        }
        if(loanRequest.getHasRiskApproved()==null || loanRequest.getHasRiskApproved().equalsIgnoreCase("0")){
            return "Risk";
        }
        if(loanRequest.getHasLegalApproved()==null || loanRequest.getHasLegalApproved().equalsIgnoreCase("0")){
            return "legal officer";
        }
        return "";
    }
//    public String returnStatus(LoanRequest loanRequest){
//        System.out.println(" DATA----"+loanRequest.getHasCreditComitteeAproved()+" DATA loan officer----"+loanRequest.getHasLoanOfficerApproved()+" DATA risk----"+loanRequest.getHasRiskApproved()+" DATA legal----"+loanRequest.getHasLegalApproved());
//        if(loanRequest.getHasCreditComitteeAproved()==null || loanRequest.getHasRiskApproved()==null || loanRequest.getHasLoanOfficerApproved()==null || loanRequest.getHasLegalApproved()==null){
//            return "Pending";
//        }
//        if(loanRequest.getHasCreditComitteeAproved().equalsIgnoreCase("D") || loanRequest.getHasRiskApproved().equalsIgnoreCase("D") || loanRequest.getHasLoanOfficerApproved().equalsIgnoreCase("D") || loanRequest.getHasLegalApproved().equalsIgnoreCase("D")){
//            return "Declined";
//        }else if(loanRequest.getHasCreditComitteeAproved().equalsIgnoreCase("A") && loanRequest.getHasRiskApproved().equalsIgnoreCase("A") && loanRequest.getHasLoanOfficerApproved().equalsIgnoreCase("A") && loanRequest.getHasLegalApproved().equalsIgnoreCase("A")){
//            return "Approved";
//        }else{
//            return "Pending";
//        }
//    }
public String returnStatus(LoanRequest loanRequest){
    System.out.println(" DATA----"+loanRequest.getHasCreditComitteeAproved()+" DATA loan officer----"+loanRequest.getHasLoanOfficerApproved()+" DATA risk----"+loanRequest.getHasRiskApproved()+" DATA legal----"+loanRequest.getHasLegalApproved());
    if(loanRequest.getStatus()==null){
        return "Pending";
    }
    if(loanRequest.getStatus().equalsIgnoreCase("D")){
        return "Declined";
    }else if(loanRequest.getStatus().equalsIgnoreCase("A")){
        return "Approved";
    }else{
        return "Pending";
    }
}
    public List<LoanRequest> getApproved(List<LoanRequest> list){
        List<LoanRequest> retu = new ArrayList<>();
        list.forEach((request) -> {
            if (request.getStatus()!=null && request.getStatus().equalsIgnoreCase("A")) {
                retu.add(request);
            }
        });
        return  retu;
    }
    public List<LoanRequest> getPending(List<LoanRequest> list){
        List<LoanRequest> retu = new ArrayList<>();
        list.forEach((request) -> {
            if (request.returnStatus(request).equalsIgnoreCase("Pending")) {
                retu.add(request);
            }
        });
        return  retu;
    }
    public List<LoanRequest> getDeclined(List<LoanRequest> list){
        List<LoanRequest> retu = new ArrayList<>();
        list.forEach((request) -> {
            if (request.getStatus()!=null && request.getStatus().equalsIgnoreCase("D")) {
                retu.add(request);
            }
        });
        return  retu;
    }

}