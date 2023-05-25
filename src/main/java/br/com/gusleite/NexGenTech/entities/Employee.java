package br.com.gusleite.NexGenTech.entities;

import br.com.gusleite.NexGenTech.entities.enums.Office;

import java.math.BigDecimal;

public class Employee {
    private String name;
    private String cpf;
    private String email;
    private Integer telephone;
    private BigDecimal salary;
    private Office office;
    private HomeAddress address;
}
