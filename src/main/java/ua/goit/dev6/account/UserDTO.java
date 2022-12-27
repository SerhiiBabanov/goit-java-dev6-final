package ua.goit.dev6.account;

import lombok.Data;
import ua.goit.dev6.note.NoteDAO;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Data
public class UserDTO {

    private UUID id;
    private String email;
    private String password;
    private String passwordConfirm;
    private Set<NoteDAO> notes = new LinkedHashSet<>();
    private Set<RoleDAO> roles = new LinkedHashSet<>();
}
