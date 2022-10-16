package hiber.service;

import hiber.model.User;
import hiber.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    @Override
    public void createUpdateUser(User user) {
        if (user.getId() == 0) {
            createUser(user);
        } else {
            updateUser(user);
        }
    }
    @Transactional
    public void createUser(User user) {
        userRepo.createUser(user);
    }

    @Transactional
    public void updateUser(User user) {
        userRepo.updateUser(user);
    }

    @Transactional
    @Override
    public void removeUser(int id) {
        User user = null;
        try {
            userRepo.removeUser(id);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.getAllUsers();
    }

    @Override
    public User getUserById(int id) {
        return userRepo.getUserById(id);
    }
}
