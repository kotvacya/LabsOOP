package ru.ssau.tk.LR2.ui.storage;

import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.CompositeFunction;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchCompositeFunctionException;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchSimpleFunctionException;
import ru.ssau.tk.LR2.ui.services.SimpleFunctionService;

@Service
@AllArgsConstructor
public class CompositeFunctionHelper {
    SimpleFunctionService functionService;

    public CompositeFunction constructFunction(SecurityContext ctx, TabulatedFunctionStorageInterface storageInterface, String func1, String func2) throws AuthException, NoSuchCompositeFunctionException {
        MathFunction mathFunction1;
        MathFunction mathFunction2;

        try {
            mathFunction1 = functionService.create(func1);
        } catch (NoSuchSimpleFunctionException e) {
            mathFunction1 = storageInterface.getCompositeFunction(ctx, func1);
        }

        try {
            mathFunction2 = functionService.create(func2);
        } catch (NoSuchSimpleFunctionException e) {
            mathFunction2 = storageInterface.getCompositeFunction(ctx, func2);
        }

        return new CompositeFunction(mathFunction1, mathFunction2);
    }

    public boolean isSimpleFunction(String name) {
        try {
            functionService.create(name);
            return true;
        } catch (NoSuchSimpleFunctionException e) {
            return false;
        }
    }
}
