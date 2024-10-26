package project.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import project.filmorate.exception.ValidationException;
import project.filmorate.model.Film;
import project.filmorate.service.FilmService;
import project.filmorate.storage.InMemoryFilmStorage;
import project.filmorate.exception.IncorrectParameterException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class FilmController {


    private final InMemoryFilmStorage filmStorage;
    private final FilmService filmService;

    @Autowired
    public FilmController(InMemoryFilmStorage filmStorage, FilmService filmService){
        this.filmStorage = filmStorage;
        this.filmService = filmService;
    }

    private boolean validation(Film film){
        if(!film.getName().isEmpty() && film.getDescription().length() <= 200 &&
                film.getReleaseDate().isAfter(LocalDate.of(1895,12,28)) &&
                film.getDuration() > 0){
            return true;
        }else{
            return false;
        }
    }

    @PostMapping("/film")
    public Film addFilm(@RequestBody Film film){

        if(validation(film)){
            filmStorage.addFilm(film);
            log.info("Фильм добавлен: {}", film.toString());
            return film;
        }else {
            throw new ValidationException("Недопустимые данные фильма");
        }
    }

    @PutMapping("/film")
    public Film updateFilm(@RequestBody Film film){

        if(validation(film)){
            filmStorage.updateFilm(film);
            return film;
        }else{
            throw new ValidationException("Недопустимые данные фильма");
        }
    }

    @GetMapping("/films")
    public ArrayList<Film> allFilms(){
        return filmStorage.allFilms();
    }

    @PutMapping("/films/{id}/like/{userId}")
    public Film addLike(@PathVariable int id, @PathVariable int userId){
        return filmService.addLike(id, userId);
    }

    @DeleteMapping("/films/{id}/like/{userId}")
    public Film deleteLike(@PathVariable int id, @PathVariable int userId){
        return filmService.deleteLike(id, userId);
    }

    @GetMapping("/films/popular")
    public List<Film> popular(@RequestParam(required = false) Integer count){
        if(count == null){
            return filmService.popular();
        }else if (count > 0){
            return filmService.popular(count);
        }else {
            throw new IncorrectParameterException("Incorrect parameter", "count" );
        }
    }

}
