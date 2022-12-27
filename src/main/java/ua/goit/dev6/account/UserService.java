package ua.goit.dev6.account;

import java.util.List;
import java.util.UUID;

public interface UserService {
    
    UserDTO save(UserDTO usersDto);
    List<UserDTO> listAll();
    UserDTO getById(UUID id);
    void deleteById(UUID id);
}
