package uz.pdp.esg.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.esg.collection.Question;
import uz.pdp.esg.collection.TestCategory;
import uz.pdp.esg.repository.CategoryRepository;
import uz.pdp.esg.repository.QuestionRepository;
import uz.pdp.esg.utils.UserUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("user/question")
public class UserQuestionController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserUtil userUtil;

    @RequestMapping
    public String question(Map<String,Object> model,Map<String,Object> model1){
        model.put("categories", categoryRepository.findAll());
       model.put("questions", questionRepository.findAllByCreatedBy(userUtil.getUserByUserName()));
        return "user/question";
    }


    @PostMapping("save")
    public void save(@ModelAttribute Question question, HttpServletResponse response) throws IOException {
        question.setCreatedBy(userUtil.getUserByUserName());
        questionRepository.save(question);
        response.sendRedirect("/user/question");
    }
}
