package ua.goit.dev6.note;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.dev6.account.UserDTO;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/findAll")
    private ModelAndView findAll(){
        ModelAndView result = new ModelAndView("/notes/notes");
        List<NoteDTO> notes = noteService.findAll();
        result.addObject("notes", notes);
        return result;
    }

    @GetMapping("/create")
    private ModelAndView getCreateForm() {
        ModelAndView result = new ModelAndView("/notes/createNoteForm");
        NoteDTO noteDTO = new NoteDTO();
        result.addObject("note", noteDTO);
        return result;
    }

    @GetMapping("/{id}")
    private ModelAndView getUpdateForm(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("/notes/updateNoteForm");
        result.addObject("note", noteService.findById(id));
        return result;
    }

    @PostMapping("/save")
    private ModelAndView save(@Validated @ModelAttribute("note") NoteDTO note){
        UserDTO user = new UserDTO(); // add another method
        note.setUser(user);
        noteService.save(note);
        return findAll();
    }

    @GetMapping("/delete")
    private RedirectView delete(@RequestParam("id") String id) {
        noteService.deleteById(UUID.fromString(id));
        return new RedirectView("/notes/findAll");
    }

}
