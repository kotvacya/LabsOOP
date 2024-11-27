package ru.ssau.tk.LR2.ui.storage;

import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.CompositeFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.dto.NamedCompositeFunctionDTO;
import ru.ssau.tk.LR2.ui.exceptions.CompositeFunctionAlreadyExists;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchCompositeFunctionException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InMemoryFunctionStorage implements TabulatedFunctionStorageInterface {
    Map<String, TabulatedFunctionFactory> current_factory = new HashMap<>();
    @Autowired
    CompositeFunctionHelper compositeHelper;
    Map<String, Map<String, CompositeFunction>> composite_functions = new HashMap<>();


    private String getKey(SecurityContext ctx) throws AuthException {
        Authentication auth = ctx.getAuthentication();
        if (auth.getDetails() instanceof WebAuthenticationDetails details) {
            return details.getSessionId();
        }
        throw new AuthException();
    }

    @Override
    public TabulatedFunctionFactory getCurrentFactory(SecurityContext ctx) throws AuthException {
        return current_factory.getOrDefault(getKey(ctx), new ArrayTabulatedFunctionFactory());
    }

    @Override
    public void setCurrentFactory(SecurityContext ctx, TabulatedFunctionFactory factory) throws AuthException {
        current_factory.put(getKey(ctx), factory);
    }

    @Override
    public List<NamedCompositeFunctionDTO> getCompositeFunctions(SecurityContext ctx) throws AuthException {
        return composite_functions.getOrDefault(getKey(ctx), new HashMap<>()).entrySet().stream().map((entry) -> new NamedCompositeFunctionDTO(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    @Override
    public CompositeFunction getCompositeFunction(SecurityContext ctx, String name) throws AuthException, NoSuchCompositeFunctionException {
        CompositeFunction function = composite_functions.getOrDefault(getKey(ctx), new HashMap<>()).get(name);
        if (Objects.nonNull(function)) {
            return function;
        } else {
            throw new NoSuchCompositeFunctionException("Composite function " + name + " doesn't exist");
        }
    }

    @Override
    public void addCompositeFunction(SecurityContext ctx, String name, String func1, String func2) throws AuthException, NoSuchCompositeFunctionException, CompositeFunctionAlreadyExists {
        String token = getKey(ctx);
        if(compositeHelper.isSimpleFunction(name)) throw new CompositeFunctionAlreadyExists("Такая простая функция уже существует");

        if (Objects.isNull(composite_functions.get(token))) composite_functions.put(token, new HashMap<>());
        Map<String, CompositeFunction> userData = composite_functions.get(token);
        if(Objects.isNull(userData.get(name))) {
            userData.put(name, compositeHelper.constructFunction(ctx, this, func1, func2));
        }else{
            throw new CompositeFunctionAlreadyExists("Такая функция уже существует!");

        }
    }


}
