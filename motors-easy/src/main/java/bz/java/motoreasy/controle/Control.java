package bz.java.motoreasy.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Control {
    @GetMapping({"/", "/home"})
    public String callHomePage() {
        return "index";
    }
}
