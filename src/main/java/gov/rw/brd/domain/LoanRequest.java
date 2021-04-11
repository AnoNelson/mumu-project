package gov.rw.brd.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by hp on 4/10/2021.
 */
@Entity
@Data
public class LoanRequest {
    @Id
    private String requestId = UUID.randomUUID().toString();
    private String loaneeFullName;
    private String loaneeAdress;
    private String loaneeEmail;
    @Transient
    private MultipartFile file;
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

}