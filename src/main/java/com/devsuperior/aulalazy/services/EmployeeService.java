package com.devsuperior.aulalazy.services;

import com.devsuperior.aulalazy.dto.EmployeeDepartmentDTO;
import com.devsuperior.aulalazy.dto.EmployeeMinDTO;
import com.devsuperior.aulalazy.entities.Employee;
import com.devsuperior.aulalazy.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

  @Autowired
  private EmployeeRepository repository;

  @Transactional(readOnly = true)
  public EmployeeMinDTO findByIdMin(Long id) {
    Optional<Employee> result = this.repository.findById(id);
    return new EmployeeMinDTO(result.get());
  }

  @Transactional(readOnly = true)
  public EmployeeDepartmentDTO findByIdWithDepartment(Long id) {
    Optional<Employee> result = this.repository.findById(id);
    return new EmployeeDepartmentDTO(result.get());
  }

  @Transactional(readOnly = true)
  public List<EmployeeDepartmentDTO> findEmployeesWithDepartments() {
    List<Employee> result = this.repository.findEmployeesWithDepartments();
    return result.stream().map(EmployeeDepartmentDTO::new).toList();
  }

  public List<EmployeeMinDTO> findByName(String name) {
    List<Employee> result = this.repository.findByNameContainingIgnoreCase(name);
    // return result.stream().map(EmployeeMinDTO::new).collect(Collectors.toList());
    return result.stream().map(EmployeeMinDTO::new).toList();
  }
}
