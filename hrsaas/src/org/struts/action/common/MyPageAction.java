/**
 * 
 */
package org.struts.action.common;

/*import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.text.NumberFormat;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.paradyne.model.common.LoginModel;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleInsets;


import com.lowagie.text.Image;

import  de.laures.cewolf.taglib.ChartImageDefinition;
import java.util.Date;
import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

import de.laures.cewolf.links.LinkGenerator;
import de.laures.cewolf.links.XYItemLinkGenerator;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Map;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.ArcDialFrame;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialTextAnnotation;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;
import org.paradyne.bean.common.MyPage;
import org.paradyne.lib.ModelBase;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.common.LoginModel;
import org.paradyne.model.common.MyPageModel;
import org.struts.lib.ParaActionSupport;

import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;
import de.laures.cewolf.links.LinkGenerator;
import de.laures.cewolf.links.XYItemLinkGenerator;
import org.jfree.data.Range;*/

import org.paradyne.bean.common.MyPage;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;

import org.paradyne.model.common.MyPageModel;
import org.struts.lib.ParaActionSupport;


import javax.swing.JSlider;

/**
 * @author shashikant
 *
 */
public class MyPageAction extends ParaActionSupport {

	/* (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */
	JSlider slider;
	MyPage myPage;
	public MyPage getMyPage() {
		return myPage;
	}

	public void setMyPage(MyPage myPage) {
		this.myPage = myPage;
	}

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
	.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		
		// TODO Auto-generated method stub
		myPage =new MyPage();
		
		//setmyPerformance();
		
	}
	public void prepare_withLoginProfileDetails() throws Exception {
System.out.println("-------------============-------");
setMyLeave();
setMyTrainingProgram();
setmyPerformance();
	}
	
	public String input() throws Exception {
		DefaultDashletConfigModel model  = new DefaultDashletConfigModel();
		model.initiate(context, session);
		model.getDashletConfig("408", myPage.getUserProfileId(),myPage.getUserLoginCode(), request);
		model.terminate();
		return "dashboard";
	}
	
	public String setMyLeave()throws Exception {
		MyPageModel model=new MyPageModel();
		model.initiate(context,session);
		model.setMyLeaveStatus(myPage);
		model.terminate();
		
		return "myLeaveStatus";
	}
	public String setutvCommunication()throws Exception {
				
		return "utvCommunication";
	}
	public String setmyPerformance()throws Exception {
		Pie();
		request.setAttribute("dashletId", request.getParameter("dashId"));
		request.setAttribute("dashletWidth", request.getParameter("dashWidth"));
		return "myPerformance";
	}
	
	/*public String setmyLeaveStatus()throws Exception {
		
		return "myLeaveStatus";
	}*/
	
	public String setmyTrainingProgram()throws Exception {
		
		return "myTrainingProgram";
	}
	
	public String setMyTrainingProgram()throws Exception {
		MyPageModel model=new MyPageModel();
		model.initiate(context,session);
		model.setMyTrainingProgram(myPage);
		model.terminate();
		
		return SUCCESS;
	}
	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		// TODO Auto-generated method stub
		return myPage;
	}
	
	
	
	/*public String Pie() {
		//LoginModel loginModel = new LoginModel();
		//loginModel.initiate(context,session);
		//final String performance="0.0";
		MyPageModel model = new MyPageModel();
		model.initiate(context,session);
		
			
		final String performance = model.getMyPerf(myPage);
			
		
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
				      
				      DefaultValueDataset data = new DefaultValueDataset(Double.parseDouble(performance));
				      
				      return data;
				    }

				

				    public String getProducerId() {
				      return "meterdata";
				    }

					public boolean hasExpired(Map params, Date since) {
						// TODO Auto-generated method stub
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
								// TODO: handle exception
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

		}
		model.terminate();
		return "success";
	}*/
	
	/*
	 * function for fusion chart
	 */
	public String Pie() {
		//LoginModel loginModel = new LoginModel();
		//loginModel.initiate(context,session);
		//final String performance="0.0";
		MyPageModel model = new MyPageModel();
		model.initiate(context,session);
		
		final String performance = model.getMyPerf(myPage,request);
		model.terminate();
		return "success";
	}
	
/*	public String Pie() {
		//LoginModel loginModel = new LoginModel();
		//loginModel.initiate(context,session);
		MyPageModel model = new MyPageModel();
		model.initiate(context,session);
		//final String[][] str = loginModel.getCalls();
		//final String[][] strMon=loginModel.getChart();
		if (request.getAttribute("initFlag") == null) {
			
			  DatasetProducer valueDatasetProducer = new DatasetProducer() {

				    public Object produceDataset(Map params) throws DatasetProduceException {
				      Integer val = new Integer(10);
				      
				      DefaultValueDataset data = new DefaultValueDataset(val);
				      
				      return data;
				    }

				    public boolean hasExpired(Map params, Date since) {
				      return false;
				    }

				    public String getProducerId() {
				      return "meterdata";
				    }

				  };

			  ChartPostProcessor meterPP = new ChartPostProcessor() {

				    public void processChart(Object chart, Map params) {
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
				 
				  LinkGenerator xyLG = new XYItemLinkGenerator() {
				    public String generateLink(Object data, int series, int item) {
				      System.out.println("3333333333333333333333333");
				    	return "#Series " + series;
				    }
				  };
				  request.setAttribute("meterRanges", meterPP);
				  request.setAttribute("meterData", valueDatasetProducer);

		}
		model.terminate();
		return "success";
	}*/
}
