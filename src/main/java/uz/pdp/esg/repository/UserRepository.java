package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.Role;
import uz.pdp.esg.collection.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailOrUsername(String email, String username);
   String findByRoles(Long id);
//   List<User> getAllByRolesName(String name);
    List<User> findAllByRolesIn(List<Role> roles);
}
