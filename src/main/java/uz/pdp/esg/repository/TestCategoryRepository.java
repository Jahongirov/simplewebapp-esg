package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.TestCategory;

public interface TestCategoryRepository extends JpaRepository<TestCategory, Long> {
}
