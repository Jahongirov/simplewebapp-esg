package uz.pdp.esg.collection;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
//@Document(collection = "video")
@Entity(name = "video")
public class Video {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String izoh;

    private Long lengthVideo;

    private Boolean freeOrPay;

    private byte[]  content;

    public Video(String name, String izoh, Long lengthVideo, Boolean freeOrPay, byte[] content) {
        this.name = name;
        this.izoh = izoh;
        this.lengthVideo = lengthVideo;
        this.freeOrPay = freeOrPay;
        this.content = content;
    }

    public Video() {
    }
}
