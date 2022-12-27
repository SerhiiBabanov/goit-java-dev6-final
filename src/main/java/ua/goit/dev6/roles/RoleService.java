package ua.goit.dev6.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.dev6.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleDAO getByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new NotFoundException("Role with name " +
                name + " doesn't exist"));
    }
}
