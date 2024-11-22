package ru.ssau.tk.LR2.ui.services;

import org.reflections.Reflections;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.annotations.TabulatedFactory;
import ru.ssau.tk.LR2.ui.dto.TabulatedFactoryDTO;
import ru.ssau.tk.LR2.ui.exceptions.ExceptionUtils;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchSimpleFunctionException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TabulatedFactoryService {
    static final String FACTORY_PREFIX = "ru.ssau.tk.LR2.functions.factory";

    public List<TabulatedFactoryDTO> getAllFactories(){
        Reflections reflections = new Reflections(FACTORY_PREFIX);

        return reflections.getTypesAnnotatedWith(TabulatedFactory.class).stream().map((func) -> {
            TabulatedFactory annotation = func.getAnnotation(TabulatedFactory.class);
            return new TabulatedFactoryDTO(func.getSimpleName(), annotation.canonName());
        }).collect(Collectors.toList());
    }

    public TabulatedFunctionFactory create(String classname) throws NoSuchSimpleFunctionException {
        try {
            Class<?> rawClass = Class.forName(FACTORY_PREFIX + "." + classname);

            Class<? extends TabulatedFunctionFactory> funcClass = rawClass.asSubclass(TabulatedFunctionFactory.class);

            return funcClass.getConstructor().newInstance();
        }catch (ClassNotFoundException | ClassCastException | NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException exception){
            throw new NoSuchSimpleFunctionException(ExceptionUtils.exceptionMessageWithClass(exception));
        }
    }
}
