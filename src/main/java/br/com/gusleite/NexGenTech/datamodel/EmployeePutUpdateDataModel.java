package br.com.gusleite.NexGenTech.datamodel;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EmployeePutUpdateDataModel {

    @NotNull
    private Long id;
    private String name;
    private String telephone;
    private AddressDataModel addressDataModel;



}
