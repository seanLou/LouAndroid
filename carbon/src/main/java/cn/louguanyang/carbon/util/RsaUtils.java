package cn.louguanyang.carbon.util;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static cn.louguanyang.carbon.spec.Constans.UTF_8;

/**
 * Created by louguanyang on 16/2/12.
 */
public class RsaUtils {
    private static final String ALGORITHM = "RSA";
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDFs9A9IlnIXDpTD1iI5BHmiV9IuyF5q3BxzVG0lm8A162pVcABlIWNm964adhQFOpwKXNChHvrAhWI1hx0wzI+HrWkUq+5NhkngiLrqDMT+tzmAenKdftvSRGt8W31093d8p0IxO1MOU9v/Myno1C/IPxIvpfyIyh9SWK55qHYJQIDAQAB";
    private static final String RSA_ECB_PKCS1_PADDING = "RSA/ECB/PKCS1Padding";

    @CheckResult
    public static String encryptString(@NonNull String source) {
        try {
            PublicKey publicKey = getPublicKeyFromX509(PUBLIC_KEY);
            Cipher cipher = Cipher.getInstance(RSA_ECB_PKCS1_PADDING);
            cipher.init(Cipher.ENCRYPT_MODE,publicKey);
            byte[] sourceBytes = source.getBytes(UTF_8);
            byte[] outBytes = cipher.doFinal(sourceBytes);
            return new String(Base64.encode(outBytes, Base64.DEFAULT));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return null;
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static PublicKey getPublicKeyFromX509(@NonNull String bysKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedKey = Base64.decode(bysKey, Base64.DEFAULT);
        X509EncodedKeySpec x509 = new X509EncodedKeySpec(decodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(x509);
    }
}
