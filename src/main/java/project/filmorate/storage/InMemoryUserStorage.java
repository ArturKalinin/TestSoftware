//package project.filmorate.storage;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import project.filmorate.exception.InvalidUser;
//import project.filmorate.exception.InvalidUserId;
//import project.filmorate.model.User;
//
//import java.util.ArrayList;
//
//@Component
//@Slf4j
//public class InMemoryUserStorage implements UserStorage{
//
//    ArrayList<User> users = new ArrayList<>();
//
//    @Override
//    public User addUser(User user) {
//        users.add(user);
//        return user;
//    }
//
//    @Override
//    public User deleteUser(User user) {
//        if (users.contains(user)){
//            users.remove(user);
//            return user;
//        }else {
//            throw new InvalidUser("User doesn't found");
//        }
//    }
//
//    @Override
//    public User updateUser(User user) {
//        for(int i = 0; i < users.size(); i++){
//            if(user.getId() == users.get(i).getId()){
//                users.set(i, user);
//                log.info("Пользователь {} обновлен: {}", user.getId(), user.toString());
//                return user;
//            }
//        }
//
//        users.add(user);
//        log.info("Пользователь добавлен: {}", user.toString());
//        return user;
//
//    }
//
//    public ArrayList<User> allUsers(){
//        return users;
//    }
//
//    public User getUser(int id){
//        for (int i = 0; i < users.size(); i++) {
//            if(users.get(i).getId() == id){
//                return users.get(i);
//            }
//        }
//        throw new InvalidUserId("User with id " + id + " doesn't found");
//    }
//
//    public boolean isUserExist(int id){
//        for (int i = 0; i < users.size(); i++) {
//            if(users.get(i).getId() == id){
//                return true;
//            }
//        }
//        return false;
//    }
//}
