package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.EmployeeNotFoundException;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;
import br.com.gusleite.NexGenTech.mapper.NexGenMapper;
import br.com.gusleite.NexGenTech.repositories.EmployeeRepository;
import br.com.gusleite.NexGenTech.vo.v1.EmployeeVO;
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

    public EmployeeVO findEmployeeVOById(long id){
        return NexGenMapper.parseObject(employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with this id Not Found!")),EmployeeVO.class);
    }
    public Employee findEmployeeById(long id){
        return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("Employee with this id Not Found!"));
    }

    public EmployeeVO findEmployeeByName(String name){
        return NexGenMapper.parseObject(employeeRepository.findEmployeeByName(name).orElseThrow(() -> new EmployeeNotFoundException("EmployeeVO Not Found!")),EmployeeVO.class);
    }

    public List<EmployeeVO> listEmployeeByOffice(Office office){
        return NexGenMapper.parseListObjects(employeeRepository.findEmployeeByOfficeIs(office),EmployeeVO.class);
    }

    public List<EmployeeVO> listEmployeeByFederativeUnit(FederativeUnit federativeUnit){
        return NexGenMapper.parseListObjects(employeeRepository.findEmployeeByAddress_FederativeUnitIs(federativeUnit),EmployeeVO.class);
    }

    public List<EmployeeVO> listEmployeeBySalaryIsLessThanEqual(BigDecimal salary){
        return NexGenMapper.parseListObjects(employeeRepository.findEmployeeBySalaryIsLessThanEqual(salary),EmployeeVO.class);
    }

    public List<EmployeeVO>listEmployeeBySalaryIsGreaterThanEqual(BigDecimal salary){
        return NexGenMapper.parseListObjects(employeeRepository.findEmployeeBySalaryIsGreaterThanEqual(salary),EmployeeVO.class);
    }

    public List<EmployeeVO> listAll() {
        return NexGenMapper.parseListObjects(employeeRepository.findAll(),EmployeeVO.class);
    }

    public EmployeeVO register(@Valid EmployeeVO data) {
        Employee employee = NexGenMapper.parseObject(data,Employee.class);
        employeeRepository.save(employee);
        return NexGenMapper.parseObject(employee,EmployeeVO.class);
    }

    public void delete(long id) {
        employeeRepository.deleteById(findEmployeeVOById(id).getId());
    }

    public void updateData(EmployeeVO data) {
        Employee savedEmployee = findEmployeeById(data.getId());
        savedEmployee.updateData(data);
    }

    public void promote(Long id) throws PromotionValidationFailAttemptException {
        Employee entity = findEmployeeById(id);
        entity.promote();
    }
}


