package br.com.gusleite.NexGenTech.dtos;

import br.com.gusleite.NexGenTech.entities.Employee;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UpdateEmployeeRegisterDTO {

    @NotNull
    private Long id;
    private String name;
    private String telephone;
    private AddressDataDTO addressDataDTO;

}
