package br.com.gusleite.NexGenTech.repositories;


import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
    Employee findEmployeeByName(String name);
    List<Employee> findEmployeeByAddress_FederativeUnitIs(FederativeUnit federativeUnit);
    List<Employee> findEmployeeByOfficeIs(Office office);
    List<Employee> findEmployeeBySalaryIsLessThanEqual(BigDecimal salary);
    List<Employee> findEmployeeBySalaryIsGreaterThanEqual(BigDecimal salary);
}
