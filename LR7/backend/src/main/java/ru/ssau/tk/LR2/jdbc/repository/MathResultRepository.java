package ru.ssau.tk.LR2.jdbc.repository;

import org.komamitsu.spring.data.sqlite.SqliteRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jdbc.model.MathResult;

import java.util.List;

@Transactional
@Repository
public interface MathResultRepository extends SqliteRepository<MathResult, Integer> {
    List<MathResult> findByHash(long hash);
    MathResult findByXAndHash(double x, long hash);

    @Modifying
    @Query(name = "MathResult.update")
    void updateYByXAndHash(@Param("x") double x, @Param("hash") long hash, @Param("y") double y);

    @Query(name = "MathResult.findByHashSortedByX")
    List<MathResult> findByHashSortedByX(@Param("hash") long hash);

    @Query(name = "MathResult.getCount")
    int getCount();

    @Modifying
    @Query(name = "MathResult.deleteAll")
    void deleteAll();

//    @Modifying
    @Query(name = "MathResult.deleteByXAndHash")
    List<MathResult> deleteByXAndHash(@Param("x") double x, @Param("hash") long hash);

//    @Modifying
    @Query(name = "MathResult.deleteByHash")
    List<MathResult> deleteByHash(@Param("hash") long hash);
}
