package hh.swd20.sauna.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import hh.swd20.sauna.validation.StringUpperCase;

public class StringUpperCaseValidator
implements ConstraintValidator<StringUpperCase, String> {
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(null == value) {
			return true;
		}
		return value.chars().mapToObj(i -> (char) i).noneMatch(Character::isLowerCase);
	}
	
	@Override
	public void initialize(StringUpperCase constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}
}
