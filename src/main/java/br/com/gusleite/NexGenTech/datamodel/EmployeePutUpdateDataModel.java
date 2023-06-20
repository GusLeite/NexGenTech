package br.com.gusleite.NexGenTech.datamodel;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EmployeePutUpdateDataModel {

    @NotNull
    private Long id;
    private String name;
    private String telephone;
    private AddressDataModel addressDataModel;



}
