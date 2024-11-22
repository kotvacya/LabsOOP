package ru.ssau.tk.LR2.jpa;

import org.springframework.stereotype.Component;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.hash.HasherFactory;
import ru.ssau.tk.LR2.jpa.repository.MathResultRepository;

@Component
public class CachedMathFunctionFactory {
    private final MathResultRepository repository;
    private final HasherFactory factory;

    public CachedMathFunctionFactory(MathResultRepository repo, HasherFactory hash_factory) {
        repository = repo;
        factory = hash_factory;
    }

    public CachedMathFunction create(MathFunction func) {
        return new CachedMathFunction(repository, func, factory);
    }
}
