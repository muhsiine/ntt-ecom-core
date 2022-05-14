package ma.nttsquad.nttecomcore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductCtrl {

    @GetMapping(value={"/",""})
    public String test(){
        return "test Prodcut Controller";
    }

}
