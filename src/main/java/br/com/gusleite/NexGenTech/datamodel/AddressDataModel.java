package br.com.gusleite.NexGenTech.datamodel;

import br.com.gusleite.NexGenTech.entities.FederativeUnit;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDataModel {
    @NotEmpty
    private String street;
    @NotNull
    private Integer number;
    @NotEmpty
    private String neighborhood;
    @NotEmpty
    private String city;
    @NotEmpty
    private FederativeUnit federativeUnit;
    @NotEmpty
    private String cep;
}
