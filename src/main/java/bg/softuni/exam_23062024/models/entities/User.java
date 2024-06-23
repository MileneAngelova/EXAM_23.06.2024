package bg.softuni.exam_23062024.models.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @OneToMany(mappedBy = "owner", targetEntity = Painting.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Painting> paintings;

    @ManyToMany(targetEntity = Painting.class, fetch = FetchType.EAGER)
    private Set<Painting> favouritePaintings;

    @ManyToMany(targetEntity = Painting.class, fetch = FetchType.EAGER)
    private Set<Painting> ratedPaintings;

    public User() {
        this.paintings = new ArrayList<>();
        this.favouritePaintings = new HashSet<>();
        this.ratedPaintings = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Painting> getPaintings() {
        return paintings;
    }

    public User setPaintings(List<Painting> paintings) {
        this.paintings = paintings;
        return this;
    }

    public Set<Painting> getFavouritePaintings() {
        return favouritePaintings;
    }

    public User setFavouritePaintings(Set<Painting> favouritePaintings) {
        this.favouritePaintings = favouritePaintings;
        return this;
    }

    public Set<Painting> getRatedPaintings() {
        return ratedPaintings;
    }

    public User setRatedPaintings(Set<Painting> ratedPaintings) {
        this.ratedPaintings = ratedPaintings;
        return this;
    }
}
