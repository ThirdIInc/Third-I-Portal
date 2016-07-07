package org.struts.action.common;

import org.struts.lib.ParaActionSupport;
import org.paradyne.bean.common.*;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.*;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import de.laures.cewolf.DatasetProducer;
import java.awt.Color;
import java.io.File;
import java.util.Date;
import java.util.Vector;
import java.util.Map;
import javax.swing.JSlider;
import de.laures.cewolf.ChartPostProcessor;

public class PerformanceHomeAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	PerformanceAppr pa;
	JSlider slider;

	public PerformanceAppr getPa() {
		return pa;
	}

	public void setPa(PerformanceAppr pa) {
		this.pa = pa;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		pa = new PerformanceAppr();
	}

	public Object getModel() {
		return pa;
	}

	public String input() throws Exception {
		DefaultDashletConfigModel model = new DefaultDashletConfigModel();
		model.initiate(context, session);
		model.getDashletConfig("412", pa.getUserProfileId(), pa
				.getUserLoginCode(), request);
		model.terminate();
		return "dashboard";
	}

	public String setEmpPerformance() throws Exception {
		/*logger.info("apprId in setEmpPerformance==="+request.getParameter("apprId"));
		logger.info("empId in setEmpPerformance==="+request.getParameter("empId"));
		logger.info("apprId in getAttribute==="+request.getAttribute("apprId"));
		logger.info("empId in getAttribute==="+request.getAttribute("empId"));*/
		request.setAttribute("dashletId", request.getParameter("dashId"));
		request.setAttribute("dashletWidth", request.getParameter("dashWidth"));
		empPie(true); //empPie
		return "empPerformance";
	}

	public String searchEmpPerformance() throws Exception {
		/*logger.info("apprId in setEmpPerformance==="+request.getParameter("apprId"));
		logger.info("empId in setEmpPerformance==="+request.getParameter("empId"));
		request.setAttribute("empId",request.getParameter("empId"));
		request.setAttribute("apprId",request.getParameter("apprId"));
		logger.info("apprId in getAttribute==="+request.getAttribute("apprId"));
		logger.info("empId in getAttribute==="+request.getAttribute("empId"));*/
		request.setAttribute("dashletId", request.getParameter("dashId"));
		request.setAttribute("dashletWidth", request.getParameter("dashWidth"));
		empPie(false);
		return "empPerformance";
	}

	public String performance() {
		//LoginModel loginModel = new LoginModel();
		//loginModel.initiate(context,session);
		PerformanceHomeModel model = new PerformanceHomeModel();
		model.initiate(context, session);
		String[][] genList = null;
		String path = context.getRealPath("\\")
				+ "\\WEB-INF\\classes\\org\\paradyne\\dataFiles\\my_Performance\\myPerformance.xml";

		model.getMyPerf(pa, request);
		try {
			System.out
					.println("in tryyyyyyyyyy---------------------------------------->>"
							+ path);
			genList = model.processGenInfo(new XMLReaderWriter()
					.parse(new File(path)), request);

		} catch (Exception e) {
			e.printStackTrace();
		}
		String empid = (String) request.getAttribute("empId");
		final String value = (String) request.getAttribute("value");
		System.out
				.println("value of empId12------------------------------------[>>"
						+ empid);
		System.out
				.println("value of value12------------------------------------[>>"
						+ value);
		//final String[][] str = loginModel.getCalls();
		//final String[][] strMon=loginModel.getChart();
		if (request.getAttribute("initFlag") == null) {/*
					  DatasetProducer valueDatasetProducer = new DatasetProducer() {
						    public Object produceDataset(Map params) throws DatasetProduceException {
						      Integer val = new Integer(10);				    
						      DefaultValueDataset data = new DefaultValueDataset(Double.parseDouble(performance)); 
						      return data;
						    }
						    public String getProducerId() {
						      return "meterdata";
						    }
							public boolean hasExpired(Map params, Date since) {
								return true;
							}
						  };
					  ChartPostProcessor meterPP = new ChartPostProcessor() {
						  //JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "Temperature", dataset, -40D, 60D, 10D, 4);
						    public void processChart(Object chart, Map params) {
						    	 //dataset = new DefaultValueDataset(10D);
						    	 logger.info("_________________________________IN  processChart_____________________________________");
						        				    	 
						    	 DialPlot dialplot = (DialPlot) ((JFreeChart) chart).getPlot();
						    	 
						    	 String img =getServletContext().getRealPath("//")+"/pages/images/family/";
						    	 com.lowagie.text.Image image=null;
									try {
										image = com.lowagie.text.Image.getInstance(img);
									} catch (Exception e) {						
										logger.info("image not available in server------------------"+e);							
									}
						    	 //dialplot.setBackgroundImage(image);
						       // DialPlot dialplot = (DialPlot)jfreechart.getPlot();
						    	 GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
						           
						    	 DialBackground dialbackground = new DialBackground(gradientpaint);
						            dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
						           
						    	  ArcDialFrame arcdialframe = new ArcDialFrame(-175D, -190D);
						          arcdialframe.setInnerRadius(0.20D);
						            arcdialframe.setOuterRadius(0.96D);
						            //arcdialframe.setForegroundPaint(Color.darkGray);
						            arcdialframe.setStroke(new BasicStroke(0F));
						            arcdialframe.setBackgroundPaint(new Color(170, 170, 220));
						            arcdialframe.setForegroundPaint(new Color(170, 170, 220));
						            dialplot.setDialFrame(arcdialframe);
						      //  dialplot.setDataset(valuedataset);
						          //dialplot.setDialFrame(new StandardDialFrame());
						          dialplot.setBackground(new DialBackground());
						          DialTextAnnotation dialtextannotation = new DialTextAnnotation("Performance");
						          dialtextannotation.setFont(new Font("Dialog", 1, 12));
						          dialtextannotation.setAngle(90D);
						          dialtextannotation.setRadius(0.39999999999999996D);
						          dialplot.addLayer(dialtextannotation);
						          DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
						          dialvalueindicator.setAngle(15D);
						          dialvalueindicator.setRadius(0.344444444444444D);
						          dialvalueindicator.setBackgroundPaint(new Color(170, 170, 220));
						          dialvalueindicator.setOutlinePaint(new Color(170, 170, 220));
						          dialplot.addLayer(dialvalueindicator);
						          StandardDialScale standarddialscale =
						        	  new StandardDialScale(0D, 100D, -180D, -180D, 20D, 0);				          
						          //StandardDialScale standarddialscale = new StandardDialScale(-40D, 60D, -120D, -300D, 10D, 4);				        	 
						          standarddialscale.setMajorTickIncrement(10D);
						          standarddialscale.setMinorTickCount(4);
						          standarddialscale.setTickRadius(0.88D);
						          standarddialscale.setTickLabelOffset(0.14999999999999999D);
						          standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
						          dialplot.addScale(0, standarddialscale);
						          dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
						          DialCap dialcap = new DialCap();
						          dialcap.setRadius(0.20D);
						          dialcap.setFillPaint(Color.YELLOW);
						          dialcap.setVisible(false);
						          dialplot.setCap(dialcap);
						        logger.info("__________________" +
						        		"_______________IN  processChart_____________________________________");
						    	   StandardDialRange standarddialrange = new StandardDialRange(60D, 100D, Color.green);
						            standarddialrange.setInnerRadius(0.52000000000000002D);
						            standarddialrange.setPaint(Color.green);
						            standarddialrange.setOuterRadius(0.55000000000000004D);
						            dialplot.addLayer(standarddialrange);
						            StandardDialRange standarddialrange1 = new StandardDialRange(40D, 60D, Color.orange);
						            standarddialrange1.setInnerRadius(0.52000000000000002D);
						            standarddialrange1.setOuterRadius(0.55000000000000004D);
						            standarddialrange1.setPaint(Color.orange);
						            dialplot.addLayer(standarddialrange1);
						            StandardDialRange standarddialrange2 = new StandardDialRange(0D, 40D, Color.red);
						            standarddialrange2.setInnerRadius(0.52000000000000002D);
						            standarddialrange2.setOuterRadius(0.55000000000000004D);
						            standarddialrange2.setPaint(Color.red);
						            dialplot.addLayer(standarddialrange2);
						            dialplot.setBackground(dialbackground);
						            dialplot.removePointer(0);
						            org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
						            pointer.setFillPaint(Color.yellow);
						            dialplot.addPointer(pointer);
						            dialplot.setOutlineVisible(false);
						            dialplot.setOutlinePaint(Color.WHITE);
						            //ChartPanel chartpanel = new ChartPanel(jfreechart);
						           // chartpanel.setPreferredSize(new Dimension(400, 400));
						            slider = new JSlider(0, 100);
						            slider.setMajorTickSpacing(20);
						            slider.setPaintLabels(true);
						          //  slider.addChangeListener(this);
						        //    add(chartpanel);
						        //    add(slider, "South");				    					    					    					  				    
						      /////////////////////////////////////////Old Meter//////////////////////////////////
						      
						      MeterPlot plot = (MeterPlot) ((JFreeChart) chart).getPlot();				   
						      double min = 0;
						      double max = 100;				     				    
						      double maxNorm = 20;
						      double minNorm = min;
						      double minBelow = maxNorm;
						      double maxBelow = 40;
						      double minAvg = maxBelow;
						      double maxAvg = 60;
						      double minGood = maxAvg;
						      double maxGood = 80;
						      double minExc = maxGood;
						      double maxExc = max;
						      plot.setTickLabelsVisible(true);
						      java.awt.Font font =new Font("Arial",1,14);
						    plot.setDialBackgroundPaint(Color.blue);
						      plot.setTickLabelFont(font) ;
						      plot.setTickSize(10);
						    //  plot.setNeedlePaint(Color.GRAY);
						     // plot.setOutlineStroke(new BasicStroke(0.0f));
						      plot.setOutlinePaint(Color.black);
						     plot.setDialShape(DialShape.CHORD);
						     plot.setTickLabelPaint(Color.red);
						     plot.setMeterAngle(180);
						     plot.valueToAngle(90.0);
						    //  plot.setForegroundAlpha(0.6f);
						    //  plot.setBackgroundAlpha(0.0f);
						     plot.setInsets(RectangleInsets.ZERO_INSETS);
						    
						      plot.setRange(new Range(min, max));
						      plot.addInterval(new MeterInterval("", new Range(minNorm, maxNorm),Color.red, new BasicStroke(6.0f), null));
						      plot.addInterval(new MeterInterval("",new Range(minBelow, maxBelow),Color.orange, new BasicStroke(6.0f), null));
						      plot.addInterval(new MeterInterval("",new Range(minAvg, maxAvg),Color.yellow, new BasicStroke(6.0f), null));
						      plot.addInterval(new MeterInterval("",new Range(minGood, maxGood),Color.green, new BasicStroke(6.0f), null));
						      plot.addInterval(new MeterInterval("",new Range(minExc, maxExc),Color.GRAY, new BasicStroke(6.0f), null));				     
						      plot.setUnits("%");				  
						    }
						  };				 				 
						  request.setAttribute("meterRanges", meterPP);
						  request.setAttribute("meterData", valueDatasetProducer);
		 */
		}
		model.terminate();
		return "perform";
	}

	/*public String performance() {
		//LoginModel loginModel = new LoginModel();
		//loginModel.initiate(context,session);
		 PerformanceHomeModel model = new  PerformanceHomeModel();
		model.initiate(context,session);
		String[][] genList = null ;
		String path =context.getRealPath("\\")+"\\WEB-INF\\classes\\org\\paradyne\\dataFiles\\my_Performance\\myPerformance.xml";
		try {
			System.out.println("in tryyyyyyyyyy---------------------------------------->>"+path);
			genList = model.processGenInfo(new XMLReaderWriter().parse(new File(path)),request);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		String empid = (String) request.getAttribute("empId");
		final String value = (String)request.getAttribute("value");
		System.out.println("value of empId------------------------------------[>>"+empid);
		System.out.println("value of value------------------------------------[>>"+value);
		//final String[][] str = loginModel.getCalls();
		//final String[][] strMon=loginModel.getChart();
		if (request.getAttribute("initFlag") == null) {			
			  DatasetProducer valueDatasetProducer = new DatasetProducer() {
				    public Object produceDataset(Map params) throws DatasetProduceException {
				      Integer val = new Integer(10);				      
				      DefaultValueDataset data = new DefaultValueDataset(Double.parseDouble(value));				     
				      return data;
				    }
				    public String getProducerId() {
				      return "meterdata";
				    }
					public boolean hasExpired(Map params, Date since) {						
						return false;
					}
				  };
			  ChartPostProcessor meterPP = new ChartPostProcessor() {
				  //JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "Temperature", dataset, -40D, 60D, 10D, 4);
				    public void processChart(Object chart, Map params) {
				    	 //dataset = new DefaultValueDataset(10D);
				    	 logger.info("_________________________________IN  processChart_____________________________________");
				        				        
				    	 DialPlot dialplot = (DialPlot) ((JFreeChart) chart).getPlot();
				       // DialPlot dialplot = (DialPlot)jfreechart.getPlot();
				    	 GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
				           
				    	 DialBackground dialbackground = new DialBackground(gradientpaint);
				            dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));				           
				    	  ArcDialFrame arcdialframe = new ArcDialFrame(-175D, -190D);
				          arcdialframe.setInnerRadius(0.20D);
				            arcdialframe.setOuterRadius(0.96D);
				            //arcdialframe.setForegroundPaint(Color.darkGray);
				            arcdialframe.setStroke(new BasicStroke(0F));
				            arcdialframe.setBackgroundPaint(new Color(170, 170, 220));
				            arcdialframe.setForegroundPaint(new Color(170, 170, 220));
				            dialplot.setDialFrame(arcdialframe);
				      //  dialplot.setDataset(valuedataset);
				          //dialplot.setDialFrame(new StandardDialFrame());
				          dialplot.setBackground(new DialBackground());
				          DialTextAnnotation dialtextannotation = new DialTextAnnotation("Performance");
				          dialtextannotation.setFont(new Font("Dialog", 1, 12));
				          dialtextannotation.setAngle(90D);
				          dialtextannotation.setRadius(0.39999999999999996D);
				          dialplot.addLayer(dialtextannotation);
				          DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
				          dialvalueindicator.setAngle(15D);
				          dialvalueindicator.setRadius(0.344444444444444D);
				          dialvalueindicator.setBackgroundPaint(new Color(170, 170, 220));
				          dialvalueindicator.setOutlinePaint(new Color(170, 170, 220));
				          dialplot.addLayer(dialvalueindicator);
				          StandardDialScale standarddialscale =
				        	  new StandardDialScale(0D, 100D, -180D, -180D, 20D, 0);				          
				          //StandardDialScale standarddialscale = new StandardDialScale(-40D, 60D, -120D, -300D, 10D, 4);				        	 
				          standarddialscale.setMajorTickIncrement(10D);
				          standarddialscale.setMinorTickCount(4);
				          standarddialscale.setTickRadius(0.88D);
				          standarddialscale.setTickLabelOffset(0.14999999999999999D);
				          standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
				          dialplot.addScale(0, standarddialscale);
				          dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
				          DialCap dialcap = new DialCap();
				          dialcap.setRadius(0.20D);
				          dialcap.setFillPaint(Color.YELLOW);
				          dialcap.setVisible(false);
				          dialplot.setCap(dialcap);
				        logger.info("__________________" +
				        		"_______________IN  processChart_____________________________________");
				    	   StandardDialRange standarddialrange = new StandardDialRange(60D, 100D, Color.red);
				            standarddialrange.setInnerRadius(0.52000000000000002D);
				            standarddialrange.setPaint(Color.red);
				            standarddialrange.setOuterRadius(0.55000000000000004D);
				            dialplot.addLayer(standarddialrange);
				            StandardDialRange standarddialrange1 = new StandardDialRange(40D, 60D, Color.orange);
				            standarddialrange1.setInnerRadius(0.52000000000000002D);
				            standarddialrange1.setOuterRadius(0.55000000000000004D);
				            dialplot.addLayer(standarddialrange1);
				            StandardDialRange standarddialrange2 = new StandardDialRange(0D, 40D, Color.green);
				            standarddialrange2.setInnerRadius(0.52000000000000002D);
				            standarddialrange2.setOuterRadius(0.55000000000000004D);
				            dialplot.addLayer(standarddialrange2);
				            dialplot.setBackground(dialbackground);
				            dialplot.removePointer(0);
				            org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
				            pointer.setFillPaint(Color.yellow);
				            dialplot.addPointer(pointer);
				            dialplot.setOutlineVisible(false);
				            dialplot.setOutlinePaint(Color.WHITE);
				            //ChartPanel chartpanel = new ChartPanel(jfreechart);
				           // chartpanel.setPreferredSize(new Dimension(400, 400));
				            slider = new JSlider(0, 100);
				            slider.setMajorTickSpacing(20);
				            slider.setPaintLabels(true);
				          //  slider.addChangeListener(this);
				        //    add(chartpanel);
				        //    add(slider, "South");
				    					    					    				   				      
				      /////////////////////////////////////////Old Meter//////////////////////////////////				     
				      MeterPlot plot = (MeterPlot) ((JFreeChart) chart).getPlot();				   
				      double min = 0;
				      double max = 100;				     				    
				      double maxNorm = 20;
				      double minNorm = min;
				      double minBelow = maxNorm;
				      double maxBelow = 40;
				      double minAvg = maxBelow;
				      double maxAvg = 60;
				      double minGood = maxAvg;
				      double maxGood = 80;
				      double minExc = maxGood;
				      double maxExc = max;
				      plot.setTickLabelsVisible(true);
				      java.awt.Font font =new Font("Arial",1,14);
				    plot.setDialBackgroundPaint(Color.blue);
				      plot.setTickLabelFont(font) ;
				      plot.setTickSize(10);
				    //  plot.setNeedlePaint(Color.GRAY);
				     // plot.setOutlineStroke(new BasicStroke(0.0f));
				      plot.setOutlinePaint(Color.black);
				     plot.setDialShape(DialShape.CHORD);
				     plot.setTickLabelPaint(Color.red);
				     plot.setMeterAngle(180);
				     plot.valueToAngle(90.0);
				    //  plot.setForegroundAlpha(0.6f);
				    //  plot.setBackgroundAlpha(0.0f);
				     plot.setInsets(RectangleInsets.ZERO_INSETS);
				    
				      plot.setRange(new Range(min, max));
				      plot.addInterval(new MeterInterval("", new Range(minNorm, maxNorm),Color.red, new BasicStroke(6.0f), null));
				      plot.addInterval(new MeterInterval("",new Range(minBelow, maxBelow),Color.orange, new BasicStroke(6.0f), null));
				      plot.addInterval(new MeterInterval("",new Range(minAvg, maxAvg),Color.yellow, new BasicStroke(6.0f), null));
				      plot.addInterval(new MeterInterval("",new Range(minGood, maxGood),Color.green, new BasicStroke(6.0f), null));
				      plot.addInterval(new MeterInterval("",new Range(minExc, maxExc),Color.GRAY, new BasicStroke(6.0f), null));				     
				      plot.setUnits("%");				  
				    }
				  };				 
				  request.setAttribute("meterRanges", meterPP);
				  request.setAttribute("meterData", valueDatasetProducer);		
		}
		model.terminate();
		return "success";
	}*/

	/*public String deptEffGraph(){				
		 DatasetProducer categoryData = new DatasetProducer() {
			    public Object produceDataset(Map params) {
			      final String[] categories = { "Physics", "Chemistry", "Mathemaics", "Zoology"};
			      final String[] seriesNames = { ""};
			      final Integer[][] startValues = new Integer[seriesNames.length][categories.length];
			      final Integer[][] endValues = new Integer[seriesNames.length][categories.length];
			      for (int series = 0; series < seriesNames.length; series++) {
			        for (int i = 0; i < categories.length; i++) {
			          int y = (int) (Math.random() * 10 + 1);
			          startValues[series][i] = new Integer(y);
			          endValues[series][i] = new Integer(y + (int) (Math.random() * 10));
			        }
			      }
			      DefaultIntervalCategoryDataset ds =
			        new DefaultIntervalCategoryDataset(seriesNames, categories, startValues, endValues);
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
			  return "department";
	}*/

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

	public String deptEffGraph() throws Exception {
		PerformanceHomeModel model = new PerformanceHomeModel();
		model.initiate(context, session);
		final Object[][] cent = model.getCenter(pa);
		final Object[][] payBill = model.getPayBill(pa);
		//Centerwise 
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
		request.setAttribute("meterRanges", meterPP);

		//Paybill wise 
		DatasetProducer categoryData1 = new DatasetProducer() {
			public Object produceDataset(Map params) {

				final String[] categorieNames = new String[payBill.length];
				final String[] seriesNames = { String.valueOf(payBill[0][0]) };
				//{String.valueOf(cent[0][0])};
				for (int j = 0; j < categorieNames.length; j++) {
					categorieNames[j] = String.valueOf(payBill[j][0]);
				}
				final Integer[][] startValues = new Integer[seriesNames.length][categorieNames.length];
				final Integer[][] endValues = new Integer[seriesNames.length][categorieNames.length];
				for (int series = 0; series < seriesNames.length; series++) {
					try {
						for (int i = 0; i < categorieNames.length; i++) {

							int y = Integer.parseInt(String
									.valueOf(payBill[i][1]));
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
		model.terminate();
		return "department";
	}

	public String appraisalAlert() {
		return "apprAlrt";
	}

	public String appraisalStatcs() {
		PerformanceHomeModel model = new PerformanceHomeModel();
		model.initiate(context, session);
		Object phaseCode[][] = model.getPhases();
		String deptApprisalData[][] = model
				.performancePageApprisalInfo(phaseCode);
		model.terminate();
		logger.info("deptApprisalData------- " + deptApprisalData.length);
		request.setAttribute("phaseData", phaseCode);
		request.setAttribute("deptApprisalData", deptApprisalData);
		if (deptApprisalData != null)
			logger.info("Inside printing Length  ------------------------- "
					+ deptApprisalData.length);
		return "statistics";
	}

	public String pendingAppraisal() {
		PerformanceHomeModel model = new PerformanceHomeModel();
		model.initiate(context, session);

		Object phaseCode[][] = model.getPhases();
		String deptApprisalXML = model.getPendingAppraisalXML(phaseCode);
		model.terminate();
		logger.info("dashletId===" + request.getParameter("dashId"));
		request.setAttribute("deptApprisalXML", deptApprisalXML);
		request.setAttribute("dashletId", request.getParameter("dashId"));
		request.setAttribute("dashletWidth", request.getParameter("dashWidth"));
		return "pendingAppr";
	}

	public String deptEffData() {
		try {
			PerformanceHomeModel model = new PerformanceHomeModel();
			model.initiate(context, session);
			Vector v = model.showDeptEffData();
			Object[][] rating = (Object[][]) v.elementAt(0);
			Object[][] deptEffCount = (Object[][]) v.elementAt(1);
			request.setAttribute("rating", rating);
			request.setAttribute("deptEffCount", deptEffCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "department";
	}

	public String deptAvgScoreData() {
		try {
			PerformanceHomeModel model = new PerformanceHomeModel();
			model.initiate(context, session);

			String deptPerformanceXML = model.getDeptPerformanceXML();
			model.terminate();
			request.setAttribute("deptPerformanceXML", deptPerformanceXML);
			request.setAttribute("dashletId", request.getParameter("dashId"));
			request.setAttribute("dashletWidth", request
					.getParameter("dashWidth"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "deptAvgScore";
	}

	/*public String empPie()throws Exception{	
	 PerformanceHomeModel model = new PerformanceHomeModel();
	 model.initiate(context,session);	
	 String apprId = (String)request.getParameter("apprId");
	 String apprCode = (String)request.getParameter("apprCode");
	 String empId = (String)request.getParameter("empId");
	 String empName = (String)request.getParameter("empName");	
	 pa.setApprId(apprId);
	 pa.setApprCode(apprCode);
	 pa.setEmpId(empId);
	 pa.setEmpName(empName);	
	 final String performance = model.getEmpPerf(pa,apprId,empId);
	 String empid = (String) request.getAttribute("empId");
	 final String value = (String)request.getAttribute("value");
	 System.out.println("empPie------value of empId------------------------------------[>>"+empid);
	 System.out.println("empPie------value of value------------------------------------[>>"+value);
	 //final String[][] str = loginModel.getCalls();
	 //final String[][] strMon=loginModel.getChart();	
	 if (request.getAttribute("initFlag") == null) {	
	 /////DatasetProducer
	 DatasetProducer valueDatasetProducer = new DatasetProducer() {	
	 public Object produceDataset(Map params) throws DatasetProduceException {
	 Integer val = new Integer(10);							      
	 DefaultValueDataset data = new DefaultValueDataset(Double.parseDouble(performance));							      
	 return data;	
	 }
	
	 public String getProducerId() {
	 return "meterdata";
	 }
	
	 public boolean hasExpired(Map params, Date since) {
	 return true;
	 }
	 };

	
	
	 //////ChartPostProcessor
	 ChartPostProcessor meterPP = new ChartPostProcessor() {
	 //JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "Temperature", dataset, -40D, 60D, 10D, 4);
	 public void processChart(Object chart, Map params) {
	 //dataset = new DefaultValueDataset(10D);
	 logger.info("_________________________________IN  processChart_____________________________________");
	 DialPlot dialplot = (DialPlot) ((JFreeChart) chart).getPlot();
	
	 String img =getServletContext().getRealPath("//")+"/pages/images/family/";
	 com.lowagie.text.Image image=null;
	 try {
	 image = com.lowagie.text.Image.getInstance(img);
	 } catch (Exception e) {
	 logger.info("image not available in server------------------"+e);	
	 }
	 //dialplot.setBackgroundImage(image);
	 // DialPlot dialplot = (DialPlot)jfreechart.getPlot();
	 GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));	
	 DialBackground dialbackground = new DialBackground(gradientpaint);
	 dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));	
	 ArcDialFrame arcdialframe = new ArcDialFrame(-175D, -190D);
	 arcdialframe.setInnerRadius(0.20D);
	 arcdialframe.setOuterRadius(0.96D);
	 //arcdialframe.setForegroundPaint(Color.darkGray);
	 arcdialframe.setStroke(new BasicStroke(0F));
	 arcdialframe.setBackgroundPaint(new Color(170, 170, 220));
	 arcdialframe.setForegroundPaint(new Color(170, 170, 220));
	 dialplot.setDialFrame(arcdialframe);
	 //  dialplot.setDataset(valuedataset);
	 //dialplot.setDialFrame(new StandardDialFrame());
	 dialplot.setBackground(new DialBackground());
	 DialTextAnnotation dialtextannotation = new DialTextAnnotation("Performance");
	 dialtextannotation.setFont(new Font("Dialog", 1, 12));
	 dialtextannotation.setAngle(90D);
	 dialtextannotation.setRadius(0.39999999999999996D);
	 dialplot.addLayer(dialtextannotation);
	 DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
	 dialvalueindicator.setAngle(15D);
	 dialvalueindicator.setRadius(0.344444444444444D);
	 dialvalueindicator.setBackgroundPaint(new Color(170, 170, 220));
	 dialvalueindicator.setOutlinePaint(new Color(170, 170, 220));
	 dialplot.addLayer(dialvalueindicator);
	 StandardDialScale standarddialscale =
	 new StandardDialScale(0D, 100D, -180D, -180D, 20D, 0);	
	 //StandardDialScale standarddialscale = new StandardDialScale(-40D, 60D, -120D, -300D, 10D, 4);	
	 standarddialscale.setMajorTickIncrement(10D);
	 standarddialscale.setMinorTickCount(4);
	 standarddialscale.setTickRadius(0.88D);
	 standarddialscale.setTickLabelOffset(0.14999999999999999D);
	 standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
	 dialplot.addScale(0, standarddialscale);
	 dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
	 DialCap dialcap = new DialCap();
	 dialcap.setRadius(0.20D);
	 dialcap.setFillPaint(Color.YELLOW);
	 dialcap.setVisible(false);
	 dialplot.setCap(dialcap);
	 logger.info("__________________" +
	 "_______________IN  processChart_____________________________________");
	 StandardDialRange standarddialrange = new StandardDialRange(60D, 100D, Color.green);
	 standarddialrange.setInnerRadius(0.52000000000000002D);
	 standarddialrange.setPaint(Color.green);
	 standarddialrange.setOuterRadius(0.55000000000000004D);
	 dialplot.addLayer(standarddialrange);
	 StandardDialRange standarddialrange1 = new StandardDialRange(40D, 60D, Color.orange);
	 standarddialrange1.setInnerRadius(0.52000000000000002D);
	 standarddialrange1.setOuterRadius(0.55000000000000004D);
	 standarddialrange1.setPaint(Color.orange);
	 dialplot.addLayer(standarddialrange1);
	 StandardDialRange standarddialrange2 = new StandardDialRange(0D, 40D, Color.red);
	 standarddialrange2.setInnerRadius(0.52000000000000002D);
	 standarddialrange2.setOuterRadius(0.55000000000000004D);
	 standarddialrange2.setPaint(Color.red);
	 dialplot.addLayer(standarddialrange2);
	 dialplot.setBackground(dialbackground);
	 dialplot.removePointer(0);
	 org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
	 pointer.setFillPaint(Color.yellow);
	 dialplot.addPointer(pointer);
	 dialplot.setOutlineVisible(false);
	 dialplot.setOutlinePaint(Color.WHITE);
	 //ChartPanel chartpanel = new ChartPanel(jfreechart);
	 // chartpanel.setPreferredSize(new Dimension(400, 400));
	 slider = new JSlider(0, 100);
	 slider.setMajorTickSpacing(20);
	 slider.setPaintLabels(true);
	 //  slider.addChangeListener(this);
	 //    add(chartpanel);
	 //    add(slider, "South");
	 }
	 };		
	 request.setAttribute("meterRanges", meterPP);
	 request.setAttribute("meterData", valueDatasetProducer);
	 }
	 model.terminate();
	 return "empSuccess";
	 }*/

	public String empPie(boolean onload) throws Exception {

		PerformanceHomeModel model = new PerformanceHomeModel();
		model.initiate(context, session);
		logger.info("getApprId===" + pa.getApprId());
		logger.info("getEmpId===" + pa.getEmpId());
		logger.info("empId in empPie===" + request.getParameter("empId"));
		logger.info("empId onload flag====" + onload);
		logger.info("generalflag====" + pa.isGeneralFlag());
		model.getEmpPerf(pa, request, onload);
		/*String empid = (String) request.getAttribute("empId");
		final String value = (String)request.getAttribute("value");
		System.out.println("empPie------value of empId------------------------------------[>>"+empid);
		System.out.println("empPie------value of value------------------------------------[>>"+value);
		//final String[][] str = loginModel.getCalls();
		//final String[][] strMon=loginModel.getChart();		
		if (request.getAttribute("initFlag") == null) {			
				 /////DatasetProducer
				DatasetProducer valueDatasetProducer = new DatasetProducer() {
		
								    public Object produceDataset(Map params) throws DatasetProduceException {
								      Integer val = new Integer(10);							      
								      DefaultValueDataset data = new DefaultValueDataset(Double.parseDouble(performance));							      
								      return data;								      
								    }								    
								    public String getProducerId() {
								      return "meterdata";
								    }
				
									public boolean hasExpired(Map params, Date since) {
										return true;
									}
					  };				 				  
				  //////ChartPostProcessor
				  ChartPostProcessor meterPP = new ChartPostProcessor() {					  
									  //JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "Temperature", dataset, -40D, 60D, 10D, 4);
									    public void processChart(Object chart, Map params) {
									    	 //dataset = new DefaultValueDataset(10D);
									    	 logger.info("_________________________________IN  processChart_____________________________________");
									        
									    	 
									    	 DialPlot dialplot = (DialPlot) ((JFreeChart) chart).getPlot();
									    	 
									    	 String img =getServletContext().getRealPath("//")+"/pages/images/family/";
									    	 com.lowagie.text.Image image=null;
												try {
													image = com.lowagie.text.Image.getInstance(img);
												} catch (Exception e) {										
													logger.info("image not available in server------------------"+e);
												
												}
									    	 //dialplot.setBackgroundImage(image);
									       // DialPlot dialplot = (DialPlot)jfreechart.getPlot();
									    	 GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(), new Color(170, 170, 220));
									           
									    	 DialBackground dialbackground = new DialBackground(gradientpaint);
									            dialbackground.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
									           
									    	  ArcDialFrame arcdialframe = new ArcDialFrame(-175D, -190D);
									          arcdialframe.setInnerRadius(0.20D);
									            arcdialframe.setOuterRadius(0.96D);
									            //arcdialframe.setForegroundPaint(Color.darkGray);
									            arcdialframe.setStroke(new BasicStroke(0F));
									            arcdialframe.setBackgroundPaint(new Color(170, 170, 220));
									            arcdialframe.setForegroundPaint(new Color(170, 170, 220));
									            dialplot.setDialFrame(arcdialframe);
									      //  dialplot.setDataset(valuedataset);
									          //dialplot.setDialFrame(new StandardDialFrame());
									          dialplot.setBackground(new DialBackground());
									          DialTextAnnotation dialtextannotation = new DialTextAnnotation("Performance");
									          dialtextannotation.setFont(new Font("Dialog", 1, 12));
									          dialtextannotation.setAngle(90D);
									          dialtextannotation.setRadius(0.39999999999999996D);
									          dialplot.addLayer(dialtextannotation);
									          DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
									          dialvalueindicator.setAngle(15D);
									          dialvalueindicator.setRadius(0.344444444444444D);
									          dialvalueindicator.setBackgroundPaint(new Color(170, 170, 220));
									          dialvalueindicator.setOutlinePaint(new Color(170, 170, 220));
									          dialplot.addLayer(dialvalueindicator);
									          StandardDialScale standarddialscale =
									        	  new StandardDialScale(0D, 100D, -180D, -180D, 20D, 0);
									          
									          //StandardDialScale standarddialscale = new StandardDialScale(-40D, 60D, -120D, -300D, 10D, 4);
									        	 
									          standarddialscale.setMajorTickIncrement(10D);
									          standarddialscale.setMinorTickCount(4);
									          standarddialscale.setTickRadius(0.88D);
									          standarddialscale.setTickLabelOffset(0.14999999999999999D);
									          standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
									          dialplot.addScale(0, standarddialscale);
									          dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
									          DialCap dialcap = new DialCap();
									          dialcap.setRadius(0.20D);
									          dialcap.setFillPaint(Color.YELLOW);
									          dialcap.setVisible(false);
									          dialplot.setCap(dialcap);
									        logger.info("__________________" +
									        		"_______________IN  processChart_____________________________________");
									    	   StandardDialRange standarddialrange = new StandardDialRange(60D, 100D, Color.green);
									            standarddialrange.setInnerRadius(0.52000000000000002D);
									            standarddialrange.setPaint(Color.green);
									            standarddialrange.setOuterRadius(0.55000000000000004D);
									            dialplot.addLayer(standarddialrange);
									            StandardDialRange standarddialrange1 = new StandardDialRange(40D, 60D, Color.orange);
									            standarddialrange1.setInnerRadius(0.52000000000000002D);
									            standarddialrange1.setOuterRadius(0.55000000000000004D);
									            standarddialrange1.setPaint(Color.orange);
									            dialplot.addLayer(standarddialrange1);
									            StandardDialRange standarddialrange2 = new StandardDialRange(0D, 40D, Color.red);
									            standarddialrange2.setInnerRadius(0.52000000000000002D);
									            standarddialrange2.setOuterRadius(0.55000000000000004D);
									            standarddialrange2.setPaint(Color.red);
									            dialplot.addLayer(standarddialrange2);
									            dialplot.setBackground(dialbackground);
									            dialplot.removePointer(0);
									            org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
									            pointer.setFillPaint(Color.yellow);
									            dialplot.addPointer(pointer);
									            dialplot.setOutlineVisible(false);
									            dialplot.setOutlinePaint(Color.WHITE);
									            //ChartPanel chartpanel = new ChartPanel(jfreechart);
									           // chartpanel.setPreferredSize(new Dimension(400, 400));
									            slider = new JSlider(0, 100);
									            slider.setMajorTickSpacing(20);
									            slider.setPaintLabels(true);
									          //  slider.addChangeListener(this);
									        //    add(chartpanel);
									        //    add(slider, "South");
					
									    	
									    	
									    	}
					  };
  				  request.setAttribute("meterRanges", meterPP);
				  request.setAttribute("meterData", valueDatasetProducer);
		}*/
		model.terminate();
		return "empSuccess";
	}

	public String f9Employee() throws Exception {

		String apprId = (String) request.getParameter("apprId");
		String query = "SELECT EMP_TOKEN ,(HRMS_EMP_OFFC.EMP_FNAME||' '||HRMS_EMP_OFFC.EMP_MNAME|| ' ' || HRMS_EMP_OFFC.EMP_LNAME),RANK_NAME,"
				+ " CENTER_NAME,HRMS_EMP_OFFC.EMP_ID FROM PAS_APPR_SCORE "
				+ " LEFT JOIN HRMS_EMP_OFFC ON(HRMS_EMP_OFFC.EMP_ID=PAS_APPR_SCORE.EMP_ID)"
				+ " LEFT JOIN HRMS_CENTER ON(HRMS_CENTER.CENTER_ID=HRMS_EMP_OFFC.EMP_CENTER)"
				+ " LEFT JOIN HRMS_RANK ON (HRMS_RANK.RANK_ID =HRMS_EMP_OFFC.EMP_CADRE)"
				+ " WHERE APPR_ID=" + apprId;

		//String[] headers = { getMessage("appraisal.emp")+" Id",getMessage("appraisal.emp"),getMessage("appraisal.desg"),getMessage("appraisal.branch")};
		String[] headers = { "Employee Id", "Employee", "Designation", "Branch" };
		String[] headerWidth = { "15", "45", "20", "20" };
		String[] fieldNames = { "token", "empName", "desg", "branch", "empId" };
		int[] columnIndex = { 0, 1, 2, 3, 4 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String f9AppCal() throws Exception {

		String query = " SELECT  APPR_CAL_CODE,TO_CHAR(APPR_CAL_START_DATE,'DD-MM-YYYY'),TO_CHAR(APPR_CAL_END_DATE,'DD-MM-YYYY'),APPR_ID FROM PAS_APPR_CALENDAR "
				+ " ORDER BY APPR_ID";
		String[] headers = { "Appraisal Code", "Start Date", "End Date" };
		String[] headerWidth = { "50", "25", "25" };
		String[] fieldNames = { "apprCode", "frmDate", "toDate", "apprId" };
		int[] columnIndex = { 0, 1, 2, 3 };
		String submitFlag = "false";
		String submitToMethod = "";
		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);
		return "f9page";
	}

	public String getPasMenu() throws Exception {
		HomePageModel performanceHomeModel= new HomePageModel();
		performanceHomeModel.initiate(context, session);
		String menuType=request.getParameter("menuType");
		performanceHomeModel.getMenuList(request, pa, menuType);
		return "pasHomePage";
	}
}
