package hh.swd20.sauna.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import hh.swd20.sauna.validation.validator.StringUpperCaseValidator;

@Documented
@Retention(RUNTIME)
@Target({ FIELD, PARAMETER })
@Constraint(validatedBy = StringUpperCaseValidator.class)
public @interface StringUpperCase {
	String message() default "String not in uppercase";
	
	Class<?> [] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
