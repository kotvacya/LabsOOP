package ru.ssau.tk.LR2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ssau.tk.LR2.jdbc.model.Log;
import ru.ssau.tk.LR2.web.dto.LogDTO;
import ru.ssau.tk.LR2.web.service.LogService;

import java.util.List;


@RestController
@RequestMapping("/log")
public class LogController {

    @Autowired
    LogService logService;

    @GetMapping("/{id}")
    public ResponseEntity<Log> getById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(logService.findById(id));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Log>> getAll() {
        try {
            return ResponseEntity.ok(logService.findSortedByTimestamp());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PostMapping("/")
    public ResponseEntity<Integer> post(@RequestBody LogDTO log) {
        try {
            Log l = new Log(log.getText());
            logService.insert(l);
            return ResponseEntity.ok(l.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> put(@PathVariable("id") int id, @RequestBody LogDTO log) {
        try {
            logService.updateTextAndTsById(id, log.getText(), log.getTs());
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
        try {
            logService.deleteById(id);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> delete() {
        try {
            logService.deleteAll();
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}
