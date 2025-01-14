package com.ems.service.impl;

import com.ems.dto.EmployeeDTO;
import com.ems.entity.Employee;
import com.ems.repository.EmployeeRepository;
import com.ems.service.EmployeeService;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public Flux<EmployeeDTO> getAllEmployee() {
    return employeeRepository
        .findAll()
        .map(employee -> modelMapper.map(employee, EmployeeDTO.class));
  }

  @Override
  public Mono<EmployeeDTO> findById(String id) {
    return employeeRepository
        .findById(id)
        .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
        .switchIfEmpty(
            Mono.error(new NoSuchElementException("Employee with id " + id + " not found")));
  }

  @Override
  public Mono<Void> deleteEmployee(String id) {
    return employeeRepository
        .findById(id)
        .switchIfEmpty(
            Mono.error(new NoSuchElementException("Employee with id " + id + " not found")))
        .flatMap(employee -> employeeRepository.deleteById(id));
  }

  @Override
  public Mono<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO) {
    return employeeRepository
        .save(modelMapper.map(employeeDTO, Employee.class))
        .map(savedEmployee -> modelMapper.map(savedEmployee, EmployeeDTO.class));
  }

  @Override
  public Mono<EmployeeDTO> updateEmployee(String id, EmployeeDTO employeeDTO) {
    return employeeRepository
        .findById(id)
        .flatMap(
            existingEmployee -> {
              existingEmployee.setName(employeeDTO.getName());
              existingEmployee.setEmail(employeeDTO.getEmail());
              return employeeRepository.save(existingEmployee);
            })
        .map(updateEmployee -> modelMapper.map(updateEmployee, EmployeeDTO.class))
        .switchIfEmpty(
            Mono.error(new NoSuchElementException("Employee with id " + id + " not found")));
  }
}
