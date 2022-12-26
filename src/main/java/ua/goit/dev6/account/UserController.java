package ua.goit.dev6.account;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.dev6.exception.ValidationException;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/save")
    public ModelAndView saveForm() {
        ModelAndView model = new ModelAndView("user/save");
        model.addObject("users", userService.listAll());
        return model;
    }

    @PostMapping("/save")
    public RedirectView saveUsers(@Validated @ModelAttribute(name = "userDto") UserDTO usersDto) throws ValidationException {
        log.info("Handling save users: " + usersDto);
        userService.save(usersDto);
        RedirectView redirectView = new RedirectView("/user/findAll");
        return redirectView;
    }

    @GetMapping("/findAll")
    public ModelAndView findAllUsers() {
        log.info("Handling find all users request");
        ModelAndView model = new ModelAndView("user/findAll");
        model.getModelMap().addAttribute("users", userService.listAll());
        return model;
    }

    @GetMapping("/findById")
    public ModelAndView findById(@RequestParam(value = "id", required = false, defaultValue = "") String id) {
        log.info("Handling find by id request: " + id);
        ModelAndView model = new ModelAndView("user/findById");
        if (!(id.isEmpty() || id.equals(""))) {
            model.getModelMap().addAttribute("users", userService.getById(UUID.fromString(id)));
        }
        return model;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateUserById(@PathVariable("id") UUID id) {
        ModelAndView model = new ModelAndView("user/editForm");
        UserDTO usersDto = userService.getById(id);
        model.addObject("user", usersDto);
        return model;
    }
    

    @GetMapping("/delete/{id}")
    public RedirectView deleteUserByIdForm(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return new RedirectView("/user/findAll");
    }
}
