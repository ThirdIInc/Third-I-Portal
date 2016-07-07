/**
 * 
 */
package org.paradyne.model.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.*;

import org.paradyne.lib.ModelBase;

/**
 * @author Pankaj_Jain
 * Model class to read and write the labels
 */
public class LabelManagementModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(LabelManagementModel.class);
	
	/**
	 * This method is defined to loadCommonLabels 
	 * from properties file
	 * @param path : specifies the property file path
	 */
	public void loadCommonLabels(String path) {
		String client = String.valueOf(session.getAttribute("session_pool"));
		try{
			/* if object is already set in context
			 * return; else set the object in context
			*/
			if (context.getAttribute("common" + client) != null)
				return;
		}catch(Exception e)
		{
			logger.error("Exception in loadCommonLabels "+e);
		}
		HashMap hMap = new HashMap();
		Properties pDefaultCom = new Properties();
		Properties pClientCom = new Properties();
		// file object to load that file
		File file;
		// InputStream for loading that file in property
		FileInputStream fin;
		try {
			try {
				file = new File(path + "/Labels/"
						+ session.getAttribute("session_pool")
						+ "/COMMON.properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pClientCom.load(fin);
			} catch (Exception e) {
				// exception caught if file is not found
				pClientCom = null;
				logger.error("Exception in innner try loadCommonLabels " + e);
			}
			try {
				file = new File(path + "/Labels/Default/COMMON.properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pDefaultCom.load(fin);
			} catch (Exception e) {
				// exception caught if file is not found
				pDefaultCom = null;
				logger.error("Exception in innertry loadCommonLabels " + e);
			}
			hMap.putAll(pDefaultCom);
			if (pClientCom != null)
				hMap.putAll(pClientCom);
			context.setAttribute("common" + client, hMap);
		} // Outer most try block end 
		catch (Exception e) {
			logger.error("Exception in outer most try loadCommonLabels " + e);
		} finally {
			// release all resources
			pDefaultCom = pClientCom = null;
			file = null;
			fin = null;
		}
	} // end of loadCommonLabels method
	
	/**
	 * This method is used to load the labels that are specific
	 * to form depending upon its menu code.
	 * @param menuCode : specifies the form for which labels have 
	 * to be loaded
	 * @param path : specifies the properties file path
	 * @return : Hash Map containing the labels id and their values.
	 */
	public HashMap loadFormLabels(int menuCode, String path) {
		HashMap hMap = new HashMap();
		Properties pDefault = new Properties();
		Properties pClient = new Properties();
		// file object to load that file
		File file;
		// InputStream for loading that file in property object
		FileInputStream fin;
		try {
			try {
				file = new File(path + "/Labels/"
						+ session.getAttribute("session_pool") + "/"
						+ menuCode + ".properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pClient.load(fin);
			} catch (Exception e) {
				// exception caught if file is not found
				pClient = null;
				logger.error(menuCode + ".properties File not found");
			}
			try {
				file = new File(path + "/Labels/Default/" + menuCode
						+ ".properties");
				fin = new FileInputStream(file);
				// loading file in property object
				pDefault.load(fin);
				logger.info(menuCode + ".properties File found");
			} catch (Exception e) {
				// exception caught if file is not found
				pDefault = null;
				logger.error(menuCode + ".properties File not found");
			}
			
			/* first load the default labels
			 * if client file exist replace the default 
			 * labels by the labels in client's file
			 */
			hMap.putAll(pDefault);
			if (pClient != null)
				hMap.putAll(pClient);
			return hMap;
		} // outer try block end 
		catch (Exception e) {
			return null;
		} finally {
			// release all resources
			pDefault = pClient = null;
			file = null;
			fin = null;
		}
	} // end of loadFormLabels method

	
	/**
	 * This method is used to store the value of label
	 * changed by the user. If file already exist it simply 
	 * stores the value if file does not exist it creates 
	 * the properties file
	 * @param labelId : id of label to be changed
	 * @param labelVal : new value given to label
	 * @param menuCode : menu code for the screen on which it
	 * has to be changed
	 * @param path : specifies where properties file has to be 
	 * stored
	 * @return : true if change value is valid else false
	 */
	public boolean changeLabel(String labelId, String labelVal,
			String menuCode, String path) {
		Properties pro = new Properties();
		String client = String.valueOf(session.getAttribute("session_pool"));
		File f;
		FileInputStream in;
		try {
			try {
				HashMap hMap = (HashMap) context
						.getAttribute("common" + client);
				
				/*
				 * if the label is found as common label
				 */
				if (hMap.get(labelId) != null && !hMap.get(labelId).equals("")) {
					f = new File(path + "/Labels/" + client
							+ "/COMMON.properties");
					if(f.exists())
					{
						in = new FileInputStream(f);
						pro.load(in);
					}
					/*
					 * method call to validate duplicate labels
					 */
					if(!checkForDuplicateLabel(labelId, labelVal, pro, path,"1",menuCode))
						return false;
					// if file does not exists create the file
					if (!f.exists())
					{	
						new File(path + "/Labels/" + client).mkdirs();
						pro.store(new FileOutputStream(path + "/Labels/" + client
								+ "/COMMON.properties"), null);
						in = new FileInputStream(f);
						pro.load(in);
					}
					hMap.put(labelId, labelVal);
					pro.setProperty(labelId, labelVal);
					pro.store(new FileOutputStream(path + "/Labels/" + client
							+ "/COMMON.properties"), null);
					
					// update the context object
					context.setAttribute("common"
							+ session.getAttribute("session_pool"), hMap);
				} 
				/*
				 * if label is found to be form specific
				 */
				else {
					f = new File(path + "/Labels/" + client + "/" + menuCode
							+ ".properties");
					if(f.exists())
					{
						in = new FileInputStream(f);
						pro.load(in);
					}
					//check for duplicate labels
					if(!checkForDuplicateLabel(labelId, labelVal, pro, path,"2", menuCode))
						return false;
					// if file does not exists create the file 
					if (!f.exists()) {
						new File(path + "/Labels/" + client).mkdirs();
						pro.store(new FileOutputStream(path + "/Labels/" + client
								+ "/" + menuCode + ".properties"), null);
						in = new FileInputStream(f);
						pro.load(in);
					}
					pro.setProperty(labelId, labelVal);
					pro.store(new FileOutputStream(path + "/Labels/" + client
							+ "/" + menuCode + ".properties"), null);
				}
			} catch (Exception e) {
				logger.error("Exception in model in change label " + e);
			}
		} catch (Exception e) {
			logger.error("Exception in model in change label " + e);
		} finally {
			// release all resources
			pro = null;
			in = null;
			f = null;
		}
		return true;
	} // end of change label method

	/**
	 * 
	 * @param labelId : label id for which default value has to be retrieved
	 * @param menuCode : menu code 
	 * @param path : describes the file from which default value has to be read
	 * @return : returns the default value for selected label
	 */
	public String restoreLabel(String labelId, String menuCode, String path) {
		Properties p = new Properties();
		FileInputStream fin;
		File f;
		String defaultVal = "";
		String filePath = "";
		String client = String.valueOf(session.getAttribute("session_pool"));
		try {
			HashMap hMap = (HashMap) context.getAttribute("common" + client);
			
			/*
			 * if the label id is found in common object
			 * update the object and set it in context
			 * and if client's COMMON file exists overwrite that
			 * property value with default 
			 */
			if (hMap.get(labelId) != null && !hMap.get(labelId).equals("")
					&& !hMap.get(labelId).equals("null")
					&& !hMap.get(labelId).equals(null)) {
				f = new File(path + "/Labels/Default/COMMON.properties");
				fin = new FileInputStream(f);
				p = new Properties();
				p.load(fin);
				defaultVal = p.getProperty(labelId);
				if(!checkDuplicateRestore(client, path, menuCode, labelId, defaultVal))
					return "";
				hMap.put(labelId, defaultVal);
				context.setAttribute("common"
						+ session.getAttribute("session_pool"), hMap);
				f = new File(path + "/Labels/" + client
						+ "/COMMON.properties");
				if (f.exists())
				{
					filePath = path + "/Labels/" + client
							+ "/COMMON.properties";
					fin = new FileInputStream(new File(filePath));
					p = new Properties();
					p.load(fin);
					p.setProperty(labelId, defaultVal);
					p.store(new FileOutputStream(filePath), null);
				}
			} // end if
			
			/*
			 * if label does not exist in common
			 * update the client file if exists 
			 */
			else {
				f = new File(path + "/Labels/Default/" + menuCode
						+ ".properties");
				fin = new FileInputStream(f);
				p = new Properties();
				p.load(fin);
				defaultVal = p.getProperty(labelId);
				if(!checkDuplicateRestore(client, path, menuCode, labelId, defaultVal))
					return "";
				f = new File(path + "/Labels/" + client + "/" + menuCode
						+ ".properties");
				
				/*if file exist overwrite the property in that file
				 * with default value
				 */ 
				if (f.exists())
				{
					filePath = path + "/Labels/" + client + "/" + menuCode
							+ ".properties";
					fin = new FileInputStream(new File(filePath));
					p = new Properties();
					p.load(fin);
					p.setProperty(labelId, defaultVal);
					p.store(new FileOutputStream(filePath), null);
				}
			} // End else
			
			return defaultVal;
		} // outer try block end
		catch (Exception e) {
			logger.error("Exception in model in restore label " + e);
			e.printStackTrace();
			return "";
		} finally {
			// release all the resources
			p = null;
			f = null;
			fin = null;
		}
	} // End of method
	
	
	/**
	 * Method use to validate duplicate labels
	 * for different keys
	 * @param labelKey : current key to be changed
	 * @param labelVal : current label value
	 * @param pro : properties object in which current key is present
	 * @param path : path of properties file
	 * @param flag :  flag defining type of label whether specific or common
	 * @param menuCode : 
	 * @return : false if duplicate else return true
	 */
	public boolean checkForDuplicateLabel(String labelKey, String labelVal, Properties pro, 
			String path, String flag, String menuCode)
	{
		try {
			Properties p = null;
			File f;
			FileInputStream fin;
			String client = (String)session.getAttribute("session_pool");
			if(flag.equals("1"))
				flag = menuCode;
			else
				flag = "COMMON";
			
			/* file array containing
			 * the files name to be checked 
			 * for label values
			 */ 
			String filePath[][]=new String[4][2];
			filePath[0][0] = "pro"; // common labels 
			filePath[0][1] = client;
			filePath[1][0] = path+"/Labels/"+client+"/"+flag+".properties"; // corresponding client file
			filePath[1][1] = client;
			filePath[2][0] = path+"/Labels/Default/COMMON.properties"; // default common file
			filePath[2][1] = "Default";
			filePath[3][0] = path+"/Labels/Default/"+menuCode+".properties"; // default menucode file
			filePath[3][1] = "Default";
			
			/*
			 * for loop checks in all the properties file
			 */
			String defValue = "";
			String defKey = "";
			
			/*
			 * loop that is use to get the default value 
			 * for this label
			 */
			for (int i = 2; i < filePath.length; i++) {
				f = new File(filePath[i][0]);
				if(f.exists())
				{
					p = new Properties();
					fin = new FileInputStream(f);
					p.load(fin);
				}
				for (Iterator iterator = p.keySet().iterator(); iterator.hasNext();) {
					String key = (String) iterator.next();
					String value = p.getProperty(key);
					if(labelVal.trim().equalsIgnoreCase(value.trim()))
					{
						defKey = key;
						defValue = value; // break when label is found
						break;
					}
				}
			} // end for loop
			String chkFlag = "";
			
			/*
			 * for loop that iterates to all the files
			 * for checking duplicate labels
			 */
			for (int i = 0; i < filePath.length; i++) {
				f = new File(filePath[i][0]);
				if(f.exists())
				{
					p = new Properties();
					fin = new FileInputStream(f);
					p.load(fin);
				}
				else
					p = null;
				if(i==0)
					p=pro;
				if(p != null && p.size() > 0)
				{
					/**
					 * iterates over all the properties for
					 * current file in outer loop
					 */
					for (Iterator iterator = p.keySet().iterator(); iterator.hasNext();) {
						String key = (String) iterator.next();
						String value = (String) p.getProperty(key);
						
						/* if key does not matches with 
						 * current key to be changed 
						 * if block1
						 */ 
						if(chkFlag.equals(""))
						{
							if(key.equals(defKey))
								if(!defValue.equalsIgnoreCase(value))
									chkFlag = "true";
								else
									chkFlag = "false";
						}
						if(i > 1 && !chkFlag.equals(""))
							return Boolean.valueOf(chkFlag);
						if (!key.equals(labelKey)) {
							// if duplicate value is found if block2 
							if (labelVal.equalsIgnoreCase(value)) {
								// if block3
								if (p.getProperty(key) != null
										&& !p.getProperty(key).equals(null)){
									if(labelVal.equalsIgnoreCase(p.getProperty(key))) 
										return false;
								} // end if block3
							}
							// end if block2
						} // end if block1
					} // end inner for loop
					
				} // end outer if
			} // end outer for loop
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	} // End of method
	
	
	/**
	 * method that check for the default value
	 * if the default value of the label is assigned to
	 * other labels it return false
	 * @param client
	 * @param path
	 * @param menuCode
	 * @param labelKey
	 * @param defLabelVal
	 * @return if label exists with same value 
	 * it returns false else true
	 */
	public boolean checkDuplicateRestore(String client,String path, String menuCode, 
			String labelKey, String defLabelVal)
	{
		String filePath[][]=new String[2][1];
		
		// files to check for duplicate value while restoring
		filePath[0][0] = path+"/Labels/"+client+"/"+menuCode+".properties";
		filePath[1][0] = path+"/Labels/"+client+"/COMMON.properties";
		Properties p = null;
		File f = null;
		FileInputStream fin = null;
		f = new File(path+"/Labels/"+client+"/"+menuCode+".properties");
		try{
			if(f.exists())
			{
				p = new Properties();
				fin = new FileInputStream(f);
				p.load(fin);
			}
		}catch(Exception e)
		{
			logger.info("File Not Found---------- "+e);
			p = null;
		}
		//if same value for other label is found 
		// return false
		if(p != null && p.size() > 0)
		{
			for (Iterator iterator = p.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				String value = (String) p.getProperty(key);
				if(!key.equals(labelKey))
					if(defLabelVal.equalsIgnoreCase(value))
						return false;
			}
		}
		
		f = new File(path+"/Labels/"+client+"/COMMON.properties");
		try{
			if(f.exists())
			{
				p = new Properties();
				fin = new FileInputStream(f);
				p.load(fin);
			}
		}catch(Exception e)
		{
			logger.info("File Not Found---------- "+e);
			p = null;
		}
		
		// if that default value is found in common label
		// return false
		if(p != null && p.size() > 0)
		{
			for (Iterator iterator = p.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				String value = (String) p.getProperty(key);
				if(!key.equals(labelKey))
					if(defLabelVal.equalsIgnoreCase(value))
						return false;
			}
		}
		
		//if value can be restored
		//and default value is not given to any other
		//label return true
		return true;
	}

} // End of model
