package bg.softuni.exam_23062024.repositories;

import bg.softuni.exam_23062024.models.entities.Painting;
import bg.softuni.exam_23062024.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaintingRepository extends JpaRepository<Painting, Long> {
List<Painting> getAllByOwner(User owner);
}
