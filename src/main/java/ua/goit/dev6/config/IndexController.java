package ua.goit.dev6.config;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class IndexController {
    @GetMapping({"/"})
    public RedirectView homepage() {
        return new RedirectView("/note/list");
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/accessdenied")
    public String deniedPage() {
        return "errors/forbidden";
    }

    //add only for test purposes, after adding page with public notes this should be deleted
    @GetMapping("/testaccess")
    @ResponseBody
    public String testAccess() {
        return "testaccess";
    }
}
