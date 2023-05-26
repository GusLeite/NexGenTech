package br.com.gusleite.NexGenTech.dtos;

import br.com.gusleite.NexGenTech.entities.HomeAddress;
import br.com.gusleite.NexGenTech.enums.Office;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RegisterEmployeeData {
    private String name;
    private String cpf;
    private String email;
    private String telephone;
    private BigDecimal salary;
    private Office office;
    private HomeAddress homeAddress;
}