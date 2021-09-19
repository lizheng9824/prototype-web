package net.itfans.prototype.web.demo5.controller;

import net.itfans.prototype.web.demo5.form.Demo5Form;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Demo5Controller {

    @GetMapping("demo5")
    public String show(@ModelAttribute Demo5Form demo5Form) {
        return "demo5/edit";
    }

    @PostMapping("demo5/update")
    public String update(@Validated Demo5Form demo5Form, BindingResult result, Model model) {
        // エラーの場合
        if (result.hasErrors()) {
            return "demo5/edit";
        }

        return "demo5/edit";
    }
}
