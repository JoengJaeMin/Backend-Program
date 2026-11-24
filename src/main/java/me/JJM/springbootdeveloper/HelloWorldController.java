package me.JJM.springbootdeveloper;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

public class HelloWorldController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello World";

    }

    // http://localhost:8080.test -> "Hello Everything"\
    @GetMapping("/test")
    public String test() {
        return "Hello World!!!!!!";
    }

    @DeleteMapping("/test")
    public String deleteTest() {
        return "Hello World!!!!!!";
    }

    @PutMapping("/test")
    public String putTest() {
        return "Hello World!!!!!!";
    }
}
