package ua.goit.dev6.note;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ua.goit.dev6.account.UserDAO;

import java.util.UUID;
@Data
@Entity
@Table(name = "notes")
public class NoteDAO {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false, length = 96)
    @NotBlank(message = "Note's name should not be empty")
    private String name;

    @Column(name = "content", nullable = false, length = 9600)
    private String content;

    @Column(name = "access_type", nullable = false, length = 20)
    @NotNull(message = "Note should have access type")
    @Enumerated(EnumType.STRING)
    private AccessType accessType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id", nullable = false)
    private UserDAO user;



}
