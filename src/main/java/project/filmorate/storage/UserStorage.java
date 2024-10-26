package project.filmorate.storage;

import project.filmorate.model.User;

import java.util.ArrayList;

public interface UserStorage {
    User addUser(User user);
    User deleteUser(User user);

    User updateUser(User user);
    ArrayList<User> allUsers();

    User getUser(int id);

    boolean isUserExist(int id);
}
