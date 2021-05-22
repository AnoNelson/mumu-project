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
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
            String fileNm = UUID.randomUUID().toString()+ StringUtils.cleanPath(loanee.getLoanRequest().getRequestLetter().getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + fileNm);
            Files.copy(loanee.getLoanRequest().getRequestLetter().getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            loanee.getLoanRequest().setRequestLetterName( fileNm);
            System.out.println(loanee);
            String fileNm2 = UUID.randomUUID().toString()+ StringUtils.cleanPath(loanee.getLoanRequest().getBusinessPlan().getOriginalFilename());
            Path path2 = Paths.get(UPLOAD_DIR + fileNm2);
            Files.copy(loanee.getLoanRequest().getBusinessPlan().getInputStream(), path2, StandardCopyOption.REPLACE_EXISTING);
            loanee.getLoanRequest().setBusinessPlanName(fileNm2);
            String fileNm3 = UUID.randomUUID().toString()+ StringUtils.cleanPath(loanee.getLoanRequest().getBankStatement().getOriginalFilename());
            Path path3 = Paths.get(UPLOAD_DIR + fileNm3);
            Files.copy(loanee.getLoanRequest().getBankStatement().getInputStream(), path3, StandardCopyOption.REPLACE_EXISTING);
            loanee.getLoanRequest().setBankStatementName(fileNm3);
            String fileNm4 = UUID.randomUUID().toString()+ StringUtils.cleanPath(loanee.getLoanRequest().getLandDocuments().getOriginalFilename());
            Path path4 = Paths.get(UPLOAD_DIR + fileNm4);
            Files.copy(loanee.getLoanRequest().getLandDocuments().getInputStream(), path4, StandardCopyOption.REPLACE_EXISTING);
            loanee.getLoanRequest().setLandDocumentsName(fileNm4);
            redirAttrs.addFlashAttribute("success", "file successfully added");
            service.saveLoanRequest(loanee);

            return "redirect:/view-requests";
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
        System.out.println("file name is " + name);
        LoanRequest loanRequest = service.getRequests(name);
        System.out.println("data =>>"+loanRequest.getLoanee());
        model.addAttribute("request",loanRequest);
        return "view-account-files";
    }
    @RequestMapping(value = "downloadFile/{name}", method = RequestMethod.GET)
    public StreamingResponseBody getSteamingFile(HttpServletResponse response, @PathVariable(name = "name") String name)
            throws IOException {
        response.setContentType("application/pdf");
        InputStream inputStream = new FileInputStream(new File(UPLOAD_DIR + "" + name));
        return outputStream -> {
            int nRead;
            byte[] data = new byte[1024];
            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                outputStream.write(data, 0, nRead);
            }
        };
    }

    @RequestMapping("/view-report")
    public String viewReport (Model model) {
        List<LoanRequest> list = service.getAllRequests();
        model.addAttribute("list", list);
        return "report-view";
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
