package ru.ssau.tk.LR2.ui.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.LR2.functions.IdentityFunction;
import ru.ssau.tk.LR2.functions.Point;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.dto.ArrayTabulatedRequestDTO;
import ru.ssau.tk.LR2.ui.dto.CompositeFunctionRequestDTO;
import ru.ssau.tk.LR2.ui.dto.SimpleTabulatedRequestDTO;
import ru.ssau.tk.LR2.ui.services.SimpleFunctionService;
import ru.ssau.tk.LR2.ui.services.TabulatedFactoryService;
import ru.ssau.tk.LR2.ui.storage.FunctionStorageFactory;
import ru.ssau.tk.LR2.ui.storage.TabulatedFunctionStorageInterface;
import ru.ssau.tk.LR2.ui.storage.TemporaryFunctionsStorage;
import ru.ssau.tk.LR2.web.Application;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class TabulatedFunctionControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    FunctionStorageFactory storage_factory;

    @MockBean
    TemporaryFunctionsStorage temp_storage;

    @Mock
    TabulatedFunctionStorageInterface mock_storage;

    @Test
    void testPostGet() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Mockito.when(storage_factory.getStorage(Mockito.any(SecurityContext.class))).thenReturn(mock_storage);
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        Mockito.when(mock_storage.getCurrentFactory(Mockito.any(SecurityContext.class))).thenReturn(factory);

        mvc.perform(post("/tabulated/current/array").contentType("application/json").content(
                        mapper.writeValueAsString(new ArrayTabulatedRequestDTO(List.of(
                                new Point(1, 2)
                        )))
                ))
                .andExpect(status().is4xxClientError());

        mvc.perform(post("/tabulated/current/array").contentType("application/json").content(
                        mapper.writeValueAsString(new ArrayTabulatedRequestDTO(List.of(
                                new Point(1, 2),
                                new Point(3, 4),
                                new Point(5, 6)
                        )))
                ))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage).setCurrentFunction(Mockito.any(SecurityContext.class), Mockito.any(TabulatedFunction.class));

        mvc.perform(post("/tabulated/current/simple").contentType("application/json").content(
                        mapper.writeValueAsString(new SimpleTabulatedRequestDTO(
                                IdentityFunction.class.getSimpleName(),
                                0.,
                                10.,
                                11
                        ))
                ))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage, Mockito.times(2)).setCurrentFunction(Mockito.any(SecurityContext.class), Mockito.any(TabulatedFunction.class));
    }
}