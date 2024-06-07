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
            return "Customer[id = " + id + "] was deleted!";
        }
    }
}
