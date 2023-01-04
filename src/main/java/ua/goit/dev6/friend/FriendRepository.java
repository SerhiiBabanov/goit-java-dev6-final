package ua.goit.dev6.friend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FriendRepository extends JpaRepository<FriendDAO, UUID> {
//    @Query("SELECT DISTINCT f FROM FriendDAO f LEFT JOIN FETCH  ")
//    List<UserDAO> findAllFriends();
}
