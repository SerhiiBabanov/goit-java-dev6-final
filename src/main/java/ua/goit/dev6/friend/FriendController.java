package ua.goit.dev6.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.account.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/friends")
public class FriendController {
    private final UserService userService;

    @GetMapping
    private ModelAndView findAllFriend(){
        ModelAndView result = new ModelAndView("friends/friends");
        UserDTO authorizedUserId = userService.getAuthorizedUser();
        result.addObject("users",userService.allFriend(authorizedUserId));
        return result;
    }

    @GetMapping("/add")
    private ModelAndView potentialFriends() {
        ModelAndView result = new ModelAndView("friends/addFriend");
        result.addObject("users", userService.listAll());
        return result;
    }
    @GetMapping("/add/{id}")
    public String addFriend(@PathVariable("id") UUID id) {
        userService.addFriend(userService.getAuthorizedUser().getId(),id);
        return "redirect:/friends";
    }

    @GetMapping("/delete/{id}")
    private String delete(@PathVariable("id") UUID id) {
        userService.deleteFriend(userService.getAuthorizedUser().getId(),id);
        return "redirect:/friends";
    }

}
