package net.itfans.prototype.web.demo3.common;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 暗号化ユーティリティのBean定義Configurationクラス
 */
@Configuration
public class CryptConfiguration {

    /**
     * 暗号化ユーティリティを作成
     * @param decryptKeyPath 暗号化キー復号用キーファイルパス
     * @param decryptIvPath 暗号化キー復号用初期ベクトルファイルパス
     * @param keyPath 暗号キーファイルパス
     * @param ivPath 初期ベクトルファイルパス
     * @return 暗号化ユーティリティ
     * @throws Exception
     */
    @Bean
    public CryptUtils getCryptUtils(
            @Value("${hope.crypt.decryptKeyPath}")String decryptKeyPath,
            @Value("${hope.crypt.decryptIvPath}")String decryptIvPath,
            @Value("${hope.crypt.keyPath}")String keyPath,
            @Value("${hope.crypt.ivPath}")String ivPath) throws Exception {
        return new CryptUtils(decryptKeyPath,decryptIvPath,keyPath, ivPath);
    }
}
