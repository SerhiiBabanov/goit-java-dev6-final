package ua.goit.dev6.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.account.UserDAO;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.account.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/friends")
public class FriendController {
    private final UserService userService;
//    private final FriendService friendService;


    @GetMapping
    private ModelAndView findAllFriend(){
        ModelAndView result = new ModelAndView("friends/friends");
        UserDTO authorizedUserId = userService.getAuthorizedUser();
        System.out.println( userService.allFriend(authorizedUserId));
        result.addObject("user",userService.allFriend(authorizedUserId));
//        result.addObject("users",friendService.listAllFriend(authorizedUserId));
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
//        friendService.addFriend(userService.getAuthorizedUser(),userService.getById(id));
        return "redirect:/friends";
    }

    @GetMapping("/delete/{id}")
    private void delete(@PathVariable("id") UUID id) {
//        friendService.deleteFriend(id);
        throw new ResponseStatusException(HttpStatus.OK, "Note deleted");
    }

}
