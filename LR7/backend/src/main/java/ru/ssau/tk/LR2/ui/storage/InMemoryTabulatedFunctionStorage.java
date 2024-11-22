package ru.ssau.tk.LR2.ui.storage;

import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InMemoryTabulatedFunctionStorage implements TabulatedFunctionStorageInterface {
    Map<String, List<TabulatedFunction>> tabulated_store = new HashMap<>();

    Map<String, TabulatedFunctionFactory> current_factory = new HashMap<>();

    private String getKey(SecurityContext ctx) throws AuthException{
        Authentication auth = ctx.getAuthentication();
        if(auth.getDetails() instanceof WebAuthenticationDetails details){
            return details.getSessionId();
        }
        throw new AuthException();
    }

    @Override
    public List<TabulatedFunction> getTabulatedFunctions(SecurityContext ctx) throws AuthException {
        return List.of();
    }

    @Override
    public TabulatedFunctionFactory getCurrentFactory(SecurityContext ctx) throws AuthException {
        return current_factory.getOrDefault(getKey(ctx), new ArrayTabulatedFunctionFactory());
    }

    @Override
    public void setCurrentFactory(SecurityContext ctx, TabulatedFunctionFactory factory) throws AuthException {
        current_factory.put(getKey(ctx), factory);
    }

}
