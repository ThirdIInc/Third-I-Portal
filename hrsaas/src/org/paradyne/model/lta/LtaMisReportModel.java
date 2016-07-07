/**
 * 
 */
package org.paradyne.model.lta;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.paradyne.bean.admin.srd.MISReport;
import org.paradyne.bean.lta.LtaMisReport;
import org.paradyne.bean.payroll.pension.EmpConfForPension;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.Utility;
/**
 * @author aa0554
 *
 */
public class LtaMisReportModel extends ModelBase {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(LtaMisReportModel.class);
	public void callViewScreen(LtaMisReport misreport, HttpServletRequest request,
			String[] labelNames) {
		try{
			logger.info("multi select values  : " + misreport.getHiddenColumns());
			String mutliSelectValues = misreport.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0, mutliSelectValues
						.length() - 1);
				splitComma = lastComma.split(",");
			}

			int count = 0;

			logger.info("counter initial value==========" + count);

			
			
			// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
			String queryWithCount = selectQuery(misreport, labelNames, count,
					splitComma, request);
			logger.info("queryWithCount select query==========" + queryWithCount);
			// SPLIT TO GET COUNT VALUE
			String[] splitquery = queryWithCount.split("#");

			String query = splitquery[0];
			count = Integer.parseInt(splitquery[1]);
			String labels = splitquery[2];
			
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			String[] str_colNames = new String[count + 2];
			str_colNames[0] = "Employee Id";
			str_colNames[1] = "Employee Name";
			int str_colNames_array = 1;
			int[] cellWidth = new int[count + 2];
			cellWidth[0] = 5;
			cellWidth[1] = 30;
			int cellWidth_array = 1;
			int[] cellAlign = new int[count + 2];
			cellAlign[0] = 0;
			cellAlign[1] = 0;
			int cellAlign_array = 1;

			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(misreport, labelNames);

			Object[][] finalObj = getSqlModel().getSingleResult(query);

			

		

			
			logger.info("Labels before setting..."+labels);
			request.setAttribute("labelValues", labels);
			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					if (splitDash[1].equals(labelNames[2])) {
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[3])) {
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[4])) {
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[5])) {
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[6])) {
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[7])) {
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[8])) {
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[9])) {
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					

				}
			}
			
			Object[][] objData1 = null;
			String[] labelObject = null;

			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 2];
				labelObject = new String[count + 2];
				
				// for (int j = Integer.parseInt(pageIndex[0]); j < Integer
				// .parseInt(pageIndex[1]); j++) {

				if (finalObj != null && finalObj.length > 0) {

					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 1;
						int label_count = 1;
						objData1[j][0] = checkNull(String.valueOf(finalObj[j][0]));// Employee//
						objData1[j][1] = checkNull(String.valueOf(finalObj[j][1]));
						// Token
						labelObject[0] = "Employee Id";
						labelObject[1] = "Employee Name";
						
						if (misreport.getBranchChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[2];
						}

						if (misreport.getBlockYrChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[3];
						}
						if (misreport.getVisitYearChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[4];
						}

						if (misreport.getClaimDateChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[5];
						}
						if (misreport.getClaimTypeChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[6];
						}
						if (misreport.getClaimAmtChk().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[7];

						}
						if (misreport.getExemptedChk().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[8];

						}
						if (misreport.getExemptedYearChk().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[9];

						}
						
						// }
					}
					
				}

				String[] pageIndex = Utility.doPaging(misreport.getMyPage(),
						objData1.length, 20);
				if (pageIndex == null) {
					pageIndex[0] = "0";
					pageIndex[1] = "20";
					pageIndex[2] = "1";
					pageIndex[3] = "1";
					pageIndex[4] = "";
				}
				request.setAttribute("totalPage", Integer.parseInt(String
						.valueOf(pageIndex[2])));
				request.setAttribute("PageNo", Integer.parseInt(String
						.valueOf(pageIndex[3])));
				if (pageIndex[4].equals("1"))
					misreport.setMyPage("1");

				int numOfRec = Integer.parseInt(pageIndex[1])
						- Integer.parseInt(pageIndex[0]);
				int columnLength = count + 2;
				Object[][] pageObj = new Object[numOfRec][columnLength];
				int z = 0;
				int srNo = 1;
				for (int i = Integer.parseInt(pageIndex[0]); i < Integer
						.parseInt(pageIndex[1]); i++) {
					for (int j = 0; j < columnLength; j++) {
						// logger.info("objData1[" + i + "][" + j + "] : "
						// + objData1[i][j]);
						pageObj[z][j] = objData1[i][j];
					}
					z++;
					srNo++;
					request.setAttribute("finalObj", pageObj);
				}
				misreport.setDataLength(String.valueOf(finalObj.length));

			} else {
				request.setAttribute("totalPage", new Integer(0));
				request.setAttribute("PageNo", new Integer(0));
				misreport.setDataLength("0");
				misreport.setNoData("true");
			}
			}catch(Exception e){
				e.printStackTrace();
				
			}

		}
	public String checkNull(String result) {
		if (result == null || result.equals("null")) {
			return "";
		} else {
			return result;
		}
	}
	
	public String selectQuery(LtaMisReport bean, String[] labelNames, int count,
			String[] splitComma, HttpServletRequest request) {
		String labels="Employee Id,Employee,";
		String query = "SELECT HRMS_EMP_OFFC.EMP_TOKEN,NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||"
											+ "NVL(HRMS_EMP_OFFC.EMP_LNAME,' ') ";
		if (splitComma != null) {
			// new HASHMAP FOR ORDERING
			LinkedHashMap<Integer, String> columnMap = new LinkedHashMap<Integer, String>();
			LinkedHashMap<Integer, String> labelMap = new LinkedHashMap<Integer, String>();
			
			for (int i = 0; i < splitComma.length; i++) {
				String splitDash[] = splitComma[i].split("-");
				logger.info("Key....." + splitDash[0]);
				logger.info("Value....." + splitDash[1]);

				//labelMap.put(0, "Employee Code");
				 if (splitDash[1].equals(labelNames[2])) {
					// query += " ,NVL(DEPT_NAME,' ') ";
					columnMap.put(i+1,
							" NVL(CENTER_NAME,' ')");
					labelMap.put(i+1, labelNames[2]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[3])) {
					// query += " ,NVL(CENTER_NAME,' ') ";
					columnMap.put(i+1,
							" LTA_BLOCK_FROM||'-'||LTA_BLOCK_TO ");
					labelMap.put(4, labelNames[3]);
					count++;
				} else if (splitDash[1].equals(labelNames[4])) {
					// query += " ,NVL(RANK_NAME,' ') ";
					columnMap.put(i+1,
							" LTA_YEAROFVISIT");
					labelMap.put(5, labelNames[4]);
					count++;
				} else if (splitDash[1].equals(labelNames[5])) {
					columnMap.put(i+1,
							" TO_CHAR(LTA_CLAIM_DATE,'DD-MM-YYYY')");
					count++;
					labelMap.put(6, labelNames[5]);
				}

				
				else if (splitDash[1].equals(labelNames[6])) {
					columnMap.put(i+1,
							" DECODE(LTA_TYPE,'S','SELF','F','FAMILY','B','BOTH')");
					labelMap.put(7, labelNames[6]);
					count++;
				}

				
				else if (splitDash[1].equals(labelNames[7])) {
					columnMap.put(i+1,
							" NVL(LTA_CLAIM_AMOUNT,0)");
					labelMap.put(8, labelNames[7]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[8])) {
					columnMap.put(i+1,
							" DECODE(LTA_EXEMPTED,'Y','YES','N','NO')");
					labelMap.put(9, labelNames[8]);
					count++;
				}

				else if (splitDash[1].equals(labelNames[9])) {
					columnMap.put(i+1,
									" LTA_EXEMPT_IN_YEAR||'-'||(LTA_EXEMPT_IN_YEAR+1) ");
					labelMap.put(10, labelNames[9]);
					count++;
				}
				

			}
			
			Iterator<Integer> iterator = columnMap.keySet().iterator();
			while (iterator.hasNext()) {
				String mapValues = (String) columnMap.get(iterator.next());
				logger.info("mapValues : " + mapValues);
				query += "," + mapValues;
			}
			
			Iterator<Integer> labelIter = labelMap.keySet().iterator();
			String labelValues = "";
			while (labelIter.hasNext()) {
				labelValues = (String) labelMap.get(labelIter.next());
				logger.info("labelValues : " + labelValues);
				labels += labelValues + ",";
			}

		
		
		logger.info("labels........."+labels);
		//request.setAttribute("labelValues", labels);

		logger.info("count in selectQuery method : " + count);
		

		}
		query += "" + "#" + count + "#" + labels;
		return query;
	}
	
	public String conditionQuery(LtaMisReport bean, String[] labelNames) {
		String query = " FROM HRMS_LTA_TAXCALC"
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=HRMS_LTA_TAXCALC.EMP_ID)"
				+ " INNER JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " WHERE 1=1 AND( 1=0 ";
				boolean isFilterSelected =false;	
		if (!(bean.getApplEmpName().equals("")) && !(bean.getApplEmpName() == null)
				&& !bean.getApplEmpName().equals("null")) {

			query += " OR UPPER(HRMS_EMP_OFFC.EMP_ID) IN("
					+ bean.getApplEmpCode().trim()+ ")";
			isFilterSelected=true;
		}
		
		if (!(bean.getApplDivisionCode().equals("")) && !(bean.getApplDivisionCode() == null)
				&& !bean.getApplDivisionCode().equals("null")) {

			query += " OR HRMS_EMP_OFFC.EMP_DIV IN(" +bean.getApplDivisionCode()+ ")";
			isFilterSelected=true;
		}

		if (!(bean.getApplBranchCode().equals("")) && !(bean.getApplBranchCode() == null)
				&& !bean.getApplBranchCode().equals("null")) {

			query += " OR HRMS_EMP_OFFC.EMP_CENTER IN(" +bean.getApplBranchCode()+ ")";
			isFilterSelected=true;
		}
		if (!(bean.getApplDeptCode().equals("")) && !(bean.getApplDeptCode() == null)
				&& !bean.getApplDeptCode().equals("null")) {

			query += " OR HRMS_EMP_OFFC.EMP_DEPT IN(" +bean.getApplDeptCode()+ ")";
			isFilterSelected=true;
		}
		if (!(bean.getApplDesgCode().equals("")) && !(bean.getApplDesgCode() == null)
				&& !bean.getApplDesgCode().equals("null")) {

			query += " OR HRMS_EMP_OFFC.EMP_RANK IN(" +bean.getApplDesgCode()+ ")";
			isFilterSelected=true;
		}
		if (!(bean.getApplETypeCode().equals("")) && !(bean.getApplETypeCode() == null)
				&& !bean.getApplETypeCode().equals("null")) {

			query += " OR HRMS_EMP_OFFC.EMP_TYPE IN(" +bean.getApplETypeCode()+ ")";
			isFilterSelected=true;
		}
		if (!(bean.getApplGradeCode().equals("")) && !(bean.getApplGradeCode() == null)
				&& !bean.getApplGradeCode().equals("null")) {

			query += " OR HRMS_EMP_OFFC.EMP_CADRE IN(" +bean.getApplGradeCode()+ ")";
			isFilterSelected=true;
		}
		
		if(!isFilterSelected){
			query +=" OR 1=1";
		}
		
		query +=") ";
		
		/*
		 * Applying Advance filter options
		 */
		
		if(!bean.getClaimDateCompare().equals("1")){
			query+= getDateCondition(bean,Integer.parseInt(bean.getClaimDateCompare()),"claimDate");
		}
		
		if(!bean.getYearOfVisitOperator().equals("1")){
			query+= " AND LTA_YEAROFVISIT "+bean.getYearOfVisitOperator()+ bean.getYearOfVisitFilter();
		}
		
		if(!bean.getClaimAmtOperator().equals("1")){
			query+= " AND LTA_CLAIM_AMOUNT "+bean.getClaimAmtOperator()+ bean.getClaimAmtFilter();
		}
		
		if(!bean.getExemptionOperator().equals("1")){
			query+= " AND LTA_EXEMPT_IN_YEAR "+bean.getExemptionOperator()+ bean.getExemptionFilter();
		}
		
		
		// query += " ORDER BY HRMS_EMP_OFFC.EMP_ID";

		// ============ start of sorting ===========
		if (!bean.getSortBy1().equals("1") || !bean.getSortBy2().equals("1")
				|| !bean.getSortBy3().equals("1")) {
			query += " ORDER BY ";
		}
		if (!bean.getSortBy1().equals("1")) {
			query += getSortVal(bean.getSortBy1(), labelNames) + " "
					+ getSortOrder(bean.getSortByOrder1());
			if (!bean.getSortBy2().equals("1")
					|| !bean.getSortBy3().equals("1")) {
				query += " , ";
			}
		}

		if (!bean.getSortBy2().equals("1")) {
			query += getSortVal(bean.getSortBy2(), labelNames) + " "
					+ getSortOrder(bean.getSortByOrder2());
			if (!bean.getSortBy3().equals("1")) {
				query += " , ";
			}
		}

		if (!bean.getSortBy3().equals("1")) {
			query += getSortVal(bean.getSortBy3(), labelNames) + " "
					+ getSortOrder(bean.getSortByOrder3());
		}
		// ============ end of sorting ===========

		return query;
	}
	
	public String getSortVal(String Status, String[] labelNames) {
		logger.info("Sort Status............" + Status);
		String sql = "";
		
		if (Status.equals(labelNames[0])) {
			sql = "NVL(EMP_TOKEN,' ')";

		}
		if (Status.equals(labelNames[1])) {
			sql = " NVL(HRMS_EMP_OFFC.EMP_FNAME,' ')||' '||NVL(HRMS_EMP_OFFC.EMP_MNAME,' ')||' '||"
					+ "NVL(HRMS_EMP_OFFC.EMP_LNAME,' ')";

		}

		 else if (Status.equals(labelNames[2])) {
			sql = " NVL(CENTER_NAME,' ') ";

		}

		else if (Status.equals(labelNames[3])) {
			sql = " LTA_BLOCK_FROM||'-'||LTA_BLOCK_TO";

		} else if (Status.equals(labelNames[4])) {
			sql = " LTA_YEAROFVISIT ";

		} else if (Status.equals(labelNames[5])) {
			sql = " TO_CHAR(LTA_CLAIM_DATE,'DD-MM-YYYY') ";

		}

		else if (Status.equals(labelNames[6])) {
			sql = " DECODE(LTA_TYPE,'S','SELF','F','FAMILY','B','BOTH') ";

		}

		else if (Status.equals(labelNames[7])) {
			sql = " NVL(LTA_CLAIM_AMOUNT,0) ";

		}

		else if (Status.equals(labelNames[8])) {
			sql = " DECODE(LTA_EXEMPTED,'Y','YES','N','NO')";

		}

		else if (Status.equals(labelNames[9])) {
			sql = " LTA_EXEMPT_IN_YEAR||'-'||(LTA_EXEMPT_IN_YEAR+1)";

		}

		return sql;
	}

	public String getSortOrder(String Status) {
		String sql = "";
		if (Status.equals("A")) {
			sql = "ASC";
		}
		else if (Status.equals("D")) {
			sql = "DESC";
		}
		return sql;
	}
	
	public String getDateCondition(LtaMisReport bean,int id,String type){
		
		String dateCol="";
		String fromDateField="";
		String toDateField="";
		
		if(type.equals("claimDate")){
				dateCol = "LTA_CLAIM_DATE";
				fromDateField = bean.getClaimDateFrom();
				toDateField = bean.getClaimDateTo();
			}
		switch (id) {
		case 2: return " AND "+dateCol+" = TO_DATE('"+fromDateField+"','DD-MM-YYYY')";
		
		case 3: return " AND "+dateCol+" < TO_DATE('"+fromDateField+"','DD-MM-YYYY')";
		
		case 4: return " AND "+dateCol+" > TO_DATE('"+fromDateField+"','DD-MM-YYYY')";
		
		case 5: return " AND "+dateCol+" <= TO_DATE('"+fromDateField+"','DD-MM-YYYY')";
		
		case 6: return " AND "+dateCol+" >= TO_DATE('"+fromDateField+"','DD-MM-YYYY')";
		
		case 7: return " AND "+dateCol+"  BETWEEN TO_DATE('"+fromDateField+"','DD-MM-YYYY') AND TO_DATE('"+toDateField+"','DD-MM-YYYY') ";
					

		default:return "";
			
		}
	}
	
	public String getReport(LtaMisReport bean, HttpServletResponse response,
			String[] labelNames, HttpServletRequest request) {
		try {
			String reportType = "";
			if (bean.getReportType().equals("P")) {
				reportType = "Pdf";
			}
			if (bean.getReportType().equals("X")) {
				reportType = "Xls";
			}
			if (bean.getReportType().equals("T")) {
				reportType = "Txt";
			}
			logger.info("reportType--------------->" + reportType + "<-------");

			
			String reportName = "LTA MIS Report";
			org.paradyne.lib.report.ReportGenerator rg = new org.paradyne.lib.report.ReportGenerator(
					reportType, reportName,"");
			rg.addText("\n", 0, 0, 0);

			logger.info("multi select values  : " + bean.getHiddenColumns());
			String mutliSelectValues = bean.getHiddenColumns();
			String splitComma[] = null;
			if (!mutliSelectValues.equals("")) {
				String lastComma = mutliSelectValues.substring(0, mutliSelectValues
						.length() - 1);
				splitComma = lastComma.split(",");
			}

			int count = 0;

			logger.info("counter initial value==========" + count);

			
			
			// SELECT QUERY WITH COUNT (COUNT APPENDED AFTER #)
			String queryWithCount = selectQuery(bean, labelNames, count,
					splitComma, request);
			logger.info("queryWithCount select query==========" + queryWithCount);
			// SPLIT TO GET COUNT VALUE
			String[] splitquery = queryWithCount.split("#");

			String query = splitquery[0];
			count = Integer.parseInt(splitquery[1]);
			String labels = splitquery[2];
			
			logger.info("counter after select query==========" + count);
			logger.info("labels after select query==========" + labels);
			
			String[] str_colNames = new String[count + 2];
			str_colNames[0] = "Employee Id";
			str_colNames[1] = "Employee Name";
			int str_colNames_array = 1;
			int[] cellWidth = new int[count + 2];
			cellWidth[0] = 5;
			cellWidth[1] = 30;
			int cellWidth_array = 1;
			int[] cellAlign = new int[count + 2];
			cellAlign[0] = 0;
			cellAlign[1] = 0;
			int cellAlign_array = 1;

			// QUERY APPENDED WITH CONDITIONS
			query += conditionQuery(bean, labelNames);

			Object[][] finalObj = getSqlModel().getSingleResult(query);

			

		

			
			logger.info("Labels before setting..."+labels);
			request.setAttribute("labelValues", labels);
			// CODING FOR HEADERS, WIDTH AND ALIGNMENTS
			if (splitComma != null && splitComma.length > 0) {
				for (int i = 0; i < splitComma.length; i++) {
					String splitDash[] = splitComma[i].split("-");
					logger.info("Key....." + splitDash[0]);
					logger.info("Value....." + splitDash[1]);

					if (splitDash[1].equals(labelNames[2])) {
						str_colNames[++str_colNames_array] = labelNames[2];
						cellWidth[++cellWidth_array] = 20;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[3])) {
						str_colNames[++str_colNames_array] = labelNames[3];
						cellWidth[++cellWidth_array] = 25;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[4])) {
						str_colNames[++str_colNames_array] = labelNames[4];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[5])) {
						str_colNames[++str_colNames_array] = labelNames[5];
						cellWidth[++cellWidth_array] = 12;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[6])) {
						str_colNames[++str_colNames_array] = labelNames[6];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[7])) {
						str_colNames[++str_colNames_array] = labelNames[7];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[8])) {
						str_colNames[++str_colNames_array] = labelNames[8];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}

					else if (splitDash[1].equals(labelNames[9])) {
						str_colNames[++str_colNames_array] = labelNames[9];
						cellWidth[++cellWidth_array] = 15;
						cellAlign[++cellAlign_array] = 0;
					}
					

				}
			}
			
			Object[][] objData1 = null;
			String[] labelObject = null;

			if (finalObj != null && finalObj.length > 0) {
				objData1 = new Object[finalObj.length][count + 2];
				labelObject = new String[count + 2];
				
				// for (int j = Integer.parseInt(pageIndex[0]); j < Integer
				// .parseInt(pageIndex[1]); j++) {

				if (finalObj != null && finalObj.length > 0) {

					for (int j = 0; j < finalObj.length; ++j) {
						int int_count = 1;
						int label_count = 1;
						objData1[j][0] = checkNull(String.valueOf(finalObj[j][0]));// Employee//
						objData1[j][1] = checkNull(String.valueOf(finalObj[j][1]));
						// Token
						labelObject[0] = "Employee Id";
						labelObject[1] = "Employee Name";
						
						if (bean.getBranchChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[2];
						}

						if (bean.getBlockYrChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[3];
						}
						if (bean.getVisitYearChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[4];
						}

						if (bean.getClaimDateChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[5];
						}
						if (bean.getClaimTypeChk().equals("true")) {
							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[6];
						}
						if (bean.getClaimAmtChk().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[7];

						}
						if (bean.getExemptedChk().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[8];

						}
						if (bean.getExemptedYearChk().equals("true")) {

							objData1[j][++int_count] = checkNull(String
									.valueOf(finalObj[j][int_count]));
							labelObject[++label_count] = labelNames[9];

						}
						
						// }
					}
					
				}

				logger.info("Export all values   : " + bean.getExportAll());
				if (bean.getExportAll().equals("on")) {
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						//rg.setFName(reportName + ".xls");
						rg.setFName(reportName);
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, objData1, cellWidth,
								cellAlign);
					}
				} else {
					String[] pageIndex = Utility.doPaging(bean.getMyPage(),
							objData1.length, 20);
					if (pageIndex == null) {
						pageIndex[0] = "0";
						pageIndex[1] = "20";
						pageIndex[2] = "1";
						pageIndex[3] = "1";
						pageIndex[4] = "";
					}
					int numOfRec = Integer.parseInt(pageIndex[1])
							- Integer.parseInt(pageIndex[0]);
					int columnLength = count + 2;
					logger.info("columnLength   : " + columnLength);
					Object[][] pageObj = new Object[numOfRec][columnLength];
					int z = 0;
					int srNo = 1;
					for (int i = Integer.parseInt(pageIndex[0]); i < Integer
							.parseInt(pageIndex[1]); i++) {
						for (int j = 0; j < columnLength; j++) {
							// logger.info("objData1["+i+"]["+j+"] :
							// "+objData1[i][j]);
							pageObj[z][j] = objData1[i][j];
							// pageObj[z][0] = String.valueOf(srNo);
						}
						z++;
						srNo++;
					}
					if (bean.getReportType().equals("P")) {
						rg.setFName(reportName);
						rg.addFormatedText(reportName, 6, 0, 1, 1);
						rg.addText("\n", 0, 0, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else if (bean.getReportType().equals("X")) {
						rg.setFName(reportName + ".xls");
						rg.addText(reportName, 0, 1, 0);
						rg.addText("\n", 0, 0, 0);
						rg.xlsTableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					} else {
						rg.setFName(reportName + ".doc");
						rg.addText(reportName, 0, 1, 0);
						rg.tableBody(str_colNames, pageObj, cellWidth,
								cellAlign);
					}
				}

			} else {
				rg.addFormatedText("There is no data to display.", 0, 1, 1, 0);
			}
			rg.createReport(response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
