package br.com.gusleite.NexGenTech.controllers;


import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;
import br.com.gusleite.NexGenTech.services.EmployeeService;
import br.com.gusleite.NexGenTech.util.MediaType;
import br.com.gusleite.NexGenTech.vo.v1.EmployeeVO;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
@Log4j2
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeVO>> findAll() {
        return ResponseEntity.ok(employeeService.listAll());
    }

    @GetMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When EmployeeVo With This Id Does Not Exist in the Database")
    })
    public ResponseEntity<EmployeeVO> findEmployeeVoById(@PathVariable Long id) {
        return ResponseEntity.ok(new EmployeeVO(employeeService.findEmployeeVOById(id)));
    }

    @GetMapping(value = "/name/{name}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When EmployeeVo With This Name Does Not Exist in the Database")
    })
    public ResponseEntity<EmployeeVO> findEmployeeVoByName(@PathVariable String name) {
        return ResponseEntity.ok(new EmployeeVO(employeeService.findEmployeeByName(name)));
    }

    @GetMapping(value = "/office/{office}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When EmployeeVo With This Office Does Not Exist in the Database")
    })
    public ResponseEntity<List<EmployeeVO>> listEmployeeVoByOffice(@PathVariable Office office){
        return ResponseEntity.ok(employeeService.listEmployeeByOffice(office));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful Operation"),
            @ApiResponse(responseCode = "500", description = "When EmployeeVo With This Federative Unit Does Not Exist in the Database")
    })
    @GetMapping(value = "/federative_unit/{federative}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML})
    public ResponseEntity<List<EmployeeVO>>listEmployeeVoByFederativeUnit(@PathVariable FederativeUnit federative){
        return ResponseEntity.ok(employeeService.listEmployeeByFederativeUnit(federative));
    }
    @GetMapping(value = "/salary_less_than_equal/{salary}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML})
    public ResponseEntity<List<EmployeeVO>> listEmployeeBySalaryIsLessThanEqual(@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.listEmployeeBySalaryIsLessThanEqual(salary));
    }

    @GetMapping(value = "/salary_greater_than_equal/{salary}",produces = { MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML,MediaType.APPLICATION_YAML})
    public ResponseEntity<List<EmployeeVO>> listEmployeeBySalaryIsGreaterThanEqual(@PathVariable BigDecimal salary){
        return ResponseEntity.ok(employeeService.listEmployeeBySalaryIsGreaterThanEqual(salary));
    }

    @Transactional
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successful Created"),
            @ApiResponse(responseCode = "400", description = "When Some Paramater Was Incorect")
    })
    public ResponseEntity register(@RequestBody @Valid EmployeeVO employeeVo) {
            log.info(employeeService.register(employeeVo));
            return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Transactional
    @PutMapping
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"),
    @ApiResponse(responseCode = "404",description = "Not Found a emploee With this id")})
    public ResponseEntity updateEmployeeRegister(@RequestBody @Valid EmployeeVO data) {
        employeeService.updateData(data);

        return ResponseEntity.noContent().build();
    }

    @Transactional
    @PutMapping("/promote/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404",description = "Not Found a emploee With this id")})
    public ResponseEntity promoteEmployeeVo(@PathVariable Long id) throws PromotionValidationFailAttemptException {
       employeeService.promote(id);
       return ResponseEntity.ok(employeeService.findEmployeeVOById(id));
    }

    @Transactional
    @DeleteMapping("/{id}")
    @ApiResponses(value = {@ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404",description = "Not Found a emploee With this id")})
    public ResponseEntity deleteEmployeeVoRegister(@PathVariable long id) {
        employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
