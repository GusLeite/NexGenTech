package br.com.gusleite.NexGenTech.controllers;

import br.com.gusleite.NexGenTech.datamodel.EmployeeGetDetailDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePostRegisterDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePutUpdateDataModel;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.mappers.EmployeeMapper;
import br.com.gusleite.NexGenTech.services.EmployeeService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class EmployeeControllerTest {
    @InjectMocks
    private EmployeeController employeeController;
    @Mock
    private EmployeeService employeeServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Employee> employeesPage = new PageImpl<>(List.of(EmployeeCreator.createEmployeeToBeSaved()));
        when(employeeServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(employeesPage);

        BDDMockito.when(employeeServiceMock.findEmployeeById(ArgumentMatchers.anyLong()))
                .thenReturn(EmployeeCreator.createEmployeeToBeSaved());

        BDDMockito.when(employeeServiceMock.findEmployeeByName(ArgumentMatchers.anyString()))
                .thenReturn(EmployeeCreator.createEmployeeToBeSaved());


        BDDMockito.when(employeeServiceMock.register(ArgumentMatchers.any(EmployeePostRegisterDataModel.class)))
                .thenReturn(EmployeeCreator.createEmployeeToBeSaved());

        BDDMockito.doNothing().when(employeeServiceMock).updateData(ArgumentMatchers.any(EmployeePutUpdateDataModel.class));
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listAll_ReturnListOfEmployeesInsidePageObject_WhenSuccessful(){
        String expectedName = EmployeeCreator.createEmployeeToBeSaved().getName();

        Page<Employee> employeePage = employeeController.getEmployees(null).getBody();

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

        EmployeeGetDetailDataModel employee = employeeController.findEmployeeById(1L).getBody();

        Assertions.assertThat(employee).isNotNull();

        Assertions.assertThat(employee.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void findByName_ReturnEmployee_WhenSuccessful(){
        String expectedName = EmployeeCreator.createEmployeeToBeSaved().getName();

        EmployeeGetDetailDataModel employee = employeeController.findEmployeeByName("Gioberto").getBody();

        Assertions.assertThat(employee)
                        .isNotNull();

        Assertions.assertThat(employee.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeByOffice_ReturnListOfEmployees_WhenSuccessful(){
        Office expectedOffice = Office.TRAINEE;

        List<Employee> employees = employeeController.listEmployeeByOffice(Office.TRAINEE).getBody();

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> Assertions.assertThat(employee.getOffice()).isEqualTo(expectedOffice));
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeByFederativeUnit_ReturnListOfEmployees_WhenSuccessful(){
        FederativeUnit expectedFederativeUnit= FederativeUnit.SP;

        List<Employee> employees = employeeController.listEmployeeByFederativeUnit(expectedFederativeUnit).getBody();

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> {
            Assertions.assertThat(employee.getAddress().getFederativeUnit()).isEqualTo(expectedFederativeUnit);
        });
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeBySalaryIsLessThanEqual_ReturnListOfEmployees_WhenSuccessful(){
        BigDecimal expectedSalaryIsLessThen = new BigDecimal("2500.0");

        List<Employee> employees = employeeController.listEmployeeBySalaryIsLessThanEqual(expectedSalaryIsLessThen).getBody();

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> {
            Assertions.assertThat(employee.getSalary()).isLessThanOrEqualTo(expectedSalaryIsLessThen);
        });
    }

    @Test
    @DisplayName("List returns list of employees inside page object when successful")
    void listEmployeeBySalaryIsGreaterThanEqual_ReturnListOfEmployees_WhenSuccessful(){
        BigDecimal expectedSalaryIsGreaterThen = new BigDecimal("2000.0");

        List<Employee> employees = employeeController.listEmployeeBySalaryIsLessThanEqual(expectedSalaryIsGreaterThen).getBody();

        Assertions.assertThat(employees).isNotNull();

        employees.forEach(employee -> {
            Assertions.assertThat(employee.getSalary()).isGreaterThanOrEqualTo(expectedSalaryIsGreaterThen);
        });
    }

    @Test
    void save_ReturnEmployee_WhenSuccessful(){

        Employee employee = EmployeeCreator.createEmployeeToBeSaved();

        employeeController.register(EmployeePostRegisterCreator.createEmployeePostBody());

        Assertions.assertThat(employee).isNotNull();
        Assertions.assertThat(employee.getName()).isEqualTo(EmployeeCreator.createEmployeeToBeSaved().getName());
        Assertions.assertThat(employee.getCpf()).isEqualTo(EmployeeCreator.createEmployeeToBeSaved().getCpf());
        Assertions.assertThat(employee.getOffice()).isEqualTo(EmployeeCreator.createEmployeeToBeSaved().getOffice());
    }

    @Test
    void delete_Employee_WhenSuccessful(){

        Assertions.assertThatCode(() -> employeeController.deleteEmployeeRegister(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = employeeController.deleteEmployeeRegister(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}