package uz.pdp.esg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.esg.repository.NewsRepository;
import uz.pdp.esg.repository.PostauthorRepository;
import uz.pdp.esg.repository.VideoRepository;
import uz.pdp.esg.service.VideoService;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoService videoService;

    @Autowired
    private PostauthorRepository postauthorRepository;
    @GetMapping("/")
    public String adminIndex(Model model, Model model1, Model model2){

        model.addAttribute("lastnews", newsRepository.findAll());
        model1.addAttribute("lastvideos", videoRepository.findTop3ByOrderByNameDesc());
        model2.addAttribute("lastposts", postauthorRepository.findTop2ByOrderByAuthorDesc());


        return "index";
    }
    @GetMapping("post/{id}")
    public String getPostById(@PathVariable Long id, Model model){
        model.addAttribute("postid", postauthorRepository.getById(id));
        return "user/postById";
    }

    @GetMapping("news/{id}")
    public String getNewsById(@PathVariable Long id, Model model){
        model.addAttribute("newsid", newsRepository.getById(id));
        return "indexNewsById";
    }

    @GetMapping("video/{id}")
    public String getVideoById(@PathVariable Long id, Model model){
        System.out.println("idsi="+ id);
        model.addAttribute("postid", videoRepository.getById(id));
        return "indexVideosById";
    }

    @RequestMapping("read/{id}")
    public void readFile(HttpServletResponse response, @PathVariable Long id) {
        System.out.println("id="+id);
        videoService.readFile(response, id);
    }
}
