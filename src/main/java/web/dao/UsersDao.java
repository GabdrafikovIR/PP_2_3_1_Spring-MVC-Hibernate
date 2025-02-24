package web.dao;


import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public interface UsersDao {

    void save(User user);

    List<User> index();

    void update(int id, User user);

    void delete(int id);

    User show(int id);

}
