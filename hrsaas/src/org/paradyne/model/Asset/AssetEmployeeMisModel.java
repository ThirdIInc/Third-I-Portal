
package org.paradyne.model.Asset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.AssetEmployeeMis;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj
 * Date 13/08/2008
 *AssetEmployeeMisModel class to write the business logic to view the Report
 * regarding the assignment of the assets
 */
public class AssetEmployeeMisModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);




	/*public void getReport(HttpServletRequest request,
			HttpServletResponse response,AssetEmployeeMis bean){
		String returnFlag="";
		if(bean.getStatus().equals("Y"))
			returnFlag="Yes";
		else returnFlag="No";
		String query="SELECT ROWNUM,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),ASST_INCODE ,ASSET_CATEGORY_NAME ,TO_CHAR(ASSIGN_DATE,'DD-MM-YYYY'),TO_CHAR(RETURN_DATE,'DD-MM-YYYY')," 
		+ " DECODE(RETURN_FLAG,'Y','YES','N','NO') FROM HRMS_ASST_ASSMT"
		+ " INNER JOIN HRMS_ASST_MT ON(HRMS_ASST_MT.ASST_CODE=HRMS_ASST_ASSMT.ASST_CODE)" 
		+ " INNER JOIN HRMS_ASSMT ON(HRMS_ASSMT.ASSMT_CODE=HRMS_ASST_ASSMT.ASSMT_CODE)"
		+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSMT.EMP_CODE)"
		+ " INNER JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)"
		+" INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASST_MT.ASST_HD_CODE) WHERE 1=1 ";

		String s="\nASSET TO EMPLOYEE MIS REPORT\n\n";
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator("Pdf",s);
		Object data[][]=null;
		try{

			if(!bean.getEmpCode().equals("")&& bean.getFrmDate().equals("") && bean.getStatus().equals("")&& bean.getToDate().equals("")){
					data=new Object[1][2];
				data[0][0]="Employee Name :"+ bean.getEname();
				query+= " AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+")";

			}else if(!bean.getFrmDate().equals("") && bean.getEmpCode().equals("")&& bean.getStatus().equals("") ){
				data=new Object[1][2];
				data[0][0]="Assigned From Date : "+bean.getFrmDate();

				String da="";
				if(!bean.getToDate().equals("")){
					da+=bean.getToDate();
					query+= " AND ASSIGN_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
					+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')";
				}
				else 
					{
					da+=bean.getToday();
					data[0][1]="";
					query+= " AND ASSIGN_DATE >= TO_DATE('" + bean.getFrmDate()
						+ "','DD-MM-YYYY')";
					}
			}else if(!bean.getFrmDate().equals("") && ! bean.getEmpCode().equals("") && bean.getStatus().equals("")){
				data=new Object[2][2];
				data[0][0]="Employee Name:"+bean.getEname();

				data[1][0]="Assigned From Date : "+bean.getFrmDate();


				String da="";

				if(!bean.getToDate().equals(""))
				da+=bean.getToDate();
				else da+=bean.getToday();
				data[1][1]="To Date :"+da;

				query+=" AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND ASSIGN_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
						+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')";
			}else if(!bean.getStatus().equals("") && bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals("")){
				data=new Object[1][2];
				data[0][0]="Return Flag : "+returnFlag;

				query+=" AND RETURN_FLAG='"+bean.getStatus()+"'";

			}else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals("")){
				data=new Object[1][2];
				data[0][0]="Employee Name : "+bean.getEname();

				data[0][1]="Return Flag : "+returnFlag;

				query+= " AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND RETURN_FLAG='"+bean.getStatus()+"'";

			}else if(!bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals("")){
				data=new Object[2][2];
				data[0][0]="Return Flag : "+returnFlag;
				data[1][0]="Assigned From Date : "+bean.getFrmDate();


				String da="";

				if(!bean.getToDate().equals(""))
				da+=bean.getToDate();
				else da+=bean.getToday();
				data[1][1]="To Date :"+da;

				query+=" AND ASSIGN_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
						+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY') AND RETURN_FLAG='"+bean.getStatus()+"'";
			}else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals("")){
				data=new Object[2][2];
				data[0][0]="Employee Name : "+bean.getEname();

				data[0][1]="Return Flag : "+returnFlag;

				data[1][0]="Assigned From Date : "+bean.getFrmDate();

				String da="";


				if(!bean.getToDate().equals(""))
				da+=bean.getToDate();
				else da+=bean.getToday();
				data[1][1]="To Date :"+da;

				query+=" AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND ASSIGN_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')AND RETURN_FLAG='"+bean.getStatus()+"'";
			}else if(!bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && bean.getStatus().equals("")){
				data=new Object[1][2];
				data[0][0]="Employee Name : "+bean.getEname();
				data[0][1]="Up to Date : "+bean.getToDate();

				query+=" AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND ASSIGN_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;

			}else if(bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && ! bean.getStatus().equals("")){
				data=new Object[1][2];
				data[0][0]="Return Flag : "+returnFlag;
				data[0][1]="Up to Date : "+bean.getToDate();
				query+=" AND RETURN_FLAG='"+bean.getStatus()+"' AND ASSIGN_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;

			}else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals("")){
				data=new Object[2][2];
				data[0][0]="Employee Name : "+bean.getEname();

				data[0][1]="Return Flag : "+returnFlag;

				data[1][0]="Up to Date :"+bean.getToDate();
				query+= " AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND RETURN_FLAG='"+bean.getStatus()+"' AND ASSIGN_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;

			}else if(bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals("")){
				data=new Object[1][2];
				data[0][0]="Up to Date :"+bean.getToDate();

				query+= " AND ASSIGN_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;

			}

				int [] bcellWidth={50,50};
				int [] bcellAlign={0,0};
				Object tab[][]=getSqlModel().getSingleResult(query);
				rg.addFormatedText(s, 6, 0, 1, 0);
				rg.addText("Date: "+bean.getToday(), 0, 2, 0);
				rg.addFormatedText("", 0, 0, 1, 0);
				rg.addFormatedText("", 0, 0, 1, 0);
				rg.tableBodyNoBorder(data,bcellWidth,bcellAlign);
				if(tab!=null && tab.length==0){

					rg.addFormatedText("No Records to Display", 1, 0, 1, 3);

				}
				else{
				//logger.info("inside else length="+tab.length);
				int cellwidth[]={5,30,15,15,12,12,10};
				int alignment[]={1,0,0,0,1,1,0};
				rg.addFormatedText("Asset Details :", 6, 0, 0, 0);
				rg.addText("\n", 0, 1, 0);
				String colnames[]={"Sr. No.","Employee Name","Inventory Code","Asset Type","Assigned Date","Returned Date","Return Flag"};
				rg.tableBody(colnames,tab, cellwidth, alignment);
				}
				}catch (Exception e) {
					e.printStackTrace();
				}
				rg.createReport(response);
	}*/

	/* 
	 * method name : getReport 
	 * purpose     : get the report in the PD format according to the filters selected
	 * return type : void
	 * parameter   : HttpServletRequest request,
			HttpServletResponse response,AssetEmployeeMis instance
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response,AssetEmployeeMis bean){
		String returnFlag="";
		Object [][]heading=new Object[1][1];
		int []cells={25};
		int []align={0};

		if(bean.getStatus().equals("Y"))
			returnFlag="Available";
		else if(bean.getStatus().equals("D"))
			returnFlag="Damaged";
		else if(bean.getStatus().equals("L"))
			returnFlag="Lost";
		else returnFlag="Assigned";

		String query="SELECT ROWNUM,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||" 
			+" HRMS_EMP_OFFC.EMP_LNAME), TO_CHAR(ASSET_APPL_DATE,'DD-MM-YYYY'),"
			+" "
			+" NVL(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_NAME,' '),NVL(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_NAME,' '), NVL(ASS.ASSET_INVENTORY_CODE,' '),"

			+" DECODE(ASSET_STATUS,'P','PENDING','A','APPROVED','S','ASSIGNED'), "
			+" CASE WHEN ASSET_STATUS='S' AND  ASS.ASSET_RETURN_FLAG='' THEN 'NOT RETURNED'  ELSE "  
			+" NVL(DECODE(ASS.ASSET_RETURN_FLAG,'Y','AVAILABLE','N','ASSIGNED','L','LOST','D','DAMAGED'),' ')  END , "
			+" NVL(TO_CHAR(ASS.ASSET_RETURN_DATE,'DD-MM-YYYY'),' ') "
			+" FROM HRMS_ASSET_APPLICATION  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_APPLICATION.ASSET_EMP_ID)" 
			+" INNER JOIN HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE) "
			+" LEFT JOIN HRMS_ASSET_APP_ASSIGNEMENT  ASS ON(ASS.ASSET_APPL_CODE=HRMS_ASSET_APPLICATION.ASSET_APPL_CODE)"
			//+" INNER JOIN HRMS_ASSET_APPL_DETAILS ON(HRMS_ASSET_APPLICATION.ASSET_APPL_CODE =HRMS_ASSET_APPL_DETAILS.ASSET_APPL_CODE)"
			+" INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE =ASS.ASSET_CATEGORY_CODE)"

			+" INNER JOIN HRMS_ASSET_SUBTYPE ON(HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE =ASS.ASSET_SUBTYPE_CODE) "
			+" WHERE 1=1 ";

		String s="\nASSET ASSIGNMENT MIS REPORT\n\n";
		org.paradyne.lib.report.ReportGenerator rg=new ReportGenerator(bean.getType(),s,"A4");
		Object data[][]=null;
		try{
			/*
			 * add the conditions according to the filters selected & add the filters into
			 * the object to show in the PDF report
			 */
			if(!bean.getEmpCode().equals("")&& bean.getFrmDate().equals("") && bean.getStatus().equals("")&& bean.getToDate().equals(""))
			{
				data=new Object[1][2];
				data[0][0]="Employee Name :"+ bean.getEname();
				query+= " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID="+ bean.getEmpCode();

			}  // end of if
			else if(!bean.getFrmDate().equals("") && bean.getEmpCode().equals("")&& bean.getStatus().equals("") )
			{
				data=new Object[1][2];
				data[0][0]="Application From Date : "+bean.getFrmDate();

				String da="";
				if(!bean.getToDate().equals("")){
					da+=bean.getToDate();
					query+= " AND ASSET_APPL_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
					+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')";
				}  // end of if
				else 
				{
					da+=bean.getToday();
					data[0][1]="";
					query+= " AND ASSET_APPL_DATE >= TO_DATE('" + bean.getFrmDate()
					+ "','DD-MM-YYYY')";
				}  // end of else 
			}  // end of else if
			else if(!bean.getFrmDate().equals("") && ! bean.getEmpCode().equals("") && bean.getStatus().equals(""))
			{
				data=new Object[2][2];
				data[0][0]="Employee Name:"+bean.getEname();

				data[1][0]="Application From Date : "+bean.getFrmDate();


				String da="";

				if(!bean.getToDate().equals(""))
					da+=bean.getToDate();
				else da+=bean.getToday();
				data[1][1]="To Date :"+da;

				query+=" AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID=" + bean.getEmpCode()+" AND ASSET_APPL_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')";
			}  // end of else if
			else if(!bean.getStatus().equals("") && bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals(""))
			{
				data=new Object[1][2];
				data[0][0]="Return Flag : "+returnFlag;

				query+=" AND ass.ASSET_RETURN_FLAG='"+bean.getStatus()+"'";

			}  // end of else if
			else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& bean.getToDate().equals(""))
			{
				data=new Object[1][2];
				data[0][0]="Employee Name : "+bean.getEname();

				data[0][1]="Return Flag : "+returnFlag;

				query+= " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID=" + bean.getEmpCode()+" AND ass.ASSET_RETURN_FLAG='"+bean.getStatus()+"'";

			}  // end of else if
			else if(!bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals(""))
			{
				data=new Object[2][2];
				data[0][0]="Return Flag : "+returnFlag;
				data[1][0]="Application From Date : "+bean.getFrmDate();


				String da="";

				if(!bean.getToDate().equals(""))
					da+=bean.getToDate();
				else da+=bean.getToday();
				data[1][1]="To Date :"+da;

				query+=" AND ASSET_APPL_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY') AND ass.ASSET_RETURN_FLAG='"+bean.getStatus()+"'";
			}  // end of else if
			else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals(""))
			{
				data=new Object[2][2];
				data[0][0]="Employee Name : "+bean.getEname();

				data[0][1]="Return Flag : "+returnFlag;

				data[1][0]="Application From Date : "+bean.getFrmDate();

				String da="";


				if(!bean.getToDate().equals(""))
					da+=bean.getToDate();
				else da+=bean.getToday();
				data[1][1]="To Date :"+da;

				query+=" AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID=" + bean.getEmpCode()+" AND ASSET_APPL_DATE BETWEEN TO_DATE('" + bean.getFrmDate()
				+ "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')AND ass.ASSET_RETURN_FLAG='"+bean.getStatus()+"'";
			}  // end of else if
			else if(!bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && bean.getStatus().equals(""))
			{
				data=new Object[1][2];
				data[0][0]="Employee Name : "+bean.getEname();
				data[0][1]="Up to Date : "+bean.getToDate();

				query+=" AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID=" + bean.getEmpCode()+" AND ASSIGN_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;

			}  // end of else if
			else if(bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&& bean.getFrmDate().equals("") && ! bean.getStatus().equals(""))
			{
				data=new Object[1][2];
				data[0][0]="Return Flag : "+returnFlag;
				data[0][1]="Up to Date : "+bean.getToDate();
				query+=" AND ass.ASSET_RETURN_FLAG='"+bean.getStatus()+"' AND ASSET_APPL_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;

			}  // end of else if
			else if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals(""))
			{
				data=new Object[2][2];
				data[0][0]="Employee Name : "+bean.getEname();

				data[0][1]="Return Flag : "+returnFlag;

				data[1][0]="Up to Date :"+bean.getToDate();
				query+= " AND HRMS_ASSET_APPLICATION.ASSET_EMP_ID=" + bean.getEmpCode()+" AND ass.ASSET_RETURN_FLAG='"+bean.getStatus()+"' AND ASSET_APPL_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;

			}  // end of else if
			else if(bean.getStatus().equals("") &&  bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& ! bean.getToDate().equals(""))
			{
				data=new Object[1][2];
				data[0][0]="Up to Date :"+bean.getToDate();

				query+= " AND ASSET_APPL_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;

			}  // end of else if

			int [] bcellWidth={50,50};
			int [] bcellAlign={0,0};
			Object tab[][]=getSqlModel().getSingleResult(query);
			rg.addFormatedText(s, 6, 0, 1, 0);


			if(!bean.getType().equalsIgnoreCase("Xls")){
				logger.info("not xls report....!!!");
				rg.addText("Date: "+bean.getToday(), 0, 2, 0);
				rg.addFormatedText("", 0, 0, 1, 0);
				rg.addFormatedText("", 0, 0, 1, 0);
				if(data!=null && data.length!=0)
					rg.tableBodyNoBorder(data,bcellWidth,bcellAlign);
			}  // end of if
			else 
			{
				logger.info(" xls report....!!!");
				rg.addText("ASSET ASSIGNMENT MIS REPORT", 0, 1, 0);
				rg.addText("", 0,  1, 0);
				rg.addText("Date: "+bean.getToday(), 0, 2, 0);
				if(!bean.getToDate().equals(""))
					rg.addText("To date:"+bean.getToDate(), 0, 1, 0);
				if(!bean.getFrmDate().equals(""))
					rg.addText("From date:"+bean.getFrmDate(), 0, 1, 0);
				if(!bean.getEmpCode().equals(""))
					rg.addText("Employee Name :"+ bean.getEname(), 0, 1, 0);
				if(!bean.getStatus().equals(""))
					rg.addText("Return Flag : "+returnFlag, 0, 1, 0);
			}  // end of else 




			if(tab!=null && tab.length==0){
				if(!bean.getType().equalsIgnoreCase("Xls"))
					rg.addFormatedText("No Records to Display", 1, 0, 1, 3);
				else
					rg.addText("No Records to Display",  0, 2, 0);

			}  // end of if
			else{
				int cellwidth[]={7,20,15,15,12,12,12,12,12};
				int alignment[]={1,0,0,0,0,0,0,0,0};
				if(!bean.getType().equalsIgnoreCase("Xls")){
					heading[0][0]="Asset Details :";
					rg.tableBodyBold(heading, cells, align) ;
					rg.addFormatedText("", 1, 0, 2, 3);
				}  // end of if
				else
				{
					rg.addText("Asset Details :",  0, 1, 0);
				}  // end of else 
				String colnames[]={"Sr. No.","Employee Name","Application Date","Category","Sub Type","Inventory Code","Status","Return Flag","Returned Date"};
				/*
				 * add the retrieved data into the table format with column names
				 * 
				 */
				rg.tableBody(colnames,tab, cellwidth, alignment);					
			}  // end of else
		}catch (Exception e) {
			e.printStackTrace();
		}
		rg.createReport(response);
	}



	/* method name : checkNull 
	 * purpose     : to check the null value
	 * return type : String
	 * parameter   : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}
