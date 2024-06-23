package bg.softuni.exam_23062024.models.entities;

import bg.softuni.exam_23062024.models.enums.StyleNameEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "styles")
public class Style {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private StyleNameEnum name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    public Style() {
    }

    public Long getId() {
        return id;
    }

    public Style setId(Long id) {
        this.id = id;
        return this;
    }

    public StyleNameEnum getName() {
        return name;
    }

    public Style setName(StyleNameEnum name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Style setDescription(String description) {
        this.description = description;
        return this;
    }
}
