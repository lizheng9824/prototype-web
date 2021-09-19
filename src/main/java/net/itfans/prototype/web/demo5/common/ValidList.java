package net.itfans.prototype.web.demo5.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint( validatedBy = ListValidator.class)
@Documented
public @interface ValidList {
    
    String message() default "{list.invalid}";
    
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
