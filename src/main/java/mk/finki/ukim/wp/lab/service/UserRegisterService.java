package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Role;
import mk.finki.ukim.wp.lab.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserRegisterService extends UserDetailsService {

    User register(String username, String password, String repeatPassword, Role role);

}
