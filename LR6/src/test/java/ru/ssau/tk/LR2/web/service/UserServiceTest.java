package ru.ssau.tk.LR2.web.service;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.mockito.Mockito;
import ru.ssau.tk.LR2.jdbc.model.User;
import ru.ssau.tk.LR2.jdbc.repository.UserRepository;

public class UserServiceTest extends TestCase {

    public void testService(){
        UserRepository mock_repo = Mockito.mock(UserRepository.class);

        Mockito.when(mock_repo.findByUsername("test")).thenReturn(new User("test", "password"));
        Mockito.when(mock_repo.findByUsername("test2")).thenReturn(new User("test2", "password2"));

        UserService service = new UserService(mock_repo);

        User nu = new User("test", "password");

        service.createUser(nu);

        Mockito.verify(mock_repo).insert(nu);

        nu.setPassword("password2");

        service.updateUser(nu);

        Mockito.verify(mock_repo).updateByUsername(nu.getUsername(), nu.getPassword());

        service.deleteUser(nu.getUsername());

        Mockito.verify(mock_repo).deleteByUsername(nu.getUsername());

        service.userExists(nu.getUsername());

        Mockito.verify(mock_repo).findByUsername(nu.getUsername());

        service.loadUserByUsername(nu.getUsername());

        Mockito.verify(mock_repo, Mockito.times(2)).findByUsername(nu.getUsername());
    }

    public UserServiceTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(UserServiceTest.class);
    }
}