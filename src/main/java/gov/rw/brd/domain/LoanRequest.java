package gov.rw.brd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    private String hasCreditComitteeDeclined;
    private LocalDateTime creditCommitteeDenielDate;
    private String loanCommitteeType;
    private String hasLoanOfficerApproved;
    private LocalDateTime loanOfficerAprovalDate;
    private String hasLoanOfficerDeclined;
    private LocalDateTime loanOfficerDenialDate;
    private String hasRiskApproved;
    private LocalDateTime riskAprovalDate;
    private String hasRiskDenied;
    private LocalDateTime riskDenialDate;
    private String hasNotificationLetterSent;
    private LocalDateTime notificationLetterSentDate;
    private LocalDateTime requestDate;
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

    public String getHasCreditComitteeDeclined() {
        return hasCreditComitteeDeclined;
    }

    public void setHasCreditComitteeDeclined(String hasCreditComitteeDeclined) {
        this.hasCreditComitteeDeclined = hasCreditComitteeDeclined;
    }

    public LocalDateTime getCreditCommitteeDenielDate() {
        return creditCommitteeDenielDate;
    }

    public void setCreditCommitteeDenielDate(LocalDateTime creditCommitteeDenielDate) {
        this.creditCommitteeDenielDate = creditCommitteeDenielDate;
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

    public String getHasLoanOfficerDeclined() {
        return hasLoanOfficerDeclined;
    }

    public void setHasLoanOfficerDeclined(String hasLoanOfficerDeclined) {
        this.hasLoanOfficerDeclined = hasLoanOfficerDeclined;
    }

    public LocalDateTime getLoanOfficerDenialDate() {
        return loanOfficerDenialDate;
    }

    public void setLoanOfficerDenialDate(LocalDateTime loanOfficerDenialDate) {
        this.loanOfficerDenialDate = loanOfficerDenialDate;
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

    public String getHasRiskDenied() {
        return hasRiskDenied;
    }

    public void setHasRiskDenied(String hasRiskDenied) {
        this.hasRiskDenied = hasRiskDenied;
    }

    public LocalDateTime getRiskDenialDate() {
        return riskDenialDate;
    }

    public void setRiskDenialDate(LocalDateTime riskDenialDate) {
        this.riskDenialDate = riskDenialDate;
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
                ", hasCreditComitteeDeclined=" + hasCreditComitteeDeclined +
                ", creditCommitteeDenielDate=" + creditCommitteeDenielDate +
                ", loanCommitteeType='" + loanCommitteeType + '\'' +
                ", hasLoanOfficerApproved=" + hasLoanOfficerApproved +
                ", loanOfficerAprovalDate=" + loanOfficerAprovalDate +
                ", hasLoanOfficerDeclined=" + hasLoanOfficerDeclined +
                ", loanOfficerDenialDate=" + loanOfficerDenialDate +
                ", hasRiskApproved=" + hasRiskApproved +
                ", riskAprovalDate=" + riskAprovalDate +
                ", hasRiskDenied=" + hasRiskDenied +
                ", riskDenialDate=" + riskDenialDate +
                ", hasNotificationLetterSent=" + hasNotificationLetterSent +
                ", notificationLetterSentDate=" + notificationLetterSentDate +
                ", requestDate=" + requestDate +
                '}';
    }

    public String returnCurrentLevel(LoanRequest loanRequest){
        System.out.println("DATA----"+loanRequest.getHasCreditComitteeAproved());
        if(loanRequest.getHasCreditComitteeAproved()==null || loanRequest.getHasCreditComitteeAproved().equalsIgnoreCase("0")){
            return "Credit Commitee";
        }
        if(loanRequest.getHasLoanOfficerApproved()==null || loanRequest.getHasLoanOfficerApproved().equalsIgnoreCase("0")) {
            return "Loanee officer";
        }
        if(loanRequest.getHasRiskApproved()==null || loanRequest.getHasRiskApproved().equalsIgnoreCase("0")){
            return "Risk";
        }
        return "";
    }
    public String returnStatus(LoanRequest loanRequest){
        System.out.println("DATA----"+loanRequest.getHasCreditComitteeAproved());
        if(loanRequest.getHasCreditComitteeDeclined()==null){
            return "Pending";
        }
        if(loanRequest.getHasCreditComitteeDeclined().equalsIgnoreCase("true") || loanRequest.getHasRiskDenied().equalsIgnoreCase("true") || loanRequest.getHasLoanOfficerDeclined().equalsIgnoreCase("true")){
            return "true";
        }else{
            return "Pending";
        }
    }
}