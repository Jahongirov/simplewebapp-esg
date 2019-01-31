package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.QuestionAnswer;

import java.util.Optional;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {

 Optional<QuestionAnswer> findAllByText(String text);
    QuestionAnswer findByText(String text);
    Optional<QuestionAnswer> findById(Long id);
}
