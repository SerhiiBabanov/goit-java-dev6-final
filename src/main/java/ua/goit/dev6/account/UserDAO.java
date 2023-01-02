package ua.goit.dev6.account;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import ua.goit.dev6.note.NoteDAO;
import ua.goit.dev6.roles.RoleDAO;

import java.util.*;

@Entity
@Getter
@Setter
@Table(name = "users")
public class UserDAO {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false, length = 60, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @OneToMany(mappedBy = "user")
    private Set<NoteDAO> notes = new LinkedHashSet<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_relation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleDAO> roles = new ArrayList<>();

}
