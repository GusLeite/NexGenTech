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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeVO findEmployeeVOById(long id){
        return NexGenMapper.parseObject(employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException("EmployeeVO Not Found!")),EmployeeVO.class);
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

    public Page<EmployeeVO> listAll(Pageable pageable) {
        Page<Employee> employeePage = employeeRepository.findAll(pageable);
        List<EmployeeVO> employeeVOList = NexGenMapper.parseListObjects(employeePage.getContent(), EmployeeVO.class);

        return new PageImpl<>(employeeVOList);
    }

    public EmployeeVO register(@Valid EmployeeVO data) {
        Employee employee = NexGenMapper.parseObject(data,Employee.class);
        EmployeeVO employeeVO = NexGenMapper.parseObject(employeeRepository.save(employee),EmployeeVO.class);
        return employeeVO;
    }

    public void delete(long id) {
        employeeRepository.deleteById(findEmployeeVOById(id).getId());
    }

    public void updateData(EmployeeVO data) {
        Employee savedEmployee = NexGenMapper.parseObject(findEmployeeVOById(data.getId()),Employee.class);
        savedEmployee.updateData(data);
    }

    public void promote(Long id) throws PromotionValidationFailAttemptException {
        Employee entity = NexGenMapper.parseObject(findEmployeeVOById(id),Employee.class);
        entity.promote();
    }
}


