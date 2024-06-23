package bg.softuni.exam_23062024.models.web;

import bg.softuni.exam_23062024.models.dtos.AddPaintingDTO;
import bg.softuni.exam_23062024.models.entities.Painting;
import bg.softuni.exam_23062024.models.entities.User;
import bg.softuni.exam_23062024.services.PaintingService;
import bg.softuni.exam_23062024.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class HomeController {
    private final UserService userService;
    private final PaintingService paintingService;

    public HomeController(UserService userService, PaintingService paintingService) {
        this.userService = userService;
        this.paintingService = paintingService;
    }
    @ModelAttribute("paintingModel")
    public AddPaintingDTO paintingModel() {
        return new AddPaintingDTO();
    }

    @GetMapping("/")
    public String index() {
        if (this.userService.isLoggedIn()) {
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

        List<Painting> allPaintings = this.paintingService.getAllPaintings();
        model.addAttribute("allPaintings", allPaintings);

        Long id = this.userService.getUser().getId();
        List<Painting> userPaintings = this.paintingService.getUserPaintings(id);

        model.addAttribute("userPaintings", userPaintings);

        return "home";
    }

    @GetMapping("/home/painting-remove/{id}")
    public String removePainting(@PathVariable Long id) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }
        this.paintingService.removePainting(id);
        return "redirect:/home";
    }

    @GetMapping("/home/add-to-favourite/{id}")
    public String addToFavourite(@PathVariable Long id, Model model) {
        if (!this.userService.isLoggedIn()) {
            return "redirect:/";
        }

    

        this.paintingService.removePainting(id);
        return "redirect:/home";
    }
}
