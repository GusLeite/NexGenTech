package br.com.gusleite.NexGenTech.util;

import br.com.gusleite.NexGenTech.datamodel.EmployeePutUpdateDataModel;

public class EmployeePutUpdateRegisterCreator {
    public static EmployeePutUpdateDataModel createEmployeePostBody(){
        return EmployeePutUpdateDataModel.builder()
                .id(EmployeeCreator.createEmployeeToBeSaved().getId())
                .name(EmployeeCreator.createEmployeeToBeSaved().getName())
                .telephone(EmployeeCreator.createEmployeeToBeSaved().getTelephone()).build();
    }
}
