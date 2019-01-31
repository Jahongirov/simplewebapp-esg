package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.repository.CategoryRepository;
import uz.pdp.esg.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("admin/category")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping
    public String category(Map<String, Object> model){
        model.put("categories",categoryRepository.findAll());
        return "admin/category";
    }

    @PostMapping("list")
    public @ResponseBody  List<Category> list(){
        return categoryRepository.findAll();
    }

    @PostMapping("save")
    public void save(@ModelAttribute Category category, HttpServletResponse response) throws IOException {
        categoryService.saveCategory(category);
        response.sendRedirect("/admin/category");
    }
    @PostMapping("/delete/{id}")
    public @ResponseBody Boolean delete(@PathVariable String id, HttpServletResponse response) {
        try {
            categoryRepository.deleteById(Long.valueOf(id));
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    @PostMapping("/update")
    public void updateCategory(@ModelAttribute Category category, HttpServletResponse response,Map<String, Object> model) throws IOException {
       Optional<Category> category1= categoryRepository.findById(category.getId());
//        System.out.println(category.getId());
//       System.out.println(category1.get().getName());

       if(category1!=null){
           categoryRepository.save(category);
           model.put("category","saved");
       }
       else
           model.put("category","not saved");
       response.sendRedirect("/admin/category");
    }

}
