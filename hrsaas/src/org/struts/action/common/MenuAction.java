/**
 * 
 */
package org.struts.action.common;

import java.io.File;
import java.util.Random;

import org.paradyne.bean.common.MenuBean;
import org.paradyne.lib.BeanBase;

import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.common.MenuModel;
import org.struts.lib.ParaActionSupport;

/**
 * FOR GENERATE MENU DYNAMICALLY
 * 
 * @author sunil and Lakkichand
 * @since :06-04-2007
 * 
 */
public class MenuAction extends ParaActionSupport {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return menuBean;
	}

	MenuBean menuBean;
	String poolName = "";

	/**
	 * @return the menuBean
	 */
	public BeanBase getMenuBean() {
		return menuBean;
	}

	/**
	 * @param menuBean
	 *            the menuBean to set
	 */
	public void setMenuBean(MenuBean menuBean) {
		this.menuBean = menuBean;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		menuBean = new MenuBean();
	}

	public String create() throws Exception {
		logger
				.info("in createMenu.....................>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>...............2"
						+ request.getContextPath());
		
		try {
			String leftPagemenuCode = request.getParameter("leftPagemenuCode");
			System.out.println("leftPagemenuCode--------" + leftPagemenuCode);
			menuBean.setHdMenuCode(leftPagemenuCode);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String mainMenu = menuBean.getHdMenuCode();
		logger.info("Main menu Code "+mainMenu);
		if(mainMenu.equals(""))
			mainMenu = request.getParameter("homeCode");
		try{
		if(mainMenu.equals(""))
			mainMenu = request.getParameter("menuCode");
		}catch(Exception e)
		{
			logger.error("Null Menu Code");
		}
		poolName = String.valueOf(session.getAttribute("session_pool"));
		// String query="SELECT
		// MENU_CODE,MENU_PARENT_CODE,MENU_NAME,MENU_LINK,MENU_ALT_MESSAGE,MENU_TARGET
		// FROM HRMS_MENU ORDER BY MENU_CODE";
		String contextPath = "";
		try {
			MenuModel model = new MenuModel();
			model.initiate(context, session);
			model.setLoginInfo(menuBean);
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}

			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\quick_links\\quick.xml";

			String[][] quickLinkObj;
			try {

				quickLinkObj = model.processQuickLink(new XMLReaderWriter()
						.parse(new File(path)));
			} catch (Exception e) {
				quickLinkObj = new String[0][0];

				// TODO: handle exception
			}
			request.setAttribute("quickLinkObj", quickLinkObj);
				
			contextPath = request.getContextPath();

			String query = "SELECT DISTINCT HRMS_MENU.MENU_CODE,HRMS_MENU.MENU_PARENT_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "

					+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
					+ contextPath
					+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
					+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU "
					+ " FROM HRMS_MENU "
					+ " INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE "
					
					+ "  and HRMS_PROFILE_DTL.PROFILE_CODE IN("
					+ menuBean.getMultipleProfileCode()
					+ ") AND ( PROFILE_INSERT_FLAG='Y' "
					+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
					+ " OR PROFILE_GENERAL_FLAG ='Y')) "
					+" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
					+" WHERE MENU_ISRELEASED='Y'"
					+" AND (HRMS_MENU.MENU_TYPE NOT IN ('RT','ES'))"
					+ " START WITH HRMS_MENU.MENU_CODE = "
					+ mainMenu
					+ " CONNECT BY PRIOR  HRMS_MENU.MENU_CODE=HRMS_MENU.MENU_PARENT_CODE "
					+ " ORDER BY HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_CODE ";

			String[][] twoDimObjArr = model.getMenuData(query);

			model.terminate();

			request.setAttribute("twoDimObjArr", twoDimObjArr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "success";
	}

	
	
	public String createMainMenu() throws Exception {
		logger
				.info("in createMainMenu.....................>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>...............2"
						+ request.getContextPath());
		
		String contextPath = request.getContextPath();
		int random1 = 0;
		Random random = new Random();
		String query = "SELECT distinct HRMS_MENU.MENU_CODE,NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME), "
				+ " CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  '"
				+ contextPath
				+ "'||HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK,"
				+ " NVL(HRMS_MENU.MENU_ALT_MESSAGE,'Click'),HRMS_MENU.MENU_TARGET,HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_TABORDER "
				+ " FROM HRMS_MENU "
				+ " INNER JOIN HRMS_PROFILE_DTL ON ( HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE AND ( PROFILE_INSERT_FLAG='Y' "
				+ " OR PROFILE_UPDATE_FLAG ='Y'  OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y' "
				+ " OR PROFILE_GENERAL_FLAG ='Y'))"
				+" LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE)"
				+ " WHERE HRMS_PROFILE_DTL.PROFILE_CODE ='"
				+ menuBean.getUserProfileId()
				+ "'"
				+ " AND MENU_PARENT_CODE=0 AND MENU_ISRELEASED='Y'"
				+ " ORDER BY HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_CODE";

		logger.info("in createMenu..");
		MenuModel model = new MenuModel();
		model.initiate(context, session);
		String[][] menuList = model.getMenuList(query);
		// --------------------------------------------------------

		try {

			poolName = String.valueOf(session.getAttribute("session_pool"));
			// logger.info("in createMenu..22222222222222222222");
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}
			// logger.info("in createMenu.."+poolName);
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\thought\\thought.xml";

			// logger.info("in createMenu..3333333333333");

			String[][] thoughtObj;
			String thought = "";
			try {

				// logger.info("in createMenu.. "+path);
				thoughtObj = model.processThought(new XMLReaderWriter()
						.parse(new File(path)));
				// int maxLength = thoughtObj.length;
				String[] thouthId = new String[thoughtObj.length];
				for (int i = 0; i < thouthId.length; i++) {
					thouthId[i] = thoughtObj[i][1];
				}
				logger.info("random.nextInt(thouthId.length before)=="
						+ random.nextInt(thouthId.length));
				// random1 = doRawRandomNumber(maxLength);
				// thouthId[random.nextInt(thouthId.length)]);
				int randomValue = Integer
						.parseInt(String.valueOf(thoughtObj[random
								.nextInt(thouthId.length)][1]));
				
				for (int k = 0; k < thoughtObj.length; k++) {
					logger.info("Random Value In loop       " + randomValue);
					if (randomValue == Integer.parseInt(String
							.valueOf(thoughtObj[k][1]))) {
						logger
								.info("Thought of the Day is "
										+ thoughtObj[k][2]);
						thought = String.valueOf(thoughtObj[k][2]);
					}
				}
				// loginBean.setThought(thought);
				logger.info("random.nextInt(thouthId.length)=="
						+ random.nextInt(thouthId.length));
				// logger.info("thought...................
				// "+thoughtObj[random][0]);
			} catch (Exception e) {
				thoughtObj = new String[0][0];
				e.printStackTrace();
				// TODO: handle exception
			}
			logger.info("thought..................."+thought);

			// menuBean.setThought(String.valueOf("hi11111111111111"));
			// ------------------------------------------------------

			// String str=model.getThought(menuBean);
			// /logger.info("lllllllllllllll"+str);
			Object[][] cmpName = model.getCmpName();
			String cmpanyName = null;
			try {
				cmpanyName = String.valueOf(cmpName[0][0]);
			} catch (Exception e) {
				// TODO: handle exception
			}
			// System.out.println("cmpny
			// name-------------------------------------------->>"+cmpanyName);
			logger.info("in thought.........."+thought);
			logger.info("in createMenu..");
			request.setAttribute("menuList", menuList);
			request.setAttribute("compName", cmpanyName);
			request.setAttribute("thought", thought);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "menuList";
	}

	public int doRawRandomNumber(int max1) {

		int rawRandomNumber = 0;
		int min = 1;
		int max = max1;

		rawRandomNumber = (int) (Math.random() * (max - min + 1)) + min;
		// System.out.println("Random number : " + rawRandomNumber);
		return rawRandomNumber;
	}

}
