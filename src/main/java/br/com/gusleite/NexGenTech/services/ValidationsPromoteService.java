package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;

public interface ValidationsPromoteService {
    public void validate(Employee employee) throws Exception;
}
