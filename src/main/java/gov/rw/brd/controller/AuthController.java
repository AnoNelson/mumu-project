package gov.rw.brd.controller;

import gov.rw.brd.domain.EStatus;
import gov.rw.brd.domain.LoginRequest;
import gov.rw.brd.domain.User;
import gov.rw.brd.exceptions.CustomerErrorGenerator;
import gov.rw.brd.exceptions.GlobalException;
import gov.rw.brd.repository.UserRepository;
import gov.rw.brd.service.EmailProvider;
import gov.rw.brd.service.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.UUID;

/**
 * Created by hp on 3/14/2021.
 */

@Controller
public class AuthController {

//    @Autowired
//    private UserService userService;

    @Autowired
    private CustomerErrorGenerator customerErrorGenerator;
    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private EmailProvider emailProvider;

    @RequestMapping(value = "/user-login", method = RequestMethod.POST)
    public String authenticateUser(@Valid LoginRequest loginRequest, HttpSession session,
                                   RedirectAttributes redirAttrs) {
        ModelAndView view = new ModelAndView();
        System.out.println(loginRequest);
        view.addObject("auth", loginRequest);
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
            User userPrincipal = (User) authentication.getPrincipal();
            System.out.println(userPrincipal);
            session.setAttribute("username", userPrincipal.getUsername());
            session.setAttribute("role", userPrincipal.getRole());
            view.setViewName("dashboard");
            return "redirect:/dashboard";
        } catch (Exception e) {
            // TODO: handle exception
            redirAttrs.addFlashAttribute("error", e.getMessage());
            return "redirect:/login";
        }
    }
    @RequestMapping("/login")
    public String getLogin(@ModelAttribute("auth") LoginRequest loginRequest) {
        return "login";
    }

    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String register(User user, HttpSession session,
                           RedirectAttributes redirAttrs,Model model) {

        if(userRepository.findByUsername(user.getUsername()) != null){
            throw new GlobalException("username already used");
        }
        if(user.getRole()==null || user.getRole().trim().equalsIgnoreCase("")){
            user.setRole("user");
        }
        System.out.println(user);
        if(userRepository.findByEmail(user.getEmail()) == null){
            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            System.out.println("password ----"+uuidAsString);
            user.setStatus(EStatus.ACTIVE);
            user.setPassword(bCryptPasswordEncoder.encode(uuidAsString));
            user.setConfirmPassword("");
            User savedUser = userRepository.save(user);
            if(savedUser.getUser_id()!=0){
                user.setPassword(uuidAsString);
               String sendMessage =  emailProvider.sendEmailAlert(user.getEmail(),user);
                model.addAttribute("error","user saved");
                return "redirect:/login";
            }else{
                model.addAttribute("error","error in saving a user");
                return "redirect:/signup";
            }
        }else {
            throw new GlobalException("Email alread exit");
        }
    }

    @RequestMapping(value = "/signup",method = RequestMethod.GET)
    public String registerPage(@ModelAttribute("user") User user,HttpSession session,Model model) {
        boolean use = false;
        if(session.getAttribute("username") !=null){
          use = true;
        }
        System.out.println("user --> "+use);
        model.addAttribute("use", use);
        return "signup";
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("role");
        return "redirect:/login";
    }
}
