package project.filmorate.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.filmorate.exception.InvalidUser;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class InMemoryUserStorageTest {
    private User user;
    private UserStorage userStorage;
    @BeforeEach
    void setUp(){
        user = new User(1, "frenz.mr@mail.ru", "mrfrenz", "Artur", LocalDate.of(2004,9,27));
        userStorage = new InMemoryUserStorage();
    }
    @Test
    void addUser(){
        userStorage.addUser(user);
        Assertions.assertEquals(1, userStorage.allUsers().size());
    }
    @Test
    void deleteUser_delete(){
        userStorage.addUser(user);

        userStorage.deleteUser(user);
        Assertions.assertEquals(0, userStorage.allUsers().size());
    }
    @Test
    void deleteUser_InvalidUser(){
        Assertions.assertThrows(InvalidUser.class, () -> userStorage.deleteUser(user));
    }
    @Test
    void updateUser_update(){
        userStorage.addUser(user);
        User updateUser = new User(1, "update.mail@mail.ru", "mrfrenz", "Artur", LocalDate.of(2004,9,27));

        userStorage.updateUser(updateUser);
        Assertions.assertEquals("update.mail@mail.ru", userStorage.getUser(1).getEmail());
    }
    @Test
    void updateUser_addNewUser(){
        userStorage.addUser(user);
        User newUser = new User(2, "newmail@mail.ru", "logintest", "Artur", LocalDate.of(2004,9,27));

        userStorage.updateUser(newUser);
        Assertions.assertEquals(newUser, userStorage.getUser(2));
    }
    @Test
    void getUser_user(){
        userStorage.addUser(user);
        Assertions.assertEquals(user, userStorage.getUser(1));
    }
    @Test
    void getUser_InvalidUserId(){
        Assertions.assertThrows(InvalidUserId.class, () -> userStorage.getUser(1));
    }
    @Test
    void allUsers(){
        User user2 = new User(2, "newmail@mail.ru", "logintest", "new", LocalDate.of(2004,9,27));
        User user3 = new User(3, "test@mail.ru", "test", "Maria", LocalDate.of(2004,9,27));
        ArrayList<User> users = new ArrayList<>();
        Collections.addAll(users, user, user2, user3);
        userStorage.addUser(user);
        userStorage.addUser(user2);
        userStorage.addUser(user3);

        Assertions.assertEquals(users, userStorage.allUsers());
    }
    @Test
    void isUserExist_true(){
        userStorage.addUser(user);
        Assertions.assertTrue(userStorage.isUserExist(user.getId()));
    }
    @Test
    void isUserExist_false(){
        Assertions.assertFalse(userStorage.isUserExist(user.getId()));
    }
}
