package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.LR2.ui.dto.TabulatedFactoryDTO;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.services.TabulatedFactoryService;
import ru.ssau.tk.LR2.ui.storage.FunctionStorageFactory;
import ru.ssau.tk.LR2.ui.storage.TabulatedFunctionStorageInterface;

import java.util.List;

@RestController
@RequestMapping("/tabulated/factory")
public class FactoryController {

    @Autowired
    TabulatedFactoryService factoryService;

    @Autowired
    FunctionStorageFactory storage_factory;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Cacheable
    @GetMapping("/all")
    public List<TabulatedFactoryDTO> getTabulatedFactories(){
        return factoryService.getAllFactories();
    }

    @GetMapping()
    public TabulatedFactoryDTO getCurrentFactory() throws AuthException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunctionStorageInterface storage = storage_factory.getStorage(ctx);

        return TabulatedFactoryDTO.from(storage.getCurrentFactory(ctx));
    }

    @PostMapping()
    public void setTabulatedFactory(@NonNull @RequestParam("type") String classname) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunctionStorageInterface storage = storage_factory.getStorage(ctx);

        storage.setCurrentFactory(ctx, factoryService.create(classname));
    }
}
