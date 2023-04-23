package ua.goit.dev6.note;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ua.goit.dev6.account.UserDAO;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.BDDAssertions.then;


@DataJpaTest
@PrepareForTest({ UUID.class })
@RunWith(PowerMockRunner.class)
class NoteRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private NoteRepository noteRepository;

    @Test
    void testFindByUserId_returnsNotesDetails() {
        //given
        UserDAO savedUser = testEntityManager.persistFlushFind(getTestUser());
        NoteDAO note = getTestNote(savedUser);
        NoteDAO savedNote = testEntityManager.persistFlushFind(note);


        //when
        List<NoteDAO> notes = noteRepository.findByUserId(savedUser.getId());
        //then
        then(notes.size()).isEqualTo(1);
        then(notes.get(0)).isNotNull();
        then(notes.get(0).getUser().getId()).isEqualTo(savedUser.getId());
        then(notes.get(0).getName()).isEqualTo("Test note");

    }

    private UserDAO getTestUser(){
        UserDAO user = new UserDAO();

        user.setEmail("test@test.com");
        user.setPassword("test");
        return user;
    }

    private NoteDAO getTestNote(UserDAO user){
        NoteDAO note = new NoteDAO();
        note.setName("Test note");
        note.setContent("Content");
        note.setCategory(Category.DEFAULT);
        note.setAccessType(AccessType.PRIVATE);
        note.setUser(user);
        return note;
    }
}
