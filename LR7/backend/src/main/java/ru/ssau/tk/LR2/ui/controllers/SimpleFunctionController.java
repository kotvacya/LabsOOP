package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.LR2.ui.dto.*;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.services.SimpleFunctionService;
import ru.ssau.tk.LR2.ui.storage.FunctionStorageFactory;
import ru.ssau.tk.LR2.ui.storage.TabulatedFunctionStorageInterface;

import java.util.List;

@RestController
@RequestMapping("/tabulated/simple")
public class SimpleFunctionController {

    @Autowired
    SimpleFunctionService functionService;

    @Autowired
    FunctionStorageFactory storage_factory;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();


    @GetMapping()
    public List<SimpleFunctionDTO> getSimpleFunctions() throws AuthException{
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunctionStorageInterface storage = storage_factory.getStorage(ctx);

        List<SimpleFunctionDTO> res = functionService.getAllSimpleFunctions();
        for(NamedCompositeFunctionDTO functionDTO : storage.getCompositeFunctions(ctx)){
            res.add(new SimpleFunctionDTO(functionDTO.getName(), functionDTO.getName()));
        }
        return res;
    }

    @PostMapping("/composite")
    public void newCompositeFunction(@Validated @NonNull @RequestBody CompositeFunctionRequestDTO requestDTO) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunctionStorageInterface storage = storage_factory.getStorage(ctx);

        storage.addCompositeFunction(securityContextHolderStrategy.getContext(), requestDTO.getName(), requestDTO.getOuter(), requestDTO.getInner());
    }

}


