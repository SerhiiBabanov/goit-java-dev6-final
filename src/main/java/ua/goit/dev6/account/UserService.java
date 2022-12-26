package ua.goit.dev6.account;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.dev6.EntityMapper;
import ua.goit.dev6.exception.NotFoundException;
import ua.goit.dev6.exception.ValidationException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final EntityMapper mapper;

    public UserDTO save(UserDTO usersDto) throws ValidationException {
        validateUserDto(usersDto);
        UserDAO h = mapper.userToDao(usersDto);
        UserDAO savedUser = userRepository.save(h);
        return mapper.userToDTO(savedUser);
    }

    public List<UserDTO> listAll() {
        return userRepository.findAll()
                .stream()
                .map(mapper::userToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO getById(UUID id) {
        return mapper.userToDTO(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id " + id + " doesn't exist")));
    }

    public void deleteById(UUID id) {
        getById(id);
        userRepository.deleteById(id);
    }

    private void validateUserDto (UserDTO usersDto) throws ValidationException {
        if(isNull(usersDto)) {
            throw new ValidationException("Object user is null");
        }

        if(isNull(usersDto.getEmail()) || usersDto.getEmail().isEmpty()) {
            throw new ValidationException("Email is empty");
        }

        if(isNull(usersDto.getPassword()) || usersDto.getPassword().isEmpty()) {
            throw new ValidationException("Password is empty");
        }
    }
}
