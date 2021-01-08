package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Role;
import mk.finki.ukim.wp.lab.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserById(Long id);
    Optional<User> findUserByUsername(String username);
    User login(String username, String password);
    User register(String username, String password, String repeatPassword);

}
