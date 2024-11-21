package ru.ssau.tk.LR2.ui;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tabulated")
public class TabulatedFunctionController {
    @PostMapping
    public String test(){
        return "test";
    }
}


