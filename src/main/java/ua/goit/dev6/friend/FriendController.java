package ua.goit.dev6.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.dev6.account.UserService;
import ua.goit.dev6.note.AccessType;
import ua.goit.dev6.note.NoteDTO;
import ua.goit.dev6.note.NoteService;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
@RequestMapping("/friends")
public class FriendController {
    private final NoteService noteService;
    private final UserService userService;



    @GetMapping
    private ModelAndView findAll(){
        ModelAndView result = new ModelAndView("friends/friends");
//        UUID authorizedUserId = userService.getAuthorizedUser().getId();
//        List<NoteDTO> notes = noteService.findByUserId(authorizedUserId);
//        result.addObject("notes", notes);
        return result;
    }

//    @GetMapping("/add")
//    private String getCreateForm() {
//        return "friends/add";
//    }
//
//    @PostMapping
//    private ModelAndView save(@Validated @ModelAttribute("note") NoteDTO note, BindingResult bindingResult){
//        if (bindingResult.hasErrors()) {
//            ModelAndView model = new ModelAndView("error/noteCreateError");
//            model.addObject("errors", bindingResult.getFieldErrors());
//            return model;
//        }
//        if (Objects.nonNull(note.getId())){
//            note.setId(null);
//        }
//        note.setUser(userService.getAuthorizedUser());
//        noteService.save(note);
//        return findAll();
//    }
//
//    @DeleteMapping("/{id}")
//    private void delete(@PathVariable("id") UUID id) {
//        noteService.deleteById(id);
//        throw new ResponseStatusException(HttpStatus.OK, "Note deleted");
//    }
//
//
//    @ModelAttribute("note")
//    private NoteDTO getDefaultProduct() {
//        return new NoteDTO();
//    }

}
