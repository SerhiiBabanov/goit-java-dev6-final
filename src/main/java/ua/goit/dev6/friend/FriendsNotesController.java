package ua.goit.dev6.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.account.UserService;
import ua.goit.dev6.note.NoteService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/friendsnotes")
public class FriendsNotesController {
    private final UserService userService;

    @GetMapping
    public ModelAndView findAllFriendsNotes(){
        ModelAndView result = new ModelAndView("friendsnotes/friendsNotes");
        UserDTO authUser = userService.getAuthorizedUser();
        result.addObject("notes",userService.allFriendsNotes(authUser));
        return result;
    }

    @GetMapping("/add/{id}")
    public String addFriendNote(@PathVariable("id") UUID id) {
        userService.addFriendNote(userService.getAuthorizedUser(),id);
        return "redirect:/friendsnotes";
    }

    @GetMapping("/delete/{id}")
    public String deleteFriendNote(@PathVariable("id") UUID id) {
        userService.deleteFriendNote(userService.getAuthorizedUser(),id);
        return "redirect:/friendsnotes";
    }
}
