package br.com.gusleite.NexGenTech.controllers;

import br.com.gusleite.NexGenTech.dtos.RegisterEmployeeData;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @GetMapping
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @PostMapping
    public void register(@RequestBody RegisterEmployeeData data){
        System.out.println(data);

    }

}
