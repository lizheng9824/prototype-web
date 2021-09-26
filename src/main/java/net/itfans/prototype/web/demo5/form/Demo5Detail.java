package net.itfans.prototype.web.demo5.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class Demo5Detail {

    @NotEmpty
    @Length(min = 3, max = 15)
    private String id;

    @NotEmpty
    private String name;
}
