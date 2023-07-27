package br.com.gusleite.NexGenTech.vo.v1;

import br.com.gusleite.NexGenTech.entities.Address;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.mapper.NexGenMapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"id","name","salary","office","email","cpf","address","dateLastPromotion"})
public class EmployeeVO {
    private Long id;
    @NotBlank
    private String name;
    @NotBlank
    private String cpf;
    @NotBlank
    private String email;
    @NotBlank
    private String telephone;
    @NotNull
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private Office office;
    @Embedded
    private Address address;
    @JsonProperty("date_last_promotion")
    private LocalDate dateLastPromotion;

    public EmployeeVO(EmployeeVO data){
        this.id = data.getId();
        this.name = data.getName();
        this.cpf = data.getCpf();
        this.email = data.getEmail();
        this.telephone = data.getTelephone();
        this.salary = data.getSalary();
        this.office = data.getOffice();
        this.address = NexGenMapper.parseObject(data.getAddress(),Address.class);
        this.dateLastPromotion = data.getDateLastPromotion();
    }
}
