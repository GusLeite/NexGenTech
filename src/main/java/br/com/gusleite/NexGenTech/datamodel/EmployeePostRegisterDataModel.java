package br.com.gusleite.NexGenTech.datamodel;
import br.com.gusleite.NexGenTech.entities.Address;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.enums.Office;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class EmployeePostRegisterDataModel {
    @NotEmpty
    private String name;
    @NotEmpty
    private String cpf;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String telephone;
    @NotNull
    private BigDecimal salary;
    @NotNull
    private Office office;

    private AddressDataModel address;
    @NotNull
    private LocalDate lastPromotionDate;

    public EmployeePostRegisterDataModel(Employee savedEmployee) {
        this.name = savedEmployee.getName();
        this.telephone = savedEmployee.getTelephone();
        this.cpf = savedEmployee.getCpf();
        this.email = savedEmployee.getEmail();
        this.telephone = savedEmployee.getTelephone();
        this.salary = savedEmployee.getSalary();
        this.office = savedEmployee.getOffice();
        this.address = new AddressDataModel(savedEmployee.getAddress());
        this.lastPromotionDate = savedEmployee.getDateLastPromotion();
    }
}