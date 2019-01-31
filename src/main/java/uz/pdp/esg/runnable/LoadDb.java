package uz.pdp.esg.runnable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.esg.collection.Role;
import uz.pdp.esg.collection.RoleName;
import uz.pdp.esg.collection.User;
import uz.pdp.esg.repository.RoleRepository;
import uz.pdp.esg.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class LoadDb implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {

//        if (roleRepository.count() == 0) {
//            roleRepository.save(new Role(RoleName.ROLE_USER));
//            roleRepository.save(new Role(RoleName.ROLE_MODERATOR));
//            roleRepository.save(new Role(RoleName.ROLE_ADMIN));
//        }
//        if (userRepository.count()==0){
//            List<Role> roles = new ArrayList<>();
//            roles.add(roleRepository.findByName(RoleName.ROLE_USER).get());
//            List<Role> roles2 = new ArrayList<>();
//            roles2.add(roleRepository.findByName(RoleName.ROLE_ADMIN).get());
//            List<Role> roles1 = new ArrayList<>();
//            roles1.add(roleRepository.findByName(RoleName.ROLE_MODERATOR).get());
//            userRepository.save(new User("admin",passwordEncoder().encode("1"),"admin@admin.uz","", roles2));
//            userRepository.save(new User("user",passwordEncoder().encode("1"),"user@admin.uz","", roles));
//            userRepository.save(new User("moderator",passwordEncoder().encode("1"),"moderator@admin.uz","", roles1));
//        }
    }
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
