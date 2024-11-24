package project.filmorate.storage.mapper;

import org.springframework.jdbc.core.RowMapper;
import project.filmorate.model.User;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(rs.getInt("userId"),
                rs.getString("email"),
                rs.getString("login"),
                rs.getString("username"),
                rs.getDate("birthdate").toLocalDate());
    }
}
