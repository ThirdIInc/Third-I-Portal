package org.paradyne.model.admin.master;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.CrystalReport;
import org.paradyne.lib.report.ReportGenerator;
import org.paradyne.bean.Recruitment.QuestionnaireMaster;
import org.paradyne.bean.admin.master.CadreMaster;
import org.paradyne.bean.admin.master.DivisionMaster;
import org.paradyne.bean.admin.master.TradeMaster;

public class CadreModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(ModelBase.class);
	org.paradyne.bean.admin.master.CadreMaster cadMaster = null;

	/* for checking duplicate entry of record during insertion */
	public boolean checkDuplicate(CadreMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CADRE WHERE UPPER(CADRE_NAME) LIKE '"
				+ bean.getCadreName().trim().toUpperCase() + "'";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for checking duplicate entry of record during modification */

	public boolean checkDuplicateMod(CadreMaster bean) {
		boolean result = false;
		String query = "SELECT * FROM HRMS_CADRE WHERE UPPER(CADRE_NAME) LIKE '"
				+ bean.getCadreName().trim().toUpperCase()
				+ "' AND CADRE_ID  in(" + bean.getCadreID() + ")";
		Object[][] data = getSqlModel().getSingleResult(query);
		if (data != null && data.length > 0) {
			result = true;
		}// end of if
		return result;

	}

	/* for inserting record */
	public boolean addCadre(CadreMaster bean,String[] competency) {
		boolean result=false;
		
		
		String queryDup="SELECT CADRE_ID FROM  HRMS_CADRE where  UPPER(CADRE_NAME) LIKE '"+bean.getCadreName().trim().toUpperCase()+"'";
		Object[][]rel=getSqlModel().getSingleResult(queryDup);
		boolean testDupl=false;
		if(rel.length>0){
			bean.setCadreID(String.valueOf(rel[0][0]));
			testDupl= checkDuplicateMod(bean);
		}
		if(!testDupl){
			//System.out.println("---------------addCadre---------");
			String query="SELECT NVL(MAX(CADRE_ID),0)+1 FROM HRMS_CADRE";
			Object[][]id=getSqlModel().getSingleResult(query);
			bean.setCadreID(String.valueOf(id[0][0]));
			//add data in HRMS_CADRE
			Object addObj[][] = new Object[1][5];
			addObj[0][0] = bean.getCadreName().trim();
			addObj[0][1] = bean.getCadreDesc().trim();
			addObj[0][2] = bean.getCadreAbbr().trim();
			addObj[0][3] = bean.getCadreParID().trim();
			addObj[0][4] = bean.getCadreIsActive();
			result =getSqlModel().singleExecute(getQuery(1), addObj);
			if (result) {
				//result = addOption(bean, competency);
				//if(result){
					query="SELECT CADRE_ID FROM  HRMS_CADRE where CADRE_NAME='"+bean.getCadreName()+"'";
					Object[][]rel1=getSqlModel().getSingleResult(query);
					bean.setCadreID(String.valueOf(rel1[0][0]));
					//showRecords(bean);
				//}
			}
		}
		return result;

	}
	public boolean addOption(CadreMaster cadMaster, String[] competency) {
		boolean result = false;
		Object addObj[][] = new Object[1][2];
		if (competency != null) {
			for (int i = 0; i < competency.length; i++) {
				addObj[0][0] = cadMaster.getCadreID();
				addObj[0][1] = competency[i];
				result =getSqlModel().singleExecute(getQuery(7), addObj);
			}
		}
		return result;
	}

	/* for modifying the record */
	public boolean modCadre(CadreMaster bean,String[] competency) {
		boolean result=false;
		boolean result1;
		
		boolean testDupl=false;
		
		String query="SELECT CADRE_ID FROM  HRMS_CADRE where  UPPER(CADRE_NAME) LIKE '"+bean.getCadreName().trim().toUpperCase()+"'";
		Object[][]rel=getSqlModel().getSingleResult(query);
		
		String query1="SELECT CADRE_NAME FROM  HRMS_CADRE where  CADRE_ID ="+bean.getCadreID();
		Object[][]check=getSqlModel().getSingleResult(query1);
		
		if(check[0][0].equals(bean.getCadreName())){
			testDupl=false	;
		}
		else{		
		if(rel.length>0){
			bean.setCadreID(String.valueOf(rel[0][0]));
			testDupl= checkDuplicateMod(bean);
		}
		}
		
		
		if (!testDupl) {
			Object addObj[][] = new Object[1][6];
			addObj[0][0] = bean.getCadreName().trim();
			addObj[0][1] = bean.getCadreDesc().trim();
			addObj[0][2] = bean.getCadreAbbr().trim();
			addObj[0][3] = bean.getCadreParID().trim();
			addObj[0][4] = bean.getCadreIsActive();
			addObj[0][5] = bean.getCadreID();
			result= getSqlModel().singleExecute(getQuery(2), addObj);
			if (result) {
				String queryDel = "DELETE FROM HRMS_CADRE_DTL WHERE CADRE_ID= "+ bean.getCadreID();
			    result1 = getSqlModel().singleExecute(queryDel);	
				/*if(result1){
					result1 = addOption(bean, competency); 	
				  //showRecords(bean);
				}*/
			}
		}
		
		return result;
		
	}
	public void delDtl(CadreMaster bean, String[] code, String[] competency) {
		System.out.println("---------------delDtl----cadre------");
		try {
			ArrayList<Object> list = new ArrayList<Object>();

			if (competency != null) {
				for (int i = 0; i < competency.length; i++) {
					CadreMaster bean1 = new CadreMaster();
					if ((String.valueOf(code[i]).equals(""))) {
						bean1.setCompetency(competency[i]);
						list.add(bean1);

					}
				}
			}
			bean.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


	/* for deleting the record after selecting */

	public boolean deleteCadre(CadreMaster bean) {
		System.out.println("----------deleteCadre---------"+bean.getCadreID());
		boolean res;
		Object delObj[][] = new Object[1][1];
		delObj[0][0] = bean.getCadreID();
		//res=getSqlModel().singleExecute(getQuery(9), delObj);
		res=getSqlModel().singleExecute(getQuery(8), delObj);
		if (res)
			res= getSqlModel().singleExecute(getQuery(3), delObj);
		else
			return false;
		return res;
   }

	public void getCadreRecord(CadreMaster bean) {
		Object addObj[] = new Object[1];

		addObj[0] = bean.getCadreID();
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);

		for (int i = 0; i < data.length; i++) {
			//logger.info("I am 2");
			bean.setCadreID(String.valueOf(data[i][0]));
			bean.setCadreName(String.valueOf(data[i][1]));
			bean.setCadreParName(String.valueOf(data[i][2]));
			bean.setCadreDesc(String.valueOf(data[i][3]));
			bean.setCadreAbbr(String.valueOf(data[i][4]));
			bean.setCadreParID(String.valueOf(data[i][5]));
			bean.setCadreIsActive(String.valueOf(data[i][6]));
			//bean.setCompetency(String.valueOf(data[i][7]));
			//bean.setCompetencyCode(String.valueOf(data[i][8]));
		}// end of loop

	}

	public void getReport(CadreMaster bean) {

		Object data[][] = getSqlModel().getSingleResult(getQuery(5));
		ArrayList<Object> cardeList = new ArrayList<Object>();

		for (int i = 0; i < data.length; i++) {
			CadreMaster bean1 = new CadreMaster();
			bean1.setCadreID(checkNull(String.valueOf(data[i][0])));
			bean1.setCadreName(checkNull(String.valueOf(data[i][1])));
			bean1.setCadreDesc(checkNull(String.valueOf(data[i][2])));
			bean1.setCadreAbbr(checkNull(String.valueOf(data[i][3])));
			bean1.setCadreParID(checkNull(String.valueOf(data[i][4])));
			bean1.setCadreIsActive(checkNull(String.valueOf(data[i][5])));
			cardeList.add(bean1);

		}// end of loop
		bean.setCardeList(cardeList);

	}
	/**
	 *  to check null value
	 * @param result
	 * @return
	 */

	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		}// end of if
		else {
			return result;
		}// end of else
	}

	/* generating the report */

	public void getReport(CadreMaster tadist, HttpServletRequest request,
			HttpServletResponse response, ServletContext context,
			HttpSession session, String[] label) {
		/*CrystalReport cr = new CrystalReport();
		String path = "org/paradyne/rpt/admin/master/grade.rpt";
		cr.createReport(request, response, context, session, path, "");*/
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		String toDay = sdf.format(today);
		String reportName = "\n\nGrade Master\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator("Pdf",
				reportName);
		rg.setFName("Grade  Master.Pdf");
		String queryDes = "SELECT  CADRE_NAME,CADRE_DESC,CADRE_ABBR,CADRE_PARENT_ID,DECODE(CADRE_IS_ACTIVE,'Y','YES','N','NO') FROM HRMS_CADRE ORDER BY upper(CADRE_NAME)";
						
		Object[][] data = getSqlModel().getSingleResult(queryDes);
		Object[][] Data = new Object[data.length][6];
		if (data != null && data.length > 0) {
			int j = 1;
			for (int i = 0; i < data.length; i++) {
				Data[i][0] = j;
				Data[i][1] = data[i][0];
				Data[i][2] = data[i][1];
				Data[i][3] = data[i][2];
				Data[i][4] = data[i][3];
				Data[i][5] = data[i][4];
				j++;
			}
			int cellwidth[] = { 15, 40, 40, 30, 25, 20 };
			int alignment[] = { 1, 0, 0, 0,0,0};
			rg.addFormatedText(reportName, 6, 0, 1, 0);
			rg.addText("\n", 0, 0, 0);
			rg.addTextBold("Date :" + toDay, 0, 2, 0);
			rg.addText("\n", 0, 0, 0);
			rg.tableBody(label, Data, cellwidth, alignment);
		} else {
			rg.addFormatedText("There is no data to display.", 1, 0, 0, 0);
		}

		rg.createReport(response);

	}

	/*
	 * to generate the list onload
	 */
	public void gradeData(CadreMaster cadMaster, HttpServletRequest request) {
		System.out.println("===========gradeData========890=====");	
		Object[][] repData = getSqlModel().getSingleResult(getQuery(6));
		if(repData!=null && repData.length>0){
			cadMaster.setModeLength("true");
			cadMaster.setTotalRecords(String.valueOf(repData.length));
		
		String[] pageIndex = Utility.doPaging(cadMaster.getMyPage(), repData.length, 20);	
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
			cadMaster.setMyPage("1");
		ArrayList<Object> List = new ArrayList<Object>();
		for (int i=Integer.parseInt(pageIndex[0]);i<Integer.parseInt(pageIndex[1]);i++) { 
			CadreMaster bean1 = new CadreMaster();
			bean1.setCadreID(checkNull(String.valueOf(repData[i][0])));
			bean1.setCadreName(checkNull(String.valueOf(repData[i][1])));
			bean1.setCadreDesc(checkNull(String.valueOf(repData[i][2])));
			bean1.setCadreIsActive(checkNull(String.valueOf(repData[i][3])));

			List.add(bean1);
		}// end of loop

		cadMaster.setCardeList(List);
		}
	}

	/*
	 * selecting the data from list and setting those data in respective fields
	 */
	public void calforedit(CadreMaster cadMaster) {
		System.out.println("===========calforedit===890==========");
		String query = " SELECT CADRE_ID,CADRE_NAME ,CADRE_IS_ACTIVE FROM HRMS_CADRE where CADRE_ID= "
				+ cadMaster.getHiddencode() + "    ORDER BY CADRE_ID ";

		Object[][] data = getSqlModel().getSingleResult(query);
		cadMaster.setCadreID(String.valueOf(data[0][0]));
		cadMaster.setCadreName(String.valueOf(data[0][1]));
		cadMaster.setCadreIsActive(String.valueOf(data[0][2]));
		getCadreRecord(cadMaster);
		//showRecords(cadMaster);

	}
	public void showRecords(CadreMaster cadreMaster) {
		String query = "SELECT COMPENTENCY_CODE,CADRE_COMPETENCY_DTL FROM HRMS_CADRE_DTL WHERE CADRE_ID = "
				+ cadreMaster.getCadreID()+ " ORDER BY CADRE_ID";
		Object data[][] = getSqlModel().getSingleResult(query);
		ArrayList<Object> list = new ArrayList<Object>();
		for (int i = 0; i < data.length; i++) {
			CadreMaster bean1 = new CadreMaster();
			bean1.setCompetency(checkNull(String.valueOf(data[i][1])));
			bean1.setSrNo(String.valueOf(i + 1));
			list.add(bean1);
		}
		cadreMaster.setList(list);
		if (list.size() == 0) {
			cadreMaster.setTableLength("0");
		} else
			cadreMaster.setTableLength("1");
	}
	public void addItem(CadreMaster cadMaster, String[] srNo,String[] competency, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {
			for (int i = 0; i < srNo.length; i++) {
				CadreMaster cadMast = new CadreMaster();
				cadMast.setSrNo(String.valueOf(i + 1));
				cadMast.setCompetency(competency[i]);
				tableList.add(cadMast);
			}
		}
		if (check == 1) {
			cadMaster.setSrNo(String.valueOf(tableList.size() + 1));
			cadMaster.setCompetency(cadMaster.getCompetency1());
			tableList.add(cadMaster);
		} else if (check == 0) {
			tableList.remove(Integer.parseInt(cadMaster.getSubcode()) - 1);
		}
		if (tableList.size() != 0)
			cadMaster.setTableLength("1");
		else
			cadMaster.setTableLength("0");
		
		cadMaster.setList(tableList);
	}
	public void getDuplicate(CadreMaster cadMaster, String[] srNo,String[] competency, int check) {
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {

			for (int i = 0; i < srNo.length; i++) {
				CadreMaster bean = new CadreMaster();
				bean.setSrNo(String.valueOf(i + 1));
				bean.setCompetency(competency[i]);
				tableList.add(bean);
			}
			cadMaster.setList(tableList);
		}

	}
	
	public void moditem(CadreMaster cadMaster, String[] srNo,String[] competency, int check) {
		System.out.println("======moditem============");
		ArrayList<Object> tableList = new ArrayList<Object>();
		if (srNo != null) {

			for (int i = 0; i < srNo.length; i++) {
				CadreMaster bean = new CadreMaster();
				if (i == Integer.parseInt(cadMaster.getHiddenEdit()) - 1) {
					bean.setSrNo(String.valueOf(i + 1));
					System.out.println("--competency1---"+cadMaster.getCompetency1());
					bean.setCompetency(cadMaster.getCompetency1());

				} else {
					bean.setSrNo(String.valueOf(i + 1));
					bean.setCompetency(competency[i]);
				}
				tableList.add(bean);
			}
		}	
		if (tableList.size() != 0)
			cadMaster.setTableLength("1");
		else
			cadMaster.setTableLength("0");
		
	    cadMaster.setList(tableList);

	}
/**
 *  to delete  single record after saving or searching
 * @param cadMaster
 * @return
 */
	public boolean calfordelete(CadreMaster cadMaster) {

		Object[][] delete = new Object[1][1];
		delete[0][0] = cadMaster.getHiddencode();
		return getSqlModel().singleExecute(getQuery(3), delete);
		// TODO Auto-generated method stub

	}

	/* for deleteing the multiple data from list */
	public boolean deleteGrade(CadreMaster cadMaster, String[] code) {
		boolean result = false;
		boolean flag = false;
		int cnt = 0;
		if (code != null) {
			for (int i = 0; i < code.length; i++) {

				try {
					if (!code[i].equals("")) {
						Object[][] delete = new Object[1][1];
						delete[0][0] = code[i];
						
						String query=" SELECT CADRE_ID FROM HRMS_CADRE_DTL WHERE CADRE_ID="+code[i];
						Object[][]rel=getSqlModel().getSingleResult(query);
						
						
						if(rel.length>0){
							flag=getSqlModel().singleExecute(getQuery(8), delete);
						}
						
							flag= getSqlModel().singleExecute(getQuery(3), delete);
						
						
						if (!flag) {
							cnt++;
						}// end of if
						// result=true;
					}// end of if
				} catch (Exception e) {
					// TODO: handle exception
				}
			}// end of loop
		}
		if (cnt > 0) {
			result = false;
		}//end of if
		else
			result = true;
		return result;

	}
	//get AlL Competency for particular Grade Name
	/*public void  getCompetencyDetails(CadreMaster cadMaster){	
	     System.out.println("---------cadre NAME--------"+cadMaster.getCadreName());
	     
		
	}*/

}
