package br.com.gusleite.NexGenTech.controllers;


import br.com.gusleite.NexGenTech.datamodel.EmployeeGetDetailDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePostRegisterDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePutUpdateDataModel;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;
import br.com.gusleite.NexGenTech.services.EmployeeService;
import br.com.gusleite.NexGenTech.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping(value = "/employees")
@RequiredArgsConstructor
@Log4j2
public class EmployeeController {

    private final EmployeeService employeeService;

    private final DateUtil dateUtil;

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return ResponseEntity.ok(employeeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity findEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employeeService.findEmployeeById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity findEmployeeByName(@PathVariable String name) {
        return ResponseEntity.ok(employeeService.findEmployeeByName(name));
    }

    @GetMapping("/office/{office}")
    public ResponseEntity<List<Employee>> listEmployeeByOffice(@PathVariable Office office){
        return ResponseEntity.ok(employeeService.listEmployeeByOffice(office));
    }

    @GetMapping("/federative_unit/{federative}")
    public ResponseEntity<List<Employee>>listEmployeeByFederativeUnit(@PathVariable FederativeUnit federative){
        return ResponseEntity.ok(employeeService.listEmployeeByFederativeUnit(federative));
    }
    @GetMapping("/salary_less_than_equal/{salary}")
    public ResponseEntity<List<Employee>> listEmployeeBySalaryIsLessThanEqual(@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.listEmployeeBySalaryIsLessThanEqual(salary));
    }

    @GetMapping("/salary_greater_than_equal/{salary}")
    public ResponseEntity<List<Employee>> listEmployeeBySalaryIsGreaterThanEqual(@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.listEmployeeBySalaryIsGreaterThanEqual(salary));
    }

    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid EmployeePostRegisterDataModel employee, UriComponentsBuilder uriBuilder) {
        URI uri = uriBuilder.path("/employees/{id}").buildAndExpand(employeeService.register(employee).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateEmployeeRegister(@RequestBody @Valid EmployeePutUpdateDataModel data) {

        Employee employee = employeeService.findEmployeeById(data.getId());

        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employee));
    }

    @Transactional
    @PutMapping("/promote/{id}")
    public ResponseEntity promoteEmployee(@PathVariable Long id) throws PromotionValidationFailAttemptException {
        Employee employee = employeeService.findEmployeeById(id);
        employee.promote();

        System.out.println("Successful Promotion!");

        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employee));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeRegister(@PathVariable long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
