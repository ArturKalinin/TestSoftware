package project.filmorate.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class Film {
    private Integer filmId;
    private String name;
    private String filmDescription;
    private LocalDate releaseDate;
    private Integer duration;
    private MPA mpa;
    private Genre genre;
    private List<Integer> likedByUser = new ArrayList<>();

    public Film(int filmId, String name, String filmDescription, LocalDate releaseDate, int duration, MPA mpa, Genre genre) {
        this.filmId = filmId;
        this.name = name;
        this.filmDescription = filmDescription;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.mpa = mpa;
        this.genre = genre;
    }

    public Film(int filmId, String name, String filmDescription, LocalDate releaseDate, int duration, MPA mpa, Genre genre, List<Integer> likedByUser) {
        this.filmId = filmId;
        this.name = name;
        this.filmDescription = filmDescription;
        this.releaseDate = releaseDate;
        this.duration = duration;
        this.mpa = mpa;
        this.genre = genre;
        this.likedByUser = likedByUser;
    }

    public Film(int filmId, String name, String filmDescription, LocalDate releaseDate, int duration) {
        this.filmId = filmId;
        this.name = name;
        this.filmDescription = filmDescription;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

    public Film(String name, String filmDescription, LocalDate releaseDate, int duration) {
        this.name = name;
        this.filmDescription = filmDescription;
        this.releaseDate = releaseDate;
        this.duration = duration;
    }

//    public int getLikesCount(){
//        return likes.size();
//    }
}
