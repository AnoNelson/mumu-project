package gov.rw.brd.controller;

import gov.rw.brd.domain.LoginRequest;
import gov.rw.brd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by hp on 3/16/2021.
 */
@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;
    @RequestMapping("/dashboard")
    public String getLogin(@ModelAttribute("auth") LoginRequest loginRequest) {
        return "index";
    }

    @RequestMapping("/user-account")
    public String getAllUsers (Model model) {
        model.addAttribute("usersList",userRepository.findAll());
        return "users";
    }


}
