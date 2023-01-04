package ua.goit.dev6.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class UpdateInfo {
    private final UserService userService;


    @GetMapping("/settings/change")
    public String changePassword() {
        return "account/change";
    }

    @PostMapping("/settings/change")
    public String changePassword(@RequestParam(value = "oldPassword") String oldPassword,
                                 @RequestParam(value = "newPassword") String newPassword,
                                 @RequestParam(value = "repeatPassword") String repeatPassword,
                                 BindingResult bindingResult) {

        if (!userService.checkPassword(oldPassword)){
            bindingResult.rejectValue("oldPassword", "Wrong password");
            return "account/change";
        }

        userService.changePassword(bindingResult, newPassword, repeatPassword);

        if (bindingResult.hasErrors()) {
            return "account/change";
        }

        return "redirect:/";

    }
}
