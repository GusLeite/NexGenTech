package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;

import java.time.LocalDate;
import java.time.Period;

public class ValidatePromotionByDate implements ValidationsPromoteService{
    @Override
    public void validate(Employee employee) throws PromotionValidationFailAttemptException {
        if(Period.between(employee.getDateLastPromotion(),LocalDate.now()).getMonths() < 6){
            throw new PromotionValidationFailAttemptException("the minimum time between one promotion and another is 6 months last promotio date:  " + employee.getDateLastPromotion());
        }
    }
}
