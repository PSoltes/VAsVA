package vava.soltesvasko.lezeckastena;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @RequestMapping(value = "/Insult", method=RequestMethod.GET)
    @ResponseBody
    public String index(@RequestParam("name") String name) {
        return "Fuck you sincerely " + name;
    }

}