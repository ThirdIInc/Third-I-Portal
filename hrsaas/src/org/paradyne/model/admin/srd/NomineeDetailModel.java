package org.paradyne.model.admin.srd;

import java.awt.Color;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.NomineeDetail;
import org.paradyne.bean.admin.srd.PersonnelDetail;
import org.paradyne.lib.AuditTrail;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.ireport.ReportDataSet;
import org.paradyne.lib.ireport.TableDataSet;

import com.lowagie.text.Font;

/**
 * @author radha
 * 
 */
public class NomineeDetailModel extends ModelBase {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(NomineeDetailModel.class);
	org.paradyne.bean.admin.srd.NomineeDetail nomiDetail = null;
	
	AuditTrail trial=null;

	/**
	 * To add record
	 * 
	 * @param bean
	 * @param request 
	 * @return boolean
	 */
	public String addNomDtl(NomineeDetail bean, HttpServletRequest request) {
		String result = "";
		
		try {
			Object addObj[][] = new Object[1][5];
			addObj[0][0] = bean.getEmpID();
			addObj[0][1] = bean.getNomiType();
			addObj[0][2] = bean.getMemberCode();
			addObj[0][3] = bean.getNomiFraction();
			addObj[0][4] = bean.getNomiDate();			
			String query = " SELECT NOM_SHARE  FROM HRMS_EMP_NOMINEE "
					+ " WHERE NOM_TYPE='" + bean.getNomiType()
					+ "' AND EMP_ID =" + bean.getEmpID();
			Object nomineefractiondata[][] = getSqlModel().getSingleResult(query);
			if (nomineefractiondata != null && nomineefractiondata.length > 0) {
				double total = 0.0;
				for (int i = 0; i < nomineefractiondata.length; i++) {
					total += Double.parseDouble(String
							.valueOf(nomineefractiondata[i][0]));
				}
				total = total
						+ Double.parseDouble(String.valueOf(bean
								.getNomiFraction()));
				char value = bean.getNomiType().charAt(0);
				if (total > 100 || total > 100.0) {
					result = "Total of fraction for " + getType(value)
							+ " should not be greater than 100%";
					return result;
				}
			}
			boolean res = getSqlModel().singleExecute(getQuery(1), addObj);
			if (res) {

				String query1 = "SELECT MAX(NOM_ID) FROM HRMS_EMP_NOMINEE";
				Object[][] data = getSqlModel().getSingleResult(query1);

				trial = new AuditTrail(bean.getUserEmpId());
				/** AUDIT TRIAL INITIALIZE * */
				trial.initiate(context, session);

				trial.setParameters("HRMS_EMP_NOMINEE",
						new String[] { "NOM_ID" }, new String[] { String
								.valueOf(data[0][0]) }, bean.getEmpID());

				/** AUDIT TRAIL EXECUTION * */
				trial.executeADDTrail(request);			
				deleteInsertRecords(bean,request);		
				result = "Record saved successfully";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
		
	}

	/**
	 * To update record
	 * 
	 * @param bean
	 * @return boolean
	 */
	public String modNomDtl(NomineeDetail bean,HttpServletRequest request) {
		String result = "";
		try {
			Object addObj[][] = new Object[1][5];
			logger.info("===USER LOGIN CODE====" + bean.getUserEmpId());
			trial = new AuditTrail(bean.getUserEmpId());
			/** AUDIT TRIAL INITIALIZE	**/
			trial.initiate(context, session);
			trial.setParameters("HRMS_EMP_NOMINEE", new String[] { "NOM_ID" },
					new String[] { bean.getParacode() }, bean.getEmpID());
			trial.beginTrail();
			addObj[0][0] = bean.getNomiType();
			addObj[0][1] = bean.getMemberCode();
			addObj[0][2] = bean.getNomiFraction().trim();
			addObj[0][3] = bean.getNomiDate().trim();
			addObj[0][4] = bean.getNomineeCode();
			
			String query = " SELECT NOM_SHARE  FROM HRMS_EMP_NOMINEE "
				+ " WHERE NOM_TYPE='" + bean.getNomiType()
				+ "' AND EMP_ID =" + bean.getEmpID();
		Object nomineefractiondata[][] = getSqlModel().getSingleResult(
				query);
		if (nomineefractiondata != null && nomineefractiondata.length > 0) {
			double total = 0.0;
			for (int i = 0; i < nomineefractiondata.length; i++) {
				
				if(! bean.getParacode().equals(""))
				total += Double.parseDouble(String
						.valueOf(nomineefractiondata[i][0])) ;
				
				
			}
			total -= Double.parseDouble(String
					.valueOf(bean.getOldFractionValue()));
			System.out.println("Nomineeeeeeeee......"+bean.getNomiFraction());
			total = total + Double.parseDouble(String.valueOf(bean.getNomiFraction()));
			logger.info("value of total after     "+ total);
			char value = bean.getNomiType().charAt(0);
			if (total > 100 || total > 100.0) {
				result = "Total of fraction for "+getType(value)+" should not begreater than 100%";
				return result ;
			}
		}
			boolean res = getSqlModel().singleExecute(getQuery(2), addObj);
			/**	AUDIT TRAIL	EXECUTION **/
			if(res){
				trial.executeMODTrail(request);
				
				//Added by manish sakpal Begins
				//For deleting and inserting records
				
				deleteInsertRecords(bean,request);
				
				//Added by manish sakpal Ends
				//For deleting and inserting records
				
				result="Record updated successfully";
			}
		} catch (Exception e) {			
			e.printStackTrace();
			logger.error("Exception in modNomDtl------------------"+e);			
		}
		return result;
	}

	/**
	 * To display record below
	 * 
	 * @param nomiDetail
	 */
	public void getNomineeRecord(NomineeDetail nomiDetail) {
		Object addObj[] = new Object[1];
		addObj[0] = nomiDetail.getEmpID();
		System.out.println("addObj[0]......"+addObj[0]);
		Object[][] data = getSqlModel().getSingleResult(getQuery(3), addObj);
		ArrayList<Object> nomList = new ArrayList<Object>();
		if (data!=null && data.length > 0) {
			for (int i = 0; i < data.length; i++) {
				NomineeDetail bean1 = new NomineeDetail();
				bean1.setNomineeCode(String.valueOf(data[i][6]));
				bean1.setNomiType(String.valueOf(data[i][7]));
				bean1.setMemberName(String.valueOf(data[i][9]));
				bean1.setNomiFraction(String.valueOf(data[i][10]));

				if (String.valueOf(data[i][11]).equals("null")
						|| (data[i][11] == null) || (data[i][11].equals(""))) {
					bean1.setNomiDate("");
				} // end of if
				else {
					bean1.setNomiDate(String.valueOf(data[i][11]));
				} // end of else
				nomList.add(bean1);
			}
			nomiDetail.setNoData("false");
			nomiDetail.setNomList(nomList);
		}else{
			nomiDetail.setNoData("true");
			nomiDetail.setNomList(null);
		}
	}
	
	
	/**
	 * To set record for edit
	 * 
	 * @param nomiDetail
	 */
	public void getRecord(NomineeDetail nomiDetail) {
		Object addObj[] = new Object[2];
		addObj[0] = nomiDetail.getParacode();
		System.out.println("................."+addObj[0]);
		addObj[1] = nomiDetail.getEmpID();
		System.out.println(".................ID"+addObj[1]);
		Object[][] data = getSqlModel().getSingleResult(getQuery(4), addObj);
		nomiDetail.setNomiType(String.valueOf(data[0][0]));
		nomiDetail.setMemberCode(String.valueOf(data[0][1]));
		nomiDetail.setMemberName(String.valueOf(data[0][2]));
		nomiDetail.setNomiFraction(String.valueOf(data[0][3]));

		if (String.valueOf(data[0][4]).equals("null") || (data[0][4] == null)
				|| (data[0][4].equals(""))) {
			nomiDetail.setNomiDate("");
		}// end of if
		else {
			nomiDetail.setNomiDate(String.valueOf(data[0][4]));
		}// end of else
		nomiDetail.setNomineeCode(String.valueOf(data[0][5]));
		
		String witnessQuery = "SELECT WITNESS_ID, WITNESS_NAME, WITNESS_ADDRESS FROM HRMS_EMP_NOM_WITNESS "
			+ " INNER JOIN HRMS_EMP_NOMINEE ON (HRMS_EMP_NOMINEE.NOM_TYPE = HRMS_EMP_NOM_WITNESS.NOM_TYPE AND HRMS_EMP_NOMINEE.NOM_ID = HRMS_EMP_NOM_WITNESS.NOM_ID) "
			+ " WHERE HRMS_EMP_NOMINEE.EMP_ID= "+nomiDetail.getEmpID()
			+ " AND HRMS_EMP_NOMINEE.NOM_NOMINEE="+nomiDetail.getMemberCode()
			+" AND HRMS_EMP_NOMINEE.NOM_TYPE='"+nomiDetail.getNomiType()+"'ORDER BY WITNESS_ID";
		 Object witnessObj[][] = getSqlModel().getSingleResult(witnessQuery);
		ArrayList<Object> witnessList = new ArrayList<Object>();
	    if (witnessObj != null && witnessObj.length > 0) {
		for (int i = 0; i < witnessObj.length; i++) {
			NomineeDetail innerbean = new NomineeDetail();
			innerbean.setWitnessID(checkNull(String
					.valueOf(witnessObj[i][0])));
			innerbean.setWitnessName(checkNull(String
					.valueOf(witnessObj[i][1])));
			innerbean.setWitnessAddress(checkNull(String
					.valueOf(witnessObj[i][2])));
					
			witnessList.add(innerbean);
		}
		nomiDetail.setWitnessDetailsList(witnessList);
	}else{
		nomiDetail.setWitnessDetailsList(null);
	}
	}

	/**
	 * To delete record
	 * 
	 * @param bean
	 * @param request 
	 * @return boolean
	 */
	public boolean delNomDtl(NomineeDetail bean, HttpServletRequest request) {

		Object addObj[][] = new Object[1][1];
		addObj[0][0] = bean.getParacode();
		trial = new AuditTrail(bean.getUserEmpId());
		/** AUDIT TRIAL INITIALIZE * */
		trial.initiate(context, session);
		trial.setParameters("HRMS_EMP_NOMINEE", new String[] { "NOM_ID" },
				new String[] { bean.getParacode() }, bean.getEmpID());

		/** AUDIT TRAIL EXECUTION * */
		trial.executeDELTrail(request);
		boolean result= getSqlModel().singleExecute(getQuery(5), addObj);
		
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

	 
	public String getType(char type) {
		String str = "";
		try {
			switch (type) {
			case 'G':
				str = "Gratuity";
				break;
			case 'F':
				str = "Pf";
				break;
			case 'P':
				str = "Pension";
				break;
			case 'E':
				str = "Esic";

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	// Delete witness Details function Added by manish sakpal Begins 
	public boolean delWitness(NomineeDetail nomDetail,
			HttpServletRequest request, String witnessid) {
		boolean result = false;
		try {
			logger.info("witnessid : "+witnessid);
			String deleteQuery = "DELETE FROM HRMS_EMP_NOM_WITNESS WHERE WITNESS_ID="+witnessid;
			result = getSqlModel().singleExecute(deleteQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// Delete witness Details function Added by manish sakpal Ends
	
	// Report Function Added by manish sakpal Begins
	public void getReport(NomineeDetail nomDetail, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String title ="Nominee Report ";
			ReportDataSet rds = new ReportDataSet();
			rds.setFileName("Nominee Report Of "+nomDetail.getEmpName());
			rds.setReportName(title);
			rds.setReportType("Pdf");
			org.paradyne.lib.ireport.ReportGenerator rg=new org.paradyne.lib.ireport.ReportGenerator(rds);
			
			TableDataSet repTitle = new TableDataSet();
			Object[][] repTitleObj = new Object[1][1];
			repTitleObj[0][0] = title;
			
			repTitle.setData(repTitleObj);
			repTitle.setCellAlignment(new int[] { 1 });
			repTitle.setCellWidth(new int[] { 100 });
			repTitle.setBodyFontDetails(Font.HELVETICA, 10,	Font.BOLD, new Color(0, 0, 0));
			repTitle.setBorder(false);
			repTitle.setBlankRowsBelow(1);
			rg.addTableToDoc(repTitle);
			
			// Employee Information Begins
			Object[][] empInfoDet = new Object[1][1];
			empInfoDet[0][0] = "Emoployee Information";			
			TableDataSet employeeTable = new TableDataSet();
			employeeTable.setData(empInfoDet);
			employeeTable.setCellAlignment(new int[] { 0 });
			employeeTable.setCellWidth(new int[] { 100 });
			employeeTable.setBlankRowsAbove(1);
			employeeTable.setBodyFontDetails(Font.HELVETICA, 10, Font.BOLD, new Color(0, 0, 0));			
			rg.addTableToDoc(employeeTable);
			
			Object[][] empInformationObj = new Object[2][2];
			empInformationObj[0][0] = "Employee :";
			empInformationObj[0][1] = nomDetail.getEmpToken()+" - "+nomDetail.getEmpName();	
					
			TableDataSet empTable = new TableDataSet();
			empTable.setData(empInformationObj);
			empTable.setCellAlignment(new int[] { 0,0 });
			empTable.setCellWidth(new int[] { 15,70 });
			empTable.setBlankRowsAbove(1);					
			rg.addTableToDoc(empTable);
			// Employee Information Begins
			
			//For branch and designation Begins
			Object[][] branchObj = new Object[2][4];
			branchObj[1][0] = "Branch :";
			branchObj[1][1] = ""+nomDetail.getCenterName();
			branchObj[1][2] = "Designation : ";
			branchObj[1][3] = ""+nomDetail.getRankName();

			TableDataSet branchTable = new TableDataSet();
			branchTable.setData(branchObj);
			branchTable.setCellAlignment(new int[] { 0, 0, 0, 0 });
			branchTable.setCellWidth(new int[] { 15, 35, 15, 25 });
			branchTable.setBlankRowsBelow(1);
			branchTable.setBorder(false);
			branchTable.setBlankRowsBelow(1);
			rg.addTableToDoc(branchTable);
			//For branch and designation Ends
			
			//Witness Details Begins
			Object[][] approverDtlDet = new Object[1][1];
			approverDtlDet[0][0] = "Witness Details";
			TableDataSet witnessDetTable = new TableDataSet();
			witnessDetTable.setData(approverDtlDet);
			witnessDetTable.setCellAlignment(new int[] { 0 });
			witnessDetTable.setCellWidth(new int[] { 100 });
			witnessDetTable.setBodyFontDetails(Font.HELVETICA, 10,Font.BOLD, new Color(0, 0, 0));
			witnessDetTable.setBodyBGColor(new Color(192, 192, 192));
			rg.addTableToDoc(witnessDetTable);
			witnessDetTable.setBlankRowsAbove(1);
			
			String witnessQuery = "SELECT WITNESS_NAME, WITNESS_ADDRESS FROM HRMS_EMP_NOM_WITNESS "
					+ " INNER JOIN HRMS_EMP_NOMINEE ON (HRMS_EMP_NOMINEE.NOM_ID = HRMS_EMP_NOM_WITNESS.NOM_ID) "
					+ " WHERE EMP_ID= "+nomDetail.getEmpID();
			Object witnessObj[][] = getSqlModel().getSingleResult(witnessQuery);
			Object[][] witnessTabularData = new Object[witnessObj.length][3];
			String[] columnsApp = new String[] { "Sr. No.", "Witness Name",	"Witness Address"};
			int[] bcellAlignApp = new int[] { 1, 0, 0};
			int[] bcellWidthApp = new int[] { 10, 40, 50 };
			int count = 1;
			
			ArrayList<Object> witnessList = new ArrayList<Object>();
			if (witnessObj != null && witnessObj.length > 0) {
				for (int i = 0; i < witnessObj.length; i++) {
					witnessTabularData[i][0] = count++;
					witnessTabularData[i][1] = checkNull(String.valueOf(witnessObj[i][0]));
					witnessTabularData[i][2] = checkNull(String.valueOf(witnessObj[i][1]));
				}
			}
			else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No data to display ";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));
				noData.setBorder(true);
				rg.addTableToDoc(noData);
			}

			TableDataSet witnessDetailTableData = new TableDataSet();
			witnessDetailTableData.setHeader(columnsApp);
			witnessDetailTableData.setData(witnessTabularData);
			witnessDetailTableData.setCellAlignment(bcellAlignApp);
			witnessDetailTableData.setCellWidth(bcellWidthApp);
			witnessDetailTableData.setBorder(true);
			witnessDetailTableData.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(witnessDetailTableData);
			//Witness Details Ends
			
			
			// Nominees's Particulars Begins
			Object[][] NomineeDet = new Object[1][1];
			NomineeDet[0][0] = "Nominee's Particulars";
			TableDataSet nomineeDetTable = new TableDataSet();
			nomineeDetTable.setData(NomineeDet);
			nomineeDetTable.setCellAlignment(new int[] { 0 });
			nomineeDetTable.setCellWidth(new int[] { 100 });
			nomineeDetTable.setBodyFontDetails(Font.HELVETICA, 10,Font.BOLD, new Color(0, 0, 0));
			nomineeDetTable.setBodyBGColor(new Color(192, 192, 192));
			rg.addTableToDoc(nomineeDetTable);
			nomineeDetTable.setBlankRowsAbove(1);
			
			String nomineeQuery = "SELECT HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME,"
								  +" DECODE(NOM_TYPE,'G','GRATUITY','F','PF','P','PENSION','S','ESIC'), "
								  +" NVL(NOM_SHARE,0),TO_CHAR(NOM_DATE,'DD-MM-YYYY') FROM HRMS_EMP_NOMINEE " 
								  +" LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_EMP_NOMINEE.EMP_ID) "
								  +" WHERE HRMS_EMP_NOMINEE.EMP_ID ="+nomDetail.getEmpID()+ "ORDER BY NOM_ID";
			Object nomineeObj[][] = getSqlModel().getSingleResult(nomineeQuery);
			Object[][] nomineeTabularData = new Object[nomineeObj.length][5];
			String[] columnsNom = new String[] { "Sr. No.", "Member Name",	"Nomination For", "Nomination Fraction", "Nomination Date" };
			int[] bcellAlignNom = new int[] { 1, 0, 0, 2, 1};
			int[] bcellWidthNom = new int[] { 10, 30, 20, 15, 25 };
			int nomCount = 1;
			
			if (nomineeObj != null && nomineeObj.length > 0) {
				for (int i = 0; i < nomineeObj.length; i++) {
					nomineeTabularData[i][0] = nomCount++;
					nomineeTabularData[i][1] = checkNull(String.valueOf(nomineeObj[i][0]));
					nomineeTabularData[i][2] = checkNull(String.valueOf(nomineeObj[i][1]));
					nomineeTabularData[i][3] = checkNull(String.valueOf(nomineeObj[i][2]));
					nomineeTabularData[i][4] = checkNull(String.valueOf(nomineeObj[i][3]));
				}
			}
			else {
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No data to display ";
				
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBodyFontDetails(Font.HELVETICA, 10,
						Font.NORMAL, new Color(0, 0, 0));
				noData.setBorder(true);
				rg.addTableToDoc(noData);
			}

			TableDataSet nomineeDetailTableData = new TableDataSet();
			nomineeDetailTableData.setHeader(columnsNom);
			nomineeDetailTableData.setData(nomineeTabularData);
			nomineeDetailTableData.setCellAlignment(bcellAlignNom);
			nomineeDetailTableData.setCellWidth(bcellWidthNom);
			nomineeDetailTableData.setBorder(true);
			nomineeDetailTableData.setHeaderBGColor(new Color(225, 225, 225));
			rg.addTableToDoc(nomineeDetailTableData);
			// Nominees's Particulars Ends
			
			rg.process();
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // Report function Added by manish sakpal Ends
	
	
	//	Delete Insert records Added by manish sakpal Begins		
	public boolean deleteInsertRecords(NomineeDetail bean,HttpServletRequest request)
	{
		boolean result = false;
		try {		
			Object detailQueryObj[][] = getSqlModel().getSingleResult("SELECT HRMS_EMP_NOM_WITNESS.NOM_ID,HRMS_EMP_NOM_WITNESS.NOM_TYPE FROM HRMS_EMP_NOM_WITNESS " 
					+" INNER JOIN HRMS_EMP_NOMINEE ON (HRMS_EMP_NOMINEE.NOM_ID = HRMS_EMP_NOM_WITNESS.NOM_ID and HRMS_EMP_NOMINEE.NOM_TYPE = HRMS_EMP_NOM_WITNESS.NOM_TYPE ) " 
					+" WHERE HRMS_EMP_NOMINEE.EMP_ID="+bean.getEmpID()
					+" AND HRMS_EMP_NOMINEE.NOM_NOMINEE="+bean.getMemberCode()+" AND HRMS_EMP_NOM_WITNESS.NOM_TYPE='"+bean.getNomiType()+"'");
			Object detailObj[][] = new Object[detailQueryObj.length][2];
			int z = 0;
							
				if(detailQueryObj!=null && detailQueryObj.length>0)
				{
					for (int i = 0; i < detailQueryObj.length; i++) {
						detailObj[z][0] = detailQueryObj[i][0];
						System.out.println("ID ["+i+"]"+detailObj[z][0]);
						z++;
						}					
					String deleteWitnessQuery = "DELETE FROM HRMS_EMP_NOM_WITNESS WHERE NOM_ID="+ detailQueryObj[0][0]+ "AND NOM_TYPE='"+detailQueryObj[0][1]+"'";
					getSqlModel().singleExecute(deleteWitnessQuery);
				}	
				String nomStr="";
				if(bean.getNomineeCode()==null || bean.getNomineeCode().equals("")){
					
					String query1 = "SELECT MAX(NOM_ID) FROM HRMS_EMP_NOMINEE WHERE HRMS_EMP_NOMINEE.NOM_NOMINEE="+bean.getMemberCode()+" AND NOM_TYPE='"+bean.getNomiType()+"'";
					
					Object[][] data = getSqlModel().getSingleResult(query1);
					 nomStr=String.valueOf(data[0][0]);
				}else{
					nomStr=bean.getNomineeCode();
				}
					Object witnessIDObj[][] = getSqlModel().getSingleResult("SELECT NVL(MAX(WITNESS_ID)+1,0) FROM HRMS_EMP_NOM_WITNESS");
					int witnessIncrementedID = Integer.parseInt("" + witnessIDObj[0][0]);
					
					String witnessName[] = request.getParameterValues("witnessName");					
					String witnessAddress[] = request.getParameterValues("witnessAddress");	
					for (int i = 0; i < witnessName.length; i++) {	
					if (!((witnessName==null || witnessName.equals("") ||  witnessName.equals("null") )) && witnessName.length>0 ) {
							if ((witnessName[i].trim()==null || witnessName[i].trim().equals("") ||  witnessName[i].trim().equals("null"))){
							}else{
								String witnessDetailsQuery = "INSERT INTO HRMS_EMP_NOM_WITNESS(WITNESS_ID,NOM_ID,NOM_TYPE,WITNESS_NAME,WITNESS_ADDRESS)VALUES" 
									+" ((SELECT NVL(MAX(WITNESS_ID)+1,0) FROM HRMS_EMP_NOM_WITNESS),"+nomStr+",'"+bean.getNomiType()+"','"+checkNull(String.valueOf(witnessName[i]))+"'," 
									+"'"+checkNull(String.valueOf(witnessAddress[i]))+"')";
									result = getSqlModel().singleExecute(witnessDetailsQuery);
							}
						}
					}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	// Delete Insert records Added by manish sakpal Ends

	//Added by Tinshuk Banafar Begins
	public void getImage(NomineeDetail nomDetail) {
			
			try {
				String query="select EMP_PHOTO ,NVL(EMP_FNAME,' '), NVL(EMP_MNAME,' '),NVL(EMP_LNAME,' ') from HRMS_EMP_OFFC where EMP_ID="+nomDetail.getEmpID();
				
				Object[][] myPics = getSqlModel().getSingleResult(query);
					
				nomDetail.setUploadFileName( String.valueOf(myPics[0][0]));	
				nomDetail.setProfileEmpName( String.valueOf(myPics[0][1])+" "+String.valueOf(myPics[0][2])+" "+String.valueOf(myPics[0][3]));
				
				System.out.println("persDetail.setUploadFileName( String.valueOf(myPics[0][0]))----"+nomDetail.getUploadFileName());
			
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	//Added by Tinshuk Banafar ends
	
}