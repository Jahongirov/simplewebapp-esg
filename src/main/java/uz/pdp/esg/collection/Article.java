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
//@Document(collection = "article")
@Entity(name = "article")
public class Article extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String author;

    private String title;

    private String content;

    private Integer viewCount=0;

    public Article(String author, String title, String content, Integer viewCount) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
    }
}
