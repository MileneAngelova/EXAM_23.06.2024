package bg.softuni.exam_23062024.repositories;

import bg.softuni.exam_23062024.models.entities.Style;
import bg.softuni.exam_23062024.models.enums.StyleNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StyleRepository extends JpaRepository<Style, Long> {
    Style findByName(StyleNameEnum name);
}
