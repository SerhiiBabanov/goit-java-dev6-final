package ua.goit.dev6.friend;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.dev6.EntityMapper;
import ua.goit.dev6.account.UserDAO;
import ua.goit.dev6.account.UserDTO;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FriendService {
    private final FriendRepository friendRepository;
    private final EntityMapper mapper;
    private final FriendDAO friendDAO;
    public List<UserDTO> listAllFriend(UUID id) {
        return friendRepository.findAllFriends(id)
                .stream()
                .map(friendDAO -> friendDAO.friend)
                .map(mapper::userToDTO)
                .collect(Collectors.toList());
    }
//    public void addFriend(UserDTO user, UserDTO friend){
//        friendDAO.setUser(mapper.userToDAO(user));
//        friendDAO.setFriend(mapper.userToDAO(friend));
//        friendRepository.save(friendDAO);
//    }
}
