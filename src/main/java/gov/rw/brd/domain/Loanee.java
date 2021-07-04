package gov.rw.brd.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by hp on 4/17/2021.
 */
@Entity

public class Loanee {
    @Id
    private String loaneeId = UUID.randomUUID().toString();
    private String loaneeFullName;
    private String loaneeAdress;
    private String loaneeEmail;
    private String phoneNumber;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDateTime createdDate;
    @OneToOne(mappedBy = "loanee",cascade = CascadeType.ALL)
    private LoanRequest loanRequest;
    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "user_id")
    private User user;

    @PrePersist
    private void onCreate(){
        createdDate = LocalDateTime.now();
    }

    public String getLoaneeId() {
        return loaneeId;
    }

    public void setLoaneeId(String loaneeId) {
        this.loaneeId = loaneeId;
    }

    public String getLoaneeFullName() {
        return loaneeFullName;
    }

    public void setLoaneeFullName(String loaneeFullName) {
        this.loaneeFullName = loaneeFullName;
    }

    public String getLoaneeAdress() {
        return loaneeAdress;
    }

    public void setLoaneeAdress(String loaneeAdress) {
        this.loaneeAdress = loaneeAdress;
    }

    public String getLoaneeEmail() {
        return loaneeEmail;
    }

    public void setLoaneeEmail(String loaneeEmail) {
        this.loaneeEmail = loaneeEmail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LoanRequest getLoanRequest() {
        return loanRequest;
    }

    public void setLoanRequest(LoanRequest loanRequest) {
        this.loanRequest = loanRequest;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Loanee{" +
                "loaneeId='" + loaneeId + '\'' +
                ", loaneeFullName='" + loaneeFullName + '\'' +
                ", loaneeAdress='" + loaneeAdress + '\'' +
                ", loaneeEmail='" + loaneeEmail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdDate=" + createdDate +
                ", loanRequest=" + loanRequest +
                ", gender=" + gender +
                '}';
    }
}
