package by.skillmatrix.service.impl;

import by.skillmatrix.dto.employee.EmployeeCreationDto;
import by.skillmatrix.dto.employee.EmployeeDto;
import by.skillmatrix.entity.EmployeeEntity;
import by.skillmatrix.mapper.EmployeeMapper;
import by.skillmatrix.repository.EmployeeRepository;
import by.skillmatrix.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Override
    @Transactional
    public EmployeeDto create(EmployeeCreationDto employeeDto) {
        log.debug("Admit new employee: {}", employeeDto);

        EmployeeEntity employee = employeeMapper.toEmployeeEntity(employeeDto);
        EmployeeEntity createdEmployee = employeeRepository.save(employee);
        EmployeeDto result = employeeMapper.toEmployeeDto(createdEmployee);

        log.debug("Admitted employee: {}", employee);
        return result;
    }
}
