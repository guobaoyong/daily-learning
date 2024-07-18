package qqzsh.top.soufun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SoufunApplication {

    public static void main(String[] args) {
        SpringApplication.run(SoufunApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, zsh";
    }

}
