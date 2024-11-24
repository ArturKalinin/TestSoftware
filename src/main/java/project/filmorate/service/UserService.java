package project.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.filmorate.exception.FriendsException;
import project.filmorate.exception.InvalidUserId;
import project.filmorate.model.Film;
import project.filmorate.model.User;
import project.filmorate.storage.UserDbStorage;
import project.filmorate.storage.UserStorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserStorage userStorage;

    @Autowired
    public UserService(UserDbStorage userStorage){
        this.userStorage = userStorage;
    }

    public User getUser(Integer userId){
        return userStorage.getUser(userId);
    }

    public List<User> getUsersFriends(Integer userId){
        return userStorage.getUsersFriends(userId);
    }

    public List<Film> getLikedFilmsByUser(Integer userId){
        return userStorage.getLikedFilmsByUser(userId);
    }

    public void addUser(User user){
        userStorage.addUser(user);
    }

    public void deleteUser(Integer userId){
        userStorage.deleteUser(userId);
    }

//    public User addFriend(int id, int friendId){
//        ArrayList<User> users =  userStorage.allUsers();
//
//        if(id == friendId){
//            throw new FriendsException("You can't add yourself as a friend");
//        }
//
//        for (int j = 0; j < users.size(); j++){
//            if(users.get(j).getId() == friendId){
//                for (int i = 0; i < users.size(); i++){
//                    if(users.get(i).getId() == id){
//                        User user = users.get(i);
//                        User friend = users.get(j);
//                        if(!user.getFriends().contains(friendId)){
//                            user.getFriends().add(friendId);
//                            friend.getFriends().add(id);
//                            return user;
//                        }else{
//                            throw new FriendsException("This user is already your friend");
//                        }
//                    }
//                }
//                throw new InvalidUserId("User with id " + id + " doesn't found");
//            }
//        }
//        throw new InvalidUserId("User with id " + friendId + " doesn't found");
//
//    }
//
//    public User deleteFriend(int id, int friendId){
//        ArrayList<User> users = userStorage.allUsers();
//        if(id == friendId){
//            throw new FriendsException("You can't delete yourself from friends");
//        }
//        for (int j = 0; j < users.size(); j++){
//            if(users.get(j).getId() == friendId){
//                for (int i = 0; i < users.size(); i++){
//                    if(users.get(i).getId() == id){
//                        User user = users.get(i);
//                        User friend = users.get(j);
//                        if(user.getFriends().contains(friendId)){
//                            user.getFriends().remove(friendId);
//                            friend.getFriends().remove(id);
//                            return user;
//                        }else{
//                            throw new FriendsException("This user isn't your friend");
//                        }
//                    }
//                }
//                throw new InvalidUserId("User with id " + id + " doesn't found");
//            }
//        }
//        throw new InvalidUserId("User with id " + friendId + " doesn't found");
//    }
//
//    public ArrayList<User> allFriends(int id){
//        ArrayList<User> users = userStorage.allUsers();
//        Set<Integer> friendsId = new HashSet<>();
//        ArrayList<User> friends = new ArrayList<>();
//        boolean isIdExist = false;
//
//        for (int i = 0; i < users.size(); i++) {
//            if(users.get(i).getId() == id){
//                friendsId = users.get(i).getFriends();
//                isIdExist = true;
//            }
//        }
//        if(!isIdExist){
//            throw new InvalidUserId("User with id " + id + " doesn't found");
//        }
//        if(friendsId.isEmpty()){
//            return friends;
//        }
//
//        for (int i = 0; i < users.size(); i++) {
//            if(friendsId.contains(users.get(i).getId())){
//                friends.add(users.get(i));
//            }
//        }
//        return friends;
//    }
//
//    public ArrayList<User> commonFriends(int id, int otherId){
//        ArrayList<User> users = userStorage.allUsers();
//        ArrayList<Integer> friendsId = new ArrayList<>();
//        ArrayList<Integer> friendsOtherId = new ArrayList<>();
//        ArrayList<Integer> commonFriendsId = new ArrayList<>();
//        ArrayList<User> commonFriends = new ArrayList<>();
//
//        boolean isIdExist = false;
//        boolean isOtherIdExist = false;
//
//        for (int i = 0; i < users.size(); i++) {
//            User user = users.get(i);
//            if(user.getId() == id){
//                friendsId = new ArrayList<>(user.getFriends());
//                isIdExist = true;
//            } else if (user.getId() == otherId) {
//                friendsOtherId = new ArrayList<>(user.getFriends());
//                isOtherIdExist = true;
//            }
//        }
//
//        if(!isIdExist){
//            throw new InvalidUserId("User with id " + id + " doesn't found");
//        }
//        if(!isOtherIdExist){
//            throw new InvalidUserId("User with id " + otherId + " doesn't found");
//        }
//
//        if(friendsId.isEmpty() || friendsOtherId.isEmpty()){
//            return commonFriends;
//        }
//
//        for (int i = 0; i < friendsId.size(); i++) {
//            if(friendsOtherId.contains(friendsId.get(i))){
//                commonFriendsId.add(friendsId.get(i));
//            }
//        }
//
//        for (int i = 0; i < users.size(); i++) {
//            if(commonFriendsId.contains(users.get(i).getId())){
//                commonFriends.add(users.get(i));
//            }
//
//        }
//
//        return commonFriends;
//    }


}
