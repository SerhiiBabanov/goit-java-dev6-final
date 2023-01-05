package ua.goit.dev6.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.roles.RoleService;
import ua.goit.dev6.signup.UserValidator;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/users")
@Controller
@Secured(value = {"ROLE_ADMIN"})
@Slf4j
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UserValidator userValidator;

    @GetMapping
    public ModelAndView findAllUsers() {
        log.info("Handling find all users request");
        ModelAndView model = new ModelAndView("users/users");
        model.addObject("users", userService.listAll());
        return model;
    }

    @GetMapping("/create")
    public String saveForm(Model model) {
        UserDTO userDTO = new UserDTO();
        model.addAttribute("roles", roleService.listAll());
        model.addAttribute("userForm", userDTO);
        return "users/createUserForm";
    }

    @PostMapping
    public String saveUser(@ModelAttribute(name = "userForm") UserDTO userDTO, BindingResult bindingResult, Model model) {
        log.info("Handling save users: " + userDTO);
        userValidator.validate(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.listAll());
            return "users/createUserForm";
        }
        userService.save(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/password/{id}")
    public ModelAndView changeUserPasswordForm(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("users/changePassword");
        UserDTO usersDto = userService.getById(id);
        result.addObject("userForm", usersDto);
        return result;
    }

    @PutMapping("/password/{id}")
    public String changeUserPassword(@Validated @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        log.info("Handling save users: " + userDTO);
        userValidator.validatePassword(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return "users/changePassword";
        }
        userService.updateUserPassword(userDTO);
        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public ModelAndView updateUserById(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("users/editUserForm");
        UserDTO usersDto = userService.getById(id);
        result.addObject("roles", roleService.listAll());
        result.addObject("user", usersDto);
        return result;
    }

    @PutMapping("/{id}")
    public String updateUserWithoutPassword(@RequestBody UserDTO userDTO, BindingResult bindingResult,
                                            Model model, @PathVariable UUID id) {
        log.info("Handling save users: " + userDTO);
        //перевірити в юзерадто, який приходить імейл, порівняти старий та новий імейли. якщо рівні - не робити валідацію.
        userValidator.validateEmail(userDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("roles", roleService.listAll());
            throw new ResponseStatusException(HttpStatus.OK, "User Updated");
        }
        userService.updateUserEmailAndRole(userDTO);
        throw new ResponseStatusException(HttpStatus.OK, "User Updated");
    }

    @DeleteMapping("/{id}")
    public void deleteUserByIdForm(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        throw new ResponseStatusException(HttpStatus.OK, "User deleted");
    }
}