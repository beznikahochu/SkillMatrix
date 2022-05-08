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
import by.skillmatrix.exception.NotFoundException;
import by.skillmatrix.mapper.SkillMatrixMapper;
import by.skillmatrix.repository.SkillCategoryRepository;
import by.skillmatrix.repository.SkillMatrixRepository;
import by.skillmatrix.repository.SkillMatrixSchemeRepository;
import by.skillmatrix.repository.EmployeeRepository;
import by.skillmatrix.service.SkillMatrixService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private final SkillMatrixSchemeRepository schemeRepository;
    private final SkillCategoryRepository categoryRepository;
    private final EmployeeRepository employeeRepository;
    private final SkillMatrixMapper skillMatrixMapper;

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

        skillMatrixEntity.setUser(employeeEntity);
        skillMatrixEntity.setSkillMatrixScheme(matrixScheme);
        skillMatrixEntity.setCreationDate(LocalDate.now());
        skillMatrixEntity.setCreationTime(LocalTime.now());

        SkillMatrixEntity createdMatrix = skillMatrixRepository.save(skillMatrixEntity);

        SkillMatrixDto skillMatrixDto = skillMatrixMapper.toSkillMatrixDto(createdMatrix);

        log.debug("Return saved SkillMatrix: {}", skillMatrixDto);
        return skillMatrixDto;
    }

    @Override
    @Transactional
    public SkillMatrixDto update(SkillMatrixModificationDto modificationDto) {
        log.debug("Trying to update SkillMatrix: {}", modificationDto);

        SkillMatrixEntity skillMatrixEntity = skillMatrixMapper.toSkillMatrixEntity(modificationDto);
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
    public List<SkillMatrixDto> findAll() {
        log.debug("Trying to get all SkillMatrix");

        List<SkillMatrixEntity> skillMatrixEntities = skillMatrixRepository.findAll();
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

        SkillMatrixEntity skillMatrix = skillMatrixRepository.findWithAssessmentsById(id)
                .orElseThrow(() -> new NotFoundException("SkillMatrix not found by id"));

        List<SkillAssessmentEntity> assessments = skillMatrix.getSkillAssessments();
        SkillMatrixSchemeEntity scheme = skillMatrix.getSkillMatrixScheme();
        List<SkillCategoryEntity> categories = categoryRepository.findFullSkillCategoryBySchemeId(scheme.getId());
        scheme.setSkillCategories(categories);

        List<SkillEntity> skills = categories.stream().flatMap(category -> category.getSkills()
                .stream()).collect(Collectors.toList());
        setSkillAssessments(skills,assessments);

        SkillMatrixFullInfoDto result = skillMatrixMapper.toFullSkillMatrixEntity(skillMatrix);

        log.debug("Return SkillMatrix: {}", result);
        return result;
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
