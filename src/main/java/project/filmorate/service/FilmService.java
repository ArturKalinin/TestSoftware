package project.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.filmorate.exception.InvalidFilmId;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.exception.LikesException;
import project.filmorate.model.Film;
import project.filmorate.model.User;
import project.filmorate.storage.FilmDbStorage;
import project.filmorate.storage.FilmStorage;

import project.filmorate.storage.UserDbStorage;
import project.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class FilmService {

    private final FilmStorage filmStorage;
    private final UserStorage userStorage;

    @Autowired
    public FilmService(FilmDbStorage filmStorage, UserDbStorage userStorage){
        this.filmStorage = filmStorage;
        this.userStorage = userStorage;
    }

    public Film getFilm(Integer filmId){
        return filmStorage.getFilm(filmId);
    }

    public List<User> getLikedUsers(Integer filmId){
        return filmStorage.getLikedUsers(filmId);
    }

    public void createFilm(Film film){
        filmStorage.createFilm(film);
    }

//    public Film addLike(int id, int userId){
//        if(!userStorage.isUserExist(userId)){
//            throw new InvalidUserId("User with id " + id + " doesn't found");
//        }
//        ArrayList<Film> films = filmStorage.allFilms();
//        for (int i = 0; i < films.size(); i++) {
//            if(films.get(i).getId() == id){
//                Film film = films.get(i);
//                if(film.getLikes().contains(userId)){
//                    throw new LikesException("You already liked this film");
//                }else{
//                    film.getLikes().add(userId);
//                    return film;
//                }
//            }
//        }
//        throw new InvalidFilmId("Film with id " + id + " doesn't found");
//    }
//
//    public Film deleteLike(int id, int userId){
//        if(!userStorage.isUserExist(userId)){
//            throw new InvalidUserId("User with id " + id + " doesn't found");
//        }
//        ArrayList<Film> films = filmStorage.allFilms();
//        for (int i = 0; i < films.size(); i++) {
//            if(films.get(i).getId() == id){
//                Film film = films.get(i);
//                if(film.getLikes().contains(userId)){
//                    film.getLikes().remove(userId);
//                    return film;
//                }else{
//                    throw new LikesException("You didn't like this film");
//
//                }
//            }
//        }
//        throw new InvalidFilmId("Film with id " + id + " doesn't found");
//    }

//    public List<Film> popular(int cnt){
//        ArrayList<Film> films = filmStorage.allFilms();
//        films.sort(Comparator.comparingInt(Film::getLikesCount).reversed());
//        if(films.size() > cnt){
//            return films.subList(0, cnt);
//        }
//        return films;
//    }

//    public List<Film> popular(){
//        ArrayList<Film> films = filmStorage.allFilms();
//        films.sort(Comparator.comparingInt(Film::getLikesCount).reversed());
//        if(films.size() > 10){
//            return films.subList(0, 10);
//        }
//        return films;
//    }
}
