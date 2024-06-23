package bg.softuni.exam_23062024.config;

import bg.softuni.exam_23062024.services.StyleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {
    private StyleService styleService;

    public AppRunner(StyleService styleService) {
        this.styleService = styleService;
    }


    @Override
    public void run(String... args) throws Exception {
        this.styleService.seedStyles();
    }
}
