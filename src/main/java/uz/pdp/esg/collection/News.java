package uz.pdp.esg.collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import uz.pdp.esg.collection.audit.UserDateAudit;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@EqualsAndHashCode(callSuper = true)
@Data
//@Document(collection = "news")
@Entity(name = "news")
public class News extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    private Integer viewContent=0;


    public News(String title, String content, Integer viewContent) {
        this.title = title;
        this.content = content;
        this.viewContent = viewContent;
    }

    public News() {
    }
}
