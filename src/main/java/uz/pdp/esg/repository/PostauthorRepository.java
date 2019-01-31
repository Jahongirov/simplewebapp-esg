package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.Article;
import uz.pdp.esg.collection.News;

import java.util.List;

public interface PostauthorRepository extends JpaRepository<Article,Long> {

    Article getAllById(Long id);
    Article getById(Long id);
    //oxirgi 3 tasini chiqaradi
    List<Article> findTop2ByOrderByAuthorDesc();

    List<Article> getAllByTitleContaining(String title);
}
