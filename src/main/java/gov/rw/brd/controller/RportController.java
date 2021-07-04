package gov.rw.brd.controller;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import gov.rw.brd.domain.LoanRequest;
import gov.rw.brd.repository.LoanRequestRepository;
import gov.rw.brd.repository.LoaneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hp on 6/2/2021.
 */
@Controller
public class RportController {

    @Autowired
    ServletContext servletContext;

    @Autowired
    private LoanRequestRepository repo;



    private final TemplateEngine templateEngine;

    public RportController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @RequestMapping(path = "/pdf/{id}")
    public ResponseEntity<?> getPDF(@PathVariable(name = "id") String id,HttpServletRequest request, HttpServletResponse response) throws IOException {

        /* Do Business Logic*/
        List<LoanRequest> list = repo.findAll();
        System.out.println("the id is ==>  " + id);
        if(id!=null ){
            if(id.equalsIgnoreCase("approved")){
                list = new LoanRequest().getApproved(list);
            }else if(id.equalsIgnoreCase("pending")){
                list = new LoanRequest().getPending(list);
            }else if(id.equalsIgnoreCase("Declined")){
                list = new LoanRequest().getDeclined(list);
            }else{
                list = new ArrayList<>();
            }
        }


        /* Create HTML using Thymeleaf template Engine */

        WebContext context = new WebContext(request, response, servletContext);
        context.setVariable("requests", list);
        context.setVariable("report", id);
        String orderHtml = null;
        if(id.equalsIgnoreCase("approved")){
            orderHtml = templateEngine.process("report-approved", context);
        }
        if(id.equalsIgnoreCase("declined")){
            orderHtml = templateEngine.process("report-decline", context);
        }
        if(id.equalsIgnoreCase("pending")){
            orderHtml = templateEngine.process("test-report", context);
        }


        /* Setup Source and target I/O streams */

        ByteArrayOutputStream target = new ByteArrayOutputStream();

        /*Setup converter properties. */
        ConverterProperties converterProperties = new ConverterProperties();
        converterProperties.setBaseUri("http://localhost:7878");

        /* Call convert method */
        HtmlConverter.convertToPdf(orderHtml, target, converterProperties);

        /* extract output as bytes */
        byte[] bytes = target.toByteArray();


        /* Send the response as downloadable PDF */

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(bytes);

    }
    @RequestMapping(path = "/pdf/test/data")
    private String testReport(Model model){
        model.addAttribute("requests", repo.findAll());
        return "test-report";
    }


}
