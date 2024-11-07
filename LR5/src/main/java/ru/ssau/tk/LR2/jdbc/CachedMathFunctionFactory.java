package ru.ssau.tk.LR2.jdbc;

import org.springframework.stereotype.Component;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.hash.HasherFactory;
import ru.ssau.tk.LR2.jdbc.repository.MathResultRepository;

@Component
public class CachedMathFunctionFactory {
    MathResultRepository repository;
    HasherFactory factory;

    public CachedMathFunctionFactory(MathResultRepository repo, HasherFactory hash_factory){
        repository = repo;
        factory = hash_factory;
    }

    public CachedMathFunction create(MathFunction func){
        return new CachedMathFunction(repository, func, factory);
    }
}
