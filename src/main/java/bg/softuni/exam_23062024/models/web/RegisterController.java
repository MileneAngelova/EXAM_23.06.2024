package bg.softuni.exam_23062024.models.web;

import bg.softuni.exam_23062024.models.dtos.RegisterDTO;
import bg.softuni.exam_23062024.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("registerModel")
    public RegisterDTO initRegister() {
        return new RegisterDTO();
    }

    @GetMapping("/register")
    public String register() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterDTO registerModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerModel", registerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.registerModel", bindingResult);

            return "redirect:register";
        }
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        this.userService.register(registerModel);

        return "redirect:/login";
    }
}
