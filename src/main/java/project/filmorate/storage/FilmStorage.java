package project.filmorate.storage;

import project.filmorate.model.Film;
import project.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;

public interface FilmStorage {
    void createFilm(Film Film);
    Film deleteFilm(Film Film);

    Film updateFilm(Film Film);

    ArrayList<Film> allFilms();

    List<User> getLikedUsers(Integer filmId);

    Film getFilm(int filmId);
}
