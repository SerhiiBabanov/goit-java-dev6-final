package ua.goit.dev6;

import org.springframework.stereotype.Service;

@Service
public interface Converter<T, E> {
    T fromDaoToDto(E dao);
    E fromDtoToDao(T dto);
}
