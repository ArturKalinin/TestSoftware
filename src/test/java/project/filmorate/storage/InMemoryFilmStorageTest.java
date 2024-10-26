package project.filmorate.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.filmorate.exception.InvalidFilm;
import project.filmorate.exception.InvalidFilmId;
import project.filmorate.exception.InvalidUser;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.model.Film;
import project.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class InMemoryFilmStorageTest {
    private Film film;
    private FilmStorage filmStorage;
    @BeforeEach
    void setUp(){
        film = new Film(1, "FirstFilm", "bla-bla-bla", LocalDate.of(2000, 10,10), 120);
        filmStorage = new InMemoryFilmStorage();
    }
    @Test
    void addFilm(){
        filmStorage.addFilm(film);
        Assertions.assertEquals(1, filmStorage.allFilms().size());
    }
    @Test
    void deleteFilm_delete(){
        filmStorage.addFilm(film);

        filmStorage.deleteFilm(film);
        Assertions.assertEquals(0, filmStorage.allFilms().size());
    }
    @Test
    void deleteFilm_InvalidFilm(){
        Assertions.assertThrows(InvalidFilm.class, () -> filmStorage.deleteFilm(film));
    }
    @Test
    void updateFilm_update(){
        filmStorage.addFilm(film);
        Film updateFilm = new Film(1, "updateFirstFilm", "bla-bla-bla", LocalDate.of(2000,10,10), 120);

        filmStorage.updateFilm(updateFilm);
        Assertions.assertEquals("updateFirstFilm", filmStorage.allFilms().getFirst().getName());
    }
    @Test
    void updateFilm_addNewFilm(){
        filmStorage.addFilm(film);
        Film newFilm = new Film(2, "updateFirstFilm", "bla-bla-bla", LocalDate.of(2000,10,10), 120);

        filmStorage.updateFilm(newFilm);
        Assertions.assertEquals(newFilm, filmStorage.getFilm(2));
    }
    @Test
    void getFilm_Film(){
        filmStorage.addFilm(film);
        Assertions.assertEquals(film, filmStorage.getFilm(1));
    }
    @Test
    void getFilm_InvalidFilmId(){
        Assertions.assertThrows(InvalidFilmId.class, () -> filmStorage.getFilm(1));
    }
    @Test
    void allFilms(){
        Film film2 = new Film(2, "updateFirstFilm", "bla-bla-bla", LocalDate.of(2000,10,10), 120);
        Film film3 = new Film(3, "updateFirstFilm", "bla-bla-bla", LocalDate.of(2000,10,10), 120);
        ArrayList<Film> films = new ArrayList<>();
        Collections.addAll(films, film, film2, film3);
        filmStorage.addFilm(film);
        filmStorage.addFilm(film2);
        filmStorage.addFilm(film3);

        Assertions.assertEquals(films, filmStorage.allFilms());
    }
}
