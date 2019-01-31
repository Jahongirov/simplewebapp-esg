package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.TestAnswer;

public interface TestAnswerRepository extends JpaRepository<TestAnswer, Long> {
    TestAnswer getById(Long id);
}
