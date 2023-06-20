package br.com.gusleite.NexGenTech.datamodel;


import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.enums.Office;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Data
public class EmployeeGetListDataModel {
    private Long id;
    private String name;
    private String email;
    private Office office;
    private LocalDate lastPromotionDate;

    public EmployeeGetListDataModel(Employee employee){
        this.id = employee.getId();
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.office = employee.getOffice();
        this.lastPromotionDate = employee.getDateLastPromotion();
    }

}
