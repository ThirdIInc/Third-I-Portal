package org.paradyne.model.event;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.paradyne.bean.event.CeoMessageList;
import org.paradyne.lib.MailUtility;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.model.admin.srd.SendMailModel;

public class CeoMessageListModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(CeoMessageListModel.class);

	public void getAllTypeOfMessages(CeoMessageList bean,
			HttpServletRequest request, String empId) {
		try {
			Object pendingData[][] =null;
			 boolean flag = getCeoEmployeId(bean);
		      String pendinMessageQuery = "";
		      String empDivQuery = " SELECT CEO_DIVISION FROM HRMS_CEO_CONFIG WHERE EMP_ID= "
					+ empId;
		      Object loginDiv[][]=getSqlModel().getSingleResult(empDivQuery);
		      if (flag) {
				pendinMessageQuery = " SELECT DTL_CODE , HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,"
									+ " HRMS_CEO_MESSAGES.EMP_ID ,SUBJECT ,DESCRIPTION , TO_CHAR(MESSAGE_DATE,'DD-MM-YYYY'), "
									+ " STATUS ,SHOW_IDENTITY "
									+ " FROM HRMS_CEO_MESSAGES  "
									+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_CEO_MESSAGES.EMP_ID)  "
									+ " WHERE  STATUS=? AND EMP_DIV IN ("+loginDiv[0][0]+")ORDER BY MESSAGE_DATE DESC";
				Object pendingParam[] = null;
				pendingParam = new Object[] { "P" };
				pendingData = getSqlModel().getSingleResult(pendinMessageQuery,
						pendingParam);

			}
			if (pendingData != null && pendingData.length > 0) {

				bean.setPendingLength("true");
				bean.setTotalPendingRecords(String.valueOf(pendingData.length));
				String[] pageIndex = Utility.doPaging(bean.getMyPage(),
						pendingData.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2]))); // to set the total number of page
				request.setAttribute("pageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));// to set the page number
				if (pageIndex[4].equals("1")){
					bean.setMyPageInProcess("1");
				}
				ArrayList pendingList = new ArrayList();
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					CeoMessageList bean1 = new CeoMessageList();					
					if(!String.valueOf(pendingData[i][8]).equals("Y")){
						bean1.setTokenNo(String.valueOf(pendingData[i][1]));
						bean1.setEmpName(String.valueOf(pendingData[i][2]));
					}
					else{
						bean1.setEmpName("Anonymous");
					}					
					bean1.setMessageNo((String.valueOf(pendingData[i][0])));
					bean1.setEmpCode(String.valueOf(pendingData[i][3]));
					bean1.setDate(String.valueOf(pendingData[i][6]));
					bean1.setPendingStatus(String.valueOf(pendingData[i][7]));

					// token
					pendingList.add(bean1);
				}
				bean.setPendingList(pendingList);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getApprovedList(CeoMessageList bean,
			HttpServletRequest request, String empId) {
		try {
			Object acceptedParam[] = null;
			boolean falg = getEmployeId(bean);
		      String acceptedMessageQuery = "";
		      Object acceptedData[][]=null;
		      if (falg) {
		        acceptedMessageQuery = " SELECT DTL_CODE , HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME,  " 
		        		+" HRMS_CEO_MESSAGES.EMP_ID ,SUBJECT ,DESCRIPTION , TO_CHAR(MESSAGE_DATE,'DD-MM-YYYY'),STATUS ,SHOW_IDENTITY " 
		        		+" FROM HRMS_CEO_MESSAGES  "
		        		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_CEO_MESSAGES.EMP_ID) " 
		        		+" WHERE STATUS=? ORDER BY MESSAGE_DATE DESC ";
		        acceptedParam = new Object[] { "A" };
				acceptedData= getSqlModel().getSingleResult(
						acceptedMessageQuery, acceptedParam);		
		      }
			bean.setTotalApprovedRecords(String.valueOf(acceptedData.length));
			String[] pageIndexApproved = Utility.doPaging(bean
					.getMyPageApproved(), acceptedData.length, 20);
			if (pageIndexApproved == null) {
				pageIndexApproved[0] = "0";
				pageIndexApproved[1] = "20";
				pageIndexApproved[2] = "1";
				pageIndexApproved[3] = "1";
				pageIndexApproved[4] = "";
			}
			request.setAttribute("totalPageApproved", Integer.parseInt(String
					.valueOf(pageIndexApproved[2])));
			request.setAttribute("PageNoApproved", Integer.parseInt(String
					.valueOf(pageIndexApproved[3])));
			if (pageIndexApproved[4].equals("1"))
				bean.setMyPageApproved("1");
			if (acceptedData != null && acceptedData.length > 0) {
				ArrayList acceptedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexApproved[0]); i < Integer
						.parseInt(pageIndexApproved[1]); i++) {
					CeoMessageList bean1 = new CeoMessageList();
					
					if(!String.valueOf(acceptedData[i][8]).equals("Y"))
					{
						bean1.setAppEmpToken(String.valueOf(acceptedData[i][1]));
						bean1.setAppEmpName(String.valueOf(acceptedData[i][2]));
					}
					else{
						bean1.setAppEmpName("Anonymous ");

					}
					
					bean1.setAppMessageNo(String.valueOf(acceptedData[i][0]));
				
					bean1.setAppEmpId(String.valueOf(acceptedData[i][3]));
					bean1.setAppAppDate(String.valueOf(acceptedData[i][6]));
					bean1.setAppStatus(String.valueOf(acceptedData[i][7]));
					acceptedList.add(bean1);

				}
				bean.setAppList(acceptedList);
				bean.setApprovedLength("true");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getRejectedList(CeoMessageList bean,
			HttpServletRequest request, String empId) {
		// TODO Auto-generated method stub
		try {
			Object rejectedParam[] = null;
			boolean falg = getCeoEmployeId(bean);
		      String rejectedMessageQuery = "";
		      Object rejectedData[][] = null;
		      if (falg) {
		        rejectedMessageQuery = " SELECT DTL_CODE , HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME, " 
		        						+" HRMS_CEO_MESSAGES.EMP_ID ,SUBJECT ,DESCRIPTION , TO_CHAR(MESSAGE_DATE,'DD-MM-YYYY')," 
		        						+" STATUS ,SHOW_IDENTITY " 
		        						+" FROM HRMS_CEO_MESSAGES  "
		        						+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_CEO_MESSAGES.EMP_ID)  " 
		        						+" WHERE  STATUS=? ORDER BY MESSAGE_DATE DESC";
		        rejectedParam = new Object[] {"R"};
				rejectedData = getSqlModel().getSingleResult(
						rejectedMessageQuery, rejectedParam);
		      }
			bean.setTotalRejectedRecords(String.valueOf(rejectedData.length));
			String[] pageIndexRejected = Utility.doPaging(bean.getMyPageRejected(), rejectedData.length, 20);
			if (pageIndexRejected == null) {
				pageIndexRejected[0] = "0";
				pageIndexRejected[1] = "20";
				pageIndexRejected[2] = "1";
				pageIndexRejected[3] = "1";
				pageIndexRejected[4] = "";
			}

			request.setAttribute("totalPageRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[2])));
			request.setAttribute("PageNoRejected", Integer.parseInt(String
					.valueOf(pageIndexRejected[3])));
			if (pageIndexRejected[4].equals("1"))
				bean.setMyPageRejected("1");

			if (rejectedData != null && rejectedData.length > 0) {
				bean.setRejectedLength("true");
				ArrayList rejectedList = new ArrayList();
				for (int i = Integer.parseInt(pageIndexRejected[0]); i < Integer
						.parseInt(pageIndexRejected[1]); i++) {

					CeoMessageList leaveRejectedBean = new CeoMessageList();
					
					if(!String.valueOf(rejectedData[i][8]).equals("Y"))
					{
						leaveRejectedBean.setRejEmpToken(String
								.valueOf(rejectedData[i][1]));

						leaveRejectedBean.setRejEmpName(String
								.valueOf(rejectedData[i][2]));

					}
					else{
						leaveRejectedBean.setRejEmpName("Anonymous ");

					}
					leaveRejectedBean.setRejMessageNo((String
							.valueOf(rejectedData[i][0])));
 
					leaveRejectedBean.setRejEmpId(String
							.valueOf(rejectedData[i][3]));

					leaveRejectedBean.setRejAppDate(String
							.valueOf(rejectedData[i][6]));

					leaveRejectedBean.setRejStatus(String
							.valueOf(rejectedData[i][7]));

					rejectedList.add(leaveRejectedBean);

				}
				bean.setRejList(rejectedList);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void getDetails(CeoMessageList bean, String messageNo, String empCode) {
		try {
			String query = " SELECT HRMS_EMP_OFFC.EMP_TOKEN,EMP_FNAME||' '||EMP_MNAME||' '||EMP_LNAME "
					+ " ,DIV_NAME,DEPT_NAME,SUBJECT,DESCRIPTION,DECODE(SHOW_IDENTITY,'Y','YES','N','NO') "
					+ " ,DTL_CODE, HRMS_CEO_MESSAGES.EMP_ID "
					+ " FROM HRMS_CEO_MESSAGES "
					+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID = HRMS_CEO_MESSAGES.EMP_ID) "
					+ "  INNER JOIN HRMS_DIVISION ON(HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
					+ " INNER JOIN HRMS_DEPT ON( HRMS_DEPT.DEPT_ID = HRMS_EMP_OFFC.EMP_DEPT) "
					+ " WHERE DTL_CODE="+messageNo ;
			Object result[][] = getSqlModel().getSingleResult(query);
			if (result != null && result.length > 0) {
				
				
					if(!String.valueOf(result[0][6]).equals("YES"))
					{
						bean.setEmployeeToken(checkNull(String.valueOf(result[0][0])));
						bean.setEmployeeName(checkNull(String.valueOf(result[0][1])));
					}
					else{
						bean.setEmployeeName("Anonymous");
					}
				bean.setDivisionName(checkNull(String.valueOf(result[0][2])));
				bean.setDeptName(checkNull(String.valueOf(result[0][3])));
				bean.setSubjectName(checkNull(String.valueOf(result[0][4])));
				bean.setDescName(checkNull(String.valueOf(result[0][5])));
				bean.setShowIdentity(checkNull(String.valueOf(result[0][6])));
				bean.setHiddenMessageCode(checkNull(String.valueOf(result[0][7])));
				bean.setEmployeeCode(empCode);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}
	
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} // end of if
		else {
			return result;
		}// end of else
	}// end of checkNull

	public boolean acceptRejectMessage(CeoMessageList bean) {
		// TODO Auto-generated method stub
		boolean result = false; 
		try {
			 String updateQuery = " update HRMS_CEO_MESSAGES set STATUS='"+bean.getCheckAcceptRejectStatus()+"', MESG_ADMIN_ID="+bean.getUserEmpId()+", MESSAGE_APPROVED_DATE=sysdate where DTL_CODE="+bean.getHiddenMessageCode();
			 result = getSqlModel().singleExecute(updateQuery);
			 
			 if (result) {
					sendCeoMail(bean.getSubjectName(), bean.getDescName(),bean.getShowIdentity(),bean.getEmployeeCode());
				}

			 
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result ;
	}
	
	public String sendCeoMail(String subject, String message, String hideIdentity, String loginEmpId) {
		String result = "";

		try {
			
			String query = " SELECT HRMS_CEO_CONFIG.EMP_ID ,ADD_EMAIL ,emp_FNAME||' '||emp_lname FROM HRMS_CEO_CONFIG "
			+" inner join hrms_emp_offc on(hrms_emp_offc.emp_id=HRMS_CEO_CONFIG.emp_id) "
				+" INNER JOIN HRMS_EMP_ADDRESS ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_CEO_CONFIG.EMP_ID)"
			+" WHERE ISCEOORMSGADMIN='C'"
			+" AND ADD_TYPE='P'" ;
			
			Object dataObj[][] =getSqlModel().getSingleResult(query);
			
			if(dataObj!=null && dataObj.length >0)
			{
				for (int i = 0; i < dataObj.length; i++) {
					String[] to_Emailid = new String[1];
					to_Emailid[0]=String.valueOf(dataObj[i][1]) ;
					String[] sub = { "Message to CEO" };
					String[] msg = new String[1];
					String name=String.valueOf(dataObj[i][2]) ;
					String empName="";
					System.out.println("hideIdentity "+hideIdentity);
					if(hideIdentity.equals("YES"))
					{
						empName="Anonymous ";
					}
					else{
						String empNamequery = " select emp_FNAME||' '||emp_lname from hrms_emp_offc "
								+" where emp_id="+loginEmpId ;
						Object empNamequeryObj[][] =getSqlModel().getSingleResult(empNamequery);
						if(empNamequeryObj!=null && empNamequeryObj.length >0)
						{
							empName =String.valueOf(empNamequeryObj[0][0]);
						}
						
					}
					msg[0] = getMessage(subject, message,name,empName);
					MailUtility mod = new MailUtility();
					mod.initiate(context, session);
					
					mod.sendMail(to_Emailid, mod.getDefaultMailIds(), sub[0], msg[0],
							"", "", true);
					mod.terminate();
					
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	public String getMessage(String subject, String msg, String name, String empName) {
		String[] htmlText = new String[1];
		//Added by Prajakta B
		String companyName ="";
		String commpanyQuery="SELECT COMPANY_LOGO,NVL(COMPANY_DISPLAY_NAME,COMPANY_NAME),COMPANY_NAME FROM  HRMS_COMPANY";
		Object data[][]=getSqlModel().getSingleResult(commpanyQuery);
		if(data!=null && data.length>0){
			 companyName = String.valueOf(data[0][1]);
		}
		//Ends Added by Prajakta B
		String tempMsg = "";
		htmlText[0] = "<html> "
				+ "<style>"
				+ " .txt {font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12px; color: #000000; text-decoration: none; } "
				+ " .style13 { font-family: Verdana, Arial, Helvetica, sans-serif; font-size: 12px; color: #FFFFFF; text-decoration: none; font-weight: bold; } "
				+ " .birth { font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; }"
				+ ".style14 {font-family: Monotype Corsiva; font-size: 22px; font-style: italic; font-weight: bold; text-decoration: none; color: #FF6600; }"
				+ ".style15 {color: #CC0000} "
				+ ".style16 {color: #D23A49} "
				+ "</style>"
				+ "	<body> "
				+ "<table width='60%' border='0' cellpadding='0' cellspacing='0'>"
				+ "<tr> "
				+ "<td width='66%'>"
				+ "<table width='96%' border='0' cellpadding='2' cellspacing='2' class='border'> "
				+ "<tr> "
				+ "<td><p>Dear&nbsp;<b>"
				+ ""+name+""
				+ "</b>, </p><br /> "
				+ "a message to connect CEO has been forwarded to you.<br /> "
				+ "Message sent by :"+empName+".</p> "
				+ "<br /> Message: "
				+ msg
				+ "<br /><p>Thank you for using "+ companyName +" Employee Portal</p><br />"
				+ "</td> " + "</tr> " + "</table> " + "</td> " + "</tr> "

				+ "</table> " + "</body> " + "</html> ";
		SendMailModel model = new SendMailModel();
		model.initiate(context, session);
		tempMsg = model.getMassMessage(htmlText[0]);

		return tempMsg;

	}
	
	
	/** Method to select message Administrator
	 * @author Prajakta.Bhandare
	 * @param bean
	 * @return
	 */
	public boolean getCeoEmployeId(CeoMessageList bean)
	  {
	    boolean falg = false;
	    String str = "0";
	    try
	    {
	      String selectQuery = " select emp_id from HRMS_CEO_CONFIG WHERE ISCEOORMSGADMIN='M'";
	      Object[][] ceoEmployeeCodeObj = getSqlModel().getSingleResult(
	        selectQuery);
	      if ((ceoEmployeeCodeObj != null) && (ceoEmployeeCodeObj.length > 0)) {//if data
	        for (int i = 0; i < ceoEmployeeCodeObj.length; i++) {
	          str = str + "," + String.valueOf(ceoEmployeeCodeObj[i][0]);
	        }//end of for loop

	      }//end of if data

	      if (!str.equals("0")) {
	        String[] splitArr = str.split(",");

	        for (int i = 0; i < splitArr.length; i++) {
	          if (bean.getUserEmpId().equals(splitArr[i]))
	            falg = true;
	        }//end of for loop
	      }//end of  if
	    }
	    catch (Exception localException)
	    {
	    }
	    return falg;
	  }

	
	/** Method to select CEO and Message Administrator
	 * @param bean
	 * @return
	 */
	public boolean getEmployeId(CeoMessageList bean)
	  {
	    boolean falg = false;
	    String str = "0";
	    try
	    {
	      String selectQuery = " select emp_id from HRMS_CEO_CONFIG WHERE ISCEOORMSGADMIN='M' OR ISCEOORMSGADMIN='C'";
	      Object[][] ceoEmployeeCodeObj = getSqlModel().getSingleResult(
	        selectQuery);
	      if ((ceoEmployeeCodeObj != null) && (ceoEmployeeCodeObj.length > 0)) {//if data
	        for (int i = 0; i < ceoEmployeeCodeObj.length; i++) {
	          str = str + "," + String.valueOf(ceoEmployeeCodeObj[i][0]);
	        }//end of for loop

	      }//end of if data

	      if (!str.equals("0")) {
	        String[] splitArr = str.split(",");

	        for (int i = 0; i < splitArr.length; i++) {
	          if (bean.getUserEmpId().equals(splitArr[i]))
	            falg = true;
	        }//end of for loop
	      }//end of  if
	    }
	    catch (Exception localException)
	    {
	    }
	    return falg;
	  }

}
