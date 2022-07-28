package by.skillmatrix.mapper;

import by.skillmatrix.config.MatrixMapperConfig;
import by.skillmatrix.dto.person.PersonCreationDto;
import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MatrixMapperConfig.class)
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person toPersonEntity(PersonCreationDto user);

    PersonDto toPersonDto(Person user);
}
