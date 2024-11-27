package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import ru.ssau.tk.LR2.ui.services.SerializationDeserializationService;
import ru.ssau.tk.LR2.ui.services.TabulatedOperationService;
import ru.ssau.tk.LR2.ui.storage.FunctionStorageFactory;
import ru.ssau.tk.LR2.ui.storage.TabulatedFunctionStorageInterface;
import ru.ssau.tk.LR2.ui.storage.TemporaryFunctionsStorage;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/tabulated/operands")
public class OperandsController {
    private static final Logger log = LoggerFactory.getLogger(OperandsController.class);
    @Autowired
    TabulatedOperationService operationService;

    @Autowired
    FunctionStorageFactory storage_factory;

    @Autowired
    TemporaryFunctionsStorage temp_storage;

    @Autowired
    SerializationDeserializationService serialService;

    private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

    @GetMapping()
    public List<ArrayTabulatedResponseDTO> getOperands() throws AuthException {
        return temp_storage.getOperands(securityContextHolderStrategy.getContext()).stream().map(ArrayTabulatedResponseDTO::from).toList();
    }

    @GetMapping("/{id}")
    public ArrayTabulatedResponseDTO getOperand(@PathVariable("id") Integer index) throws AuthException, BaseUIException {
        return ArrayTabulatedResponseDTO.from(temp_storage.getOperand(securityContextHolderStrategy.getContext(), index));
    }

    ///// SERIALIZATION /////

    @GetMapping(value = "/{id}/serialized", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Resource> getSerializedOperand(@PathVariable("id") Integer index) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), index);
        ByteArrayResource resource = new ByteArrayResource(serialService.serializeFunction(function));
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(resource.contentLength())
                .header(HttpHeaders.CONTENT_DISPOSITION)
                .body(resource);
    }

    @PostMapping("/{id}/serialized")
    public ArrayTabulatedResponseDTO setSerializedOperand(@PathVariable("id") Integer index, @RequestBody byte[] data) throws AuthException, BaseUIException {
        TabulatedFunction function = serialService.deserializeFunction(data);
        temp_storage.setOperand(securityContextHolderStrategy.getContext(), index, function);
        return ArrayTabulatedResponseDTO.from(function);
    }

    @GetMapping(value = "/{id}/xml", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public String getXmlOperand(@PathVariable("id") Integer index) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), index);
        return serialService.serializeFunctionXML(function);
    }

    @PostMapping("/{id}/xml")
    public ArrayTabulatedResponseDTO setXmlOperand(@PathVariable("id") Integer index, @RequestBody String data) throws AuthException, BaseUIException {
        TabulatedFunction function = serialService.deserializeFunctionXML(data);
        temp_storage.setOperand(securityContextHolderStrategy.getContext(), index, function);
        return ArrayTabulatedResponseDTO.from(function);
    }

    ///// OPERATIONS /////

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

    @GetMapping("/{id}/apply")
    public Double applyOperand(@PathVariable("id") Integer func_index, @NonNull @RequestParam("x") Double x) throws AuthException, BaseUIException {
        TabulatedFunction function = temp_storage.getOperand(securityContextHolderStrategy.getContext(), func_index);
        if(Objects.nonNull(function)) {
            return function.apply(x);
        }else{
            throw new NoSuchOperationException("No operand at index " + func_index);
        }
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
    public ArrayTabulatedResponseDTO applyOperation(@NonNull @RequestParam("operation") String operation) throws AuthException, BaseUIException {
        SecurityContext ctx = securityContextHolderStrategy.getContext();
        TabulatedFunctionStorageInterface storage = storage_factory.getStorage(ctx);

        TabulatedFunction result = operationService.apply(operation,
                new TabulatedFunctionOperationService(storage.getCurrentFactory(ctx)),
                temp_storage.getOperand(ctx, 0),
                temp_storage.getOperand(ctx, 1)
        );
        temp_storage.setOperand(ctx, 2, result);
        return ArrayTabulatedResponseDTO.from(result);
    }
}
