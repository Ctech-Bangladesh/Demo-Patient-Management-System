package com.ems.controller;

import com.ems.dto.EmployeeDTO;
import com.ems.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {
  private final EmployeeService employeeService;

  @GetMapping
  public Flux<EmployeeDTO> findAll() {
    return employeeService.getAllEmployee();
  }

  @GetMapping("/{id}")
  public Mono<ResponseEntity<EmployeeDTO>> findById(@PathVariable("id") String id) {
    return employeeService.findById(id).map(ResponseEntity::ok);
  }

  @DeleteMapping("/{id}")
  public Mono<ResponseEntity<String>> deleteById(@PathVariable("id") String id) {
    return employeeService
        .deleteEmployee(id)
        .then(Mono.just(ResponseEntity.ok("Deleted Successfully")));
  }

  @PostMapping
  public Mono<ResponseEntity<EmployeeDTO>> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
    return employeeService
        .addEmployee(employeeDTO)
        .map(savedEmployee -> ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee));
  }

  @PutMapping("/{id}")
  public Mono<ResponseEntity<EmployeeDTO>> updateEmployee(
      @PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) {
    return employeeService.updateEmployee(id, employeeDTO).map(ResponseEntity::ok);
  }
}
