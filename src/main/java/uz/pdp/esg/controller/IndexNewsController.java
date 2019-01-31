package uz.pdp.esg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.esg.repository.NewsRepository;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/index/news")
public class IndexNewsController {
    @Autowired
    private NewsRepository newsRepository;
    @GetMapping("/list")
    public String userNews(Model model){
        model.addAttribute("news", newsRepository.findAll());
        return "indexnews";
    }
    @GetMapping("{id}")
    public String getPostById(@PathVariable Long id, Model model){
        model.addAttribute("postid", newsRepository.getById(id));
        return "indexNewsById";
    }

    @RequestMapping("/search")
    public String search(HttpServletResponse response, @RequestParam String title, Model model) {
        model.addAttribute("news", newsRepository.getAllByTitleContaining(title));

//       videoService.search(response, name);
        return "indexnews";
    }
}
