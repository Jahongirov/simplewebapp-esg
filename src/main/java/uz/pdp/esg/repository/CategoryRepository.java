package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
