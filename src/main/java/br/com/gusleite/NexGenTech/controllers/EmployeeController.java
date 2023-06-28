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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When Employee With This Id Does Not Exist in the Database")
    })
    public ResponseEntity<EmployeeGetDetailDataModel> findEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employeeService.findEmployeeById(id)));
    }

    @GetMapping("/name/{name}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When Employee With This Name Does Not Exist in the Database")
    })
    public ResponseEntity<EmployeeGetDetailDataModel> findEmployeeByName(@PathVariable String name) {
        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employeeService.findEmployeeByName(name)));
    }

    @GetMapping("/office/{office}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When Employee With This Office Does Not Exist in the Database")
    })
    public ResponseEntity<List<Employee>> listEmployeeByOffice(@PathVariable Office office){
        return ResponseEntity.ok(employeeService.listEmployeeByOffice(office));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When Employee With This Federative Unit Does Not Exist in the Database")
    })
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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Created"),
            @ApiResponse(responseCode = "400", description = "When Some Paramater Was Incorect")
    })
    public ResponseEntity register(@RequestBody @Valid EmployeePostRegisterDataModel employee) {
            employeeService.register(employee);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Transactional
    @PutMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"),
    @ApiResponse(responseCode = "404",description = "Not Found a emploee With this id")})
    public ResponseEntity updateEmployeeRegister(@RequestBody @Valid EmployeePutUpdateDataModel data) {

        Employee employee = employeeService.findEmployeeById(data.getId());

        employee.updateData(data);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/promote/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404",description = "Not Found a emploee With this id")})
    public ResponseEntity promoteEmployee(@PathVariable Long id) throws PromotionValidationFailAttemptException {
        Employee employee = employeeService.findEmployeeById(id);
        employee.promote();

        System.out.println("Successful Promotion!");

        return ResponseEntity.ok(new EmployeeGetDetailDataModel(employee));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404",description = "Not Found a emploee With this id")})
    public ResponseEntity deleteEmployeeRegister(@PathVariable long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
