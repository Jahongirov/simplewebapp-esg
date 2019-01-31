package uz.pdp.esg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.esg.collection.Video;

import java.io.File;
import java.util.List;

public interface VideoRepository extends JpaRepository<Video, Long> {
//    String getByName();
Video getById(Long id);

List<Video> getAllByNameContaining(String name);
//    findTop3ByOrderBySalaryDesc();

    //oxirgi 3 tasini chiqaradi
List<Video> findTop3ByOrderByNameDesc();
}
