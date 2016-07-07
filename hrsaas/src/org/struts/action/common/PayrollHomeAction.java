package org.struts.action.common;

import org.paradyne.lib.Utility;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.HomePageModel;
import org.paradyne.model.common.PayrollHomeModel;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.struts.lib.ParaActionSupport;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import java.util.Date;
import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProducer;
import org.paradyne.bean.common.PayrollHome;

/**
 * @author Pradeep Sahoo
 * 
 */
public class PayrollHomeAction extends ParaActionSupport {

	PayrollHome ph;

	public PayrollHome getPh() {
		return ph;
	}

	public void setPh(PayrollHome ph) {
		this.ph = ph;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		ph = new PayrollHome();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return ph;
	}

	public String input() throws Exception {
		DefaultDashletConfigModel model = new DefaultDashletConfigModel();
		model.initiate(context, session);
		System.out.println("in payroll home");
		/*model.getDashletConfig("3", ph.getUserProfileId(), ph
				.getUserLoginCode(), request);*/
		model.terminate();
		return "input";
	}
	public String getPayrollMenu() throws Exception {
		HomePageModel payHomeModel=new HomePageModel();
		String menuType=request.getParameter("menuType");
		payHomeModel.initiate(context, session);
		payHomeModel.getMenuList(request, ph, menuType);
		payHomeModel.terminate();
		return "input";
	}

	//
	public String pie() {/*
							 * PayrollHomeModel hhm=new PayrollHomeModel();
							 * hhm.initiate(context,session);
							 * 
							 * DatasetProducer categoryData1 = new
							 * DatasetProducer() { public Object
							 * produceDataset(Map params) {
							 * 
							 * final String[] categorieNames =
							 * {"HR","Sales","Management","Account"}; final
							 * String[] seriesNames = {"HR"};
							 * //{String.valueOf(cent[0][0])};
							 * 
							 * final Integer[][] startValues = new
							 * Integer[seriesNames.length][categorieNames.length];
							 * final Integer[][] endValues = new
							 * Integer[seriesNames.length][categorieNames.length];
							 * for (int series = 0; series < seriesNames.length;
							 * series++) { try{ for (int i = 0; i
							 * <categorieNames.length; i++) {
							 * 
							 * int y = (int) (Math.random() * 10 + 1);;
							 * startValues[series][i] = new Integer(y);
							 * endValues[series][i] = new Integer(y + (int)
							 * (Math.random() * 10)); } }catch(Exception e){
							 * e.printStackTrace(); } }
							 * DefaultIntervalCategoryDataset ds = new
							 * DefaultIntervalCategoryDataset(seriesNames,
							 * categorieNames, startValues, endValues); return
							 * ds; } public String getProducerId() { return
							 * "CategoryDataProducer"; } public boolean
							 * hasExpired(Map params, Date since) { return
							 * false; } }; request.setAttribute("categoryData1",
							 * categoryData1);
							 * 
							 * 
							 * DatasetProducer categoryData2 = new
							 * DatasetProducer() { public Object
							 * produceDataset(Map params) {
							 * 
							 * final String[] categorieNames =
							 * {"Cuttack","Mumbai","Coimbatore","Nasik"}; final
							 * String[] seriesNames = {"HR"};
							 * //{String.valueOf(cent[0][0])};
							 * 
							 * final Integer[][] startValues = new
							 * Integer[seriesNames.length][categorieNames.length];
							 * final Integer[][] endValues = new
							 * Integer[seriesNames.length][categorieNames.length];
							 * for (int series = 0; series < seriesNames.length;
							 * series++) { try{ for (int i = 0; i
							 * <categorieNames.length; i++) {
							 * 
							 * int y = (int) (Math.random() * 10 + 1);;
							 * startValues[series][i] = new Integer(y);
							 * endValues[series][i] = new Integer(y + (int)
							 * (Math.random() * 10)); } }catch(Exception e){
							 * e.printStackTrace(); } }
							 * DefaultIntervalCategoryDataset ds = new
							 * DefaultIntervalCategoryDataset(seriesNames,
							 * categorieNames, startValues, endValues); return
							 * ds; } public String getProducerId() { return
							 * "CategoryDataProducer"; } public boolean
							 * hasExpired(Map params, Date since) { return
							 * false; } }; request.setAttribute("categoryData2",
							 * categoryData2);
							 * 
							 * hhm.terminate();
							 */
		return "success";
	}

	public String piee() {

		PayrollHomeModel model = new PayrollHomeModel();
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

		
		String query = "SELECT SAL_NET_SALARY,LEDGER_MONTH"
				+ " FROM HRMS_SALARY_" + previousYear
				+ " INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_"
				+ previousYear
				+ ".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
				+ " where LEDGER_YEAR=" + previousYear + " AND LEDGER_MONTH>=4"
				+ " AND EMP_ID=" + ph.getUserEmpId()
				+ " ORDER BY LEDGER_MONTH";
		Object[][] queryData = model.getSqlModel().getSingleResult(query);

		Object obj[][] = new Object[9][2];
		int cnt = 0;
		for (int i = 4, k = 0; i < 13; i++) {

			if (cnt < queryData.length
					&& i == Integer.parseInt(String.valueOf(queryData[cnt][1])))
				obj[k][0] = queryData[cnt++][0];
			else
				obj[k][0] = 0;
			obj[k][1] = Utility.month(i);
			k++;

		}	
		for (int i = 0; i < obj.length; i++) {

			System.out.println("objData---------------------" + obj[i][0]);
			System.out.println("objData---------------------" + obj[i][1]);
		}

		System.out.println("queryData.length------------" + queryData.length);
		String query1 = "SELECT SAL_NET_SALARY,LEDGER_MONTH"
				+ " FROM HRMS_SALARY_" + nextYear
				+ " INNER JOIN HRMS_SALARY_LEDGER ON(HRMS_SALARY_" + nextYear
				+ ".SAL_LEDGER_CODE=HRMS_SALARY_LEDGER.LEDGER_CODE)"
				+ " where  LEDGER_YEAR=" + nextYear + " AND LEDGER_MONTH<4"
				+ " AND EMP_ID=" + ph.getUserEmpId()
				+ " ORDER BY LEDGER_MONTH";
		Object[][] query1Data = model.getSqlModel().getSingleResult(query1);

		System.out.println("query1Data.length------------" + query1Data.length);

		Object objData[][] = new Object[3][2];
		int count = 0;
		for (int i = 1, k = 0; i < 4; i++) {

			if (count < query1Data.length
					&& i == Integer.parseInt(String
							.valueOf(query1Data[count][1])))
				objData[k][0] = query1Data[count++][0];
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
				String s = "Net Salary ";
				DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
				for (int series = 0; series < seriesNames.length; series++) {
					try {

						for (int i = 0; i < categorieNames.length; i++) {
							defaultcategorydataset.addValue(Double.parseDouble(String.valueOf(finalObj[i][0]))/1000,
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
		return "annualCost";
	}

	public String piee1() {
		PayrollHomeModel hhm = new PayrollHomeModel();
		hhm.initiate(context, session);

		DatasetProducer categoryData1 = new DatasetProducer() {
			public Object produceDataset(Map params) {

				final String[] categorieNames = { "HR", "Sales", "Management",
						"Account", "Purchase", "Investment" };
				final String[] seriesNames = { "HR" };
				// {String.valueOf(cent[0][0])};

				final Integer[][] startValues = new Integer[seriesNames.length][categorieNames.length];
				final Integer[][] endValues = new Integer[seriesNames.length][categorieNames.length];
				for (int series = 0; series < seriesNames.length; series++) {
					try {
						for (int i = 0; i < categorieNames.length; i++) {

							int y = (int) (Math.random() * 10 + 1);
							;
							startValues[series][i] = new Integer(y);
							endValues[series][i] = new Integer(y
									+ (int) (Math.random() * 10));
						}
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
				return false;
			}
		};
		request.setAttribute("categoryData1", categoryData1);

		/*
		 * DatasetProducer categoryData2 = new DatasetProducer() { public Object
		 * produceDataset(Map params) {
		 * 
		 * final String[] categorieNames =
		 * {"Cuttack","Mumbai","Coimbatore","Nasik"}; final String[] seriesNames =
		 * {"HR"}; //{String.valueOf(cent[0][0])};
		 * 
		 * final Integer[][] startValues = new
		 * Integer[seriesNames.length][categorieNames.length]; final Integer[][]
		 * endValues = new Integer[seriesNames.length][categorieNames.length];
		 * for (int series = 0; series < seriesNames.length; series++) { try{
		 * for (int i = 0; i <categorieNames.length; i++) {
		 * 
		 * int y = (int) (Math.random() * 10 + 1);; startValues[series][i] = new
		 * Integer(y); endValues[series][i] = new Integer(y + (int)
		 * (Math.random() * 10)); } }catch(Exception e){ e.printStackTrace(); } }
		 * DefaultIntervalCategoryDataset ds = new
		 * DefaultIntervalCategoryDataset(seriesNames, categorieNames,
		 * startValues, endValues); return ds; } public String getProducerId() {
		 * return "CategoryDataProducer"; } public boolean hasExpired(Map
		 * params, Date since) { return false; } };
		 * request.setAttribute("categoryData2", categoryData2);
		 */
		hhm.terminate();
		return "annualCost";
	}

	public String pendingAvt() {

		return "pendingAct";
	}

	public String pendingQuery() {

		return "pendingQuery";
	}

	public String annualSal() {

		return "annualSal";
	}

}
