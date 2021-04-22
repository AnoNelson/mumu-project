package gov.rw.brd.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by hp on 4/17/2021.
 */
@Entity
@Data
public class Loanee {
    @Id
    private String loaneeId = UUID.randomUUID().toString();
    private String loaneeFullName;
    private String loaneeAdress;
    private String loaneeEmail;
    private String phoneNumber;
    private LocalDateTime createdDate;
    @OneToOne(mappedBy = "loanee",cascade = CascadeType.ALL)
    private LoanRequest loanRequest;

    @PrePersist
    private void onCreate(){
        createdDate = LocalDateTime.now();
    }
}
