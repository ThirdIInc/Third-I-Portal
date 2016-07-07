/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.io.File;
import java.util.Vector;
import org.paradyne.bean.ApplicationStudio.UserDashletConfig;
import org.struts.lib.ParaActionSupport;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.UserDashletConfigModel;

/**
 * @author Reeba_Joseph
 * 
 */
public class UserDashletConfigAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	UserDashletConfig dashletConfig = null;
	String poolName = "";
	String fileName = "";

	/**
	 * @return the dashletConfig
	 */
	public UserDashletConfig getDashletConfig() {
		return dashletConfig;
	}

	/**
	 * @param dashletConfig
	 *            the dashletConfig to set
	 */
	public void setDashletConfig(UserDashletConfig dashletConfig) {
		this.dashletConfig = dashletConfig;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		dashletConfig = new UserDashletConfig();
		dashletConfig.setMenuCode(797);
		poolName = String.valueOf(session.getAttribute("session_pool"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return dashletConfig;
	}

	/**
	 * 
	 */
	public String input() throws Exception {
		// TODO Auto-generated method stub
		try {
			getNavigationPanel(1);
			onProfileSelect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dashletConfig.setDivFlag(true);
		dashletConfig.setOnLoadFlag(true);

		return INPUT;
	}

	/**
	 * 
	 * @return
	 */
	public String getPages() {
		UserDashletConfigModel model = new UserDashletConfigModel();
		model.initiate(context, session);
		try {
			if(!dashletConfig.getSaveChkFlag().equals(""))
				save();
		} catch (Exception e) {
			// TODO: handle exception
		}
		dashletConfig.setSaveChkFlag("");
		getDashletProfileConfig();
		dashletConfig.setDivFlag(true);
		dashletConfig.setOnLoadFlag(true);
		model.terminate();
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String setMenuNames() {
		try {
			getNavigationPanel(2);
			UserDashletConfigModel model = new UserDashletConfigModel();
			model.initiate(context, session);
			// call model for retrieving menu list
			String[][] menuNames = model.getMenuList(dashletConfig);
			request.setAttribute("menuNames", menuNames);
			logger.info("Setting menu list ===== " + menuNames.length);
			model.terminate();
		}// end try block
		catch (Exception e) {
			logger.error("Error in setMenuNames method : " + e);
			e.printStackTrace();
		}// end catch block
		return SUCCESS;
	}// end of setMenuNames method

	/**
	 * Creation of row and column matrix as per the no.s selected
	 * 
	 * @return String
	 */
	public String matrixOnGo() {
		try {
			getNavigationPanel(2); // mode 2 buttons
			dashletConfig.setColShowFlag("true");
			String gridRows = dashletConfig.getGridRows();
			String gridCols = dashletConfig.getGridCols();
			setMenuNames();// display the menus
			String[] dashCode = request.getParameterValues("dashCode");// dashlet
																		// code
			String[] dashName = request.getParameterValues("dashName");// dashlet
																		// name
			int row = Integer.parseInt(dashletConfig.getGridRows());// total
																	// rows
			int col = Integer.parseInt(dashletConfig.getGridCols());// total
																	// cols
			int rowCount = 1;
			if (dashCode != null && dashCode.length > 0) {
				String[][] dashletObj = new String[dashCode.length][5];
				for (int i = 0; i < dashletObj.length; i++) {// loop x
					logger.info("DashCode[" + i + "] : " + dashCode[i]);
					logger.info("dashName[" + i + "] : " + dashName[i]);
					dashletObj[i][0] = dashCode[i];
					dashletObj[i][1] = dashName[i];
					dashletObj[i][2] = "";
					if (col == 2) {
						if (i % 2 == 0) {
							dashletObj[i][3] = "" + rowCount;
							dashletObj[i][4] = "1";
						} else {
							dashletObj[i][3] = "" + rowCount;
							dashletObj[i][4] = "2";
							rowCount++;
						}
					} else {
						dashletObj[i][3] = "" + rowCount;
						dashletObj[i][4] = "1";
						rowCount++;
					}
				}// end loop x
				request.setAttribute("dashletObj", dashletObj);
			}// end of if
			int total = 1;
			try {
				total = Integer.parseInt(gridRows) * Integer.parseInt(gridCols);
			}// end try block
			catch (Exception e) {
				logger.error("Error in calculating total matrixOnGo method : "
						+ e);
			}// end catch block

			// creation of matrix
			String matrix[][] = new String[total][3];
			int colCounter = 1, rowCounter = 1;
			for (int i = 0; i < total; i++) {// loop x
				matrix[i][0] = "" + rowCounter;
				matrix[i][1] = "" + colCounter;
				matrix[i][2] = "";
				if (colCounter == Integer.parseInt(gridCols)) {
					colCounter = 0;
					rowCounter++;
				}// end of if
				colCounter++;
			}// end of loop x
			request.setAttribute("matrix", matrix);
		} // end try block
		catch (Exception e) {
			logger.error("Error in matrixOnGo method : " + e);
			e.printStackTrace();
		}// end catch block
		return SUCCESS;
	}// end of matrixOnGo method

	/**
	 * Retrieving the list of all dashlets available for the selected profile.
	 * 
	 * @return String
	 */
	public String dashletList() {
		logger.info("Profile code ------ " + dashletConfig.getProfileID());
		if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
			poolName = "\\" + poolName;
		}// end of if

		String configPath = getText("data_path") + "\\dashlet\\" + poolName
				+ "\\profile\\" + dashletConfig.getUserProfileId()
				+ "\\profileDashletList.xml";
		Object[][] result = null;
		UserDashletConfigModel model = new UserDashletConfigModel();
		model.initiate(context, session);
		File file = new File(configPath);
		if (!file.exists()) {
			logger.info("File exists");

			configPath = getText("data_path") + "\\dashlet\\default\\profile\\"
					+ dashletConfig.getUserProfileId()
					+ "\\profileDashletList.xml";
			logger.info("Config file path---------------- " + configPath);
			file = new File(configPath);
			if (!file.exists()) {
				configPath = getText("data_path") + "\\dashlet\\default\\profile\\2\\"
						+ "profileDashletList.xml";
			}// end inner if -- file not exists
		}// end if
		try {
			logger.info("Config file path---------------- " + configPath);
			result = model.setProfileDashlets(new XMLReaderWriter()
					.parse(new File(configPath)), dashletConfig);
		} // end try block
		catch (Exception e) {
			logger.error("Error in setting dashlets  : " + e);
			e.printStackTrace();
		}// end catch block
		String[] dashCodeObj = request.getParameterValues("dashCode");
		int count = 0;
		for (int i = 0; i < dashCodeObj.length; i++) {
			if (!dashCodeObj[i].equals(""))
				count++;
		}// loop for count
		String dashCode[] = new String[count];
		for (int i = 0, z = 0; i < dashCodeObj.length; i++) {
			if (!dashCodeObj[i].equals(""))
				dashCode[z++] = dashCodeObj[i];
		}// end loop

		// sorting dashCode - bubble sort
		for (int i = 0; i < dashCode.length; i++) {// loop i
			for (int j = i + 1; j < dashCode.length; j++) {// loop j
				if (Integer.parseInt(dashCode[i]) > Integer
						.parseInt(dashCode[j])) {
					String temp = dashCode[i];
					dashCode[i] = dashCode[j];
					dashCode[j] = temp;
				}// end if
			}// end loop j
		}// end loop i

		Object finalObj[][] = new Object[result.length - count][2];
		int k = 0, j = 0;
		if (dashCode != null && dashCode.length > 0) {// if dashCode not null
			for (int i = 0; i < result.length; i++) {// loop x
				if (k < dashCode.length
						&& (dashCode[k].equals(String.valueOf(result[i][0])) || dashCode[k]
								.equals(""))) {// if k
					k++;
					continue;
				}// end if k
				else
					finalObj[j++] = result[i];
			}// end of loop x
		}// end if dashCode not null
		else
			finalObj = result;

		request.setAttribute("listObj", finalObj);
		model.terminate();
		return "dashletList";
	} // end of dashletList method

	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String save() throws Exception {
		try {
			getNavigationPanel(2);
			String[] dashCode = request.getParameterValues("dashCode");
			String[] rows = request.getParameterValues("row");
			String[] cols = request.getParameterValues("col");
			if (rows != null && rows.length > 0) {
				if (!(poolName.equals("") || poolName == null || poolName
						.equals(null))) {
					poolName = "\\" + poolName;
				}// end of if

				UserDashletConfigModel model = new UserDashletConfigModel();
				model.initiate(context, session);
				model.saveDashletConfig(dashletConfig, dashCode, rows, cols);
				model.terminate();
			}
			getDashletProfileConfig();
			if(dashletConfig.getSaveChkFlag().equals(""))
				addActionMessage("Record saved Successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String onProfileSelect() {
		try {
			dashletConfig.setHMenuCode("");
			dashletConfig.setMenuHome("");
			getDashletProfileConfig();
			dashletConfig.setDivFlag(true);
			dashletConfig.setOnLoadFlag(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 */
	public void getDashletProfileConfig() {
		try {
			UserDashletConfigModel model = new UserDashletConfigModel();
			model.initiate(context, session);
			String menuCode = request.getParameter("menuId");
			String menuHome = request.getParameter("menuHome");
			if (request.getParameter("menuId") == (null)
					|| request.getParameter("menuId").equals(null))
				//if no menuId from JSP
				menuCode = dashletConfig.getHMenuCode();
			if (menuCode.equals("")) {
				String[][] menuNames = model.getMenuList(dashletConfig);
				menuCode = menuNames[0][0];//if no menuCode available, provide default code 
				menuHome = menuNames[0][1];
			}
			dashletConfig.setHMenuCode(menuCode);
			dashletConfig.setMenuHome(menuHome);
			Vector v = model.getDashletConfig(menuCode, dashletConfig
					.getUserProfileId(), dashletConfig.getUserLoginCode(),
					request);
			Object[][] finalObj = (Object[][]) v.get(0);
			dashletConfig.setGridRows(String.valueOf(v.get(1)));
			dashletConfig.setGridCols(String.valueOf(v.get(2)));
			model.terminate();
			if (finalObj == null || finalObj.length == 0) {
				dashletConfig.setWidthCol1("");
				dashletConfig.setWidthCol2("");
				logger.info("finalObj Length " + finalObj.length);
			} else {
				logger.info("finalObj Length " + finalObj.length);
			}
			matrixOnGo();// call for matrix creation
			request.setAttribute("dashletObj", finalObj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of getDashletProfileConfig method

}// end of class
