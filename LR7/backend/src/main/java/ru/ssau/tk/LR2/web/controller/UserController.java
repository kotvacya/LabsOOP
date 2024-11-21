package ru.ssau.tk.LR2.web.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.ssau.tk.LR2.jdbc.model.MathResult;
import ru.ssau.tk.LR2.jdbc.model.User;
import ru.ssau.tk.LR2.jdbc.repository.UserRepository;
import ru.ssau.tk.LR2.web.dto.MathResultDTO;
import ru.ssau.tk.LR2.web.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    ModelMapper mapper = new ModelMapper();

    private UserDTO convertToDto(User user){
        return mapper.map(user, UserDTO.class);
    }

    @GetMapping("/")
    public ResponseEntity<List<UserDTO>> getUsers() {
        try {
            return ResponseEntity.ok(
                    userRepository.getUsers().stream().map(this::convertToDto).collect(Collectors.toList())
            );
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable("id") int id) {
        try {
            return ResponseEntity.ok(convertToDto(userRepository.findById(id)));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

    @GetMapping("/whoami")
    public ResponseEntity<String> getMe() {
        try {
            SecurityContext ctx = SecurityContextHolder.getContext();
            return ResponseEntity.ok(ctx.getAuthentication().getName());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.toString());
        }
    }

}