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
import ru.ssau.tk.LR2.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.LR2.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.LR2.ui.dto.CompositeFunctionRequestDTO;
import ru.ssau.tk.LR2.ui.services.SimpleFunctionService;
import ru.ssau.tk.LR2.ui.services.TabulatedFactoryService;
import ru.ssau.tk.LR2.ui.storage.FunctionStorageFactory;
import ru.ssau.tk.LR2.ui.storage.TabulatedFunctionStorageInterface;
import ru.ssau.tk.LR2.web.Application;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class SimpleFunctionControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    SimpleFunctionService functionService;

    @MockBean
    FunctionStorageFactory storage_factory;

    @Mock
    TabulatedFunctionStorageInterface mock_storage;

    @Test
    void testPostGet() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Mockito.when(storage_factory.getStorage(Mockito.any(SecurityContext.class))).thenReturn(mock_storage);
        Mockito.when(functionService.getAllSimpleFunctions()).thenReturn(List.of());
        Mockito.when(mock_storage.getCompositeFunctions(Mockito.any(SecurityContext.class))).thenReturn(List.of());

        mvc.perform(get("/tabulated/simple"))
                .andExpect(status().isOk());

        Mockito.verify(functionService).getAllSimpleFunctions();
        Mockito.verify(mock_storage).getCompositeFunctions(Mockito.any(SecurityContext.class));

        mvc.perform(post("/tabulated/simple/composite").contentType("application/json").content(
                mapper.writeValueAsString(new CompositeFunctionRequestDTO("test1", "test2", "test3"))
                ))
                .andExpect(status().isOk());

        Mockito.verify(mock_storage).addCompositeFunction(Mockito.any(SecurityContext.class), Mockito.eq("test1"), Mockito.eq("test2"), Mockito.eq("test3"));
    }
}