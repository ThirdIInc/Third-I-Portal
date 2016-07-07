package org.paradyne.model.payroll.incometax;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.paradyne.bean.payroll.incometax.Form27A;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.ReportDataSet;
import org.paradyne.lib.ireportV2.ReportGenerator;
import org.paradyne.lib.ireportV2.TableDataSet;

public class Form27Model extends ModelBase {
	/**
	 * logger initialization.
	 */
	private org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(ModelBase.class);
	
	private NumberFormat formatter = new DecimalFormat("#0.00");
    
	public Form27Model() {
    }
    //REPORT FORMAT CONVERTED TO NEW FORMAT=========REEBA========
    public void generateReport(final Form27A bean, final HttpServletRequest request,
    		final HttpServletResponse response, final String reportPath) {
    	
    	final String query = (new StringBuilder("SELECT NVL(HRMS_DIVISION.DIV_TANNO,' '),nvl(HRMS_DIVISION.DIV_PANNO,' '),NVL(HRMS_DIVISION.DIV_NAME,' '),NVL(HRMS_DIVISION.DIV_ADDRESS1,' '),NVL(HRMS_DIVISION.DIV_ADDRESS2,' '),  NVL(HRMS_DIVISION.DIV_ADDRESS3,' '),  NVL(L1.LOCATION_NAME,' '),  HRMS_DIVISION.DIV_PINCODE,NVL(L2.LOCATION_NAME,' '),HRMS_DIVISION.DIV_TELEPHONE,HRMS_DIVISION.DIV_EMAIL, NVL(HRMS_DIVISION.DIV_ABBR,' ')  FROM HRMS_DIVISION   LEFT JOIN HRMS_LOCATION L1 ON L1.LOCATION_CODE = HRMS_DIVISION.DIV_CITY_ID  LEFT JOIN HRMS_LOCATION L2 ON L1.LOCATION_PARENT_CODE = L2.LOCATION_CODE  WHERE HRMS_DIVISION.DIV_ID=")).append(bean.getDivId()).toString();
		final Object dataDeductor[][] = this.getSqlModel().getSingleResult(query);
		
    	final String strFileName = "TDS_Form 27A_" + dataDeductor[0][11] + "_"
			+ bean.getFinFrmYr() + "-" + bean.getFinToYr() + "_"
			+ Utility.getRandomNumber(1000);
    	
    	final String reportType = bean.getReportType();
    	
    	final ReportDataSet rds = new ReportDataSet();
		rds.setReportName(strFileName);
		rds.setReportType(reportType);
		rds.setFileName(strFileName);
		//rds.setUserEmpId(bean.getEmailUserId());
		rds.setReportHeaderRequired(false);
		org.paradyne.lib.ireportV2.ReportGenerator rg = null;
		
		if (reportPath.equals("")) {
			rg = new ReportGenerator(rds, session, context, request);
		} else {
			this.logger.info("################# ATTACHMENT PATH #############" + 
					reportPath + strFileName + "." + reportType);
			rg = new ReportGenerator(rds, reportPath, session, context, request);
			request.setAttribute("reportPath", reportPath + strFileName + "." +
					reportType);
			request.setAttribute("fileName", strFileName + "."	+ reportType);
			request.setAttribute("action", "Form27A_input.action");
		}
    	
    	//ReportGenerator rg = new ReportGenerator("Pdf", "Form27A Report");
        try {
            int startMth = 0;
            String endMth = "0";
            String year = "0";
            final String quarter = String.valueOf(bean.getQuarter());
            String QUARTER_NUMBER = "0";
            if(quarter.equals("June")) {
                startMth = 4;
                endMth = "6";
                year = bean.getFinFrmYr();
                QUARTER_NUMBER = "1";
            } else if(quarter.equals("September")) {
                startMth = 7;
                endMth = "9";
                year = bean.getFinFrmYr();
                QUARTER_NUMBER = "2";
            } else if(quarter.equals("December")) {
                startMth = 10;
                endMth = "12";
                year = bean.getFinFrmYr();
                QUARTER_NUMBER = "3";
            } else if(quarter.equals("March")) {
                startMth = 1;
                endMth = "3";
                year = bean.getFinToYr();
                QUARTER_NUMBER = "4";
            }
            //rg.setFName("Form27A.Pdf");
            //rg.addText("\n", 0, 0, 0);
            
            // rg.addTextBold("Form No. 27 A", 0, 1, 0);
            // rg.addTextBold("Form for furnishing information with the statement of deduction of tax at source filed on computer media for the period", 0, 1, 0);
            String frmDt = "";
            String toDt = "";
            if(bean.getQuarter().equals("June")) {
                frmDt = (new StringBuilder("01/04/")).append(bean.getFinFrmYr()).toString();
                toDt = (new StringBuilder("30/06/")).append(bean.getFinFrmYr()).toString();
            } else if(bean.getQuarter().equals("September")) {
                frmDt = (new StringBuilder("01/07/")).append(bean.getFinFrmYr()).toString();
                toDt = (new StringBuilder("30/09/")).append(bean.getFinFrmYr()).toString();
            } else if(bean.getQuarter().equals("December")) {
                frmDt = (new StringBuilder("01/10/")).append(bean.getFinFrmYr()).toString();
                toDt = (new StringBuilder("31/12/")).append(bean.getFinFrmYr()).toString();
            } else if(bean.getQuarter().equals("March")) {
            	 frmDt = (new StringBuilder("01/04/")).append(bean.getFinFrmYr()).toString();
                toDt = (new StringBuilder("31/03/")).append(bean.getFinToYr()).toString();
            }
            // rg.addTextBold((new StringBuilder(" From:")).append(frmDt).append("  To:").append(toDt).toString(), 0, 1, 0);
            // rg.addText("\n", 0, 0, 0);
            
            final Object[][] titleObj = new Object[1][1];
            titleObj[0][0] = "Form No. 27 A";
            final TableDataSet titleName = new TableDataSet();
    		titleName.setData(titleObj);
    		titleName.setCellAlignment(new int[] {1});
    		titleName.setCellWidth(new int[] {100});
    		titleName.setCellColSpan(new int[] {5});
    		titleName.setBodyFontStyle(1);
    		titleName.setBorder(false);
    		rg.addTableToDoc(titleName);
    		
    		final Object[][] subtitleObj = new Object[2][1];
    		//subtitleObj[0][0] = "Form for furnishing information with the statement of deduction of tax at source filed on computer media for the period";
    		subtitleObj[0][0] = "Form for furnishing information with the statement of deduction/collection of tax at source filed" +
    				" on computer media for the period";
    		
    		subtitleObj[1][0] = (new StringBuilder(" From:")).append(frmDt).append("  To:").append(toDt).toString();
           
    		final TableDataSet subtitleName = new TableDataSet();
    		subtitleName.setData(subtitleObj);
    		subtitleName.setCellAlignment(new int[] {1});
			subtitleName.setCellWidth(new int[] {100});
			subtitleName.setCellColSpan(new int[] {5});
			subtitleName.setBodyFontStyle(1);
    		subtitleName.setBorder(false);
    		subtitleName.setBlankRowsBelow(1);
    		rg.addTableToDoc(subtitleName);
    		
    		final String addDetailQuery = (new StringBuilder(" SELECT '',  NVL(HRMS_EMP_ADDRESS.ADD_1,' '),NVL(HRMS_EMP_ADDRESS.ADD_2,' '),NVL(HRMS_EMP_ADDRESS.ADD_3,' '),NVL(HRMS_LOCATION.LOCATION_NAME,' '),NVL(HRMS_EMP_ADDRESS.ADD_STATE,' '),NVL(HRMS_EMP_ADDRESS.ADD_PINCODE,''),HRMS_EMP_ADDRESS.ADD_MOBILE,HRMS_EMP_ADDRESS.ADD_EMAIL  FROM HRMS_EMP_ADDRESS   LEFT JOIN HRMS_LOCATION ON(HRMS_LOCATION.LOCATION_CODE=HRMS_EMP_ADDRESS.ADD_CITY)  INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_ADDRESS.EMP_ID=HRMS_EMP_OFFC.EMP_ID)   WHERE HRMS_EMP_ADDRESS.ADD_TYPE='P' AND HRMS_EMP_ADDRESS.EMP_ID =")).append(bean.getSignAuthId()).append(" ").toString();
    		final Object addDetail[][] = this.getSqlModel().getSingleResult(addDetailQuery);
            if (dataDeductor != null && dataDeductor.length != 0) {
            	final Object data[][] = new Object[3][4];
                data[0][0] = "1.   (a) Tax Deduction Account Number:  ";
                data[0][1] = dataDeductor[0][0];
                data[0][2] = "(d) Financial Year:  ";
                data[0][3] = (new StringBuilder(String.valueOf(bean.getFinFrmYr()))).append(bean.getFinToYr().substring(2, 4)).toString();
                int a = Integer.parseInt(String.valueOf(bean.getFinFrmYr()));
                a++;
                int b = Integer.parseInt(String.valueOf(bean.getFinToYr()));
                b++;
                data[1][0] = "      (b) Permanent Account Number:  ";
                data[1][1] = dataDeductor[0][1];
                data[1][2] = "(e) Assessment Year:  ";
                data[1][3] = (new StringBuilder(String.valueOf(String.valueOf(a)))).append(String.valueOf(b).substring(2, 4)).toString();
                
                data[2][0] = "      (c) Form Number:  ";
                data[2][1] = " 24Q";
                data[2][2] = "(f) Previous receipt number";
                data[2][3] = " ";
                final int cellwidth[] = {30, 20, 30, 20};
                final int alignment[] = new int[4];
                //rg.tableBodyNoBorder(data, cellwidth, alignment);
                
                final TableDataSet consTable = new TableDataSet();
				consTable.setData(data);
				consTable.setCellAlignment(alignment);
				consTable.setCellWidth(cellwidth);
				consTable.setBlankRowsBelow(1);
				consTable.setBorder(true);
				consTable.setBorderDetail(3);
				rg.addTableToDoc(consTable);
				
            }
            if (dataDeductor != null && dataDeductor.length != 0) {
            	final TableDataSet reportHeading = new TableDataSet();
        		reportHeading.setData(new Object[][] {{"2.Particulars of the Deductor/Collector"}});
        		reportHeading.setCellAlignment(new int[] {0});
        		reportHeading.setCellWidth(new int[] {100});
        		reportHeading.setCellColSpan(new int[] {5});
        		reportHeading.setBodyFontStyle(1);
        		reportHeading.setBorder(false);
        		rg.addTableToDoc(reportHeading);
        		
            	//rg.addTextBold("2.Particulars of the Deductor/Collector", 0, 0, 0);
        		
        		final Object deductor[][] = new Object[13][2];
                deductor[0][0] = "(a) Name";
                deductor[0][1] = (new StringBuilder()).append(bean.getDivName()).toString();
                deductor[1][0] = "(b) Type Of Deductor";
                deductor[1][1] = "K";
                deductor[2][0] = "(c) Branch/Division(if any)";
                deductor[2][1] = " ";
                deductor[3][0] = "(d) Address";
                deductor[3][1] = " ";
                deductor[4][0] = "    Flat No.";
                deductor[4][1] = (new StringBuilder()).append(dataDeductor[0][3]).toString();
                deductor[5][0] = "    Name of premises/building";
                deductor[5][1] = (new StringBuilder()).append(dataDeductor[0][4]).toString();
                deductor[6][0] = "    Road/Street/Lane";
                deductor[6][1] = (new StringBuilder()).append(dataDeductor[0][5]).toString();
                deductor[7][0] = "    Arear/Location";
                deductor[7][1] = " ";
                deductor[8][0] = "    Town/City/District";
                deductor[8][1] = (new StringBuilder()).append(dataDeductor[0][6]).toString();
                deductor[9][0] = "    State";
                deductor[9][1] = (new StringBuilder()).append(dataDeductor[0][8]).toString();
                deductor[10][0] = "   Pincode";
                deductor[10][1] = (new StringBuilder()).append(dataDeductor[0][7]).toString();
                deductor[11][0] = "   Telephone Number";
                deductor[11][1] = (new StringBuilder()).append(dataDeductor[0][9]).toString();
                deductor[12][0] = "   Email";
                deductor[12][1] = (new StringBuilder()).append(dataDeductor[0][10]).toString();
                final int align[] = new int[2];
                final int width[] = {10, 10};
               // rg.addText("\n\n", 0, 0, 0);
               // rg.tableBody1(deductor, width, align);
                final TableDataSet consTable = new TableDataSet();
				consTable.setData(deductor);
				consTable.setCellAlignment(align);
				consTable.setCellWidth(width);
				consTable.setCellColSpan(new int[] {2,3});
				consTable.setBlankRowsBelow(1);
				consTable.setBorder(true);
				consTable.setBorderDetail(3);
				rg.addTableToDoc(consTable);
            }
            if (addDetail != null && addDetail.length > 0) {
                //rg.addTextBold("3.Name of the person responsible for deduction/collection of the tax", 0, 0, 0);
            	final TableDataSet reportHeading = new TableDataSet();
        		reportHeading.setData(new Object[][] {{"3.Name of the person responsible for deduction/collection of the tax"}});
        		reportHeading.setCellAlignment(new int[] {0});
        		reportHeading.setCellWidth(new int[] {100});
        		reportHeading.setCellColSpan(new int[] {5});
        		reportHeading.setBodyFontStyle(1);
        		reportHeading.setBorder(false);
        		rg.addTableToDoc(reportHeading);
        		
                Object responsiblePers[][] = new Object[11][2];
                responsiblePers[0][0] = "(a) Name";
                responsiblePers[0][1] = (new StringBuilder()).append(bean.getSignAuthName()).toString();
                responsiblePers[1][0] = "(b) Address";
                responsiblePers[1][1] = " ";
                responsiblePers[2][0] = "    Flat No.";
                responsiblePers[2][1] = (new StringBuilder()).append(addDetail[0][1]).toString();
                responsiblePers[3][0] = "    Name of premises/building";
                responsiblePers[3][1] = (new StringBuilder()).append(addDetail[0][2]).toString();
                responsiblePers[4][0] = "    Road/Street/Lane";
                responsiblePers[4][1] = (new StringBuilder()).append(addDetail[0][3]).toString();
                responsiblePers[5][0] = "    Arear/Location";
                responsiblePers[5][1] = " ";
                responsiblePers[6][0] = "    Town/City/District";
                responsiblePers[6][1] = (new StringBuilder()).append(addDetail[0][4]).toString();
                responsiblePers[7][0] = "    State";
                responsiblePers[7][1] = (new StringBuilder()).append(addDetail[0][5]).toString();
                responsiblePers[8][0] = "    Pincode";
                responsiblePers[8][1] = (new StringBuilder()).append(addDetail[0][6]).toString();
                responsiblePers[9][0] = "    Telephone Number";
                responsiblePers[9][1] = (new StringBuilder()).append(Utility.checkNull(String.valueOf(addDetail[0][7]))).toString();
                
                responsiblePers[10][0] = "    Email";
                responsiblePers[10][1] = (new StringBuilder()).append(Utility.checkNull(String.valueOf(addDetail[0][8]))).toString();
                final int align[] = new int[2];
                final int width[] = {10, 10};
                // rg.addText("\n\n", 0, 0, 0);
                //rg.tableBody1(responsiblePers, width, align);
                //rg.addText("\n\n", 0, 0, 0);
                TableDataSet consTable = new TableDataSet();
				consTable.setData(responsiblePers);
				consTable.setCellAlignment(align);
				consTable.setCellWidth(width);
				consTable.setCellColSpan(new int[] {2, 3});
				consTable.setBlankRowsBelow(1);
				consTable.setBorder(true);
				consTable.setBorderDetail(3);
				rg.addTableToDoc(consTable);
            }
            final Object ob[] = new Object[4];
            ob[0] = bean.getDivId();
            ob[1] = year;
            ob[2] = (new StringBuilder()).append(startMth).toString();
            ob[3] = endMth;
            double totAmt = 0.0D;
            double taxableAmt = 0.0D;
            int count = 0;
            String challanQuery = "SELECT NVL(HRMS_TAX_CHALLAN.CHALLAN_TOTALTAX,0.00),  HRMS_TAX_CHALLAN.CHALLAN_CODE,HRMS_TAX_CHALLAN.CHALLAN_MONTH,HRMS_TAX_CHALLAN.CHALLAN_YEAR  FROM HRMS_TAX_CHALLAN   WHERE HRMS_TAX_CHALLAN.CHALLAN_DIVISION_ID=?  AND HRMS_TAX_CHALLAN.CHALLAN_YEAR =? AND HRMS_TAX_CHALLAN.CHALLAN_MONTH BETWEEN ? AND ? ";
            Object challanData[][]=null;
           /* if(QUARTER_NUMBER.equals("4")){
            	challanQuery = "SELECT NVL(HRMS_TAX_CHALLAN.CHALLAN_TOTALTAX,0.00),  HRMS_TAX_CHALLAN.CHALLAN_CODE,HRMS_TAX_CHALLAN.CHALLAN_MONTH,HRMS_TAX_CHALLAN.CHALLAN_YEAR  FROM HRMS_TAX_CHALLAN   WHERE HRMS_TAX_CHALLAN.CHALLAN_DIVISION_ID="+bean.getDivId()+"  AND ((HRMS_TAX_CHALLAN.CHALLAN_YEAR ="+bean.getFinFrmYr()+" AND HRMS_TAX_CHALLAN.CHALLAN_MONTH BETWEEN 4 AND 12) OR (HRMS_TAX_CHALLAN.CHALLAN_YEAR ="+bean.getFinToYr()+" AND HRMS_TAX_CHALLAN.CHALLAN_MONTH BETWEEN 1 AND 3)) ";
            	challanData = getSqlModel().getSingleResult(challanQuery);
            }
            else{
            	challanData = getSqlModel().getSingleResult(challanQuery, ob);
            }*/
            challanData = getSqlModel().getSingleResult(challanQuery, ob);
            if(challanData != null && challanData.length != 0)
            {
                for(int i = 0; i < challanData.length; i++)
                {
                    totAmt += Double.parseDouble(String.valueOf(challanData[i][0]));
                    String empQuery = (new StringBuilder("  SELECT HRMS_TAX_CHALLAN_DTL.EMP_ID,count(HRMS_TAX_CHALLAN_DTL.emp_id) FROM HRMS_TAX_CHALLAN_DTL WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS >0 	AND HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE=")).append(String.valueOf(challanData[i][1])).append(" GROUP BY HRMS_TAX_CHALLAN_DTL.EMP_ID").toString();
                    Object emp_id[][] = getSqlModel().getSingleResult(empQuery);
                    if(emp_id!=null && emp_id.length>0){
                    	count+=emp_id.length;
                    }
                    //ADDED BY REEBA BEGINS
                    String queryIncome = getUnionQuery(String.valueOf(challanData[i][3]),String.valueOf(challanData[i][2]),
                    		bean.getDivId(),String.valueOf(challanData[i][1]));
                    Object[][] incomeData = getSqlModel().getSingleResult(queryIncome);
                    if(challanData != null && challanData.length != 0){
                    	for (int j = 0; j < incomeData.length; j++) {
                    		 taxableAmt += Double.parseDouble(String.valueOf(incomeData[j][1]));
						} //end of loop
                    } //end of if
                  //ADDED BY REEBA ENDS
                   
                   /* for(int j = 0; j < emp_id.length; j++)
                    {
                        count += Integer.parseInt(String.valueOf(emp_id[j][1]));
                        String queryTaxableAmt = (new StringBuilder("SELECT NVL(SAL_TOTAL_CREDIT,0.00) FROM HRMS_SALARY_")).append(year).append(" ").append(" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_").append(year).append(".SAL_LEDGER_CODE) ").append(" WHERE EMP_ID =").append(emp_id[j][0]).append(" AND LEDGER_YEAR=").append(year).append(" AND LEDGER_MONTH=").append(String.valueOf(challanData[i][2])).toString();
                        Object taxamt[][] = getSqlModel().getSingleResult(queryTaxableAmt);
                        if(taxamt.length <= 0)
                            taxableAmt += 0.0D;
                        else
                            taxableAmt += Double.parseDouble(String.valueOf(taxamt[0][0]));
                    }*/

                }

            }
            Object param[][] = new Object[2][5];
            param[0][0] = "Sr. No.";
            param[0][1] = "No. of Deductee/Party records";
            param[0][2] = "Amount Paid\n(Rs.)";
            param[0][3] = "Tax Deducted/Collection\n(Rs.)";
            param[0][4] = "Tax Deposited(Total Challan Amount)\n(Rs.)";
            param[1][0] = "TOTAL";
            param[1][1] = Integer.valueOf(count);
            param[1][2] = formatter.format(taxableAmt);
            param[1][3] = formatter.format(totAmt);
            param[1][4] = formatter.format(totAmt);
            int align[] = {0, 2, 2, 2, 2};
            int width[] = {5, 10, 10, 10, 10};
            //rg.addText("\n", 0, 0, 0);
            //rg.addTextBold("4.Control Total", 0, 0, 0);
            //rg.addText("\n", 0, 0, 0);
            TableDataSet reportHeading = new TableDataSet();
    		reportHeading.setData(new Object[][] {{"4.Control Total"}});
    		reportHeading.setCellAlignment(new int[] {0});
    		reportHeading.setCellWidth(new int[] {100});
    		reportHeading.setCellColSpan(new int[] {5});
    		reportHeading.setBodyFontStyle(1);
    		reportHeading.setBorder(true);
    		reportHeading.setBorderDetail(3);
    		rg.addTableToDoc(reportHeading);
    		
            //rg.tableBody1(param, width, align);
    		TableDataSet consTable = new TableDataSet();
			consTable.setData(param);
			consTable.setCellAlignment(align);
			consTable.setCellWidth(width);
			consTable.setBlankRowsBelow(1);
			consTable.setBorder(true);
			consTable.setBorderDetail(3);
			rg.addTableToDoc(consTable);
			
            //rg.addTextBold("5.Total No. of Annexures enclosed :", 0, 0, 0);
            TableDataSet reportHeading2 = new TableDataSet();
    		reportHeading2.setData(new Object[][] {{"5.Total No. of Annexures enclosed :"}});
    		reportHeading2.setCellAlignment(new int[] {0});
    		reportHeading2.setCellWidth(new int[] {100});
    		reportHeading2.setCellColSpan(new int[] {5});
    		reportHeading2.setBodyFontStyle(1);
    		reportHeading2.setBorder(false);
    		rg.addTableToDoc(reportHeading2);
    		
            //rg.addText("\n", 0, 0, 0);
            //rg.addTextBold("6.Other Information :", 0, 0, 0);
            TableDataSet reportHeading3 = new TableDataSet();
    		reportHeading3.setData(new Object[][] {{"6.Other Information :"}});
    		reportHeading3.setCellAlignment(new int[] {0});
    		reportHeading3.setCellWidth(new int[] {100});
    		reportHeading3.setCellColSpan(new int[] {5});
    		reportHeading3.setBodyFontStyle(1);
    		reportHeading3.setBorder(false);
    		reportHeading3.setBlankRowsBelow(1);
    		rg.addTableToDoc(reportHeading3);
    		
    		TableDataSet reportHeading4 = new TableDataSet();
    		reportHeading4.setData(new Object[][] {{"Verification"}});
    		reportHeading4.setCellAlignment(new int[] {1});
    		reportHeading4.setCellWidth(new int[] {100});
    		reportHeading4.setCellColSpan(new int[] {5});
    		reportHeading4.setBodyFontStyle(1);
    		reportHeading4.setBorder(false);
    		reportHeading4.setBlankRowsBelow(1);
    		rg.addTableToDoc(reportHeading4);
    		
    		TableDataSet reportHeading5 = new TableDataSet();
    		reportHeading5.setData(new Object[][] {{(new StringBuilder("I,  ")).append(bean.getSignAuthName()).append(" ,hereby certify that all the particulars furnished above are correct and complete. ").toString()}});
    		reportHeading5.setCellAlignment(new int[] {0});
    		reportHeading5.setCellWidth(new int[] {100});
    		reportHeading5.setCellColSpan(new int[] {5});
    		reportHeading5.setBorder(false);
    		reportHeading5.setBlankRowsBelow(2);
    		rg.addTableToDoc(reportHeading5);
    		
    		Object[][] footerObj = new Object[4][3];
    		footerObj[0][0] = "Place :";
    		footerObj[0][1] = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    		footerObj[0][2] = "Signature of person responsible for deducting/collecting tax at source.";
    		footerObj[1][0] = "\n\n";
    		footerObj[1][1] = "\n\n";
    		footerObj[1][2] = "\n\n";
    		footerObj[2][0] = "";
    		footerObj[2][1] = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    		footerObj[2][2] = bean.getSignAuthName()+" - "+bean.getSignAuthDesg();
    		footerObj[3][0] = "Date :";
    		footerObj[3][1] = "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
    		footerObj[3][2] = "Name and designation of person responsible for deducting/collecting tax at source.";
    		
    		TableDataSet footerTable = new TableDataSet();
    		footerTable.setData(footerObj);
    		footerTable.setCellAlignment(new int[] {0, 0, 0});
    		footerTable.setCellWidth(new int[] {10, 5, 85});
    		footerTable.setCellColSpan(new int[] {1,1,3});
    		footerTable.setBorder(false);
			rg.addTableToDoc(footerTable);
			
			//rg.addText("\n\n\n\n", 0, 0, 0);
            //rg.addText((new StringBuilder("I  ")).append(bean.getSignAuthName()).append(" ,hereby certify that all the particulars furnished above are correct and complete. ").toString(), 0, 0, 0);
            //rg.addText("\n", 0, 0, 0);
            //rg.addText("Place :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tSignature of person responsible for deducting tax  at source.", 0, 0, 0);
            //rg.addText("\n", 0, 0, 0);
            //rg.addText("Date :\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tName and designation of person responsible for deducting tax at source.", 0, 0, 0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		rg.process();
		if (reportPath.equals("")) {
			rg.createReport(response);
		} else {
			/* Generates the report as attachment */
			rg.saveReport(response);
		}
    }
    
    /**
     * @author REEBA_JOSEPH
     * @param year
     * @param month
     * @param divCode
     * @param challanCode
     * @return
     */
    public String getUnionQuery(String year, String month, String divCode,String challanCode) {

    	String query = "";
    	try{
    		query = "SELECT EMPID,ROUND(SUM(AMT)) FROM ( "
    		+" SELECT DISTINCT HRMS_SALARY_"+year+".EMP_ID AS EMPID,SUM(HRMS_SAL_CREDITS_"+year+".SAL_AMOUNT) AS AMT "
    		+" FROM HRMS_SAL_CREDITS_"+year+"  "
    		+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SAL_CREDITS_"+year+".SAL_CREDIT_CODE) "  
    		+" INNER JOIN HRMS_SALARY_"+year+" ON (HRMS_SALARY_"+year+".SAL_LEDGER_CODE = HRMS_SAL_CREDITS_"+year+".SAL_LEDGER_CODE " 
    		+" AND HRMS_SAL_CREDITS_"+year+".EMP_ID = HRMS_SALARY_"+year+".EMP_ID)  "
    		+" INNER JOIN HRMS_SALARY_LEDGER ON (HRMS_SALARY_LEDGER.LEDGER_CODE = HRMS_SALARY_"+year+".SAL_LEDGER_CODE) "  
    		+" WHERE HRMS_SALARY_LEDGER.LEDGER_DIVISION="+divCode+" AND HRMS_SALARY_LEDGER.LEDGER_YEAR="+year+" AND HRMS_SALARY_LEDGER.LEDGER_MONTH="+month+"  AND HRMS_CREDIT_HEAD.CREDIT_TAXABLE_FLAG = 'Y' "
    		+" AND HRMS_SALARY_"+year+".EMP_ID NOT IN(SELECT HRMS_SETTL_HDR.SETTL_ECODE FROM HRMS_SETTL_HDR  "
    		+" INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) "
    		+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) "    
    		+" WHERE HRMS_RESIGN.RESIGN_WITHDRAWN = 'N' AND TO_CHAR(HRMS_SETTL_HDR.SETTL_SETTLDT,'MM')="+month+" AND TO_CHAR(HRMS_SETTL_HDR.SETTL_SETTLDT,'YYYY')="+year+" "
    		+" AND HRMS_EMP_OFFC.EMP_DIV = "+divCode+")  "
    		+" AND HRMS_SALARY_"+year+".EMP_ID IN (SELECT HRMS_TAX_CHALLAN_DTL.EMP_ID AS EMPID FROM HRMS_TAX_CHALLAN_DTL "
    		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
    		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_TAX_CHALLAN_DTL.EMP_ID) "
    		+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)   "
    		+" WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS >0 AND HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE = "+challanCode+")"
    		+" GROUP BY HRMS_SALARY_"+year+".EMP_ID "
    		+" UNION "
    		+" SELECT HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID AS EMPID,SUM(NVL(HRMS_ARREARS_CREDIT_"+year+".ARREARS_AMT,0))AS AMT "   
    		+" FROM HRMS_ARREARS_CREDIT_"+year+"  "
    		+" INNER JOIN HRMS_CREDIT_HEAD ON HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ARREARS_CREDIT_"+year+".ARREARS_CREDIT_CODE " 
    		+" INNER JOIN HRMS_EMP_OFFC ON HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID = HRMS_EMP_OFFC.EMP_ID  "
    		+" WHERE HRMS_ARREARS_CREDIT_"+year+".ARREARS_CODE IN (SELECT HRMS_ARREARS_LEDGER.ARREARS_CODE FROM HRMS_ARREARS_LEDGER WHERE HRMS_ARREARS_LEDGER.ARREARS_REF_MONTH ="+month+" " 
    		+" AND HRMS_ARREARS_LEDGER.ARREARS_REF_YEAR = "+year+" AND HRMS_ARREARS_LEDGER.ARREARS_DIVISION = "+divCode+") AND HRMS_CREDIT_HEAD.CREDIT_TAXABLE_FLAG = 'Y'  "
    		+" AND HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID IN (SELECT HRMS_TAX_CHALLAN_DTL.EMP_ID AS EMPID FROM HRMS_TAX_CHALLAN_DTL "
    		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
    		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_TAX_CHALLAN_DTL.EMP_ID) "
    		+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)   "
    		+" WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS >0 AND HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE = "+challanCode+")"
    		+" GROUP BY HRMS_ARREARS_CREDIT_"+year+".ARREARS_EMP_ID "
    		+" UNION "
    		+" SELECT HRMS_SETTL_HDR.SETTL_ECODE AS EMPID, "
    		+" CEIL(SUM(HRMS_SETTL_CREDITS.SETTL_AMT))AS AMT  "
    		+" FROM HRMS_SETTL_CREDITS   "
    		+" INNER JOIN HRMS_SETTL_HDR ON (HRMS_SETTL_HDR.SETTL_CODE = HRMS_SETTL_CREDITS.SETTL_CODE) "
    		+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_SETTL_CREDITS.SETTL_CREDIT_CODE) " 
    		+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_SETTL_HDR.SETTL_ECODE) "
    		+" INNER JOIN HRMS_RESIGN ON (HRMS_RESIGN.RESIGN_CODE = HRMS_SETTL_HDR.SETTL_RESGNO) " 
    		+" WHERE (HRMS_SETTL_CREDITS.SETTL_MTH_TYPE IN ('OH','CO')) AND HRMS_RESIGN.RESIGN_WITHDRAWN = 'N' AND HRMS_EMP_OFFC.EMP_DIV = "+divCode+"  "
    		+" AND TO_CHAR(HRMS_SETTL_HDR.SETTL_SETTLDT,'MM')="+month+" AND TO_CHAR(HRMS_SETTL_HDR.SETTL_SETTLDT,'YYYY')="+year+" "
    		+" AND HRMS_CREDIT_HEAD.CREDIT_TAXABLE_FLAG = 'Y' "
    		+" AND HRMS_SETTL_HDR.SETTL_ECODE IN (SELECT HRMS_TAX_CHALLAN_DTL.EMP_ID AS EMPID FROM HRMS_TAX_CHALLAN_DTL "
    		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
    		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_TAX_CHALLAN_DTL.EMP_ID) "
    		+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)   "
    		+" WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS >0 AND HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE = "+challanCode+")"
    		+" GROUP BY HRMS_SETTL_HDR.SETTL_ECODE "
    		+" UNION "
    		+" SELECT HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID AS EMPID,NVL(SUM(HRMS_ALLOWANCE_EMP_DTL.ALLW_AMOUNT_FINAL),0) AS AMT  "
    		+" FROM HRMS_ALLOWANCE_HDR  "
    		+" INNER JOIN HRMS_ALLOWANCE_EMP_DTL ON (HRMS_ALLOWANCE_EMP_DTL.ALLW_ID = HRMS_ALLOWANCE_HDR.ALLW_ID) "  
    		+" INNER JOIN HRMS_EMP_OFFC ON (HRMS_EMP_OFFC.EMP_ID = HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID)  "
    		+" INNER JOIN HRMS_CREDIT_HEAD ON (HRMS_CREDIT_HEAD.CREDIT_CODE = HRMS_ALLOWANCE_HDR.ALLW_CREDIT_HEAD) " 
    		+" AND TO_CHAR(HRMS_ALLOWANCE_HDR.ALLW_PROCESS_DATE,'MM')="+month+" "
    		+" AND TO_CHAR(HRMS_ALLOWANCE_HDR.ALLW_PROCESS_DATE,'YYYY')="+year+" AND HRMS_EMP_OFFC.EMP_DIV = "+divCode+" " 
    		+" AND HRMS_CREDIT_HEAD.CREDIT_TAXABLE_FLAG = 'Y' "
    		+" AND HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID IN (SELECT HRMS_TAX_CHALLAN_DTL.EMP_ID AS EMPID FROM HRMS_TAX_CHALLAN_DTL "
    		+" INNER JOIN HRMS_TAX_CHALLAN ON (HRMS_TAX_CHALLAN.CHALLAN_CODE = HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE) "
    		+" INNER JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID =HRMS_TAX_CHALLAN_DTL.EMP_ID) "
    		+" LEFT JOIN HRMS_SALARY_MISC ON(HRMS_SALARY_MISC.EMP_ID = HRMS_EMP_OFFC.EMP_ID)   "
    		+" WHERE HRMS_TAX_CHALLAN_DTL.CHALLAN_TDS >0 AND HRMS_TAX_CHALLAN_DTL.CHALLAN_CODE = "+challanCode+")"
    		+" GROUP BY HRMS_ALLOWANCE_EMP_DTL.ALLW_EMP_ID "
    		+" )GROUP BY EMPID";
    	}catch(Exception e){
    		e.printStackTrace();
    	} //end of catch
    	return query;
    } //end of getUnionQuery method

}
