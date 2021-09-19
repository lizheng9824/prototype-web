package net.itfans.prototype.web.demo5.common;

import net.itfans.prototype.web.demo5.form.Demo5Detail;

import javax.validation.*;
import java.util.*;

public class ListValidator implements ConstraintValidator<ValidList, List<?>> {

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Map<Integer, Object> violationMap = new HashMap<Integer, Object>();
        for (int i = 0; i < value.size(); i++) {
            Set<? extends ConstraintViolation<?>> result = validator.validate(value.get(i));
            violationMap.put(i, result);
        }

        return false;
    }
}
