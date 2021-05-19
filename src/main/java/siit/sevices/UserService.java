package siit.sevices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siit.db.UserDao;
import siit.model.User;

import java.util.List;
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public List<User> getUserList(){
        List<User> users = userDao.getUsers();
        return users;
    }


}
