package gov.rw.brd.service;

import gov.rw.brd.domain.LoanRequest;
import gov.rw.brd.domain.Loanee;
import gov.rw.brd.exceptions.GlobalException;
import gov.rw.brd.repository.LoanRequestRepository;
import gov.rw.brd.repository.LoaneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        try {
            LoanRequest request = loanee.getLoanRequest();
            System.out.println(request);
            loanee.setLoanRequest(null);
            System.out.println("loonee " + loanee);
            Loanee saved = loaneeRepository.save(loanee);
            request.setLoanee(saved);
            loanRequestRepository.save(request);
        }catch (Exception e){
            throw new GlobalException("error "+e.getMessage());
        }
        return null;
    }
    public List<LoanRequest> getAllRequests(){
        return loanRequestRepository.findAll();
    }

    public LoanRequest getRequests(String id){
        return loanRequestRepository.findById(id).orElse(null);
    }
}
