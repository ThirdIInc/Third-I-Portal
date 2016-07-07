package org.struts.action.common;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.common.AdminHome;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.plot.Plot;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;

import de.laures.cewolf.DatasetProducer;

import java.awt.Color;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import de.laures.cewolf.ChartPostProcessor;

import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.AdminHomeModel;
import org.paradyne.model.common.HomePageModel;
import org.paradyne.model.common.LeaveHomeModel;
import org.paradyne.model.common.PerformanceHomeModel;
import org.jfree.ui.RefineryUtilities;
import org.jfree.util.Rotation;

public class AdminHomeAction extends ParaActionSupport {

	AdminHome adHm;

	public AdminHome getAdHm() {
		return adHm;
	}

	public void setAdHm(AdminHome adHm) {
		this.adHm = adHm;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub

		adHm = new AdminHome();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return adHm;
	}

	public String input() throws Exception {
		DefaultDashletConfigModel model = new DefaultDashletConfigModel();
		model.initiate(context, session);

		model.getDashletConfig("1", adHm.getUserProfileId(), adHm
				.getUserEmpId(), request);

		model.getDashletConfig("1", adHm.getUserProfileId(), adHm
				.getUserLoginCode(), request);

		model.terminate();
		return "adminPage";
	}

	/*
	 * public String combineGraph(){
	 * 
	 * 
	 * DatasetProducer categoryData = new DatasetProducer() { public Object
	 * produceDataset(Map params) { final String[] categories = { "Training",
	 * "Assets", "Staffing", "Marketing","Sales"}; final String[] seriesNames = {
	 * "Balance","Utilized"}; final Integer[][] startValues = new
	 * Integer[seriesNames.length][categories.length]; final Integer[][]
	 * endValues = new Integer[seriesNames.length][categories.length]; for (int
	 * series = 0; series < seriesNames.length; series++) { for (int i = 0; i <
	 * categories.length; i++) { int y = (int) (Math.random() * 10 + 1);
	 * startValues[series][i] = new Integer(y); endValues[series][i] = new
	 * Integer(y + (int) (Math.random() * 10)); } }
	 * DefaultIntervalCategoryDataset ds = new
	 * DefaultIntervalCategoryDataset(seriesNames, categories, startValues,
	 * endValues); return ds; } public String getProducerId() { return
	 * "CategoryDataProducer"; } public boolean hasExpired(Map params, Date
	 * since) { return false; } };
	 * 
	 * 
	 * 
	 * request.setAttribute("categoryData", categoryData);
	 * 
	 * return "budget"; }
	 */
	private static CategoryDataset createDataset2() {
		String s = "S1";

		String s3 = "Category 1";
		String s4 = "Category 2";
		String s5 = "Category 3";
		String s6 = "Category 4";
		String s7 = "Category 5";
		String s8 = "Category 6";
		String s9 = "Category 7";
		String s10 = "Category 8";
		DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
		defaultcategorydataset.addValue(1.0D, s, s3);
		defaultcategorydataset.addValue(4D, s, s4);
		defaultcategorydataset.addValue(3D, s, s5);
		defaultcategorydataset.addValue(5D, s, s6);
		defaultcategorydataset.addValue(5D, s, s7);
		defaultcategorydataset.addValue(7D, s, s8);
		defaultcategorydataset.addValue(7D, s, s9);
		defaultcategorydataset.addValue(8D, s, s10);
		return defaultcategorydataset;
	}

	public String combineGraph() throws Exception {
		AdminHomeModel model = new AdminHomeModel();
		model.initiate(context, session);
		// final Object[][] cent = model.getCenter(pa);
		// final Object[][] payBill=model.getPayBill(pa);
		// Centerwise
		DatasetProducer categoryData = new DatasetProducer() {
			public Object produceDataset(Map params) {

				String s = "S1";

				String s3 = "Category 1";
				String s4 = "Category 2";
				String s5 = "Category 3";
				String s6 = "Category 4";
				String s7 = "Category 5";
				String s8 = "Category 6";
				String s9 = "Category 7";
				String s10 = "Category 8";
				DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
				defaultcategorydataset.addValue(1.0D, s, s3);
				defaultcategorydataset.addValue(4D, s, s4);
				defaultcategorydataset.addValue(3D, s, s5);
				defaultcategorydataset.addValue(5D, s, s6);
				defaultcategorydataset.addValue(5D, s, s7);
				defaultcategorydataset.addValue(7D, s, s8);
				defaultcategorydataset.addValue(7D, s, s9);
				defaultcategorydataset.addValue(8D, s, s10);

				return defaultcategorydataset;
			}

			public String getProducerId() {
				return "CategoryDataProducer";
			}

			public boolean hasExpired(Map params, Date since) {
				return false;
			}
		};
		request.setAttribute("categoryData", categoryData);

		ChartPostProcessor meterPP = new ChartPostProcessor() {

			public void processChart(Object chart, Map params) {

				CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart)
						.getPlot();

				categoryplot.setBackgroundPaint(new Color(238, 238, 255));
				CategoryDataset categorydataset = createDataset2();
				categoryplot.setDataset(1, categorydataset);
				categoryplot.mapDatasetToRangeAxis(1, 1);
				CategoryAxis categoryaxis = categoryplot.getDomainAxis();
				categoryaxis
						.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
				NumberAxis numberaxis = new NumberAxis("Secondary");
				categoryplot.setRangeAxis(1, numberaxis);
				LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
				lineandshaperenderer
						.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
				categoryplot.setRenderer(1, lineandshaperenderer);
				categoryplot
						.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				LegendTitle legendtitle = new LegendTitle(categoryplot
						.getRenderer(0));
				legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
				legendtitle.setFrame(new BlockBorder());
				LegendTitle legendtitle1 = new LegendTitle(categoryplot
						.getRenderer(1));
				legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
				legendtitle1.setFrame(new BlockBorder());
				BlockContainer blockcontainer = new BlockContainer(
						new BorderArrangement());
				blockcontainer.add(legendtitle, RectangleEdge.LEFT);
				blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
				blockcontainer.add(new EmptyBlock(2000D, 0.0D));
				CompositeTitle compositetitle = new CompositeTitle(
						blockcontainer);
				compositetitle.setPosition(RectangleEdge.BOTTOM);
				categoryplot.setForegroundAlpha(0.6F);

			}
		};

		DatasetProducer categoryData1 = new DatasetProducer() {
			public Object produceDataset(Map params) {
				final String[] categories = { "Training", "Assets", "Staffing",
						"Marketing", "Sales" };
				final String[] seriesNames = { "Balance", "Utilized" };
				final Integer[][] startValues = new Integer[seriesNames.length][categories.length];
				final Integer[][] endValues = new Integer[seriesNames.length][categories.length];
				for (int series = 0; series < seriesNames.length; series++) {
					for (int i = 0; i < categories.length; i++) {
						int y = (int) (Math.random() * 10 + 1);
						startValues[series][i] = new Integer(y);
						endValues[series][i] = new Integer(y
								+ (int) (Math.random() * 10));
					}
				}
				DefaultIntervalCategoryDataset ds = new DefaultIntervalCategoryDataset(
						seriesNames, categories, startValues, endValues);
				return ds;
			}

			public String getProducerId() {
				return "CategoryDataProducer";
			}

			public boolean hasExpired(Map params, Date since) {
				return false;
			}
		};

		request.setAttribute("categoryData", categoryData);
		request.setAttribute("meterRanges", meterPP);

		model.terminate();

		return "budget";

	}

	public String conference() throws Exception {

		AdminHomeModel model = new AdminHomeModel();
		model.initiate(context, session);
		model.getConf(adHm);
		model.terminate();

		return "conference";
	}

	public String pendingVoucher() throws Exception {

		AdminHomeModel model = new AdminHomeModel();
		model.initiate(context, session);
		model.voucher(adHm);
		model.terminate();

		return "voucher";
	}

	public String assets() throws Exception {

		String chk = "";
		String assettype = "";
		try {
			chk = (String) request.getParameter("id");
			assettype = (String) request.getParameter("id1");

			System.out.println("value of chk---------------------------" + chk);
			System.out.println("value of  asset type ____________________"
					+ assettype);
		} catch (Exception e) {
			e.printStackTrace();
			chk = "";
			assettype = "";
			// TODO: handle exception
		}

		AdminHomeModel model = new AdminHomeModel();
		model.initiate(context, session);

		TreeMap map = new TreeMap();
		TreeMap map1 = new TreeMap();
		Object[][] data = null;
		Object[][] data1 = null;
		if (chk != null && !chk.equals("")) {

			String sql = " SELECT  ASSET_CATEGORY_CODE, ASSET_CATEGORY_NAME    FROM  HRMS_ASSET_CATEGORY  ";
			// + " where ASSET_CATEGORY_CODE= "+chk;
			data = model.getSqlModel().getSingleResult(sql);

			for (int j = 0; j < data.length; j++) {

				map.put(String.valueOf(data[j][0]), String.valueOf(data[j][1]));

			}// end inner for

			adHm.setAssetmap(map);
			adHm.setAssetCategory(chk);

			String sql1 = "  Select  ASSET_SUBTYPE_CODE,ASSET_SUBTYPE_NAME   from HRMS_ASSET_SUBTYPE "
					+ " where  ASSET_CATEGORY_CODE=	" + chk;

			data1 = model.getSqlModel().getSingleResult(sql1);

			for (int j = 0; j < data1.length; j++) {

				map1.put(String.valueOf(data1[j][0]), String
						.valueOf(data1[j][1]));

			}// end inner for

			adHm.setAssetmap1(map1);
			if (assettype != null && !assettype.equals("")) {
				adHm.setAssetType(assettype);
				}

			else {
				if (data1 != null && data1.length > 0) {
					adHm.setAssetType(String.valueOf(data1[0][0]));
				
				}

			}
		} else {

			String[] str = model.getAssetRecord(adHm);
			adHm.setAssetCategory(str[0]);
			adHm.setAssetType(str[1]);

		}

		final String[] chartValues;

		String type = "";
		String cat = "";
		if (chk == null || chk.equals("null") || chk.equals("")) {
			type = "1";
			cat = "1";
			chartValues = model.getChart(type, cat);

		} else {
			chartValues = model.getChart(adHm.getAssetCategory(), adHm
					.getAssetType());

		}

		if (request.getAttribute("initFlag") == null) {
			DatasetProducer pieData = new DatasetProducer() {

				public Object produceDataset(Map params) {

					final String[] categories = { "U:Utilized", "B:Balance" };
					DefaultPieDataset ds = new DefaultPieDataset();
				
					if (chartValues != null && chartValues.length > 0) {
						for (int i = 0; i < chartValues.length; i++) {
							// int y = (int) (Math.random()* 10 + 1);
							
							double y = 0;
							try {
								y = Double.parseDouble(String
										.valueOf(chartValues[i]));
							} catch (Exception e) {
								// TODO: handle exception
								y =0.00;
							}
							ds.setValue(categories[i], new Double(y));

						}
					}
					return ds;

				}

				public String getProducerId() {
					return "PieDataProducer";
				}

				public boolean hasExpired(Map params, Date since) {
					return true;
				}
			};
			ChartPostProcessor meterPP = new ChartPostProcessor() {
				// JFreeChart jfreechart = createStandardDialChart("Dial Demo
				// 1", "Temperature", dataset, -40D, 60D, 10D, 4);
				public void processChart(Object chart, Map params) {
					// dataset = new DefaultValueDataset(10D);
					// logger.info("_________________________________IN
					// processChart_____________________________________");

					// DialPlot dialplot = (DialPlot) ((JFreeChart)
					// chart).getPlot();

					PiePlot3D pieplot3d = (PiePlot3D) ((JFreeChart) chart)
							.getPlot();

					/*
					 * pieplot.setSectionPaint( 1,new Color(128, 128, 223));
					 * pieplot.setSectionPaint( 2,new Color(96, 96, 191));
					 * pieplot.setSectionPaint( 3,new Color(64, 64, 159));
					 * pieplot.setSectionPaint(4, new Color(32, 32, 127));
					 */

					pieplot3d.setSectionPaint("Six", new Color(0, 0, 111));
					pieplot3d.setNoDataMessage("No data available");
					pieplot3d.setExplodePercent("Two", 0.20000000000000001D);
					pieplot3d
							.setLabelGenerator(new StandardPieSectionLabelGenerator(
									"{2}"));
					// pieplot3d.setLabelBackgroundPaint(Color.white);
					pieplot3d.setLabelLinksVisible(false);

					pieplot3d.setLabelLinkPaint(Color.white);
					pieplot3d.setOutlineStroke(null);
					pieplot3d.setLabelOutlineStroke(null);
					pieplot3d.setLabelOutlinePaint(null);
					pieplot3d.setLabelPaint(Color.LIGHT_GRAY);
					// pieplot3d.setLegendLabelToolTipGenerator(new
					// StandardPieSectionLabelGenerator("Tooltip for legend item
					// {0}"));
					pieplot3d.setSimpleLabels(true);
					pieplot3d.setInteriorGap(0.0D);
					// DialPlot dialplot = (DialPlot)jfreechart.getPlot();

					pieplot3d.setStartAngle(270D);
					pieplot3d.setDirection(Rotation.ANTICLOCKWISE);
					pieplot3d.setForegroundAlpha(0.6F);
					pieplot3d.setCircular(true, false);

					// pieplot3d.setInteriorGap(0.33000000000000002D);

				}
			};

			request.setAttribute("meterRanges", meterPP);

			request.setAttribute("pieData", pieData);
		}

		model.getAsset(adHm);
		model.terminate();

		return "asset";

	}
	
	
	
  /* Added by Priyanka*/
	public String getAdminMenu() throws Exception {
		HomePageModel ahm=new HomePageModel();
		String menuType=request.getParameter("menuType");
		ahm.initiate(context, session);
		ahm.getMenuList(request, adHm, menuType);
		ahm.terminate();
		return "adminPage";
	}
	/*Ended By Priyanka*/
	
}
