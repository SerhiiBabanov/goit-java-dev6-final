package ua.goit.dev6.note;


import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.dev6.account.UserDTO;

import java.util.UUID;

@Data
@NoArgsConstructor
public class NoteDTO {
    private UUID id;
    private String name;
    private String content;
    private AccessType accessType;
    private UserDTO user;
}
