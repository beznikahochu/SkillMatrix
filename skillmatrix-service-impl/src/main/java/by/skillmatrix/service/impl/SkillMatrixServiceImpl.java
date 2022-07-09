package by.skillmatrix.service.impl;


import by.skillmatrix.dto.skillmatrix.SkillMatrixCreationDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixFullInfoDto;
import by.skillmatrix.dto.skillmatrix.SkillMatrixModificationDto;
import by.skillmatrix.entity.SkillMatrixEntity;
import by.skillmatrix.entity.SkillMatrixSchemeEntity;
import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.entity.SkillEntity;
import by.skillmatrix.entity.SkillAssessmentEntity;
import by.skillmatrix.entity.SkillCategoryEntity;
import by.skillmatrix.excel.SkillMatrixExcelBuilder;
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMatrixMapper;
import by.skillmatrix.param.MatrixSearchParams;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.repository.EmployeeRepository;
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

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SkillMatrixServiceImpl implements SkillMatrixService {

    private final SkillMatrixRepository skillMatrixRepository;
    private final SkillMatrixSchemeRepository schemeRepository;
    private final SkillCategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillMatrixMapper skillMatrixMapper;
    private final SkillMatrixExcelBuilder excelBuilder;

    @Override
    @Transactional
    public SkillMatrixDto create(SkillMatrixCreationDto skillMatrixCreationDto) {
        log.debug("Trying to save SkillMatrix: {}", skillMatrixCreationDto);

        SkillMatrixEntity skillMatrixEntity = skillMatrixMapper.toSkillMatrixEntity(skillMatrixCreationDto);

        Long userId = skillMatrixCreationDto.getUserId();
        Long schemeId = skillMatrixCreationDto.getSchemeId();

        EmployeeEntity employeeEntity = employeeRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found by id"));
        SkillMatrixSchemeEntity matrixScheme = schemeRepository.findById(schemeId)
                .orElseThrow(() -> new NotFoundException("SkillMatrixScheme not found by id"));

        skillMatrixEntity.setEmployee(employeeEntity);
        skillMatrixEntity.setSkillMatrixScheme(matrixScheme);
        skillMatrixEntity.setCreationDate(LocalDateTime.now());

        SkillMatrixEntity createdMatrix = skillMatrixRepository.save(skillMatrixEntity);

        SkillMatrixDto skillMatrixDto = skillMatrixMapper.toSkillMatrixDto(createdMatrix);

        log.debug("Return saved SkillMatrix: {}", skillMatrixDto);
        return skillMatrixDto;
    }

    @Override
    @Transactional
    public SkillMatrixDto update(Long id, SkillMatrixModificationDto modificationDto) {
        log.debug("Trying to update SkillMatrix with id: {}", id);

        SkillMatrixEntity skillMatrixEntity = skillMatrixMapper.toSkillMatrixEntity(modificationDto);
        skillMatrixEntity.setId(id);
        SkillMatrixEntity updatedSkillMatrix = skillMatrixRepository.save(skillMatrixEntity);
        SkillMatrixDto createdSchemeDto = skillMatrixMapper.toSkillMatrixDto(updatedSkillMatrix);

        log.debug("Return updated SkillMatrix: {}", createdSchemeDto);
        return createdSchemeDto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        log.debug("Trying to delete SkillMatrix by id: {}", id);

        skillMatrixRepository.delete(id);

        log.debug("SkillMatrix with ID {} has been removed", id);
    }

    @Override
    public List<SkillMatrixDto> findByParams(MatrixSearchParams params) {
        log.debug("Trying to get all SkillMatrix");

        PageOptions pageOptions = new PageOptions(params.getPage(), params.getPageSize());
        SkillMatrixSortType sortType = SkillMatrixSortType.getTypeByString(params.getSort());
        SkillMatrixCriteria criteria = SkillMatrixCriteria.builder()
                .schemeId(params.getSchemeId())
                .userId(params.getUserId())
                .build();

        List<SkillMatrixEntity> skillMatrixEntities = skillMatrixRepository
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

        SkillMatrixEntity skillMatrix = skillMatrixRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found by id"));
        SkillMatrixDto result = skillMatrixMapper.toSkillMatrixDto(skillMatrix);

        log.debug("Return SkillMatrix: {}", result);
        return result;
    }

    @Override
    public SkillMatrixFullInfoDto findFullInfoById(Long id) {
        log.debug("Find full SkillMatrix by id: {}", id);

        SkillMatrixEntity skillMatrix = findEntityWithFullInfoById(id);
        SkillMatrixFullInfoDto result = skillMatrixMapper.toFullSkillMatrixEntity(skillMatrix);

        log.debug("Return SkillMatrix: {}", result);
        return result;
    }
    @Override
    public ResponseEntity<byte[]> exportMatrixToExcelById(Long id) throws IOException {
        log.debug("Export SkillMatrix by id: {}", id);

        SkillMatrixEntity skillMatrix = findEntityWithFullInfoById(id);
        byte[] excel = excelBuilder.build(skillMatrix);

        String fileName = skillMatrix.getName() + ".xlsx";
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
        ResponseEntity<byte[]> response = new ResponseEntity(excel, headers, HttpStatus.OK);

        return response;
    }

    private SkillMatrixEntity findEntityWithFullInfoById(Long id) {
        SkillMatrixEntity skillMatrix = skillMatrixRepository.findWithAssessmentsById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found by id"));

        List<SkillAssessmentEntity> assessments = skillMatrix.getSkillAssessments();
        SkillMatrixSchemeEntity scheme = skillMatrix.getSkillMatrixScheme();
        List<SkillCategoryEntity> categories = categoryRepository.findFullSkillCategoryBySchemeId(scheme.getId());
        scheme.setSkillCategories(categories);

        List<SkillEntity> skills = categories.stream().flatMap(category -> category.getSkills()
                .stream()).collect(Collectors.toList());
        setSkillAssessments(skills,assessments);

        return skillMatrix;
    }

    private void setSkillAssessments(List<SkillEntity> skills, List<SkillAssessmentEntity> assessments) {
        for (SkillEntity skill: skills) {
            skill.setSkillAssessments(new ArrayList<>());
            for (SkillAssessmentEntity assessment: assessments) {
                if (assessment.getSkillId().equals(skill.getId())) {
                    skill.getSkillAssessments().add(assessment);
                    break;
                }
            }
        }
    }
}
