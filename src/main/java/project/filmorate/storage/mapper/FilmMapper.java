package project.filmorate.storage.mapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import project.filmorate.model.Film;
import project.filmorate.model.Genre;
import project.filmorate.model.MPA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmMapper implements RowMapper<Film> {

    JdbcTemplate jdbcTemplate;

    public FilmMapper(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Film mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Film(rs.getInt("filmId"),
                rs.getString("name"),
                rs.getString("filmDescription"),
                rs.getDate("releaseDate").toLocalDate(),
                rs.getInt("duration"),
                new MPA(rs.getInt("mpaid"), rs.getString("code"), rs.getString("mpaDescription")),
                new Genre(rs.getInt("genreId"), rs.getString("genreName")),
                new ArrayList<>(getLikedUsers(rs.getInt("filmId")))
                );
    }
    private List<Integer> getLikedUsers(Integer filmId){
        String sql = "select * from likedfilmbyuser where filmId = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> rs.getInt("userId"), filmId);
    }
}
