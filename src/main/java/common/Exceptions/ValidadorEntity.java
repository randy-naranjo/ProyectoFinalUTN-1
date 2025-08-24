package common.Exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

public class ValidadorEntity {
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    public static final Validator validator= factory.getValidator();

    public static <T> void validar(T entidad){

        Set<ConstraintViolation<T>> violaciones = validator.validate(entidad);

        if(!violaciones.isEmpty()){
            StringBuilder errores= new StringBuilder("Errores de validacion:\n");
            for(ConstraintViolation<T> v: violaciones){
                errores.append("* ").append(v.getPropertyPath()).append(": ").append(v.getMessage()).append("\n");
            }
            throw new ValidationException(errores.toString());
        }
    }
}
