package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.Role;
import uz.pdp.esg.collection.RoleName;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName name);
//    List<Role> findByName(String name);
}
