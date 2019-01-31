package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.News;
import uz.pdp.esg.collection.Video;

import java.util.List;

public interface NewsRepository extends JpaRepository<News,Long> {

//    List<News> findFirst3OrOrderByDesc();
News getById(Long id);

    List<News> getAllByTitleContaining(String title);
}
