package ru.ssau.tk.LR2.web.service;

import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.jpa.repository.LogRepository;

@Service
@AllArgsConstructor
public class LogService {
    @Delegate(types = LogRepository.class)
    private LogRepository mathRepo;
}
