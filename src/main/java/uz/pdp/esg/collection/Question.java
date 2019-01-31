package uz.pdp.esg.collection;

import lombok.Data;

import javax.persistence.*;


@Data
//@Document(collection = "question")
@Entity(name = "question")
public class Question/* extends UserDateAudit*/ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionAnswer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by")
    private User createdBy;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    public Question() {
    }

    public Question(Category category, String text) {
        this.category = category;
        this.text = text;
    }

    public Question(Category category, String text, QuestionAnswer answer) {
        this.category = category;
        this.text = text;
        this.answer = answer;
    }


}

