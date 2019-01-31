package uz.pdp.esg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.esg.repository.VideoRepository;
import uz.pdp.esg.service.VideoService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping("/video")
public class IndexVideoController {
    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private VideoService videoService;
    @GetMapping("/list")
    public String getVideoListUser(Map<String,Object> model){
        model.put("videos",videoRepository.findAll());
        return "indexVideos";
    }

    @RequestMapping("/read/{id}")
    public void readFile(HttpServletResponse response, @PathVariable Long id) {
        System.out.println("id="+id);
        videoService.readFile(response, id);
    }


    @RequestMapping("/search")
    public String search(HttpServletResponse response, @RequestParam String name, Model model) {
        model.addAttribute("videos", videoRepository.getAllByNameContaining(name));
        return "indexVideos";
    }

    @GetMapping("v/{id}")
    public String getPostById(@PathVariable Long id, Model model, Model model1){
        System.out.println("index page idsi="+ id);
        model.addAttribute("postid", videoRepository.getById(id));
//        model.addAttribute("videos", videoRepository.getAllByNameContaining());
        return "indexVideosById";
    }
}
