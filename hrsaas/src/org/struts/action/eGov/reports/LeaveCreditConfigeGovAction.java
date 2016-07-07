package org.struts.action.eGov.reports;
import java.util.ArrayList;

import org.paradyne.bean.eGov.reports.LeaveCreditConfigeGovBean;
import org.paradyne.bean.leave.LeavePolicy;
import org.paradyne.model.D1.BusinessUnitModel;
import org.paradyne.model.eGov.reports.LeaveCreditConfigeGovModel;
import org.paradyne.model.leave.LeavePolicyModel;



public class LeaveCreditConfigeGovAction extends org.struts.lib.ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LeaveCreditConfigeGovAction.class);
	LeaveCreditConfigeGovBean bean ;

	
	public LeaveCreditConfigeGovBean getBean() {
                return bean;
        }
        public void setBean(LeaveCreditConfigeGovBean bean) {
                this.bean = bean;
        }
        
        public Object getModel() {
                return bean;
        }
        
        public String input() throws Exception {
       
        System.out.println("inside input------------------------------->");	
        LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
        model.initiate(context, session);
		bean.setEnableAll("N");
		model.leaveCreditData(bean, request);
		
		model.terminate();
		
		getNavigationPanel(1);
		return "input";
	}
	public void prepare_local() throws Exception {
		bean = new LeaveCreditConfigeGovBean();
		bean.setMenuCode(2100);
	}

	/**
	 * called on load to set the list
	 */
	
	/*
	public void prepare_withLoginProfileDetails() throws Exception {
	        LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
		model.initiate(context, session);
		model.leaveCreditData(bean, request);
		model.terminate();
	}*/

	/**
	 * @param divMast the divMast to set
	 */
	/*public void setBean(LeaveCreditConfigeGovBean bean) {
		this.bean = bean;
		bean.setMenuCode(42);
		
	}*/
	
/**to display the save mode( next mode)
 * 
 */ 
	public String addNew() {
		try {
			reset();
			getNavigationPanel(2);
			return "showData";
		} catch(Exception e) {
			logger.error("Exception in addNew in action:" + e);
			return null;
		}
	}
	
	public String reset()
	{
		bean.setLeaveConfigId("");
		bean.setLeaveName("");
		bean.setDebitAbbr("");
		bean.setDebitCode("");
		bean.setDebitName("");
		
		getNavigationPanel(2);
		return "showData";
		
	}
	
	
	/**
	 * To edit any record in the list by double clicking on it
	 * @return String
	 * @throws Exception
	 */
	public String calforedit() throws Exception {		
	        LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
		model.initiate(context, session);
		model.data(bean);
		//data();
		getNavigationPanel(3);
		bean.setEnableAll("N");
		model.terminate();
		return "showData";
	}

	
	/**
	 * To save the record
	 * @return String
	 * @throws Exception
	 */
	public String save() throws Exception {
	        LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
		model.initiate(context, session);
		
		boolean result;

		if(bean.getLeaveConfigId().equals("")) {
			logger.info("else addDiv");
			result = model.insertRecord(bean);
			if(result) {
				addActionMessage(getMessage("save"));
				getNavigationPanel(3);
				return "showData";
			} else {
				addActionMessage(getMessage("duplicate"));
				//reset();
				getNavigationPanel(1);
				return "input";
			}// end of else
		} else {
			result = model.updateRecord(bean);
			System.out.println("------RESULT--------------"+result);
			model.terminate();
			if(result) {
				addActionMessage(getMessage("update"));
				getNavigationPanel(3);
				return "showData";
			} else {
				addActionMessage(getMessage("duplicate"));
				reset();// new added
				getNavigationPanel(1);
				return "input";
			}// end of else
		} // end of else
		
			
		
	}
	
	 /**
         * @return String f9page
         */
        public String f9leaveType() {
                try {
                        String query = " SELECT HRMS_LEAVE.LEAVE_ID,HRMS_LEAVE.LEAVE_NAME FROM HRMS_LEAVE WHERE HRMS_LEAVE.LEAVE_ID NOT IN(SELECT LEAVE_CODE FROM HRMS_LEAVE_CREDIT_CONFIG )  ORDER BY HRMS_LEAVE.LEAVE_ID ";

                        String[] headers = { getMessage("leav.id"),getMessage("leave.name")};

                        String[] headerWidth = { "20","80"};

                        String[] fieldNames = {"leaveId","leaveName"};

                        int[] columnIndex = {0, 1};

                        String submitFlag = "false";

                        String submitToMethod = "";

                        setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

                        return "f9page";
                } catch(Exception e) {
                      e.printStackTrace();
                        return null;
                } // end of try-catch block
        }
        public String getLeaveHead()
        {
                
                /*try {
                         
                        LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
                        model.initiate(context, session);
                        model.debitHeadData(bean, request);
                        model.terminate();
                         
                         
                } catch (Exception e) {
                        e.printStackTrace();
                }*/
                
                try {
                        LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
                       // String hCode = request.getParameter("hCode");
                       // bean.setHLeaveCode(hCode);
                        model.initiate(context, session);
                        String query = " SELECT CREDIT_CODE, CREDIT_ABBR FROM HRMS_CREDIT_HEAD WHERE CREDIT_TYPE='1'  ORDER BY HRMS_CREDIT_HEAD.CREDIT_CODE";
                        Object obj[][] = model.getSqlModel().getSingleResult(query);
                        ArrayList<Object> arr = new ArrayList<Object>();
                        for (int i = 0; i < obj.length; i++) {
                                // if(!hCode.equals(String.valueOf(obj[i][0]))){
                                LeaveCreditConfigeGovBean bean1 = new LeaveCreditConfigeGovBean();
                                bean1.setDebitCode(String.valueOf(obj[i][0]));// debit code
                                bean1.setDebitName(String.valueOf(obj[i][1]));// debit name
                                arr.add(bean1);
                                // }

                        }// end of for
                        bean.setArr(arr);
                        String str = request.getParameter("id");
                        String str1 = request.getParameter("id1");
                        bean.setFrmId(str);
                        bean.setHiddenfrmId(str1);
                        model.terminate();
                } catch (Exception e) {
                        logger.info("Exception in leaveConsecutive------- " + e);
                }
                
                
                return "leavehead";
        }
        
        public String back() throws Exception {
                input();
                             
                return INPUT;
        }
    
        /**
        * Added by Nilesh Start
        */ 

    	/**
    	 * method name : delete.
    	 * purpose - deleting particular record.
    	 * @return String.
    	 */
    	public String delete()  {
    		 LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
    		model.initiate(context, session);
    		
    		boolean result;
    		result = model.delete(bean);
    		model.leaveCreditData(bean, request);
    		model.terminate();

    		if (result) {
    			this.addActionMessage("Record Deleted successfully.");
    		}
    		getNavigationPanel(1);
    		return "input";
    	}
        
    	
    	/**
    	 * method name : deleteChkRecords.
    	 * purpose - deleting multiple records.
    	 * @return String.
    	 */
    	public String deleteChkRecords()  {
    		 String [] code = request.getParameterValues("hdeleteCode");
    		System.out.println("code values are -----------------------"+code );
    		 LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();

    		model.initiate(context, session);
    	
    		 boolean result = model.deleteCheckedRecords(bean, code);

    		if (result) {
    			addActionMessage(getText("delMessage", ""));
    			reset();
    		} else {
    			addActionMessage("One or more records can't be deleted \n as they are associated with some other records. ");
    		}

    		model.leaveCreditData(bean, request);
    	   
    		model.terminate();
    		 getNavigationPanel(1);
    		 
    		return "input";
    	}
    	
    	/**
    	 * purpose - Set Division.
    	 * @return f9page.
    	 */
    	public String f9leaveCredit()  {

    	      String  query = " SELECT HRMS_LEAVE.LEAVE_NAME, HRMS_CREDIT_HEAD.CREDIT_ABBR, HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID, HRMS_CREDIT_HEAD.CREDIT_CODE "
    							+ " FROM HRMS_LEAVE_CREDIT_CONFIG "
    							+ " LEFT JOIN HRMS_LEAVE ON (HRMS_LEAVE.LEAVE_ID = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CODE) "
    							+ " LEFT JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID)"
    							+	" ORDER BY HRMS_LEAVE_CREDIT_CONFIG.LEAVE_CONF_ID DESC ";
    	      
    	      final String[] headers = {getMessage("leave.type")};

    		final String[] headerWidth = {"50", "10"};

    		final String[] fieldNames = {"leaveName", "leaveConfigId"};

    		final int[] columnIndex = {0, 1};

    		final String submitFlag = "true";

    		final String submitToMethod = "LeaveCreditConfigeGov_setRecord.action";

    		this.setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);

    		return "f9page";

    	}		
    	
    	
    	/**method name : setRecord.
    	 * purpose - Set search record.
    	 * @return String.
    	 */
    	public String setRecord() throws Exception {
    	try {
    		 LeaveCreditConfigeGovModel model = new LeaveCreditConfigeGovModel();
			model.initiate(context, session);
			model.display(bean);
			model.terminate();
			this.getNavigationPanel(3);

		} 
    	catch (Exception e) {
			e.printStackTrace();
		}

		getNavigationPanel(3);
		bean.setEnableAll("N");
		return "showData";
	}
    	
     /**
      * Added by Nilesh END
      */   
        
        
        
        
        
}