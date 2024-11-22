package ru.ssau.tk.LR2.ui.storage;

import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.context.SecurityContext;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;

import javax.annotation.Nullable;
import java.util.List;

public interface TabulatedFunctionStorageInterface {

    List<TabulatedFunction> getTabulatedFunctions(SecurityContext ctx) throws AuthException;
    @Nullable
    TabulatedFunction getCurrentFunction(SecurityContext ctx) throws AuthException;
    void setCurrentFunction(SecurityContext ctx, @Nullable TabulatedFunction function) throws AuthException;
    TabulatedFunctionFactory getCurrentFactory(SecurityContext ctx) throws AuthException;
    void setCurrentFactory(SecurityContext ctx, TabulatedFunctionFactory factory) throws AuthException;

}
