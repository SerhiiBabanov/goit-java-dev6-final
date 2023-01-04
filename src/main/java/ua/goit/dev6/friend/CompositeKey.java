package ua.goit.dev6.friend;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
public class CompositeKey implements Serializable {
    @Column(name = "user_id")
    private UUID userId;
    @Column(name = "friend_id")
    private UUID friendId;

    public CompositeKey() {
    }

    public CompositeKey(UUID userId, UUID friendId) {
        this.userId = userId;
        this.friendId = friendId;
    }
}
