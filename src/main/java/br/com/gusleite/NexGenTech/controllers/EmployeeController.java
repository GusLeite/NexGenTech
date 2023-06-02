package br.com.gusleite.NexGenTech.controllers;

import br.com.gusleite.NexGenTech.dtos.EmployeeListDTO;
import br.com.gusleite.NexGenTech.dtos.RegisterEmployeeDataDTO;
import br.com.gusleite.NexGenTech.dtos.UpdateEmployeeRegisterDTO;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
@NoArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<EmployeeListDTO> getEmployees(){
        return employeeRepository.findAll().stream().map(EmployeeListDTO::new).toList();
    }

    @Transactional
    @PostMapping
    public void register(@RequestBody @Valid RegisterEmployeeDataDTO data){
        employeeRepository.save(new Employee(data));
    }

    @Transactional
    @PutMapping
    public void updateEmployeeRegister(@RequestBody @Valid UpdateEmployeeRegisterDTO data){
        Employee employee = employeeRepository.getReferenceById(data.getId());
        employee.updateData(data);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public void deleteEmployeeRegister(@PathVariable Long id){
        employeeRepository.deleteById(id);
    }


}
