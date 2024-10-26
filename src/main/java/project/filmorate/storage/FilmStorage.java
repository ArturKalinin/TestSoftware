package project.filmorate.storage;

import project.filmorate.model.Film;

import java.util.ArrayList;

public interface FilmStorage {
    Film addFilm(Film Film);
    Film deleteFilm(Film Film);

    Film updateFilm(Film Film);

    ArrayList<Film> allFilms();

    Film getFilm(int id);
}
