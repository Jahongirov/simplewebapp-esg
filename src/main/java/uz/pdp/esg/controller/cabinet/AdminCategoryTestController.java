package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.collection.TestCategory;
import uz.pdp.esg.model.Result;
import uz.pdp.esg.repository.CategoryRepository;
import uz.pdp.esg.repository.TestCategoryRepository;
import uz.pdp.esg.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/test/category")
public class AdminCategoryTestController {
//    @Autowired
//    private CategoryService categoryService;
//
//    @Autowired
//    private CategoryRepository categoryRepository;
//    @GetMapping
//    public String getTest(Map<String, Object> model){
//        model.put("categories",categoryRepository.findAll());
//    }
    @Autowired
    private TestCategoryRepository testCategoryRepository;
        @GetMapping
    public String getTestCategory(Map<String, Object> model){
        model.put("categories",testCategoryRepository.findAll());
        return "admin/categoryTest";
    }
    @PostMapping("save")
    public void save(@ModelAttribute TestCategory category, HttpServletResponse response) throws IOException {
        Result result=new Result();
      try {
          testCategoryRepository.save(category);


      }catch (Exception e){
          e.printStackTrace();
      }
        response.sendRedirect("/admin/test/category");
    }


    @PostMapping("/update")
    public void updateCategory(@ModelAttribute TestCategory category, HttpServletResponse response, Map<String, Object> model) throws IOException {
        Optional<TestCategory> category1= testCategoryRepository.findById(category.getId());
//        System.out.println(category.getId());
//       System.out.println(category1.get().getName());

        if(category1!=null){
            testCategoryRepository.save(category);
            model.put("category","saved");
        }
        else
            model.put("category","not saved");
        response.sendRedirect("/admin/test/category");
    }
}
