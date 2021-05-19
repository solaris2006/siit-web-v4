package siit.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import siit.model.Customer;
import siit.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users",
                this::getUser);

    }

    private User getUser(ResultSet resultSet, int rowNum) throws SQLException {
        User user = new User();

        user.setName(resultSet.getString("user_name"));
        user.setPass(resultSet.getString("password"));

        return user;
    }

}
