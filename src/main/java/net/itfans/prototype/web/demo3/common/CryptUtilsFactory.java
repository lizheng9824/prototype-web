package net.itfans.prototype.web.demo3.common;


import java.io.InputStream;
import java.util.Properties;

/**
 * CryptUtilのファクトリクラス
 */
public class CryptUtilsFactory {

    /**
     * cryptの設定ファイルリソース名
     */
    private static final String cryptPropertyResourceName = "/crypt.properties";

    /**
     * CryptUtilsFactoryクラス
     */
    private static CryptUtils cryptUtils = null;

    /**
     * CryptUtilの取得する
     * @return　CryptUtilのSingletonインスタンス
     * @throws Exception
     */
    public static synchronized CryptUtils getCryptUtils() throws Exception {

        // インスタンス化された場合、インスタンスを返却
        if (cryptUtils != null) {
            return cryptUtils;
        }

        // インスタンス化されていなかったら、インスタンスを作成する。
        Properties properties = new Properties();

        // プロパティファイルを読み込む
        InputStream inputStream = CryptUtilsFactory.class.getResourceAsStream(cryptPropertyResourceName);
        if (inputStream != null) {
            properties.load(inputStream);
        }

        // 暗号化キー、初期ベクトルのパスを取得
        String decryptKeyPath = properties.getProperty("hope.crypt.decryptKeyPath");
        String decryptIvPath = properties.getProperty("hope.crypt.decryptIvPath");
        String keyPathPath = properties.getProperty("hope.crypt.keyPath");
        String ivPath = properties.getProperty("hope.crypt.ivPath");

        // 取得した暗号化キー、初期ベクトルでインスタンスを作成し返却する。
        cryptUtils = new CryptUtils(decryptKeyPath, decryptIvPath, keyPathPath, ivPath);
        return cryptUtils;
    }
}
