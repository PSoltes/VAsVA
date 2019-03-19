package vava.soltesvasko.lezeckastena;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

}