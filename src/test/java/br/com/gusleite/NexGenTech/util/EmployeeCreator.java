package br.com.gusleite.NexGenTech.util;

import br.com.gusleite.NexGenTech.entities.Address;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import br.com.gusleite.NexGenTech.enums.Office;

import java.math.BigDecimal;

public class EmployeeCreator {
    public static Employee createEmployeeToBeSaved(){
        Employee employee = Employee.builder().name("Gioberto").cpf("52633374903").office(Office.MANAGER).email("gioberto@email.com").telephone("11953128344").salary(new BigDecimal("2350")).office(Office.TRAINEE).build();
        employee.setAddress(new Address());
        employee.getAddress().setCep("08230040");
        employee.getAddress().setCity("Sao Paulo");
        employee.getAddress().setNumber(116);
        employee.getAddress().setNeighborhood("Itaquera");
        employee.getAddress().setFederativeUnit(FederativeUnit.SP);
        employee.getAddress().setStreet("Rua Jagyaryba");
        employee.setId(1L);

        return employee;
    }
}
