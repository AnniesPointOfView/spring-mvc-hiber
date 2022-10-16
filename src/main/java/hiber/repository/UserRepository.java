package hiber.repository;


import hiber.model.User;

import java.util.List;

public interface UserRepository {

    List<User> getAllUsers();

    void createUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

}
