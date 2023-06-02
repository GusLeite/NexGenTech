package br.com.gusleite.NexGenTech.dtos;

import br.com.gusleite.NexGenTech.enums.Office;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class RegisterEmployeeDataDTO {
    @NotEmpty
    private String name;
    @NotEmpty
    @CPF
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

    private AddressDataDTO address;
    @NotNull
    private LocalDate lastPromotionDate;

}