package org.paradyne.lib.sms;

import org.paradyne.lib.SqlModel;

public class SMSUtil {
	
	private String userName;
	private String password;
	private String URL;
		
	public void init(SqlModel SqlModel) {
		// get the encrypted information from properties file.
		// decrypt and set it in username, password and URL
	}
	
	public boolean send(String mobile, String message) {
		// append username, passsword, mobile and message to URL
		// and run the URL;
		
		// If message is successful
		return true;
		// if message is anything else
		//return false;
	}
	
	public void close() {
	
	}
}