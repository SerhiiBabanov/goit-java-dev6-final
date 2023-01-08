package ua.goit.dev6.note;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.goit.dev6.account.UserDAO;

import java.util.Set;
import java.util.UUID;

//@Data
@Getter
@Setter
@Entity
@Table(name = "notes")
public class NoteDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 96)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false, length = 20)
    private Category category;
    @Column(name = "content", nullable = false, length = 9600)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "access_type", nullable = false, length = 20)
    private AccessType accessType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDAO user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends_notes",
            joinColumns = @JoinColumn(name = "friend_note_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserDAO> users;

}
