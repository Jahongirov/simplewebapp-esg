package uz.pdp.esg.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.esg.repository.PostauthorRepository;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user/posts")
public class UserPostsController {
@Autowired
private PostauthorRepository postauthorRepository;
    @GetMapping("/list")
    public String getUserNews(Model model){

        model.addAttribute("articles",postauthorRepository.findAll());
        return "user/posts";
    }
    @GetMapping("{id}")
    public String getPostById(@PathVariable Long id, Model model){
        model.addAttribute("postid", postauthorRepository.getById(id));
        return "user/postById";
    }


    @RequestMapping("/search")
    public String search(HttpServletResponse response, @RequestParam String title, Model model) {
        model.addAttribute("articles", postauthorRepository.getAllByTitleContaining(title));

//       videoService.search(response, name);
        return "user/posts";
    }
}
