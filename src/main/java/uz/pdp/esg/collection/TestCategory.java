package uz.pdp.esg.collection;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "test_category")
public class TestCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //    @Indexed(unique = true)
    private String name;

    private String description;

    public TestCategory() {
    }

    public TestCategory(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
