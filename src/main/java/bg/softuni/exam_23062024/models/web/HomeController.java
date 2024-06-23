package bg.softuni.exam_23062024.models.web;

import bg.softuni.exam_23062024.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

//        List<Task> allTasks = this.taskService.getAllTasks();
//        model.addAttribute("allTasks", allTasks);
//        User user = this.userService.getUser();
//        List<Task> allUserTasks = this.taskService.getAllUserTasks(user);
//
//        model.addAttribute("allUserTasks", allUserTasks);

        return "home";
    }
}
