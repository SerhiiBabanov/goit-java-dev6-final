package ua.goit.dev6.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping({"/"})
    public String homepage() {
        return "redirect:/notes";
    }

    @GetMapping("/accessdenied")
    public String deniedPage() {
        return "error/nopermission";
    }
}
