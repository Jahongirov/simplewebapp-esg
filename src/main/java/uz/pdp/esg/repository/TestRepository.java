package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.collection.Test;
import uz.pdp.esg.collection.TestCategory;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    @Query(nativeQuery=true,value = "select * from public.test order by  random() limit 10")
    List<Test> findAllByCategory(TestCategory category);
//    List<Test> findAllByCategory(Category category);
    Test getById(Long id);

}
