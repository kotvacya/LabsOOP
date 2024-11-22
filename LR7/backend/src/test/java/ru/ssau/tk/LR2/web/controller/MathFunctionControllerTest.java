package ru.ssau.tk.LR2.web.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.LR2.jpa.model.MathResult;
import ru.ssau.tk.LR2.web.service.MathService;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MathFunctionControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    MathService mathService;

    @Test
    void testGet() throws Exception {
        Mockito.when(mathService.findByHash(123)).thenReturn(getMathResults().subList(0, 1));

        mvc.perform(get("/math/123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].hash").value(123));

        Mockito.verify(mathService).findByHash(123);
    }

    @Test
    void testCreate() throws Exception {
        Mockito.when(mathService.save(Mockito.any(MathResult.class))).thenReturn(new MathResult());
        mvc.perform(post("/math/222?x=2&y=2")).andExpect(status().isOk());
        Mockito.verify(mathService).save(Mockito.any(MathResult.class));
    }

    @Test
    void updateByHashAndX() throws Exception {
        mvc.perform(put("/math/222?x=2&y=5")).andExpect(status().isOk());
        Mockito.verify(mathService).updateYByXAndHash(2, 222, 5);
    }

    @Test
    void deleteAll() throws Exception {
        mvc.perform(delete("/math/")).andExpect(status().isOk());
        Mockito.verify(mathService).deleteAll();
    }

    @Test
    void deleteByHash() throws Exception {
        mvc.perform(delete("/math/222")).andExpect(status().isOk());
        mvc.perform(delete("/math/123?x=1")).andExpect(status().isOk());
        Mockito.verify(mathService).deleteByHash(222);
        Mockito.verify(mathService).deleteByXAndHash(1, 123);
    }

    @Test
    void getCount() throws Exception {
        Mockito.when(mathService.count()).thenReturn((long)getMathResults().size());

        mvc.perform(get("/math/count"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(2));

        Mockito.verify(mathService).count();
    }

    private List<MathResult> getMathResults() {
        return Arrays.asList(
                new MathResult(1, 2, 123),
                new MathResult(2, 4, 321)
        );
    }
}