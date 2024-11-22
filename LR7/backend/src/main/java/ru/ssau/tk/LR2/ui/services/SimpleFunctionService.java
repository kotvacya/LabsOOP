package ru.ssau.tk.LR2.ui.services;

import org.reflections.Reflections;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.ui.annotations.SimpleFunction;
import ru.ssau.tk.LR2.ui.dto.SimpleFunctionDTO;
import ru.ssau.tk.LR2.ui.exceptions.ExceptionUtils;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchSimpleFunctionException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleFunctionService {
    static final String FUNCTIONS_PREFIX = "ru.ssau.tk.LR2.functions";

    public List<SimpleFunctionDTO> getAllSimpleFunctions(){
        Reflections reflections = new Reflections(FUNCTIONS_PREFIX);

        return reflections.getTypesAnnotatedWith(SimpleFunction.class).stream().map((func) -> {
            SimpleFunction annotation = func.getAnnotation(SimpleFunction.class);
            return new SimpleFunctionDTO(func.getSimpleName(), annotation.canonName());
        }).collect(Collectors.toList());
    }

    public MathFunction create(String classname) throws NoSuchSimpleFunctionException {
        try {
            Class<?> rawClass = Class.forName(FUNCTIONS_PREFIX + "." + classname);

            Class<? extends MathFunction> funcClass = rawClass.asSubclass(MathFunction.class);

            return funcClass.getConstructor().newInstance();
        }catch (ClassNotFoundException | ClassCastException | NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException exception){
            throw new NoSuchSimpleFunctionException(ExceptionUtils.exceptionMessageWithClass(exception));
        }
    }
}
