package gov.rw.brd.controller;

import gov.rw.brd.domain.LoanRequest;
import gov.rw.brd.domain.Loanee;
import gov.rw.brd.domain.User;
import gov.rw.brd.repository.LoanRequestRepository;
import gov.rw.brd.repository.LoaneeRepository;
import gov.rw.brd.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

/**
 * Created by hp on 4/17/2021.
 */
@Controller
public class LoanRequestController {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private AppService service;

    private String UPLOAD_DIR = "C:\\xampp\\htdocs\\loanAppFiles\\";

    @RequestMapping("/request-loan")
    public String getAllUsers (Model model) {
        Loanee loanee = new Loanee();
        LoanRequest loanRequest = new LoanRequest();
        model.addAttribute("loanee",loanee);
        model.addAttribute("loanRequest",loanRequest);
        return "loan-request";
    }

    @RequestMapping(value = "/save-loanee", method = RequestMethod.POST)
    public String addAccount(@Valid Loanee loanee, HttpServletRequest request, RedirectAttributes redirAttrs) {
        try {
            HttpSession session = request.getSession(false);
            String username = (String) session.getAttribute("username");
            UPLOAD_DIR = UPLOAD_DIR;
            ModelAndView view = new ModelAndView();
            view.addObject("loanee", loanee);
            String fileNm = StringUtils.cleanPath(loanee.getLoanRequest().getRequestLetter().getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + fileNm);
            Files.copy(loanee.getLoanRequest().getRequestLetter().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            loanee.getLoanRequest().setRequestLetterName(username + "\\" + fileNm);
            System.out.println(loanee);
                String fileNm2 = StringUtils.cleanPath(loanee.getLoanRequest().getBusinessPlan().getOriginalFilename()) +UUID.randomUUID().toString();
                Path path2 = Paths.get(UPLOAD_DIR + fileNm2);
                Files.copy(loanee.getLoanRequest().getBusinessPlan().getInputStream(), path2, StandardCopyOption.REPLACE_EXISTING);
                loanee.getLoanRequest().setBusinessPlanName(username + "\\" + fileNm2);

                String fileNm3 = StringUtils.cleanPath(loanee.getLoanRequest().getBankStatement().getOriginalFilename()) +UUID.randomUUID().toString();
                Path path3 = Paths.get(UPLOAD_DIR + fileNm3);
                Files.copy(loanee.getLoanRequest().getBankStatement().getInputStream(), path3, StandardCopyOption.REPLACE_EXISTING);
                loanee.getLoanRequest().setBankStatementName(username + "\\" + fileNm3);

                String fileNm4 = StringUtils.cleanPath(loanee.getLoanRequest().getLandDocuments().getOriginalFilename()) +UUID.randomUUID().toString();
                Path path4 = Paths.get(UPLOAD_DIR + fileNm4);
                Files.copy(loanee.getLoanRequest().getLandDocuments().getInputStream(), path4, StandardCopyOption.REPLACE_EXISTING);
                loanee.getLoanRequest().setLandDocumentsName(username + "\\" + fileNm4);
            redirAttrs.addFlashAttribute("success", "file successfully added");
            service.saveLoanRequest(loanee);

            return "redirect:/request-loan";
        } catch (Exception e) {
            // TODO Auto-generated catch block
            redirAttrs.addFlashAttribute("error", "Error: " + e.getMessage());
            return "redirect:/request-loan";
        }
    }

    @RequestMapping("/view-requests")
    public String returnLoaneeRequest (Model model) {
        List<LoanRequest> list = service.getAllRequests();
        model.addAttribute("requests",list);
        return "account-view";
    }
    @RequestMapping("/view-docs/{name}")
    public String viewDocuments (@PathVariable(name = "name") String name,Model model) {
        System.out.println("file name is "+name);
        LoanRequest loanRequest = service.getRequests(name);

        model.addAttribute("request",loanRequest);
        model.addAttribute("currentLevel",loanRequest);
        return "account-view";
    }

    public String returnCurrentLevel(LoanRequest loanRequest){
        System.out.println("DATA----"+loanRequest.getHasCreditComitteeAproved());
        if(loanRequest.getHasCreditComitteeAproved()==null || loanRequest.getHasCreditComitteeAproved().equalsIgnoreCase("false")){
          return "Credit Commitee";
        }
        if(loanRequest.getHasLoanOfficerApproved()==null || loanRequest.getHasLoanOfficerApproved().equalsIgnoreCase("false")) {
            return "Loanee officer";
        }
        if(loanRequest.getHasRiskApproved()==null || loanRequest.getHasRiskApproved().equalsIgnoreCase("false")){
            return "Risk";
        }
        return "";
    }
}
