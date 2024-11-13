package ru.ssau.tk.LR2.web.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.ssau.tk.LR2.jdbc.model.User;
import ru.ssau.tk.LR2.jdbc.repository.UserRepository;

import java.util.Objects;

@AllArgsConstructor
public class UserService implements UserDetailsManager {

    UserRepository user_repo;

    @Override
    public void createUser(UserDetails user) {
        user_repo.insert(new User(user.getUsername(), user.getPassword()));
    }

    @Override
    public void updateUser(UserDetails user) {
        user_repo.updateByUsername(user.getUsername(), user.getPassword());
    }

    @Override
    public void deleteUser(String username) {
        user_repo.deleteByUsername(username.toLowerCase());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean userExists(String username) {
        return Objects.nonNull(user_repo.findByUsername(username));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return user_repo.findByUsername(username);
    }
}
