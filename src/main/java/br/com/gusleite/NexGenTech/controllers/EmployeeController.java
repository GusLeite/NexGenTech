package br.com.gusleite.NexGenTech.controllers;

import br.com.gusleite.NexGenTech.dtos.DetailEmployeeDataDTO;
import br.com.gusleite.NexGenTech.dtos.EmployeeListDTO;
import br.com.gusleite.NexGenTech.dtos.RegisterEmployeeDataDTO;
import br.com.gusleite.NexGenTech.dtos.UpdateEmployeeRegisterDTO;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/employees")
@NoArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<EmployeeListDTO>> getEmployees(){
        List<EmployeeListDTO> list = employeeRepository.findAll().stream().map(EmployeeListDTO::new).toList();
        return ResponseEntity.ok(list);
    }

    @Transactional
    @PostMapping
    public ResponseEntity register(@RequestBody @Valid RegisterEmployeeDataDTO data, UriComponentsBuilder uriBuilder){
        Employee employee = new Employee(data);
        employeeRepository.save(employee);

        URI uri = uriBuilder.path("/employees/{id}").buildAndExpand(employee.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailEmployeeDataDTO(employee));
    }

    @Transactional
    @PutMapping
    public ResponseEntity updateEmployeeRegister(@RequestBody @Valid UpdateEmployeeRegisterDTO data){
        Employee employee = employeeRepository.getReferenceById(data.getId());
        employee.updateData(data);

        return ResponseEntity.ok(new DetailEmployeeDataDTO(employee));
    }

    @Transactional
    @PutMapping("/promote/{id}")
    public ResponseEntity promoteEmployee(@PathVariable Long id) throws PromotionValidationFailAttemptException {
        Employee employee = employeeRepository.getReferenceById(id);

        employee.promote();

        return ResponseEntity.ok(new DetailEmployeeDataDTO(employee));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteEmployeeRegister(@PathVariable Long id){
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{id}")
    public ResponseEntity detailEmployee(@PathVariable Long id){
        Employee employee = employeeRepository.getReferenceById(id);
        return ResponseEntity.ok(new DetailEmployeeDataDTO(employee));
    }


}
