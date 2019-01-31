package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.collection.Test;
import uz.pdp.esg.collection.TestAnswer;
import uz.pdp.esg.collection.TestCategory;
import uz.pdp.esg.repository.CategoryRepository;
import uz.pdp.esg.repository.TestAnswerRepository;
import uz.pdp.esg.repository.TestCategoryRepository;
import uz.pdp.esg.repository.TestRepository;
import uz.pdp.esg.utils.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("admin/test")
public class AdminTestController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private TestAnswerRepository testAnswerRepository;

    @Autowired
    private TestRepository testRepository;
    @Autowired
    private TestCategoryRepository testCategoryRepository;
    @RequestMapping
    public String test(Model model, Model model1, Model model2){
//        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("categories",testCategoryRepository.findAll());
        model1.addAttribute("tests",testRepository.findAll());
        model2.addAttribute("testanswers",testAnswerRepository.findAll());
        return "admin/test";
    }

@PostMapping("save")
public @ResponseBody Boolean save(@RequestParam TestCategory category,
                                  @RequestParam String question, HttpServletRequest request){
    HashMap<String, String> params = CommonUtils.getRequestMap(request);
    List<String> answers = Arrays.asList(request.getParameterValues("answers[]"));
    List<String> answersBool = Arrays.asList(request.getParameterValues("answersBool[]"));
    List<TestAnswer> testAnswers = new ArrayList<>();
    for (int i = 0; i < answers.size(); i++) {
        testAnswers.add(new TestAnswer(answers.get(i),Boolean.parseBoolean(answersBool.get(i))));
    }
    try {
        testAnswerRepository.saveAll(testAnswers);
        Test test = new Test();
        test.setAnswers(testAnswers);
        test.setCategory(category);
        test.setQuestion(question);
        testRepository.save(test);
        return true;
    }
    catch (Exception e){
        e.printStackTrace();
        return false;
    }
}
    @PostMapping("/testdelete/{id}")
    @ResponseBody
    public Boolean postUserList(@PathVariable String id, Model model){
        System.out.println("id="+id);
        try {

            testRepository.deleteById(Long.valueOf(id));
            return true;

        }catch (Exception e){
            System.out.println("in catch");
            e.printStackTrace();
            return false;
        }

    }



}
