package br.com.gusleite.NexGenTech.entities;

import br.com.gusleite.NexGenTech.enums.Office;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String telephone;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private Office office;

//    @OneToOne
//    private HomeAddress address;
}
