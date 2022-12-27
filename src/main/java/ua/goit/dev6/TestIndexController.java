package ua.goit.dev6;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestIndexController {
    @GetMapping("/note/list")
    public String index(){
        return "notes/notes";
    }
}
