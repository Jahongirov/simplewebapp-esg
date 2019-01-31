package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.Question;
import uz.pdp.esg.collection.User;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findAllByCreatedBy(User user);
//    List<Question> findAllByCreatedBy(String id);
    List<Question> findAllByAnswerIsNull();
    Question getById(Long id);
}
