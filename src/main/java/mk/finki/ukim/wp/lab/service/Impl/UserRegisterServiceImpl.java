package mk.finki.ukim.wp.lab.service.Impl;

import mk.finki.ukim.wp.lab.model.Role;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.model.exceptions.UserNotFoundException;
import mk.finki.ukim.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.UserRegisterService;
import mk.finki.ukim.wp.lab.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserRegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPassword, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username, passwordEncoder.encode(password), role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s).orElseThrow(() -> new UserNotFoundException(s));
    }
}
