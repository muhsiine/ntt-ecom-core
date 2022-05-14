package ma.nttsquad.nttecomcore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PingCtrl {

    @GetMapping("/ping")
    public String ping(){
        return "Hello Squad";
    }
}
