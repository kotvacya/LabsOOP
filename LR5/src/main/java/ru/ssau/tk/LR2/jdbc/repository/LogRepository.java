package ru.ssau.tk.LR2.jdbc.repository;

import org.komamitsu.spring.data.sqlite.SqliteRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jdbc.model.Log;

import java.sql.Timestamp;
import java.util.List;

@Transactional
@Repository
public interface LogRepository extends SqliteRepository<Log, Integer> {
    Log findById(int id);

    @Modifying
    @Query(name = "Log.update")
    void updateTextAndTsById(@Param("id") int id, @Param("text") String text, @Param("ts") Timestamp ts);

    @Query(name = "Log.findSortedByTimestamp")
    List<Log> findSortedByTimestamp();

    @Query(name = "Log.getCount")
    int getCount();

    @Modifying
    @Query(name = "Log.deleteAll")
    void deleteAll();

    @Modifying
    @Query(name = "Log.delete")
    void delete(@Param("id") int id);
}
