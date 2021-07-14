package net.itfans.prototype.web.demo2.controller;

import net.itfans.prototype.web.demo2.entity.UserEntity;
import net.itfans.prototype.web.demo2.service.Demo2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;
import java.util.Date;

@RequestMapping("/demo2")
@Controller
public class Demo2Controller {

    @Autowired
    private Demo2Service demo2Service;

    @GetMapping("")
    public String index() {
        return "demo2/show";
    }

    @PostMapping
    public String insert() {

        UserEntity userEntity = new UserEntity();
        userEntity.setPassword("base1234");
        userEntity.setInsertDate(new Timestamp(new Date().getTime()));
        userEntity.setUpdateDate(new Timestamp(new Date().getTime()));

        demo2Service.execute(userEntity);
        return "demo2/show";
    }
}
