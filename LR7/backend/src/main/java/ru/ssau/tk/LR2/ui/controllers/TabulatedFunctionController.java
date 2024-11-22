package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.LR2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.LR2.functions.MathFunction;
import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.dto.ArrayTabulatedDTO;
import ru.ssau.tk.LR2.ui.dto.SimpleFunctionDTO;
import ru.ssau.tk.LR2.ui.dto.SimpleTabulatedRequestDTO;
import ru.ssau.tk.LR2.ui.dto.TabulatedFactoryDTO;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.exceptions.ExceptionUtils;
import ru.ssau.tk.LR2.ui.exceptions.TabulatedCreationException;
import ru.ssau.tk.LR2.ui.services.SimpleFunctionService;
import ru.ssau.tk.LR2.ui.services.TabulatedFactoryService;
import ru.ssau.tk.LR2.ui.storage.InMemoryTabulatedFunctionStorage;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tabulated")
public class TabulatedFunctionController {

    @Autowired
    SimpleFunctionService functionService;

    @Autowired
    TabulatedFactoryService factoryService;

    @Autowired
    InMemoryTabulatedFunctionStorage storage;

    @PostMapping("/array")
    public ArrayTabulatedDTO TabulatedFunctionFromArray(@RequestBody ArrayTabulatedDTO requestDTO) throws BaseUIException, AuthException {
        double[] xValues = new double[requestDTO.getPoints().size()];
        double[] yValues = new double[requestDTO.getPoints().size()];

        int idx = 0;
        for(Point point : requestDTO.getPoints()){
            xValues[idx] = point.x;
            yValues[idx] = point.y;
            idx++;
        }

        try {
            TabulatedFunctionFactory factory = storage.getCurrentFactory(SecurityContextHolder.getContext());
            TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);

            storage.setCurrentFunction(SecurityContextHolder.getContext(), tabulatedFunction);

            return ArrayTabulatedDTO.from(tabulatedFunction);
        }catch (IllegalArgumentException | ArrayIsNotSortedException e){
            throw new TabulatedCreationException(ExceptionUtils.exceptionMessageWithClass(e));
        }
    }

    @PostMapping("/simple")
    public ArrayTabulatedDTO TabulatedFunctionFromSimpleFunction(@RequestBody SimpleTabulatedRequestDTO requestDTO) throws BaseUIException, AuthException {
        MathFunction mathFunction = functionService.create(requestDTO.getFunction());

        try {
            TabulatedFunctionFactory factory = storage.getCurrentFactory(SecurityContextHolder.getContext());
            TabulatedFunction tabulatedFunction = factory.create(mathFunction, requestDTO.getStart(), requestDTO.getEnd(), requestDTO.getCount());

            storage.setCurrentFunction(SecurityContextHolder.getContext(), tabulatedFunction);

            return ArrayTabulatedDTO.from(tabulatedFunction);
        }catch (IllegalArgumentException e){
            throw new TabulatedCreationException(ExceptionUtils.exceptionMessageWithClass(e));
        }
    }

    @Cacheable
    @GetMapping("/simple")
    public List<SimpleFunctionDTO> getSimpleFunctions(){
        return functionService.getAllSimpleFunctions();
    }

    @GetMapping()
    public ArrayTabulatedDTO getCurrentFunction() throws BaseUIException, AuthException{
        TabulatedFunction func = storage.getCurrentFunction(SecurityContextHolder.getContext());
        if(Objects.nonNull(func)) {
            return ArrayTabulatedDTO.from(func);
        }
        return null;
    }

    @Cacheable
    @GetMapping("/factory/all")
    public List<TabulatedFactoryDTO> getTabulatedFactories(){
        return factoryService.getAllFactories();
    }

    @GetMapping("/factory")
    public TabulatedFactoryDTO getCurrentFactory() throws AuthException{
        return TabulatedFactoryDTO.from(storage.getCurrentFactory(SecurityContextHolder.getContext()));
    }

    @PostMapping("/factory")
    public void setTabulatedFactory(@NonNull @RequestParam("type") String classname) throws AuthException, BaseUIException{
        storage.setCurrentFactory(SecurityContextHolder.getContext(), factoryService.create(classname));
    }




}


