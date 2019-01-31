package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.collection.User;
import uz.pdp.esg.repository.RoleRepository;
import uz.pdp.esg.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminUserCrud {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping("/user-list")
    public String getUserList(Map<String,Object> model){
        model.put("users",userRepository.findAll());
        model.put("roles",roleRepository.findAll());

//        model.put("userrolename",userRepository.getAllByRoles());

        return "admin/adminUser";
    }
//    @PostMapping("/save")
//    public String save(@ModelAttribute User user) throws IOException {
//        userRepository.save(user);
//       return "admin/adminUser";
//    }
    @PostMapping("list")
    public @ResponseBody
    List<User> list(){
        return userRepository.findAll();
    }

    @PostMapping("/userdelete/{id}")
    @ResponseBody
    public Boolean postUserList(@PathVariable String id, Model model){
        System.out.println("id="+id);
        try {
            System.out.println("in try");
            userRepository.deleteById(Long.valueOf(id));
return true;
//            return "admin/adminUser";
        }catch (Exception e){
            System.out.println("in catch");
            e.printStackTrace();
            return false;
        }

    }

    @PostMapping("/update")
    public void updateCategory(@ModelAttribute User user, HttpServletResponse response,Map<String, Object> model) throws IOException {
        Optional<User> user1= userRepository.findById(user.getId());
//        System.out.println(user.getId());
//        System.out.println(user.get().getName());

        if(user1!=null){
            userRepository.save(user);
            model.put("category","saved");
        }
        else
            model.put("category","not saved");
        response.sendRedirect("/admin/adminUser");
    }
}
