package bg.softuni.exam_23062024.models.dtos;

import bg.softuni.exam_23062024.models.enums.StyleNameEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AddPaintingDTO {
    @NotBlank(message = "This field can not be empty.")
    @Size(min = 5, max = 40, message = "Name length must be between 5 and 40 characters!")
    private String name;

    @NotBlank(message = "This field can not be empty!")
    @Size(min = 5, max = 30, message = "Author name must be between 5 and 30 characters!")
    private String author;

    @NotBlank(message = "Please enter a valid image URL!")
    @Size(max = 150, message = "The field can NOT contain more than 150 characters!")
    private String imageUrl;

    @NotNull(message = "You must select a style!")
    private StyleNameEnum style;

    public AddPaintingDTO() {
    }

    public String getName() {
        return name;
    }

    public AddPaintingDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public AddPaintingDTO setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AddPaintingDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public StyleNameEnum getStyle() {
        return style;
    }

    public AddPaintingDTO setStyle(StyleNameEnum style) {
        this.style = style;
        return this;
    }
}
