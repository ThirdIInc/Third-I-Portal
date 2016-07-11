package org.paradyne.lib;

/**
 * @author Sunil
 * @date 29 Feb 2008
**/

/**
 * This class encrypts the database attributes
**/

public class ConnectionEncripter {
	public static void main(String[] args) {
		String driver = "";
		String url = "";
		String user = "";
		String pwd ="";
		String user1 = "";
		String pwd1 ="";
		String user2 = "";
		try {
			driver = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).encrypt("oracle.jdbc.OracleDriver");
			url = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).encrypt("jdbc:oracle:thin:@192.168.1.144:1521:orcl11g");
			user = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).encrypt("hr_the3i");
			pwd = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).encrypt("hrsaas");
			user1 = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).decrypt("glqazo/pwAZ6GXTO1WoZ+RKAw/SPnTynquETQ9oOMlk=");
			user2 = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME).decrypt("uV/F7FNussnOMijKdwfnug==");
			pwd1 = new StringEncrypter(
					StringEncrypter.DESEDE_ENCRYPTION_SCHEME,
					StringEncrypter.DBPOOL_ENCRYPTION_KEY).encrypt("hr_demo");
			
			System.out.println("url....."+url);
			System.out.println("user...."+user);
			System.out.println("user1...."+user1);
			System.out.println("user1...."+user2);
		} catch (Exception e) {System.out.println("exception");}
	}
}