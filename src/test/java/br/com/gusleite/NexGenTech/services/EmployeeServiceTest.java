package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import br.com.gusleite.NexGenTech.util.EmployeeCreator;
import br.com.gusleite.NexGenTech.util.EmployeePostRegisterCreator;
import br.com.gusleite.NexGenTech.util.EmployeePutUpdateRegisterCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    private EmployeeRepository employeeRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Employee> employeesPage = new PageImpl<>(List.of(EmployeeCreator.createEmployeeToBeSaved()));
        when(employeeRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(employeesPage);

        BDDMockito.when(employeeRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(EmployeeCreator.createEmployeeToBeSaved()));

        BDDMockito.when(employeeRepositoryMock.findEmployeeByName(ArgumentMatchers.anyString()))
                .thenReturn(Optional.of(EmployeeCreator.createEmployeeToBeSaved()));


        BDDMockito.when(employeeRepositoryMock.save(ArgumentMatchers.any(Employee.class)))
                .thenReturn(EmployeeCreator.createEmployeeToBeSaved());

        BDDMockito.doNothing().when(employeeRepositoryMock).delete(ArgumentMatchers.any(Employee.class));

    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listAll_ReturnListOfEmployeesInsidePageObject_WhenSuccessful(){
        String expectedName = EmployeeCreator.createEmployeeToBeSaved().getName();

        Page<Employee> employeePage = employeeService.listAll(PageRequest.of(1,1));

        Assertions.assertThat(employeePage).isNotNull();

        Assertions.assertThat(employeePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(employeePage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void findById_ReturnEmployee_WhenSuccessful(){
        Long expectedId = EmployeeCreator.createEmployeeToBeSaved().getId();

        Employee employee = employeeService.findEmployeeById(1);

        Assertions.assertThat(employee).isNotNull();

        Assertions.assertThat(employee.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void findByName_ReturnEmployee_WhenSuccessful(){
        String expectedName = EmployeeCreator.createEmployeeToBeSaved().getName();

        Employee employee = employeeService.findEmployeeByName("Gioberto");

        Assertions.assertThat(employee)
                .isNotNull();

        Assertions.assertThat(employee.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeByOffice_ReturnListOfEmployees_WhenSuccessful(){
        Office expectedOffice = Office.TRAINEE;

        List<Employee> employees = employeeService.listEmployeeByOffice(Office.TRAINEE);

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> Assertions.assertThat(employee.getOffice()).isEqualTo(expectedOffice));
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeByFederativeUnit_ReturnListOfEmployees_WhenSuccessful(){
        FederativeUnit expectedFederativeUnit= FederativeUnit.SP;

        List<Employee> employees = employeeService.listEmployeeByFederativeUnit(expectedFederativeUnit);

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> {
            Assertions.assertThat(employee.getAddress().getFederativeUnit()).isEqualTo(expectedFederativeUnit);
        });
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeBySalaryIsLessThanEqual_ReturnListOfEmployees_WhenSuccessful(){
        BigDecimal expectedSalaryIsLessThen = new BigDecimal("2500.0");

        List<Employee> employees = employeeService.listEmployeeBySalaryIsLessThanEqual(expectedSalaryIsLessThen);

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> {
            Assertions.assertThat(employee.getSalary()).isLessThanOrEqualTo(expectedSalaryIsLessThen);
        });
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeBySalaryIsGreaterThanEqual_ReturnListOfEmployees_WhenSuccessful(){
        BigDecimal expectedSalaryIsGreaterThen = new BigDecimal("2000.0");

        List<Employee> employees = employeeService.listEmployeeBySalaryIsLessThanEqual(expectedSalaryIsGreaterThen);

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> {
            Assertions.assertThat(employee.getSalary()).isGreaterThanOrEqualTo(expectedSalaryIsGreaterThen);
        });
    }

    @Test
    void save_ReturnEmployee_WhenSuccessful(){

        Employee employee = EmployeeCreator.createEmployeeToBeSaved();

        employeeService.register(EmployeePostRegisterCreator.createEmployeePostBody());

        Assertions.assertThat(employee).isNotNull();
        Assertions.assertThat(employee.getName()).isEqualTo(EmployeeCreator.createEmployeeToBeSaved().getName());
        Assertions.assertThat(employee.getCpf()).isEqualTo(EmployeeCreator.createEmployeeToBeSaved().getCpf());
        Assertions.assertThat(employee.getOffice()).isEqualTo(EmployeeCreator.createEmployeeToBeSaved().getOffice());
    }

    @Test
    void update_ReturnEmployee_WhenSuccessful() {

        Assertions.assertThatCode(() -> employeeService.updateData(EmployeePutUpdateRegisterCreator.createEmployeePostBody()))
                .doesNotThrowAnyException();
    }


    @Test
    void delete_Employee_WhenSuccessful() {

        Assertions.assertThatCode(() -> employeeService.delete(1)).doesNotThrowAnyException();

    }

}