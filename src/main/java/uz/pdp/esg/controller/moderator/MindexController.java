package uz.pdp.esg.controller.moderator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.esg.repository.NewsRepository;
import uz.pdp.esg.repository.PostauthorRepository;
import uz.pdp.esg.repository.VideoRepository;
import uz.pdp.esg.service.VideoService;

@Controller
@RequestMapping("/moderator")
public class MindexController {
    @Autowired
    private NewsRepository newsRepository;
    @Autowired
    private VideoRepository videoRepository;
    @Autowired
    private VideoService videoService;

    @Autowired
    private PostauthorRepository postauthorRepository;
    @GetMapping("/index")
    public String adminIndex(Model model, Model model1, Model model2){

        model.addAttribute("lastnews", newsRepository.findAll());
        model1.addAttribute("lastvideos", videoRepository.findTop3ByOrderByNameDesc());
        model2.addAttribute("lastposts", postauthorRepository.findTop2ByOrderByAuthorDesc());


        return "moderator/index";
    }
}
