package project.filmorate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.filmorate.model.Film;
import project.filmorate.model.Genre;
import project.filmorate.model.MPA;
import project.filmorate.model.User;
import project.filmorate.service.FilmService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class FilmControllerTest {
    @Mock
    private FilmService filmService;

    @InjectMocks
    private FilmController filmController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(filmController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getFilm() throws Exception {
        Film film = new Film(2, "Shutter Island",
                "Два американских судебных пристава отправляются на один из островов в штате Массачусетс, чтобы расследовать исчезновение пациентки клиники для умалишенных преступников. При проведении расследования им придется столкнуться с паутиной лжи, обрушившимся ураганом и смертельным бунтом обитателей клиники.",
                 LocalDate.of(2010,2,13),
                138,
                new MPA(4, "R","Restricted"),
                new Genre(6, "Thriller"));

        when(filmService.getFilm(2)).thenReturn(film);
        mockMvc.perform(get("/films/{filmId}", 2))
                .andExpect(jsonPath("$.filmId").value(2))
                .andExpect(jsonPath("$.name").value("Shutter Island"))
                .andExpect(jsonPath("$.duration").value("138"));
    }

    @Test
    void createFilm() throws Exception {
        Film film = new Film("test", "test", LocalDate.of(2000, 10, 10), 100);
        String filmJson = objectMapper.writeValueAsString(film);
        mockMvc.perform(post("/films")
                .contentType(MediaType.APPLICATION_JSON)
                .content(filmJson))
                .andExpect(status().is2xxSuccessful());
    }

}
