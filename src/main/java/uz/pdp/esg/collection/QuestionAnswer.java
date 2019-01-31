package uz.pdp.esg.collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.esg.collection.audit.DateAudit;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
//@Document(collection = "question_answer")
@Entity(name = "question_answer")
public class QuestionAnswer extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public QuestionAnswer() {
    }

    public QuestionAnswer(String text, User user) {
        this.text = text;
        this.user = user;
    }


}
