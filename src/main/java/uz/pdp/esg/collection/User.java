package uz.pdp.esg.collection;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
//@Document(collection = "user")
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Indexed(unique = true)
    private String username;

    private String password;

    //    @Indexed(unique = true)
    private String email;

    private String fullName;

    //    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "test_id")
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles = new ArrayList<>();

    public User() {
    }

    public User(String username, String password, String email, String fullName, List<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.roles = roles;
    }


}
