package ru.ssau.tk.LR2.jdbc;

import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.hash.Hasher;
import ru.ssau.tk.LR2.hash.HasherFactory;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.jdbc.repository.MathResultRepository;

public class CachedMathFunction implements MathFunction {

    private final MathResultRepository repository;
    private final MathFunction function;
    private final long function_hash;

    public CachedMathFunction(MathResultRepository repo, MathFunction func, HasherFactory factory) {
        repository = repo;
        function = func;
        Hasher hasher = factory.create();
        function_hash = function.hash(hasher);
    }

    public long getHash() {
        return function_hash;
    }

    @Override
    public double apply(double x) {
        MathResult res = repository.findByXAndHash(x, function_hash);

        if (res != null) return res.getY();
        double y = function.apply(x);
        repository.insert(new MathResult(x, y, function_hash));
        return y;
    }

    @Override
    public long hash(Hasher h) {
        return function.hash(h);
    }
}
