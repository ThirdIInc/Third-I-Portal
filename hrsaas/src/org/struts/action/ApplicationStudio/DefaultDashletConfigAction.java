/**
 * 
 */
package org.struts.action.ApplicationStudio;

import java.io.File;
import java.util.Vector;
import org.paradyne.bean.ApplicationStudio.DefaultDashletConfig;
import org.struts.lib.ParaActionSupport;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;

/**
 * @author Reeba_Joseph/Pankaj_Jain
 *
 */
public class DefaultDashletConfigAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);
	
	DefaultDashletConfig dashletConfig = null;
	String poolName="";
	String fileName="";
	
	/**
	 * @return the dashletConfig
	 */
	public DefaultDashletConfig getDashletConfig() {
		return dashletConfig;
	}

	/**
	 * @param dashletConfig the dashletConfig to set
	 */
	public void setDashletConfig(DefaultDashletConfig dashletConfig) {
		this.dashletConfig = dashletConfig;
	}

	@Override
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		dashletConfig = new DefaultDashletConfig();
		dashletConfig.setMenuCode(796);
		poolName = String.valueOf(session.getAttribute("session_pool"));
	}

	public Object getModel() {
		return dashletConfig;
	}
	
	/**
	 * Method called on load
	 */
	public String input() throws Exception {
		try{
			getNavigationPanel(1); //mode 1 buttons
			dashletConfig.setEnableAll("Y");
		}//end try block
		catch(Exception e){
			//logger.error("Error in input method : "+e);
			e.printStackTrace();
		}// end catch block
		dashletConfig.setOnLoadFlag(true);
		return INPUT;
	}//end of input method
	
	/**
	 * 
	 * @return String
	 */
	public String getPages() {
		DefaultDashletConfigModel model = new DefaultDashletConfigModel();
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
	}//end of getPages method
	
	/**
	 * Method to set the home menus on the form
	 * @return String
	 */
	public String setMenuNames() {
		try {
			getNavigationPanel(2);// mode 2 buttons
			DefaultDashletConfigModel model = new DefaultDashletConfigModel();
			model.initiate(context, session);
			//call model for retrieving menu list
			String[][] menuNames = model.getMenuList(dashletConfig);
			request.setAttribute("menuNames", menuNames);
			//logger.info("Setting menu list ===== "+menuNames.length);
			model.terminate();
		}//end try block
		catch (Exception e) {
			//logger.error("Error in setMenuNames method : "+e);
			e.printStackTrace();
		}// end catch block
		return SUCCESS;
	}//end of setMenuNames method
	
	/**
	 * Creation of row and column matrix as per the no.s selected
	 * @return String
	 */
	public String matrixOnGo() {
		try {
			getNavigationPanel(2); // mode 2 buttons
			dashletConfig.setColShowFlag("true");
			String gridRows = dashletConfig.getGridRows();
			String gridCols = dashletConfig.getGridCols();
			setMenuNames();//display the menus
			String []dashCode = request.getParameterValues("dashCode");//dashlet code
			String []dashName = request.getParameterValues("dashName");//dashlet name
			int col = Integer.parseInt(dashletConfig.getGridCols());// total cols
			int rowCount=1;
			
			/*
			 * code to display the rows to set 
			 * no of dashlet for selected home menu
			 * if rows = 2 and cols = 2
			 * then 2*2=4 dashlets can be selected
			 * Also if dashlets are previously selected and 
			 * row/col count is increased
			 * previously selected daslets are restored
			 */
			if(dashCode != null && dashCode.length > 0)
			{
				String[][] dashletObj = new String[dashCode.length][5];
				for (int i = 0; i < dashletObj.length; i++) {//loop x
					//logger.info("DashCode["+i+"] : "+dashCode[i]);
					//logger.info("dashName["+i+"] : "+dashName[i]);
					dashletObj[i][0] = dashCode[i];
					dashletObj[i][1] = dashName[i];
					dashletObj[i][2] = "";
					if(col == 2)
					{
						if(i % 2 == 0)
						{
							dashletObj[i][3] = ""+rowCount;
							dashletObj[i][4] = "1";
						}
						else
						{
							dashletObj[i][3] = ""+rowCount;
							dashletObj[i][4] = "2";
							rowCount++;
						}
					}
					else
					{
						dashletObj[i][3] = ""+rowCount;
						dashletObj[i][4] = "1";
						rowCount++;
					}
				}//end loop x
				request.setAttribute("dashletObj", dashletObj);
			}//end of if
			int total = 1;
			try {
				total = Integer.parseInt(gridRows) * Integer.parseInt(gridCols);
			}//end try block
			catch(Exception e){
				//logger.error("Error in calculating total matrixOnGo method : "+e);
			}// end catch block
			
			//creation of matrix
			String matrix[][] = new String[total][3];
			int colCounter = 1, rowCounter = 1;
			for (int i = 0; i < total; i++) {//loop x
				matrix[i][0] = "" + rowCounter;
				matrix[i][1] = "" + colCounter;
				matrix[i][2] = "";
				if (colCounter == Integer.parseInt(gridCols)) {
					colCounter = 0;
					rowCounter++;
				}//end of if
				colCounter++;
			}//end of loop x
			request.setAttribute("matrix", matrix);
		} //end try block
		catch (Exception e) {
			//logger.error("Error in matrixOnGo method : "+e);
			e.printStackTrace();
		}// end catch block
		return SUCCESS;
	}//end of matrixOnGo method
	
	/**
	 * Retrieving the list of all dashlets available for the selected profile. 
	 * @return String
	 */
	public String dashletList() {
		//logger.info("Profile code ------ "+dashletConfig.getProfileID());
		if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
			poolName = "\\" + poolName;
		}// end of if
		
		String configPath = getText("data_path") + "\\dashlet\\" + poolName+"\\profile\\"
		+dashletConfig.getProfileID()+"\\profileDashletList.xml";
		Object[][] result = null;
		DefaultDashletConfigModel model = new DefaultDashletConfigModel();
		model.initiate(context, session);
		File file = new File(configPath);
		if (!file.exists()) {// if file does not exist
			//logger.info("File not exists");
			
			configPath = getText("data_path") + "\\dashlet\\default\\profile\\" 
			+dashletConfig.getProfileID()+"\\profileDashletList.xml";
			//logger.info("Config file path---------------- "+configPath);
			file = new File(configPath);
			if(!file.exists()){
				configPath = getText("data_path") + "\\dashlet\\default\\profile\\2\\" 
				+"profileDashletList.xml";
			}// end inner if -- file not exists
		}//end if
		try {
			//logger.info("Config file path---------------- "+configPath);
			result = model.setProfileDashlets(new XMLReaderWriter()
					.parse(new File(configPath)), dashletConfig);
		} //end try block
		catch (Exception e) {
			//logger.error("Error in setting dashlets  : "+e);
			e.printStackTrace();
		}//end catch block
		String[] dashCodeObj = request.getParameterValues("dashCode");
		int count = 0;
		for (int i = 0; i < dashCodeObj.length; i++) {
			if(!dashCodeObj[i].equals(""))
				count++;
		}//loop for count
		String dashCode[] = new String[count];
		for (int i = 0,z=0; i < dashCodeObj.length; i++) {
			if(!dashCodeObj[i].equals(""))
				dashCode[z++] = dashCodeObj[i];
		}//end loop
			
		//sorting dashCode - bubble sort
		for (int i = 0; i < dashCode.length; i++) {//loop i
			for (int j = i+1; j < dashCode.length; j++) {//loop j
				if(Integer.parseInt(dashCode[i]) > Integer.parseInt(dashCode[j]))
				{
					String temp = dashCode[i];
					dashCode[i] = dashCode[j];
					dashCode[j] = temp;
				}//end if
			}//end loop j
		}//end loop i
		
		Object finalObj[][] = new Object[result.length - count][2];
		int k=0,j=0;
		if(dashCode != null && dashCode.length > 0)
		{// if dashCode not null
			for (int i = 0; i < result.length; i++) {//loop x
				if(k < dashCode.length &&
						(dashCode[k].equals(String.valueOf(result[i][0])) || dashCode[k].equals("")))
				{// if k
						k++;
						continue;
				}//end if k
				else  
					finalObj[j++] = result[i];
			}// end of loop x
		}// end if dashCode not null
		else
			finalObj = result;
			
			
		request.setAttribute("listObj", finalObj);
		model.terminate();
		return "dashletList";
	} //end of dashletList method
	
	public String save() throws Exception {
		try {
			getNavigationPanel(2);
			String[] dashCode = request.getParameterValues("dashCode");
			String[] rows = request.getParameterValues("row");
			String[] cols = request.getParameterValues("col");
			if(rows != null && rows.length > 0)
			{
				if (!(poolName.equals("") || poolName == null || poolName
						.equals(null))) {
					poolName = "\\" + poolName;
				}// end of if
				
				//according to no of rows and columns 
				//XML will be saved
				DefaultDashletConfigModel model = new DefaultDashletConfigModel();
				model.initiate(context, session);
				model.saveDashletConfig(dashletConfig, dashCode, rows, cols);
				model.terminate();
			}
			getDashletProfileConfig();
			if(dashletConfig.getSaveChkFlag().equals(""))
				addActionMessage(getMessage("save"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * Search Profile
	 * 
	 * @return f9page
	 * @throws Exception
	 */
	public String f9profile() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT ALONG WITH PROFILES
		 */

		String query = " SELECT PROFILE_NAME,PROFILE_CODE FROM HRMS_PROFILE_HDR ORDER BY PROFILE_CODE";

		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = {getMessage("profile")};

		/**
		 * DEFINE THE PERCENT WIDTH OF EACH COLUMN
		 */
		String[] headerWidth = { "100" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */

		String[] fieldNames = { "profileName", "profileID" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "DefaultDashlet_onProfileSelect.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}
	
	/**
	 * Called after selecting any profile through f9
	 * @return String
	 */
	public String onProfileSelect()
	{
		try {
			dashletConfig.setHMenuCode("");
			dashletConfig.setMenuHome("");
			getDashletProfileConfig();
			dashletConfig.setDivFlag(true);
			dashletConfig.setOnLoadFlag(true);
		} //end try block
		catch (Exception e) {
			//logger.error("Error in onProfileSelect method: "+e);
			e.printStackTrace();
		}//end catch block
		return SUCCESS;
	}
	
	/**
	 * 
	 */
	public void getDashletProfileConfig()
	{
		DefaultDashletConfigModel model = new DefaultDashletConfigModel();
		dashletConfig.setActionMessage("");
		model.initiate(context, session);
		String menuCode = request.getParameter("menuId");
		String menuHome = request.getParameter("menuHome");
		if(request.getParameter("menuId")==(null) ||
				request.getParameter("menuId").equals(null))
			//if no menuId from JSP
			menuCode = dashletConfig.getHMenuCode();
		if(menuCode.equals(""))
		{
			try {
				String[][] menuNames = model.getMenuList(dashletConfig);
				menuCode = menuNames[0][0];//if no menuCode available, provide default code 
				menuHome = menuNames[0][1];
			} catch (Exception e) {
				addActionMessage("No menus available for selected profile");
				getNavigationPanel(2);
				return;
			}
		}
		dashletConfig.setHMenuCode(menuCode);
		dashletConfig.setMenuHome(menuHome);
		Vector v = model.getDashletConfig(menuCode,dashletConfig.getProfileID(),
				dashletConfig.getUserLoginCode(),request);
		Object[][] finalObj = (Object[][])v.get(0);
		dashletConfig.setGridRows(String.valueOf(v.get(1)));
		dashletConfig.setGridCols(String.valueOf(v.get(2)));
		model.terminate();
		if(finalObj==null || finalObj.length ==0)
		{
			dashletConfig.setWidthCol1("");
			dashletConfig.setWidthCol2("");
		}
		matrixOnGo();//call for matrix creation
		request.setAttribute("dashletObj",finalObj);
	}// end of getDashletProfileConfig method

}//end of class
