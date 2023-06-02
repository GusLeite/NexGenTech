package br.com.gusleite.NexGenTech.entities;

import br.com.gusleite.NexGenTech.dtos.AddressDataDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Address {
    @NotBlank
    private String street;
    @NotNull
    private Integer number;
    @NotBlank
    private String neighborhood;
    @NotBlank
    private String city;
    @NotBlank
    private String federativeUnit;
    @NotBlank
    private String cep;


    public Address(AddressDataDTO address) {
        this.cep = address.getCep();
        this.city = address.getCity();
        this.federativeUnit = address.getFederativeUnit();
        this.number = address.getNumber();
        this.neighborhood = address.getNeighborhood();
        this.street = address.getStreet();
    }

    public void updateAddress(AddressDataDTO data) {
        if(data.getCep() != null){
            this.cep = data.getCep();
        }
        if(data.getCity() != null){
            this.city = data.getCity();
        }
        if(data.getFederativeUnit() != null){
            this.federativeUnit = data.getFederativeUnit();
        }
        if(data.getNumber() != null){
            this.number = data.getNumber();
        }
        if(data.getStreet() != null){
            this.street = data.getStreet();
        }
        if(data.getNeighborhood() != null){
            this.neighborhood = data.getNeighborhood();
        }
    }
}
