package br.com.gusleite.NexGenTech.repositories;


import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.util.EmployeeCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for employee repository")
class EmployeeRepositoryTest {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("Save create employee when successful")
    void save_PersistEmployee_WhenSuccessful(){
        Employee employeeToBeSave = EmployeeCreator.createEmployeeToBeSaved();

        Employee employeeSaved = this.employeeRepository.save(employeeToBeSave);

        Assertions.assertThat(employeeSaved).isNotNull();

        Assertions.assertThat(employeeSaved.getId()).isNotNull();

        Assertions.assertThat(employeeSaved.getName()).isEqualTo(employeeToBeSave.getName());

    }


    @Test
    @DisplayName("Update create employee when successful")
    void update_PersistEmployee_WhenSuccessful(){
        Employee employeeToBeSave = EmployeeCreator.createEmployeeToBeSaved();

        Employee employeeSaved = this.employeeRepository.save(employeeToBeSave);

        employeeSaved.setName("Giomar");

        Employee employeeUpdated = this.employeeRepository.save(employeeSaved);

        this.employeeRepository.save(employeeSaved);
        Assertions.assertThat(employeeUpdated).isNotNull();
        Assertions.assertThat(employeeUpdated.getId()).isNotNull();
        Assertions.assertThat(employeeUpdated.getName()).isEqualTo(employeeToBeSave.getName());

    }

    @Test
    @DisplayName("delete removes employee when successful")
    void delete_Removes_WhenSuccessful(){
        Employee employeeToBeSave = EmployeeCreator.createEmployeeToBeSaved();

        Employee employeeSaved = this.employeeRepository.save(employeeToBeSave);

        this.employeeRepository.delete(employeeSaved);

        Optional<Employee> employeeO = this.employeeRepository.findById(employeeSaved.getId());

        Assertions.assertThat(employeeO).isEmpty();

    }

    @Test
    void findEmployeesByName_ReturnEmployee_WhenSuccessful() {
        Employee employeeToBeSaved = EmployeeCreator.createEmployeeToBeSaved();

        employeeRepository.save(employeeToBeSaved);

        Optional<Employee> employeeByName = employeeRepository.findEmployeeByName(employeeToBeSaved.getName());

        Employee employee = employeeByName.get();

        Assertions.assertThat(employee).isEqualTo(employeeToBeSaved);
    }

    @Test
    void findEmployeeByAddress_FederativeUnitIs() {
        Employee employeeToBeSaved = EmployeeCreator.createEmployeeToBeSaved();

        employeeRepository.save(employeeToBeSaved);

        List<Employee> employeesByFederativeUnit = employeeRepository.findEmployeeByAddress_FederativeUnitIs(FederativeUnit.SP);

        employeesByFederativeUnit.forEach(employee -> Assertions.assertThat(employee.getAddress().getFederativeUnit()).isEqualTo(FederativeUnit.SP));

    }

    @Test
    void findEmployeeByOfficeIs() {
        Employee employeeToBeSaved = EmployeeCreator.createEmployeeToBeSaved();

        employeeRepository.save(employeeToBeSaved);

        List<Employee> employeesByOffice = employeeRepository.findEmployeeByOfficeIs(Office.MANAGER);

        employeesByOffice.forEach(employee -> Assertions.assertThat(employee.getOffice()).isEqualTo(Office.MANAGER));
    }

    @Test
    void findEmployeeBySalaryIsLessThanEqual() {
        Employee employeeToBeSaved = EmployeeCreator.createEmployeeToBeSaved();

        employeeRepository.save(employeeToBeSaved);

        List<Employee> employeesBySalary = employeeRepository.findEmployeeBySalaryIsLessThanEqual(new BigDecimal("2500"));

        employeesBySalary.forEach(employee -> Assertions.assertThat(employee.getSalary()).isLessThanOrEqualTo(new BigDecimal("2500")));
    }

    @Test
    void findEmployeeBySalaryIsGreaterThanEqual() {
        Employee employeeToBeSaved = EmployeeCreator.createEmployeeToBeSaved();

        employeeRepository.save(employeeToBeSaved);

        List<Employee> employeesBySalary = employeeRepository.findEmployeeBySalaryIsGreaterThanEqual(new BigDecimal("2500"));

        employeesBySalary.forEach(employee -> Assertions.assertThat(employee.getSalary()).isGreaterThanOrEqualTo(new BigDecimal("2500")));
    }


}