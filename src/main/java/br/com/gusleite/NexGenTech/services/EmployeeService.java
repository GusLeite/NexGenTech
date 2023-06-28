package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.datamodel.EmployeePostRegisterDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePutUpdateDataModel;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.EmployeeNotFoundException;
import br.com.gusleite.NexGenTech.mappers.EmployeeMapper;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee findEmployeeById(long id){
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found!"));
    }

    public Employee findEmployeeByName(String name){
        return employeeRepository.findEmployeeByName(name).orElseThrow(() -> new EmployeeNotFoundException("Employee Not Found!"));
    }

    public List<Employee> listEmployeeByOffice(Office office){
        return employeeRepository.findEmployeeByOfficeIs(office);
    }

    public List<Employee> listEmployeeByFederativeUnit(FederativeUnit federativeUnit){
        return employeeRepository.findEmployeeByAddress_FederativeUnitIs(federativeUnit);
    }

    public List<Employee> listEmployeeBySalaryIsLessThanEqual(BigDecimal salary){
        return employeeRepository.findEmployeeBySalaryIsLessThanEqual(salary);
    }

    public List<Employee>listEmployeeBySalaryIsGreaterThanEqual(BigDecimal salary){
        return employeeRepository.findEmployeeBySalaryIsGreaterThanEqual(salary);
    }

    public Page<Employee> listAll(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    public Employee register(@Valid EmployeePostRegisterDataModel data) {
        Employee employee = new Employee(data);
        return employeeRepository.save(employee);
    }

    public void delete(long id) {

        employeeRepository.deleteById(findEmployeeById(id).getId());
    }

    public void updateData(EmployeePutUpdateDataModel data) {
        Employee savedEmployee = findEmployeeById(data.getId());
        savedEmployee.updateData(data);

    }
}


