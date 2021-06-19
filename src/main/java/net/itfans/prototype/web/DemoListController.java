package net.itfans.prototype.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoListController {

    @GetMapping("/")
    public String index() {
        return "index";
    }
}
