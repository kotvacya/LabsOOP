package ru.ssau.tk.LR2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.web.service.MathService;

import java.util.Collections;
import java.util.List;


@RestController
@RequestMapping("/math")
public class MathFunctionController {

    @Autowired
    MathService mathService;

    @GetMapping("/{hash}")
    public ResponseEntity<List<MathResult>> getByHashAndX(@PathVariable("hash") long hash, @RequestParam(value = "x", required = false) Double x) {
        try {
            if (x == null) return ResponseEntity.ok(mathService.findByHash(hash));
            return ResponseEntity.ok(Collections.singletonList(mathService.findByXAndHash(x, hash)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PostMapping("/{hash}")
    public ResponseEntity<Integer> createByHashAndX(@PathVariable("hash") long hash, @RequestParam("x") double x, @RequestParam("y") double y) {
        try {
            MathResult mathResult = new MathResult(x, y, hash);
            mathService.insert(mathResult);
            return ResponseEntity.ok(mathResult.getId());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

//    @PutMapping("/{hash}")
//    public ResponseEntity updateByHashAndX(@PathVariable("hash") long hash, @RequestParam("x") double x, @RequestParam("y") double y) {
//        try {
//            MathResult mathResult = new MathResult(x, y, hash);
//            mathService.update()
//            return ResponseEntity.ok("Update" + hash + "(" + x + ")");
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
//        }
//    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<List<MathResult>> deleteByHashAndX(@PathVariable("hash") long hash, @RequestParam(value = "x", required = false) Double x) {
        try {

            if (x == null) {
                List<MathResult> list = mathService.findByHash(hash);
                mathService.deleteByHash(hash);
                return ResponseEntity.ok(list);
            }

            MathResult mr = mathService.findByXAndHash(x, hash);
            mathService.deleteByHash(hash);
            return ResponseEntity.ok(Collections.singletonList(mr));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getCount() {
        try {
            return ResponseEntity.ok(mathService.getCount());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }
}