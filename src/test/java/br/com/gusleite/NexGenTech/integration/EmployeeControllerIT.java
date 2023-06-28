package br.com.gusleite.NexGenTech.integration;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import br.com.gusleite.NexGenTech.util.EmployeeCreator;
import br.com.gusleite.NexGenTech.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class EmployeeControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @DisplayName("list returns list of Employee inside page object when successful")
    void list_ReturnsListOfEmployeesInsidePageObject_WhenSuccessful() {
        Employee savedEmployee = employeeRepository.save(EmployeeCreator.createEmployeeToBeSaved());

        String expectedName = savedEmployee.getName();

        PageableResponse<Employee> EmployeePage = testRestTemplate.exchange("/employees", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Employee>>() {
                }).getBody();

        Assertions.assertThat(EmployeePage).isNotNull();

        Assertions.assertThat(EmployeePage.toList())
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(EmployeePage.toList().get(0).getName()).isEqualTo(expectedName);
    }


    @Test
    @DisplayName("findById returns Employee when successful")
    void findById_ReturnsEmployee_WhenSuccessful() {
        Employee savedEmployee = employeeRepository.save(EmployeeCreator.createEmployeeToBeSaved());

        Long expectedId = savedEmployee.getId();

        Employee Employee = testRestTemplate.getForObject("/employees/{id}", Employee.class, expectedId);

        Assertions.assertThat(Employee).isNotNull();

        Assertions.assertThat(Employee.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of Employee when successful")
    void findByName_ReturnsListOfEmployee_WhenSuccessful(){
        Employee savedEmployee = employeeRepository.save(EmployeeCreator.createEmployeeToBeSaved());

        String expectedName = savedEmployee.getName();

        String url = String.format("/employees/name/%s", expectedName);


        Employee employee = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Employee>() {
                }).getBody();
        Assertions.assertThat(employee)
                .isNotNull();
        Assertions.assertThat(employee.getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByOffice returns a list of Employee when successful")
    void findByOffice_ReturnsListOfEmployee_WhenSuccessful() {
        Employee savedEmployee = employeeRepository.save(EmployeeCreator.createEmployeeToBeSaved());

        Office expectedOfficce = savedEmployee.getOffice();

        String url = String.format("/employees/name/%s", expectedOfficce.toString());


        Employee employee = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Employee>() {
                }).getBody();
        Assertions.assertThat(employee)
                .isNotNull();
    }

    @Test
    @DisplayName("findByFederativeUnit returns a list of Employee when successful")
    void findByFederativeUnit_ReturnsListOfEmployee_WhenSuccessful() {
        Employee savedEmployee = employeeRepository.save(EmployeeCreator.createEmployeeToBeSaved());

        FederativeUnit expectedFederativeUnit= savedEmployee.getAddress().getFederativeUnit();

        String url = String.format("/employees/name/%s", expectedFederativeUnit.toString());


        Employee employee = testRestTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<Employee>() {
                }).getBody();
        Assertions.assertThat(employee)
                .isNotNull();
    }

    @Test
    @DisplayName("replace updates Employee when successful")
    void replace_UpdatesEmployee_WhenSuccessful(){
        Employee savedEmployee = employeeRepository.save(EmployeeCreator.createEmployeeToBeSaved());

        savedEmployee.setName("new name");

        System.out.println(savedEmployee);

        ResponseEntity<Void> employeeResponseEntity = testRestTemplate.exchange("/employees",
                HttpMethod.PUT,new HttpEntity<>(savedEmployee), Void.class);

        Assertions.assertThat(employeeResponseEntity).isNotNull();

        Assertions.assertThat(employeeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes Employee when successful")
    void delete_RemovesEmployee_WhenSuccessful(){
        Employee savedEmployee = employeeRepository.save(EmployeeCreator.createEmployeeToBeSaved());

        ResponseEntity<Void> EmployeeResponseEntity = testRestTemplate.exchange("/employees/{id}",
                HttpMethod.DELETE,null, Void.class, savedEmployee.getId());

        Assertions.assertThat(EmployeeResponseEntity).isNotNull();

        Assertions.assertThat(EmployeeResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}