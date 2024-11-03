package ru.ssau.tk.LR2.jdbc.repository;

import org.komamitsu.spring.data.sqlite.SqliteRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jdbc.model.MathResult;

import java.util.List;

@Transactional
@Repository
public interface MathResultRepository extends SqliteRepository<MathResult, Integer> {
    List<MathResult> findByHash(long hash);
    MathResult findByXAndHash(double x, long hash);
}
