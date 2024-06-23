package bg.softuni.exam_23062024.services;

import bg.softuni.exam_23062024.models.entities.Style;
import bg.softuni.exam_23062024.models.enums.StyleNameEnum;
import bg.softuni.exam_23062024.repositories.StyleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StyleService {
    private final StyleRepository styleRepository;

    public StyleService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    public void seedStyles() {
        if (this.styleRepository.count() == 0) {
            for (StyleNameEnum name : StyleNameEnum.values()) {
                Style style = new Style();
                style.setName(name);

                if (style.getName().equals(StyleNameEnum.IMPRESSIONISM)) {
                    style.setDescription("Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.");
                } else if (style.getName().equals(StyleNameEnum.ABSTRACT)) {
                    style.setDescription("Abstract art does not attempt to represent recognizable subjects in a realistic manner. ");
                } else if (style.getName().equals(StyleNameEnum.EXPRESSIONISM)) {
                    style.setDescription("Expressionism is a style of art that doesn't concern itself with realism.");
                } else if (style.getName().equals(StyleNameEnum.SURREALISM)) {
                    style.setDescription("Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.");
                } else if (style.getName().equals(StyleNameEnum.REALISM)) {
                    style.setDescription("Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance.");
                }
                this.styleRepository.save(style);
            }
        }
    }

    public List<StyleNameEnum> getAllStyles() {
        return this.styleRepository.findAll().stream()
                .map(Style::getName)
                .collect(Collectors.toList());
    }
}
