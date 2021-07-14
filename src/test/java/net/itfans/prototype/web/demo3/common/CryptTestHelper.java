package net.itfans.prototype.web.demo3.common;

import org.postgresql.shaded.com.ongres.scram.common.bouncycastle.base64.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.SecureRandom;

public class CryptTestHelper {



    public static String CIPHER = "AES";

    public static int KEY_SIZE = 128;


    private static String generateKey () throws Exception {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance(CIPHER);
            SecureRandom random = new SecureRandom();
            keygen.init(KEY_SIZE, random);
            return Base64.toBase64String(keygen.generateKey().getEncoded());
        } catch (Exception e) {
            throw new Exception("Can not make key", e);
        }
    }

    private static String generateIv () throws Exception {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] iv = new byte[16];
        random.nextBytes(iv);
        return Base64.toBase64String(iv);
    }

    public static void prepareTest() throws Exception{
        String key = generateKey();
        String decryptKey = generateKey();
        String decreptIv = generateIv();
        String iv = generateIv();

        String decryptKeyPath = "c:\\tmp\\decryptKey";
        String decryptIvPath = "c:\\tmp\\decryptIv";
        String keyPath =  "c:\\tmp\\key";
        String ivPath =  "c:\\tmp\\iv";

        Files.writeString(Paths.get(decryptKeyPath), decryptKey, StandardOpenOption.CREATE);
        Files.writeString(Paths.get(decryptIvPath), decreptIv, StandardOpenOption.CREATE);
        Files.writeString(Paths.get(ivPath), iv, StandardOpenOption.CREATE);

        SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(decryptKey), CIPHER);
        IvParameterSpec ivp = new IvParameterSpec(Base64.decode(iv));
        // アルゴリズムで暗号化用インスタンス作成
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        // 暗号化用インスタンス初期化
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivp);
        // 暗号化実施し、結果返却
        String encryptKey = Base64.toBase64String(cipher.doFinal(Base64.decode(key)));
        Files.writeString(Path.of(keyPath), encryptKey, StandardOpenOption.CREATE);

    }

    public static void endTest() throws Exception{
        String decryptKeyPath = "c:\\tmp\\decryptKey";
        String decryptIvPath = "c:\\tmp\\decryptIv";
        String keyPath =  "c:\\tmp\\key";
        String ivPath =  "c:\\tmp\\iv";

        Files.deleteIfExists(Paths.get(decryptKeyPath));
        Files.deleteIfExists(Paths.get(decryptIvPath));
        Files.deleteIfExists(Paths.get(keyPath));
        Files.deleteIfExists(Paths.get(ivPath));

    }
}
