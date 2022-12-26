package ua.goit.dev6;

import org.mapstruct.Mapper;
import ua.goit.dev6.account.UserDAO;
import ua.goit.dev6.account.UserDTO;

import java.time.LocalDate;

@Mapper(componentModel = "Spring", imports = {LocalDate.class})
public interface EntityMapper {

    UserDTO userToDTO(UserDAO userDAO);

    UserDAO userToDao(UserDTO userDTO);
}
