package ua.goit.dev6.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.account.UserService;
import ua.goit.dev6.note.NoteDTO;
import ua.goit.dev6.note.NoteService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Secured(value = {"ROLE_ADMIN"})
@Controller
@RequestMapping("/friends")
public class FriendController {
    private final UserService userService;
    private final NoteService noteService;
    @GetMapping
    public ModelAndView findAllFriend(){
        ModelAndView result = new ModelAndView("friends/friends");
        UserDTO authorizedUser = userService.getAuthorizedUser();
        result.addObject("users",userService.allFriend(authorizedUser));
        return result;
    }

    @GetMapping("/add")
    public ModelAndView potentialFriends() {
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
    public String delete(@PathVariable("id") UUID id) {
        userService.deleteFriend(userService.getAuthorizedUser().getId(),id);
        return "redirect:/friends";
    }
    @GetMapping("/view/{id}")
    public ModelAndView viewNoteFriend(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("friends/friendNotes");
        List<NoteDTO> notes = noteService.findPublicByUserId(id);
        result.addObject("notes", notes);
        return result;
    }

}
