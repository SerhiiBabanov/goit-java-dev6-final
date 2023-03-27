package ua.goit.dev6.account;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.dev6.EntityMapper;
import ua.goit.dev6.exception.NotFoundException;
import ua.goit.dev6.note.NoteDAO;
import ua.goit.dev6.note.NoteDTO;
import ua.goit.dev6.note.NoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final EntityMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public UserDTO save(UserDTO usersDto) {
        if (usersDto.getId() == null) {
            usersDto.setId(UUID.randomUUID());
        }
        usersDto.setPassword(passwordEncoder.encode(usersDto.getPassword()));
        UserDAO savedUser = userRepository.save(mapper.userToDAO(usersDto));
        return mapper.userToDTO(savedUser);
    }

    public UserDTO updateUserEmailAndRole(UserDTO userDTO) {
        UserDTO updateUser = getById(userDTO.getId());
        updateUser.setEmail(userDTO.getEmail());
        updateUser.setRoles(userDTO.getRoles());
        userRepository.save(mapper.userToDAO(updateUser));
        return updateUser;
    }
    public UserDTO updateUserPassword(UserDTO updateUser) {
        UserDTO userDTO = getById(updateUser.getId());
        userDTO.setPassword(passwordEncoder.encode(updateUser.getPassword()));
        userRepository.save(mapper.userToDAO(userDTO));
        return userDTO;
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
        userRepository.deleteById(id);
    }


    public Optional<UserDTO> findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(mapper::userToDTO);
    }

    public boolean checkPassword(String password) {
        return passwordEncoder.matches(password, getAuthorizedUser().getPassword());
    }

    public UserDTO getAuthorizedUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(userDetails.getUsername()).map(mapper::userToDTO)
                .orElseThrow(() -> new NotFoundException("Error with getting autorized user"));
    }

    public List<UserDTO> allFriend(UserDTO user){
        UserDAO userDAO = mapper.userToDAO(user);
        return userDAO.getFriends().stream().map(mapper::userToDTO).collect(Collectors.toList());
    }

    public void addFriend(UUID userId,UUID friendToAddId){
        Optional<UserDAO> authUser = userRepository.findById(userId);
        Optional<UserDAO> newFriend = userRepository.findById(friendToAddId);
        if (authUser.isPresent() && newFriend.isPresent()){
            authUser.get().getFriends().add(newFriend.get());
            userRepository.save(authUser.get());
        }
    }

    public void deleteFriend(UUID userId,UUID friendToRemoveId){
        Optional<UserDAO> authUser = userRepository.findById(userId);
        Optional<UserDAO> userToRemoveFromFriend = userRepository.findById(friendToRemoveId);
        if (authUser.isPresent() && userToRemoveFromFriend.isPresent()){
            authUser.get().getFriends().remove(userToRemoveFromFriend.get());
            userRepository.save(authUser.get());
        }
    }

    public List<NoteDTO> getSavedPublicNotes(UserDTO user){
        UserDAO userDAO = mapper.userToDAO(user);
        return userDAO.getFriendsNotes().stream().map(mapper::noteToDTO).collect(Collectors.toList());
    }

    public void savePublicNote(UserDTO user, UUID noteToAddId){
        UserDAO userDAO = mapper.userToDAO(user);
        Optional<NoteDAO> noteToAdd = noteRepository.findById(noteToAddId);
        if (noteToAdd.isPresent()){
            userDAO.getFriendsNotes().add(noteToAdd.get());
            userRepository.save(userDAO);
        }
    }

    public void deleteSavedPublicNote(UserDTO user, UUID noteToDeleteId){
        UserDAO userDAO = mapper.userToDAO(user);
        Optional<NoteDAO> noteToDelete = noteRepository.findById(noteToDeleteId);
        if (noteToDelete.isPresent()){
            userDAO.getFriendsNotes().remove(noteToDelete.get());
            userRepository.save(userDAO);
        }
    }
}
