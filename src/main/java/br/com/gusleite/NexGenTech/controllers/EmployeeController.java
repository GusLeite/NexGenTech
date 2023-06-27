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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping(value = "/employees")
@RequiredArgsConstructor
@Log4j2
public class EmployeeController {

    private final EmployeeService employeeService;

    private final DateUtil dateUtil;


    @GetMapping
    public ResponseEntity<Page<Employee>> getEmployees(Pageable pageable) {
        return ResponseEntity.ok(employeeService.listAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeGetDetailDataModel> findEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employeeService.findEmployeeById(id)));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<EmployeeGetDetailDataModel> findEmployeeByName(@PathVariable String name) {
        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employeeService.findEmployeeByName(name)));
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
    public ResponseEntity<List<Employee>> listEmployeeBySalaryIsLessThanEqual                   (@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.listEmployeeBySalaryIsLessThanEqual(salary));
    }

    @GetMapping("/salary_greater_than_equal/{salary}")
    public ResponseEntity<List<Employee>> listEmployeeBySalaryIsGreaterThanEqual(@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.listEmployeeBySalaryIsGreaterThanEqual(salary));
    }

    @Transactional
    @PostMapping
    public ResponseEntity<EmployeePostRegisterDataModel> register(@RequestBody @Valid EmployeePostRegisterDataModel employee) {
        return ResponseEntity.ok(employee);
    }

    @Transactional
    @PutMapping
    public ResponseEntity<Void> updateEmployeeRegister(@RequestBody @Valid EmployeePutUpdateDataModel data) {

        Employee employee = employeeService.findEmployeeById(data.getId());

        employee.updateData(data);

        return (ResponseEntity<Void>) ResponseEntity.noContent();
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
