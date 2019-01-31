package uz.pdp.esg.controller.moderator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.esg.collection.Role;
import uz.pdp.esg.collection.RoleName;
import uz.pdp.esg.collection.User;
import uz.pdp.esg.repository.RoleRepository;
import uz.pdp.esg.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

@Controller
@RequestMapping("/moderator/ms")
public class ModeratorsListController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping("/ms-list")
    public String getUserList(Map<String,Object> model){
String name="ROLE_MODERATOR";
List<Role> roles=new ArrayList<>();
roles.add(roleRepository.findByName(RoleName.ROLE_MODERATOR).get());
//userRepository.findAllByRolesIn(roles);
model.put("mlist",userRepository.findAllByRolesIn(roles));
        return "moderator/moderators";
    }
}
