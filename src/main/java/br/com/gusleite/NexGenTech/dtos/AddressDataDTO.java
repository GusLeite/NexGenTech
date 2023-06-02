package br.com.gusleite.NexGenTech.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDataDTO {
    @NotEmpty
    private String street;
    @NotNull
    private Integer number;
    @NotEmpty
    private String neighborhood;
    @NotEmpty
    private String city;
    @NotEmpty
    private String federativeUnit;
    @NotEmpty
    private String cep;
}
