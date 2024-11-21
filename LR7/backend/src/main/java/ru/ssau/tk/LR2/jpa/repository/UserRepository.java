package ru.ssau.tk.LR2.jpa.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jpa.model.User;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findById(int id);
    User findByUsername(String username);

    void deleteByUsername(@Param("username") String username);

    //@Query
    //void updateByUsername(@Param("username") String username, @Param("password") String password);
}
