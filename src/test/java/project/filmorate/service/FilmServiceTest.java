package project.filmorate.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.filmorate.exception.InvalidFilmId;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.exception.LikesException;
import project.filmorate.model.Film;
import project.filmorate.model.User;
import project.filmorate.storage.InMemoryFilmStorage;
import project.filmorate.storage.InMemoryFilmStorageTest;
import project.filmorate.storage.InMemoryUserStorage;
import project.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class FilmServiceTest {

    private User user1;
    private User user2;
    private Film film1;
    private Film film2;
    private Film film3;
    private InMemoryFilmStorage filmStorage;
    private FilmService filmService;
    private InMemoryUserStorage userStorage;
    @BeforeEach
    void setUp(){
        user1 = new User(1, "frenz.mr@mail.ru", "mrfrenz", "Artur", LocalDate.of(2004,9,27));
        user2 = new User(2, "mmmmm@mail.ru", "mshsnk", "Maria", LocalDate.of(2004,12,4));
        film1 = new Film(1, "FirstFilm", "bla-bla-bla", LocalDate.of(2000, 10,10), 120);
        film2 = new Film(2, "SecondFilm", "bla-bla-bla", LocalDate.of(2000, 10,10), 120);
        film3 = new Film(3, "ThirdFilm", "bla-bla-bla", LocalDate.of(2000, 10,10), 120);

        userStorage = new InMemoryUserStorage();
        filmStorage = new InMemoryFilmStorage();

        userStorage.addUser(user1);
        userStorage.addUser(user2);
        filmStorage.addFilm(film1);
        filmStorage.addFilm(film2);
        filmStorage.addFilm(film3);

        filmService = new FilmService(filmStorage,userStorage);
    }
    @Test
    void addLike_add(){
        filmService.addLike(1, 1);

        Assertions.assertTrue(film1.getLikes().contains(1));
    }
    @Test
    void addLike_filmIdIncorrect(){
        Assertions.assertThrows(InvalidFilmId.class, ()-> filmService.addLike(4, 1));
    }
    @Test
    void addLike_userIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, ()-> filmService.addLike(1, 3));
    }
    @Test
    void addLike_alreadyLike(){
        filmService.addLike(1, 1);

        Assertions.assertThrows(LikesException.class, () -> filmService.addLike(1,1));
    }
    @Test
    void deleteLike_delete(){
        filmService.addLike(1,1);
        filmService.deleteLike(1,1);

        Assertions.assertTrue(film1.getLikes().isEmpty());
    }
    @Test
    void deleteLike_filmIdIncorrect(){
        Assertions.assertThrows(InvalidFilmId.class, () -> filmService.deleteLike(4, 1));
    }
    @Test
    void deleteLike_userIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, () -> filmService.deleteLike(1, 4));
    }
    @Test
    void deleteLike_notLiked(){
        Assertions.assertThrows(LikesException.class, () -> filmService.deleteLike(1,1));
    }
    @Test
    void popular_count(){
        filmService.addLike(1,1);
        filmService.addLike(1,2);
        filmService.addLike(2,2);
        ArrayList<Film> popular = new ArrayList<>();
        Collections.addAll(popular, film1, film2);

        Assertions.assertEquals(popular, filmService.popular(2));
    }
    @Test
    void popular_withoutCount(){
        ArrayList<Film> popular = new ArrayList<>();
        filmService.addLike(3, 1);
        filmService.addLike(3,2);
        filmService.addLike(1,1);
        Collections.addAll(popular, film3, film1, film2);

        Assertions.assertEquals(popular, filmService.popular());
    }
}
