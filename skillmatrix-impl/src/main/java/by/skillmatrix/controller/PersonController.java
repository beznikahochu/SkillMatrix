package by.skillmatrix.controller;

import by.skillmatrix.dto.person.PersonCreationDto;
import by.skillmatrix.dto.person.PersonDto;
import by.skillmatrix.dto.user.UserFullInfoDto;
import by.skillmatrix.param.PageParams;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.service.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "api/people")
@Tag(name = "4. Person Controller", description = "works with people")
public class PersonController {

    private final PersonService personService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create new person")
    public PersonDto create(@RequestBody PersonCreationDto personDto) {
        log.info("Try to create new person with login: {}", personDto);

        PersonDto createdPerson = personService.create(personDto);

        log.info("Return created person: {}", createdPerson);
        return createdPerson;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    @Operation(summary = "Get people")
    public List<PersonDto> getAll(@ParameterObject PageParams page, @RequestParam(required = false) String sort) {
        log.info("Find people");

        List<PersonDto> result = personService.findAll(page, sort);

        log.info("Return size: {}", result.size());
        return result;
    }
}
