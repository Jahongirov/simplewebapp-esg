package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.esg.collection.Question;
import uz.pdp.esg.collection.QuestionAnswer;
import uz.pdp.esg.repository.QuestionAnswerRepository;
import uz.pdp.esg.repository.QuestionRepository;
import uz.pdp.esg.utils.UserUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminQuestionController {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;

    @Autowired
    private UserUtil userUtil;

    @RequestMapping("question")
    public String question(Map<String,Object> model){
        model.put("questions",questionRepository.findAllByAnswerIsNull());
        return "admin/question";
    }

    @PostMapping("question-answer/save")
    public void answer(@ModelAttribute QuestionAnswer questionAnswer, @RequestParam Long questionId, HttpServletResponse response) throws IOException {
        questionAnswer.setUser(userUtil.getUserByUserName());
        QuestionAnswer questionAnswer1 = questionAnswerRepository.save(questionAnswer);
        Question question = questionRepository.getById(questionId);
        question.setAnswer(questionAnswer1);
        questionRepository.save(question);
        response.sendRedirect("/admin/question");
    }
}
