package net.itfans.prototype.web.demo5.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class Demo5Form {

    @NotEmpty
    @Length(min = 3, max = 15)
    private String id;

    @NotEmpty
    private String name;

    @Valid
    private List<Demo5Detail> detailList;
}
