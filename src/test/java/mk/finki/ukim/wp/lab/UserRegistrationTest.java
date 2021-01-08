package mk.finki.ukim.wp.lab;

import mk.finki.ukim.wp.lab.model.User;
import mk.finki.ukim.wp.lab.model.exceptions.InvalidArgumentException;
import mk.finki.ukim.wp.lab.model.exceptions.PasswordsDoNotMatchException;
import mk.finki.ukim.wp.lab.model.exceptions.UsernameAlreadyExistsException;
import mk.finki.ukim.wp.lab.repository.jpa.UserRepository;
import mk.finki.ukim.wp.lab.service.Impl.UserServiceImpl;
import mk.finki.ukim.wp.lab.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @Before
    public void init(){

        MockitoAnnotations.initMocks(this);
        User user = new User("user", "password");
        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        this.userService = Mockito.spy(new UserServiceImpl(userRepository, passwordEncoder));

    }

    @Test
    public void testSuccessfulRegistration(){

        User user = this.userService.register("user", "password", "password");
        Mockito.verify(this.userService).register("user", "password", "password");

        Assert.assertNotNull("User is null", user);
        Assert.assertEquals("username do not match", "user", user.getUsername());
        Assert.assertEquals("password do not match", "password", user.getPassword());

    }

    @Test
    public void testEmptyUsername(){
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> userService.register("", "password", "password"));
        Mockito.verify(userService).register("", "password", "password");
    }

    @Test
    public void testPasswordIsEmpty(){
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> userService.register("user", "", "password"));
        Mockito.verify(userService).register("user", "", "password");
    }

    @Test
    public void testUsernameNull(){
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> userService.register(null, "password", "password"));
        Mockito.verify(this.userService).register(null, "password", "password");

    }

    @Test
    public void testPasswordNull(){
        Assert.assertThrows("InvalidArgumentException expected",
                InvalidArgumentException.class,
                () -> userService.register("user", null, "password"));
        Mockito.verify(userService).register("user", null, "password");
    }

    @Test
    public void testPasswordMissMatch(){
        Assert.assertThrows("PasswordsDoNotMatchException expected",
                PasswordsDoNotMatchException.class,
                () -> userService.register("user", "p1", "p2"));
        Mockito.verify(userService).register("user", "p1", "p2");
    }

    @Test
    public void testForNoDuplicateUsernames(){

        User user = new User("username", "password");
        Mockito.when(userRepository.findByUsername(Mockito.anyString())).thenReturn(Optional.of(user));

        String username = "username";

        Assert.assertThrows("UsernameAlreadyExistsException expected",
                UsernameAlreadyExistsException.class,
                () -> userService.register(username, "password", "password"));
        Mockito.verify(userService).register(username, "password", "password");

    }


}
