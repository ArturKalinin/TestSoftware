package project.filmorate.storage;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import project.filmorate.model.Film;
import project.filmorate.model.User;
import project.filmorate.storage.mapper.FilmMapper;
import project.filmorate.storage.mapper.UserMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDbStorage implements UserStorage{

    private JdbcTemplate jdbcTemplate;

    public UserDbStorage(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public void addUser(User user) {
        String sql = "INSERT INTO users(email, login, username, birthdate) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, user.getEmail(), user.getLogin(), user.getName(), user.getBirthday());
    }

    @Override
    public void deleteUser(Integer userId) {
        String sql = "DELETE FROM users WHERE userid = ?";
        jdbcTemplate.update(sql, userId);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public ArrayList<User> allUsers() {
        return null;
    }

    @Override
    public User getUser(int userId) {
        String sql = "select * from users where userId = ?";
        return jdbcTemplate.query(sql, new UserMapper() ,userId).get(0);

    }

    @Override
    public List<User> getUsersFriends(Integer userId){
        String sql = "select * from users u join friendrequest fr on (fr.sender = u.userId or fr.taker = u.userid) where accept = true and (fr.sender = ? or fr.taker = ?) and u.userId != ?";
        return jdbcTemplate.query(sql, new UserMapper(), userId, userId, userId);
    }

    @Override
    public List<Film> getLikedFilmsByUser(Integer userId) {
        String sql = "SELECT * FROM users u JOIN likedfilmbyuser lfu on u.userid = lfu.userid JOIN film f on f.filmid = lfu.filmid JOIN genre on f.genreId = genre.genreid join mpa on mpa.mpaid = f.mpaid WHERE u.userid = ?";
        return jdbcTemplate.query(sql, new FilmMapper(jdbcTemplate), userId);
    }



    @Override
    public boolean isUserExist(int id) {
        return false;
    }

//    private User makeUser(ResultSet rs) throws SQLException {
//        Integer userId = rs.getInt("userId");
//        String email = rs.getString("email");
//        String login = rs.getString("login");
//        String username = rs.getString("username");
//        LocalDate birthdate = rs.getDate("birthdate").toLocalDate();
//        User user = new User(userId, email, login, username, birthdate);
//        return user;
//    }
}
