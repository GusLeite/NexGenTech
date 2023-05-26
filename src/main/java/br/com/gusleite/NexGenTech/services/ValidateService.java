package br.com.gusleite.NexGenTech.services;

import br.com.gusleite.NexGenTech.entities.Employee;
import br.com.gusleite.NexGenTech.enums.Office;
import br.com.gusleite.NexGenTech.exceptions.PromotionValidationFailAttemptException;

public class ValidateService implements ValidationsPromoteService{
    @Override
    public void validate(Employee employee) throws PromotionValidationFailAttemptException {
        if(employee.getOffice() == Office.PRODUCT_OWNER){
            throw new PromotionValidationFailAttemptException("Managers can't be promoted");
        }
        new ValidateDataOfLastPromotionService().validate(employee);
    }
}
