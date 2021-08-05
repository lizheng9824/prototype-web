package net.itfans.prototype.web.demo2.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class Demo2DispatchServiceTest {

    @Autowired
    Demo2DispatchService demo2DispatchService;

    @Test
    public void testSuccess() {
        demo2DispatchService.execute(null, false, false);
    }
}