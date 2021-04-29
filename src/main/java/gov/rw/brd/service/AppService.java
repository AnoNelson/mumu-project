package gov.rw.brd.service;

import gov.rw.brd.domain.LoanRequest;
import gov.rw.brd.domain.Loanee;
import gov.rw.brd.repository.LoanRequestRepository;
import gov.rw.brd.repository.LoaneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hp on 4/18/2021.
 */
@Service
public class AppService {

    @Autowired
    private LoaneeRepository loaneeRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    public Loanee saveLoanRequest(Loanee loanee){
        LoanRequest request = loanee.getLoanRequest();
        System.out.println(request);
//        Loanee result = loaneeRepository.save(loanee);
//        request.setLoanee(result);
        loaneeRepository.save(loanee);
        request.setLoanee(loanee);
        loanRequestRepository.save(request);
        return null;
    }
}
