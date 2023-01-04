package ua.goit.dev6.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.roles.RoleService;

import java.util.UUID;

@RequiredArgsConstructor
@Secured(value = {"ROLE_ADMIN"})
@RequestMapping("/users")
@Controller
@Slf4j
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @GetMapping("/create")
    public String saveForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("roles", roleService.listAll());
        model.addAttribute("userForm", userDTO);
        return "users/createUserForm";
    }

    @PostMapping("/create")
    public String saveUser(@ModelAttribute(name = "userForm") UserDTO userDTO, BindingResult bindingResult, Model model) {
        log.info("Handling save users: " + userDTO);
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.listAll());
            return "users/createUserForm";
        }
        userService.save(userDTO);
        return "redirect:/users/list";
    }

    @GetMapping("/changePassword/{id}")
    public ModelAndView changeUserPasswordForm(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("users/changePassword");
        UserDTO usersDto = userService.getById(id);
        result.addObject("userForm", usersDto);
        return result;
    }

    @PostMapping("/changePassword")
    public String changeUserPassword(@ModelAttribute(name = "userForm") UserDTO userDTO, BindingResult bindingResult) {
        log.info("Handling save users: " + userDTO);
        userValidator.validatePassword(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "users/changePassword";
        }
        userService.updateUserPassword(userDTO);
        return "redirect:/users/list";
    }

    @PostMapping("/updateUserOpenData")
    public String updateUserWithoutPassword(@ModelAttribute(name = "userForm") UserDTO userDTO,
                                            BindingResult bindingResult, Model model) {
        log.info("Handling save users: " + userDTO);
        userValidator.validateEmail(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.listAll());
            return "users/editUserForm";
        }
        userService.updateUserEmailAndRole(userDTO);
        return "redirect:/users/list";
    }

//    @Secured(value = {"ROLE_ADMIN"})
    @GetMapping("/list")
    public ModelAndView findAllUsers() {
        log.info("Handling find all users request");
        ModelAndView model = new ModelAndView("users/users");
        model.addObject("users", userService.listAll());
        return model;
    }

    @GetMapping("/update/{id}")
    public ModelAndView updateUserById(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("users/editUserForm");
        UserDTO usersDto = userService.getById(id);
        result.addObject("roles", roleService.listAll());
        result.addObject("userForm", usersDto);
        return result;
    }

    @GetMapping("/delete/{id}")
    public String deleteUserByIdForm(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return "redirect:/users/list";
    }
}
