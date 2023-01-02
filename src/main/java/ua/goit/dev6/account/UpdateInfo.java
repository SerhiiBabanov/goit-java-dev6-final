package ua.goit.dev6.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ua.goit.dev6.config.UserPrincipal;
import ua.goit.dev6.roles.RoleService;
import ua.goit.dev6.signin.SecurityService;
import ua.goit.dev6.signup.UserValidator;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UpdateInfo {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final RoleService roleService;
    private final SecurityService securityService;
    private final UserValidator userValidator;

    @GetMapping("/settings/change")
    public String changePassword(Model model) {
        return "account/change";
    }

    @PostMapping("/settings/change")
    public String changePassword(Model model, Authentication authentication,
                                 @RequestParam(value = "oldPassword") String oldPassword,
                                 @RequestParam(value = "newPassword") String newPassword,
                                 @RequestParam(value = "repeatPassword") String repeatPassword) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        String userEmail = principal.getUsername();

        List<UserDTO> byName = userService.findByName(userEmail);
        UserDTO user = byName.get(0);

        model.addAttribute("user", user);

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            //!passwordEncoder.matches(passwordEncoder.encode(oldPassword), user.getPassword())) {
            model.addAttribute("oldPasswordErr", "Old password is invalid!");
            return "account/change";
        } else if (!newPassword.equals(repeatPassword)) {
            model.addAttribute("repeatPasswordErr", "New passwords aren't equals!");
            return "account/change";
        } else if (newPassword.length() < 8 || newPassword.length() > 100) {
            model.addAttribute("passwordErr", "Please use between 8 and 100 characters!");
            return "account/change";
        }

        user.setPassword(newPassword);
        userService.save(user);

        return "redirect:/";
    }
}
