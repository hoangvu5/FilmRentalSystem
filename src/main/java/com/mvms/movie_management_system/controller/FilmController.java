package com.mvms.movie_management_system.controller;

import com.mvms.movie_management_system.entity.Film;
import com.mvms.movie_management_system.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class FilmController {
    @Autowired
    private FilmRepository filmRepository;

    @GetMapping("/films")
    public List<Film> fetchFilm() {
        System.out.println("FilmController - fetchFilm()");
        return filmRepository.findAll();
    }

    @PostMapping("/films")
    public Film createFilm(@RequestBody Film film) {
        System.out.println("FilmController - createFilm()");
        if (film.getFilmId() != null && filmRepository.findById(film.getFilmId()).isPresent()) {
            throw new IllegalArgumentException("Film[id = " + film.getFilmId() + "] has already existed.");
        }
        return filmRepository.save(film);
    }

    @PutMapping("/films/{id}")
    public Film updateFilm(@PathVariable Long id, @RequestBody Film film) {
        System.out.println("FilmController - updateFilm()");
        Optional<Film> dbFilm = filmRepository.findById(id);
        if (dbFilm.isEmpty()) {
            throw new IllegalArgumentException("Film[id = " + id + "] does not exist.");
        } else {
            return filmRepository.save(film);
        }
    }

    @DeleteMapping("/films/{id}")
    public String deleteFilm(@PathVariable Long id) {
        System.out.println("FilmController - deleteFilm()");
        Optional<Film> dbFilm = filmRepository.findById(id);
        if (dbFilm.isEmpty()) {
            throw new IllegalArgumentException("Film[id = " + id + "] does not exist.");
        } else {
            filmRepository.deleteById(id);
            return "Film[id = " + id + "] was deleted!";
        }
    }

    // Select films with category X, actor Y, and has 1 or more copies
    @GetMapping("/films/query")
    public List<Film> queryFilmSpecial(@RequestParam String categoryName, @RequestParam String actorName) {
        return filmRepository.findFilmsByCategoryActorAndInventoryCount(categoryName, actorName);
    }
}
