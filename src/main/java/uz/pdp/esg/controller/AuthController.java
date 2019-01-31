package uz.pdp.esg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.esg.collection.User;
import uz.pdp.esg.model.Result;
import uz.pdp.esg.repository.RoleRepository;
import uz.pdp.esg.repository.UserRepository;
import uz.pdp.esg.service.AuthService;

import java.util.Map;

@Controller
@RequestMapping("sign")
public class AuthController {
@Autowired
private AuthService authService;
@Autowired
private UserRepository userRepository;
@Autowired
private RoleRepository roleRepository;
    @RequestMapping("in")
    public String signInPage() {
        return "sign/in";
    }


//    @PostMapping("/in")
//    public String signInPage1() {
//        return "sign/in";
//    }

    @GetMapping("up")
    public String signUpPage(Map<String, Object> model, Model model1) {
        model.put("users",userRepository.findAll());
        model1.addAttribute("roles", roleRepository.findAll());
        return "sign/up";
    }
    @PostMapping("/up")
    public String signUpPage1(@ModelAttribute User user, Map<String,Object> model) {
        Result result=authService.signUp(user);
        if (result.isSuccess()){
            model.put("save",result.getMessage());
            return "sign/success";
        }
        model.put("save",result.getMessage());
        return "sign/up";
    }
}
