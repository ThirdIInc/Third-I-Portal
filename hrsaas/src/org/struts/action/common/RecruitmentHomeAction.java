package org.struts.action.common;
import java.util.Date;
import java.util.Map;


import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.HomePageModel;
import org.paradyne.model.common.HrmHomeModel;
import org.paradyne.model.common.RecruitmentHomeModel;

import org.struts.lib.ParaActionSupport;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.paradyne.bean.common.AdminHome;
import org.paradyne.bean.common.RecruitmentHome;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Paint;
import java.awt.Stroke;
import java.awt.geom.Rectangle2D;
import java.text.NumberFormat;
import java.util.Map;

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
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.Range;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;


import com.lowagie.text.Image;

import  de.laures.cewolf.taglib.ChartImageDefinition;
import java.util.Date;
import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

import de.laures.cewolf.links.LinkGenerator;
import de.laures.cewolf.links.XYItemLinkGenerator;
/*
 * author:Pradeep Kumar Sahoo
 * Date:31-01-2008
 */

import de.laures.cewolf.DatasetProducer;

public class RecruitmentHomeAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger=org.apache.log4j.Logger.getLogger(RecruitmentHomeAction.class);
	RecruitmentHome recHome;

	public RecruitmentHome getRh() {
		return recHome;
	}

	public void setRh(RecruitmentHome rh) {
		this.recHome = rh;
	} 
	
	
	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		recHome=new RecruitmentHome();
	}
	
	public Object getModel() {
		// TODO Auto-generated method stub
		return recHome;
	}
	
	
	public String openReq() {
		try {
			RecruitmentHomeModel model = new RecruitmentHomeModel();
			model.initiate(context, session);
			model.getRecord(recHome, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "requisition";
	}
	
	public String interviewSch() {
		
		return "schedule";
		
	}
	public String input() throws Exception {
		DefaultDashletConfigModel model  = new DefaultDashletConfigModel();
		model.initiate(context, session);
		//model.getDashletConfig("409", recHome.getUserProfileId(),recHome.getUserLoginCode(), request);
		model.terminate();
		return "recruitmentHomePage";
	}
	
	/*public String attritionRate(){
		
		
		 DatasetProducer categoryData = new DatasetProducer() {
			    public Object produceDataset(Map params) {
			      final String[] categories = { "2005-06", "2006-07", "2008-09"};
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
			  
			  return "attrition";
	}*/
	
	public String qualificationAnalysis(){
		RecruitmentHomeModel model = new RecruitmentHomeModel();
		model.initiate(context, session);
		model.getquaAnalysisRecord(recHome,request);
		request.setAttribute("dashletId", request.getParameter("dashId"));
		request.setAttribute("dashletWidth", request.getParameter("dashWidth"));
		model.terminate();
		return "quaAnalysisGraph";
	} //end of qualificationAnalysis method
	
	 private static CategoryDataset createDataset2()
	    {
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
	
	public String attritionRate()throws Exception {
		RecruitmentHomeModel hhm=new RecruitmentHomeModel();
		hhm.initiate(context,session);
		final Object[][] cent = hhm.getCenter(recHome);
		final Object[][] payBill=hhm.getPayBill(recHome);
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
				   
				        CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart).getPlot();
				        
				        categoryplot.setBackgroundPaint(new Color(238, 238, 255));
				        CategoryDataset categorydataset = createDataset2();
				        categoryplot.setDataset(1, categorydataset);
				        categoryplot.mapDatasetToRangeAxis(1, 1);
				        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
				        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
				        NumberAxis numberaxis = new NumberAxis("Secondary");
				        categoryplot.setRangeAxis(1, numberaxis);
				        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
				        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
				        categoryplot.setRenderer(1, lineandshaperenderer);
				        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				        LegendTitle legendtitle = new LegendTitle(categoryplot.getRenderer(0));
				        legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
				        legendtitle.setFrame(new BlockBorder());
				        LegendTitle legendtitle1 = new LegendTitle(categoryplot.getRenderer(1));
				        legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
				        legendtitle1.setFrame(new BlockBorder());
				        BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
				        blockcontainer.add(legendtitle, RectangleEdge.LEFT);
				        blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
				        blockcontainer.add(new EmptyBlock(2000D, 0.0D));
				        CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
				        compositetitle.setPosition(RectangleEdge.BOTTOM);
				       categoryplot.setForegroundAlpha(0.6F);
				       
				   }
				  };
				 
				 
				  request.setAttribute("meterRanges", meterPP);
			  
			 //Paybill wise 
				  DatasetProducer categoryData1 = new DatasetProducer() {
					    public Object produceDataset(Map params) {
					    	
					      final String[] categorieNames = new String[payBill.length];
					      final String[] seriesNames = {String.valueOf(payBill[0][0])};
					      //{String.valueOf(cent[0][0])};
					      for (int j = 0; j < categorieNames.length; j++) {
					    	  categorieNames[j]=String.valueOf(payBill[j][0]);
					    	  
						}
					      final Integer[][] startValues = new Integer[seriesNames.length][categorieNames.length];
					      final Integer[][] endValues = new Integer[seriesNames.length][categorieNames.length];
					      for (int series = 0; series < seriesNames.length; series++) {
					    	  try{
					    	  for (int i = 0; i <categorieNames.length; i++) {
					        	
					    		  int y = Integer.parseInt(String.valueOf(payBill[i][1])) ;
						          startValues[series][i] = new Integer(y);
						          endValues[series][i] = new Integer(y + (int) (Math.random() * 10));
					        }
					    	  }catch(Exception e){
					    		  e.printStackTrace();
					    	  }
					    	  
					      }
					      DefaultIntervalCategoryDataset ds =
					        new DefaultIntervalCategoryDataset(seriesNames, categorieNames, startValues, endValues);
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
			hhm.terminate();
		hhm.terminate();
		return"attrition";
		
	}
	
	public String workFlow(){
		RecruitmentHomeModel model=new RecruitmentHomeModel();
		model.initiate(context, session);
		model.getInterviewSchedule(recHome);
		model.terminate();
		
		return "workflow";
	}
	
	public String getInterviewerSchDet(){
		RecruitmentHomeModel model=new RecruitmentHomeModel();
		model.initiate(context, session);
		model.getIntrvwrSchedule(recHome);
		model.terminate();
		
		return "intervwrSch";
	}
	
	/*public String manpowerSt(){
		RecruitmentHomeModel model=new RecruitmentHomeModel();

		 model.initiate(context,session);
		 final Object[][] result1=model.getDepartment(rh);
		 
	
	if (request.getAttribute("initFlag") == null) {
		 DatasetProducer pieData = new DatasetProducer() {
		    public Object produceDataset(Map params) {
		    	
		    	
		    	final String[] categories = new String[result1.length];
		      DefaultPieDataset ds = new DefaultPieDataset();
		      for (int i = 0; i < result1.length; i++) {
		    		categories[i] = String.valueOf(result1[i][1]);
		       
		      
		      }
		  	for (int i = 0; i < categories.length; i++) {
				int y = Integer.parseInt(String
						.valueOf(result1[i][0]));
				ds.setValue(categories[i], new Integer(y));

			}
			return ds;
	
		    }
		    public String getProducerId() {
		      return "PieDataProducer";
		    }
		    public boolean hasExpired(Map params, Date since) {
		      return false;
		    }
		  };
		  
		  ChartPostProcessor meterPP = new ChartPostProcessor() {
			  //JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "Temperature", dataset, -40D, 60D, 10D, 4);
			    public void processChart(Object chart, Map params) {
			    	 //dataset = new DefaultValueDataset(10D);
			    	// logger.info("_________________________________IN  processChart_____________________________________");
			        
			        
			    	 //DialPlot dialplot = (DialPlot) ((JFreeChart) chart).getPlot();
			    	 
			    	 PiePlot3D pieplot3d = (PiePlot3D)((JFreeChart) chart).getPlot();
			        
			    	  pieplot.setSectionPaint( 1,new Color(128, 128, 223));
				         pieplot.setSectionPaint( 2,new Color(96, 96, 191));
				         pieplot.setSectionPaint( 3,new Color(64, 64, 159));
				         pieplot.setSectionPaint(4, new Color(32, 32, 127));
				     pieplot3d.setSectionPaint("Six", new Color(0, 0, 111));
			         pieplot3d.setNoDataMessage("No data available");
			         pieplot3d.setExplodePercent("Two", 0.20000000000000001D);
			         pieplot3d.setLabelGenerator(new StandardPieSectionLabelGenerator("{2}"));
			         //pieplot3d.setLabelBackgroundPaint(Color.white);
			         pieplot3d.setLabelLinksVisible(false);
			        
			         pieplot3d.setLabelLinkPaint(Color.white);	
			         pieplot3d.setOutlineStroke(null);
			         pieplot3d.setLabelOutlineStroke(null);
			         pieplot3d.setLabelOutlinePaint(null);
			         pieplot3d.setLabelPaint(Color.LIGHT_GRAY);
			        // pieplot3d.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("Tooltip for legend item {0}"));
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
		 
		  request.setAttribute("pieData1", pieData);
		 
		 
		}
	 model.terminate();
	 return "department";  
	}
	*/
	
	 private  CategoryDataset createDataset3()
	    {
		 RecruitmentHomeModel hhm=new RecruitmentHomeModel();
		 hhm.initiate(context,session);
			
		 final Object[][] result1=hhm.getDepartment(recHome);
			
		 
			
				    	 
				    	String s = "Departments";
				        
				      
				        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
				        for(int i=0;i<result1.length;i++){
							 System.out.println("--------------result----------------->>"+result1[i][0]);
				    	 
				        defaultcategorydataset.addValue(Integer.parseInt(String.valueOf(result1[i][0])), s, String.valueOf(result1[i][1]));
				       
				        }
	        return defaultcategorydataset;
	    }
	 
	public String getManPowerAnalysis()throws Exception{
		RecruitmentHomeModel model=new RecruitmentHomeModel();
		model.initiate(context, session);
		model.getManpowerDistributionAnalysis(recHome,request);
		request.setAttribute("dashletId", request.getParameter("dashId"));
		request.setAttribute("dashletWidth", request.getParameter("dashWidth"));
		model.terminate();
		return "manPower";
	} //end of getManPowerAnalysis method
	 
	public String manpowerSt()throws Exception {
		RecruitmentHomeModel hhm=new RecruitmentHomeModel();
		hhm.initiate(context,session);
		final Object[][] cent = hhm.getCenter(recHome);
		final Object[][] payBill=hhm.getPayBill(recHome);
		 final Object[][] result1=hhm.getDepartment(recHome);
		//Centerwise 
		 DatasetProducer categoryData = new DatasetProducer() {
			    public Object produceDataset(Map params) {
			    	 
			    	String s = "Departments";
			        
			      
			        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
			        for(int i=0;i<result1.length;i++){
						 System.out.println("--------------result----------------->>"+result1[i][0]);
			    	 
			        defaultcategorydataset.addValue(Integer.parseInt(String.valueOf(result1[i][0])), s, String.valueOf(result1[i][1]));
			       
			        }
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
				   
				        CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart).getPlot();
				        
				        categoryplot.setBackgroundPaint(new Color(238, 238, 255));
				        CategoryDataset categorydataset = createDataset3();
				        categoryplot.setDataset(1, categorydataset);
				        categoryplot.mapDatasetToRangeAxis(1, 1);
				        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
				        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_90);
				        NumberAxis numberaxis = new NumberAxis("Secondary");
				        categoryplot.setRangeAxis(1, numberaxis);
				        LineAndShapeRenderer lineandshaperenderer = new LineAndShapeRenderer();
				        lineandshaperenderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
				        categoryplot.setRenderer(1, lineandshaperenderer);
				        categoryplot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
				        LegendTitle legendtitle = new LegendTitle(categoryplot.getRenderer(0));
				        legendtitle.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
				        legendtitle.setFrame(new BlockBorder());
				        LegendTitle legendtitle1 = new LegendTitle(categoryplot.getRenderer(1));
				        legendtitle1.setMargin(new RectangleInsets(2D, 2D, 2D, 2D));
				        legendtitle1.setFrame(new BlockBorder());
				        BlockContainer blockcontainer = new BlockContainer(new BorderArrangement());
				        blockcontainer.add(legendtitle, RectangleEdge.LEFT);
				        blockcontainer.add(legendtitle1, RectangleEdge.RIGHT);
				        blockcontainer.add(new EmptyBlock(2000D, 0.0D));
				        CompositeTitle compositetitle = new CompositeTitle(blockcontainer);
				        compositetitle.setPosition(RectangleEdge.BOTTOM);
				       categoryplot.setForegroundAlpha(0.6F);
				       
				   }
				  };
				 
				 
				  request.setAttribute("meterRanges", meterPP);
			  
		
			hhm.terminate();
		
		return"department";
		
	}
	
public String empReferalprogram() {
		
		try {
			RecruitmentHomeModel model = new RecruitmentHomeModel();
			model.initiate(context, session);
			logger.info("u r at empReferalprogram.....!!!");
			model.getReferalprogramRecord(recHome, request);
			model.terminate();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "empReferalprogram";
		
	}

//Added by Priyanka
public String getRecruitmentMenu() throws Exception {
	HomePageModel recruitmentHomeModel=new HomePageModel();
	String menuType=request.getParameter("menuType");
	recruitmentHomeModel.initiate(context, session);
	recruitmentHomeModel.getMenuList(request, recHome, menuType);
	recruitmentHomeModel.terminate();
	return "recruitmentHomePage";
}
//Ended by Priyanka
	
}

