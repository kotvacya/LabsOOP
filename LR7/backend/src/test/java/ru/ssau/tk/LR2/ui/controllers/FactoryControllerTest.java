package ru.ssau.tk.LR2.ui.controllers;

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
import ru.ssau.tk.LR2.ui.services.TabulatedFactoryService;
import ru.ssau.tk.LR2.ui.storage.FunctionStorageFactory;
import ru.ssau.tk.LR2.ui.storage.TabulatedFunctionStorageInterface;
import ru.ssau.tk.LR2.web.Application;
import ru.ssau.tk.LR2.web.service.LogService;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class FactoryControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    TabulatedFactoryService factoryService;

    @MockBean
    FunctionStorageFactory storage_factory;

    @Mock
    TabulatedFunctionStorageInterface mock_storage;

    @Test
    void testPostGet() throws Exception{
        Mockito.when(storage_factory.getStorage(Mockito.any(SecurityContext.class))).thenReturn(mock_storage);
        TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
        Mockito.when(factoryService.create(Mockito.any(String.class))).thenReturn(factory);

        mvc.perform(post("/tabulated/factory?type=test"))
                .andExpect(status().isOk());

        Mockito.verify(mock_storage).setCurrentFactory(Mockito.any(SecurityContext.class), Mockito.eq(factory));
        Mockito.when(mock_storage.getCurrentFactory(Mockito.any(SecurityContext.class))).thenReturn(factory);

        mvc.perform(get("/tabulated/factory"))
                .andExpect(status().isOk());

        Mockito.verify(mock_storage).getCurrentFactory(Mockito.any(SecurityContext.class));

        mvc.perform(get("/tabulated/factory/all"))
                .andExpect(status().isOk());

        Mockito.verify(factoryService).getAllFactories();
    }


}