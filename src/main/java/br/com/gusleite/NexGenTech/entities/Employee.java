package br.com.gusleite.NexGenTech.entities;

import br.com.gusleite.NexGenTech.datamodel.EmployeePostRegisterDataModel;
import br.com.gusleite.NexGenTech.datamodel.EmployeePutUpdateDataModel;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;
import br.com.gusleite.NexGenTech.services.ValidatePromotionService;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String telephone;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private Office office;
    @Embedded
    private Address address;
    private LocalDate dateLastPromotion;

    public Employee(EmployeePostRegisterDataModel data){
        this.name = data.getName();
        this.cpf = data.getCpf();
        this.email = data.getEmail();
        this.telephone = data.getTelephone();
        this.salary = data.getSalary();
        this.office = data.getOffice();
        this.address = new Address(data.getAddress());
        this.dateLastPromotion = data.getLastPromotionDate();
    }

    public void promote() throws PromotionValidationFailAttemptException {
        new ValidatePromotionService().validate(this);
        this.office = this.office.NextOffice();
        this.dateLastPromotion = LocalDate.now();
    }

    public void updateData(EmployeePutUpdateDataModel data) {
        if(data.getName() != null) {
            this.name = data.getName();
        }
        if(data.getTelephone() != null) {
            this.telephone = data.getTelephone();
        }
        if(data.getAddressDataModel() != null) {
            address.updateAddress(data.getAddressDataModel());
        }
        if(data.getName() != null) {
            this.name = data.getName();
        }
    }
}
