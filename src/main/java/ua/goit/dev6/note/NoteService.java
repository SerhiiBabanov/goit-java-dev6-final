package ua.goit.dev6.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteConverter converter;
    private final NoteRepository repository;

    public NoteDTO findById(UUID id) {
        Optional<NoteDAO> optional = repository.findById(id);
        return optional
                .map(converter::fromDaoToDto)
                .orElseGet(() -> {return null;});
    }

    public List<NoteDTO> findAll() {
        return repository.findAll().stream()
                .map(converter::fromDaoToDto)
                .collect(Collectors.toList());
    }

    public NoteDTO save(NoteDTO dto) {
        if (dto.getId() == null){
            dto.setId(UUID.randomUUID());
        }
        NoteDAO dao = repository.save(converter.fromDtoToDao(dto));
        return converter.fromDaoToDto(dao);
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
    }
}

