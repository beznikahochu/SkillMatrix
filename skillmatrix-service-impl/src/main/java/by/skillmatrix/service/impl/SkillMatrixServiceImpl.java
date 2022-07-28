package by.skillmatrix.service.impl;


import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.entity.*;
import by.skillmatrix.entity.Person;
import by.skillmatrix.excel.SkillMatrixExcelBuilder;
import by.skillmatrix.exception.IncorrectDateException;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.FullSkillMatrixMapper;
import by.skillmatrix.mapper.SkillMatrixMapper;
import by.skillmatrix.param.MatrixSearchParams;
import by.skillmatrix.param.PageParams;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.repository.PersonRepository;
import by.skillmatrix.repository.criteria.SkillMatrixCriteria;
import by.skillmatrix.repository.page.PageOptions;
import by.skillmatrix.repository.sorttype.SkillMatrixSortType;
import by.skillmatrix.service.SkillMatrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillMatrixServiceImpl implements SkillMatrixService {

    private final SkillMatrixRepository skillMatrixRepository;
    private final SkillMatrixSchemeRepository schemeDao;
    private final SkillCategoryRepository categoryDao;
    private final PersonRepository personRepository;
    private final SkillMatrixMapper skillMatrixMapper;
    private final FullSkillMatrixMapper fullSkillMatrixMapper;
    private final SkillMatrixExcelBuilder excelBuilder;

    @Override
    @Transactional
    public SkillMatrixDto create(SkillMatrixCreationDto skillMatrixCreationDto) {
        log.debug("Try to save SkillMatrix: {}", skillMatrixCreationDto);

        SkillMatrix skillMatrix = skillMatrixMapper.toSkillMatrixEntity(skillMatrixCreationDto);

        Long userId = skillMatrixCreationDto.getPersonId();
        Long schemeId = skillMatrixCreationDto.getSchemeId();

        Person person = personRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Person not found by id"));
        SkillMatrixScheme matrixScheme = schemeDao.findById(schemeId)
                .orElseThrow(() -> new NotFoundException("SkillMatrixScheme not found by id"));

        skillMatrix.setPerson(person);
        skillMatrix.setSkillMatrixScheme(matrixScheme);
        skillMatrix.setCreationDate(LocalDate.now());
        skillMatrix.setCreationTime(LocalTime.now());

        SkillMatrix createdMatrix = skillMatrixRepository.save(skillMatrix);

        SkillMatrixDto skillMatrixDto = skillMatrixMapper.toSkillMatrixDto(createdMatrix);

        log.debug("Return saved SkillMatrix: {}", skillMatrixDto);
        return skillMatrixDto;
    }

    @Override
    @Transactional
    public SkillMatrixDto update(Long id, SkillMatrixModificationDto modificationDto) {
        log.debug("Try to update SkillMatrix with id: {}", id);

        SkillMatrix skillMatrix = skillMatrixMapper.toSkillMatrixEntity(modificationDto);
        skillMatrix.setId(id);
        SkillMatrix updatedSkillMatrix = skillMatrixRepository.save(skillMatrix);
        SkillMatrixDto createdSchemeDto = skillMatrixMapper.toSkillMatrixDto(updatedSkillMatrix);

        log.debug("Return updated SkillMatrix: {}", createdSchemeDto);
        return createdSchemeDto;
    }

    @Override
    @Transactional
    public SkillMatrixDto calkAvgAssessment(Long id) {
        log.debug("Try to calculate AvgAssessment of SkillMatrix with id: {}", id);

        skillMatrixRepository.calkAvgAssessment(id);
        SkillMatrix updatedMatrix = skillMatrixRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found by id"));
        SkillMatrixDto updatedSchemeDto = skillMatrixMapper.toSkillMatrixDto(updatedMatrix);

        log.debug("AvgAssessment of SkillMatrix with id {} updated", id);
        return updatedSchemeDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Try to delete SkillMatrix by id: {}", id);

        skillMatrixRepository.delete(id);

        log.debug("SkillMatrix with ID {} has been removed", id);
    }

    @Override
    public List<SkillMatrixDto> findByParams(PageParams pageParams, MatrixSearchParams params, String sort) {
        log.debug("Try to get all SkillMatrix");

        PageOptions pageOptions = new PageOptions(pageParams.getPage(), pageParams.getPageSize());
        SkillMatrixSortType sortType = getMatrixSortTypeByString(sort);

        checkDates(params.getFromDate(), params.getToDate());

        SkillMatrixCriteria criteria = SkillMatrixCriteria.builder()
                .schemeId(params.getSchemeId())
                .personId(params.getPersonId())
                .fromDate(params.getFromDate())
                .toDate(params.getToDate())
                .isEmployee(params.getIsEmployee())
                .build();//TODO Перенестри в маппер

        List<SkillMatrix> skillMatrixEntities = skillMatrixRepository
                .findByCriteria(criteria, pageOptions, sortType);
        List<SkillMatrixDto> result = skillMatrixEntities.stream()
                .map(skillMatrixMapper::toSkillMatrixDto)
                .collect(Collectors.toList());

        log.debug("The size of the returned list is {}", result.size());
        return result;
    }

    @Override
    public SkillMatrixDto findById(Long id) {
        log.debug("Find SkillMatrix by id: {}", id);

        SkillMatrix skillMatrix = skillMatrixRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found by id"));
        SkillMatrixDto result = skillMatrixMapper.toSkillMatrixDto(skillMatrix);

        log.debug("Return SkillMatrix: {}", result);
        return result;
    }

    @Override
    public SkillMatrixFullInfoDto findFullInfoById(Long id) {
        log.debug("Find full SkillMatrix by id: {}", id);

        SkillMatrix skillMatrix = findEntityWithFullInfoById(id);
        SkillMatrixFullInfoDto result = fullSkillMatrixMapper.toFullSkillMatrixEntity(skillMatrix);

        log.debug("Return SkillMatrix: {}", result);
        return result;
    }
    @Override
    public ResponseEntity<byte[]> exportMatrixToExcelById(Long id) {
        log.debug("Export SkillMatrix by id: {}", id);

        SkillMatrix skillMatrix = findEntityWithFullInfoById(id);
        byte[] excel = excelBuilder.build(skillMatrix);

        String fileName = skillMatrix.getName() + ".xlsx";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        ResponseEntity<byte[]> response = new ResponseEntity(excel, headers, HttpStatus.OK);

        log.debug("Export done");
        return response;
    }

    private SkillMatrix findEntityWithFullInfoById(Long id) {
        SkillMatrix skillMatrix = skillMatrixRepository.findWithAssessmentsById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found by id"));

        List<SkillAssessment> assessments = skillMatrix.getSkillAssessments();
        SkillMatrixScheme scheme = skillMatrix.getSkillMatrixScheme();
        List<SkillCategory> categories = categoryDao.findFullSkillCategoryBySchemeId(scheme.getId());
        scheme.setSkillCategories(categories);

        List<Skill> skills = categories.stream().flatMap(category -> category.getSkills()
                .stream()).collect(Collectors.toList());
        setSkillAssessments(skills,assessments);

        return skillMatrix;
    }

    private void setSkillAssessments(List<Skill> skills, List<SkillAssessment> assessments) {
        for (Skill skill: skills) {
            skill.setSkillAssessments(new ArrayList<>());
            for (SkillAssessment assessment: assessments) {
                if (assessment.getSkillId().equals(skill.getId())) {
                    skill.getSkillAssessments().add(assessment);
                    break;
                }
            }
        }
    }

    private SkillMatrixSortType getMatrixSortTypeByString(String type) {
        if (type == null) {
            return SkillMatrixSortType.CREATION_DATE_DESC;
        }
        switch (type) {
            case "date.a":
                return SkillMatrixSortType.CREATION_DATE_ASC;
            case "date.d":
                return SkillMatrixSortType.CREATION_DATE_DESC;
            case "assessment.a":
                return SkillMatrixSortType.AVG_ASSESSMENT_ASC;
            case "assessment.d":
                return SkillMatrixSortType.AVG_ASSESSMENT_DESC;
        }
        return SkillMatrixSortType.CREATION_DATE_DESC;
    }

    private void checkDates(LocalDate from, LocalDate to) {
        if (from != null && from.isAfter(LocalDate.now())) {
            throw new IncorrectDateException("Date " + from.toString() + " is after current moment");
        }
        if (to != null && to.isAfter(LocalDate.now())) {
            throw new IncorrectDateException("Date " + to.toString() + " is after current moment");
        }
        if (from == null || to == null) {
            return;
        }
        if (from.isAfter(to)) {
            throw new IncorrectDateException("Date " + from.toString() + " is after date " + to.toString());
        }
    }
}
