/**
 * Date : - 25/12/2008
 */
package org.paradyne.model.ApplicationStudio;

import org.paradyne.bean.ApplicationStudio.ThemeSettings;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;

/**
 * @author Pankaj_Jain Model class save the settings applied by the user and to
 *         read the settings and to display the settings when user browse though
 *         this form
 */

public class ThemeSettingsModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ThemeSettings.class);

	/**
	 * Method use to apply the themes and other font settings and to save them
	 * to database
	 * 
	 * @param bean :
	 *            to fetch selected user settings
	 * @param poolDir
	 */
	public void applyTheme(ThemeSettings bean, String poolDir) {
		/*
		 * session.setAttribute("FONTSIZE", bean.getFontSize());
		 * session.setAttribute("FONTFAMILY", bean.getSelFont());
		 * session.setAttribute("FONTCOLOR", bean.getFontColor());
		 */
		logger.info("bean.getFontSize()" + bean.getFontSize());

		/*
		 * save the user settings in to database
		 */
		String query = "DELETE FROM  HRMS_THEME  WHERE LOGIN_CODE="
				+ bean.getUserLoginCode();
		getSqlModel().singleExecute(query);
		String homeCode = String.valueOf(session.getAttribute("homeCode"));
		if(homeCode== null || homeCode.equals("null"))
			homeCode="";
		String insertQuery = "INSERT INTO HRMS_THEME (LOGIN_THEME,LOGIN_FONT,LOGIN_FONTSIZE,LOGIN_CODE,LOGIN_DEF_HOME_CODE) VALUES('"
				+ bean.getThemeName()
				+ "','"
				+ bean.getFontName()
				+ "',"
				+ bean.getFontSize() + "," + bean.getUserLoginCode() + ",'"+homeCode+"')";
		getSqlModel().singleExecute(insertQuery);
	} // end of method

	/**
	 * method use to fetch the user settings when user comes to this form
	 * 
	 * @param bean
	 * @return
	 */
	public Object[][] getTheme(ThemeSettings bean) {
		// TODO Auto-generated method stub
		String query = "SELECT LOGIN_THEME,LOGIN_FONT,LOGIN_FONTSIZE,NVL(MENU_LINK,'/common/HomePage_input.action'),LOGIN_DEF_HOME_CODE FROM HRMS_THEME "
				+ " LEFT JOIN HRMS_MENU ON (HRMS_MENU.MENU_CODE = HRMS_THEME.LOGIN_DEF_HOME_CODE)"
				+ " WHERE LOGIN_CODE= " + bean.getUserLoginCode();
		logger.info("query" + query);
		Object[][] obj = getSqlModel().getSingleResult(query);
		return obj;
	}

	/**
	 * method use to fetch all the properties key value pair according to the
	 * theme selected by user if no theme has been selected it returns default
	 * theme
	 * 
	 * @param themeName :
	 *            name of the theme
	 * @param path :
	 *            path where properties file is stored
	 * @return : Hash Map Object contains all the key, value pair
	 */
	public HashMap loadFormLabels(String themeName, String path) {
		HashMap hMap = new HashMap();
		Properties pDefault = new Properties();
		// file object to load that file
		File file;
		// InputStream for loading that file in property object
		FileInputStream fin;
		try {
			try {
				file = new File(path + "\\Themes\\" + themeName + ".properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pDefault.load(fin);
			} catch (Exception e) {
				// exception caught if file is not found
				pDefault = null;
				logger.error(themeName + ".properties File not found");
			}

			hMap.putAll(pDefault);
			return hMap;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			// release all resources
			return hMap;
		}
	} // end of method
}// end of class
