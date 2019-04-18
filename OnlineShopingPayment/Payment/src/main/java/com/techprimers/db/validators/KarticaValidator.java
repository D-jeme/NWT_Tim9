package com.techprimers.db.validators;

import com.techprimers.db.model.Kartice;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class KarticaValidator implements Validator {

    private final Validator addressValidator;

    public KarticaValidator(Validator addressValidator) {
        this.addressValidator = addressValidator;
    }

    @Override
    public boolean supports(Class clazz) {
        return Kartice.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"broj","field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"tip","field.required ");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"securitycode","field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"datumisteka","field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"nosilackartice","field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"stanje","field.required");
        Kartice kartice = (Kartice) o;
        try{
            errors.pushNestedPath("kartice");
            ValidationUtils.invokeValidator(this.addressValidator,kartice.getNosilac_kartice(),errors);
        }finally {
            errors.popNestedPath();

        }
    }

    public void validateBroj(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"broj","broj.empty");
        Kartice k = (Kartice) o;
        if(!(k.getBroj()==0)){
            e.rejectValue("broj","emptyslot");
        }
    }
    public void validateTip(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"tip","tip.empty");
        Kartice k = (Kartice) o;
        if(!(k.getTip().equals(" "))){
            e.rejectValue("tip","emptyslot");
        }
    }
    public void validateSecurityCode(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"securitycode","securitycode.empty");
        Kartice k = (Kartice) o;
        if(!(k.getSecurity_code()==0)){
            e.rejectValue("securitycode","emptyslot");
        }
    }
    public void validateDatumIsteka(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"datumisteka","datumisteka.empty");
        Kartice k = (Kartice) o;
        if(!(k.getDatum_isteka().equals(" "))){
            e.rejectValue("datumisteka","emptyslot");
        }
    }
    public void validateNosilacKartice(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"nosilackartice","nosilackartice.empty");
        Kartice k = (Kartice) o;
        if(!(k.getNosilac_kartice().equals(" "))){
            e.rejectValue("nosilackartice","emptyslot");
        }
    }
    public void validateStanje(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"stanje","stanje.empty");
        Kartice k = (Kartice) o;
        if(k.getStanje()<0){
            e.rejectValue("stanje","negativevalue");
        }
    }
}

