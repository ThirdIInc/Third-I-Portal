/**
 * 
 */
package org.struts.action.common;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.HrmHomeModel;

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
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.block.BlockBorder;
import org.jfree.chart.block.BlockContainer;
import org.jfree.chart.block.BorderArrangement;
import org.jfree.chart.block.EmptyBlock;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterInterval;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.title.CompositeTitle;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.Range;

import org.paradyne.bean.common.MyPage;
import org.paradyne.model.common.HomePageModel;
import org.paradyne.model.common.LoginModel;
import org.paradyne.model.common.MyPageModel;
import org.paradyne.model.common.PayrollHomeModel;
import org.paradyne.model.common.RecruitmentHomeModel;
import org.paradyne.model.common.WeekPlannerModel;
import org.struts.lib.ParaActionSupport;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;


import com.lowagie.text.Image;

import  de.laures.cewolf.taglib.ChartImageDefinition;
import java.util.Date;

import javax.swing.ImageIcon;

import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProduceException;
import de.laures.cewolf.DatasetProducer;

import de.laures.cewolf.links.LinkGenerator;
import de.laures.cewolf.links.XYItemLinkGenerator;
import demo.CombinedCategoryPlotDemo1;

import org.paradyne.bean.common.HrmHome;
/**
 * @author Pradeep Sahoo
 *
 */
public class HrmHomeAction extends ParaActionSupport {

	
	HrmHome hrm;
	
	
	
	public HrmHome getHrm() {
		return hrm;
	}

	public void setHrm(HrmHome hrm) {
		this.hrm = hrm;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		hrm=new HrmHome();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return hrm;
	}
	
	public String input() throws Exception {
		DefaultDashletConfigModel model  = new DefaultDashletConfigModel();
		model.initiate(context, session);
		model.getDashletConfig("410", hrm.getUserProfileId(),hrm.getUserLoginCode(), request);
		model.terminate();
		return "hrmHomePage";
	}
	public String pie(){/*
		HrmHomeModel hhm=new HrmHomeModel();
		hhm.initiate(context,session);
		  final Object[][] result=hhm.getQualification(hrm);
		  final Object[][] result1=hhm.getDepartment(hrm);
		  final Object[][] result2=hhm.getCategory(hrm);
		hrm.setLoginTime("10");
		
		//Qualificationwise
	if (request.getAttribute("initFlag") == null) {
		 DatasetProducer pieData = new DatasetProducer() {
		    public Object produceDataset(Map params) {
		    	final String[] categories = new String[result.length];
		      DefaultPieDataset ds = new DefaultPieDataset();
		      for (int i = 0; i < result.length; i++) {
		    		categories[i] = String.valueOf(result[i][1]);
		       
		      
		      }
		  	for (int i = 0; i < categories.length; i++) {
				int y = Integer.parseInt(String
						.valueOf(result[i][0]));
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
			 	
			    public void processChart(Object chart, Map params) {
			    	  PiePlot pieplot = (PiePlot)((JFreeChart)chart).getPlot();
			    	  pieplot.setNoDataMessage("No data available");
			        //  pieplot.setExplodePercent("Two", 0.20000000000000001D);
			          pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2} )"));
			          pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));
			          //pieplot.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("Tooltip for legend item {0}"));
			        //  pieplot.setSimpleLabels(true);
			          pieplot.setInsets(RectangleInsets.ZERO_INSETS);
			          //pieplot.setInteriorGap(0.0D);
			          pieplot.setOutlinePaint(Color.white);
			        //  pieplot.setOutlineVisible(false);
			          pieplot.setBackgroundPaint(Color.white);
			          pieplot.setBaseSectionOutlinePaint(Color.white);
			          pieplot.setBaseSectionPaint(Color.white);
			        
			          
			    }
		 };
		 request.setAttribute("meterRanges", meterPP);
		  request.setAttribute("pieData", pieData);

		}
	
	//Departmentwise
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
			 	
			    public void processChart(Object chart, Map params) {
			    	  PiePlot pieplot = (PiePlot)((JFreeChart)chart).getPlot();
			    	  pieplot.setNoDataMessage("No data available");
			          pieplot.setExplodePercent("Two", 0.20000000000000001D);
			          pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2} )"));
			          pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));
			          //pieplot.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("Tooltip for legend item {0}"));
			          pieplot.setSimpleLabels(true);
			          pieplot.setInsets(RectangleInsets.ZERO_INSETS);
			          //pieplot.setInteriorGap(0.0D);
			          pieplot.setOutlinePaint(Color.white);
			          pieplot.setOutlineVisible(false);
			          pieplot.setBackgroundPaint(Color.white);
			          pieplot.setBaseSectionOutlinePaint(Color.white);
			          pieplot.setBaseSectionPaint(Color.white);
			        
			          
			    }
		 };request.setAttribute("meterRanges", meterPP);
		  request.setAttribute("pieData1", pieData);

		}
	
	if (request.getAttribute("initFlag") == null) {
		 DatasetProducer pieData = new DatasetProducer() {
		    public Object produceDataset(Map params) {
		    	final String[] categories = new String[result2.length];
		      DefaultPieDataset ds = new DefaultPieDataset();
		      for (int i = 0; i < result2.length; i++) {
		    		categories[i] = String.valueOf(result2[i][1]);
		       
		      
		      }
		  	for (int i = 0; i < categories.length; i++) {
				int y = Integer.parseInt(String
						.valueOf(result2[i][0]));
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
			 	
			    public void processChart(Object chart, Map params) {
			    	  PiePlot pieplot = (PiePlot)((JFreeChart)chart).getPlot();
			    	  pieplot.setNoDataMessage("No data available");
			          pieplot.setExplodePercent("Two", 0.20000000000000001D);
			          pieplot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0} ({2} )"));
			          pieplot.setLabelBackgroundPaint(new Color(220, 220, 220));
			          //pieplot.setLegendLabelToolTipGenerator(new StandardPieSectionLabelGenerator("Tooltip for legend item {0}"));
			          pieplot.setSimpleLabels(true);
			          pieplot.setInsets(RectangleInsets.ZERO_INSETS);
			          //pieplot.setInteriorGap(0.0D);
			          pieplot.setOutlinePaint(Color.white);
			          pieplot.setOutlineVisible(false);
			          pieplot.setBackgroundPaint(Color.white);
			          pieplot.setBaseSectionOutlinePaint(Color.white);
			          pieplot.setBaseSectionPaint(Color.white);
			        
			          
			    }
		 };
	//	 request.setAttribute("meterRanges", meterPP);
		  request.setAttribute("pieData2", pieData);

		}
	
	
	
	
	final Object[][] cent = hhm.getCenter(hrm);
	final Object[][] payBill=hhm.getPayBill(hrm);
	 DatasetProducer categoryData = new DatasetProducer() {
		    public Object produceDataset(Map params) {
		    	
		      final String[] categorieNames ={"HR","Software","Customer Support","Accounts","Sales"};
		      final String[] seriesNames = {"HR"};
		      //{String.valueOf(cent[0][0])};
		      for (int j = 0; j < categorieNames.length; j++) {
		    	  categorieNames[j]=String.valueOf(cent[j][1]);
		    	  
			}
		      final Integer[][] startValues = new Integer[seriesNames.length][categorieNames.length];
		      final Integer[][] endValues = new Integer[seriesNames.length][categorieNames.length];
		      for (int series = 0; series < seriesNames.length; series++) {
		    	  try{
		    	  for (int i = 0; i <categorieNames.length; i++) {
		    		  
		    		  int y = (int) (Math.random() * 10) ;
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
		  request.setAttribute("categoryData", categoryData);
		  
		  
		  
		  
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
	hhm.getSuggestion(hrm);
	hhm.terminate();*/
	return "success";
	}
	
	private  CategoryDataset createDataset3()
    {
		HrmHomeModel hhm=new HrmHomeModel();
		hhm.initiate(context,session);
		  final Object[][] result=hhm.getDepartment(hrm);
		
	 
		
			    	 
			    	String s = "Department";
			        
			      
			        DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
			        for(int i=0;i<result.length;i++){
						 System.out.println("--------------result----------------->>"+result[i][0]);
							if(!(String.valueOf(result[i][0]).equals("0"))){
			        defaultcategorydataset.addValue(Integer.parseInt(String.valueOf(result[i][0])), s, String.valueOf(result[i][1]));
			    	 }
			        }
        return defaultcategorydataset;
    }
 
	
	public String getmanPower()throws Exception {
		
		
		HrmHomeModel hhm=new HrmHomeModel();
		hhm.initiate(context,session);
		  final Object[][] result=hhm.getQualification(hrm);
		  final Object[][] result1=hhm.getDepartment(hrm);
		  final Object[][] result2=hhm.getCategory(hrm);
		hrm.setLoginTime("10");
		//Qualification Wise

		//Qualificationwise 
		 DatasetProducer categoryData = new DatasetProducer() {
			    public Object produceDataset(Map params) {
			    	 
			    	String s = "Department";
			        
			      
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
			      return true;
			    }
			  };
			  request.setAttribute("categoryData", categoryData);
			  
			  ChartPostProcessor meterPP = new ChartPostProcessor() {
					 
				    public void processChart(Object chart, Map params) {
				   
				        CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart).getPlot();
				       
				        categoryplot.setBackgroundPaint(new Color(238, 238, 255));
				       /* java.net.URL url = (HrmHomeAction.class).getClassLoader().getResource("Life.jpg");
				        if(url != null)
				        {
				            ImageIcon imageicon = new ImageIcon(url);
				            categoryplot.setBackgroundImage(imageicon.getImage());
				           
				        }*/
				        CategoryItemRenderer categoryitemrenderer = categoryplot.getRenderer();
				        categoryitemrenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator("{2}",NumberFormat.getNumberInstance()));
				        categoryitemrenderer.setSeriesItemLabelsVisible(0, Boolean.TRUE);
				       // CategoryAxis categoryaxis = categoryplot.getDomainAxis();
				        /*for(int i=0;i<result1.length;i++){
				        CategoryTextAnnotation categorytextannotation = new CategoryTextAnnotation("", String.valueOf(result1[i][0]), 0.2D);
				        }*/
				        CategoryDataset categorydataset = createDataset3();
				        categoryplot.setDataset(1, categorydataset);
				        categoryplot.mapDatasetToRangeAxis(1, 1);
				        CategoryAxis categoryaxis = categoryplot.getDomainAxis();
				        categoryaxis.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
				        NumberAxis numberaxis = new NumberAxis("");
				        numberaxis.setAxisLinePaint(Color.MAGENTA);
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
		return "manPower";
		
	}
	public String getSugg()throws Exception {
		HrmHomeModel hhm=new HrmHomeModel();
		hhm.initiate(context,session);
		hhm.getSuggestion(hrm);
		hhm.terminate();
		return "sugg";
		
	}
	
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
	public String getAnnual()throws Exception {
		HrmHomeModel hhm=new HrmHomeModel();
		hhm.initiate(context,session);
		System.out.println("......tgfsdg.........out url");
		final Object[][] cent = hhm.getCenter(hrm);
		final Object[][] payBill=hhm.getPayBill(hrm);
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
				  //JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", "Temperature", dataset, -40D, 60D, 10D, 4);
				    public void processChart(Object chart, Map params) {
				    	 //dataset = new DefaultValueDataset(10D);
				    	// logger.info("_________________________________IN  processChart_____________________________________");
				        
				        
				    	 //DialPlot dialplot = (DialPlot) ((JFreeChart) chart).getPlot();
				    	 
				    	// PiePlot pieplot = (PiePlot)((JFreeChart) chart).getPlot();
				        CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart).getPlot();
				        /*java.net.URL url = (HrmHomeAction.class).getClassLoader().getResource("bg.jpg");
				        System.out.println("...............out url"+url);
				        if(url != null)
				        {
				        	System.out.println("...............in url"+url);
				            ImageIcon imageicon = new ImageIcon(url);
				            categoryplot.setBackgroundImage(imageicon.getImage());
				           // categoryplot.setBackgroundPaint(new Color(0, 0, 0, 0));
				        }*/
				       // categoryplot.setBackgroundPaint(new Color(238, 238, 255));
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
				     //  categoryplot.setForegroundAlpha(0.6F);
				    
				        
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
		return"annual";
		
	}
	public String getWorkFlow()throws Exception {
		HrmHomeModel hhm=new HrmHomeModel();
		hhm.initiate(context,session);
		hhm.getIncrDue(hrm);
		hhm.terminate();
		System.out.println("Pradeep kumar Sahoo");
		return "work";
	}
	
	
	/** modified by Reeba Joseph **/
	public String showOnSubject() {
		HrmHomeModel model=new HrmHomeModel();
		model.initiate(context, session);
		String suggId = request.getParameter("suggId");
		System.out.println("suggestion code ________$$$$_______-"+suggId);
		model.showOnSubject(hrm, suggId);
		model.terminate();
		return "grievanceView";
	}
	
	public String showSettlementDue() {
		HrmHomeModel model=new HrmHomeModel();
		model.initiate(context, session);
		model.showSettlementDue(hrm);
		model.terminate();
		return "settlementDue";
	}
	
	//Added by Priyanka
	public String getHrmMenu() throws Exception {
		HomePageModel hrmHomeModel=new HomePageModel();
		String menuType=request.getParameter("menuType");
		hrmHomeModel.initiate(context, session);
		hrmHomeModel.getMenuList(request, hrm, menuType);
		hrmHomeModel.terminate();
		return "hrmHomePage";
	}
	//Ended by Priyanka
}
	/*final String[][] str = loginModel.getCalls();
	final String[][] strMon=loginModel.getChart();
	DatasetProducer categoryData = new DatasetProducer() {
		public Object produceDataset(Map params) {
			final String[] categories = { "SEPTEMBER" };
			final String[] seriesNames = new String[strMon.length];
			final String[] categoryNames = new String[strMon.length];
			for (int i = 0; i < strMon.length; i++) {
				seriesNames[i] = strMon[i][1];
				categoryNames[i]=strMon[i][2];
				System.out.println("category values-------------============================"+categoryNames.length);
			}
			final Integer[][] startValues = new Integer[seriesNames.length][categories.length];
			final Integer[][] endValues = new Integer[seriesNames.length][categories.length];
			for (int series = 0; series < seriesNames.length; series++) {
				seriesNames[series] = strMon[series][1];
				categoryNames[series]=strMon[series][2];
				for (int i = 0; i < categories.length; i++) {
		
					int y = Integer.parseInt(String.valueOf(categoryNames[series]));
					System.out.println("value of------------------------------------------"+ y);
					startValues[series][i] =new Integer(y);
					
					endValues[series][i] =new Integer(y);
					System.out.println("start value--------------------------------------"+ startValues[series][i] );
					System.out.println("end value--------------------------------------"+ endValues[series][i]);
							
							
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
	 (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 
}
	
	
*/