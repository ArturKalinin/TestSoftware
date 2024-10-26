package project.filmorate.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.filmorate.exception.FriendsException;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.model.User;
import project.filmorate.storage.InMemoryUserStorage;
import project.filmorate.storage.UserStorage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

public class UserServiceTest {

    private User user1;
    private User user2;
    private User user3;
    private UserService userService;
    private InMemoryUserStorage userStorage;
    @BeforeEach
    void setUp(){
        user1 = new User(1, "frenz.mr@mail.ru", "mrfrenz", "Artur", LocalDate.of(2004,9,27));
        user2 = new User(2, "mmmmm@mail.ru", "mshsnk", "Maria", LocalDate.of(2004,12,4));
        user3 = new User(3, "common@mail.ru", "common", "Misha", LocalDate.of(2000,2,2));
        userStorage = new InMemoryUserStorage();
        userStorage.addUser(user1);
        userStorage.addUser(user2);
        userStorage.addUser(user3);
        userService = new UserService(userStorage);
    }
    @Test
    void addFriend_add(){
        userService.addFriend(1, 2);
        Assertions.assertTrue(userStorage.getUser(1).getFriends().contains(user2.getId()));
    }
    @Test
    void addFriends_firstIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, () -> userService.addFriend(100,2));
    }
    @Test
    void addFriends_secondIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, () -> userService.addFriend(1,100));
    }
    @Test
    void addFriends_sameId(){
        Assertions.assertThrows(FriendsException.class, () -> userService.addFriend(1,1));
    }
    @Test
    void addFriends_alreadyFriend(){
        userService.addFriend(1, 2);

        Assertions.assertThrows(FriendsException.class, () ->userService.addFriend(1, 2));
    }
    @Test
    void deleteFriends_delete(){
        userService.addFriend(1,2);

        userService.deleteFriend(1, 2);
        Assertions.assertEquals(0, user1.getFriends().size());
    }
    @Test
    void deleteFriends_firstIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, () -> userService.deleteFriend(100,2));
    }
    @Test
    void deleteFriends_secondIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, () -> userService.deleteFriend(1,100));
    }
    @Test
    void deleteFriends_sameId(){
        Assertions.assertThrows(FriendsException.class, () -> userService.deleteFriend(1,1));
    }
    @Test
    void deleteFriends_alreadyNotFriend(){
        Assertions.assertThrows(FriendsException.class, () ->userService.deleteFriend(1, 2));
    }
    @Test
    void allFriends_friends(){
        userService.addFriend(1,2);
        userService.addFriend(1,3);
        ArrayList<User> friends = new ArrayList<>();
        Collections.addAll(friends, user2, user3);

        Assertions.assertEquals(friends, userService.allFriends(1));
    }
    @Test
    void allFriends_InvalidUserId(){
        Assertions.assertThrows(InvalidUserId.class, () -> userService.allFriends(4));
    }
    @Test
    void commonFriends_common(){
        userService.addFriend(1, 3);
        userService.addFriend(2, 3);
        ArrayList<User> commonFriends = new ArrayList<>();
        commonFriends.add(user3);

        Assertions.assertEquals(commonFriends, userService.commonFriends(1,2));
    }
    @Test
    void commonFriends_firstIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, () -> userService.commonFriends(4, 1));
    }
    @Test
    void commonFriends_secondIdIncorrect(){
        Assertions.assertThrows(InvalidUserId.class, () -> userService.commonFriends(1, 4));
    }
    @Test
    void commonFriends_empty(){
        Assertions.assertEquals(new ArrayList<User>(), userService.commonFriends(1,2));
    }
}
