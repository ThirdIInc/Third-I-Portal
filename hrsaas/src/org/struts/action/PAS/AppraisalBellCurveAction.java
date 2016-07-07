package org.struts.action.PAS;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.io.File;

import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.annotations.XYTextAnnotation;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.paradyne.bean.PAS.AppraisalBellCurve;
import org.paradyne.model.PAS.AppraisalBellCurveModel;
import org.struts.lib.ParaActionSupport;

public class AppraisalBellCurveAction extends ParaActionSupport {

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(AppraisalBellCurveAction.class);
	AppraisalBellCurve bean = null;

	@Override
	public void prepare_local() throws Exception {
		bean = new AppraisalBellCurve();
		bean.setMenuCode(821);
	}

	public Object getModel() {
		return bean;
	}

	public void setAppraisalBellCurve(AppraisalBellCurve appraisalBellCurve) {
		this.bean = appraisalBellCurve;
	}

	public String input() throws Exception {
		bean.setSAppCode("");
		return INPUT;
	}

	public void prepare_withLoginProfileDetails() throws Exception {
		AppraisalBellCurveModel model = new AppraisalBellCurveModel();
		model.initiate(context, session);
		model.terminate();
	}

	public Object drawBellCurveNew() throws Exception {
		float iDeviation[][] = new float[6][3];
		boolean flg = false;

		iDeviation[0][0] = 2;
		iDeviation[0][1] = 1;
		iDeviation[0][2] = 0.1f;
		iDeviation[1][0] = 5;
		iDeviation[1][1] = 2;
		iDeviation[1][2] = 0.3f;
		iDeviation[2][0] = 10;
		iDeviation[2][1] = 3;
		iDeviation[2][2] = 0.4f;
		iDeviation[3][0] = 20;
		iDeviation[3][1] = 4;
		iDeviation[3][2] = 0.8f;
		iDeviation[4][0] = 30;
		iDeviation[4][1] = 5;
		iDeviation[4][2] = 1.0f;
		iDeviation[5][0] = 50;
		iDeviation[5][1] = 6;
		iDeviation[5][2] = 1.2f;

		int iMultiplicationFactor = 0;

		System.out.println("In drawBellCurveNew() Method :- ");
		final XYSeries dataset = new XYSeries("Bell Curve for '"
				+ bean.getSAppCode() + "'");
		int sMaxEmp = 0;
		double iMinScore = 0;

		if (getBellCurveData() == true) {

			if (bean.getLstBellCurveData() != null) {
				AppraisalBellCurve bean1 = null;
				for (int i = 0; i < bean.getLstBellCurveData().size(); i++) {
					bean1 = (AppraisalBellCurve) bean.getLstBellCurveData()
							.get(i);
					dataset.add(Double.valueOf(bean1.getSFinalScore()), Double
							.valueOf(bean1.getSNoOfEmployess()));

					if (Integer.parseInt(bean1.getSNoOfEmployess()) > sMaxEmp)
						sMaxEmp = Integer.parseInt(bean1.getSNoOfEmployess());

					if (i == 0)
						iMinScore = Double.parseDouble(bean1.getSFinalScore());
					else if (Double.parseDouble(bean1.getSFinalScore()) < iMinScore)
						iMinScore = Double.parseDouble(bean1.getSFinalScore());

				}
				iMultiplicationFactor = getMultiplicationFactor(sMaxEmp,
						iDeviation);
			}

			XYDataset data = new XYSeriesCollection(dataset);
			XYSplineRenderer splineRenderer = new XYSplineRenderer();
			splineRenderer.setPaint(Color.GREEN);

			/* Domain Axis */
			NumberAxis xAxis = new NumberAxis("Final Score");
			xAxis.setLabelFont(new Font("Verdana", Font.BOLD, 11));
			xAxis.setInverted(true);

			/* Range Axis */
			NumberAxis yAxis = new NumberAxis("No of Employee");
			yAxis.setLabelFont(new Font("Verdana", Font.BOLD, 11));
			yAxis.setAutoRangeIncludesZero(false);
			yAxis.setUpperMargin(0.60);
			yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

			XYPlot plot = new XYPlot(data, xAxis, yAxis, splineRenderer);
			plot.setRangeAxisLocation(AxisLocation.TOP_OR_LEFT);
			plot.setBackgroundPaint(new Color(238, 238, 255));

			/* Set Lower Bound */
			NumberAxis xAxis1 = (NumberAxis) plot.getDomainAxis();
			//xAxis1.setUpperMargin(0.01);
			xAxis1.setLowerBound(iMinScore - 10);
			/*-----------------*/

			plotLabels(plot, bean, iMultiplicationFactor, iDeviation);

			final JFreeChart chart = new JFreeChart("Bell Curve",
					JFreeChart.DEFAULT_TITLE_FONT, plot, true);

			try {
				final ChartRenderingInfo info = new ChartRenderingInfo(
						new StandardEntityCollection());

				final File file1 = new File(getServletContext().getRealPath(
						"/pages/images/areachart.png"));

				ChartUtilities.saveChartAsPNG(file1, chart, 500, 400, info);
			} catch (Exception e) {
				System.out.println(e);
			}

		}

		return INPUT;
	}

	private void plotLabels(XYPlot subPlot, AppraisalBellCurve bean,
			int iMultiplicationFactor, float iDeviation[][]) {
		AppraisalBellCurve bean1 = null;
		String sCategory, sRange = null;

		for (int i = 0; i < bean.getLstBellCurveData().size(); i++) {
			bean1 = (AppraisalBellCurve) bean.getLstBellCurveData().get(i);
			sCategory = bean1.getSApprScoreValue();
			
			/* Show the labels */
			final XYTextAnnotation annotation = new XYTextAnnotation(sCategory,
					Double.valueOf(bean1.getSFinalScore()), Double
							.valueOf(bean1.getSNoOfEmployess()));
			annotation.setFont(new Font("Verdana", Font.PLAIN, 9));
			annotation.setY(Double.valueOf(bean1.getSNoOfEmployess())
					+ iDeviation[iMultiplicationFactor][2] * 2);
			annotation.setPaint(Color.BLACK);
			subPlot.addAnnotation(annotation);

			//sRange = "(" + bean1.getSApprScoreFrom() + "-"
			//		+ bean1.getSApprScoreTo() + ")";
			//final XYTextAnnotation annotation1 = new XYTextAnnotation(sRange,
			//		Double.valueOf(bean1.getSFinalScore()), Double
			//				.valueOf(bean1.getSNoOfEmployess()));
			//annotation1.setFont(new Font("Verdana", Font.PLAIN, 9));
			//annotation1.setY(Double.valueOf(bean1.getSNoOfEmployess())
			//		+ (iDeviation[iMultiplicationFactor][2]));
			//annotation1.setPaint(Color.BLACK);
			//subPlot.addAnnotation(annotation1);
			
			/* Section points draw with vertical line's */
			XYLineAnnotation a1 = new XYLineAnnotation(Double.valueOf(bean1
					.getSFinalScore()), 0.0, Double.valueOf(bean1
					.getSFinalScore()), subPlot.getRangeAxis().getUpperBound() , new BasicStroke(1.0f),
					Color.LIGHT_GRAY);
			subPlot.addAnnotation(a1);
		}
	}

	private int getMultiplicationFactor(int iMaxEmp, float iDeviation[][]) {
		int iResult = 0;
		for (int i = iDeviation.length - 1; i >= 0; i--) {
			if (iMaxEmp >= iDeviation[i][0]) {
				iResult = i;
				break;
			}
		}
		return iResult;
	}

	private boolean getBellCurveData() throws Exception {
		AppraisalBellCurveModel model = new AppraisalBellCurveModel();
		Boolean bResult = false;
		model.initiate(context, session);
		bResult = model.getBellCurveData(bean);
		bean.setFlgData(bResult);
		model.terminate();
		return bResult; 
	}

	public String dashlet() throws Exception {
		request.setAttribute("dashlet", "true");
		return SUCCESS;
	}

	public String f9SelectAppraisal() throws Exception {
		/**
		 * BUILD COMPLETE QUERY (ALONG WITH PARAMETERS) WHICH GIVES THE DESIRED
		 * OUTPUT
		 */
		String query = " SELECT APPR_CAL_CODE, TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'), APPR_ID, "
				+ "'','','','','','','','' "
				+ " FROM PAS_APPR_CALENDAR WHERE APPR_ID IN "
				+ " ( SELECT DISTINCT A.APPR_ID FROM PAS_APPR_OVERALL_RATING A INNER JOIN "
				+ " PAS_APPR_SCORE B ON A.APPR_ID = B.APPR_ID )ORDER BY APPR_ID ";
		/**
		 * SET THE HEADER NAMES OF TABLE WHICH IS DISPLAYED IN POP-UP WINDOW. *
		 */
		String[] headers = { "Appraisal Code", "Start Date", "End Date" };

		/**
		 * SET THE WIDTH OF TABLE COLUMNS.
		 */
		String[] headerWidth = { "20", "30", "30" };

		/**
		 * -SET THE FIELDNAMES INTO WHICH THE VALUES ARE BEING POPULATED AFTER A
		 * ROW IS SELECTED. -USEFULL IN CASES WHERE SUBMIT FLAG IS 'false'
		 * -PARENT FORM WILL SHOW THE VALUES IN THE FILDS CORRSPONDING TO COLUMN
		 * INDEX. NOTE: LENGHT OF COLUMN INDEX MUST BE SAME AS THE LENGTH OF
		 * FIELDNAMES
		 */
		String[] fieldNames = { "sAppCode", "sAppStartDate", "sAppEndDate",
				"sAppId","divCode","divName","branchCode","branchName","deptCode","deptNameUp","apprEmpCode","apprEmpName" };

		/**
		 * SET THE COLUMN INDEX E.G. SUPPOSE THE POP-UP SHOWS 4 COLUMNS, BUT ON
		 * CLICKING A ROW ONLY SECOND AND FORTH COLUMN VALUES NEED TO BE SHOWN
		 * IN THE PARENT WINDOW FIELDS THEN THE COLUMN INDEX CAN BE {1,3}
		 * 
		 * NOTE: COLUMN NUMBERS STARTS WITH 0
		 * 
		 */
		int[] columnIndex = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

		/**
		 * WHEN SET TO 'true' WILL SUBMIT THE FORM
		 * 
		 */
		String submitFlag = "true";

		/**
		 * IF THE 'submitFlag' IS 'true' , THE FORM WILL SUBMIT AND CALL
		 * FOLLOWING METHOD IN THE ACTION * NAMING CONVENSTION: <NAME OF
		 * ACTION>_<METHOD TO CALL>.action
		 */
		String submitToMethod = "AppraisalBellCurve_drawBellCurveNew.action";
		//String submitToMethod = "AppraisalBellCurve_drawBellCurveXYSplineRenderer.action";

		/**
		 * CALL THIS METHOD AFTER ALL PARAMETERS ARE DEFINED *
		 */
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";
	}
	public String f9Div()
	{
		try
		{
			String query = " SELECT  DISTINCT DIV_NAME,DIV_ID FROM HRMS_DIVISION "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DIVISION.DIV_ID = HRMS_EMP_OFFC.EMP_DIV) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getSAppId() 
				+" ORDER BY UPPER(DIV_NAME) ";

			String[] headers = {getMessage("division")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"divName", "divCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "true";

			String submitToMethod = "AppraisalBellCurve_drawBellCurveNew.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Div
	
	public String f9Branch()
	{
		try
		{
			String query = " SELECT  DISTINCT CENTER_NAME,CENTER_ID FROM HRMS_CENTER " 
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_CENTER.CENTER_ID = hrms_emp_offc.EMP_CENTER) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (HRMS_EMP_OFFC.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getSAppId() 
				+" ORDER BY UPPER(CENTER_NAME) ";

			String[] headers = {getMessage("branch")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"branchName", "branchCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "true";

			String submitToMethod = "AppraisalBellCurve_drawBellCurveNew.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Branch
	
	/**
	 * Popup window contains list of all departments
	**/
	/**
	 * @return String f9page
	**/
	public String f9Dept()
	{
		try
		{
			String query = " SELECT DISTINCT DEPT_NAME,DEPT_ID FROM  HRMS_DEPT "
				+" INNER JOIN  HRMS_EMP_OFFC ON (HRMS_DEPT.DEPT_ID = hrms_emp_offc.EMP_DEPT) "
				+" INNER JOIN  PAS_APPR_ELIGIBLE_EMP ON (hrms_emp_offc.EMP_ID = PAS_APPR_ELIGIBLE_EMP.APPR_EMP_ID) "
				+" WHERE PAS_APPR_ELIGIBLE_EMP.APPR_ID = "+bean.getSAppId() 
				+" ORDER BY UPPER(DEPT_NAME) ";

			String[] headers = {getMessage("department")};

			String[] headerWidth = {"100"};

			String[] fieldNames = {"deptNameUp", "deptCode"};

			int[] columnIndex = {0, 1};

			String submitFlag = "true";

			String submitToMethod = "AppraisalBellCurve_drawBellCurveNew.action";

			setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
			
			return "f9page";
		}
		catch (Exception e)
		{
			return "";
		} //end of try-catch block
	} //end of f9Dept
	
	public String f9Employee()throws Exception
	{
		String query = " SELECT DISTINCT HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME,APPR_APPRAISER_CODE FROM PAS_APPR_APPRAISER " 
							+" INNER JOIN HRMS_EMP_OFFC ON HRMS_EMP_OFFC.EMP_ID = PAS_APPR_APPRAISER.APPR_APPRAISER_CODE "
							+" WHERE APPR_ID = "+bean.getSAppId() +" AND APPR_APPRAISER_CODE IS NOT NULL "  
							+" ORDER BY UPPER(HRMS_EMP_OFFC.EMP_FNAME||' '|| HRMS_EMP_OFFC.EMP_LNAME)";  
		
		String[] headers = {getMessage("appraiser")};
		String[] headerWidth = { "100" };
		String[] fieldNames = {"apprEmpName", "apprEmpCode" };
		int[] columnIndex = { 0,1 };
		String submitFlag = "true";
		String submitToMethod = "AppraisalBellCurve_drawBellCurveNew.action";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex, submitFlag, submitToMethod);
		return "f9page";
	}
}
