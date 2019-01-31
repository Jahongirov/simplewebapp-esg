package uz.pdp.esg.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.pdp.esg.collection.User;
import uz.pdp.esg.repository.UserRepository;

@Component
public class UserUtil {

    @Autowired
    private UserRepository userRepository;
//login bo'b kirgan userni qaytarish uchun qo'llaniladi gan
    public User getUserByUserName() {
        User user = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication == null || !authentication.isAuthenticated()
                || "anonymousUser".equals("" + authentication.getPrincipal()))) {
            String userName = authentication.getName();
            user = userRepository.findByEmailOrUsername(userName,userName).get();
        }
        return user;
    }

}
