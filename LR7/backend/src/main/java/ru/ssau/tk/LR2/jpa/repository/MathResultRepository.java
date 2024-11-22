package ru.ssau.tk.LR2.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jpa.model.MathResult;

import java.util.List;

@Transactional
@Repository
public interface MathResultRepository extends CrudRepository<MathResult, Integer> {
    List<MathResult> findByHash(long hash);
    MathResult findByXAndHash(double x, long hash);

    @Modifying
    @Query(nativeQuery = true)
    void updateYByXAndHash(@Param("x") double x, @Param("hash") long hash, @Param("y") double y);

    List<MathResult> findByHashOrderByXDesc(@Param("hash") long hash);

    List<MathResult> deleteByXAndHash(@Param("x") double x, @Param("hash") long hash);

    List<MathResult> deleteByHash(@Param("hash") long hash);
}
