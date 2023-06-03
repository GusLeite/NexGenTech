package br.com.gusleite.NexGenTech.dtos;

import br.com.gusleite.NexGenTech.entities.Address;
import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.enums.Office;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class DetailEmployeeDataDTO {

        private Long id;
        private String name;
        private String cpf;
        private String telephone;
        private BigDecimal salary;
        private Office office;
        private Address address;
        private LocalDate lastPromotionDate;


    public DetailEmployeeDataDTO(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.cpf = employee.getCpf();
        this.telephone = employee.getTelephone();
        this.salary = employee.getSalary();
        this.office = employee.getOffice();
        this.address = employee.getAddress();
        this.lastPromotionDate = employee.getDateLastPromotion();
    }
}
