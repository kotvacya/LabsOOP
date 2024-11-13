package ru.ssau.tk.LR2.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.ssau.tk.LR2.jdbc.model.Log;
import ru.ssau.tk.LR2.web.dto.LogDTO;
import ru.ssau.tk.LR2.web.service.LogService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class LogControllerTest {
    @Autowired
    MockMvc mvc;

    @MockBean
    LogService logService;

    @Test
    void testGet() throws Exception {
        Mockito.when(logService.findSortedByTimestamp()).thenReturn(getLogs());

        mvc.perform(get("/log/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void testGetById() throws Exception {
        Mockito.when(logService.findById(1)).thenReturn(getLogs().get(1));

        mvc.perform(get("/log/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.text").value("log2"));
    }

    @Test
    void testPost() throws Exception {
        Mockito.when(logService.insert(Mockito.any(Log.class))).thenReturn(new Log());

        ObjectMapper objectMapper = new ObjectMapper();


        mvc.perform(post("/log/")
                        .content(objectMapper.writeValueAsString(
                                new LogDTO("log3", Timestamp.from(Instant.EPOCH))
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        Mockito.verify(logService).insert(Mockito.any(Log.class));
    }

    @Test
    void testPut() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        mvc.perform(put("/log/2")
                        .content(objectMapper.writeValueAsString(
                                new LogDTO("log3", Timestamp.from(Instant.EPOCH))
                        ))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());

        Mockito.verify(logService).updateTextAndTsById(2, "log3", Timestamp.from(Instant.EPOCH));
    }

    @Test
    void testDelete() throws Exception {

        mvc.perform(delete("/log/2"))
                .andExpect(status().isOk());
        mvc.perform(delete("/log/"))
                .andExpect(status().isOk());


        Mockito.verify(logService).deleteById(2);
        Mockito.verify(logService).deleteAll();
    }



    private List<Log> getLogs() {
        return Arrays.asList(
                new Log("log1", Timestamp.from(Instant.EPOCH)),
                new Log("log2", Timestamp.from(Instant.EPOCH.plusSeconds(10)))
        );

    }
}