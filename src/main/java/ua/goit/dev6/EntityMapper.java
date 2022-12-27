package ua.goit.dev6;

import org.mapstruct.Mapper;
import ua.goit.dev6.account.UserDAO;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.note.NoteDAO;
import ua.goit.dev6.note.NoteDTO;

import java.time.LocalDate;

@Mapper(componentModel = "Spring", imports = {LocalDate.class})
public interface EntityMapper {

    UserDTO userToDTO(UserDAO userDAO);

    UserDAO userToDao(UserDTO userDTO);

    NoteDTO noteToDTO(NoteDAO dao);
    NoteDAO noteToDAO(NoteDTO dto);
}
