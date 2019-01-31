package uz.pdp.esg.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.esg.collection.Video;
import uz.pdp.esg.model.Result;
import uz.pdp.esg.repository.VideoRepository;
import uz.pdp.esg.service.VideoService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user/video")
public class UserVideoController {
@Autowired
private VideoRepository videoRepository;

@Autowired
private VideoService videoService;
    @GetMapping("/list")
    public String getVideoListUser(Map<String,Object> model){
        model.put("videos",videoRepository.findAll());
        return "user/videos";
    }

    @RequestMapping("/read/{id}")
    public void readFile(HttpServletResponse response, @PathVariable Long id) {
        System.out.println("id="+id);
        videoService.readFile(response, id);
    }



    @RequestMapping("/search")
    public String search(HttpServletResponse response, @RequestParam String name,Model model) {
        model.addAttribute("videos", videoRepository.getAllByNameContaining(name));

//       videoService.search(response, name);
        return "user/videos";
    }

    @GetMapping("{id}")
    public String getPostById(@PathVariable Long id, Model model, Model model1){
        System.out.println("idsi="+ id);
        model.addAttribute("postid", videoRepository.getById(id));
        model1.addAttribute("lastvideos", videoRepository.findTop3ByOrderByNameDesc());
//        model.addAttribute("videos", videoRepository.getAllByNameContaining());
        return "user/videosById";
    }


}
