package textencryptor;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {
	private static final String SECRET_KEY = "MySecretKey10101";
	private static final String ALGORITHM = "AES";
	private static final String TRANSFORMATION = "AES/ECB/PKCS5Padding";
	private static final String ENCRYPTION_TYPE = "encrypt";
	private static final String DECRYPTION_TYPE = "decrypt";

	public static String encryptOrDecrypt(final String strToEncrypt, final String type) throws Exception {
		final SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
		final Cipher cipher = Cipher.getInstance(TRANSFORMATION);
		if (type.equalsIgnoreCase(DECRYPTION_TYPE)) {
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.getDecoder().decode(strToEncrypt)));
		} else if (type.equalsIgnoreCase(ENCRYPTION_TYPE)) {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
		} else {
			throw new IllegalArgumentException("Invalid type: " + type);
		}
	}
}
