package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    void addUser(User user);
    List<User> listUsers();
    void deleteUser(Long id);
    User getUserById(Long id);
    void changeUser(User newUser);
}
