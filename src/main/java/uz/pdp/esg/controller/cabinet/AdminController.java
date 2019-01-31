package uz.pdp.esg.controller.cabinet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {

    @RequestMapping
    public String admin(){
        return "admin/index";
    }

}
