package uz.pdp.esg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import uz.pdp.esg.collection.Video;
import uz.pdp.esg.model.Result;
import uz.pdp.esg.repository.VideoRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

@Service("videoService")
public class VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Transactional
    public Result uploadFile(MultipartRequest request, Video video1){
        Result result=new Result();

        try {
            MultipartFile file= request.getFile("video");
            if(file!=null){
                Boolean t=false;
                Video video=new Video();
                video.setContent(file.getBytes());
                video.setIzoh(video1.getIzoh());
//                Double d= (double)(file.getSize()*1.0/(1024*1024));
//                NumberFormat numberFormat=new DecimalFormat("#.#");
//                    Long lo= Long.valueOf(numberFormat.format(d));
//                System.out.println("size="+numberFormat.format(d));
                video.setLengthVideo(file.getSize());
                video.setName(file.getOriginalFilename());

                if(video1.getFreeOrPay()==null)
                {        video.setFreeOrPay(false);
                    System.out.println(false);
                   }
                else {
                    video.setFreeOrPay(video1.getFreeOrPay());
                    System.out.println(video1.getFreeOrPay());
                }
                videoRepository.save(video);
                result.setMessage("Video saved to base");
                result.setSuccess(true);
            }
        }catch (Exception e){
            result.setSuccess(false);
            result.setMessage("Video not saved");
            e.printStackTrace();
        }

        return result;
    }


//    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
//    public void getFile(HttpServletResponse response, String name) {
//        try {
//            Video file = videoRepository.getByName(name);
//            response.setContentType(file.getMimeType());
//            response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
//            FileCopyUtils.copy(file.getContent(), response.getOutputStream());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void readFile(HttpServletResponse response, Long id) {
        try {
            Video file = videoRepository.getById(id);
            response.setContentType(file.getName());
            response.setHeader("Content-disposition", "inline; filename=\"" + file.getName() + "\"");
            response.setHeader("Content-type", file.getName());
            FileCopyUtils.copy(file.getContent(), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public void search(HttpServletResponse response, String name) {
        try {
            List<Video> file = videoRepository.getAllByNameContaining(name);
            for(int i=0;i<file.size();i++) {
                response.setContentType(file.get(i).getName());
                response.setHeader("Content-disposition", "inline; filename=\"" + file.get(i).getName() + "\"");
                response.setHeader("Content-type", file.get(i).getName());
                FileCopyUtils.copy(file.get(i).getContent(), response.getOutputStream());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}



//   try {
//           List<Video> file = videoRepository.getAllByNameContaining(name);
//        for(int i=0;i<videoRepository.getAllByNameContaining(name).size();i++) {
//        response.setContentType(file.get(i).getName());
//        response.setHeader("Content-disposition", "inline; filename=\"" + file.get(i).getName() + "\"");
//        response.setHeader("Content-type", file.get(i).getName());
//        FileCopyUtils.copy(file.get(i).getContent(), response.getOutputStream());
//        }
//        } catch (IOException e) {
//        e.printStackTrace();
//        }
