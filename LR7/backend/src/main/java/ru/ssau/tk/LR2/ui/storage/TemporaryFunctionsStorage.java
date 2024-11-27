package ru.ssau.tk.LR2.ui.storage;

import jakarta.security.auth.message.AuthException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.exceptions.OperandIndexException;

import javax.annotation.Nullable;
import java.util.*;

@Service
public class TemporaryFunctionsStorage {
    Map<String, TabulatedFunction> current_functions = new HashMap<>();
    Map<String, List<TabulatedFunction>> operands_store = new HashMap<>();

    private String getKey(SecurityContext ctx) throws AuthException {
        Authentication auth = ctx.getAuthentication();
        if (auth.getDetails() instanceof WebAuthenticationDetails details) {
            return details.getSessionId();
        }
        throw new AuthException();
    }

    private List<TabulatedFunction> getOperandsOrDefault(String key) {
        if (!operands_store.containsKey(key)) {
            operands_store.put(key, new ArrayList<>(Collections.nCopies(4, null)));
        }
        return operands_store.get(key);
    }

    @Nullable
    public TabulatedFunction getCurrentFunction(SecurityContext ctx) throws AuthException {
        return current_functions.get(getKey(ctx));
    }

    public void setCurrentFunction(SecurityContext ctx, @Nullable TabulatedFunction function) throws AuthException {
        current_functions.put(getKey(ctx), function);
    }

    @Nullable
    public TabulatedFunction getOperand(SecurityContext ctx, int index) throws AuthException, BaseUIException {
        try {
            return getOperandsOrDefault(getKey(ctx)).get(index);
        } catch (IndexOutOfBoundsException e) {
            throw new OperandIndexException();
        }
    }

    public void setOperand(SecurityContext ctx, int index, @Nullable TabulatedFunction function) throws AuthException, BaseUIException {
        try {
            getOperandsOrDefault(getKey(ctx)).set(index, function);
        } catch (IndexOutOfBoundsException e) {
            throw new OperandIndexException();
        }
    }

    public List<TabulatedFunction> getOperands(SecurityContext ctx) throws AuthException {
        return getOperandsOrDefault(getKey(ctx));
    }
}
