package bg.softuni.exam_23062024.models.web;

import bg.softuni.exam_23062024.models.dtos.AddPaintingDTO;
import bg.softuni.exam_23062024.models.enums.StyleNameEnum;
import bg.softuni.exam_23062024.services.PaintingService;
import bg.softuni.exam_23062024.services.StyleService;
import bg.softuni.exam_23062024.services.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AddPaintingController {
    private final UserService userService;
    private final PaintingService paintingService;
    private final StyleService styleService;

    public AddPaintingController(UserService userService, PaintingService paintingService, StyleService styleService) {
        this.userService = userService;
        this.paintingService = paintingService;
        this.styleService = styleService;
    }

    @ModelAttribute("addPaintingModel")
    public AddPaintingDTO initPainting() {
        return new AddPaintingDTO();
    }

    @GetMapping("/painting/add")
    public String addPainting(Model model) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        List<StyleNameEnum> styles = this.styleService.getAllStyles();
        model.addAttribute("styles", styles);

        return "/add-painting";
    }

    @PostMapping("/painting/add")
    public String addPainting(@Valid AddPaintingDTO addPaintingModel, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addPaintingModel", addPaintingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addPaintingModel", bindingResult);

            return "redirect:/painting/add";
        }
        this.paintingService.addPainting(addPaintingModel);
        return "redirect:/home";
    }
}
