package uz.pdp.esg.controller.moderator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/moderator")
public class MaboutController {
    @GetMapping("/about")
    public String getAbout(){
        return "moderator/about";
    }
}
