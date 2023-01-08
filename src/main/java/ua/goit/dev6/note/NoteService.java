package ua.goit.dev6.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.dev6.EntityMapper;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository repository;
    private final EntityMapper mapper;

    public NoteDTO findById(UUID id) {
        Optional<NoteDAO> optional = repository.findById(id);
        return optional
                .map(mapper::noteToDTO)
                .orElseGet(() -> {return null;});
    }

    public List<NoteDTO> findAll() {
        return repository.findAll().stream()
                .map(mapper::noteToDTO)
                .collect(Collectors.toList());
    }

    public NoteDTO save(NoteDTO dto) {
        if (dto.getId() == null){
            dto.setId(UUID.randomUUID());
        }
        NoteDAO dao = repository.save(mapper.noteToDAO(dto));
        return mapper.noteToDTO(dao);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }

    public List<NoteDTO> findByUserId(UUID id){
        return repository.findByUser_Id(id).stream()
                .map(mapper::noteToDTO)
                .collect(Collectors.toList());
    }

    public List<NoteDTO> findPublicByUserId(UUID id){
        return repository.findByUser_Id(id).stream()
                .filter(noteDTO -> noteDTO.getAccessType().equals(AccessType.PUBLIC))
                .map(mapper::noteToDTO)
                .collect(Collectors.toList());
    }

}

