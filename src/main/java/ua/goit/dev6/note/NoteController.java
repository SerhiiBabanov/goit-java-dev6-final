package ua.goit.dev6.note;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.account.UserService;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/notes")
public class NoteController {
    private final NoteService noteService;
    private final UserService userService;


    @GetMapping
    private ModelAndView findAll(){
        ModelAndView result = new ModelAndView("notes/notes");
        List<NoteDTO> notes = noteService.findAll();
        result.addObject("notes", notes);
        return result;
    }

    @GetMapping("/create")
    private String getCreateForm() {
        return "notes/createNoteForm";
    }

    @GetMapping("/{id}")
    private ModelAndView getEditForm(@PathVariable("id") UUID id) {
        ModelAndView result = new ModelAndView("notes/editNoteForm");
        NoteDTO note = noteService.findById(id);
        result.addObject("note", note);
        return result;
    }

    @PostMapping
    private String save(@Validated @ModelAttribute("note") NoteDTO note, BindingResult result){
        if (result.hasErrors()) {
            return "notes/createNoteForm";
        }
        note.setUser(userService.getAuthorizedUser());
        noteService.save(note);
        return "redirect:/notes";
    }
    @PutMapping("/{id}")
    private String update(@Validated @RequestBody NoteDTO note, BindingResult result, @PathVariable("id") UUID id){
        if (result.hasErrors()) {
            return "notes/createNoteForm";
        }
        note.setUser(userService.getAuthorizedUser());
        noteService.save(note);
        throw new ResponseStatusException(HttpStatus.OK, "Note Updated");
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") UUID id) {
        noteService.deleteById(id);
        throw new ResponseStatusException(HttpStatus.OK, "Note deleted");
    }
    @ModelAttribute("note")
    private NoteDTO getDefaultProduct() {
        return new NoteDTO();
    }

}
