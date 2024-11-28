package ru.ssau.tk.LR2.ui.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.ssau.tk.LR2.functions.ArrayTabulatedFunction;
import ru.ssau.tk.LR2.functions.SqrFunction;
import ru.ssau.tk.LR2.functions.TabulatedFunction;

import static org.junit.jupiter.api.Assertions.*;

class SerializationDeserializationServiceTest {

    @Test
    void testSerialization() throws Exception {
        TabulatedFunction function = new ArrayTabulatedFunction(new SqrFunction(), 0, 10, 11);

        SerializationDeserializationService service = new SerializationDeserializationService();

        assertEquals(function.toString(), service.deserializeFunction(service.serializeFunction(function)).toString());

        assertEquals(function.toString(), service.deserializeFunctionXML(service.serializeFunctionXML(function)).toString());
    }

}