package ua.goit.dev6.abaut;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("about")
public class AboutController {
    @GetMapping
    private ModelAndView viewAboutPage(){
        return new ModelAndView("about");
    }
}