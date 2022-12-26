package ua.goit.dev6.account;

import org.springframework.stereotype.Service;
import ua.goit.dev6.Converter;

@Service
public class UserConverter implements Converter<UserDTO, UserDAO> {

    @Override
    public UserDTO fromDaoToDto(UserDAO dao) {
        UserDTO dto = new UserDTO();
        return dto;
    }

    @Override
    public UserDAO fromDtoToDao(UserDTO dto) {
        UserDAO dao = new UserDAO();
        return dao;
    }
}
