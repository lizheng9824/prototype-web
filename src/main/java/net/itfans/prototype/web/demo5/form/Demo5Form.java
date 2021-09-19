package net.itfans.prototype.web.demo5.form;

import lombok.Data;
import net.itfans.prototype.web.demo5.common.ValidList;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class Demo5Form {

    @NotEmpty
    private String id;

    @NotEmpty
    private String name;

    @ValidList
    @Valid
    private List<Demo5Detail> detailList;
}
