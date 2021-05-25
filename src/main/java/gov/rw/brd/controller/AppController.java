package gov.rw.brd.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import gov.rw.brd.domain.EStatus;
import gov.rw.brd.domain.LoginRequest;
import gov.rw.brd.domain.User;
import gov.rw.brd.exceptions.GlobalException;
import gov.rw.brd.repository.UserRepository;
import gov.rw.brd.service.EmailProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by hp on 3/16/2021.
 */
@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailProvider emailProvider;
    @RequestMapping("/dashboard")
    public String getLogin(@ModelAttribute("auth") LoginRequest loginRequest) {
        return "index";
    }

    @RequestMapping("/user-account")
    public String getAllUsers (Model model,HttpSession session) {
        List<User> users = userRepository.findAll();
        model.addAttribute("usersList",users);
        return "users";
    }

    @RequestMapping(value="/edit/{id}", method= RequestMethod.POST, params="action=edit")
    public ModelAndView edit(@PathVariable(value = "id") long id) {
        ModelAndView view = new ModelAndView();
        User user = userRepository.findById(id).orElse(new User());
        view.addObject("user",user);
        view.setViewName("user-edit");
        return view;
    }


    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST, params="action=desactivate")
    public String desactivate(@PathVariable(value = "id") long id) {
        ModelAndView view = new ModelAndView();
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            user1.setStatus(EStatus.LOCKED);
            userRepository.save(user1);
            view.setViewName("users");
        }else{
            view.addObject("error","User with "+id +" not found");
        }
        view.setViewName("users");
        return "redirect:/user-account";
    }
    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST, params="action=activate")
    public String activate(@PathVariable(value = "id") long id) {
        ModelAndView view = new ModelAndView();
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User user1 = user.get();
            user1.setStatus(EStatus.ACTIVE);
            System.out.println(user1.getStatus().toString());
            userRepository.save(user1);
            view.setViewName("users");
        }else{
            view.addObject("error","User with "+id +" not found");
        }
        view.setViewName("users");
        return "redirect:/user-account";
    }
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public String register(User user,Model model) {
            User user1 = userRepository.findById(user.getUser_id()).orElse(new User());
             if(user1.getUser_id()==0){
                 model.addAttribute("error","error in saving a user");
                 return "redirect:/signup";
             } else{
                 user1.setEmail(user.getEmail());
                 user1.setNames(user.getNames());
                 user1.setUsername(user.getUsername());
                 User savedUser = userRepository.save(user1);
                 if(savedUser.getUser_id()!=0){
                     model.addAttribute("error","user saved");
                     return "redirect:/user-account";
                 }else{
                     model.addAttribute("error","error in saving a user");
                     return "redirect:/signup";
                 }
             }
    }

    @RequestMapping(value = "/reset",method = RequestMethod.POST)
    public String reset(User user,Model model) {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        user.setPassword(bCryptPasswordEncoder.encode(uuidAsString));
        user.setConfirmPassword("");
        User savedUser = userRepository.save(user);
        if (savedUser.getUser_id() != 0) {
            user.setPassword(uuidAsString);
            String sendMessage = emailProvider.sendEmailAlert(user.getEmail(), user);
            User user1 = userRepository.findById(user.getUser_id()).orElse(new User());
            return "redirect:/user-account";
        }
        return null;
    }

}
