package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.LR2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.LR2.functions.*;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.dto.*;
import ru.ssau.tk.LR2.ui.exceptions.*;
import ru.ssau.tk.LR2.ui.services.SimpleFunctionService;
import ru.ssau.tk.LR2.ui.storage.FunctionStorageFactory;
import ru.ssau.tk.LR2.ui.storage.TabulatedFunctionStorageInterface;
import ru.ssau.tk.LR2.ui.storage.TemporaryFunctionsStorage;

@RestController
@RequestMapping("/tabulated")
public class TabulatedFunctionController {

    @Autowired
    SimpleFunctionService functionService;

    @Autowired
    FunctionStorageFactory storage_factory;

    @Autowired
    TemporaryFunctionsStorage temp_storage;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();


    @PostMapping("/current/array")
    public ArrayTabulatedResponseDTO TabulatedFunctionFromArray(@RequestBody ArrayTabulatedRequestDTO requestDTO) throws BaseUIException, AuthException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunctionStorageInterface storage = storage_factory.getStorage(ctx);

        double[] xValues = new double[requestDTO.getPoints().size()];
        double[] yValues = new double[requestDTO.getPoints().size()];

        int idx = 0;
        for(Point point : requestDTO.getPoints()){
            xValues[idx] = point.x;
            yValues[idx] = point.y;
            idx++;
        }

        try {
            TabulatedFunctionFactory factory = storage.getCurrentFactory(ctx);
            TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);

            temp_storage.setCurrentFunction(ctx, tabulatedFunction);

            return ArrayTabulatedResponseDTO.from(tabulatedFunction);
        }catch (IllegalArgumentException | ArrayIsNotSortedException e){
            throw new TabulatedCreationException(ExceptionUtils.exceptionMessageWithClass(e));
        }
    }

    private MathFunction getSimpleOrComposite(String name, TabulatedFunctionStorageInterface storageInterface, SecurityContext ctx) throws AuthException, NoSuchSimpleFunctionException {
        try {
            return functionService.create(name);
        } catch (NoSuchSimpleFunctionException e) {
            try {
                return storageInterface.getCompositeFunction(ctx, name);
            } catch (NoSuchCompositeFunctionException ex) {
                throw e;
            }
        }
    }

    @PostMapping("/current/simple")
    public ArrayTabulatedResponseDTO TabulatedFunctionFromSimpleFunction(@RequestBody SimpleTabulatedRequestDTO requestDTO) throws BaseUIException, AuthException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunctionStorageInterface storage = storage_factory.getStorage(ctx);

        MathFunction mathFunction = getSimpleOrComposite(requestDTO.getFunction(), storage, ctx);
//        MathFunction mathFunction = functionService.create(requestDTO.getFunction());

        try {
            TabulatedFunctionFactory factory = storage.getCurrentFactory(ctx);
            TabulatedFunction tabulatedFunction = factory.create(mathFunction, requestDTO.getStart(), requestDTO.getEnd(), requestDTO.getCount());

            temp_storage.setCurrentFunction(ctx, tabulatedFunction);

            return ArrayTabulatedResponseDTO.from(tabulatedFunction);
        }catch (IllegalArgumentException e){
            throw new TabulatedCreationException(ExceptionUtils.exceptionMessageWithClass(e));
        }
    }

//    @Cacheable
//    @GetMapping("/simple")
//    public List<SimpleFunctionDTO> getSimpleFunctions(){
//        return functionService.getAllSimpleFunctions();
//    }

    @GetMapping()
    public ArrayTabulatedResponseDTO getCurrentFunction() throws BaseUIException, AuthException{
        TabulatedFunction func = temp_storage.getCurrentFunction(securityContextHolderStrategy.getContext());
        return ArrayTabulatedResponseDTO.from(func);
    }


}


