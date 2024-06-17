package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Film;
import com.mvms.movie_management_system.exception.custom.RecordFoundException;
import com.mvms.movie_management_system.exception.custom.RecordNotFoundException;
import com.mvms.movie_management_system.repository.FilmRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/films")
    public ResponseEntity<List<Film>> fetchFilm() {
        System.out.println("FilmController - fetchFilm()");
        return ResponseEntity.ok(filmRepository.findAll());
    }

    @PostMapping("/films")
    public ResponseEntity<Film> createFilm(@Valid @RequestBody Film film) {
        System.out.println("FilmController - createFilm()");
        if (film.getFilmId() != null && filmRepository.findById(film.getFilmId()).isPresent()) {
            throw new RecordFoundException("Film[id = " + film.getFilmId() + "] has already existed.");
        }
        return ResponseEntity.ok(filmRepository.save(film));

    }

    @PutMapping("/films/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Long id, @Valid @RequestBody Film film) {
        System.out.println("FilmController - updateFilm()");
        Optional<Film> dbFilm = filmRepository.findById(id);
        if (dbFilm.isEmpty()) {
            throw new RecordNotFoundException("Film[id = " + id + "] does not exist.");
        } else {
            return ResponseEntity.ok(filmRepository.save(film));
        }
    }

    @DeleteMapping("/films/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Long id) {
        System.out.println("FilmController - deleteFilm()");
        Optional<Film> dbFilm = filmRepository.findById(id);
        if (dbFilm.isEmpty()) {
            throw new RecordNotFoundException("Film[id = " + id + "] does not exist.");
        } else {
            filmRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    // Select films with category X, actor Y, and has 1 or more copies
    @GetMapping("/films/query")
    public ResponseEntity<List<Film>> queryFilmSpecial(@RequestParam String categoryName, @RequestParam String actorName) {
        return ResponseEntity.ok(filmRepository.findFilmsByCategoryActorAndInventoryCount(categoryName, actorName));
    }
}
