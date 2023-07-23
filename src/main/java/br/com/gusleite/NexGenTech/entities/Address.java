package br.com.gusleite.NexGenTech.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Embeddable
public class Address {
    private String street;
    private Integer number;
    private String neighborhood;
    private String city;
    @Enumerated(EnumType.STRING)
    private FederativeUnit federativeUnit;
    private String cep;

    public void updateAddress(Address data) {
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
