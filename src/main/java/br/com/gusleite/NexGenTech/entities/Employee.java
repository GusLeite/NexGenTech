package br.com.gusleite.NexGenTech.entities;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;
import br.com.gusleite.NexGenTech.services.ValidatePromotionService;
import br.com.gusleite.NexGenTech.vo.v1.EmployeeVO;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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

    public void promote() throws PromotionValidationFailAttemptException {
        new ValidatePromotionService().validate(this);
        this.office = this.office.NextOffice();
        this.dateLastPromotion = LocalDate.now();
    }

    public void updateData(EmployeeVO data) {
        if(data.getName() != null) {
            this.name = data.getName();
        }
        if(data.getTelephone() != null) {
            this.telephone = data.getTelephone();
        }
        if(data.getAddress() != null) {
            address.updateAddress(data.getAddress());
        }
        if(data.getName() != null) {
            this.name = data.getName();
        }
    }
}
