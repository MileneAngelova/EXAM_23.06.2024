package bg.softuni.exam_23062024.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "paintings")
public class Painting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "style_id", nullable = false)
    private Style style;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;

    @Column(nullable = false)
    private boolean isFavourite;

    @Column(nullable = false)
    private int votes;

    public Painting() {
    }

    public Long getId() {
        return id;
    }

    public Painting setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Painting setName(String name) {
        this.name = name;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public Painting setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public Painting setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public Painting setFavourite(boolean favourite) {
        isFavourite = favourite;
        return this;
    }

    public int getVotes() {
        return votes;
    }

    public Painting setVotes(int votes) {
        this.votes = votes;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }


    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }

    public void addVote() {
    }
}
