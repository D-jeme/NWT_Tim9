package com.techprimers.db.validators;

import com.techprimers.db.model.Users;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UsersValidator implements Validator {
    private final Validator addressValidator;

    public UsersValidator(Validator addressValidator) {
        this.addressValidator = addressValidator;
    }

    public void validateName(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"name","name.empty");
        Users u = (Users) o;
        if(u.getName().equals(" ")){
            e.rejectValue("name","emtpyslot");
        }
    }

    public void validateTeamName(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"teamname","teamname.empty");
        Users u = (Users) o;
        if(u.getTeamName().equals(" ")){
            e.rejectValue("teamname","emtpyslot");
        }
    }

    public void validateSalary(Object o, Errors e){
        ValidationUtils.rejectIfEmpty(e,"salary","salary.empty");
        Users u = (Users) o;
        if(u.getSalary()<0){
            e.rejectValue("salary","negativevalue");
        }else if(u.getSalary()==0){
            e.rejectValue("salary","empty");
        }
    }

    @Override
    public boolean supports(Class clazz) {
        return Users.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"name","field.required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"teamname","field.required ");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors,"salary","field.required");
        Users user = (Users) o;
        try{
            errors.pushNestedPath("users");
            ValidationUtils.invokeValidator(this.addressValidator,user.getName(),errors);
        }finally {
            errors.popNestedPath();

        }
    }
}
