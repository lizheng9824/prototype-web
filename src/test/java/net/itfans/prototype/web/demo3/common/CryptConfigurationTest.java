package net.itfans.prototype.web.demo3.common;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {net.itfans.prototype.web.demo3.common.CryptConfiguration.class},
        initializers = ConfigDataApplicationContextInitializer.class)
public class CryptConfigurationTest {

    @Autowired
    private CryptUtils cryptUtils;


    @BeforeClass
    public static void setUp() throws Exception{
        CryptTestHelper.prepareTest();
    }

    @Test
    public void test暗号化テスト() throws Exception {
        String str = "hopeアプリ基盤のタスクは、アプリケーション開発をサポートするための部品開発やドキュメント標準作成することです。";
        String hopeCrypt = cryptUtils.encrypt(str);
        assertThat(hopeCrypt, not(str));

        String decryptStr = cryptUtils.decrypt(hopeCrypt);
        assertThat(decryptStr, is(str));
    }

    @AfterClass
    public static void shutDown() throws Exception {
        CryptTestHelper.endTest();
    }

}