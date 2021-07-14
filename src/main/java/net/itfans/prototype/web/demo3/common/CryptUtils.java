package net.itfans.prototype.web.demo3.common;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/** 暗号化用ツールクラス */
public class CryptUtils {

    /** 暗号化モード */
    enum MODE {
        ENCRYPT,
        DECRYPT
    };

    /**
     * 暗号化アルゴリズム
     */
    public static String ALGORITHM = "AES/CBC/PKCS5Padding";

    /**
     * 暗号化CIPHER
     */
    public static String CIPHER = "AES";

    /**
     * 暗号化文字CHARSET
     */
    public static String CHARSET = "UTF-8";

    /**
     * 暗号化用キー
     */
    private byte[] encryptKey;

    /**
     * 暗号化用初期ベクトル
     */
    private byte[] encryptIv;

    /**
     * コンストラクタ
     * @param decryptKeyPath 暗号化キー復号用キーファイルパス
     * @param decryptIvPath 暗号化キー復号用初期ベクトルファイルパス
     * @param keyPath 暗号キーファイルパス
     * @param ivPath 初期ベクトルファイルパス
     * @throws Exception
     */
    public CryptUtils(String decryptKeyPath, String decryptIvPath, String keyPath, String ivPath)
            throws Exception {    // TODO システム例外をthrow

        // 暗号化キー復号用キーを読み込み
        String decryptKey = Files.readString(Paths.get(decryptKeyPath));
        // 暗号化キー復号用初期ベクトルを読み込み
        String decryptIv = Files.readString(Paths.get(decryptIvPath));
        // 暗号化キーを読み込み
        String key = Files.readString(Paths.get(keyPath));
        // 暗号化初期ベクトルを読み込み
        String iv = Files.readString(Paths.get(ivPath));

        // 読み込んだ暗号化キー復号用キー、暗号化キー復号用初期ベクトル、暗号化キーをBASE64デコード
        byte[] decryptBytes = Base64.getDecoder().decode(decryptKey);
        byte[] decryptIvBytes = Base64.getDecoder().decode(decryptIv);
        byte[] keyBytes = Base64.getDecoder().decode(key);

        // 暗号化初期ベクトルをBASE64デコードし、退避する
        this.encryptIv = Base64.getDecoder().decode(iv);

        // 暗号化状態になっている暗号化キーを平文の暗号化キーに復号し、退避する
        this.encryptKey = crypt(keyBytes, MODE.DECRYPT, decryptBytes, decryptIvBytes);
    }

    /**
     * 平文文字の暗号を行う
     * @param plainText 平文テキスト
     * @return 暗号化後文字列
     * @throws Exception
     */
    public String encrypt (String plainText)
            throws Exception {  // TODO システム例外をthrow
        // 退避された暗号化キー、初期ベクトルで暗号化を行う
        byte[] cipherBytes = crypt(plainText.getBytes(), MODE.ENCRYPT, this.encryptKey, this.encryptIv);

        // 暗号化した結果をBASE64をエンコードして返却
        String base64Text = Base64.getEncoder().encodeToString(cipherBytes);
        return base64Text;
    }

    /**
     * 暗号化文字の復号を行う
     * @param base64Text 暗号化したBASE64文字
     * @return 平文文字
     * @throws Exception 例外
     */
    public String decrypt (String base64Text)
            throws Exception { // TODO システム例外をthrow
        // 暗号化文字をBASE64デコードする
        byte[] cipherBytes = Base64.getDecoder().decode(base64Text.getBytes() );

        // 退避した暗号化キー、初期ベクトルで復号を行う
        byte[] plainBytes = crypt(cipherBytes, MODE.DECRYPT, this.encryptKey, this.encryptIv);

        // 復号したデータを文字列に変換し返却する
        String plainText = new String(plainBytes, CHARSET);
        return plainText;
    }

    /**
     * 暗号化・復号化処理
     * @param input 暗号化・復号化対象データ
     * @param mode 暗号化・復号化モード  ENCRYPT-暗号化、DECRYPT-復号化
     * @param key 暗号化キー
     * @param iv 初期ベクトル
     * @return 暗号化・復号化結果データ
     * @throws Exception
     */
    private byte[] crypt(byte[] input, MODE mode, byte[] key, byte[] iv) throws Exception {
        byte[] output;
        // 暗号化キー、初期ベクトルのオブジェクトを作成
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, CIPHER);
        IvParameterSpec ivp = new IvParameterSpec(iv);

        // アルゴリズムで暗号化用インスタンス作成
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        // 暗号化・復号化モード設定
        int m = (mode == MODE.ENCRYPT)
                ? Cipher.ENCRYPT_MODE : Cipher.DECRYPT_MODE;

        // 暗号化用インスタンス初期化
        cipher.init(m, secretKeySpec, ivp);

        // 暗号化・復号化実施し、結果返却
        output = cipher.doFinal(input);
        return output;
    }
}
