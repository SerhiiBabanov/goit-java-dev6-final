package ua.goit.dev6.friend;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import ua.goit.dev6.account.UserDAO;

@Entity
@Table(name = "friend")
@NoArgsConstructor
public class FriendDAO {
    @EmbeddedId
    CompositeKey id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    protected UserDAO user;

    @MapsId("friendId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id", referencedColumnName = "id")
    protected UserDAO friend;

    public FriendDAO(UserDAO user, UserDAO friend) {
        this.user = user;
        this.friend = friend;
    }

}
