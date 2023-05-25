package br.com.gusleite.NexGenTech.controllers;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import br.com.gusleite.NexGenTech.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
