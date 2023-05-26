package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
}
