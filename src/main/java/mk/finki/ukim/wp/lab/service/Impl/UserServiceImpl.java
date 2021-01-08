package mk.finki.ukim.wp.lab.service.Impl;

import mk.finki.ukim.wp.lab.model.Role;
import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidUserCredentialsException;
import mk.finki.ukim.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password).orElseThrow(InvalidUserCredentialsException::new);
    }

    @Override
    public User register(String username, String password, String repeatPassword) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()){
            throw new InvalidArgumentException();
        }
        if (!password.equals(repeatPassword)) {
            throw new PasswordsDoNotMatchException();
        }

        if (userRepository.findByUsername(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }

        User user = new User(username, passwordEncoder.encode(password));
        return userRepository.save(user);
    }

}
