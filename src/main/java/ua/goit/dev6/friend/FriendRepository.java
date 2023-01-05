package ua.goit.dev6.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ua.goit.dev6.account.UserDAO;

import java.util.List;
import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<FriendDAO, UUID> {
    @Query("from FriendDAO f where f.user.id = :id")
    List<FriendDAO> findAllFriends(@Param("id") UUID id);
}
