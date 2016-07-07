package org.struts.action.common;

import java.io.File;
import org.paradyne.bean.common.EmployeePortal;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.common.EmployeePortalModel;
import org.struts.lib.ParaActionSupport;

/**
 * @MOdified By AA1711
 *
 */
public class EmployeePortalAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;

	EmployeePortal employeePortal;

	String poolName = "";

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(EmployeePortalAction.class);

	public void prepare_local() throws Exception {
		employeePortal = new EmployeePortal();
		poolName = String.valueOf(session.getAttribute("session_pool"));
	}

	public Object getModel() {
		return employeePortal;
	}

	public String showEmployeePortal() {
		try {
			System.out.println("In method--------------------------");
			getEmpInfo();
			getHRComm();
			getGeneralInfo();
			getPoll();
			getKnowInfo();
			getCorpInfo();
			getImageGallery();
			getAwardImages();
			getQuickLinks();
			getApplicationLoginDetails();
			getEventData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showEmpPortal";
	}
	
	
	public String showMoreInfo() {
		try {
			System.out.println("showMoreInfo showMoreInfo showMoreInfo");
			getEmpInfo();
			getHomeSearch();
			System.out.println("In method--------------------------"+request.getParameter("dashletCode"));
			request.setAttribute("dashletCode", request.getParameter("dashletCode"));
			getHRComm();
			getEventData();
			getGeneralInfo();
			getApplicationLoginDetails();
			getKnowInfo();
			getCorpInfo();
			quickDownloadInfoDashlet();
		 
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showMorePortal";
	}
	
	public void quickDownloadInfoDashlet()
	{
			try {
				EmployeePortalModel model = new EmployeePortalModel();
				model.initiate(context, session);
				model.quickDownload(request, employeePortal);
				model.terminate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		 
	}
	
  
	public String showQuickDownload(){
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			 String downloadCode =request.getParameter("downloadCode");
			 request.setAttribute("catagoreyNmae",downloadCode);
			 System.out.println("downloadCode   "+downloadCode);
			 model.initiate(context, session);
				model.showQuickDownload(request,employeePortal, downloadCode);
				model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "showQuickDownload";
	}
	
	public String showKnowledge(){
		EmployeePortalModel model= new EmployeePortalModel();
		try {
			model.initiate(context, session);
			String knowCat = request.getParameter("knowCode");
			request.setAttribute("knowCategoryName", knowCat);
			model.showKnowledge(request,employeePortal,knowCat);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showKnowledge";
	}
	 
	
	
	public void getHolidayList() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getHolidayList(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getQuickContacts() {	
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getQuickContacts(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Inn getQuickContacts---------------------------");
		return "quickContactsDashlet";
	}
	
	
	public void getBirthdayList() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getBirthdayList(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getNewJoineeList() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getNewJoineeList(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void getResignedList() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getResignedEmployeeList(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
/*	public void showDownload() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			String downloadCode =request.getParameter("downloadCode");
			System.out.println("downloadCode-----------------"+downloadCode);
			model.getDownloadInfo(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	
	
	public String showGeneralDashletList() {
		try {
			getEmpInfo();
			getHomeSearch();
			System.out.println("In method--------type-----------------"+request.getParameter("type"));
			request.setAttribute("type", request.getParameter("type"));
			getHRComm();
			getEventData();
			getGeneralInfo();
			getApplicationLoginDetails();
			getKnowInfo();
			getCorpInfo();
			getHolidayList();
			getBirthdayList();
			getNewJoineeList();
			//Added by janisha for resigned employee list display
			getResignedList();
			//End added by janisha for resigned employee list display
			 
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "showGeneralInfo";
	}
	
	
	
	public String quickDownload() {
		String quickDownloadShow ="";
		try {
			getEmpInfo();
			getHomeSearch();			
			request.setAttribute("downloadCode", request.getParameter("downloadCode"));
			getHRComm();
			getEventData();
			getGeneralInfo();
			getKnowInfo();
			getCorpInfo();
			
			if(request.getParameter("downloadCode").equals("1")){
				quickDownloadShow ="glodyneLogosDownload";
			}
			if(request.getParameter("downloadCode").equals("2")){
				quickDownloadShow ="gree_wall";
			}
		 
		} catch (Exception e) {
			e.printStackTrace();
		}
		return quickDownloadShow;
	}
	
	public void getEventData() {

		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getEventData(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void getApplicationLoginDetails() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getApplicationLoginDetails(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getImageGallery() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getImageGallery(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setThoughtOfDay() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\thought\\thought.xml";
			model.setThoughtOfDay(request, employeePortal, path);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getAwardImages() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getAwardImages(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getQuickLinks() {
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\quick_links\\quick.xml";

			model.getQuickLinks(request, employeePortal, path);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getEmpInfo() {

		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getEmployeeInfo(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getCorpInfo() {

		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getCorpInfo(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getGeneralInfo() {
		try {
			EmployeePortalModel model = new EmployeePortalModel();
			model.initiate(context, session);
			String[][] genList = null;
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\general\\general.xml";// path of XML
			try {
				// XML read
				genList = model.processGenInfo(new XMLReaderWriter()
						.parse(new File(path)));

			} catch (Exception e) {
				genList = new String[0][0];
				logger.error("Exception Caught - General Info: " + e);
			}
			request.setAttribute("genList", genList);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void getHRComm() {

		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getHRComm(request, employeePortal.getUserEmpId());
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	/*public void quickDownloadInfoDashlet()
	{
		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.quickDownloadInfoDashlet(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	
	 
	
	public void getPoll() {

		EmployeePortalModel model = null;
		try {
			model = new EmployeePortalModel();
			model.initiate(context, session);
			String[][] pollList = null;
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}// end of if

			// READING FROM XML
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\poll\\poll.xml";

			File file = new File(path);

			final Object[][] result = model
					.processPollInfo(new XMLReaderWriter()
							.parse(new File(path)), employeePortal);
			logger.info("Poll Code-----" + employeePortal.getPollCode());
			Object optionObj[][] = new Object[result.length][4];
			for (int i = 0; i < optionObj.length; i++) {
				optionObj[i][0] = result[i][0];
				optionObj[i][2] = result[i][2];
				optionObj[i][3] = result[i][3];
				optionObj[i][1] = String.valueOf(result[i][1]).replace(
						"%26apos;", "'").replace("%26", "&");
			}
			request.setAttribute("pollData", result);
			request.setAttribute("optionObj", optionObj);

			model.terminate();

			model.terminate();
		} catch (Exception e) {
			//e.printStackTrace();
		}

	}

	public void getKnowInfo() throws Exception {
		try {
			EmployeePortalModel model = null;
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.getKnowInfo(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
 

	public String connectWithCeo() throws Exception {
		try {
			EmployeePortalModel model = null;
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "connectWithCeo";
	}

	public String submitCeoDesc() {
		try {
			EmployeePortalModel model = null;
			model = new EmployeePortalModel();
			model.initiate(context, session);
			String description = request.getParameter("description");
			String subject = request.getParameter("subject");
			String hideIdentity = request.getParameter("hideIdentity");
			String loginEmpId = request.getParameter("loginEmpId");

			System.out.println("description   " + description);
			System.out.println("subject  " + subject);

			System.out.println("hideIdentity   " + hideIdentity);
			System.out.println("loginEmpId  " + loginEmpId);
			boolean res = model.submitCeoDesc(description, subject,
					hideIdentity, loginEmpId);

			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");

			if (res) {
				response.getWriter().write(
						"<ceoresult>" + "saved" + "</ceoresult>");

			} else {
				response.getWriter().write(
						"<ceoresult>" + "notsaved" + "</ceoresult>");
			}

			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//Added by janisha for birthday month wise search
	public String searchBdayRecord()
	{
		EmployeePortalModel model = null;
		try {
			request.setAttribute("type", request.getParameter("type"));
			model = new EmployeePortalModel();
			model.initiate(context, session);
			//model.getBdayRecord(request, employeePortal);
			model.getBirthdayList(request, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "showGeneralInfo";
	}
	//end added by janisha for birthday month wise search

	public String pollSave() throws Exception {
		try {
			System.out
					.println("In savepoll method---------------------------------------");
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\poll\\poll.xml";
			EmployeePortalModel model = null;
			model = new EmployeePortalModel();
			model.initiate(context, session);
			model.savePoll(request, response, path, employeePortal);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getHomeSearch() {
		try {
			
			//do logic here
			
			String searchType = request.getParameter("searchType");
			String searchString = request.getParameter("searchText");
			String resultOfSearch=request.getParameter("resultOfSearch");
			System.out.println("Result Of Search received in Action getHomeSearch()................."+resultOfSearch);
			
			String menuoremployee =request.getParameter("searchType");
			System.out.println("searchType  "+searchType);
			System.out.println("menuoremployee  "+menuoremployee);
			
			EmployeePortalModel model = new EmployeePortalModel();
			model.initiate(context, session);
			getEmpInfo();
			getApplicationLoginDetails();
			model.getSearch(searchType, searchString, request, employeePortal
					.getMultipleProfileCode(),employeePortal.getMyPage(),employeePortal,resultOfSearch);
			Object data[][]=model.getEmployeeDirectoryConfiguration();
			request.setAttribute("employeeDirConf", data);
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchString", searchString);
			request.setAttribute("selectedTab", menuoremployee);
			request.setAttribute("searchResult", resultOfSearch);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "searchRecord";

	}
	
	
	public String getMenuSearch() {
		try {
			String searchType = request.getParameter("searchType");
			String searchString = request.getParameter("searchText");
			String resultOfSearch =request.getParameter("resultOfSearch");
			EmployeePortalModel model = new EmployeePortalModel();
			model.initiate(context, session);
			String actionName =request.getParameter("actionName");
			System.out.println("actionName     "+actionName);
			request.setAttribute("actionName", actionName);
			getEmpInfo();
			getApplicationLoginDetails();
			model.getSearch(searchType, searchString, request, employeePortal
					.getUserProfileId(),employeePortal.getMyPage(),employeePortal, resultOfSearch);
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchString", searchString);
			request.setAttribute("selectedTab", searchType);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "searchMenuRecord";

	}
	
	public String getEmployeeDetails()
	{
		try {
			String searchType = request.getParameter("searchType");
			String searchString = request.getParameter("searchText");
			String menuoremployee = request.getParameter("searchType");
			String resultOfSearch =request.getParameter("resultOfSearch");
			System.out.println("searchType  " + searchType);
			System.out.println("menuoremployee  " + menuoremployee);
			EmployeePortalModel model = new EmployeePortalModel();
			model.initiate(context, session);
			String empCode =request.getParameter("empCode");
			
			System.out.println("empCode  "+empCode);
			getEmpInfo();
			getApplicationLoginDetails();
			model.getSearch(searchType, searchString, request, employeePortal
					.getUserProfileId(), employeePortal.getMyPage(),
					employeePortal, resultOfSearch);
			Object empDtlObj[][] = model.getEmployeeDetailtRecord(empCode,employeePortal,request);
			
			Object data[][] = model.getEmployeeDirectoryConfiguration();
			request.setAttribute("employeeDirConf", data);
			request.setAttribute("empDtlObj", empDtlObj);
			request.setAttribute("searchType", searchType);
			request.setAttribute("searchString", searchString);
			request.setAttribute("selectedTab", menuoremployee);
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "employeeDtlPage";
	}

	public EmployeePortal getEmployeePortal() {
		return employeePortal;
	}

	public void setEmployeePortal(EmployeePortal employeePortal) {
		this.employeePortal = employeePortal;
	} 
}
