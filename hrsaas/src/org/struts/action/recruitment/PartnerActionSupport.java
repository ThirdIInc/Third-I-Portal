package org.struts.action.recruitment;  

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.util.ServletContextAware;
import org.dom4j.Document;
import org.dom4j.Element;
import org.paradyne.bean.Recruitment.PartnerLogin;
import org.paradyne.lib.BeanBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.common.F9Model;
import org.paradyne.model.common.ReportingModel;
import org.paradyne.model.common.UserProfileModel;
import org.struts.lib.GloActionSupport;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;



/**
 * @author Sunil
 * @date 29 Nov 2008
 */

/**
 * Base Action class
 */

public abstract class PartnerActionSupport extends ActionSupport implements ModelDriven, Preparable, ServletRequestAware, ServletResponseAware, ServletContextAware {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(org.struts.action.recruitment.PartnerActionSupport.class);
	public ActionContext actionContext = ActionContext.getContext();
	public HttpServletRequest request;
	public HttpServletResponse response;
	public HttpSession session;
	public ServletContext context;
	String Actionmessage = "";


	public void setServletRequest(HttpServletRequest httpServletRequest) {
		this.request = httpServletRequest;
	}

	public void setServletResponse(HttpServletResponse httpServletResponse) {
		this.response = httpServletResponse;
	}

	public void setServletContext(ServletContext servletContext) {
		this.context = servletContext;
	}

	public ServletContext getServletContext() {
		return context;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
		this.session.setMaxInactiveInterval(1800);
	}
	
	public void prepare()  
	{
		try {
			setSession(request.getSession());
			prepare_local();
			PartnerLogin bean = null;
			bean = (PartnerLogin) request.getSession().getAttribute("loginBeanData");
			BeanBase applicationBean =  (BeanBase) getModel();
			System.out.println("bean.setEmpId() :" + bean.getEmpId());
			applicationBean.setUserEmpId(bean.getEmpId());
			System.out.println("@@@@@@@@@@@@ MENU CODE : "+applicationBean.getMenuCode());
			/** * Set lable properties Begins. */
			org.paradyne.model.common.LabelManagementModel model = new org.paradyne.model.common.LabelManagementModel();
			model.initiate(context, session);
			model.loadCommonLabels(getText("data_path"));
			if(applicationBean.getMenuCode() > 0) {
				java.util.HashMap formLabels = model.loadFormLabels(applicationBean.getMenuCode(), getText("data_path"));
				getRequest().setAttribute("formLabels", formLabels);
			}
			/** * Set lable properties Ends. */
			prepare_withLoginProfileDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Calls when page loads
	 */
	public void prepare_withLoginProfileDetails() throws Exception {}
	
	
	public String getprofileQuery(BeanBase bean) {
		String query = "";
		try {
			if (bean.isGeneralFlag()) {
				query += " WHERE HRMS_EMP_OFFC.EMP_ID=" + bean.getUserEmpId();
			} else {
				if (bean.getUserProfileCenters().length() > 0) {
					query += " WHERE HRMS_EMP_OFFC.EMP_CENTER IN ("
							+ bean.getUserProfileCenters() + ")";
				}
				if (bean.getUserProfileEmpType().length() > 0) {
					if (bean.getUserProfileCenters().length() > 0) {
						query += " AND ";
					} else {
						query += " WHERE ";
					}
					query += " HRMS_EMP_OFFC.EMP_TYPE IN ("
							+ bean.getUserProfileEmpType() + ")";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}

	/**
	 * Get records of the employees who are under same paybill group
	 * @param bean
	 * @return String as part of query that returns records of the employees who are under same paybill group
	 */
	public String getprofilePaybillQuery(BeanBase bean) {
		String query = "";
		try {
			if (bean.isGeneralFlag()) {
				return query;
			} else {
				if (bean.getUserProfilePaybill().length() > 0) {
					query += "WHERE HRMS_EMP_OFFC.EMP_PAYBILL IN (" + bean.getUserProfilePaybill() + ")";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return query;
	}
	
	/**
	 * Initialize the bean object and sets menu code
	 */
	public abstract void prepare_local() throws Exception;

	
	/**
	 * Method overload for LMS
	 * Added by REEBA
	 * @param query
	 * @param headers
	 * @param headerWidth
	 * @param fieldNames
	 * @param columnIndex
	 * @param submitFlag
	 * @param submitToMethod
	 */
	public void setF9Window(String query, String[] headers,
			String[] headerWidth, String[] fieldNames, int[] columnIndex,
			String submitFlag, String submitToMethod) {
		 setF9Window(query, headers,
					 headerWidth, fieldNames, columnIndex,
					submitFlag, submitToMethod,false );
	}

	/**
	 * Opens a popup window and shows saved records
	 */
	/**
	 * @param query -: Specifies sql query to execute
	 * @param headers -: Specifies column headings
	 * @param headerWidth -: Specifies column widths
	 * @param fieldNames -: Specifies the fields on a page
	 * @param columnIndex -: Specifies the indexes of the columns
	 * @param submitFlag -: Specifies the boolean flag to call another method which returns 'success'
	 * @param submitToMethod -: Specifies the path of the method
	 */
	public void setF9Window(String query, String[] headers, String[] headerWidth, String[] fieldNames, int[] columnIndex, String submitFlag, String submitToMethod, boolean isLms) {
		int RECORDS_TOTAL = 0;
		int TOTAL_RECORDS = 0;
		int PAGE_NO = 0;
		int RECORDS_TO = 0;
		int RECORDS_INTERVAL = 25;

		BeanBase applicationBean = (BeanBase)getModel();
		RECORDS_TO = applicationBean.getRecordCount();// Get total number of records fetched from executing a query

		int PageNo = 1;

		try {
			if(applicationBean.getPageNo() != null) {// Check page is selected or not
				PageNo = Integer.parseInt(applicationBean.getPageNo());// sets page no. to selected page number
			} else {
				RECORDS_TO += RECORDS_INTERVAL;
			} // end of else statement
		} catch(Exception e) {
			//logger.error(e);
		} // end of try-catch block
		applicationBean.setRecordCount(RECORDS_TO);

		/**
		 * Executes a query and get the records
		 */
		F9Model model = new F9Model();
		model.initiate(context, session);

		String f9query = applicationBean.getF9query();

		String[][] obj = new String[0][0];
		if(applicationBean.getF9query().equals("")) {
			applicationBean.setF9query(query);
			obj = model.getData(query,isLms, null);
		} else {
			obj = model.getData(f9query,isLms, null);
		} // end of else statement

		/**
		 * Searches the given string and returns the records
		 */
		try {
			if(!(applicationBean.getF9SearchText().equals("") || applicationBean.getF9SearchText() == null || applicationBean.getF9SearchText().equals(null))) {
				obj = new GloActionSupport().searchData(request, obj, applicationBean.getHdF9SearchIndex(), applicationBean.getF9SearchText(),applicationBean.getF9SearchSelect());
			}
		} catch(Exception e) {
			//logger.error(e);
		}

		RECORDS_TOTAL = obj.length;
		TOTAL_RECORDS = obj.length;
		int pages = Math.round(RECORDS_TOTAL / 25);// get total number of pages
		double row = (double)obj.length / 25.0;
		java.math.BigDecimal value1 = new java.math.BigDecimal(row);

		int rowCount = Integer.parseInt(value1.setScale(0, java.math.BigDecimal.ROUND_UP).toString());

		// --------------Show Master--------------------------------------------------------start
		int masterMenuCode = applicationBean.getMasterMenuCode();
		String userProfileID = applicationBean.getUserProfileId();
		String integratedLink = model.getIntegratedLink(masterMenuCode, userProfileID, request);
		request.setAttribute("integratedLink", integratedLink);
		// --------------Show Master--------------------------------------------------------end

		model.terminate();

		if(obj.length == 0) {
			addActionMessage("NO DATA FOUND");
		}

		String[] displayData = new String[RECORDS_INTERVAL];
		String[][] f9DataObject = new String[RECORDS_INTERVAL][columnIndex.length];

		int z = 0;
		for(int x = 0; x < obj.length; x++) {
			try {
				if(x <= RECORDS_TO && x >= RECORDS_TO - RECORDS_INTERVAL) {
					String value = "";
					for(int y = 0; y < columnIndex.length; y++) {
						value += obj[x][columnIndex[y]] + "***";
						f9DataObject[z][y] = obj[x][columnIndex[y]];
					}
					String actValue = value.substring(0, value.length() - 3);
					displayData[z] = actValue.replaceAll("'", "");
					z++;
				}
			} catch(Exception e) {
				//logger.error(e);
			}
		} // end of for loop

		// Append fields names
		String fldName = "";
		for(int i = 0; i < fieldNames.length; i++) {
			fldName += fieldNames[i] + ",";
		}
		fldName = fldName.substring(0, fldName.length() - 1);

		// Add column headings into a MAP
		java.util.Map<String, String> headersMAP = new HashMap<String, String>();
		for(int i = 0; i < headers.length; i++) {
			headersMAP.put("" + i, headers[i]);
		} // end of for loop

		/**
		 * Sets the necessary request objects
		 */
		request.setAttribute("headerMAP", headersMAP);
		request.setAttribute("headerNames", headers);
		request.setAttribute("headerWidth", headerWidth);
		request.setAttribute("fieldNames", fldName);
		request.setAttribute("f9Data", GloActionSupport.scanData(f9DataObject));
		request.setAttribute("columnIndex", columnIndex);
		request.setAttribute("displayData", displayData);
		request.setAttribute("submitToMethod", submitToMethod);
		request.setAttribute("submitFlag", submitFlag);
		request.setAttribute("pages", rowCount);
		request.setAttribute("PageNo", PageNo);
		request.setAttribute("TOTAL_RECORDS", TOTAL_RECORDS);
	}

	
	
	/**
	 * Gets the reporting empoyee's code and name concatenated.
	 * @param empcode
	 * @return String as concatenated reporting empoyee's code and name
	 */
	public String returnReportingOffc(String empcode) {
		UserProfileModel userModel = new UserProfileModel();
		userModel.initiate(context, session);
		String offc = userModel.selectRecomended(empcode);
		try {
			offc = offc.substring(0, offc.length() - 5);
		} catch(Exception e) {
			logger.error(e);
			offc = "0";
		} // end of try-catch block
		userModel.terminate();
		return offc;
	}

	public void addActionMessage(String message) {
		try {
			Actionmessage = Actionmessage + "\n" + message;
			BeanBase applicationBean = (BeanBase) getModel();
			applicationBean.setActionMessage(Actionmessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets reporting officer's employee id from reporting structure for a particular employee.
	 * @param empCode -: Specifies employee id of employee whose reporting structure is defined
	 * @param type -: Specifies the purpose
	 * @param order -: Specifies the level
	 * @return Object[][] as employee id of the reporting officer
	 */
	
	public Object[][] generateEmpFlow(String empCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateEmpFlow(empCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * Gets reporting officer's employee id from reporting structure for a particular department
	 * @param deptCode -: Specifies department id of employee whose reporting structure is defined
	 * @param type -: Specifies the purpose
	 * @param order -: Specifies the level
	 * @return Object[][] as employee id of the reporting officer
	 */
	public Object[][] generateDeptFlow(String deptCode, String type, int order) {
		Object result[][] = null;
		try {
			ReportingModel reporting = new ReportingModel();
			reporting.initiate(context, session);
			result = reporting.generateDeptFlow(deptCode, type, order);
			reporting.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public void getNavigationPanel(int modeCode) {
		try {
			String menuCode = "";
			boolean insertFlag = false;
			boolean updateFlag = false;
			boolean deleteFlag = false;
			boolean viewFlag = false;
			boolean generalFlag = false;
			
			BeanBase bean = (BeanBase)getModel();
			
			if(bean != null) {
				menuCode = String.valueOf(bean.getMenuCode());
				insertFlag = bean.isInsertFlag();
				updateFlag = bean.isUpdateFlag();
				deleteFlag = bean.isDeleteFlag();
				viewFlag = bean.isViewFlag();
				generalFlag = bean.isGeneralFlag();
				
				String enableAll = request.getParameter("enableAll");
				if(!(enableAll == null || enableAll.equals("") || enableAll.equals("null"))) {
					bean.setEnableAll(enableAll);
				}
			} else {
				menuCode = request.getParameter("menuCode");
				insertFlag = Boolean.parseBoolean(request.getParameter("insertFlag"));
				updateFlag = Boolean.parseBoolean(request.getParameter("updateFlag"));
				deleteFlag = Boolean.parseBoolean(request.getParameter("deleteFlag"));
				viewFlag = Boolean.parseBoolean(request.getParameter("viewFlag"));
				generalFlag = Boolean.parseBoolean(request.getParameter("generalFlag"));
			}
			
			String mCode = String.valueOf(modeCode);
			
			getPanel(menuCode, mCode, insertFlag, updateFlag, deleteFlag, viewFlag, generalFlag);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Object[][] getNavigationSettings(String menuCode, String modeCode) {
		Object[][] navList = null;
		try {
			String fileName = menuCode + ".xml";
			String file = getText("data_path") + "/NavigationPanel/" + fileName;
			File navFile = new File(file);

			if(navFile.exists()) {
				Document document = new XMLReaderWriter().parse(navFile);
				
				String templateName = "";
				List listTemplate = document.selectNodes("//NAVIGATION_PANEL/TEMPLATE");
				for(Iterator<Element> itTemplate = listTemplate.iterator(); itTemplate.hasNext();) {
					Element templateEl = (Element)itTemplate.next();
					templateName = String.valueOf(templateEl.attributeValue("templateName"));
				}

				List listButtons = document.selectNodes("//NAVIGATION_PANEL/TEMPLATE/MODE[@modeCode='" + modeCode + "']/BUTTON");

				navList = new Object[listButtons.size()][3];
				int cnt = 0;
				for(Iterator<Element> itButton = listButtons.iterator(); itButton.hasNext();) {
					Element navEl = (Element)itButton.next();
					navList[cnt][0] = String.valueOf(navEl.attributeValue("btnCode"));
					try {
						navList[cnt][1] = String.valueOf(navEl.attributeValue("nextMode"));
					} catch(Exception e) {
						navList[cnt][1] = "0";
					}
					
					navList[cnt][2] = templateName;
					cnt++;
				}
			} else {
				addActionMessage(fileName + " not found!");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return navList;
	}

	
	public Object[][] getButtons(String templateName) {
		Object[][] btnList = null;
		try {
			String fileName = "buttons_" + templateName + ".xml";
			String file = getText("data_path") + "/NavigationPanel/" + fileName;
			File btnFile = new File(file);

			if(!btnFile.exists()) {
				file = getText("data_path") + "/NavigationPanel/buttons.xml";
				btnFile = new File(file);
				//addActionMessage(fileName + " not found!");
			}
			
			Document document = new XMLReaderWriter().parse(btnFile);
			List btnNodes = document.selectNodes("//BUTTON_MASTER/BUTTON");

			btnList = new Object[btnNodes.size()][6];
			int cnt = 0;
			for(Iterator<Element> itButton = btnNodes.iterator(); itButton.hasNext();) {
				Element elButton = (Element)itButton.next();
				btnList[cnt][0] = String.valueOf(elButton.attributeValue("btnCode"));
				btnList[cnt][1] = String.valueOf(elButton.attributeValue("btnName"));
				btnList[cnt][2] = String.valueOf(elButton.attributeValue("btnOrder"));
				btnList[cnt][3] = String.valueOf(elButton.attributeValue("btnFlag"));
				btnList[cnt][4] = String.valueOf(elButton.attributeValue("enableAll"));
				btnList[cnt][5] = String.valueOf(elButton.attributeValue("btnClass"));
				cnt++;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return btnList;
	}
	
	public String getMessage(String key) {
		String obj = "";
		try {
			HashMap hMap = (HashMap)context.getAttribute("common" + session.getAttribute("session_pool"));
			obj = (String)hMap.get(key);
			if(obj == null || obj.equals(null)) {
				hMap = (HashMap)request.getAttribute("formLabels");
				obj = (String)hMap.get(key);
			}
		} catch(Exception e) {
			return "Message Not Found";
		}
		if(obj == null)
			return "Message Not Found";
		return obj;
	}

	

	
	public void getPanel(String menuCode, String modeCode, boolean insertFlag, boolean updateFlag, boolean deleteFlag, 
			boolean viewFlag, boolean generalFlag) {
		Object[][] settingsList = null;
		try {
			Object[][] navList = getNavigationSettings(menuCode, modeCode);
			Object[][] btnList = getButtons(String.valueOf(navList[0][2]));

			Object[][] settings = {};
			
			for(int i = 0; i < btnList.length; i++) {
				String btnCodeMaster = String.valueOf(btnList[i][0]);
				String btnName = String.valueOf(btnList[i][1]);
				String btnOrder = String.valueOf(btnList[i][2]);
				String btnFlag = String.valueOf(btnList[i][3]);
				String enableAll = String.valueOf(btnList[i][4]);
				String btnClass = String.valueOf(btnList[i][5]);
				
				for(int j = 0; j < navList.length; j++) {
					String btnCodeNav = String.valueOf(navList[j][0]);
					String nextModeCode = String.valueOf(navList[j][1]);
					
					if(btnCodeMaster.equals(btnCodeNav)) {
						boolean isButtonApplicable = false;
						if(btnFlag.equals("I") && insertFlag) {
							isButtonApplicable = true;
						} else if(btnFlag.equals("U") && updateFlag) {
							isButtonApplicable = true;
						} else if(btnFlag.equals("D") && deleteFlag) {
							isButtonApplicable = true;
						} else if(btnFlag.equals("V") && viewFlag) {
							isButtonApplicable = true;
						} else if(btnFlag.equals("G") && generalFlag) {
							isButtonApplicable = true;
						}
						
						if(isButtonApplicable) {
							Object[][] navigationSettings = new Object[1][5];
							
							navigationSettings[0][0] = btnName;
							navigationSettings[0][1] = btnOrder;
							navigationSettings[0][2] = enableAll;
							navigationSettings[0][3] = nextModeCode;
							navigationSettings[0][4] = btnClass;
							
							settings = Utility.joinArrays(settings, navigationSettings, 1, 0);
						}
						break;
					}
				}
			}

			settingsList = settings;
			
			for(int i = 0; i < settingsList.length; i++) {
				for(int j = i + 1; j < settingsList.length; j++) {
					int a = Integer.parseInt(String.valueOf(settingsList[i][1]));
					int b = Integer.parseInt(String.valueOf(settingsList[j][1]));

					if(a > b) {
						Object tempObj[][] = new Object[1][5];
						tempObj[0][0] = settingsList[i][0];
						tempObj[0][1] = settingsList[i][1];
						tempObj[0][2] = settingsList[i][2];
						tempObj[0][3] = settingsList[i][3];
						tempObj[0][4] = settingsList[i][4];

						settingsList[i][0] = settingsList[j][0];
						settingsList[i][1] = settingsList[j][1];
						settingsList[i][2] = settingsList[j][2];
						settingsList[i][3] = settingsList[j][3];
						settingsList[i][4] = settingsList[j][4];

						settingsList[j][0] = tempObj[0][0];
						settingsList[j][1] = tempObj[0][1];
						settingsList[j][2] = tempObj[0][2];
						settingsList[j][3] = tempObj[0][3];
						settingsList[j][4] = tempObj[0][4];
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("settingsList", settingsList);
		request.setAttribute("currentMode", modeCode);
		
		BeanBase bean = (BeanBase)getModel();
		
		if(bean == null) {
			try {
				String responseText = "";
				
				for(int row = 0; row < settingsList.length; row++) {
					for(int col = 0; col < settingsList[0].length; col++) {
						if(col == (settingsList[0].length - 1)) {
							responseText += settingsList[row][col];
						} else {
							responseText += settingsList[row][col] + ",";
						}
					}
					if(row < (settingsList.length - 1)) {
						responseText += "@";
					}
				}
				
				response.setContentType("text/html");
				PrintWriter pw = response.getWriter();
				pw.print(responseText);
				pw.flush();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}	
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public ServletContext getContext() {
		return context;
	}

	public void setContext(ServletContext context) {
		this.context = context;
	}
	
	/*public void prepare_OLD() throws Exception {
		*//**
		 * Session Handling
		 *//*
		try {
			setSession(request.getSession());
		} catch(Exception e) {
			logger.error(e);
		} // end of try-catch block

		try {
			prepare_local(); // TO BE OVERRIDEN IN EACH ACTION SUBCLASS
		} catch(Exception e) {
			logger.error(e);
		} // end of try-catch block

		*//**
		 * Session Check for Login
		 *//*
		try {
			PartnerLogin bean = (PartnerLogin)request.getSession().getAttribute("loginBeanData");
			System.out.println("bean.getEmpId()==========In PartnetActionsupport============ ");
			System.out.println("bean.getEmpId()==========In PartnetActionsupport============ "+bean.getEmpId());
			if(bean == null) {
				// forward to login Page
				response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/recruit/PartnerLogin_input.action");
			} // end of if statement
			else {
				*//**
				 * Instantiate BeanBase object
				 *//*
				BeanBase applicationBean = (BeanBase)getModel();
				applicationBean.setUserLoginCode(bean.getLoginCode());
				applicationBean.setUserEmpId(bean.getEmpId());
				applicationBean.setUserVisitorNo(bean.getVisitorNo());
				applicationBean.setUserLoginDate(bean.getLoginDate());
				applicationBean.setLoginTime(bean.getLoginTime());
				applicationBean.setUserProfileId(bean.getProfileCode());
				applicationBean.setChatLogin(bean.getLoginName());
				applicationBean.setEmailUserId(bean.getEmailId());
				applicationBean.setEmailPwd(bean.getEmailPwd());
				UserProfileModel userModel = new UserProfileModel();
				userModel.initiate(context, session);

				*//**
				 * Setting of Service Flag
				 *//*
				applicationBean.setVasFlag(false);
				
				 * try { Object[][] res = userModel.selectServiceFlag(); if (String.valueOf(res[0][0]).equals("Y")) {
				 * applicationBean.setVasFlag(true); } //end of if statement else { applicationBean.setVasFlag(false); } } catch
				 * (Exception e) { logger.error(e); } //end of try-catch block
				 
				*//**
				 * Set lable properties
				 *//*

				logger.info("menu code " + applicationBean.getMenuCode());
				long timeStart = System.currentTimeMillis();
				org.paradyne.model.common.LabelManagementModel model = new org.paradyne.model.common.LabelManagementModel();
				model.initiate(context, session);
				model.loadCommonLabels(getText("data_path"));
				if(applicationBean.getMenuCode() > 0) {
					java.util.HashMap formLabels = model.loadFormLabels(applicationBean.getMenuCode(), getText("data_path"));
					getRequest().setAttribute("formLabels", formLabels);
				}
				model.terminate();
				long timeEnd = System.currentTimeMillis();
				logger.info("Time taken to load labels " + (timeEnd - timeStart));

				*//**
				 * Set Access Rights
				 *//*
				try {
					boolean[] rights = userModel.getAccessRights(String.valueOf(applicationBean.getMenuCode()), bean.getProfileCode());

					applicationBean.setInsertFlag(rights[0]);
					applicationBean.setUpdateFlag(rights[1]);
					applicationBean.setDeleteFlag(rights[2]);
					applicationBean.setViewFlag(rights[3]);
					applicationBean.setGeneralFlag(rights[4]);

					
					 * if (!applicationBean.isGeneralFlag()) { //Not a General Employee
					 * applicationBean.setUserProfileCenters(userModel.getProfileCenters(bean.getProfileCode())); //center
					 * applicationBean.setUserProfileEmpType(userModel.getProfileEmpTypes(bean.getProfileCode())); //emptype
					 * applicationBean.setUserProfilePaybill(userModel.getProfilePaybill(bean.getProfileCode())); //paybill }
					 * //end of if statement
					 } catch(Exception e) {
					logger.error(e);
				} // end of try-catch block

				// Redirect if no view access
				if(applicationBean.isInsertFlag() || applicationBean.isUpdateFlag() || applicationBean.isViewFlag() || applicationBean.getMenuCode() == -1) {} // end
																																								// of
																																								// if
																																								// statement
				else {
					response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/pages/common/noAccess.jsp");
				}
				userModel.terminate();
			} // end of else statement
		} catch(Exception e) {
			logger.error(e);
		} // end of try-catch block

		// To user login and profile details
		try {
			prepare_withLoginProfileDetails();
		} catch(Exception e) {
			logger.error(e);
		} // end of try-catch block
	}
	*/
}