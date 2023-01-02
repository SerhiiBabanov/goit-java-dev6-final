package ua.goit.dev6.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequiredArgsConstructor
@Controller
@RequestMapping("/contact")
public class ContactController {
    @GetMapping
    private ModelAndView viewContactPage(){
        return new ModelAndView("contact");
    }
}
