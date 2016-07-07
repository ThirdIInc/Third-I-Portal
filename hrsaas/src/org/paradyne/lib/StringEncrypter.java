package org.paradyne.lib;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Sunil
 * @date 29 Nov 2007
**/

/**
 * This class encrypts and decrypts the strings
**/

public class StringEncrypter {
	public static class EncryptionException extends Exception {

		public EncryptionException(Throwable t) {
			super(t);
		}
	}

	public static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
	public static final String DES_ENCRYPTION_SCHEME = "DES";
	public static final String DEFAULT_ENCRYPTION_KEY = "This is a fairly long phrase used to Naval Dokyards";
	public static final String URL_ENCRYPTION_KEY = "Beauty only gets attention but personality captures the heart ";

	public static final String DBPOOL_ENCRYPTION_KEY = "DECIDE what YOU wish TO do, AND then DO it.";
	public static final String ONLINE_EMAIL_ENCRYPTION_KEY ="hgdfhgf  kjhpioueqw3456h hguy9986443 hjgu7853h jkjii743 mn984321"; 
	public static final String USER_EMAIL_ENCRYPTION_KEY ="fjgkldfj erwerh qwerqewdeb fsdfufg23432j hgdsas4320kxcuhj hiosahdjkasbd";
		//"If you think you can,you can.If you think you can not,You are right.";
	private static final String UNICODE_FORMAT = "UTF8";
	
	/**
	 * A (transparent) specification of the key material that constitutes a cryptographic key.
	**/
	private KeySpec keySpec;
	
	/**
	 * It represents a factory for secret keys used to convert keys into key specifications and vice versa.
	 * It operates only on secret (symmetric) keys.
	**/
	private SecretKeyFactory keyFactory;
	
	/**
	 * It provides the functionality of a cryptographic cipher for encryption and decryption.
	**/
	private Cipher cipher;

	/**
	 * Make a String from array of bytes
	**/
	/**
	 * @param bytes
	 * @return String
	**/
	private static String bytes2String(byte[] bytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			stringBuffer.append((char) bytes[i]);
		}
		return stringBuffer.toString();
	}

	/**
	 * @param encryptionScheme
	 * @throws EncryptionException
	**/
	public StringEncrypter(String encryptionScheme) throws EncryptionException {
		this(encryptionScheme, DEFAULT_ENCRYPTION_KEY);//Calls overloaded constructor
	}

	/**
	 * @param encryptionScheme
	 * @param encryptionKey
	 * @throws EncryptionException
	**/
	public StringEncrypter(String encryptionScheme, String encryptionKey) throws EncryptionException {
		if (encryptionKey == null) {//If key for encryption is not defined
			throw new IllegalArgumentException("encryption key was null");
		}
		
		if (encryptionKey.trim().length() < 24) {//If key for encryption is having less than 24 characters
			throw new IllegalArgumentException("encryption key was less than 24 characters");
		}

		try {
			byte[] keyAsBytes = encryptionKey.getBytes(UNICODE_FORMAT);

			if (encryptionScheme.equals(DESEDE_ENCRYPTION_SCHEME)) {
				keySpec = new DESedeKeySpec(keyAsBytes);//It specifies a DES-EDE ("triple-DES") key
			} else if (encryptionScheme.equals(DES_ENCRYPTION_SCHEME)) {
				keySpec = new DESKeySpec(keyAsBytes);//It specifies a DES key
			} else {
				throw new IllegalArgumentException("Encryption scheme not supported: " + encryptionScheme);
			}
			keyFactory = SecretKeyFactory.getInstance(encryptionScheme);
			cipher = Cipher.getInstance(encryptionScheme);
		} catch (InvalidKeyException e) {
			throw new EncryptionException(e);
		} catch (UnsupportedEncodingException e) {
			throw new EncryptionException(e);
		} catch (NoSuchAlgorithmException e) {
			throw new EncryptionException(e);
		} catch (NoSuchPaddingException e) {
			throw new EncryptionException(e);
		} //end of try-catch block
	}

	/**
	 * Decrypts the encrypted string
	**/
	/**
	 * @param encryptedString
	 * @return String
	 * @throws EncryptionException
	**/
	public String decrypt(String encryptedString) throws EncryptionException {
		if (encryptedString == null || encryptedString.trim().length() <= 0)
			throw new IllegalArgumentException("encrypted string was null or empty");
		try {
			SecretKey key = keyFactory.generateSecret(keySpec);//Generates a key
			cipher.init(Cipher.DECRYPT_MODE, key);//Initializes the cipher with a key
			
			/**
			 * BASE64Decoder: This class is used to decode data in Base64 format as described in RFC 1521
			 * RFC (Request For Comments) describing methods, behaviors, research, or innovations applicable to the 
			 * working of the Internet and Internet-connected systems
			**/
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] cleartext = base64decoder.decodeBuffer(encryptedString);
			
			//Encrypts or decrypts data in a single-part operation, or finishes a multiple-part operation
			byte[] ciphertext = cipher.doFinal(cleartext);
			return bytes2String(ciphertext);//Converts array of bytes in a String
		} catch (Exception e) {
			throw new EncryptionException(e);
		} //end of try-catch block
	}

	/**
	 * Encrypts the given String
	**/
	/**
	 * @param unencryptedString
	 * @return
	 * @throws EncryptionException
	**/
	public String encrypt(String unencryptedString) throws EncryptionException {
		if (unencryptedString == null || unencryptedString.trim().length() == 0)
			throw new IllegalArgumentException("unencrypted string was null or empty");
		try {
			SecretKey key = keyFactory.generateSecret(keySpec);//Generates a key
			cipher.init(Cipher.ENCRYPT_MODE, key);//Initializes the cipher with a key
			byte[] cleartext = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] ciphertext = cipher.doFinal(cleartext);

			/**
			 * BASE64Decoder: This class is used to encode data in Base64 format as described in RFC 1521
			 * RFC (Request For Comments) describing methods, behaviors, research, or innovations applicable to the 
			 * working of the Internet and Internet-connected systems
			**/
			BASE64Encoder base64encoder = new BASE64Encoder();
			return base64encoder.encode(ciphertext);//Returns encoded string
		} catch (Exception e) {
			throw new EncryptionException(e);
		} //end of try-catch block
	}
}