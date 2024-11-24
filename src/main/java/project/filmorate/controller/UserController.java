package project.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.filmorate.exception.ValidationException;
import project.filmorate.model.Film;
import project.filmorate.service.UserService;
import project.filmorate.storage.UserDbStorage;
import project.filmorate.storage.UserStorage;
import project.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;


    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/users/{userId}")
    public User getUser(@PathVariable Integer userId){
        return userService.getUser(userId);
    }

    @GetMapping("/users/{userId}/friends")
    public List<User> getUsersFriends(@PathVariable Integer userId){
        return userService.getUsersFriends(userId);
    }

    @GetMapping("/users/{userId}/likes")
    public List<Film> getLikedFilmsByUser(@PathVariable Integer userId){
        return userService.getLikedFilmsByUser(userId);
    }

    @PostMapping("/users")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @DeleteMapping("/users/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
    }
//    private boolean validation(User user){
//        if(!user.getEmail().isEmpty() && user.getEmail().contains("@") && !user.getLogin().isEmpty() &&
//        !user.getLogin().contains(" ") && user.getBirthday().isBefore(LocalDate.now())){
//            if(user.getName().isEmpty()){
//                user.setName(user.getLogin());
//            }
//            return true;
//        }else{
//            return false;
//
//        }
//    }
//
//    @PostMapping("/user")
//    public User create(@RequestBody User user){
//
//        if(validation(user)){
//            userStorage.addUser(user);
//            log.info("Пользователь добавлен: {}", user.toString());
//            return user;
//        }else{
//            throw new ValidationException("Invalid fields for user");
//        }
//
//    }
//
//    @PutMapping("/user")
//    public User update(@RequestBody User user){
//
//        if(validation(user)){
//            return userStorage.updateUser(user);
//        }else{
//            throw new ValidationException("Invalid fields for user");
//        }
//
//    }
//
//    @GetMapping("/users")
//    public ArrayList<User> allUsers(){
//        return userStorage.allUsers();
//    }
//
//    @PutMapping("/users/{id}/friends/{friendId}")
//    public User addFriend(@PathVariable int id, @PathVariable int friendId){
//        return userService.addFriend(id, friendId);
//    }
//
//    @DeleteMapping("/users/{id}/friends/{friendId}")
//    public User deleteFriend(@PathVariable int id, @PathVariable int friendId){
//        return userService.deleteFriend(id, friendId);
//    }
//
//    @GetMapping("/users/{id}/friends")
//    public ArrayList<User> friends(@PathVariable int id){
//        return userService.allFriends(id);
//    }
//
//    @GetMapping("/users/{id}/friends/common/{otherId}")
//    public ArrayList<User> commonFriends(@PathVariable int id, @PathVariable int otherId){
//        return userService.commonFriends(id, otherId);
//    }
//
//    @GetMapping("/users/{id}")
//    public User user(@PathVariable int id){
//        return userStorage.getUser(id);
//    }
}
