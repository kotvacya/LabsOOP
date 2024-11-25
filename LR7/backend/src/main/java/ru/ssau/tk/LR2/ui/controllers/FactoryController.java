package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.LR2.ui.dto.TabulatedFactoryDTO;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.services.TabulatedFactoryService;
import ru.ssau.tk.LR2.ui.storage.InMemoryTabulatedFunctionStorage;

import java.util.List;

@RestController
@RequestMapping("/tabulated/factory")
public class FactoryController {

    @Autowired
    TabulatedFactoryService factoryService;

    @Autowired
    InMemoryTabulatedFunctionStorage storage;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @Cacheable
    @GetMapping("/all")
    public List<TabulatedFactoryDTO> getTabulatedFactories(){
        return factoryService.getAllFactories();
    }

    @GetMapping()
    public TabulatedFactoryDTO getCurrentFactory() throws AuthException {
        return TabulatedFactoryDTO.from(storage.getCurrentFactory(securityContextHolderStrategy.getContext()));
    }

    @PostMapping()
    public void setTabulatedFactory(@NonNull @RequestParam("type") String classname) throws AuthException, BaseUIException {
        storage.setCurrentFactory(securityContextHolderStrategy.getContext(), factoryService.create(classname));
    }
}
