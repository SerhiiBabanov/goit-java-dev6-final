package ua.goit.dev6;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ua.goit.dev6.account.UserDAO;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.note.NoteDAO;
import ua.goit.dev6.note.NoteDTO;

import java.time.LocalDate;

@Mapper(componentModel = "Spring", imports = {LocalDate.class})
public interface EntityMapper {

    @Mapping(target = "passwordConfirm", ignore = true)
    UserDTO userToDTO(UserDAO userDAO);
    UserDAO userToDAO(UserDTO userDTO);


    NoteDTO noteToDTO(NoteDAO dao);
    NoteDAO noteToDAO(NoteDTO dto);
}
