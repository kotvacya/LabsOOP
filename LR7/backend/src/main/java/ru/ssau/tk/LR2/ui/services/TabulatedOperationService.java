package ru.ssau.tk.LR2.ui.services;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.Scanners;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.LR2.ui.annotations.TabulatedOperation;
import ru.ssau.tk.LR2.ui.dto.OperationDTO;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.exceptions.ExceptionUtils;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchOperationException;
import ru.ssau.tk.LR2.ui.exceptions.OperationFailedException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@Service
public class TabulatedOperationService {
    public List<OperationDTO> getAllOperations(){
        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setUrls(ClasspathHelper.forClass(TabulatedFunctionOperationService.class))
                .setScanners(Scanners.MethodsAnnotated));
        return reflections.getMethodsAnnotatedWith(TabulatedOperation.class).stream().map( (method) -> new OperationDTO(method.getName(), method.getAnnotation(TabulatedOperation.class).symbol()) ).toList();
    }

    public TabulatedFunction apply(String operation, TabulatedFunctionOperationService service, TabulatedFunction op1, TabulatedFunction op2) throws BaseUIException {
        try {
            Method method = TabulatedFunctionOperationService.class.getMethod(operation, TabulatedFunction.class, TabulatedFunction.class);

            return (TabulatedFunction) method.invoke(service, op1, op2);
        }catch (NoSuchMethodException | IllegalAccessException e) {
            throw new NoSuchOperationException(ExceptionUtils.exceptionMessageWithClass(e));
        }catch (InvocationTargetException e){
            throw new OperationFailedException(ExceptionUtils.exceptionMessageWithClass(e.getCause()));
        }
    }
}
