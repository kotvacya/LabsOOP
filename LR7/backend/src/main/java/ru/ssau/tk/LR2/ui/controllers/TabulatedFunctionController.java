package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.LR2.exceptions.ArrayIsNotSortedException;
import ru.ssau.tk.LR2.functions.*;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.LR2.ui.dto.*;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.exceptions.ExceptionUtils;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchOperationException;
import ru.ssau.tk.LR2.ui.exceptions.TabulatedCreationException;
import ru.ssau.tk.LR2.ui.services.SimpleFunctionService;
import ru.ssau.tk.LR2.ui.services.TabulatedFactoryService;
import ru.ssau.tk.LR2.ui.services.TabulatedOperationService;
import ru.ssau.tk.LR2.ui.storage.InMemoryTabulatedFunctionStorage;
import ru.ssau.tk.LR2.ui.storage.TemporaryFunctionsStorage;

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
    TabulatedOperationService operationService;

    @Autowired
    InMemoryTabulatedFunctionStorage storage;

    @Autowired
    TemporaryFunctionsStorage temp_storage;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    /////////// CURRENT ///////////

    @PostMapping("/current/array")
    public ArrayTabulatedResponseDTO TabulatedFunctionFromArray(@RequestBody ArrayTabulatedRequestDTO requestDTO) throws BaseUIException, AuthException {
        double[] xValues = new double[requestDTO.getPoints().size()];
        double[] yValues = new double[requestDTO.getPoints().size()];

        int idx = 0;
        for(Point point : requestDTO.getPoints()){
            xValues[idx] = point.x;
            yValues[idx] = point.y;
            idx++;
        }

        try {
            TabulatedFunctionFactory factory = storage.getCurrentFactory(securityContextHolderStrategy.getContext());
            TabulatedFunction tabulatedFunction = factory.create(xValues, yValues);

            temp_storage.setCurrentFunction(securityContextHolderStrategy.getContext(), tabulatedFunction);

            return ArrayTabulatedResponseDTO.from(tabulatedFunction);
        }catch (IllegalArgumentException | ArrayIsNotSortedException e){
            throw new TabulatedCreationException(ExceptionUtils.exceptionMessageWithClass(e));
        }
    }

    @PostMapping("/current/simple")
    public ArrayTabulatedResponseDTO TabulatedFunctionFromSimpleFunction(@RequestBody SimpleTabulatedRequestDTO requestDTO) throws BaseUIException, AuthException {
        MathFunction mathFunction = functionService.create(requestDTO.getFunction());

        try {
            TabulatedFunctionFactory factory = storage.getCurrentFactory(securityContextHolderStrategy.getContext());
            TabulatedFunction tabulatedFunction = factory.create(mathFunction, requestDTO.getStart(), requestDTO.getEnd(), requestDTO.getCount());

            temp_storage.setCurrentFunction(securityContextHolderStrategy.getContext(), tabulatedFunction);

            return ArrayTabulatedResponseDTO.from(tabulatedFunction);
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
    public ArrayTabulatedResponseDTO getCurrentFunction() throws BaseUIException, AuthException{
        TabulatedFunction func = temp_storage.getCurrentFunction(securityContextHolderStrategy.getContext());
        return ArrayTabulatedResponseDTO.from(func);
    }

    /////////// FACTORY ///////////

    @Cacheable
    @GetMapping("/factory/all")
    public List<TabulatedFactoryDTO> getTabulatedFactories(){
        return factoryService.getAllFactories();
    }

    @GetMapping("/factory")
    public TabulatedFactoryDTO getCurrentFactory() throws AuthException{
        return TabulatedFactoryDTO.from(storage.getCurrentFactory(securityContextHolderStrategy.getContext()));
    }

    @PostMapping("/factory")
    public void setTabulatedFactory(@NonNull @RequestParam("type") String classname) throws AuthException, BaseUIException{
        storage.setCurrentFactory(securityContextHolderStrategy.getContext(), factoryService.create(classname));
    }

    /////////// OPERANDS ///////////


    @GetMapping("/operands")
    public List<ArrayTabulatedResponseDTO> getOperands() throws AuthException{
        return temp_storage.getOperands(securityContextHolderStrategy.getContext()).stream().map(ArrayTabulatedResponseDTO::from).toList();
    }

    @GetMapping("/operands/{id}")
    public ArrayTabulatedResponseDTO getOperand(@PathVariable("id") Integer index) throws AuthException, BaseUIException {
        return ArrayTabulatedResponseDTO.from(temp_storage.getOperand(securityContextHolderStrategy.getContext(), index));
    }

    @PostMapping("/operands/{id}/setY")
    public ArrayTabulatedResponseDTO setYOperand(@PathVariable("id") Integer func_index, @NonNull @RequestParam("index") Integer index, @NonNull @RequestParam("y") Double y) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), func_index);
        if(Objects.nonNull(function)) function.setY(index, y);
        return ArrayTabulatedResponseDTO.from(function);
    }

    @PostMapping("/operands/{id}/remove")
    public ArrayTabulatedResponseDTO removeIndexOperand(@PathVariable("id") Integer func_index, @NonNull @RequestParam("index") Integer index) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), func_index);
        if(function instanceof Removable removable){
            removable.remove(index);
        }else{
            throw new NoSuchOperationException("this function doesn't implement Removable interface");
        }
        return ArrayTabulatedResponseDTO.from(function);
    }

    @PostMapping("/operands/{id}/insert")
    public ArrayTabulatedResponseDTO insertOperand(@PathVariable("id") Integer func_index, @NonNull @RequestParam("x") Double x, @NonNull @RequestParam("y") Double y) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), func_index);
        if(function instanceof Insertable insertable){
            insertable.insert(x, y);
        }else{
            throw new NoSuchOperationException("this function doesn't implement Insertable interface");
        }
        return ArrayTabulatedResponseDTO.from(function);
    }

    @PostMapping("/operands/set")
    public void setOperandFromCurrent(@NonNull @RequestParam("index") Integer index) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        temp_storage.setOperand(ctx, index, temp_storage.getCurrentFunction(ctx));
    }

    @PostMapping("/operands/get")
    public void setCurrentFromOperand(@NonNull @RequestParam("index") Integer index) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        temp_storage.setCurrentFunction(ctx, temp_storage.getOperand(ctx, index));
    }

    @Cacheable
    @GetMapping("/operands/methods")
    public List<OperationDTO> getOperations() {
        return operationService.getAllOperations();
    }

    @PostMapping("/operands/apply")
    public ArrayTabulatedResponseDTO applyOperation(@NonNull @RequestParam String operation) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunction result = operationService.apply(operation,
                new TabulatedFunctionOperationService(storage.getCurrentFactory(ctx)),
                temp_storage.getOperand(ctx, 0),
                temp_storage.getOperand(ctx, 1)
        );
        temp_storage.setOperand(ctx, 2, result);
        return ArrayTabulatedResponseDTO.from(result);
    }



}


