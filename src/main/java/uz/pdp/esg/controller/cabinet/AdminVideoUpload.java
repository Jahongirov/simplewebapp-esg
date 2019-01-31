package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartRequest;
import uz.pdp.esg.collection.Video;
import uz.pdp.esg.model.Result;
import uz.pdp.esg.repository.VideoRepository;
import uz.pdp.esg.service.VideoService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin/video")
public class AdminVideoUpload {
    @Autowired
    private VideoService videoService;
    @Autowired
    private VideoRepository videoRepository;

    //@Autowired
//private FileService fileService;
    @GetMapping("/video-list")
    public String getVideoList(Model model) {
        model.addAttribute("videos", videoRepository.findAll());
        return "admin/adminVideo";
    }

    @PostMapping("/videoUpload")
    @ResponseBody
    public Result uploadVideo(@ModelAttribute Video video, MultipartRequest request) {

        return videoService.uploadFile(request, video);
    }


    @RequestMapping("/read/{id}")
    public void readFile(HttpServletResponse response, @PathVariable Long id) {
        videoService.readFile(response, id);
    }

    @PostMapping("/delete/{id}")
    @ResponseBody
    public Boolean postUserList(@PathVariable String id, Model model){
        System.out.println("id="+id);
        try {
            videoRepository.deleteById(Long.valueOf(id));
            return true;
//
        }catch (Exception e){

            e.printStackTrace();
            return false;
        }

    }
}
