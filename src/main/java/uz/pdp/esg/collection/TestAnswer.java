package uz.pdp.esg.collection;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Data
@Entity(name = "test_answer")
public class TestAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //savol uzunligi 3000 ta char
@Length(max = 3000)
    private String text;

    private Boolean isTrue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id")
    private Test test;

    public TestAnswer() {
    }

    public TestAnswer(String text, Boolean isTrue) {
        this.text = text;
        this.isTrue = isTrue;
    }


}
