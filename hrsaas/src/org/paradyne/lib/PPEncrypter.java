/**
 * 
 */
package org.paradyne.lib;

import java.util.StringTokenizer;

import org.paradyne.lib.StringEncrypter.EncryptionException;

/**
 * @author Lakkichand_Golegaonkar
 * @date 17-August-2009
 */
public class PPEncrypter {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(PPEncrypter.class);
/**
 * This method encrypt the string using ASCII codes and Application defined secure string encrypter
 * @param strToBeEncrypt
 * @return decryptedString
 * @ 
 */
	public static String encrypt(String strToBeEncrypt) {
		String encryptedString = "";
		String localEncryptedString = "";

		try {
			encryptedString = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.ONLINE_EMAIL_ENCRYPTION_KEY).encrypt(strToBeEncrypt);
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
		System.out.println("encryptedString  "+encryptedString);
		for (int i = 0; i < encryptedString.length(); i++) {

			localEncryptedString += (int) encryptedString.charAt(i) + "-";

		}
		localEncryptedString = localEncryptedString.substring(0,
				localEncryptedString.length() - 1);
		logger.info(" encryptedString  :" + localEncryptedString);
		return localEncryptedString;
	}

	public static String decrypt(String strToBeDecrypt) {
		String decryptedString = "";
		String localDecryptedString="";
		StringTokenizer tokens=new StringTokenizer(strToBeDecrypt,"-");
		
		while (tokens.hasMoreElements()) {
			String token = tokens.nextToken();
			int tokenInt = Integer.parseInt(token);
			localDecryptedString+=(char)tokenInt;
		}
		System.out.println("localDecryptedString"+localDecryptedString);
		try {
			decryptedString = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.ONLINE_EMAIL_ENCRYPTION_KEY)
					.decrypt(localDecryptedString);
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
		logger.info(" decryptedString  :" + decryptedString);
		return decryptedString;
	}

	public static String encryptEmailUser(String strToBeEncrypt) {
		String encryptedString = "";
		String localEncryptedString = "";

		try {
			encryptedString = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.USER_EMAIL_ENCRYPTION_KEY).encrypt(strToBeEncrypt);
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
		System.out.println("encryptedString  "+encryptedString);
		for (int i = 0; i < encryptedString.length(); i++) {

			localEncryptedString += (int) encryptedString.charAt(i) + "-";

		}
		localEncryptedString = localEncryptedString.substring(0,
				localEncryptedString.length() - 1);
		logger.info(" encryptedString  :" + localEncryptedString);
		return localEncryptedString;
	}

	public static String decryptEmailUser(String strToBeDecrypt) {
		String decryptedString = "";
		String localDecryptedString="";
		StringTokenizer tokens=new StringTokenizer(strToBeDecrypt,"-");
		
		while (tokens.hasMoreElements()) {
			String token = tokens.nextToken();
			int tokenInt = Integer.parseInt(token);
			localDecryptedString+=(char)tokenInt;
		}
		System.out.println("localDecryptedString"+localDecryptedString);
		try {
			decryptedString = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.USER_EMAIL_ENCRYPTION_KEY)
					.decrypt(localDecryptedString);
		} catch (EncryptionException e) {
			e.printStackTrace();
		}
		logger.info(" decryptedString  :" + decryptedString);
		return decryptedString;
	}
	public static void main(String[] args) {

		decrypt(encrypt("LeaveAppl#123#1#A#34#1"));
		// encrypt(decrypt("pool_fort"));

	}
}
