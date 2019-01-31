package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.esg.collection.Article;
import uz.pdp.esg.repository.PostauthorRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

//import uz.pdp.esg.repository.ArticleRepository;

@Controller
@RequestMapping("admin/author")
public class AdminAuthorController {
    @Autowired
    private PostauthorRepository postauthorRepository;

    @GetMapping("/list")
    public String getArticle(Map<String, Object> model) {
        model.put("articles", postauthorRepository.findAll());
        return "admin/adminAritcle";
    }



    @PostMapping("/save")
    public String saveArticle(@ModelAttribute Article article,Model model) {
        try {
            System.out.println("author=" +article.getAuthor());
            postauthorRepository.save(article);
            model.addAttribute("save",article.getAuthor()+" saved");
        } catch (Exception e) {
            model.addAttribute("save",article.getAuthor()+" not saved");
        }
        return "admin/adminAritcle";


    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id,Model model) {
        try {
            System.out.println("author=" + id);
            postauthorRepository.deleteById(Long.valueOf(id));
       model.addAttribute("delete","deleted");
        }
        catch (Exception e){
            model.addAttribute("delete","deleted");
            e.printStackTrace();

        }
        return "admin/adminAritcle";

    }

    @PostMapping("/update")
    public void updateCategory(@ModelAttribute Article article, HttpServletResponse response,Map<String, Object> model) throws IOException {

        System.out.println("awfaw="+article.getAuthor());

//       Optional<Article> article1= postauthorRepository.findById(article.getId());
        Optional<Object> article1= Optional.of(postauthorRepository.findById(article.getId()));
        System.out.println(article.getId());
//        System.out.println("aftor="+article1.getAuthor());

        if(article1!=null){
            System.out.println("admin aftor=");
            postauthorRepository.save(article);
            model.put("category","saved");
        }
        else
        { System.out.println("admin not aftor=");
            model.put("category","not saved");}
//       return "/admin/adminAritcle";
        response.sendRedirect("/admin/author/list");
    }
}
