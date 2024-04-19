package com.devsuperior.aulalazy.repositories;

import com.devsuperior.aulalazy.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
  // Método customizado com sintaxe JPQL
  // No SQL seria algo como: SELECT * FROM tb_employees (tabela)
  // SELECT obj (apelido) FROM Employee (classe) obj JOIN FETCH obj.department (atributo q referencia o objeto
  // associado)
  @Query("SELECT obj FROM Employee obj JOIN FETCH obj.department")

  // Sem o Join Fetch seria realizada 3 voltas ao banco, pois quando fazemos uma consulta
  // Buscando uma coleção de objetos, msm com relacionamento p/ 1, a busca dps obj associados na coleção será LAZY.
  // Ou seja, ele buscou os func e dps voltou 3x no banco (pois há 3 dept), buscando os dept, o que é
  // ineficiente. Nâo foi necessário voltar 14x ao banco, pois existe o cache de memória das entidades.
  List<Employee> findEmployeesWithDepartments();

  List<Employee> findByNameContainingIgnoreCase(String name);
}
