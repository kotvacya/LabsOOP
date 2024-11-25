package ru.ssau.tk.LR2.ui.services;

import org.springframework.stereotype.Service;
import ru.ssau.tk.LR2.functions.TabulatedFunction;
import ru.ssau.tk.LR2.io.FunctionsIO;
import ru.ssau.tk.LR2.ui.exceptions.BaseUIException;
import ru.ssau.tk.LR2.ui.exceptions.SerializationException;

import java.io.*;

@Service
public class SerializationDeserializationService {

    public byte[] serializeFunction(TabulatedFunction func) throws BaseUIException {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            BufferedOutputStream out = new BufferedOutputStream(outputStream);
            FunctionsIO.serialize(out, func);
            out.flush();
            outputStream.flush();
            return outputStream.toByteArray();
        }catch (IOException e){
            throw new SerializationException("Cannot serialize function");
        }
    }

    public TabulatedFunction deserializeFunction(byte[] array) throws BaseUIException {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(array);
            BufferedInputStream in = new BufferedInputStream(inputStream);
            return FunctionsIO.deserialize(in);
        }catch (IOException | ClassNotFoundException e){
            throw new SerializationException("Cannot deserialize function");
        }
    }

}
