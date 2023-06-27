package br.com.gusleite.NexGenTech.datamodel;
import br.com.gusleite.NexGenTech.enums.Office;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

}