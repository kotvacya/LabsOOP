package ru.ssau.tk.LR2.ui.storage;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FunctionStorageFactory {
    InMemoryFunctionStorage inMemoryFunctionStorage;

    public TabulatedFunctionStorageInterface getStorage(SecurityContext ctx){
//        System.out.println(ctx.getAuthentication().getPrincipal());
        return inMemoryFunctionStorage;
    }
}
