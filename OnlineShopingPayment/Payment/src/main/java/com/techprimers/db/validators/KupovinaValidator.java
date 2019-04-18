package com.techprimers.db.validators;

import com.techprimers.db.model.Kupovina;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class KupovinaValidator implements Validator{
    private final Validator addressValidator;

    public KupovinaValidator(Validator addressValidator) {
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class clazz) {
        return Kupovina.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"kolicina","field.required ");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"date","field.required");
        Kupovina kupovina = (Kupovina) o;
        try{
            errors.pushNestedPath("kupovina");
            ValidationUtils.invokeValidator(this.addressValidator,kupovina.getId(),errors);
        }finally {
            errors.popNestedPath();

        }
    }
    public void validateDate(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"date","date.empty");
        Kupovina k = (Kupovina) o;
        if(k.getDate().equals(" ")){
            e.rejectValue("date","emtpyslot");
        }
    }
    public void validateKolicina(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"kolicina","kolicina.empty");
        Kupovina k = (Kupovina) o;
        if(k.getKolicina()==0){
            e.rejectValue("kolicina","emtpyslot");
        }
    }
}
