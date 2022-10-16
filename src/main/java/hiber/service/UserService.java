package hiber.service;

//
// #ffe4e1
import hiber.model.User;

import java.util.List;

public interface UserService {

    void createUpdateUser(User user);

    void removeUser(int id);

    List<User> getAllUsers();

    User getUserById(int id);

}
