package uz.pdp.esg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.esg.collection.User;
import uz.pdp.esg.model.Result;
import uz.pdp.esg.model.UserPrincipal;
import uz.pdp.esg.repository.RoleRepository;
import uz.pdp.esg.repository.UserRepository;

import java.util.Arrays;

@Service("authService")
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
@Autowired
private PasswordEncoder passwordEncoder;
@Autowired
private RoleRepository roleRepository;
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail).orElseThrow(() ->
                new UsernameNotFoundException("User not found with username or email : " + usernameOrEmail)
        );
        return UserPrincipal.create(user);
    }

//    public Boolean deleteUserById(Integer id){
//        try {
//
//        }catch (Exception e){
//
//        }
//    }

    public Result signUp(User user){
        Result result=new Result();
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
//            System.out.println("role="+ user.getRoles().get(0).getName());
//            user.setRoles(Arrays.asList(roleRepository.findByName()));

            userRepository.save(user);

            result.setMessage("user saved");
            result.setSuccess(true);
        }catch (Exception e){
            result.setMessage("user not saved");
            result.setSuccess(false);
            e.printStackTrace();
        }
        return result;
    }
}
