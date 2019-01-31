package uz.pdp.esg.controller.cabinet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.esg.collection.Category;
import uz.pdp.esg.collection.QuestionAnswer;
import uz.pdp.esg.collection.User;
import uz.pdp.esg.repository.CategoryRepository;
import uz.pdp.esg.repository.QuestionAnswerRepository;
import uz.pdp.esg.repository.QuestionRepository;
import uz.pdp.esg.repository.UserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin/question")
public class AdminSavollarListController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private QuestionAnswerRepository questionAnswerRepository;
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/lists")
    public String getQuestionsList(Model model, Model model1,Model model2,Model model3){
        model.addAttribute("qlists",questionRepository.findAll());
        model.addAttribute("categories",categoryRepository.findAll());
        model.addAttribute("answers",questionAnswerRepository.findAll());
        model.addAttribute("users",userRepository.findAll());

        return "admin/savollarList";

    }

    @PostMapping("/update")
    public String updateAnswer(@RequestParam String answer,@RequestParam Long id, HttpServletResponse response, Map<String, Object> model) throws IOException {
        System.out.println("answer="+id);
        Optional<QuestionAnswer> questionAnswer1=questionAnswerRepository.findById(id);
//        System.out.println("question="+ questionAnswer1.getText());
        if(questionAnswer1!=null){
            System.out.println("getText="+questionAnswer1.get().getText());
            questionAnswer1.get().setText(answer);
                questionAnswerRepository.save(questionAnswer1.get());
            model.put("category","saved");
        }
        else
        {
            System.out.println("elsega kirid");
            model.put("category","not saved");}
//        response.sendRedirect("/admin/question/savollarList");
        return "admin/savollarList";
    }



}
