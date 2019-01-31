package uz.pdp.esg.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.collection.Test;
import uz.pdp.esg.collection.TestAnswer;
import uz.pdp.esg.collection.TestCategory;
import uz.pdp.esg.model.TestResult;
import uz.pdp.esg.model.UserPrincipal;
import uz.pdp.esg.repository.CategoryRepository;
import uz.pdp.esg.repository.TestAnswerRepository;
import uz.pdp.esg.repository.TestCategoryRepository;
import uz.pdp.esg.repository.TestRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("user")
public class UserTestController {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TestCategoryRepository testCategoryRepository;
    @Autowired
    private TestRepository testRepository;

    @Autowired
    private TestAnswerRepository testAnswerRepository;

    @ModelAttribute("security")
    public UserPrincipal userPrincipal(){
        return (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
//    @RequestMapping
//    public String test(Map<String, Object> model) {
//        model.put("categories", categoryRepository.findAll());
//        return "user/test";
//    }
    @RequestMapping
    public String test(Map<String, Object> model) {
        model.put("categories", testCategoryRepository.findAll());
        return "user/test";
    }
//    @PostMapping("test")
//    public @ResponseBody
//    List<Test> tests(@ModelAttribute Category category) {
//        return testRepository.findAllByCategory(category);
//    }
    @PostMapping("test")
    public @ResponseBody
    List<Test> tests(Long category) {
        TestCategory c = testCategoryRepository.getOne(category);
        return testRepository.findAllByCategory(c);
    }

    @PostMapping("test/check")
    public @ResponseBody
    TestResult check(HttpServletRequest request) {
        TestResult testResult = new TestResult();
        List<String> answerIds = Arrays.asList(request.getParameterValues("answerIds[]"));
        System.out.println(answerIds);
        List<String> testIds = Arrays.asList(request.getParameterValues("testIds[]"));
        Integer result = 0;
        List<Test> tests = new ArrayList<>();
        for (int i = 0; i < testIds.size(); i++) {
            TestAnswer testAnswer = testAnswerRepository.getById(Long.valueOf(answerIds.get(i)));
            List<TestAnswer> testAnswers = testRepository.getById(Long.valueOf(testIds.get(i))).getAnswers();
            for (TestAnswer testAnswer1 : testAnswers) {
                if (testAnswer1.getIsTrue()) {
                    testResult.getResultIds().add(testAnswer1.getId());
                    break;
                }
            }
            if (testAnswers.contains(testAnswer) && testAnswer.getIsTrue()) {
                result++;
            }
        }
        testResult.setResultCount(result);
            return testResult;
    }
}
