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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import project.filmorate.model.Film;
import project.filmorate.model.Genre;
import project.filmorate.model.MPA;
import project.filmorate.model.User;
import project.filmorate.service.FilmService;
import project.filmorate.service.UserService;

import java.time.LocalDate;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void getUser() throws Exception {
        User user = new User(8, "frenz.mr@mail.ru", "mrfrenz", "Artur", LocalDate.of(2004, 9, 27));

        when(userService.getUser(8)).thenReturn(user);
        mockMvc.perform(get("/users/{userId}", 8))
                .andExpect(jsonPath("$.id").value(8))
                .andExpect(jsonPath("$.email").value("frenz.mr@mail.ru"))
                .andExpect(jsonPath("$.login").value("mrfrenz"))
                .andExpect(jsonPath("$.name").value("Artur"));
    }

    @Test
    void addUser() throws Exception{
        User user = new User("test@mail.ru", "test", "test", LocalDate.of(2000, 10, 10));
        String userJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                        .andExpect(status().is2xxSuccessful());
    }

    @Test
    void deleteUser() throws Exception{
        Integer userId = 11;

        mockMvc.perform(delete("/users/{userId}", userId))
                .andExpect(status().is2xxSuccessful());
    }
}
