package ua.goit.dev6.note;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.dev6.Converter;
import ua.goit.dev6.account.UserConverter;

@Service
@RequiredArgsConstructor
public class NoteConverter implements Converter<NoteDTO, NoteDAO> {
    private final UserConverter converter;
    @Override
    public NoteDTO fromDaoToDto(NoteDAO dao) {
        NoteDTO dto = new NoteDTO();
        dto.setId(dao.getId());
        dto.setName(dao.getName());
        dto.setContent(dao.getContent());
        dto.setAccessType(dao.getAccessType());
        dto.setUser(converter.fromDaoToDto(dao.getUser()));
        return dto;
    }

    @Override
    public NoteDAO fromDtoToDao(NoteDTO dto) {
        NoteDAO dao = new NoteDAO();
        dao.setId(dto.getId());
        dao.setName(dto.getName());
        dao.setContent(dto.getContent());
        dao.setAccessType(dto.getAccessType());
        dao.setUser(converter.fromDtoToDao(dto.getUser()));
        return dao;
    }
}
