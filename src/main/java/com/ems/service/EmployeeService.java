package com.ems.service;

import com.ems.dto.EmployeeDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {

  public Flux<EmployeeDTO> getAllEmployee();

  public Mono<EmployeeDTO> findById(String id);

  public Mono<Void> deleteEmployee(String id);

  public Mono<EmployeeDTO> addEmployee(EmployeeDTO employeeDTO);

  public Mono<EmployeeDTO> updateEmployee(String id, EmployeeDTO employeeDTO);
}
