package project.filmorate.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.filmorate.model.Film;
import project.filmorate.model.Genre;
import project.filmorate.model.MPA;
import project.filmorate.model.User;
import project.filmorate.storage.mapper.FilmMapper;
import project.filmorate.storage.mapper.UserMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
public class FilmDbStorage implements FilmStorage{

    private JdbcTemplate jdbcTemplate;

    public FilmDbStorage(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film deleteFilm(Film Film) {
        return null;
    }

    @Override
    public Film updateFilm(Film Film) {
        return null;
    }

    @Override
    public ArrayList<Film> allFilms() {
        return null;
    }

    @Override
    public Film getFilm(int filmId) {
        String sql = "select * from film join genre on film.genreId = genre.genreId join mpa on film.mpaId=mpa.mpaId where filmId = ?";
        return jdbcTemplate.query(sql, new FilmMapper(jdbcTemplate), filmId).get(0);
    }

    public List<User> getLikedUsers(Integer filmId){
        String sql = "select * from likedfilmbyuser lfu left join users u on lfu.userId = u.userId where filmId = ?";
        return jdbcTemplate.query(sql, new UserMapper(), filmId);
    }

    @Override
    public void createFilm(Film film){
        String sql = "insert into film(name, filmdescription, releaseDate, duration) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql, film.getName(), film.getFilmDescription(), film.getReleaseDate(), film.getDuration());
    }

//    private Film makeFilm(ResultSet rs) throws SQLException {
//        Integer filmId = rs.getInt("filmId");
//        String description = rs.getString("filmDescription");
//        String name = rs.getString("name");
//        LocalDate releaseDate = rs.getDate("releaseDate").toLocalDate();
//        Integer duration = rs.getInt("duration");
//        Genre genre = new Genre(rs.getInt("genreId"), rs.getString("genreName"));
//        MPA mpa = new MPA(rs.getInt("mpaId"), rs.getString("code"), rs.getString("mpaDescription"));
//        List<Integer> likedByUser = getLikedUsers(filmId);
//        return new Film(filmId, name, description, releaseDate, duration, mpa, genre, likedByUser);
//    }


}
