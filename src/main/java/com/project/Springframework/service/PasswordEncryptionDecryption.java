package com.project.Springframework.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordEncryptionDecryption {

	private static final String ALGORITHM = "AES";
	private static final byte[] SECRET_KEY = "MySecretKey12345".getBytes(); // Must be 16 bytes

	// Encrypt the password
	public static String encrypt(String password) throws Exception {
		SecretKey key = new SecretKeySpec(SECRET_KEY, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedBytes = cipher.doFinal(password.getBytes());
		return Base64.getEncoder().encodeToString(encryptedBytes); 
	}

	// Decrypt the password
	public static String decrypt(String encryptedPassword) throws Exception {
		SecretKey key = new SecretKeySpec(SECRET_KEY, ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedPassword));
		return new String(decryptedBytes);
	}

}
