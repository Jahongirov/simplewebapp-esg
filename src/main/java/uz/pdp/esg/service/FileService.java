//package uz.pdp.esg.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.util.FileCopyUtils;
//import uz.pdp.esg.collection.Video;
//import uz.pdp.esg.repository.VideoRepository;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Service("fileService")
//public class FileService {
//@Autowired
//private VideoRepository videoRepository;
//    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//    public void getFile(HttpServletResponse response, Long id) {
//        try {
//            Video file = videoRepository.getById(id);
//            response.setContentType(file.getName());
//            response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
//            FileCopyUtils.copy(file.getContent(), response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//    public void readFile(HttpServletResponse response, Long id) {
//        try {
//            Video file = videoRepository.getById(id);
//            response.setContentType(file.getName());
//            response.setHeader("Content-disposition", "inline; filename=\"" + file.getName() + "\"");
//            response.setHeader("Content-type", file.getName());
//            FileCopyUtils.copy(file.getContent(), response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
