package ru.ssau.tk.LR2.ui.controllers;

import jakarta.security.auth.message.AuthException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.IdentityFunction;
import ru.ssau.tk.LR2.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.services.SerializationDeserializationService;
import ru.ssau.tk.LR2.ui.storage.TemporaryFunctionsStorage;
import ru.ssau.tk.LR2.web.Application;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
class OperandsControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    TemporaryFunctionsStorage temp_storage;

    @MockBean
    SerializationDeserializationService serialService;

    @BeforeEach
    public void initTests() throws Exception {
        Mockito.clearInvocations(temp_storage, serialService);
        Mockito.when(temp_storage.getOperands(Mockito.any(SecurityContext.class))).thenReturn(getOperands());
        Mockito.when(temp_storage.getOperand(Mockito.any(SecurityContext.class), Mockito.eq(0))).thenReturn(getOperands().get(0));
        Mockito.when(temp_storage.getOperand(Mockito.any(SecurityContext.class), Mockito.eq(1))).thenReturn(getOperands().get(1));
    }

    @Test
    void testIndex() throws Exception{
        mvc.perform(get("/tabulated/operands"))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage).getOperands(Mockito.any(SecurityContext.class));

        mvc.perform(get("/tabulated/operands/0"))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage).getOperand(Mockito.any(SecurityContext.class), Mockito.eq(0));

        mvc.perform(get("/tabulated/operands/1"))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage).getOperand(Mockito.any(SecurityContext.class), Mockito.eq(1));

    }

    @Test
    void testSerial() throws Exception {
        Mockito.when(serialService.serializeFunction(Mockito.any(TabulatedFunction.class))).thenReturn(new byte[]{1,2,3});

        mvc.perform(get("/tabulated/operands/0/serialized"))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage).getOperand(Mockito.any(SecurityContext.class), Mockito.eq(0));
        Mockito.verify(serialService).serializeFunction(Mockito.any(TabulatedFunction.class));

        mvc.perform(post("/tabulated/operands/0/serialized").content(new byte[]{1,2,3,4}))
                .andExpect(status().isOk());

        Mockito.verify(serialService).deserializeFunction(Mockito.any(byte[].class));



        Mockito.when(serialService.serializeFunctionXML(Mockito.any(TabulatedFunction.class))).thenReturn("123");

        mvc.perform(get("/tabulated/operands/0/xml"))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage, Mockito.times(2)).getOperand(Mockito.any(SecurityContext.class), Mockito.eq(0));
        Mockito.verify(serialService).serializeFunctionXML(Mockito.any(TabulatedFunction.class));

        mvc.perform(post("/tabulated/operands/0/xml").content("123"))
                .andExpect(status().isOk());

        Mockito.verify(serialService).deserializeFunctionXML(Mockito.any(String.class));
    }

    @Test
    void testOperations() throws Exception {
        ArrayTabulatedFunction function = new ArrayTabulatedFunction(new IdentityFunction(), 0, 10, 11);
        ArrayTabulatedFunction mock_function = Mockito.spy(function);

        Mockito.when(temp_storage.getOperand(Mockito.any(SecurityContext.class), Mockito.eq(0))).thenReturn(mock_function);

        mvc.perform(post("/tabulated/operands/0/setY?index=1&y=1"))
                .andExpect(status().isOk());

        Mockito.verify(mock_function).setY(1, 1);

        mvc.perform(post("/tabulated/operands/0/remove?index=1"))
                .andExpect(status().isOk());

        Mockito.verify(mock_function).remove(1);

        mvc.perform(post("/tabulated/operands/0/insert?x=1&y=1"))
                .andExpect(status().isOk());

        Mockito.verify(mock_function).insert(1, 1);

        mvc.perform(get("/tabulated/operands/0/apply?x=1"))
                .andExpect(status().isOk())
                .andExpect(content().string("1.0"));

        Mockito.verify(mock_function).apply(1);

        mvc.perform(get("/tabulated/operands/0/applyIntegral"))
                .andExpect(status().isOk())
                .andExpect(content().string("50.0"));
    }

    @Test
    void testOperands() throws Exception{
        Mockito.when(temp_storage.getCurrentFunction(Mockito.any(SecurityContext.class))).thenReturn(getOperands().get(1));

        mvc.perform(post("/tabulated/operands/set?index=0"))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage).setOperand(Mockito.any(SecurityContext.class), Mockito.eq(0), Mockito.any(TabulatedFunction.class));

        mvc.perform(post("/tabulated/operands/get?index=0"))
                .andExpect(status().isOk());

        Mockito.verify(temp_storage).setCurrentFunction(Mockito.any(SecurityContext.class), Mockito.any(TabulatedFunction.class));
    }

    @Test
    void testApply() throws Exception{
        mvc.perform(get("/tabulated/operands/methods"))
                .andExpect(status().isOk());

    }

    List<TabulatedFunction> getOperands(){
        return List.of(
                new ArrayTabulatedFunction(new IdentityFunction(), 0, 10, 11),
                new LinkedListTabulatedFunction(new IdentityFunction(), 0, 10, 11)
        );
    }
}