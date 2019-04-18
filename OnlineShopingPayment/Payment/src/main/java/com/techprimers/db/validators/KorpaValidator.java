package com.techprimers.db.validators;

import com.techprimers.db.model.Korpa;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class KorpaValidator implements Validator{
    private final Validator addressValidator;

    public KorpaValidator(Validator addressValidator)
    {
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class clazz) {
        return Korpa.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"brojproizvoda","field.required ");
        Korpa korpa = (Korpa) o;
        try{
            errors.pushNestedPath("korpa");
            ValidationUtils.invokeValidator(this.addressValidator,korpa.getId_korpe(),errors);
        }finally {
            errors.popNestedPath();

        }
    }
    public void validateBrojProizvoda(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"brojproizvoda","brojproizvoda.empty");
        Korpa k = (Korpa) o;
        if(k.getBroj_proizvoda()==0){
            e.rejectValue("brojpzoizvoda","emtpyslot");
        }
    }
}
