package project.filmorate.storage;

import project.filmorate.model.Film;
import project.filmorate.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserStorage {
    void addUser(User user);
    void deleteUser(Integer userId);

    User updateUser(User user);
    ArrayList<User> allUsers();

    User getUser(int id);

    List<User> getUsersFriends(Integer userId);
    List<Film> getLikedFilmsByUser(Integer userId);
    boolean isUserExist(int id);
}
