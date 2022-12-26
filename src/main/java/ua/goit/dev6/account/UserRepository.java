package ua.goit.dev6.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDAO, String> {
    public Optional<UserDAO> findByUsername(String email);
}
