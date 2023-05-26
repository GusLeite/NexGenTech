package br.com.gusleite.NexGenTech.entities;

import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.services.EmployeeService;
import br.com.gusleite.NexGenTech.services.ValidateService;
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
    private LocalDate lastPromotionDate;
//    private HomeAddress homeAddres;s;

    public void promote() throws Exception {
        new ValidateService().validate(this);
        this.office = this.office.NextOffice();
    }
}
