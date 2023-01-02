package ua.goit.dev6.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ua.goit.dev6.roles.RoleDAO;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, UUID> {
    Optional<UserDAO> findByEmail(String email);
}
