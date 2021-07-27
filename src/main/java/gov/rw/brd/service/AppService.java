package gov.rw.brd.service;

import gov.rw.brd.domain.LoanRequest;
import gov.rw.brd.domain.Loanee;
import gov.rw.brd.domain.User;
import gov.rw.brd.exceptions.GlobalException;
import gov.rw.brd.repository.LoanRequestRepository;
import gov.rw.brd.repository.LoaneeRepository;
import gov.rw.brd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 4/18/2021.
 */
@Service
public class AppService {

    @Autowired
    private LoaneeRepository loaneeRepository;
    @Autowired
    private UserRepository customerUserDetailsService;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    public Loanee saveLoanRequest(Loanee loanee){
        try {
            if(loanee.getUser_id()==0){
                throw new GlobalException("error "+"user not found");
            }
            LoanRequest request = loanee.getLoanRequests().get(0);
            System.out.println(request);
            loanee.setLoanRequests(null);
            System.out.println("loonee " + loanee);
            Loanee saved = loaneeRepository.save(loanee);
            request.setLoanee(saved);
            loanRequestRepository.save(request);
        }catch (Exception e){
            throw new GlobalException("error "+e.getMessage());
        }
        return null;
    }
    public List<LoanRequest>  getAllRequests(String role,String username){

        List<LoanRequest> list =  loanRequestRepository.findAll();
        List<LoanRequest> list2 = new ArrayList<>();
        if(role.trim().equalsIgnoreCase("user")){
            for(LoanRequest l : list){
                User user = customerUserDetailsService.findById((long)l.getLoanee().getUser_id()).get();
                if(l.getLoanee().getUser_id()!=0 && user.getUsername().equalsIgnoreCase(username)){
                    list2.add(l);
                }
            }
        }else{
            for(LoanRequest l : list){
                if(l.returnCurrentLevel(l).toLowerCase().startsWith(role.toLowerCase())){
                    list2.add(l);
                }
            }
        }
        if(role.trim().equalsIgnoreCase("admin")){
            list2 = list;
        }
        System.out.println(list2);
        return list2;
    }

    public LoanRequest getRequests(String id){
        return loanRequestRepository.findById(id).orElse(null);
    }

    public LoanRequest save(LoanRequest loanRequest){
        return loanRequestRepository.save(loanRequest);
    }

    public User getUserByUserName(String userName){
        User user = customerUserDetailsService.findByUsername(userName);
       return user.getUser_id()!=null ? user :null;
    }


}
