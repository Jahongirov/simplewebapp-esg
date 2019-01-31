package uz.pdp.esg.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/about")
public class UserAboutController {

    @GetMapping("/about")
    public String getAbout(){
        return "user/about";
    }
}
