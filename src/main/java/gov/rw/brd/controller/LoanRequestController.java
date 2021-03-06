package gov.rw.brd.controller;

import gov.rw.brd.domain.LoanRequest;
import gov.rw.brd.domain.Loanee;
import gov.rw.brd.domain.RequestCheck;
import gov.rw.brd.domain.User;
import gov.rw.brd.repository.LoanRequestRepository;
import gov.rw.brd.repository.LoaneeRepository;
import gov.rw.brd.repository.UserRepository;
import gov.rw.brd.service.AppService;
import gov.rw.brd.service.EmailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
    private UserRepository userRepository;
    @Autowired
    private AppService service;
    @Autowired
    private EmailProvider emailProvider;

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
            if(username.trim().equalsIgnoreCase("") || username ==null){
                redirAttrs.addFlashAttribute("error", "Error: " + "please login");
                return "redirect:/login";
            }
            User user = service.getUserByUserName(username);
            if(user==null){
                redirAttrs.addFlashAttribute("error", "Error: " + " user data not found");
                return "redirect:/request-loan";
            }
            loanee.setUser(user);
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
    public String returnLoaneeRequest (Model model,HttpSession session) {
        if(session.getAttribute("role") !=null){
            String role = session.getAttribute("role").toString();
            String username = (String) session.getAttribute("username");
            List<LoanRequest> list = service.getAllRequests(role,username);
            model.addAttribute("requests",list);
            return "account-view";
        }else{
            return "redirect:/login";
        }
    }
    @RequestMapping("/view-docs/{name}")
    public String viewDocuments (@PathVariable(name = "name") String name,Model model) {
        System.out.println("file name is " + name);
        LoanRequest loanRequest = service.getRequests(name);
        System.out.println("data =>>"+loanRequest.getLoanee());
        model.addAttribute("check",new RequestCheck());
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
    public String viewReport (Model model,HttpSession session) {
        String role = session.getAttribute("role").toString();
        List<LoanRequest> list = new ArrayList<>();
        model.addAttribute("list", list);
        return "report-view";
    }

    @RequestMapping(value = "/action/{id}", method = RequestMethod.POST)
    public String requestAction(HttpSession session,@PathVariable(name = "id") String id,@PathParam(value = "action") String action, RequestCheck comment){
        String role = session.getAttribute("role").toString();
        System.out.println("role ----> "+session.getAttributeNames().toString());
        System.out.println("action ---> "+comment);
        LoanRequest loanRequest = service.getRequests(id);
        loanRequest = service.save(handleRequestAction(role,loanRequest,action,comment.getComments()));
        System.out.println(loanRequest);
        return "redirect:/view-requests";
    }

    public LoanRequest handleRequestAction(String role,LoanRequest loanRequest,String action,String comments){
        String sone = null;
        if(action.equalsIgnoreCase("approve")){
            sone = "A";
        }else{
            sone = "D";
            loanRequest.setStatus("D");
            emailProvider.sendApprovalEmaail(loanRequest.getLoanee(),"Rejected");
        }
        if(role.equalsIgnoreCase("officer")){
         loanRequest.setHasLoanOfficerApproved(sone);
         loanRequest.setLoanOfficerAprovalDate(LocalDateTime.now());
        }else if(role.equalsIgnoreCase("credit")){
         loanRequest.setHasCreditComitteeAproved(sone);
         loanRequest.setCreditCommitteeAprovalDate(LocalDateTime.now());
        }
        else if(role.equalsIgnoreCase("risk")) {
            loanRequest.setHasRiskApproved(sone);
            loanRequest.setRiskAprovalDate(LocalDateTime.now());
        }
        else if(role.equalsIgnoreCase("legal")) {
            loanRequest.setHasLegalApproved(sone);
            loanRequest.setLegalAprovalDate(LocalDateTime.now());
        }
        if(sone.equalsIgnoreCase("A") && role.equalsIgnoreCase("legal")){
            loanRequest.setStatus("A");
            emailProvider.sendApprovalEmaail(loanRequest.getLoanee(),"Approved");
        }
        loanRequest.setDeclineReason(comments);
      return loanRequest;
    }
    @PostMapping("/saveProfile")
    public String saveProfile(HttpSession session,@RequestParam("image") MultipartFile multipartFile) throws IOException {
        if(session.getAttribute("role") !=null) {
            String username = session.getAttribute("username").toString();
            User user = service.getUserByUserName(username);
            String fileNm = UUID.randomUUID().toString()+ StringUtils.cleanPath(multipartFile.getOriginalFilename());
            Path path = Paths.get(UPLOAD_DIR + fileNm);
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            user.setPhoto(fileNm);
            userRepository.save(user);
            return "redirect:/dashboard";
        }else {
            return "profile";
        }
    }

    @RequestMapping("/view-profile")
    public String viewProfile (Model model,HttpSession session) {
        if(session.getAttribute("username") !=null) {
            String username = session.getAttribute("username").toString();
            User user = service.getUserByUserName(username);
            model.addAttribute("user", user);
            return "profile";
        }else{
            return "index";
        }
    }
}
