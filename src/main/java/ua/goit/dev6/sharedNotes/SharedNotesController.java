package ua.goit.dev6.sharedNotes;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.account.UserService;

import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/sharednotes")
public class SharedNotesController {
    private final UserService userService;

    @GetMapping
    public ModelAndView getSavedPublicNotes(){
        ModelAndView result = new ModelAndView("sharedNotes/sharedNotes");
        UserDTO authUser = userService.getAuthorizedUser();
        result.addObject("notes",userService.getSavedPublicNotes(authUser));
        return result;
    }

    @GetMapping("/add/{id}")
    public ModelAndView addNoteToSavedPublicNotes(@PathVariable("id") UUID id) {
        userService.savePublicNote(userService.getAuthorizedUser(),id);
        return getSavedPublicNotes();
    }

    @GetMapping("/delete/{id}")
    public ModelAndView deletePublicNoteFromSaved(@PathVariable("id") UUID id) {
        userService.deleteSavedPublicNote(userService.getAuthorizedUser(),id);
        return getSavedPublicNotes();
    }
}
