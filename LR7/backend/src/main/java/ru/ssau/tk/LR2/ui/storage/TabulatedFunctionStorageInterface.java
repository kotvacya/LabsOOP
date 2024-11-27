package ru.ssau.tk.LR2.ui.storage;

import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.context.SecurityContext;
import ru.ssau.tk.LR2.functions.CompositeFunction;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.dto.NamedCompositeFunctionDTO;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchCompositeFunctionException;

import java.util.List;

public interface TabulatedFunctionStorageInterface {

    TabulatedFunctionFactory getCurrentFactory(SecurityContext ctx) throws AuthException;
    void setCurrentFactory(SecurityContext ctx, TabulatedFunctionFactory factory) throws AuthException;

    List<NamedCompositeFunctionDTO> getCompositeFunctions(SecurityContext ctx) throws AuthException;
    CompositeFunction getCompositeFunction(SecurityContext ctx, String name) throws AuthException, NoSuchCompositeFunctionException;
    void addCompositeFunction(SecurityContext ctx, String name, String func1, String func2) throws AuthException, NoSuchCompositeFunctionException;

}
