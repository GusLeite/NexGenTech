package br.com.gusleite.NexGenTech.repositories;

import br.com.gusleite.NexGenTech.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
