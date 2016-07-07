package org.paradyne.model.admin.srd;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.paradyne.bean.admin.srd.OfficialDetail;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;
import org.paradyne.model.common.ApplCodeTemplateModel;
import org.xhtmlrenderer.css.style.derived.StringValue;
public class OfficialDetailModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(OfficialDetailModel.class);
	org.paradyne.bean.admin.srd.OfficialDetail offDetail = null;
	AuditTrail trial=null;
	/**
	 * Method to add official details of an Employee.
	 * @param bean
	 * @param request 
	 * @return String
	 */
	public boolean addOffDtl(OfficialDetail bean, HttpServletRequest request) {
		Object addObj[][] = new Object[1][24];
        String str=null;
        boolean flag;
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE	**/
		trial.initiate(context, session);
		//trial.beginTrail();
		if (bean.getTitle().equals("null")) {
			addObj[0][0] = "";
		}// end of if
		else {
			addObj[0][0] = bean.getTitle();
		}// end of else
		addObj[0][1] = bean.getFirstName();
		addObj[0][2] = bean.getMiddleName().trim();
		addObj[0][3] = bean.getLastName().trim();
		addObj[0][4] = bean.getGender();
		addObj[0][5] = bean.getBirthDate().trim();
		addObj[0][6] = bean.getCenterCode();// Branch

		if (bean.getDeptCode().equals("null")) {
			addObj[0][7] = "";
		}// end of if
		else {
			addObj[0][7] = bean.getDeptCode();
		}// end of else

		if (bean.getDivCode().equals("null")) {
			addObj[0][8] = "";
		}// end of if
		else {
			addObj[0][8] = bean.getDivCode();
		}// end of else

		addObj[0][9] = bean.getRankCode();// .getDesgCode();//eMP RANK
		//addObj[0][10] = bean.getUploadFileName();

		if (bean.getCadreCode().equals("null")) {

			addObj[0][10] = "";
		}// end of if
		else {
			addObj[0][10] = bean.getCadreCode();// Grade
		}// end of else

		if (bean.getTradeCode().equals("null")) {
			addObj[0][11] = "";
		}// end of if
		else {
			addObj[0][11] = bean.getTradeCode();
		}// end of else

		if (bean.getShiftCode().equals("null")) {
			addObj[0][12] = "";

		}// end of if
		else {
			addObj[0][12] = bean.getShiftCode();
		}// end of else
		addObj[0][13] = bean.getStatus();
		addObj[0][14] = bean.getRegularDate().trim();
		addObj[0][15] = bean.getLeaveDate().trim();
		addObj[0][16] = bean.getLastIncrDt().trim();
		if (bean.getReportingID().equals("null")) {
			addObj[0][17] = "";
		}// end of if
		else {
			addObj[0][17] = bean.getReportingID();
		}// end of else
		addObj[0][18] = bean.getEmpToken().trim();
		addObj[0][19] = bean.getTypeCode();
		addObj[0][20] = bean.getPayBillId();
		addObj[0][21] = bean.getConfirmDate().trim();
		addObj[0][22] = bean.getGroupjoinDate().trim();
		addObj[0][23] = bean.getRoleName().trim();
		String autoGenTokenQuery=" SELECT * FROM HRMS_APPL_CODE_TEMPLATE WHERE APPL_TYPE='EmpId'";
		Object[][]isAutoGenTokenQuery=getSqlModel().getSingleResult(autoGenTokenQuery);
		logger.info("isAutoGenTokenQuery.length : "+isAutoGenTokenQuery.length);
		boolean result=false;
		if(isAutoGenTokenQuery!=null && isAutoGenTokenQuery.length>0){
			Date datetime = new Date();
			SimpleDateFormat formaterTime = new SimpleDateFormat("yyMMddHHmmss");
			String sysDateTime = formaterTime.format(datetime);
			addObj[0][18] =sysDateTime;
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			if (result) {
				String query1 = "SELECT MAX(EMP_ID) FROM HRMS_EMP_OFFC";
				Object[][] data = getSqlModel().getSingleResult(query1);
				bean.setEmpCode(String.valueOf(data[0][0]));
			}
			Date date = new Date();
			SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
			String sysDate = formater.format(date);
			ApplCodeTemplateModel tempModel = new ApplCodeTemplateModel();
			tempModel.initiate(context, session);
			//UPDATE EMP_TOKEN
			String token ="";
			token = tempModel.generateApplicationCode("", "EmpId", bean.getEmpCode(), sysDate);
			logger.info("token   : "+token);
			String updateQuery = "UPDATE HRMS_EMP_OFFC SET EMP_TOKEN ='"+token+"' WHERE EMP_ID="+bean.getEmpCode();
			getSqlModel().singleExecute(updateQuery);
		}
		else if(!bean.getEmpToken().trim().equals("")){
			logger.info("token   : "+bean.getEmpToken());
			result = getSqlModel().singleExecute(getQuery(1), addObj);
			if (result) {
				String query1 = "SELECT MAX(EMP_ID) FROM HRMS_EMP_OFFC";
				Object[][] data = getSqlModel().getSingleResult(query1);
				bean.setEmpCode(String.valueOf(data[0][0]));
			}
		}else{
			logger.info("in else");
			//return "Please enter Employee Id";
		}
		if (result) {
			
			trial.setParameters("HRMS_EMP_OFFC", new String[] { "EMP_ID" },
					new String[] { bean.getEmpCode() }, bean.getEmpCode());
	
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeADDTrail(request);
			 flag=true;
			
		} 
		else
		{
	        //str="Employee ID Already Exist"; 
	        flag=false;// end of if
		}
         return flag;
	}

	/**
	 * Method to modify official details of an Employee.
	 * 
	 * @param bean
	 * @return boolean
	 */
	public boolean modOffDtl(OfficialDetail bean,HttpServletRequest request) {
		/*"UPDATE HRMS_EMP_OFFC SET EMP_TITLE_CODE=?,EMP_FNAME=?,EMP_MNAME=?,EMP_LNAME=?,EMP_GENDER=?,EMP_DOB=TO_DATE(?,'DD-MM-YYYY'),"
		+ " EMP_RANK=?,  EMP_CENTER=?,EMP_TRADE=?,EMP_TYPE=?,EMP_CADRE=?,EMP_SHIFT=?,EMP_STATUS=?,EMP_REGULAR_DATE=TO_DATE(?,'DD-MM-YYYY'),EMP_TOKEN=?,  "
		+ " EMP_LEAVE_DATE=TO_DATE(?,'DD-MM-YYYY'),EMP_REPORTING_OFFICER=?,EMP_INCR_DATE=TO_DATE(?,'DD-MM-YYYY'),"
		+ " EMP_PHOTO= ? ,EMP_DEPT=?,EMP_DIV= ?,EMP_PAYBILL= ?,EMP_CONFIRM_DATE=TO_DATE(?,'DD-MM-YYYY'),"
		+ " EMP_GROUP_JOIN_DATE =TO_DATE(?,'DD-MM-YYYY'),EMP_ROLE= ? WHERE EMP_ID=? ";
		*/
		boolean result=false;
		String str=null;
		try {
			Object addObj[][] = new Object[1][25];
			trial = new AuditTrail(bean.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session);
			trial.setParameters("HRMS_EMP_OFFC", new String[] { "EMP_ID" },
					new String[] { bean.getEmpCode() }, bean.getEmpCode());
			trial.beginTrail();
			if (bean.getTitle().equals("null")) {
				addObj[0][0] = "";
			}// end of if
			else {
				addObj[0][0] = bean.getTitle();
			}// end of else
			addObj[0][1] = bean.getFirstName().trim();
			addObj[0][2] = bean.getMiddleName().trim();
			addObj[0][3] = bean.getLastName().trim();
			addObj[0][4] = bean.getGender();
			addObj[0][5] = bean.getBirthDate();
			addObj[0][6] = bean.getRankCode();
			addObj[0][7] = bean.getCenterCode();
			if (bean.getTradeCode().equals("null")) {
				addObj[0][8] = "";
			}// end of if
			else {
				addObj[0][8] = bean.getTradeCode();
			}// end of else
			if (bean.getTypeCode().equals("null")) {
				addObj[0][9] = "";
			}// end of if
			else {
				addObj[0][9] = bean.getTypeCode();
			}// end of else
			if (bean.getCadreCode().equals("null")) {
				addObj[0][10] = "";
			}// end of if
			else {
				addObj[0][10] = bean.getCadreCode();
			}// end of else
			if (bean.getShiftCode().equals("null")) {
				addObj[0][11] = "";
			}// end of if
			else {
				addObj[0][11] = bean.getShiftCode();
			}// end of else
			addObj[0][12] = bean.getStatus();
			addObj[0][13] = bean.getRegularDate().trim();
			addObj[0][14] = bean.getEmpToken().trim();
			addObj[0][15] = bean.getLeaveDate().trim();
			if (bean.getReportingID().equals("null")) {
				addObj[0][16] = "";
			}// end of if
			else {
				addObj[0][16] = bean.getReportingID();
			}// end of else
			addObj[0][17] = bean.getLastIncrDt().trim();
			/*if (bean.getUploadFileName().equals("null")) {
				addObj[0][18] = "";
			}// end of if
			else {
				addObj[0][18] = bean.getUploadFileName();
			}// end of else
*/			if (bean.getDeptCode().equals("null")) {
				addObj[0][18] = "";
			}// end of if
			else {
				addObj[0][18] = bean.getDeptCode();
			}// end of else
			if (bean.getDivCode().equals("null")) {
				addObj[0][19] = "";
			}// end of if
			else {
				addObj[0][19] = bean.getDivCode();
			}// end of else
			if (bean.getPayBillId().equals("null")) {
				addObj[0][20] = "";
			}// end of if
			else {
				addObj[0][20] = bean.getPayBillId();
			}// end of else
			addObj[0][21] = bean.getConfirmDate().trim();
			addObj[0][22] = bean.getGroupjoinDate().trim();			
			addObj[0][23] = bean.getRoleName();
			addObj[0][24] = bean.getEmpCode();
			result = getSqlModel().singleExecute(getQuery(2), addObj);
			/**	AUDIT TRAIL	EXECUTION **/
			trial.executeMODTrail(request);
			/*if(result){
			 str="Record updated successfully";	
			}else
			{
				str="Employee Id already exists";
			}*/
		} catch (Exception e) {
			logger.error("Exception in modOffDtl---------------"+e);
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Method to replace the null with a space.
	 * 
	 * @param result
	 * @return String
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/**
	 * Method to set the form fields.
	 * 
	 * @return Object
	 * @throws Exception
	 */
	public OfficialDetail getRecord(OfficialDetail offDetail) {
		try {
			Object addObj[] = new Object[1];
			if(offDetail.getEmpCode()==null || offDetail.getEmpCode().equals("")){
				offDetail.setEmpCode(offDetail.getUserEmpId());	
			}
			addObj[0] = offDetail.getEmpCode();
			Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
			Object[][] ph = getSqlModel().getSingleResult(getQuery(4), addObj);
			Object[][] data1 = getSqlModel().getSingleResult(getQuery(5), addObj);
			offDetail.setEmpCode(String.valueOf(data[0][0]));
			offDetail.setEmpToken(String.valueOf(data[0][1]));
			offDetail.setTitleName(String.valueOf(data[0][2]));
			offDetail.setFirstName(String.valueOf(data[0][3]));
			offDetail.setMiddleName(String.valueOf(data[0][4]).trim());
			offDetail.setLastName(String.valueOf(data[0][5]).trim());
			String gender=String.valueOf(data[0][6]);
		
			if(gender.equals("M")){
				offDetail.setGenderProperty("Male");// gender="Male";
			}else if(gender.equals("F")){
				offDetail.setGenderProperty("Female"); //gender="Female";
			}else if(gender.equals("O")){
				offDetail.setGenderProperty("Other"); //gender="Other";
			}
		    offDetail.setGender(String.valueOf(data[0][6]).trim());	
			offDetail.setBirthDate(String.valueOf(data[0][7]).trim());
			offDetail.setDivCode(checkNull(String.valueOf(data[0][8])));
			offDetail.setDivName(String.valueOf(data[0][9]));
			offDetail.setDeptCode(checkNull(String.valueOf(data[0][10])));
			offDetail.setDeptName(checkNull(String.valueOf(data[0][11])));
			offDetail.setCenterCode(checkNull(String.valueOf(data[0][12])));
			offDetail.setCenterName(String.valueOf(data[0][13]));
			offDetail.setRankCode(checkNull(String.valueOf(data1[0][6])));
			offDetail.setRankName(String.valueOf(data1[0][7]));
			String empName=String.valueOf(data[0][2])+ " "+String.valueOf(data[0][3])+" "+String.valueOf(data[0][5]);
			offDetail.setEmpName(empName);
			if (String.valueOf(ph[0][0]).equals("null")) {
				offDetail.setUploadFileName("");
			}// end of if
			else {
				offDetail.setUploadFileName(String.valueOf(ph[0][0]));
			}// end of else

			//offDetail.setStatus(String.valueOf(ph[0][1]));
				String status=String.valueOf(data[0][23]);
			
			if(status.equals("S")){
				offDetail.setStatusProperty("Service");
			}else if(status.equals("R")){
			    offDetail.setStatusProperty("Retired");
			}else if(status.equals("N")){
				offDetail.setStatusProperty("Resigned");
			}else if(status.equals("E")){
				offDetail.setStatusProperty("Terminated");
			}else if(status.equals("A")){
				offDetail.setStatusProperty("Absconding");
			}
		
			offDetail.setStatus(String.valueOf(data[0][23]).trim());	
			offDetail.setCadreCode(String.valueOf(data1[0][0]));
			offDetail.setCadreName(String.valueOf(data1[0][1]));
			offDetail.setTradeCode(String.valueOf(data1[0][4]));
			offDetail.setTradeName(String.valueOf(data1[0][5]));
			offDetail.setShiftCode(checkNull(String.valueOf(data1[0][2])));
			offDetail.setShiftType(String.valueOf(data1[0][3]));
			offDetail.setRegularDate(String.valueOf(data[0][24]).trim());
			offDetail.setLeaveDate(String.valueOf(data[0][25]).trim());
			offDetail.setLastIncrDt(String.valueOf(data[0][26]).trim());
			offDetail.setReportingID(checkNull(String.valueOf(data[0][27])));
			offDetail.setReportingTo(checkNull(String.valueOf(data[0][28])));
			offDetail.setTitle(String.valueOf(data[0][29]));
			offDetail.setConfirmDate(checkNull(String.valueOf(data[0][32])));//confirmation date
			offDetail.setTypeCode(checkNull(String.valueOf(data1[0][8])));
			offDetail.setTypeName(String.valueOf(data1[0][9]));
			offDetail.setPayBillId(String.valueOf(ph[0][2]));
			offDetail.setPayBillName(String.valueOf(ph[0][3]));
			
			/**
			 * SET SERVICE TENURE33
			 */
			//IN SERVICE EMPLOYEE
			if(String.valueOf(ph[0][1]).equals("S")){
				if(String.valueOf(data[0][34]).trim().equals("1")){
					offDetail.setServiceTenureValue(checkNull(String.valueOf(data[0][33])));
				}
				else{
					offDetail.setServiceTenureValue("0 Months 0 Years");	
				}
				
			}
			else{
				String serTenv=" select TO_CHAR(HRMS_EMP_OFFC.EMP_LEAVE_DATE,'DD-MM-YYYY'),  FLOOR(MONTHS_BETWEEN(EMP_LEAVE_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12)||' '||'Years'||' '|| 						 FLOOR(MONTHS_BETWEEN(EMP_LEAVE_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)-FLOOR(MONTHS_BETWEEN(EMP_LEAVE_DATE,HRMS_EMP_OFFC.EMP_REGULAR_DATE)/12) *12) ||' '||'Months' AS EXPERRIENCE "
                                +"  from hrms_emp_offc where emp_id = "+offDetail.getEmpCode()+" AND EMP_LEAVE_DATE IS NOT NULL ";
				Object[][]tenureData=getSqlModel().getSingleResult(serTenv);
				if(tenureData !=null && tenureData.length>0){
					offDetail.setServiceTenureValue(checkNull(String.valueOf(tenureData[0][1])));
				}
				else{
					offDetail.setServiceTenureValue("");
				}
			}
			offDetail.setGroupjoinDate(checkNull(String.valueOf(data[0][35])));
			offDetail.setRoleName(checkNull(String.valueOf(data[0][36])));
			
		}// end of try
		catch (Exception e) {
		}// end of catch

		return offDetail;
	}// end of getRecord

	/**
	 * 
	 * @param no
	 * @return String
	 */
	public String getSuffix(String no) {
		String Suffix[] = { "A", "B", "F", "H", "K", "N", "R", "T", "W", "Y",
				"Z" };
		int sfx = 0;
		int rmndr = 0;
		// SUFFIX CALCULATION
		for (int i = 0; i < no.length(); i++) {
			int tmp = Integer.parseInt("" + no.charAt(i));
			switch (i) {
			case 0:
				sfx += tmp * 5;
				break;
			case 1:
				sfx += tmp * 4;
				break;
			case 2:
				sfx += tmp * 3;
				break;
			case 3:
				sfx += tmp * 2;
				break;
			}// end of switch
		}// end of for loop
		rmndr = sfx % 11;
		return Suffix[rmndr];
	}

	/**
	 * Method to get the Employee Token.
	 * 
	 * @param offDetail
	 */
	public void getToken(OfficialDetail offDetail) {
		String empType = offDetail.getTypeCode();
		String SQL = "";
		String TOKEN = "";
		String newToken = "";
		String prefix = "";
		String suffix = "";
		if (!empType.equals("3")) {
			SQL = "select max(to_number(SUBSTR(EMP_TOKEN,2,4))) from hrms_emp_offc where EMP_TOKEN like '"
					+ empType.trim() + "%' order by emp_token";
			prefix = empType;
		}// end of if
		else if (empType.equals("3")
				&& (offDetail.getRankCode().equals("83") || offDetail
						.getRankCode().equals("84"))) {
			SQL = "select max(to_number(SUBSTR(EMP_TOKEN,2,4))) from hrms_emp_offc where EMP_TOKEN like '9%' order by emp_token";
			prefix = "9";
		}// end of else if
		else if (empType.equals("3")
				&& !(offDetail.getRankCode().equals("83") || offDetail
						.getRankCode().equals("84"))) {
			SQL = "select max(to_number(SUBSTR(EMP_TOKEN,2,4))) from hrms_emp_offc where EMP_TOKEN like '8%' order by emp_token";
			prefix = "8";
		}// end of else if
		Object data[][] = getSqlModel().getSingleResult(SQL);
		try {
			if (data != null && data.length > 0) {
				int maxToken = Integer.parseInt(String.valueOf(data[0][0]));
				maxToken++;
				String token = "" + maxToken;
				int len = token.length();
				switch (len) {
				case 1:
					newToken = "000" + token;
					break;
				case 2:
					newToken = "00" + token;
					break;
				case 3:
					newToken = "0" + token;
					break;
				default:
					newToken = "" + token;
				}// end of switch
			}// end of if
			else
				newToken = "0000";
		}// end of try
		catch (Exception e) {
			e.printStackTrace();
			newToken = "0000";
		}// end of catch

		suffix = getSuffix(newToken);
		TOKEN = prefix + "" + newToken + "" + suffix;
		offDetail.setEmpToken(TOKEN);
	}

	/**
	 * Method to display menu names
	 * 
	 * @param paraFrofilecode
	 */
	public void showHRM(String paraFrofilecode) {
		String Query = " SELECT DISTINCT NVL(HRMS_MENU_CLIENT.MENU_NAME,HRMS_MENU.MENU_NAME),  " +
				" CASE WHEN HRMS_MENU.MENU_LINK IS NOT NULL THEN  HRMS_MENU.MENU_LINK ELSE HRMS_MENU.MENU_LINK END AS HRLINK "
				+ "	,HRMS_MENU.MENU_CODE, MENU_IMAGE,HRMS_MENU.MENU_PLACEINMENU FROM HRMS_MENU  "
				+ " LEFT JOIN HRMS_MENU_CLIENT on(HRMS_MENU_CLIENT.MENU_CODE =HRMS_MENU.MENU_CODE) "
				+ "	INNER JOIN HRMS_PROFILE_DTL ON (HRMS_MENU.MENU_CODE = HRMS_PROFILE_DTL.MENU_CODE   and "
				+ "	HRMS_PROFILE_DTL.PROFILE_CODE IN("
				+ paraFrofilecode
				+ ") AND ( PROFILE_INSERT_FLAG='Y'  OR PROFILE_UPDATE_FLAG ='Y'  "
				+ "	OR PROFILE_DELETE_FLAG ='Y' OR PROFILE_VIEW_FLAG ='Y'  OR PROFILE_GENERAL_FLAG ='Y')) "
				+ "	 where HRMS_MENU.MENU_GROUP='Employee Information' AND HRMS_MENU.MENU_TYPE='HP' AND MENU_ISRELEASED='Y'   ORDER BY HRMS_MENU.MENU_PLACEINMENU,HRMS_MENU.MENU_CODE  ";
		Object hrmData[][] = getSqlModel().getSingleResult(Query);
		String[][] strObj = new String[hrmData.length][4];
		for (int i = 0; i < hrmData.length; i++) {

			try {
				strObj[i][1] = String.valueOf(hrmData[i][1]);
				strObj[i][0] = String.valueOf(hrmData[i][0]);
				strObj[i][2] = String.valueOf(hrmData[i][2]);
				strObj[i][3]= String.valueOf(hrmData[i][3]);
			}// end of try
			catch (Exception e) {
				strObj[i][0] = String.valueOf(hrmData[i][0]);
				strObj[i][1] = "";
				strObj[i][2] = String.valueOf(hrmData[i][2]);
				strObj[i][3]=String.valueOf(hrmData[i][3]);
			}// end of catch

		}// end of for loop
		session.setAttribute("hrmData", strObj);
	}
	
	/*//Added by janisha for D1 field Integration
	public Object[][] getFilters( OfficialDetail offDetail) {
		Object[][] FiltersObj = null;
		boolean value=false;
		System.out.println(offDetail.getDivisionCode()+"---offDetail.getUserProfileDivision()--00jjjj");
		
		try {
			Object[][] data=null;
			Object [][]data1=new Object[1][1];
			String selectDivision="select EMP_DIV from hrms_emp_offc "
				+ " where EMP_ID="+offDetail.getEmpCode();
			data=getSqlModel().getSingleResult(selectDivision);
			if(data!=null && data.length>0){
				data1[0][0]=String.valueOf(data[0][0]);
			}
			
			
			String d1filterSql = " SELECT CONF_DIV_CODE,DECODE(CONF_SSN_FLAG, 'Y', 'true','N','false') AS SSN_FLAG, "
				+ "	 DECODE(CONF_SIN_FLAG, 'Y', 'true', 'N', 'false') AS SIN_FLAG, "
				+ "	 DECODE(CONF_DEPTNO_FLAG, 'Y', 'true', 'N', 'false') AS DEPTNO_FLAG, "
				+ "	 DECODE(CONF_REG_FLAG, 'Y', 'true', 'N', 'false') AS REG_FLAG, " 
				+ "	 DECODE(CONF_EXE_FLAG, 'Y', 'true', 'N', 'false') AS EXECUTIVE_FLAG, " 
				+ "  DECODE(CONF_DIV_FLAG, 'Y', 'true', 'N', 'false') AS DIV_FLAG "
				+"  FROM HRMS_D1_OFFICE_CONF ";
			
			
			//if(offDetail.getUserProfileDivision() !=null && offDetail.getUserProfileDivision().length()>0)
				d1filterSql += " where CONF_DIV_CODE IN (" + data1[0][0]+")";
				
			FiltersObj = getSqlModel().getSingleResult(
					d1filterSql);
		} catch (Exception e) {
			logger.error("Exception in getFilters:" + e);
		}
		return FiltersObj;
	}
	//End added by janisha for D1 integration field
	 */
	public void getProfileEmpName(OfficialDetail offDetail){
	try {
		 //String EmpId=offDetail2.getEmpCode();
		 
	    //offDetail2.setProfileEmpId(EmpId);
	    
	    //System.out.println("Profile Emp Id :----->"+offDetail2.getProfileEmpId());
		String query="select NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+offDetail.getEmpCode();
		
		Object[][] myPics = getSqlModel().getSingleResult(query);
			
		offDetail.setProfileEmpName( String.valueOf(myPics[0][0])+" "+String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2]));
		
		System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+offDetail.getUploadFileName());
	
	} catch (Exception e) {
		e.printStackTrace();
	}	
	}
	
}
