package net.itfans.prototype.web.demo5.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class Demo5Detail {

    @NotEmpty
    private String id;

    @NotEmpty
    private String name;
}
