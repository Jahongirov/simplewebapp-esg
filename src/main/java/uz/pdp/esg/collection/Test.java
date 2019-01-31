package uz.pdp.esg.collection;

import lombok.Data;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import java.util.List;

@Data
//@Document(collection = "test")
@Entity(name = "test")
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY)
////    @JoinColumn(name = "category_id")
////    private Category category;

        @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private TestCategory category;
    //savol javobi 3000 ta char
    @Length(max = 3000)
    private String question;
@OneToMany(cascade = CascadeType.ALL)
@JoinColumn(name = "test_answer_id")
    private List<TestAnswer> answers;


}
