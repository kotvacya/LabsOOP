package ru.ssau.tk.LR2.web.service;


import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.jdbc.repository.MathResultRepository;

@Service
@AllArgsConstructor
public class MathService {
    @Delegate(types = MathResultRepository.class)
    private MathResultRepository logRepo;
}