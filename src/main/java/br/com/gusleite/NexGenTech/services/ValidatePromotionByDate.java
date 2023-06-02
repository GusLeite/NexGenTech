package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;

import java.time.LocalDate;

public class ValidatePromotionByDate implements ValidationsPromoteService{
    @Override
    public void validate(Employee employee) throws PromotionValidationFailAttemptException {
        if(LocalDate.now().getMonthValue() - employee.getDateLastPromotion().getMonthValue() < 6){
            throw new PromotionValidationFailAttemptException("the minimum time between one promotion and another is 6 months");
        }
    }
}
