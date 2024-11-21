package ru.ssau.tk.LR2.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jpa.model.Log;

import java.sql.Timestamp;
import java.util.List;

@Transactional
@Repository
public interface LogRepository extends CrudRepository<Log, Integer> {
//    Log findById(int id);

    @Modifying
    @Query(nativeQuery = true)
    void updateTextAndTsById(@Param("id") int id, @Param("text") String text, @Param("ts") Timestamp ts);

    List<Log> findAllByOrderByTsAsc();

    void deleteById(@Param("id") int id);
}
