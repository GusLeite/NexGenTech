package br.com.gusleite.NexGenTech.mappers;

import br.com.gusleite.NexGenTech.datamodel.EmployeeGetDetailDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeeGetListDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePostRegisterDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePutUpdateDataModel;
import br.com.gusleite.NexGenTech.entities.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    public static final EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);
    public Employee toEmployee(EmployeeGetDetailDataModel employeeGetDetailDataModel );
    public  Employee toEmployee(EmployeeGetListDataModel employeeGetListDataModel);
    public Employee toEmployee(EmployeePutUpdateDataModel employeePutUpdateDataModel);
    public Employee toEmployee(EmployeePostRegisterDataModel employeePostRegisterDataModel);
}
