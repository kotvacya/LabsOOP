package ru.ssau.tk.LR2.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.ssau.tk.LR2.jpa.model.CompositeFuncEntity;

@Transactional
@Repository
public interface CompositeFuncRepository extends JpaRepository<CompositeFuncEntity, Integer> {

}
