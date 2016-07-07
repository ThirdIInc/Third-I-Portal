// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 10/07/2009 5:39:38 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) 
// Source File Name:   Form27Action.java

package org.struts.action.payroll.incometax;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.paradyne.bean.payroll.incometax.Form27A;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.payroll.incometax.Form27Model;
import org.struts.lib.ParaActionSupport;

// Referenced classes of package org.struts.action.payroll.incometax:
//            Form24Action

public class Form27Action extends ParaActionSupport
{
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
    public Form27Action()
    {
    }

    public void prepare_local()
        throws Exception
    {
        bean = new Form27A();
        bean.setMenuCode(727);
    }

    public Form27A getBean()
    {
        return bean;
    }

    public void setBean(Form27A bean)
    {
        this.bean = bean;
    }

    public Object getModel()
    {
        return bean;
    }

    public void prepare_withLoginProfileDetails() throws Exception {
    	Form27Model model = new Form27Model();
		model.initiate(context, session);
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
		String sysDate = formater.format(date);
		String split[] = sysDate.split("/");
		int currentMonth =  Integer.parseInt((split[1]));
		int year =  Integer.parseInt((split[2]));
		if(currentMonth<4){
			year = year -1;
		}
		
		double remianMonth=0;
		double month =  Double.parseDouble(split[1]);
		if(month>3 && month<=12){
			remianMonth = 15 - month;
		}//end of if
		else if(month<=3){
			remianMonth = 3 - month;
		}//end of else if
		model.terminate();
		
		bean.setFinFrmYr(String.valueOf(year));
		bean.setFinToYr(String.valueOf(year + 1));
	}


	/**
	 * Reset the fields of the form
	 * 
	 * @return String
	 */
	public String reset() throws Exception {
		bean.setDivId("");
		bean.setDivName("");
		bean.setSignAuthDesg("");
		bean.setSignAuthId("");
		bean.setSignAuthName("");
		bean.setEmpToken("");
		bean.setQuarter("");
		bean.setFinFrmYr("");
		bean.setFinToYr("");
		prepare_withLoginProfileDetails();
		return SUCCESS;

	}

    
    public String report()
        throws Exception
    {
        Form27Model model = new Form27Model();
        model.initiate(context, session);
        model.generateReport(bean, request, response, "");
        model.terminate();
        return null;
    }

    public String f9division()
        throws Exception
    {
    	String query = " SELECT DIV_ID,DIV_NAME FROM HRMS_DIVISION ";
		
		if(bean.getUserProfileDivision() !=null && bean.getUserProfileDivision().length()>0)
			query+= " WHERE DIV_ID IN ("+bean.getUserProfileDivision() +")"; 
			query+= " ORDER BY  UPPER(DIV_NAME) ";
        String headers[] = {
            "Division Code", "Division Name"
        };
        String headerWidth[] = {
            "10", "30"
        };
        String fieldNames[] = {
            "divId", "divName"
        };
        int columnIndex[] = {
            0, 1
        };
        String submitFlag = "false";
        String submitToMethod = "";
        setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
        return "f9page";
    }

    public String f9signAuthority()
        throws Exception
    {
        logger.info("In f9 action===========1");
        String query = " SELECT EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,' '),  NVL(RANK_NAME,' '),EMP_ID  FROM HRMS_EMP_OFFC  LEFT JOIN HRMS_RANK ON HRMS_RANK.RANK_ID = HRMS_EMP_OFFC.EMP_RANK ";
        String headers[] = {
            "Employee Id", "Employee Name", "Designation"
        };
        String headerWidth[] = {
            "15", "30", "20"
        };
        String fieldNames[] = {
            "empToken", "signAuthName", "signAuthDesg", "signAuthId"
        };
        int columnIndex[] = {
            0, 1, 2, 3
        };
        String submitFlag = "false";
        logger.info("In f9 action===========3");
        String submitToMethod = "";
        logger.info("In f9 action===========4");
        setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
        return "f9page";
    }

    Form27A bean;


	/**
	 * 
	 * Sending report as a mail attachment 
	 * @return String
	 */
	public final String mailReport() {
		try {
			final Form27Model model = new Form27Model();
			model.initiate(context, session);
			String poolName = String.valueOf(session.getAttribute("session_pool"));
			if (!(poolName.equals("") || poolName == null)) {
				poolName = "/" + poolName;
			}
			final String reportPath = getText("data_path") + "/Report/Payroll/" + 
					poolName + "/";
			model.generateReport(this.bean, request, response, reportPath);
			model.terminate();
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return "mailReport";
	}

}