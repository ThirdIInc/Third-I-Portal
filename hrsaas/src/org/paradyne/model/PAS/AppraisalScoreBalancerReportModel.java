package org.paradyne.model.PAS;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.paradyne.bean.PAS.AppraisalScoreBalancerReport;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.SqlModel;
import org.paradyne.lib.Utility;
import org.paradyne.lib.ireportV2.TableDataSet;
import org.paradyne.lib.report.ReportGenerator;
/**
 * @modified by @author Prajakta.Bhandare
 * @date 25 April 2013
 */
public class AppraisalScoreBalancerReportModel extends ModelBase
{
  static Logger _logger = Logger.getLogger(AppraisalScoreBalancerReportModel.class);

  public void getReport(HttpServletRequest request, HttpServletResponse response, AppraisalScoreBalancerReport bean)
  {/*
	  String s = "\n Appraisal Score Balancer Report";
	     ReportGenerator rg = new ReportGenerator("Xls", s, "A4");
	      rg.addFormatedText(s, 6, 0, 1, 0);
    try
    {
      Object[][] data = new Object[1][3];
      rg.addFormatedText("", 0, 0, 0, 0);
      
      data[0][0] = ("\n Appraisal Code : " + bean.getSAppCode());
      data[0][1] = ("\n Appraisal Start Date : " + bean.getSAppStartDate());
      data[0][2] = ("\n Appraisal End Date : " + bean.getSAppEndDate());

      int [] cols = {30,30,30};
	  int [] align = {1,1,1};
	  rg.tableBodyNoCellBorder(data,cols,align,1);
		
      //int[] bcellWidth = { 10, 10, 10 };
      //int[] bcellAlign = new int[3];
      //rg.addText("\n", 0, 1, 0);
      //rg.tableBodyNoBorder(data, bcellWidth, bcellAlign);

      Object[] InputDate = new Object[1];
      InputDate[0] = bean.getSAppId();
      Object[][] objData = getSqlModel().getSingleResult(getQuery(1), InputDate);

      _logger.info("result.length====" + objData.length);
      int[] cellwidth = { 10,20, 30, 20, 20, 20, 20, 20, 20, 20, 20 };
      int[] alignment = { 1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1 };
      String[] colnames = { "Sr. No.","Employee Id", "Employee Name", "Division", "Department", "Branch", "Appraiser Name", "Actual Score", 
        "Operand", "Adjusted Score", "Final Score" };

      rg.addText("\n", 0, 1, 0);
      try
      {
        if (objData.length != 0)
        {
          objData = getAppraiserDetails(objData, bean);
          rg.tableBody(colnames, objData, cellwidth, alignment);
        }
        else
        {
          rg.addFormatedText("No records to display ", 0, 0, 1, 0);
        }
      }
      catch (Exception e)
      {
        rg.addFormatedText("No records to display ", 0, 0, 1, 0);
      }

    }
    catch (Exception e)
    {
      _logger.error(e.getMessage());
    }

    rg.createReport(response);
  */
	  }

  public void genReport(HttpServletRequest request, HttpServletResponse response, AppraisalScoreBalancerReport bean,String reportPath)
  {
	 
		org.paradyne.lib.ireportV2.ReportDataSet rds = new org.paradyne.lib.ireportV2.ReportDataSet();
			String type = "Xls";
			rds.setReportType(type);
			String fileName = "AppraisalScoreBalancerReport"
					+ Utility.getRandomNumber(1000);
			rds.setFileName(fileName);
			rds.setReportName("Appraisal Score Balancer Report");
			rds.setTotalColumns(11);
			rds.setShowPageNo(true);
			rds.setPageOrientation("A4");
			rds.setUserEmpId(bean.getUserEmpId());

			// Report generator starts here
			org.paradyne.lib.ireportV2.ReportGenerator rg = null;

			// Attachment Path Definition
			if (reportPath.equals("")) {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds, session,
						context, request);
			} //END OF IF
			else {
				rg = new org.paradyne.lib.ireportV2.ReportGenerator(rds,
						reportPath, session, context, request);
				request.setAttribute("reportPath", reportPath + fileName + "."
						+ type);
				request.setAttribute("action", "/PAS/AppraisalScoreBalancerReportAction_input.action");
				request.setAttribute("fileName", fileName + "." + type);
				// Initial Page Action
			}//END OF ELSE
			/* Setting Filter Details */
		 try {
			String filter = "";
			filter+= "\n Appraisal Code : " + bean.getSAppCode();
			filter+=  "\n Appraisal Start Date : " + bean.getSAppStartDate();
			filter+=  "\n Appraisal End Date : " + bean.getSAppEndDate();
			TableDataSet filterData = new TableDataSet();
			filterData.setData(new Object[][] { { filter } });
			filterData.setCellAlignment(new int[] { 0 });
			filterData.setCellWidth(new int[] { 100 });
			filterData.setCellColSpan(new int[] { 11 });
			filterData.setBodyFontStyle(1);
			filterData.setCellNoWrap(new boolean[] { false });
			rg.addTableToDoc(filterData);
			
			// Defining table Structure and Data
			
			Object[] InputDate = new Object[1];
		    InputDate[0] = bean.getSAppId();
			org.paradyne.lib.ireportV2.TableDataSet tdstable = new org.paradyne.lib.ireportV2.TableDataSet();
			Object[][] objData = getSqlModel().getSingleResult(getQuery(1),InputDate);
			if (objData.length != 0)
		    {
		      objData = getAppraiserDetails(objData, bean);
		    }
			if (objData == null || objData.length == 0) {//IF NO DATA
				TableDataSet noData = new TableDataSet();
				Object[][] noDataObj = new Object[1][1];
				noDataObj[0][0] = "No records available";
				noData.setData(noDataObj);
				noData.setCellAlignment(new int[] { 1 });
				noData.setCellWidth(new int[] { 100 });
				noData.setBorder(false);
				rg.addTableToDoc(noData);
			} //END OF IF NO DATA
			else {
				for (int i = 0; i < objData.length; i++) {
					//objData[i][0] = String.valueOf(i + 1);
				}// end of for (It reassign Value of  Rownum field again in Ascending order)
				
				// tdstable.setBlankRowsBelow(1);
				tdstable.setBlankRowsAbove(1);
				tdstable.setHeader(new String[] { "Sr. No.","Employee Id", "Employee Name", "Division", "Department", "Branch", "Appraiser Name", "Actual Score", 
				        "Operand", "Adjusted Score", "Final Score" });
				tdstable.setHeaderTable(true);
				tdstable.setHeaderBorderDetail(3);
				tdstable.setCellAlignment(new int[] {1, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1 });
				tdstable.setCellWidth(new int[] { 10,20, 30, 20, 20, 20, 20, 20, 20, 20, 20});
				tdstable.setData(objData);
				tdstable.setBorderDetail(3);
				tdstable.setBlankRowsBelow(1);
				rg.addTableToDoc(tdstable);

			}//END OF ELSE
			
	} catch (RuntimeException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	rg.process();

	if (reportPath.equals("")) {
		rg.createReport(response);
	} else {
		rg.saveReport(response);
	}
  }
  private Object[][] getAppraiserDetails(Object[][] data, AppraisalScoreBalancerReport bean)
  {
    String sAppId = null; String sEmpId = null;
    Object[] InputDate = new Object[3];

    if (data.length > 0)
    {
      for (int i = 0; i < data.length; i++)
      {
        InputDate[0] = String.valueOf(data[i][6]);
        InputDate[1] = bean.getSAppId();
        InputDate[2] = bean.getSAppId();

        Object[][] objData = getSqlModel().getSingleResult(getQuery(2), InputDate);

        for (int j = 0; j < objData.length; j++)
        {
          if (j == 0)
            data[i][6] = String.valueOf(objData[j][0]);
          else
            data[i][6] = (data[i][6] + "," + String.valueOf(objData[j][0]));
        }
      }
    }
    return data;
  }
}