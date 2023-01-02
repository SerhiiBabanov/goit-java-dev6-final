package ua.goit.dev6.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.dev6.account.UserDTO;
import ua.goit.dev6.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleDAO getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Role with name " +
                name + " doesn't exist"));
    }

    public List<RoleDAO> listAll() {
        return roleRepository.findAll();
    }

}
