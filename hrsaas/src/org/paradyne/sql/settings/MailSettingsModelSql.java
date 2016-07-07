package org.paradyne.sql.settings;

import org.paradyne.lib.SqlBase;

/**
 * @author Ayyappa
 *
 */
public class MailSettingsModelSql extends SqlBase {
	public String getQuery(int code) {

		switch (code) {
		case 1:
			return " INSERT INTO HRMS_SETTINGS_MAILBOX(MAILBOX_SERVER,MAILBOX_PROTOCOL,MAILBOX_PORT,MAILBOX_USERID,MAILBOX_PASSW,MAILBOX_COMP,MAILBOX_AUTH_CHK,MAILBOX_POP_BEFORE_SMTP,MAILBOX_FLAG) "
					+ " VALUES(?,?,?,?,?,1,?,?,?)";

		case 2:
			return " UPDATE HRMS_SETTINGS_MAILBOX SET MAILBOX_SERVER = ?,MAILBOX_PROTOCOL = ?,MAILBOX_PORT = ?,MAILBOX_USERID = ?,MAILBOX_PASSW = ?,MAILBOX_AUTH_CHK = ?,MAILBOX_POP_BEFORE_SMTP=? WHERE MAILBOX_FLAG = ?";

		case 3:
			return " INSERT INTO HRMS_SETTINGS_SYSMAILID(SYSMAIL_CODE,SYSMAIL_EMAIL_ID,SYSMAIL_EMAIL_PASS,SYSMAIL_SERVER_CODE) "
					+ " VALUES(?,?,?,?)";

		case 4:
			return " UPDATE HRMS_SETTINGS_SYSMAILID SET SYSMAIL_EMAIL_ID = ?, SYSMAIL_EMAIL_PASS = ? "
					+ " WHERE SYSMAIL_CODE = ? AND SYSMAIL_SERVER_CODE=? ";

		case 5:
			return " SELECT SYSMAIL_CODE,SYSMAIL_EMAIL_ID FROM HRMS_SETTINGS_SYSMAILID ";
			
		case 6:
			return " SELECT SYSMAIL_CODE,SYSMAIL_EMAIL_ID FROM HRMS_SETTINGS_SYSMAILID "
			+" WHERE SYSMAIL_SERVER_CODE=? order by SYSMAIL_CODE";

			 

		default:
			return " HI";
		}
	}
}
