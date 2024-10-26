package project.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import project.filmorate.exception.InvalidFilm;
import project.filmorate.exception.InvalidFilmId;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.model.Film;
import project.filmorate.model.User;

import java.util.ArrayList;

@Component
@Slf4j
public class InMemoryFilmStorage implements FilmStorage{

    ArrayList<Film> films = new ArrayList<>();
    @Override
    public Film addFilm(Film film) {
        films.add(film);
        return film;
    }

    @Override
    public Film deleteFilm(Film film) {
        if(films.contains(film)){
            films.remove(film);
            return film;
        }else{
            throw new InvalidFilm("Film doesn't found");
        }

    }

    @Override
    public Film updateFilm(Film film) {
        for(int i = 0; i < films.size(); i++){
            if(film.getId() == films.get(i).getId()){
                films.set(i, film);
                log.info("Фильм {} обновлен: {}", film.getId(), film.toString());
                return film;
            }
        }

        films.add(film);
        log.info("Фильм добавлен: {}", film.toString());
        return film;
    }

    public ArrayList<Film> allFilms(){
        return films;
    }

    public Film getFilm(int id){
        for (int i = 0; i < films.size(); i++) {
            if(films.get(i).getId() == id){
                return films.get(i);
            }
        }
        throw new InvalidFilmId("Film with id " + id + " doesn't found");
    }

}
