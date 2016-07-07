package org.paradyne.model.attendance;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.attendance.AutoPresentAttendance;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

public class AutoPresentAttendanceModel extends ModelBase {

	
			public void addEmployeeList(AutoPresentAttendance bean,
			HttpServletRequest request) {
				try {
		
		String query="  SELECT AUTO_PRESENT_EMP_ID,"
					+" EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) empname,RANK_NAME ,"
					+" DECODE(AUTO_PRESENT_LATE_MARK,'Y','Yes','N','No'),DECODE(AUTO_PRESENT_HALF_DAY,'Y','Yes','N','No'),"
					+" DECODE(AUTO_PRESENT_ABSENT,'Y','Yes','N','No')"
					+" ,AUTO_PRESENT_LATE_MARK,AUTO_PRESENT_HALF_DAY, AUTO_PRESENT_ABSENT "
					+" FROM HRMS_AUTO_PRESENT "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID) "
					+" left JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK =HRMS_RANK.RANK_ID) "
					+" ORDER BY UPPER(empname)";
		Object obj[][] = getSqlModel().getSingleResult(query);
		String[] pageIndex = Utility.doPaging(bean.getMyPage(),obj.length,20);	
		if(pageIndex==null){
			pageIndex[0] = "0";
			pageIndex[1] ="20";
			pageIndex[2] = "1";
			pageIndex[3] = "1";
			pageIndex[4] = "";
		}
		
		request.setAttribute("totalPage", Integer.parseInt(String.valueOf(pageIndex[2])));
		request.setAttribute("pageNo", Integer.parseInt(String.valueOf(pageIndex[3])));
		
		
		
		 if(pageIndex[4].equals("1"))
			   bean.setMyPage("1");
		  			
			
			ArrayList<Object> list = new ArrayList<Object>();
			if (obj != null && obj.length > 0) {

			//	for (int i = 0; i < obj.length; i++) {
				 for(int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++){

					 AutoPresentAttendance locbean = new AutoPresentAttendance();
					 
					 locbean.setLempId(String.valueOf(obj[i][0]));
					 locbean.setEmpToken(String.valueOf(obj[i][1]));
					 locbean.setEmpName(String.valueOf(obj[i][2]));
					 locbean.setEmpDesg(String.valueOf(obj[i][3]));					 
					 locbean.setLwaiveOffLate(String.valueOf(obj[i][4]));
					 locbean.setLwaiveOffHalfday(String.valueOf(obj[i][5]));
					 locbean.setLwaiveOffAbsent(String.valueOf(obj[i][6]));
							

					list.add(locbean);
				}
				
				 bean.setTotalRecords(""+obj.length);
			}
			else{
				bean.setNoData("true");
				bean.setListLength(false);
				
			}
			bean.setEmpList(list);
			bean.setEmpListLength(String.valueOf(list.size()));
		    if(list.size()>0)
		    	bean.setListLength(true);
		    else
		    	bean.setListLength(false);
		
		   
			} catch (Exception e) {
				
			}
		
	}

			public boolean save(AutoPresentAttendance bean) {
				boolean result=false;
				try {
					Object add[][] = new Object[1][4];
					add[0][0] = bean.getEmpId();
					
					add[0][1] = bean.getWaiveOffLate();
					add[0][2] = bean.getWaiveOffHalfday();
					add[0][3] = bean.getWaiveOffAbsent();
					
				/*	if(bean.getWaiveOffLate().equals("true"))
					add[0][1] = "Y";
					else
						add[0][1] = "N";
					if(bean.getWaiveOffHalfday().equals("true"))
						add[0][2] = "Y";
					else
						add[0][2] = "N";
					if(bean.getWaiveOffAbsent().equals("true"))
						add[0][3] = "Y";
					else
						add[0][3] = "N";*/
					
					for (int i = 0; i < 4; i++) {
						System.out.println("row...!"+i+"  "+add[0][i]);
					}
					String query = "insert into HRMS_AUTO_PRESENT(AUTO_PRESENT_EMP_ID, AUTO_PRESENT_LATE_MARK, AUTO_PRESENT_HALF_DAY, AUTO_PRESENT_ABSENT) values(?,?,?,?)";
					result = getSqlModel().singleExecute(query, add);
					
					return result;
				} catch (Exception e) {
					
					return result;
				}
				
			}
			
			
			public boolean update(AutoPresentAttendance bean) {
				boolean result=false;
				try {
					Object mod[][] = new Object[1][4];
					
					mod[0][0] = bean.getWaiveOffLate();
					mod[0][1] = bean.getWaiveOffHalfday();
					mod[0][2] = bean.getWaiveOffAbsent();
					
					/*if(bean.getWaiveOffLate().equals("true"))
						mod[0][0] = "Y";
						else
							mod[0][0] = "N";
						if(bean.getWaiveOffHalfday().equals("true"))
							mod[0][1] = "Y";
						else
							mod[0][1] = "N";
						if(bean.getWaiveOffAbsent().equals("true"))
							mod[0][2] = "Y";
						else
							mod[0][2] = "N";
					*/
					
					mod[0][3] = bean.getEmpId();
					
					for (int i = 0; i < 4; i++) {
						System.out.println("row...!"+i+"  "+mod[0][i]);
					}
					
					String query = "update HRMS_AUTO_PRESENT set   AUTO_PRESENT_LATE_MARK=?, AUTO_PRESENT_HALF_DAY=?,AUTO_PRESENT_ABSENT=? " +
							" where AUTO_PRESENT_EMP_ID=? ";
					result = getSqlModel().singleExecute(query,mod);
					return result;
				} catch (Exception e) {
					
					return result;
				}
				
			}

			public void calforedit(AutoPresentAttendance bean) {
			
				String quey="  SELECT AUTO_PRESENT_EMP_ID,"
					+" EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) empname ,"
					+" AUTO_PRESENT_LATE_MARK,AUTO_PRESENT_HALF_DAY, AUTO_PRESENT_ABSENT,"
					+" DECODE(AUTO_PRESENT_LATE_MARK,'Y','Yes','N','No'),DECODE(AUTO_PRESENT_HALF_DAY,'Y','Yes','N','No'),"
					+" DECODE(AUTO_PRESENT_ABSENT,'Y','Yes','N','No')"
					+" FROM HRMS_AUTO_PRESENT "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID) "
					+" left JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK =HRMS_RANK.RANK_ID) "
					+" where AUTO_PRESENT_EMP_ID="+bean.getHiddencode();
				Object obj[][] = getSqlModel().getSingleResult(quey);
				
				if (obj!=null && obj.length==1) {
					
					 bean.setEmpId(String.valueOf(obj[0][0]));
					 bean.setEToken(String.valueOf(obj[0][1]));
					 bean.setEmpName(String.valueOf(obj[0][2]));									 
					 bean.setWaiveOffLate(String.valueOf(obj[0][3]));
					 bean.setWaiveOffHalfday(String.valueOf(obj[0][4]));
					 bean.setWaiveOffAbsent(String.valueOf(obj[0][5]));
					 bean.setIsNewrecord(String.valueOf(obj[0][0]));
				}
				
			}
			
			public void data(AutoPresentAttendance bean) {
				
				String quey="  SELECT AUTO_PRESENT_EMP_ID,"
					+" EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) empname ,"
					+" AUTO_PRESENT_LATE_MARK,AUTO_PRESENT_HALF_DAY, AUTO_PRESENT_ABSENT,"
					+" DECODE(AUTO_PRESENT_LATE_MARK,'Y','Yes','N','No'),DECODE(AUTO_PRESENT_HALF_DAY,'Y','Yes','N','No'),"
					+" DECODE(AUTO_PRESENT_ABSENT,'Y','Yes','N','No')"
					+" FROM HRMS_AUTO_PRESENT "
					+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID) "
					+" left JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK =HRMS_RANK.RANK_ID) "
					+" where AUTO_PRESENT_EMP_ID="+bean.getEmpId();
				
				System.out.println("--->"+bean.getEmpId());
				Object obj[][] = getSqlModel().getSingleResult(quey);
				
				if (obj!=null && obj.length==1) {
					
					 bean.setEmpId(String.valueOf(obj[0][0]));
					 bean.setEToken(String.valueOf(obj[0][1]));
					 bean.setEmpName(String.valueOf(obj[0][2]));									 
					 bean.setWaiveOffLate(String.valueOf(obj[0][3]));
					 bean.setWaiveOffHalfday(String.valueOf(obj[0][4]));
					 bean.setWaiveOffAbsent(String.valueOf(obj[0][5]));
					 bean.setIsNewrecord(String.valueOf(obj[0][0]));
				}
				
			}
				
			
			
			
			public boolean delete(AutoPresentAttendance bean) {
				String query="Delete FROM HRMS_AUTO_PRESENT where AUTO_PRESENT_EMP_ID=?";
				Object[][] delete = new Object[1][1];
				delete[0][0] = bean.getEmpId();
				return getSqlModel().singleExecute(query,delete);

			}

			/*
			 * deleting data from list
			 */
			public boolean delete1(AutoPresentAttendance bean, String[] code) {
               
				String query="Delete FROM HRMS_AUTO_PRESENT where AUTO_PRESENT_EMP_ID=?";
				boolean result = false;
				boolean flag = false;
				int cnt = 0;
				if (code != null) {
					for (int i = 0; i < code.length; i++) {

						if (!code[i].equals("")) {
							System.out.println(" into delete<----------------checkkkkkkkkkkkkkkkkkkkkkkk----------------------->"
											+ code[i]);
							Object[][] delete = new Object[1][1];
							delete[0][0] = code[i];
							flag = getSqlModel().singleExecute(query, delete);
							if (!flag) {
								cnt++;
							}
							
						}//end of nested if
					}//end of loop
				}//end of if
				if (cnt > 0) {
					result = false;
				}//end of if
				else
					result = true;
				return result;

			}
			public void getReport(HttpServletRequest request, HttpServletResponse response, AutoPresentAttendance bean, String[] headers) {
				
				System.out.println("getReport");
				try {
					String s = "\nAttendance AutoPresent\n\n";
					org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(
							"Pdf", s);
					String query = "  SELECT rownum,"
							+ " EMP_TOKEN, (EMP_FNAME || ' ' || EMP_MNAME || ' ' || EMP_LNAME) empname,RANK_NAME ,"
							+ " DECODE(AUTO_PRESENT_LATE_MARK,'Y','Yes','N','No'),DECODE(AUTO_PRESENT_HALF_DAY,'Y','Yes','N','No'),"
							+ " DECODE(AUTO_PRESENT_ABSENT,'Y','Yes','N','No')"
							//	+" ,AUTO_PRESENT_LATE_MARK,AUTO_PRESENT_HALF_DAY, AUTO_PRESENT_ABSENT "
							+ " FROM HRMS_AUTO_PRESENT "
							+ " INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_AUTO_PRESENT.AUTO_PRESENT_EMP_ID) "
							+ " left JOIN HRMS_RANK ON(HRMS_EMP_OFFC.EMP_RANK =HRMS_RANK.RANK_ID) "
							+ " ORDER BY UPPER(empname)";
					Object data[][] = getSqlModel().getSingleResult(query);
					System.out.println("1111");
					
					
					String dateQuery = "SELECT TO_CHAR(SYSDATE,'DD-MM-YYYY') FROM DUAL";
					Object[][] date = getSqlModel().getSingleResult(dateQuery);
					
					System.out.println("2222");
					rg.addFormatedText(s, 6, 0, 1, 0);
					rg.addText("Date: " + String.valueOf(date[0][0]), 0, 2, 0);
					rg.addText("\n", 0, 2, 0);
					int bcellWidth[] = { 10, 15, 30, 20, 10, 10, 10 };
					int bcellAlign[] = { 1, 0, 0,0, 1, 1, 1 };
					/*String appCol[] = { "Sr no", "Employee Id",
							"Employee Name", "Designation", "Waiveoff late",
							"Waiveoff Halfday", "Waiveoff Absent" };*/
					if (data != null && data.length > 0)
						{
						for (int i = 0; i < data.length; i++) {
							data[i][0]=String.valueOf(""+(i+1));
						}
						rg.tableBody(headers, data, bcellWidth, bcellAlign);
						
						}
					else
						rg.addFormatedText("NoRecords Available", 6, 0, 1, 0);
					
				System.out.println("1  "+data.length);
					rg.createReport(response);
					
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}
			}
		

}
