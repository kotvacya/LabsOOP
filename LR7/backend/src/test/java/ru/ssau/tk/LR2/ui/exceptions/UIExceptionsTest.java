package ru.ssau.tk.LR2.ui.exceptions;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;

class UIExceptionsTest {

    static final String EXCEPTIONS_PREFIX = "ru.ssau.tk.LR2.ui.exceptions";

    @Test
    void testExceptions() throws Exception{

        Reflections reflections = new Reflections(EXCEPTIONS_PREFIX);

        for( Class<? extends BaseUIException> exception_class : reflections.getSubTypesOf(BaseUIException.class)){
            try {
                Constructor<? extends BaseUIException> constructor = exception_class.getDeclaredConstructor(String.class);
                constructor.newInstance("test");
            } catch (NoSuchMethodException  ignored) { }
            try {
                Constructor<? extends BaseUIException> constructor = exception_class.getDeclaredConstructor();
                constructor.newInstance();
            } catch (NoSuchMethodException  ignored) { }
        }
    }

}