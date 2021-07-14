package net.itfans.prototype.web.demo3.common;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class CryptUtilsFactoryTest {

    public CryptUtilsFactory target;

    @BeforeClass
    public static void setUp() throws Exception{
        CryptTestHelper.prepareTest();
    }

    @Test
    public void test暗号化() throws Exception {
        CryptUtils cryptUtils = target.getCryptUtils();
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