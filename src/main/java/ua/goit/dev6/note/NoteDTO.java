package ua.goit.dev6.note;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.goit.dev6.account.UserDTO;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
public class NoteDTO {
    private UUID id;
    @Size(min = 4, message = "{validation.name.size.too_short}")
    @Size(max = 100, message = "{validation.name.size.too_long}")
    private String name;
    @NotNull(message = "{validation.name.category}")
    private Category category;
    private String content;
    @NotNull(message = "{validation.name.asses}")
    private AccessType accessType;
    private UserDTO user;
    private Set<UserDTO> users;
}
