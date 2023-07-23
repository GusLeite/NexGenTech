package br.com.gusleite.NexGenTech.vo.v1;

import br.com.gusleite.NexGenTech.entities.Address;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.mapper.NexGenMapper;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class EmployeeVO {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String telephone;
    private BigDecimal salary;
    @Enumerated(EnumType.STRING)
    private Office office;
    @Embedded
    private Address address;
    private LocalDate dateLastPromotion;

    public EmployeeVO(EmployeeVO data){
        this.name = data.getName();
        this.cpf = data.getCpf();
        this.email = data.getEmail();
        this.telephone = data.getTelephone();
        this.salary = data.getSalary();
        this.office = data.getOffice();
        this.address = NexGenMapper.parseObject(data.getAddress(),Address.class);
        this.dateLastPromotion = data.getDateLastPromotion();
    }
}
