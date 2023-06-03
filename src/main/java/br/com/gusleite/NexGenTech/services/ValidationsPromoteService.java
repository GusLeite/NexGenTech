package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;

public interface ValidationsPromoteService {
    public void validate(Employee employee) throws PromotionValidationFailAttemptException;
}
