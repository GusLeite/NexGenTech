package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;

import java.time.LocalDate;

public class ValidateDataOfLastPromotionService implements ValidationsPromoteService{
    @Override
    public void validate(Employee employee) throws PromotionValidationFailAttemptException {
        if(LocalDate.now().getMonthValue() - employee.getLastPromotionDate().getMonthValue() < 6 ); {
            throw new PromotionValidationFailAttemptException("The minimum time for a promotion to occur is 6 months.");
        }
    }
}
