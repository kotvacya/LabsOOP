package ru.ssau.tk.LR2.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.web.dto.MathResultDTO;
import ru.ssau.tk.LR2.web.service.MathService;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/math")
public class MathFunctionController {

    @Autowired
    MathService mathService;

    ModelMapper mapper = new ModelMapper();

    private MathResultDTO convertToDto(MathResult res){
        return mapper.map(res, MathResultDTO.class);
    }

    @GetMapping("/{hash}")
    public ResponseEntity<List<MathResultDTO>> get(@PathVariable("hash") long hash, @RequestParam(value = "x", required = false) Double x) {
        try {
            List<MathResult> res;

            if (Objects.isNull(x)) res = mathService.findByHash(hash);
            else res = Collections.singletonList(mathService.findByXAndHash(x, hash));

            return ResponseEntity.ok(res.stream().map(this::convertToDto).collect(Collectors.toList()));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PostMapping("/{hash}")
    public ResponseEntity<String> create(@PathVariable("hash") long hash, @RequestParam("x") double x, @RequestParam("y") double y) {
        try {
            mathService.insert(new MathResult(x, y, hash));
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @PutMapping("/{hash}")
    public ResponseEntity<String> updateByHashAndX(@PathVariable("hash") long hash, @RequestParam("x") double x, @RequestParam("y") double y) {
        try {
            mathService.updateYByXAndHash(x, hash, y);
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @DeleteMapping("/")
    public ResponseEntity<String> deleteAll() {
        try {
            mathService.deleteAll();
            return ResponseEntity.ok("success");
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @DeleteMapping("/{hash}")
    public ResponseEntity<List<MathResultDTO>> deleteByHash(@PathVariable("hash") long hash, @RequestParam(value = "x", required = false) Double x) {
        try {
            List<MathResult> res;

            if (Objects.isNull(x)) res = mathService.deleteByHash(hash);
            else res = mathService.deleteByXAndHash(x, hash);

            return ResponseEntity.ok(res.stream().map(this::convertToDto).collect(Collectors.toList()));
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