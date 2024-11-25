package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.LR2.functions.Insertable;
import ru.ssau.tk.LR2.functions.Removable;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.operations.TabulatedFunctionOperationService;
import ru.ssau.tk.LR2.ui.dto.ArrayTabulatedResponseDTO;
import ru.ssau.tk.LR2.ui.dto.OperationDTO;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.exceptions.NoSuchOperationException;
import ru.ssau.tk.LR2.ui.services.TabulatedOperationService;
import ru.ssau.tk.LR2.ui.storage.InMemoryTabulatedFunctionStorage;
import ru.ssau.tk.LR2.ui.storage.TemporaryFunctionsStorage;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tabulated/operands")
public class OperandsController {
    @Autowired
    TabulatedOperationService operationService;

    @Autowired
    InMemoryTabulatedFunctionStorage storage;

    @Autowired
    TemporaryFunctionsStorage temp_storage;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @GetMapping()
    public List<ArrayTabulatedResponseDTO> getOperands() throws AuthException {
        return temp_storage.getOperands(securityContextHolderStrategy.getContext()).stream().map(ArrayTabulatedResponseDTO::from).toList();
    }

    @GetMapping("/{id}")
    public ArrayTabulatedResponseDTO getOperand(@PathVariable("id") Integer index) throws AuthException, BaseUIException {
        return ArrayTabulatedResponseDTO.from(temp_storage.getOperand(securityContextHolderStrategy.getContext(), index));
    }

    @PostMapping("/{id}/setY")
    public ArrayTabulatedResponseDTO setYOperand(@PathVariable("id") Integer func_index, @NonNull @RequestParam("index") Integer index, @NonNull @RequestParam("y") Double y) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), func_index);
        if(Objects.nonNull(function)) function.setY(index, y);
        return ArrayTabulatedResponseDTO.from(function);
    }

    @PostMapping("/{id}/remove")
    public ArrayTabulatedResponseDTO removeIndexOperand(@PathVariable("id") Integer func_index, @NonNull @RequestParam("index") Integer index) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), func_index);
        if(function instanceof Removable removable){
            removable.remove(index);
        }else{
            throw new NoSuchOperationException("this function doesn't implement Removable interface");
        }
        return ArrayTabulatedResponseDTO.from(function);
    }

    @PostMapping("/{id}/insert")
    public ArrayTabulatedResponseDTO insertOperand(@PathVariable("id") Integer func_index, @NonNull @RequestParam("x") Double x, @NonNull @RequestParam("y") Double y) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), func_index);
        if(function instanceof Insertable insertable){
            insertable.insert(x, y);
        }else{
            throw new NoSuchOperationException("this function doesn't implement Insertable interface");
        }
        return ArrayTabulatedResponseDTO.from(function);
    }

    @PostMapping("/set")
    public void setOperandFromCurrent(@NonNull @RequestParam("index") Integer index) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        temp_storage.setOperand(ctx, index, temp_storage.getCurrentFunction(ctx));
    }

    @PostMapping("/get")
    public void setCurrentFromOperand(@NonNull @RequestParam("index") Integer index) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        temp_storage.setCurrentFunction(ctx, temp_storage.getOperand(ctx, index));
    }

    @Cacheable
    @GetMapping("/methods")
    public List<OperationDTO> getOperations() {
        return operationService.getAllOperations();
    }

    @PostMapping("/apply")
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
