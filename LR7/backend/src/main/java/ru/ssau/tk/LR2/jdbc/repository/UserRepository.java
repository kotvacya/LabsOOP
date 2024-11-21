package ru.ssau.tk.LR2.jdbc.repository;

import org.komamitsu.spring.data.sqlite.SqliteRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jdbc.model.User;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends SqliteRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);

    @Query(name = "User.getUsers")
    List<User> getUsers();

    @Modifying
    @Query(name = "User.deleteByUsername")
    void deleteByUsername(@Param("username") String username);

    @Modifying
    @Query(name = "User.updateByUsername")
    void updateByUsername(@Param("username") String username, @Param("password") String password);
}
