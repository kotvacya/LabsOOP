package ru.ssau.tk.LR2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users")
public class UserController {

    //@Autowired
    //UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok("Вернуть пользователя по id: " + id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }
//
//    @PostMapping("/employees/{id}")
//    Employee postUser(@RequestBody Employee newEmployee, @PathVariable Long id) {
//
//        return repository.findById(id)
//                .map(employee -> {
//                    employee.setName(newEmployee.getName());
//                    employee.setRole(newEmployee.getRole());
//                    return repository.save(employee);
//                })
//                .orElseGet(() -> {
//                    return repository.save(newEmployee);
//                });
//    }
//
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id) {
        //userService.deleteById(id);
    }
}