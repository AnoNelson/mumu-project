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
    private boolean hasCreditComitteeAproved;
    @JsonFormat(pattern = "DD-MM-YYYY")
    private LocalDateTime creditCommitteeAprovalDate;
    private boolean hasCreditComitteeDeclined;
    private LocalDateTime creditCommitteeDenielDate;
    private String loanCommitteeType;
    private boolean hasLoanOfficerApproved;
    private LocalDateTime loanOfficerAprovalDate;
    private boolean hasLoanOfficerDeclined;
    private LocalDateTime loanOfficerDenialDate;
    private boolean hasRiskApproved;
    private LocalDateTime riskAprovalDate;
    private boolean hasRiskDenied;
    private LocalDateTime riskDenialDate;
    private boolean hasNotificationLetterSent;
    private LocalDateTime notificationLetterSentDate;
    private LocalDateTime requestDate;
    @PrePersist
    private void beforeSave(){
        if(loanAmount.compareTo(new BigDecimal("1000000000")) <=1){
            loanCommitteeType = "Credit committee";
        }else if(loanAmount.compareTo(new BigDecimal("3000000000")) <=1){
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

    public boolean isHasCreditComitteeAproved() {
        return hasCreditComitteeAproved;
    }

    public void setHasCreditComitteeAproved(boolean hasCreditComitteeAproved) {
        this.hasCreditComitteeAproved = hasCreditComitteeAproved;
    }

    public LocalDateTime getCreditCommitteeAprovalDate() {
        return creditCommitteeAprovalDate;
    }

    public void setCreditCommitteeAprovalDate(LocalDateTime creditCommitteeAprovalDate) {
        this.creditCommitteeAprovalDate = creditCommitteeAprovalDate;
    }

    public boolean isHasCreditComitteeDeclined() {
        return hasCreditComitteeDeclined;
    }

    public void setHasCreditComitteeDeclined(boolean hasCreditComitteeDeclined) {
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

    public boolean isHasLoanOfficerApproved() {
        return hasLoanOfficerApproved;
    }

    public void setHasLoanOfficerApproved(boolean hasLoanOfficerApproved) {
        this.hasLoanOfficerApproved = hasLoanOfficerApproved;
    }

    public LocalDateTime getLoanOfficerAprovalDate() {
        return loanOfficerAprovalDate;
    }

    public void setLoanOfficerAprovalDate(LocalDateTime loanOfficerAprovalDate) {
        this.loanOfficerAprovalDate = loanOfficerAprovalDate;
    }

    public boolean isHasLoanOfficerDeclined() {
        return hasLoanOfficerDeclined;
    }

    public void setHasLoanOfficerDeclined(boolean hasLoanOfficerDeclined) {
        this.hasLoanOfficerDeclined = hasLoanOfficerDeclined;
    }

    public LocalDateTime getLoanOfficerDenialDate() {
        return loanOfficerDenialDate;
    }

    public void setLoanOfficerDenialDate(LocalDateTime loanOfficerDenialDate) {
        this.loanOfficerDenialDate = loanOfficerDenialDate;
    }

    public boolean isHasRiskApproved() {
        return hasRiskApproved;
    }

    public void setHasRiskApproved(boolean hasRiskApproved) {
        this.hasRiskApproved = hasRiskApproved;
    }

    public LocalDateTime getRiskAprovalDate() {
        return riskAprovalDate;
    }

    public void setRiskAprovalDate(LocalDateTime riskAprovalDate) {
        this.riskAprovalDate = riskAprovalDate;
    }

    public boolean isHasRiskDenied() {
        return hasRiskDenied;
    }

    public void setHasRiskDenied(boolean hasRiskDenied) {
        this.hasRiskDenied = hasRiskDenied;
    }

    public LocalDateTime getRiskDenialDate() {
        return riskDenialDate;
    }

    public void setRiskDenialDate(LocalDateTime riskDenialDate) {
        this.riskDenialDate = riskDenialDate;
    }

    public boolean isHasNotificationLetterSent() {
        return hasNotificationLetterSent;
    }

    public void setHasNotificationLetterSent(boolean hasNotificationLetterSent) {
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

    @Override
    public String toString() {
        return "LoanRequest{" +
                "requestId='" + requestId + '\'' +
                ", loanee=" + loanee +
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
}