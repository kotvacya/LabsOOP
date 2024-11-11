package ru.ssau.tk.LR2.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(@PathVariable("id") long id) {
        try {
            return ResponseEntity.ok("Вернуть пользователя по id: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Ошибка");
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
//    @DeleteMapping("/employees/{id}")
//    void deleteUser(@PathVariable Long id) {
//        repository.deleteById(id);
//    }
}