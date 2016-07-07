package org.struts.action.common;
import org.struts.lib.ParaActionSupport;
import org.struts.lib.ParaActionSupport;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.category.DefaultIntervalCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.RectangleInsets;
import org.jfree.util.Rotation;

import java.awt.Color;
import java.util.Date;
import java.util.Map;

import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProducer;
import org.paradyne.bean.common.LeaveHome;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.HomePageModel;
import org.paradyne.model.common.HrmHomeModel;
import org.paradyne.model.common.LeaveHomeModel;

public class LeaveHomeAction extends ParaActionSupport {
	
	LeaveHome lh;

	
	public LeaveHome getLh() {
		return lh;
	}

	public void setLh(LeaveHome lh) {
		this.lh = lh;
	}

	public void prepare_local() throws Exception {
		// TODO Auto-generated method stub
		
		lh=new LeaveHome();
	}

	public Object getModel() {
		// TODO Auto-generated method stub
		return lh;
	}
	
	//Overall
	public String pie(){
		System.out.println("---------------+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		LeaveHomeModel lhm=new LeaveHomeModel();
		lhm.initiate(context,session);
		 final Object[][] result=lhm.getBranch(lh);
		 final Object[][] result1=lhm.getDepartment(lh);
		 final Object[][] result2=lhm.overAll(lh);
		 
		 DatasetProducer pieData = new DatasetProducer() {
			    public Object produceDataset(Map params) {
			      final String[] categories = { "Present","Absentees","Leave","Late"};
			      DefaultPieDataset ds = new DefaultPieDataset();
					      for (int i = 0; i < categories.length; i++) {
							       try{
							    	 int y = (int) Integer.parseInt(String.valueOf(result2[i][0]));
							        ds.setValue(categories[i], new Integer(y));
							       }catch(Exception e){
							    	   e.printStackTrace();			    	   
							       }
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
		        
		    	 /* pieplot.setSectionPaint( 1,new Color(128, 128, 223));
			         pieplot.setSectionPaint( 2,new Color(96, 96, 191));
			         pieplot.setSectionPaint( 3,new Color(64, 64, 159));
			         pieplot.setSectionPaint(4, new Color(32, 32, 127));*/
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
			
			  request.setAttribute("pieData", pieData);

		
		
		//Departmentwise
		if (request.getAttribute("initFlag") == null) {
		 DatasetProducer pieData1 = new DatasetProducer() {
			    public Object produceDataset(Map params) {
			      final String[] categories =new String[result1.length];// {  "Physics","Chemistry","Mathematics" };
			      DefaultPieDataset ds = new DefaultPieDataset();
			      for (int i = 0; i < result1.length; i++) {
			    		categories[i] = String.valueOf(result1[i][1]);
			       
			      
			      }
			  	for (int i = 0; i < categories.length; i++) {
					int y = Integer.parseInt(String
							.valueOf(result1[i][0]));
					ds.setValue(categories[i], new Integer(y));

				}
			   //   for (int i = 0; i < categories.length; i++) {
			   //     int y = (int) (Math.random() * 10 + 1);
			  //      ds.setValue(categories[i], new Integer(y));
			      
			   //   }
			      return ds;
			    }
			    public String getProducerId() {
			      return "PieDataProducer";
			    }
			    public boolean hasExpired(Map params, Date since) {
			      return false;
			    }
			  };
			
			  request.setAttribute("pieData1", pieData1);

			}
		//Branchwise
		if (request.getAttribute("initFlag") == null) {
			 DatasetProducer pieData2 = new DatasetProducer() {
				    public Object produceDataset(Map params) {
				      final String[] categories = new String[result.length];//{ "Rourkela", "Bhubaneswar", "Cuttack", "Kolkata" };
				      DefaultPieDataset ds = new DefaultPieDataset();
				     /* for (int i = 0; i < categories.length; i++) {
				        int y = (int) (Math.random() * 10 + 1);
				        ds.setValue(categories[i], new Integer(y));
				      
				      }*/
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
				  request.setAttribute("pieData2", pieData2);

				}
		//empLeave();
	//	pendingLeave();
	//	lhm.empOnPending(lh);
	//	lhm.empOnLeave(lh);
		lhm.terminate();
		return "attendance";
		}
		/* (non-Javadoc)
		 * @see org.struts.lib.ParaActionSupport#prepare_local()
		 */
	public String empLeave()throws Exception{
	
		LeaveHomeModel lhm=new LeaveHomeModel();
		lhm.initiate(context,session);
		lh.setName("bunnuuuu");
		System.out.println(lh.getName());
		lhm.empOnLeave(lh);
		lhm.terminate();
		return "empLeave";
			
	}
	public String pendingLeave()throws Exception{
	
		System.out.println("Pending Leave Application-------------------------------------");
		LeaveHomeModel lhm=new LeaveHomeModel();
		lhm.initiate(context,session);
		lhm.empOnPending(lh);
		lhm.terminate();	 
		System.out.println("Pending Leave Application---------------11111111111122222222223333333333----------------------");
		return "pending";
	}
	
	
	//leave
	
	public String getLeaveDetails()throws Exception {
		LeaveHomeModel lhm=new LeaveHomeModel();
		lhm.initiate(context,session);
		lhm.getLeaveDetails(lh);
		lhm.terminate();
		return "leaveJsp";
		
	}
	
	
	public String getLeaveinfoDetails()throws Exception {
		LeaveHomeModel lhm=new LeaveHomeModel();
		lhm.initiate(context,session);
		lhm.getLeaveinfoDetails(lh);
		lhm.terminate();
		return "leaveinfoJsp";
		
	}
	
	public String input() throws Exception {
		DefaultDashletConfigModel model  = new DefaultDashletConfigModel();
		model.initiate(context, session);
		model.getDashletConfig("15", lh.getUserProfileId(),lh.getUserLoginCode(), request);
		model.terminate();
		return "leaveAttendanceHomePage";
	}
	
	
	
	//Added by Priyanka
	
	public String getLeaveAttendanceMenu() throws Exception {
		HomePageModel lhm=new HomePageModel();
		String menuType=request.getParameter("menuType");
		lhm.initiate(context, session);
		lhm.getMenuList(request, lh, menuType);
		lhm.terminate();
		return "leaveAttendanceHomePage";
	}
	
	
	//Ended by Priyanka
}
