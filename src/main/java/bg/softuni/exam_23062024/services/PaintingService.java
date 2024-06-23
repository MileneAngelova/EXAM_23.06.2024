package bg.softuni.exam_23062024.services;

import bg.softuni.exam_23062024.models.dtos.AddPaintingDTO;
import bg.softuni.exam_23062024.models.entities.Painting;
import bg.softuni.exam_23062024.models.entities.Style;
import bg.softuni.exam_23062024.models.entities.User;
import bg.softuni.exam_23062024.repositories.PaintingRepository;
import bg.softuni.exam_23062024.repositories.StyleRepository;
import bg.softuni.exam_23062024.repositories.UserRepository;
import bg.softuni.exam_23062024.session.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PaintingService {
    private final UserRepository userRepository;
    private final StyleRepository styleRepository;
    private final PaintingRepository paintingRepository;
    private final CurrentUser currentUser;
    private final ModelMapper modelMapper;

    public PaintingService(UserRepository userRepository, CurrentUser currentUser, ModelMapper modelMapper, StyleRepository styleRepository, PaintingRepository paintingRepository) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.modelMapper = modelMapper;
        this.styleRepository = styleRepository;
        this.paintingRepository = paintingRepository;
    }

    public List<Painting> getAllPaintings() {
        return this.paintingRepository.findAll();
    }

    @Transactional
    public List<Painting> getUserPaintings(Long id) {

        return this.paintingRepository.findAll().stream()
                .filter(painting -> painting.getOwner().getId().equals(id)).toList();
    }

    public void addPainting(AddPaintingDTO addPaintingDTO) {
        Optional<User> byUsername = this.userRepository.findByUsername(currentUser.getUsername());

        if (byUsername.isEmpty()) {
            throw new NoSuchElementException();
        }

        Style style = this.styleRepository.findByName(addPaintingDTO.getStyle());

        User owner = this.modelMapper.map(byUsername, User.class);
        Painting painting = new Painting();

        painting.setName(addPaintingDTO.getName());
        painting.setAuthor(addPaintingDTO.getAuthor());
        painting.setImageUrl(addPaintingDTO.getImageUrl());
        painting.setStyle(style);
        painting.setOwner(owner);

        getAllPaintings().add(painting);
        this.paintingRepository.save(painting);
        this.userRepository.save(owner);
    }

    @Transactional
    public void removePainting(Long id) {
        Painting paintingToDelete = this.paintingRepository.findById(id).orElse(null);
        User user = paintingToDelete.getOwner();
        List<Painting> userPaintings = user.getPaintings();
        userPaintings.remove(paintingToDelete);
        this.userRepository.save(user);
        this.paintingRepository.deleteById(id);
    }

    public void addToFavourite(Long paintingId) {
        Optional<User> byUsername = this.userRepository.findByUsername(currentUser.getUsername());

        if (byUsername.isEmpty()) {
            throw new NoSuchElementException();
        }

        User user = this.modelMapper.map(byUsername, User.class);
        Painting painting = paintingRepository.findById(modelMapper.map(paintingId, Painting.class).getId()).orElse(null);
        user.getFavouritePaintings().add(painting);
    }
}
