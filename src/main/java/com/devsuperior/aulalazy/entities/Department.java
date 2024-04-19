package com.devsuperior.aulalazy.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_department")
public class Department {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  public String name;

  @OneToMany(mappedBy = "department")
  //  @OneToMany(mappedBy = "department", fetch = FetchType.EAGER) --> NÃO RECOMENDADO
  public List<Employee> employees = new ArrayList<>();

  public Department() {
  }

  public Department(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Employee> getEmployees() {
    return this.employees;
  }
}
