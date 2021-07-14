package net.itfans.prototype.web.demo3.common;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;


public class CryptUtilsTest {

    private static CryptUtils target;

    @BeforeClass
    public static void setUp() throws Exception {
        CryptTestHelper.prepareTest();

        String decryptKeyPath = "c:\\tmp\\decryptKey";
        String decryptIvPath = "c:\\tmp\\decryptIv";
        String keyPath =  "c:\\tmp\\key";
        String ivPath =  "c:\\tmp\\iv";

        target = new CryptUtils(decryptKeyPath, decryptIvPath, keyPath, ivPath);

    }

    @Test
    public void test暗号化テスト() throws Exception {
        String str = "hopeアプリ基盤のタスクは、アプリケーション開発をサポートするための部品開発やドキュメント標準作成することです。";
        String hopeCrypt = target.encrypt(str);
        assertThat(hopeCrypt, not(str));

        String decryptStr = target.decrypt(hopeCrypt);
        assertThat(decryptStr, is(str));
    }

    @AfterClass
    public static void shutDown() throws Exception {
        CryptTestHelper.endTest();
    }
}