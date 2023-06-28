package br.com.gusleite.NexGenTech.datamodel;

import br.com.gusleite.NexGenTech.entities.Address;
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

    public AddressDataModel(Address address) {
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.neighborhood = address.getNeighborhood();
        this.city = address.getCity();
        this.federativeUnit = address.getFederativeUnit();
        this.cep = address.getCep();
    }
}
