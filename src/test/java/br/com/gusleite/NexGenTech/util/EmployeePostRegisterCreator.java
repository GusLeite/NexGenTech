package br.com.gusleite.NexGenTech.util;

import br.com.gusleite.NexGenTech.datamodel.AddressDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePostRegisterDataModel;
import br.com.gusleite.NexGenTech.entities.Address;
import br.com.gusleite.NexGenTech.entities.FederativeUnit;

public class EmployeePostRegisterCreator {
    public static EmployeePostRegisterDataModel createEmployeePostBody(){
        EmployeePostRegisterDataModel employee = EmployeePostRegisterDataModel.builder().cpf(EmployeeCreator.createEmployeeToBeSaved().getCpf())
                .email(EmployeeCreator.createEmployeeToBeSaved().getEmail())
                .lastPromotionDate(EmployeeCreator.createEmployeeToBeSaved().getDateLastPromotion())
                .telephone(EmployeeCreator.createEmployeeToBeSaved().getTelephone())
                .salary(EmployeeCreator.createEmployeeToBeSaved().getSalary())
                .name(EmployeeCreator.createEmployeeToBeSaved().getName())
                .office(EmployeeCreator.createEmployeeToBeSaved().getOffice()).
                build();

                employee.setAddress(new AddressDataModel());
                employee.getAddress().setCep("08230040");
                employee.getAddress().setCity("Sao Paulo");
                employee.getAddress().setNumber(116);
                employee.getAddress().setNeighborhood("Itaquera");
                employee.getAddress().setFederativeUnit(FederativeUnit.SP);
                employee.getAddress().setStreet("Rua Jagyaryba");

                return employee;
    }
}
