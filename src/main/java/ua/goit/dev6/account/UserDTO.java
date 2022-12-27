package ua.goit.dev6.account;

import lombok.Data;
import ua.goit.dev6.note.NoteDAO;
import ua.goit.dev6.roles.RoleDAO;

import java.util.*;

@Data
public class UserDTO {

    private UUID id;
    private String email;
    private String password;
    private String passwordConfirm;
    private Set<NoteDAO> notes = new LinkedHashSet<>();
    private List<RoleDAO> roles = new ArrayList<>();
}
