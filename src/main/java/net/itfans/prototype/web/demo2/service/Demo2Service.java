package net.itfans.prototype.web.demo2.service;

import net.itfans.prototype.web.demo2.dao.Demo2Dao;
import net.itfans.prototype.web.demo2.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Demo2Service {

    @Autowired
    private Demo2DispatchService demo2DispatchService;

    @Autowired
    private Demo2Dao demo2Dao;

    @Transactional
    public void execute(UserEntity userEntity) {
        demo2Dao.insertUser(userEntity);
    }
}
