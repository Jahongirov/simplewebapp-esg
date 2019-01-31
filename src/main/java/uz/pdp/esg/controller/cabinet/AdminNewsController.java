package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.esg.collection.Article;
import uz.pdp.esg.collection.News;
import uz.pdp.esg.repository.NewsRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("admin/news")
public class AdminNewsController {
    @Autowired
    private NewsRepository newsRepository;

    @GetMapping("/list")
    public String getArticle(Map<String, Object> model) {
        model.put("yangiliklar", newsRepository.findAll());
        return "admin/adminNews";
    }

    @PostMapping("/save")
    public String saveArticle(@ModelAttribute News news, Model model) {
        try {
           System.out.println("author=" +news.getTitle());
            newsRepository.save(news);
            model.addAttribute("save",news.getTitle()+" saved");
        } catch (Exception e) {
            model.addAttribute("save",news.getTitle()+" not saved");
        }
        return "admin/adminNews";


    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable String id, Model model) {
        try {
            System.out.println("news=" + id);
            newsRepository.deleteById(Long.valueOf(id));
            model.addAttribute("delete","deleted");
        }
        catch (Exception e){
            model.addAttribute("delete","not deleted");
            e.printStackTrace();

        }
        return "admin/adminNews";

    }

    @PostMapping("/update")
    public void updateCategory(@ModelAttribute News news, HttpServletResponse response, Map<String, Object> model) throws IOException {

//        System.out.println("awfaw="+new .getAuthor());

//       Optional<Article> article1= postauthorRepository.findById(article.getId());
        Optional<Object> article1= Optional.of(newsRepository.findById(news.getId()));
        System.out.println(news.getId());
//        System.out.println("aftor="+article1.getAuthor());

        if(article1!=null){
            System.out.println("admin new=");
            newsRepository.save(news);
            model.put("yangiliklar","saved");
        }
        else
        { System.out.println("admin not news=");
            model.put("yangiliklar","not saved");}
//       return "/admin/adminAritcle";
        response.sendRedirect("/admin/adminNews");
    }
}
