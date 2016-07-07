package org.struts.action.common;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.struts.lib.ParaActionSupport;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.paradyne.lib.Utility;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.PayrollHomeModel;
import org.paradyne.model.common.TaxHomeModel;
import org.paradyne.bean.common.AdminHome;
import org.paradyne.bean.common.TaxHome;

import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProducer;

public class TaxHomeAction extends ParaActionSupport {
	
	TaxHome th;

	public TaxHome getTh() {
		return th;
	}

	public void setTh(TaxHome th) {
		this.th = th;
	}
	
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		th=new TaxHome();
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return th;
	}
	
	
	public String pending() {
		
		return "pending";
		
	}
	
	public String input() throws Exception {
		DefaultDashletConfigModel model  = new DefaultDashletConfigModel();
		model.initiate(context, session);
		model.getDashletConfig("413", th.getUserProfileId(),th.getUserLoginCode(), request);
		model.terminate();
		return "dashboard";
	}
	
	public String piee() {

		TaxHomeModel model = new TaxHomeModel();
		//To find current year and next year
		Date date = new Date();
		SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yyyy");
		String sysDate = formater.format(date);
		String year = sysDate.substring(6, 10);
		String month = sysDate.substring(3, 5);
		model.initiate(context, session);
		int previousYear;
		int nextYear;
		if (Integer.parseInt(month) < 4) {
			previousYear = Integer.parseInt(year) - 1;
			nextYear = Integer.parseInt(year);

		} else {
			previousYear = Integer.parseInt(year);
			nextYear = Integer.parseInt(year) + 1;
		}

		String preQurey="SELECT SAL_AMOUNT,LEDGER_MONTH "
			+ " FROM HRMS_SAL_DEBITS_"+ previousYear
			+ " INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SAL_DEBITS_"+ previousYear+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)" 
			//+ " INNER JOIN HRMS_TAX_PARAMETER ON(HRMS_SAL_DEBITS_"+ previousYear+".SAL_DEBIT_CODE=HRMS_TAX_PARAMETER.TDS_CODE) "
			+ " where  LEDGER_YEAR=" + previousYear + " AND LEDGER_MONTH>=4 AND EMP_ID=" + th.getUserEmpId()
			+ " AND SAL_DEBIT_CODE=(SELECT TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER "
			+ " WHERE to_char(TDS_EFF_DATE,'yyyy-mm')<=to_char(SYSDATE,'yyyy-mm')) ORDER BY LEDGER_MONTH";
		Object[][] preQureyData = model.getSqlModel().getSingleResult(preQurey);

		Object obj[][] = new Object[9][2];
		int cnt = 0;
		for (int i = 4, k = 0; i < 13; i++) {

			if (cnt < preQureyData.length
					&& i == Integer.parseInt(String.valueOf(preQureyData[cnt][1])))
				obj[k][0] = preQureyData[cnt++][0];
			else
				obj[k][0] = 0;
			obj[k][1] = Utility.month(i);
			k++;

		}	
		for (int i = 0; i < obj.length; i++) {

			System.out.println("objData---------------------" + obj[i][0]);
			System.out.println("objData---------------------" + obj[i][1]);
		}


		System.out.println("preQureyData.length------------" + preQureyData.length);
		
		String nextQurey="SELECT SAL_AMOUNT,LEDGER_MONTH "
			+ " FROM HRMS_SAL_DEBITS_"+ previousYear
			+ " INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SAL_DEBITS_"+ previousYear+".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)" 
			//+ " INNER JOIN HRMS_TAX_PARAMETER ON(HRMS_SAL_DEBITS_"+ previousYear+".SAL_DEBIT_CODE=HRMS_TAX_PARAMETER.TDS_CODE) "
			+ " where  LEDGER_YEAR=" + previousYear + " AND LEDGER_MONTH < 4 AND EMP_ID=" + th.getUserEmpId()
			+ " AND SAL_DEBIT_CODE=(SELECT TDS_DEBIT_CODE FROM  HRMS_TAX_PARAMETER "
			+ " WHERE to_char(TDS_EFF_DATE,'yyyy-mm')<=to_char(SYSDATE,'yyyy-mm')) ORDER BY LEDGER_MONTH" ;
		
		
		Object[][] nextQureyData = model.getSqlModel().getSingleResult(nextQurey);

		System.out.println("nextQureyData.length------------" + nextQureyData.length);

		Object objData[][] = new Object[3][2];
		int count = 0;
		for (int i = 1, k = 0; i < 4; i++) {

			if (count < nextQureyData.length
					&& i == Integer.parseInt(String
							.valueOf(nextQureyData[count][1])))
				objData[k][0] = nextQureyData[count++][0];
			else
				objData[k][0] = 0;
			objData[k][1] = Utility.month(i);
			k++;

		}		
		for (int i = 0; i < objData.length; i++) {

			System.out.println("objData---------------------" + objData[i][0]);
			System.out.println("objData---------------------" + objData[i][1]);
		}

		final Object[][] finalObj = Utility.joinArrays(obj, objData, 1, 0);

		model.terminate();
		

		
		final String[] categorieNames = { "April", "May", "June", "July",
				"August", "September", "October", "November", "December",
				"January", "February", "March" };
		final String[] seriesNames = { "Net Salary" };

		DatasetProducer categoryData1 = new DatasetProducer() {
			public Object produceDataset(Map params) {

				final Integer[][] startValues = new Integer[seriesNames.length][categorieNames.length];
				final Integer[][] endValues = new Integer[seriesNames.length][categorieNames.length];
				String s = "Annual Tax";
				DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
				for (int series = 0; series < seriesNames.length; series++) {
					try {

						for (int i = 0; i < categorieNames.length; i++) {
							defaultcategorydataset.addValue(Integer
									.parseInt(String.valueOf(finalObj[i][0])),
									s, categorieNames[i]);
							int y = (int) (Math.random() * 10 + 1);
							startValues[series][i] = new Integer(y);
							endValues[series][i] = new Integer(y
									+ (int) (Math.random() * 10));
						}
						return defaultcategorydataset;

					} catch (Exception e) {
						e.printStackTrace();
					}

				}
				DefaultIntervalCategoryDataset ds = new DefaultIntervalCategoryDataset(
						seriesNames, categorieNames, startValues, endValues);
				return ds;
			}

			public String getProducerId() {
				return "CategoryDataProducer";
			}

			public boolean hasExpired(Map params, Date since) {
				return true;
			}
		};

		ChartPostProcessor meterPP = new ChartPostProcessor() {

			public void processChart(Object chart, Map params) {

				try {
					// for no data on display
					CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart)
							.getPlot();
					categoryplot.setNoDataMessage("No Data Available");
					// for displaying labels diagonally.
					CategoryAxis categoryaxis = categoryplot.getDomainAxis();
					categoryaxis
							.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
					//display values on graph
					CategoryItemRenderer categoryitemrenderer = categoryplot
							.getRenderer();
					categoryitemrenderer
							.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(
									"{2}", NumberFormat.getNumberInstance()));
					categoryitemrenderer.setSeriesItemLabelsVisible(0,
							Boolean.TRUE);

				} catch (Exception e) {
					// logger.error("Exception in processChart method:",e);
				}

			}// end of Process method
		};// end of Chart processor - details

		request.setAttribute("meterRanges", meterPP);
		request.setAttribute("categoryData1", categoryData1);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("previousYear", previousYear);
		return "taxPaid";
	}
	
	public String taxSt() {
		
		
		return "taxSt";
	}
	
	

}
