package gov.rw.brd.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import gov.rw.brd.domain.LoginRequest;
import gov.rw.brd.domain.User;
import gov.rw.brd.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

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
        List<User> users = userRepository.findAll();
        model.addAttribute("usersList",users);

        return "users";
    }


}
