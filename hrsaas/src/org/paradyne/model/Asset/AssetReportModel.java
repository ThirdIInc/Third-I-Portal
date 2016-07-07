 /**
 * 
 */
package org.paradyne.model.Asset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.Asset.AssetMaster;
import org.paradyne.bean.Asset.AssetReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.report.ReportGenerator;

/**
 * @author mangeshj Date 12/08/2008 AssetReportModel class to write business
 *         logic to show the PDF report for the assets present in the asset
 *         master.
 */
public class AssetReportModel extends ModelBase {

	/*
	 * method name : getReport purpose : to show the asset master details in PDF
	 * format return type : void parameter : HttpServletRequest
	 * request,HttpServletResponse response,AssetReport instance
	 */
	public void getReport(HttpServletRequest request,
			HttpServletResponse response, AssetReport bean) {
		String s = "\nASSET REPORT\n\n";
		org.paradyne.lib.report.ReportGenerator rg = new ReportGenerator(bean
				.getReportType(), s, "A4");
		rg.addFormatedText(s, 6, 0, 1, 0);
		rg.addFormatedText("", 0, 0, 1, 0);
		rg.addFormatedText("", 0, 0, 1, 0);
		Object data[][] = new Object[6][4];
		int[] cellwidth = { 20, 30, 20, 30 };
		int[] alignment = { 0, 0, 0, 0 };
		String returnType = "";
		String invStatus = "";
		if (bean.getAvailability().equals("U")) {
			invStatus = "Un-assigned";
		} // end of if
		else if (bean.getAvailability().equals("A")) {
			invStatus = "Assigned";
		} // end of else if
		else if (bean.getAvailability().equals("L")) {
			invStatus = "Lost";
		} // end of else if
		else if (bean.getAvailability().equals("D")) {
			invStatus = "Damaged";
		} // end of else if
		if (bean.getReturnType().equals("R")) {
			returnType = "Returnable";
		} // end of if
		else if (bean.getReturnType().equals("N")) {
			returnType = "Non-Returnable";
		} // end of else if
		/*
		 * add the components if the report type is 'PDF'
		 * 
		 */

		if (!bean.getReportType().equalsIgnoreCase("Xls")) {
			rg.addText("Date: " + bean.getToday(), 0, 2, 0);
			rg.addFormatedText("", 0, 0, 1, 0);
			rg.addFormatedText("", 0, 0, 1, 0);

			/*
			 * show the component in report according to the filters selected
			 * 
			 */
			if (!bean.getWarehouseCode().equals("")) {
				data[0][0] = "Warehouse Name ";
				data[0][1] = " :" + bean.getWarehouseName();

				if (!bean.getVendorCode().equals("")) {
					data[0][2] = "Vendor Name ";
					data[0][3] = " :" + bean.getVendorName();
				} // end of if

			} // end of if
			else if (!bean.getVendorCode().equals("")) {
				data[0][0] = "Vendor Name ";
				data[0][1] = " :" + bean.getVendorName();
			} // end of else if
			if (!bean.getCategoryCode().equals("")) {
				data[1][0] = "Asset Category ";
				data[1][1] = " :" + bean.getCategoryName();

				if (!bean.getSubTypeCode().equals("")) {
					data[1][2] = "Asset Subtype ";
					data[1][3] = " :" + bean.getSubTypeName();
				} // end of if

			} // end of if
			else if (!bean.getSubTypeCode().equals("")) {
				data[1][0] = "Asset Subtype ";
				data[1][1] = " :" + bean.getSubTypeName();
			} // end of else if
			if (!bean.getReturnType().equals("")) {
				data[2][0] = "Asset Return type ";
				data[2][1] = " :" + returnType;
			} // end of if
			if (!bean.getAvailability().equals("")) {
				data[2][2] = "Asset Status";
				data[2][3] = " :" + invStatus;
			} // end of if

			if (!bean.getFromDate().equals("")) {
				data[3][0] = "Purchase From Date ";
				data[3][1] = " :" + bean.getFromDate();

				if (!bean.getToDate().equals("")) {
					data[3][2] = "Purchase To Date";
					data[3][3] = " :" + bean.getToDate();
				} // end of if

			} // end of if
			else if (!bean.getToDate().equals("")) {
				data[3][0] = "Purchase To Date";
				data[3][1] = " :" + bean.getToDate();

			} // end of else if

			if (!bean.getLeaFromDate().equals("")) {
				data[4][0] = "Lease From Date ";
				data[4][1] = " :" + bean.getLeaFromDate();

				if (!bean.getLeaToDate().equals("")) {
					data[4][2] = "Lease To Date";
					data[4][3] = " :" + bean.getLeaToDate();
				} // end of if

			} // end of if
			else if (!bean.getLeaToDate().equals("")) {
				data[4][0] = "Lease To Date";
				data[4][1] = " :" + bean.getLeaToDate();

			} // end of else if

			if (!bean.getInsFromDate().equals("")) {
				data[5][0] = "Insurance From Date ";
				data[5][1] = " :" + bean.getInsFromDate();

				if (!bean.getInsToDate().equals("")) {
					data[5][2] = "Insurance To Date";
					data[5][3] = " :" + bean.getInsToDate();
				} // end of if

			} // end of if
			else if (!bean.getInsToDate().equals("")) {
				data[5][0] = "Insurance To Date";
				data[5][1] = " :" + bean.getInsToDate();

			} // end of else if
			rg.tableBodyNoBorder(data, cellwidth, alignment);

		} // end of if

		/*
		 * add the component if the report type is 'Xls'
		 * 
		 */
		else {
			/*
			 * show the component in report according to the filters selected
			 * 
			 */
			rg.addText("ASSET REPORT", 0, 1, 0);
			rg.addText("", 0, 1, 0);
			rg.addText("Date: " + bean.getToday(), 0, 1, 0);
			if (!bean.getToDate().equals(""))
				rg.addText("To date:" + bean.getToDate(), 0, 1, 0);
			if (!bean.getFromDate().equals(""))
				rg.addText("From date:" + bean.getFromDate(), 0, 1, 0);
			if (!bean.getWarehouseCode().equals(""))
				rg
						.addText("Warehouse Name:" + bean.getWarehouseName(),
								0, 1, 0);
			if (!bean.getCategoryCode().equals(""))
				rg.addText("Asset Category:" + bean.getCategoryName(), 0, 1, 0);
			if (!bean.getSubTypeCode().equals(""))
				rg.addText("Asset Subtype:" + bean.getSubTypeName(), 0, 1, 0);
			if (!bean.getVendorCode().equals(""))
				rg.addText("Vendor Name:" + bean.getVendorName(), 0, 1, 0);
			if (!bean.getAvailability().equals(""))
				rg.addText("Asset Status:" + invStatus, 0, 1, 0);
			if (!bean.getReturnType().equals(""))
				rg.addText("Asset Return type:" + returnType, 0, 1, 0);
			if (!bean.getLeaToDate().equals(""))
				rg.addText("Lease To date:" + bean.getLeaToDate(), 0, 1, 0);
			if (!bean.getLeaFromDate().equals(""))
				rg.addText("Lease From date:" + bean.getLeaFromDate(), 0, 1, 0);
			if (!bean.getInsToDate().equals(""))
				rg.addText("Lease To date:" + bean.getInsToDate(), 0, 1, 0);
			if (!bean.getInsFromDate().equals(""))
				rg.addText("Lease From date:" + bean.getInsFromDate(), 0, 1, 0);

		} // end of else

		if (bean.getReturnType().equals("R")) {

			rg = getReportForReturnable(rg, bean); // get the report for
			// returnable assets
		} // end of if
		else if (bean.getReturnType().equals("N")) {
			rg = getReportForNonReturnable(rg, bean); // get the report for
			// Non returnable assets
		} // end of else if
		else {
			rg = getReportForReturnable(rg, bean);
			rg.addFormatedText("", 0, 0, 0, 0);
			rg = getReportForNonReturnable(rg, bean);
		} // end of else

		/*
		 * String returnFlag=""; if(bean.getStatus().equals("Y"))
		 * returnFlag="Yes"; else returnFlag="No"; String query="SELECT
		 * ROWNUM,(HRMS_TITLE.TITLE_NAME||' '||HRMS_EMP_OFFC.EMP_FNAME||'
		 * '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' ||
		 * HRMS_EMP_OFFC.EMP_LNAME),ASST_INCODE ,ASSET_CATEGORY_NAME
		 * ,TO_CHAR(ASSIGN_DATE,'DD-MM-YYYY'),TO_CHAR(RETURN_DATE,'DD-MM-YYYY')," + "
		 * DECODE(RETURN_FLAG,'Y','YES','N','NO') FROM HRMS_ASST_ASSMT" + "
		 * INNER JOIN HRMS_ASST_MT
		 * ON(HRMS_ASST_MT.ASST_CODE=HRMS_ASST_ASSMT.ASST_CODE)" + " INNER JOIN
		 * HRMS_ASSMT ON(HRMS_ASSMT.ASSMT_CODE=HRMS_ASST_ASSMT.ASSMT_CODE)" + "
		 * INNER JOIN HRMS_EMP_OFFC
		 * ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSMT.EMP_CODE)" + " INNER JOIN
		 * HRMS_TITLE ON(HRMS_TITLE.TITLE_CODE=HRMS_EMP_OFFC.EMP_TITLE_CODE)" +"
		 * INNER JOIN HRMS_ASSET_CATEGORY
		 * ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASST_MT.ASST_HD_CODE)
		 * WHERE 1=1 ";
		 * 
		 * String s="\nASSET TO EMPLOYEE MIS REPORT\n\n";
		 * org.paradyne.lib.report.ReportGenerator rg=new
		 * ReportGenerator("Pdf",s); Object data[][]=null; try{
		 * 
		 * if(!bean.getEmpCode().equals("")&& bean.getFrmDate().equals("") &&
		 * bean.getStatus().equals("")&& bean.getToDate().equals("")){ data=new
		 * Object[1][2]; data[0][0]="Employee Name :"+ bean.getEname(); query+= "
		 * AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT
		 * WHERE EMP_CODE=" + bean.getEmpCode()+")";
		 * 
		 * }else if(!bean.getFrmDate().equals("") &&
		 * bean.getEmpCode().equals("")&& bean.getStatus().equals("") ){
		 * data=new Object[1][2]; data[0][0]="Assigned From Date :
		 * "+bean.getFrmDate();
		 * 
		 * String da=""; if(!bean.getToDate().equals("")){ da+=bean.getToDate();
		 * query+= " AND ASSIGN_DATE BETWEEN TO_DATE('" + bean.getFrmDate() +
		 * "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY')"; } else {
		 * da+=bean.getToday(); data[0][1]=""; query+= " AND ASSIGN_DATE >=
		 * TO_DATE('" + bean.getFrmDate() + "','DD-MM-YYYY')"; } }else
		 * if(!bean.getFrmDate().equals("") && ! bean.getEmpCode().equals("") &&
		 * bean.getStatus().equals("")){ data=new Object[2][2];
		 * data[0][0]="Employee Name:"+bean.getEname();
		 * 
		 * data[1][0]="Assigned From Date : "+bean.getFrmDate();
		 * 
		 * 
		 * String da="";
		 * 
		 * if(!bean.getToDate().equals("")) da+=bean.getToDate(); else
		 * da+=bean.getToday(); data[1][1]="To Date :"+da;
		 * 
		 * query+=" AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM
		 * HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND ASSIGN_DATE
		 * BETWEEN TO_DATE('" + bean.getFrmDate() + "','DD-MM-YYYY')AND
		 * TO_DATE('" + da + "','DD-MM-YYYY')"; }else
		 * if(!bean.getStatus().equals("") && bean.getEmpCode().equals("")&&
		 * bean.getFrmDate().equals("")&& bean.getToDate().equals("")){ data=new
		 * Object[1][2]; data[0][0]="Return Flag : "+returnFlag;
		 * 
		 * query+=" AND RETURN_FLAG='"+bean.getStatus()+"'";
		 * 
		 * }else if(!bean.getStatus().equals("") && !
		 * bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&&
		 * bean.getToDate().equals("")){ data=new Object[1][2];
		 * data[0][0]="Employee Name : "+bean.getEname();
		 * 
		 * data[0][1]="Return Flag : "+returnFlag;
		 * 
		 * query+= " AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM
		 * HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND
		 * RETURN_FLAG='"+bean.getStatus()+"'";
		 * 
		 * }else if(!bean.getStatus().equals("") &&
		 * bean.getEmpCode().equals("")&& ! bean.getFrmDate().equals("")){
		 * data=new Object[2][2]; data[0][0]="Return Flag : "+returnFlag;
		 * data[1][0]="Assigned From Date : "+bean.getFrmDate();
		 * 
		 * 
		 * String da="";
		 * 
		 * if(!bean.getToDate().equals("")) da+=bean.getToDate(); else
		 * da+=bean.getToday(); data[1][1]="To Date :"+da;
		 * 
		 * query+=" AND ASSIGN_DATE BETWEEN TO_DATE('" + bean.getFrmDate() +
		 * "','DD-MM-YYYY')AND TO_DATE('" + da + "','DD-MM-YYYY') AND
		 * RETURN_FLAG='"+bean.getStatus()+"'"; }else
		 * if(!bean.getStatus().equals("") && ! bean.getEmpCode().equals("")&& !
		 * bean.getFrmDate().equals("")){ data=new Object[2][2];
		 * data[0][0]="Employee Name : "+bean.getEname();
		 * 
		 * data[0][1]="Return Flag : "+returnFlag;
		 * 
		 * data[1][0]="Assigned From Date : "+bean.getFrmDate();
		 * 
		 * String da="";
		 * 
		 * 
		 * if(!bean.getToDate().equals("")) da+=bean.getToDate(); else
		 * da+=bean.getToday(); data[1][1]="To Date :"+da;
		 * 
		 * query+=" AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM
		 * HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND ASSIGN_DATE
		 * BETWEEN TO_DATE('" + bean.getFrmDate() + "','DD-MM-YYYY')AND
		 * TO_DATE('" + da + "','DD-MM-YYYY')AND
		 * RETURN_FLAG='"+bean.getStatus()+"'"; }else
		 * if(!bean.getEmpCode().equals("")&& ! bean.getToDate().equals("")&&
		 * bean.getFrmDate().equals("") && bean.getStatus().equals("")){
		 * data=new Object[1][2]; data[0][0]="Employee Name : "+bean.getEname();
		 * data[0][1]="Up to Date : "+bean.getToDate();
		 * 
		 * query+=" AND HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM
		 * HRMS_ASSMT WHERE EMP_CODE=" + bean.getEmpCode()+") AND ASSIGN_DATE <=
		 * TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;
		 * 
		 * }else if(bean.getEmpCode().equals("")&& !
		 * bean.getToDate().equals("")&& bean.getFrmDate().equals("") && !
		 * bean.getStatus().equals("")){ data=new Object[1][2];
		 * data[0][0]="Return Flag : "+returnFlag; data[0][1]="Up to Date :
		 * "+bean.getToDate(); query+=" AND RETURN_FLAG='"+bean.getStatus()+"'
		 * AND ASSIGN_DATE <= TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY')" ;
		 * 
		 * }else if(!bean.getStatus().equals("") && !
		 * bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& !
		 * bean.getToDate().equals("")){ data=new Object[2][2];
		 * data[0][0]="Employee Name : "+bean.getEname();
		 * 
		 * data[0][1]="Return Flag : "+returnFlag;
		 * 
		 * data[1][0]="Up to Date :"+bean.getToDate(); query+= " AND
		 * HRMS_ASST_ASSMT.ASSMT_CODE IN (SELECT ASSMT_CODE FROM HRMS_ASSMT
		 * WHERE EMP_CODE=" + bean.getEmpCode()+") AND
		 * RETURN_FLAG='"+bean.getStatus()+"' AND ASSIGN_DATE <=
		 * TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ;
		 * 
		 * }else if(bean.getStatus().equals("") &&
		 * bean.getEmpCode().equals("")&& bean.getFrmDate().equals("")&& !
		 * bean.getToDate().equals("")){ data=new Object[1][2]; data[0][0]="Up
		 * to Date :"+bean.getToDate();
		 * 
		 * query+= " AND ASSIGN_DATE <=
		 * TO_DATE('"+bean.getToDate()+"','DD-MM-YYYY') " ; }
		 * 
		 * int [] bcellWidth={50,50}; int [] bcellAlign={0,0}; Object
		 * tab[][]=getSqlModel().getSingleResult(query); rg.addFormatedText(s,
		 * 6, 0, 1, 0); rg.addText("Date: "+bean.getToday(), 0, 2, 0);
		 * rg.addFormatedText("", 0, 0, 1, 0); rg.addFormatedText("", 0, 0, 1,
		 * 0); rg.tableBodyNoBorder(data,bcellWidth,bcellAlign); if(tab!=null &&
		 * tab.length==0){
		 * 
		 * rg.addFormatedText("No Records to Display", 1, 0, 1, 3); } else{
		 * //logger.info("inside else length="+tab.length); int
		 * cellwidth[]={5,30,15,15,12,12,10}; int alignment[]={1,0,0,0,1,1,0};
		 * rg.addFormatedText("Asset Details :", 6, 0, 0, 0); rg.addText("\n",
		 * 0, 1, 0); String colnames[]={"Sr. No.","Employee Name","Inventory
		 * Code","Asset Type","Assigned Date","Returned Date","Return Flag"};
		 * rg.tableBody(colnames,tab, cellwidth, alignment); } }catch (Exception
		 * e) { e.printStackTrace(); }
		 * 
		 */
		rg.createReport(response);
	}

	/*
	 * method name : getReportForNonReturnable purpose : to show the asset
	 * master details in PDF format for Non returnable assets return type : void
	 * parameter : ReportGenerator rg,AssetReport instance
	 */
	public ReportGenerator getReportForNonReturnable(ReportGenerator rg,
			AssetReport bean) {
		String dataQuery = "SELECT HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE,ASSET_INVENTORY_CODE,ASSET_SUBTYPE_NAME,WAREHOUSE_NAME,VENDOR_NAME,HRMS_ASSET_MASTER_DTL.ASSET_QUANTITY,HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE,"
				+ " DECODE(ASSET_ASSISGN_FLAG,'U','AVAILABLE','A','ASSIGNED','L','LOST','D','DAMAGED'),"
				+ " ASSET_CATEGORY_NAME,TO_CHAR(ASSET_PURCHASE_DATE,'DD-MM-YYYY') FROM HRMS_ASSET_MASTER_DTL "
				+ " INNER JOIN HRMS_ASSET_MASTER ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE) "
				+ " INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE) "
				+ " LEFT JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_MASTER_DTL.ASSET_ASSIGNED_VENDOR) "
				+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE) "
				+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE) "
				+ " WHERE ASSET_SUBTYPE_FLAG='N' ";
		Object[][] heading = new Object[1][1];

		int[] cells = { 25 };
		int[] align = { 0 };

		double total_qty = 0;
		double total_available = 0;
		double total_assigned = 0;

		/*
		 * adding the conditions according to the filters selected
		 * 
		 */
		if (!bean.getWarehouseCode().equals("")) {
			dataQuery += " AND ASSET_WAREHOUSE_CODE=" + bean.getWarehouseCode();
		} // end of if
		if (!bean.getSubTypeCode().equals("")) {
			dataQuery += " AND HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE="
					+ bean.getSubTypeCode();
		} // end of if
		if (!bean.getCategoryCode().equals("")) {
			dataQuery += " AND ASSET_CATEGORY_CODE=" + bean.getCategoryCode();
		} // end of if
		if (!bean.getVendorCode().equals("")) {
			dataQuery += " AND ASSET_VENDOR=" + bean.getVendorCode();
		} // end of if

		if (!bean.getAvailability().equals("")) {
			dataQuery += " AND HRMS_ASSET_MASTER_DTL.ASSET_ASSISGN_FLAG='"
					+ bean.getAvailability() + "'";
		} // end of if

		if (!bean.getFromDate().equals("")) {
			dataQuery += " AND ASSET_PURCHASE_DATE >= TO_DATE('"
					+ bean.getFromDate() + "','DD-MM-YYYY')";
		} // end of if
		if (!bean.getToDate().equals("")) {
			dataQuery += " AND ASSET_PURCHASE_DATE <= TO_DATE('"
					+ bean.getToDate() + "','DD-MM-YYYY')";
		} // end of if
		Object[][] dataObject = getSqlModel().getSingleResult(dataQuery);
		Object[][] actualdata = new Object[dataObject.length][11];
		Object[][] totalData = new Object[1][10];
		/*
		 * getting the retrieved data in an Object to add it in the table format
		 * 
		 */
		for (int i = 0; i < actualdata.length; i++) {

			actualdata[i][0] = String.valueOf(i + 1);
			actualdata[i][1] = checkNull(String.valueOf(dataObject[i][1]));
			actualdata[i][2] = checkNull(String.valueOf(dataObject[i][3]));
			actualdata[i][3] = checkNull(String.valueOf(dataObject[i][8]));
			actualdata[i][4] = checkNull(String.valueOf(dataObject[i][2]));
			actualdata[i][5] = checkNull(String.valueOf(dataObject[i][4]));
			actualdata[i][6] = checkNull(String.valueOf(dataObject[i][9]));
			actualdata[i][7] = ""
					+ Double.parseDouble(checkNull(String
							.valueOf(dataObject[i][5])));
			actualdata[i][8] = ""
					+ Double.parseDouble(String.valueOf(dataObject[i][6]));
			actualdata[i][9] = (Double.parseDouble("" + actualdata[i][7]) - Double
					.parseDouble("" + actualdata[i][8]));
			actualdata[i][10] = checkNull(String.valueOf(dataObject[i][7]));

			total_qty += Double.parseDouble(String.valueOf(actualdata[i][7]));
			total_available += Double.parseDouble(String
					.valueOf(actualdata[i][8]));
			total_assigned += Double.parseDouble(String
					.valueOf(actualdata[i][9]));

		} // end of for loop

		totalData[0][0] = "";
		totalData[0][1] = "";
		totalData[0][2] = "Total";
		totalData[0][3] = "";
		totalData[0][4] = "";
		totalData[0][5] = "";
		totalData[0][6] = "";
		totalData[0][7] = String.valueOf(total_qty);
		totalData[0][8] = String.valueOf(total_available);
		totalData[0][9] = String.valueOf(total_assigned);

		String colNames[] = { "Sr.No", "Inventory Code", "Warehouse",
				"Category", "SubType", "Vendor", "Purchase Date", "Quantity",
				"Available", "Assigned", "Asset Status" };
		int[] cellwidth = { 8, 25, 25, 20, 20, 20, 20, 20, 20, 20, 20 };
		int[] alignmnet = { 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0 };
		rg.addFormatedText("", 1, 0, 0, 0);
		if (bean.getReportType().equalsIgnoreCase("Pdf")) {
			heading[0][0] = "Non-Returnable Inventory List :";
			rg.tableBodyBold(heading, cells, align);
			rg.addFormatedText("", 1, 0, 2, 3);
		} // end of if
		else {
			rg.addText("Non-Returnable Inventory List :", 0, 1, 0);
		} // end of else
		if (dataObject != null && dataObject.length != 0) {
			rg.tableBody(colNames, actualdata, cellwidth, alignmnet);
			rg.addText("", 0, 1, 0);
			rg.tableBody(totalData, cellwidth, alignmnet);
		}
		/*
		 * if no data retrieved from table then give the message
		 * 
		 */
		else {
			if (bean.getReportType().equalsIgnoreCase("Pdf")) {
				rg.addFormatedText("No records to display", 1, 0, 1, 3);
			} // end of if
			else {
				rg.addText("No Records to Display", 0, 2, 0);
			} // end of else
		} // end of else
		return rg;
	}

	/*
	 * method name : getReportForReturnable purpose : to show the asset master
	 * details in PDF format for returnable assets return type : void parameter :
	 * ReportGenerator rg,AssetReport instance
	 */
	public ReportGenerator getReportForReturnable(ReportGenerator rg,
			AssetReport bean) {

		try {
			String dataQuery = "SELECT DISTINCT HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE,ASSET_INVENTORY_CODE,ASSET_SUBTYPE_NAME,WAREHOUSE_NAME,VENDOR_NAME,NVL(HRMS_ASSET_MASTER_DTL.ASSET_QUANTITY,0),NVL(HRMS_ASSET_MASTER_DTL.ASSET_AVAILABLE,0),"
					+ " ASSET_ASSISGN_FLAG,ASSET_CATEGORY_NAME,TO_CHAR(ASSET_PURCHASE_DATE,'DD-MM-YYYY'),"
					+ " DECODE(ASSET_ASSISGN_FLAG,'U','AVAILABLE','A','ASSIGNED','L','LOST','D','DAMAGED'),"
					+ " TO_CHAR(ASSET_LEASE_RENEW_DATE,'DD-MM-YYYY'),TO_CHAR(ASSET_INSURANCE_RENEW_DATE,'DD-MM-YYYY'),CASE WHEN ASSET_OWNED_BY='E' "
					+ " THEN TO_CHAR(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME||' '||HRMS_EMP_OFFC.EMP_LNAME) WHEN ASSET_OWNED_BY='V'  "
					+ " THEN  TO_CHAR(NVL(VENDOR_NAME,' '))  "
					+ " ELSE ' ' END AS ASSIGNED_TO ,PROPERTY_UNIT"
					+ " FROM HRMS_ASSET_MASTER_DTL "
					+ " INNER JOIN HRMS_ASSET_MASTER ON(HRMS_ASSET_MASTER.ASSET_MASTER_CODE=HRMS_ASSET_MASTER_DTL.ASSET_MASTER_CODE) "
					+ " INNER JOIN HRMS_ASSET_SUBTYPE ON (HRMS_ASSET_SUBTYPE.ASSET_SUBTYPE_CODE=HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE) "
					+ " LEFT JOIN HRMS_VENDOR ON (HRMS_VENDOR.VENDOR_CODE=HRMS_ASSET_MASTER_DTL.ASSET_ASSIGNED_VENDOR) "
					+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_ASSET_MASTER_DTL.ASSET_ASSIGNED_EMP)  "
					+ " INNER JOIN HRMS_WAREHOUSE_MASTER ON (HRMS_WAREHOUSE_MASTER.WAREHOUSE_CODE=HRMS_ASSET_MASTER_DTL.ASSET_WAREHOUSE_CODE) "
					+ " INNER JOIN HRMS_ASSET_CATEGORY ON(HRMS_ASSET_CATEGORY.ASSET_CATEGORY_CODE=HRMS_ASSET_SUBTYPE.ASSET_CATEGORY_CODE) " 
					+"  LEFT  JOIN ASSET_PROPERTY_VIEW ON(ASSET_PROPERTY_VIEW.ASSET_SUBTYPE = HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE) "
					+ " WHERE ASSET_SUBTYPE_FLAG='R' ";
			Object[][] heading = new Object[1][1];
			double total_qty = 0;
			double total_available = 0;
			double total_assigned = 0;
			int[] cells = { 25 };
			int[] align = { 0 };
			/*
			 * adding the conditions according to the filters selected
			 * 
			 */
			if (!bean.getWarehouseCode().equals("")) {
				dataQuery += " AND ASSET_WAREHOUSE_CODE="
						+ bean.getWarehouseCode();
			} // end of if
			if (!bean.getCategoryCode().equals("")) {
				dataQuery += " AND ASSET_CATEGORY_CODE="
						+ bean.getCategoryCode();
			} // end of if
			if (!bean.getSubTypeCode().equals("")) {
				dataQuery += " AND HRMS_ASSET_MASTER.ASSET_SUBTYPE_CODE="
						+ bean.getSubTypeCode();
			} // end of if
			if (!bean.getVendorCode().equals("")) {
				dataQuery += " AND ASSET_VENDOR=" + bean.getVendorCode();
			} // end of if
			if (!bean.getAvailability().equals("")) {
				dataQuery += " AND HRMS_ASSET_MASTER_DTL.ASSET_ASSISGN_FLAG='"
						+ bean.getAvailability() + "'";
			} // end of if
			if (!bean.getFromDate().equals("")) {
				dataQuery += " AND ASSET_PURCHASE_DATE >= TO_DATE('"
						+ bean.getFromDate() + "','DD-MM-YYYY')";
			} // end of if
			if (!bean.getToDate().equals("")) {
				dataQuery += " AND ASSET_PURCHASE_DATE <= TO_DATE('"
						+ bean.getToDate() + "','DD-MM-YYYY')";
			} // end of if
			if (!bean.getLeaFromDate().equals("")) {
				dataQuery += " AND ASSET_LEASE_RENEW_DATE >= TO_DATE('"
						+ bean.getLeaFromDate() + "','DD-MM-YYYY')";
			} // end of if
			if (!bean.getLeaToDate().equals("")) {
				dataQuery += " AND ASSET_LEASE_RENEW_DATE <= TO_DATE('"
						+ bean.getLeaToDate() + "','DD-MM-YYYY')";
			} // end of if
			if (!bean.getInsFromDate().equals("")) {
				dataQuery += " AND ASSET_INSURANCE_RENEW_DATE >= TO_DATE('"
						+ bean.getInsFromDate() + "','DD-MM-YYYY')";
			} // end of if
			if (!bean.getInsToDate().equals("")) {
				dataQuery += " AND ASSET_INSURANCE_RENEW_DATE <= TO_DATE('"
						+ bean.getInsToDate() + "','DD-MM-YYYY')";
			} // end of if
			dataQuery += " order by ASSET_INVENTORY_CODE ";
			Object[][] dataObject = getSqlModel().getSingleResult(dataQuery);
			Object[][] totalData = new Object[1][15];
			rg.addFormatedText("", 1, 0, 0, 0);
			/*Object[][] propertyDataObj = null;
			if (!bean.getSubTypeCode().equals("")) {
				String query = "  select ASSET_SUBTYPE, PROPERTY_ID, PROPERTY_ATTRIBUTE, UNIT_ID from HRMS_ASSET_PROPERTY_MASTER "
						+ " where asset_subtype="
						+ bean.getSubTypeCode()
						+ "  order by PROPERTY_ID ";
				propertyDataObj = getSqlModel().getSingleResult(query);
			}*/
			int totalCol = 15;
			
		/*	if (propertyDataObj != null && propertyDataObj.length > 0) {*/
			//	totalCol = 15
			//}
			String[] colNames = new String[totalCol];
			int[] cellWidth = new int[totalCol];
			int[] alignment = new int[totalCol];
			int counter = 0;
			colNames[counter] = "Sr. No.";
			cellWidth[counter] = 10;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Inventory Code";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Warehouse";
			cellWidth[counter] = 30;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Category";
			cellWidth[counter] = 20;
			alignment[counter] = 1;
			counter++;
			colNames[counter] = "SubType";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Vendor";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Purchase Date";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Lease Renewal Date";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Insurance Renewal Date";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Quantity";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Available";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Assigned";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Asset Status";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Assigned To";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			colNames[counter] = "Properties";
			cellWidth[counter] = 20;
			alignment[counter] = 0;
			counter++;
			/*if (propertyDataObj != null && propertyDataObj.length > 0) {
				for (int i = 0; i < propertyDataObj.length; i++) {
					colNames[counter] = String.valueOf(propertyDataObj[i][2]);
					cellWidth[counter] = 10;
					alignment[counter] = 2;
					counter++;
				}

			}*/
			Object finalObj1[][] = null;
			if (dataObject != null && dataObject.length > 0) {
				finalObj1 = new Object[dataObject.length][15];
			}
			int s = 1;
			if (dataObject != null && dataObject.length > 0) {
				for (int i = 0; i < dataObject.length; i++) {

				
							finalObj1[i][13] =  checkNull(String
									.valueOf(dataObject[i][13]));;
					//} // end of if
					/*else {
						finalObj1[i][13] = "";
					} // end of else
*/					finalObj1[i][0] = s++;// sr no
					finalObj1[i][1] = checkNull(String
							.valueOf(dataObject[i][1]));
					finalObj1[i][2] = checkNull(String
							.valueOf(dataObject[i][3]));
					finalObj1[i][3] = checkNull(String
							.valueOf(dataObject[i][8]));
					finalObj1[i][4] = checkNull(String
							.valueOf(dataObject[i][2]));
					finalObj1[i][5] = checkNull(String
							.valueOf(dataObject[i][4]));
					finalObj1[i][6] = checkNull(String
							.valueOf(dataObject[i][9]));
					finalObj1[i][7] = checkNull(String
							.valueOf(dataObject[i][11]));
					finalObj1[i][8] = checkNull(String
							.valueOf(dataObject[i][12]));
					finalObj1[i][9] = ""
							+ Double.parseDouble(checkNull(String
									.valueOf(dataObject[i][5])));
					finalObj1[i][10] = ""
							+ Double.parseDouble(checkNull(String
									.valueOf(dataObject[i][6])));
					finalObj1[i][11] = ""
							+ (Double.parseDouble("" + finalObj1[i][9]) - Double
									.parseDouble("" + finalObj1[i][10]));
					finalObj1[i][12] = checkNull(String
							.valueOf(dataObject[i][10]));
					finalObj1[i][14] = checkNull(String
							.valueOf(dataObject[i][14]));
					total_qty += Double.parseDouble(String
							.valueOf(finalObj1[i][9]));
					total_available += Double.parseDouble(String
							.valueOf(finalObj1[i][10]));
					total_assigned += Double.parseDouble(String
							.valueOf(finalObj1[i][11]));

				}

			}
			totalData[0][0] = "";
			totalData[0][1] = "";
			totalData[0][2] = "Total";
			totalData[0][3] = "";
			totalData[0][4] = "";
			totalData[0][5] = "";
			totalData[0][6] = "";
			totalData[0][7] = "";
			totalData[0][8] = "";
			totalData[0][9] = String.valueOf(total_qty);
			totalData[0][10] = String.valueOf(total_available);
			totalData[0][11] = String.valueOf(total_assigned);
			totalData[0][12] = "";
			totalData[0][13] = "";
			/*Object finalObj[][] = null;
			if (propertyDataObj != null && propertyDataObj.length > 0) {
				finalObj = new Object[dataObject.length][propertyDataObj.length];
			}
			int cnt = 0;
			
			if (propertyDataObj != null && propertyDataObj.length > 0)
			{
				if (dataObject != null && dataObject.length > 0) {

					int k = 0;
					for (int j = 0; j < dataObject.length; j++) {

						String propertyObjQuery = " SELECT ASSET_PROPERTY_ID,ASSET_PROPERTY_VALUE ,ASSET_MASTER_CODE,ASSET_INVENTORY_CODE FROM  HRMS_ASSET_PROPERTIES WHERE ASSET_MASTER_CODE="
								+ String.valueOf(dataObject[j][0])
								+ " order by ASSET_INVENTORY_CODE,ASSET_PROPERTY_ID  ";

						Object[][] propertyObjObj = null;

						propertyObjObj = getSqlModel().getSingleResult(
								propertyObjQuery);

						if (k == propertyObjObj.length) {
							k = 0;
						}

						if (propertyObjObj.length > 0) {

							if (propertyDataObj != null
									&& propertyDataObj.length > 0) {
								for (int m = 0; m < propertyDataObj.length; m++) {
									finalObj[j][m] = propertyObjObj[k++][1];
								}

								cnt++;
							}

						} else {
							finalObj[j][0] = " ";
						}

					}
				}
			}

			Object[][] final_obj = null;
			if (finalObj != null && finalObj.length > 0) {
				final_obj = Utility.joinArrays(finalObj1, finalObj, 0, 0);
			} else {
				if (finalObj1 != null && finalObj1.length > 0) {
					final_obj = finalObj1;
				} else {
					final_obj = null;
				}
			}*/
			if (finalObj1 != null && finalObj1.length > 0) {
				
				
				if (bean.getReportType().equals("Xls")) {
					rg.addText("\n", 0, 0, 0);
					rg.xlsTableBody(colNames, finalObj1, cellWidth, alignment);
					rg.addText("", 0, 1, 0);
					rg.tableBody(totalData, cellWidth, alignment);

				} else if (bean.getReportType().equals(
						"Pdf")) {
					rg.addText("\n", 0, 0, 0);
					rg.tableBody(colNames, finalObj1, cellWidth, alignment);
					rg.tableBody(totalData, cellWidth, alignment);
				} else if (bean.getReportType().equals(
						"Txt")) {
					rg.tableBody(colNames, finalObj1, cellWidth, alignment);
					rg.tableBody(totalData, cellWidth, alignment);
				}
				
			
			}
			/*
			 * if (bean.getReportType().equalsIgnoreCase("Pdf")) { heading[0][0] =
			 * "Returnable Inventory List :"; rg.tableBodyBold(heading, cells,
			 * align); rg.addFormatedText("", 1, 0, 2, 3); } // end of if else {
			 * rg.addText("Returnable Inventory List :", 0, 1, 0); } // end of
			 * else if (dataObject != null && dataObject.length != 0) {
			 * rg.tableBody(colNames, actualdata, cellWidth, alignment);
			 * rg.addText("", 0, 1, 0); rg.tableBody(totalData, cellWidth,
			 * alignment); }
			 * 
			 * if no data retrieved from table then give the message
			 * 
			 * 
			 * else { if (bean.getReportType().equalsIgnoreCase("Pdf")) {
			 * rg.addFormatedText("No records to display", 1, 0, 1, 3); } // end
			 * of if else { rg.addText("No Records to Display", 0, 2, 0); } //
			 * end of else } // end of else
			 */
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rg;
	}

	/*
	 * method name : checkNull purpose : check the null value return type :
	 * String parameter : String result
	 */
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
}
