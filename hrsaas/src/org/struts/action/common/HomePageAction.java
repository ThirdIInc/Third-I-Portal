package org.struts.action.common;
import java.awt.Font;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.dom4j.Document;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.CategoryTextAnnotation;
import org.jfree.chart.axis.CategoryAnchor;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.data.Range;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.paradyne.bean.common.HomePage;
import org.paradyne.lib.XMLReaderWriter;
import org.paradyne.model.ApplicationStudio.DefaultDashletConfigModel;
import org.paradyne.model.common.DefaultMailboxConnection;
import org.paradyne.model.common.HomePageModel;
import org.struts.lib.ParaActionSupport;
import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProducer;

/**
 * @author lakkichand modified by: Reeba_Joseph
 * @Date 12-DEC-2008
 * @modified By AA1711 To Dispaly HomePage Dashlet DivisionWise
 * @Date 22-Jan-2013
 */
public class HomePageAction extends ParaActionSupport {
	private static final long serialVersionUID = 1L;
	HomePage home = null;
	String poolName = "";
	String type = "";

	/*
	 * (non-Javadoc)
	 * @see org.struts.lib.ParaActionSupport#prepare_local()
	 */

	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(org.struts.lib.ParaActionSupport.class);

	public void prepare_local() throws Exception {
		home = new HomePage();
		poolName = String.valueOf(session.getAttribute("session_pool"));
	}

	/*
	 * (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	public Object getModel() {
		return home;
	}

	/**
	 * @return the home
	 */
	public HomePage getHome() {
		return home;
	}

	/**
	 * @param home
	 *            the home to set
	 */
	public void setHome(HomePage home) {
		this.home = home;
	}

	/**Method Name: input()
	 * @purpose Used to display Dashlet with respective Details
	 * @see com.opensymphony.xwork2.ActionSupport#input()
	 * @return String
	 */
	public String input() throws Exception {
		try {
			DefaultDashletConfigModel model = new DefaultDashletConfigModel();
			model.initiate(context, session);
			model.getDashletConfig("35", home.getUserProfileId(), home
					.getUserLoginCode(), request);
			home.setCeoFlag("false");
			model.terminate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "decisionOneHomePage";
	}

	/**
	 * Reading Corporate Communication XML
	 * @return String
	 * @throws Exception
	 */
	public String getCorpComm_XML() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
			poolName = "\\" + poolName;
		}// END if
		String path = getText("data_path") + "\\datafiles\\" + poolName
				+ "\\xml\\corp_comm\\cmp.xml"; // path of XML
		String[][] corpList;
		try {
			// reading XML
			corpList = model.processCorpComm(new XMLReaderWriter()
					.parse(new File(path)));
		} catch (Exception e) {
			logger.error("Exception Caught - Corp Communication: " + e);
			corpList = new String[0][0];
		}
		request.setAttribute("corpList", corpList);
		return "corpComm";
	}
	
	/**Method name: rewardData()
	 * @purpose Used to display Reward Data in Dashlet
	 * 		    and Return Reward's Dashlet's JSP
	 * @Modified By : AA1711 -Randomly move Awards according to  Financial Year
	 * @return String 
	 */
	public String rewardData()
	{
			try {				
				HomePageModel model = new HomePageModel();
				model.initiate(context, session);
				Date date= new Date();
				DateFormat dateFormater = new SimpleDateFormat("dd-MM-yyyy");
				String currentDate = dateFormater.format(date);				
				String currentYear = currentDate.substring(6, 10);
				int frmYear= Integer.parseInt(currentYear);				
				int currentMonth= date.getMonth();
				if(currentMonth <3){
					frmYear = frmYear-1;
				}			
				model.getAwardImages(request,home, frmYear);
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "rewardDashlet";	
	}
	
	/**Method Name :corporateEvent()
	 * @purpose Used to Display Events in Corporate Event Dashlet's JSP
	 * 	        And return that JSP
	 * @return String 
	 */
	public String corporateEvent()
	{
			try {
				HomePageModel model = new HomePageModel();
				model.initiate(context, session);
				model.getEventData(request, home);
				model.terminate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		return "corporateEvent";	
	}
	
	/**Method Name :quickDownloadInfoDashlet()
	 * @purpose Used to Display information in Quick DownLoad Dashlet's JSP
	 * 	        and return that JSP
	 * @return String 
	 */
	public String quickDownloadInfoDashlet(){
			try {
				HomePageModel model = new HomePageModel();
				model.initiate(context, session);
				model.quickDownload(request, home);
				model.terminate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "quickDownload";	
	}
	
	/**Method Name :ceoConnect()
	 * @purpose Used to Display CEO Dashlet's JSP
	 * @return String
	 */
	public String ceoConnect(){
	  try {
			HomePageModel model = new HomePageModel();
			model.initiate(context, session);
			home.setCeoFlag("false");
			String ceoResult="SELECT EMP_ID FROM HRMS_CEO_CONFIG WHERE ISCEOORMSGADMIN='C'";
			System.out.println("............IN HOME PAGE MODEL......."+ceoResult);
			Object result[][]=model.getSqlModel().getSingleResult(ceoResult);
			System.out.println("........IN HOME PAGE MODEL....."+result);
			if(result != null && result.length > 0)
			{
				home.setCeoPresentFlag("true");
			}
			else
			{
			home.setCeoPresentFlag("false");
			}
			model.ceoConnect(request, home);
			model.terminate();
			request.setAttribute("login_EmpId", home
						.getUserEmpId());
	   } catch (Exception e) {
				e.printStackTrace();
	   }
	   return "ceoConnect";	
	}
	
	public String ceoConnectData(){
	  try {
			
	  } catch (Exception e) {
				e.printStackTrace();
	  }
	  return "ceoConnectData";	
	}
	
	/**Method to show messages when user is CEO
	 *  @author Prajakta.Bhandare
	 *  @date 15 Feb 2013
	 * @return String 
	 */
	public String ceoConnectMsg(){
		try {
			HomePageModel model = new HomePageModel();
			model.initiate(context, session);
			model.getCeoConnectMsg(request, home);
			model.terminate();
		  } catch (Exception e) {
					e.printStackTrace();
		  }
		return "ceoConnectMsg";	
	}
	
	/**
	 * To set the page according to the page numbers
	 * @author Prajakta.Bhandare
	 * @return String
	 * @throws Exception
	 */
	public String callPage() throws Exception {
		try {
			HomePageModel model = new HomePageModel();
			model.initiate(context, session);
			model.getCeoConnectMsg(request, home);
			model.terminate();
		} catch (RuntimeException e) {
			e.printStackTrace();
		}
		return "ceoConnectMsg";	
	}
	
	/**Method Name: getCorpComm()
	 * @purpose Used to display division wise Corporate Information on Dashlet
	 * @return String
	 * @throws Exception
	 */
	public String getCorpComm() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		String[][] corpList = null;
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ home.getUserEmpId();
		     Object empDivObj[][] = model.getSqlModel().getSingleResult(
				empDivQuery);
			String query = " SELECT CORPMSG_LINKNAME,CORPMSG_LINK FROM HRMS_SETTINGS_CORPMSG" 
					 		+ " WHERE CORPMSG_FLAG='Y' AND" 
					 		+ " (','||CORPMSG_DIVISION||',' LIKE '%,"+empDivObj[0][0]+",%' OR CORPMSG_DIVISION IS NULL)"
					 		+ " ORDER BY CORPMSG_CODE DESC";
			Object accessDivObj[][] = model.getSqlModel()
					.getSingleResult(query);
			if (accessDivObj != null && accessDivObj.length >0) {//Check for null
				corpList = new String[accessDivObj.length][2];
				for (int i = 0; i < accessDivObj.length; i++) {
					corpList[i][0] = String.valueOf(accessDivObj[i][0]); //Link Name
					corpList[i][1] = String.valueOf(accessDivObj[i][1]); //Link
				}
			}//end if				
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("corpList", corpList);
		return "corpComm";
	}
	
	/**Method Name : getBulletin()
	 * Reading HrWorK Communication XML
	 * @return String
	 * @throws Exception
	 */
	public String getBulletin() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		String path = getText("data_path")
				+ "\\datafiles\\hrwork_comm\\hrworkcomm.xml";// path of XML
		String[][] hrWorkList;
		try {
			// reading from XML
			hrWorkList = model.processHrWorkComm(new XMLReaderWriter()
					.parse(new File(path)));
		} catch (Exception e) {
			hrWorkList = new String[0][0];
			logger.error("Exception Caught - HrWorK Communication: " + e);
		}
		model.terminate();
		request.setAttribute("bulletinList", hrWorkList);
		return "bulletin";
	}
	
	/**Method Name: getQuickSearch
	 * @return String
	 */
	public String getQuickSearch() {
		return "quickSearch";
	}

	/**Method Name: getCorpInfo_XML()
	 * Reading Corporate Information XML
	 * @return String
	 * @throws Exception
	 */
	public String getCorpInfo_XML() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		String poolName = String.valueOf(session.getAttribute("session_pool"));
		model.terminate();
		if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
			poolName = "\\" + poolName;
		}
		String path = getText("data_path") + "\\datafiles\\" + poolName
				+ "\\xml\\corp_info\\corp.xml";// path of XML
		String[][] corpInfoList;
		try {
			// XML read
			corpInfoList = model.processCorpInfo(new XMLReaderWriter()
					.parse(new File(path)));
		} catch (Exception e) {
			corpInfoList = new String[0][0];
			logger.error("Exception Caught - Corporate Info: " + e);
		}
		request.setAttribute("corpInfoList", corpInfoList);
		return "corpInfo";
	}
	
	
	/**Method Name: getCorpInfo()
	 * @purpose Used to display division wise Corporate Message on Dashlet
	 * @return String
	 * @throws Exception
	 */
	public String getCorpInfo() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		String[][] corpInfoList=null;
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ home.getUserEmpId();
			Object empDivObj[][] = model.getSqlModel().getSingleResult(
				empDivQuery);
			String query = " SELECT CORPINFO_LINKNAME,CORPINFO_LINK FROM HRMS_SETTINGS_CORPINFO" 
					 	  + " WHERE CORPINFO_FLAG='Y' AND (','||CORPINFO_DIVISION||',' LIKE '%,"+empDivObj[0][0]
					 	  + ",%' OR CORPINFO_DIVISION IS NULL)"  
					 	  + " ORDER BY CORPINFO_CODE DESC";
			Object accessDivObj[][] = model.getSqlModel()
					.getSingleResult(query);
			if (accessDivObj != null && accessDivObj.length > 0) {//Check for null
				corpInfoList = new String[accessDivObj.length][2];
				for (int i = 0; i < accessDivObj.length; i++) {
					corpInfoList[i][0] = String.valueOf(accessDivObj[i][0]); //Link Name
					corpInfoList[i][1] = String.valueOf(accessDivObj[i][1]); //Link
				}
			}// end if
		} catch (Exception e) {
			corpInfoList = new String[0][0];
			logger.error("Exception Caught - Corporate Info: " + e);
		}
		request.setAttribute("corpInfoList", corpInfoList);
		return "corpInfo";
	}

	/**Method Name: getKnowInfo_XML()
	 * Reading XML for Knowledge Information
	 * @return String
	 * @throws Exception
	 */
	public String getKnowInfo_XML() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		if (!(poolName.equals("") || poolName == null || poolName.equals(null))) {
			poolName = "\\" + poolName;
		}
		String path = getText("data_path") + "\\datafiles\\" + poolName
				+ "\\xml\\knowledge\\know.xml";// path of XML
		String[][] knowList;
		try {
			// XML read
			knowList = model.processKnowInfo(new XMLReaderWriter()
					.parse(new File(path)));
		} catch (Exception e) {
			knowList = new String[0][0];
			logger.error("Exception Caught - Knowledge Info: " + e);
		}
		request.setAttribute("knowList", knowList);
		model.terminate();
		return "knowInfo";
	}
	
	/**Method Name: getKnowInfo()
	 * @purpose Used to display division wise Knowledge Information on Dashlet
	 * @return String
	 * @throws Exception
	 */
	public String getKnowInfo() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		String[][] knowledgeList = null;
		try {
			String empDivQuery = " SELECT EMP_DIV FROM HRMS_EMP_OFFC WHERE EMP_ID="
				+ home.getUserEmpId();
			Object empDivObj[][] = model.getSqlModel().getSingleResult(
				empDivQuery);
			String query = " SELECT DISTINCT KNOW_CATEGORY FROM HRMS_SETTINGS_KNOWLEDGE" 
					 + " WHERE KNOW_FLAG='Y' AND (','||KNOW_DIVISION||',' LIKE '%,"+empDivObj[0][0]
					 + ",%' OR KNOW_DIVISION IS NULL)";

			Object accessDivObj[][] = model.getSqlModel()
					.getSingleResult(query);
			if (accessDivObj != null && accessDivObj.length > 0) { //Check for null
				knowledgeList = new String[accessDivObj.length][2];
				for (int i = 0; i < accessDivObj.length; i++) {
					knowledgeList[i][0] = String.valueOf(accessDivObj[i][0]); //Link Name				
				}
			} //End if
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("knowList", knowledgeList);
		return "knowledgeList";
	}

	/**Method Name :getGenInfo()
	 * @purpose Used to Display Quick Information Dashlet's JSP
	 * 	        And return that JSP
	 * @return String
	 */
	public String getGenInfo() {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		Object[][] genList = null;
		String query ="SELECT GENERAL_ID, GENERAL_LINK_NAME FROM HRMS_SETTINGS_GENERAL "
		+" WHERE GENERAL_VIEW_FLAG='Y' "
		+" ORDER BY GENERAL_ID ";
		try {
			// XML read
			genList =model.getSqlModel().getSingleResult(query) ;
		} catch (Exception e) {
			genList = new Object[0][0];
			logger.error("Exception Caught - General Info: " + e);
		}
		request.setAttribute("genList", genList);
		model.terminate();
		return "genInfo";
	}
		
	/**Method Name: getPolls()
	 * @purpose : Used to display poll on opinion Poll's Dashlet
	 * 			  And return that JSP 
	 * @return String
	 * @throws Exception
	 */
	public String getPolls() throws Exception{
		try {
			HomePageModel model = null;
			try {
				model = new HomePageModel();
				model.initiate(context, session);
				String[][] pollList = null;
				if (!(poolName.equals("") || poolName == null || poolName
						.equals(null))) {
					poolName = "\\" + poolName;
				}
				// READING FROM XML
				String path = getText("data_path") + "\\datafiles\\" + poolName
						+ "\\xml\\poll\\poll.xml";

				File file = new File(path);
				final Object[][] result = model.processPollInfo(home);
				logger.info("Poll Code-----" + home.getPollCode());
				Object optionObj[][] = new Object[result.length][4];
				for (int i = 0; i < optionObj.length; i++) {
					optionObj[i][0] = result[i][0];
					optionObj[i][2] = result[i][2];
					optionObj[i][3] = result[i][3];
					optionObj[i][1] = String.valueOf(result[i][1]).replace(
							"%26apos;", "'").replace("%26", "&");
				}
				request.setAttribute("pollData", result);
				request.setAttribute("optionObj", optionObj);
				model.terminate();
				model.terminate();
			} catch (Exception e) {
				e.printStackTrace();
			}		
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "pollChart";
	}
	
/*
	*//**
	 * XML read for polls. Reading XML on the homepage of application 
	 * @return String - jsp page(pollChart.jsp)
	 * @throws Exception
	 *//*
	public String getPolls_OLD() throws Exception {
		try {
			HomePageModel model = new HomePageModel();
			model.initiate(context, session);
			request.setAttribute("dashletId", request.getParameter("dashId"));
			request.setAttribute("dashletWidth", request
					.getParameter("dashWidth"));
			String[][] pollList = null;
			if (!(poolName.equals("") || poolName == null || poolName
					.equals(null))) {
				poolName = "\\" + poolName;
			}// end of if
			// READING FROM XML
			String path = getText("data_path") + "\\datafiles\\" + poolName
					+ "\\xml\\poll\\poll.xml";
			File file = new File(path);
			if (file.exists()) {
				final Object[][] result = model.processPollInfo(
						new XMLReaderWriter().parse(new File(path)), home);
				logger.info("Poll Code-----" + home.getPollCode());
				Object optionObj[][] = new Object[result.length][4];
				for (int i = 0; i < optionObj.length; i++) {
					optionObj[i][0] = result[i][0];
					optionObj[i][2] = result[i][2];
					optionObj[i][3] = result[i][3];
					optionObj[i][1] = String.valueOf(result[i][1]).replace(
							"%26apos;", "'").replace("%26", "&");
				}
				request.setAttribute("pollData", result);
				if (String.valueOf(home.getPollCode()).equals("id")) {
					request.setAttribute("message", "File not found");
				} else {

					request.setAttribute("pollList", pollList);

					// CALCULATING TOTAL NO. OF HITS
					int totalPollCount = 0;
					for (int j = 0; j < result.length; j++)
						totalPollCount += Integer.parseInt(String
								.valueOf(result[j][2]));
					if (request.getAttribute("initFlag") == null) {
						// CREATION OF PIE CHART
						DatasetProducer pieData = new DatasetProducer() {
							public Object produceDataset(Map params) {

								final String[] categories = new String[result.length];
								for (int i = 0; i < result.length; i++) {
									categories[i] = String
											.valueOf(result[i][1]);
								}// end of loop
								DefaultPieDataset ds = new DefaultPieDataset();

								for (int i = 0; i < categories.length; i++) {
									int y = Integer.parseInt(String
											.valueOf(result[i][2]));
									ds.setValue(categories[i], new Integer(y));
								}// end of loop
								return ds;
							}// end of produce method

							public String getProducerId() {
								return "PieDataProducer";
							}

							public boolean hasExpired(Map params, Date since) {
								return true;
							}

						};// end of DataSet...Chart Creation

						ChartPostProcessor meterPP = new ChartPostProcessor() {

							public void processChart(Object chart, Map params) {
								PiePlot pieplot = (PiePlot) ((JFreeChart) chart)
										.getPlot();
								pieplot.setNoDataMessage("No data available");
								pieplot.setExplodePercent("Two",
										0.20000000000000001D);
								pieplot
										.setLabelGenerator(new StandardPieSectionLabelGenerator(
												"{2}"));
								// pieplot.setLabelBackgroundPaint(new Color(220,
								// 220,
								// 220));
								pieplot.setLabelLinksVisible(true);
								// pieplot.setLegendLabelToolTipGenerator(new
								// StandardPieSectionLabelGenerator("Tooltip for
								// legend
								// item
								// {0}"));
								// pieplot.setSimpleLabels(true);

								for (int i = 0; i < result.length; i++) {
									logger.info("color..............."
											+ String.valueOf(result[i][3]));
									// setting color
									pieplot
											.setSectionPaint(
													String
															.valueOf(result[i][1]),
													new DefaultGimpyEngine()
															.getHTMLColor(String
																	.valueOf(result[i][3])));
								}// end of loop
								pieplot.setInsets(RectangleInsets.ZERO_INSETS);
								pieplot.setInteriorGap(0.0D);
								pieplot.setOutlinePaint(Color.white);
								pieplot.setOutlineVisible(false);
								pieplot.setBackgroundPaint(Color.white);
								pieplot.setBaseSectionOutlinePaint(Color.white);
								pieplot.setBaseSectionPaint(Color.white);
								pieplot.setLegendFlag(false);
							}// end of process method
						};// end of Chart Processor
						request.setAttribute("meterRanges", meterPP);
						request.setAttribute("pieData", pieData);
						request.setAttribute("optionObj", optionObj);

						home.setTotalVotes(totalPollCount);
						if (home.getTotalVotes() == 0) {
							home.setStatFlag(true);
						}// end of nested if

					}// end of if
				}
				model.terminate();

			} else {
				logger.info("File does not exist");
				request.setAttribute("message", "File not found");

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "pollChart";
	}*/

	/**Method Name: showPollStatistics()
	 * @purpose: Displaying Poll Statistics on Opinion Poll's Dashlet 
	 * 			while Click on poll Statistics
	 * @return String - jsp page(pollStat.jsp)
	 * @throws Exception
	 */
	public String showPollStatistics() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		if (home.getClickType().equals("S")) {
			String pollCode = "";
			try {
				pollCode = request.getParameter("prevPollCode");
				logger.info("PollCode          :"
						+ request.getParameter("prevPollCode"));
				home.setPollCode(pollCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("prev PollCode()===" + home.getPollCode());
			// home.setPollCode(home.getPrevPollCode());
			// Back button only for statistics under previous polls
			home.setBackFlag(true);
		}// end of if
		else {
			String pollCode = "";
			try {
				pollCode = request.getParameter("pollCode");
				logger.info("PollCode          :"
						+ request.getParameter("pollCode"));
				home.setPollCode(pollCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			logger.info("home.getPollCode()===" + home.getPollCode());
		}
		try {
			Object[][] pollStatList = model.getPollStatistics(home);
			final String[][] pollDeptStat = model.getDeptPoll(home);
			request.setAttribute("dashletId", request.getParameter("dashId"));
			request.setAttribute("dashletWidth", request
					.getParameter("dashWidth"));
			request.setAttribute("pollStatList", pollStatList);
			request.setAttribute("pollDeptStat", pollDeptStat);
			model.terminate();
			// CREATION OF BAR GRAPH FOR DEPARTMENTWISE STATISTICS
			DatasetProducer categoryData = new DatasetProducer() {
				public Object produceDataset(Map params) {
					String s = "Department";
					DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();
					if (pollDeptStat != null && pollDeptStat.length > 0) {
						for (int i = 0; i < pollDeptStat.length; i++) {
							defaultcategorydataset.addValue(Double
									.parseDouble(String
											.valueOf(pollDeptStat[i][2])), s,
									String.valueOf(pollDeptStat[i][1]));
						}// end of loop
					}
					return defaultcategorydataset;
				}// end of produce method

	public String getProducerId() {
		return "CategoryDataProducer";
	}

		public boolean hasExpired(Map params, Date since) {
					return true;
				}
			};// end of DataSet - Bar creation
			request.setAttribute("categoryData", categoryData);
			ChartPostProcessor meterPP = new ChartPostProcessor() {
				public void processChart(Object chart, Map params) {
					try {
						CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart)
								.getPlot();
						categoryplot.setNoDataMessage("No Data Available");

						// categoryplot.setBackgroundPaint(new Color(238, 238,
						// 255));
						/*
						 * java.net.URL url =
						 * (HrmHomeAction.class).getClassLoader().getResource("Life.jpg");
						 * if(url != null) { ImageIcon imageicon = new
						 * ImageIcon(url);
						 * categoryplot.setBackgroundImage(imageicon.getImage()); }
						 */

						CategoryItemRenderer categoryitemrenderer = categoryplot
								.getRenderer();
						categoryitemrenderer
								.setItemLabelGenerator(new StandardCategoryItemLabelGenerator(
										"{2}", NumberFormat
												.getPercentInstance()));
						categoryitemrenderer.setSeriesItemLabelsVisible(0,
								Boolean.TRUE);
						CategoryAxis categoryaxis = categoryplot
								.getDomainAxis();
						categoryaxis
								.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
						CategoryTextAnnotation categorytextannotation = new CategoryTextAnnotation(
								"", String.valueOf(pollDeptStat[0][1]), 0.2D);
						categorytextannotation
								.setCategoryAnchor(CategoryAnchor.MIDDLE);
						categorytextannotation.setFont(new Font("SansSerif", 0,
								12));
						categorytextannotation
								.setTextAnchor(TextAnchor.BOTTOM_LEFT);
						categoryplot.addAnnotation(categorytextannotation);
						NumberAxis numberaxis = (NumberAxis) categoryplot
								.getRangeAxis();
						numberaxis.setNumberFormatOverride(NumberFormat
								.getPercentInstance());
						numberaxis.setRange(new Range(0.0, 1.0));

						/**
						 * to disable the labels....
						 * categoryaxis.setVisible(false);
						 */
					} catch (Exception e) {
						logger.error("Exception in processChart method:", e);
					}
				}// end of Process method
			};// end of Chart processor - details
			request.setAttribute("meterRanges", meterPP);
		} catch (Exception e) {
			logger.error("Exception in Poll statistics : ", e);
		} finally {
			logger.error("Exception in finally: ");
		}
		return "pollStat";
	}

	/**Method Name :showPreviousPoll()
	 * @purpose : DISPLAYING PREVIOUS POLL AND ITS STATISTICS
	 * @Modified for Fusion Pie Chart
	 * @return String - jsp page(prevPoll.jsp)
	 * @throws Exception
	 */
	public String showPreviousPoll() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		String pollCode = "";
		try {
			if (!request.getParameter("pollCode").equals("null")) {
				pollCode = request.getParameter("pollCode");
				logger.info("PollCode          :"
						+ request.getParameter("pollCode"));
				home.setPollCode(pollCode);
			}
		} catch (Exception e) {
			logger.error("Error in setting previous poll code: " + e);
		}				
		final String[][] result = model.getPreviousPoll(home);		
		String chartStr= model.getPieChartData(home, request);		
		request.setAttribute("chartString", chartStr);
		model.terminate();
		return "prevPoll";
	}

	/**Method Name: prevPoll()
	 * @purpose: Displaying Previous Polls
	 * @return previous poll method
	 * @throws Exception
	 */
	public String prevPoll() throws Exception {
		String pollCode = "";
		try {
			pollCode = request.getParameter("prevPollCode");
			logger.info("PollCode          :"
					+ request.getParameter("prevPollCode"));
			home.setPollCode(pollCode);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		// int pollcode = Integer.parseInt(home.getPrevPollCode());
		// home.setPollCode(home.getPrevPollCode());
		return showPreviousPoll();
	}

	/**Method Name : nextPoll()
	 * @purpose Displaying next polls> this Option present after click on View All link
	 * @return previous poll method
	 * @throws Exception
	 */
	public String nextPoll() throws Exception {
		String pollCode = "";
		try {
			pollCode = request.getParameter("prevPollCode");
			logger.info("PollCode          :"
					+ request.getParameter("prevPollCode"));
			home.setPollCode(pollCode);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		// int pollcode = Integer.parseInt(home.getPrevPollCode());
		// home.setPollCode(home.getPrevPollCode());
		return showPreviousPoll();
	}

	/**MEthod Name:pollSave()
	 * @purpose: While Click on Submit on Poll's Dashlet
	 * 			 to submit Poll Save record
	 * @return String
	 * @throws Exception
	 */
	
	public String pollSave() throws Exception {
		String path = getText("data_path") + "\\datafiles\\" + poolName
				+ "\\xml\\poll\\poll.xml";
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);

		String pollCode = "";
		String optionCode = "";
		try {
			pollCode = request.getParameter("pollCode");
			optionCode = request.getParameter("optionCode");
			request.setAttribute("dashletId", request.getParameter("dashId"));
			request.setAttribute("dashletWidth", request
					.getParameter("dashWidth"));
			home.setPollCode(pollCode);
			home.setOptionValue(optionCode);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		// query for expiry
		// check if expired or not i.e if sysdate>exp date
		// if not expired following conditions to be checked
		// Make sure the Month ("MM") in the "MM-dd-yyyy" is in CAPITALS
		/*
		 * SimpleDateFormat DateFormat = new SimpleDateFormat("dd-MM-yyyy");
		 * Date today = new Date(); String currentDate = DateFormat.format
		 * (today).toString (); System.out.println("current
		 * date------"+currentDate);
		 */
		String expiryQuery = "SELECT TO_CHAR(EXPIRY_DATE,'DD-MM-YYYY') FROM HRMS_POLL_HDR WHERE TO_DATE(SYSDATE,'dd-mm-yyyy') > TO_DATE(EXPIRY_DATE,'dd-mm-yyyy') "
				+ " AND POLL_CODE= " + home.getPollCode();
		Object expiryData[][] = model.getSqlModel()
				.getSingleResult(expiryQuery);
		if (expiryData != null && expiryData.length > 0) {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write("<message>expiry</message>");
		} else {
			String pollCheck = "SELECT * FROM HRMS_POLL_EMP WHERE POLL_EMP_ID ="
					+ home.getUserEmpId()
					+ " AND "
					+ "POLL_CODE="
					+ home.getPollCode() + " ";
			Object pollData[][] = model.getSqlModel()
					.getSingleResult(pollCheck);
			if (!(pollData.length > 0)) {
				String pollQuery = " INSERT INTO HRMS_POLL_EMP(POLL_EMP_ID,POLL_CODE,POLL_DATE,POLL_OPTION_CODE) "
						+ " VALUES ("
						+ home.getUserEmpId()
						+ ","
						+ home.getPollCode()
						+ ",to_date(sysdate,'dd-mm-yyyy'),"
						+ home.getOptionValue() + ")  ";

				model.getSqlModel().singleExecute(pollQuery);
				Document doc = model.writeInXML(new XMLReaderWriter() // Read from XML on load
						.parse(new File(path)), home);
				try {
					new XMLReaderWriter().write(doc, path); // Write into XML on
					// submit
				} catch (Exception e) {
					e.printStackTrace();
				}
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("<message>valid</message>");
			} else {
				response.setContentType("text/xml");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write("<message>invalid</message>");
			}
		}
		return null;
	}

	public String chat() throws Exception {
		return "chat";
	}

	public String getHomeAdv() throws Exception {
		return "homeAdv";
	}

	public String getTrainingHome() throws Exception {
		return "trainingHome";
	}

	public String getRecHome() throws Exception {
		return "recHome";
	}

	public String getAdminHome() throws Exception {
		return "adminHome";
	}

	/** ****************MAIL ACTIONS ******************** */
	/**
	 * Chking mails
	 * @return String
	 * @throws Exception
	 */
	public String mailChk() throws Exception {
		try {
			DefaultMailboxConnection defConn = new DefaultMailboxConnection();
			defConn.initiate(context, session);
			String result = defConn.login(home);
			if (result.equals("S")) {
				defConn.getMessages(home, request);
				defConn.logout();
			} // END if "S"
			else if (result.equals("F")) {
				addActionMessage("Please configure Server Settings");
			} // END else if "F"
			else
				addActionMessage("Please configure Email Settings");
			defConn.terminate();
			logger.info("--------------------------in mailchk----2");
		} catch (RuntimeException e) {
			logger.error("Exception Caught in Mail Check : " + e);
		}
		return "mail";
	}

	/**
	 * Navigating to next messages
	 * @return String
	 * @throws Exception
	 */
	public String nextMsgs() throws Exception {
		String start = request.getParameter("startInd");
		String end = request.getParameter("endInd");
		String startInd = String.valueOf(Integer.parseInt(start) + 15);
		String endInd = String.valueOf(Integer.parseInt(end) + 15);		
		DefaultMailboxConnection defConn = new DefaultMailboxConnection();
		defConn.initiate(context, session);
		String result = defConn.login(home);
		if (result.equals("S")) {
			defConn.getMessagesInd(home, request, startInd, endInd);
			defConn.logout();
		} // END if "S"
		else if (result.equals("F")) {
			addActionMessage("Please configure Server Settings");
		}// END else if "F"
		else
			addActionMessage("Please configure Email Settings");
		defConn.terminate();
		return "mail";
	}

	/**
	 * navigating to previous messages
	 * @return
	 * @throws Exception
	 */
	public String previousMsgs() throws Exception {
		String start = request.getParameter("startInd");
		String end = request.getParameter("endInd");
		String startInd = String.valueOf(Integer.parseInt(start) - 15);
		String endInd = String.valueOf(Integer.parseInt(end) - 15);		
		DefaultMailboxConnection defConn = new DefaultMailboxConnection();
		defConn.initiate(context, session);
		String result = defConn.login(home);
		if (result.equals("S")) {
			defConn.getMessagesInd(home, request, startInd, endInd);
			defConn.logout();
		}// END if "S"
		else if (result.equals("F")) {
			addActionMessage("Please configure Server Settings");
		}// END else if "F"
		else
			addActionMessage("Please configure Email Settings");
		defConn.terminate();
		return "mail";
	}

	/**
	 * @return String
	 * @throws Exception
	 */
	public String pageMsgs() throws Exception {

		String start = request.getParameter("PageStartInd");
		String end = request.getParameter("PageEndInd");		
		DefaultMailboxConnection defConn = new DefaultMailboxConnection();
		defConn.initiate(context, session);
		String result = defConn.login(home);
		if (result.equals("S")) {
			defConn.getMessagesInd(home, request, start, end);
			defConn.logout();
		} else if (result.equals("F")) {
			addActionMessage("Please configure Server Settings");
		} else
			addActionMessage("Please configure Email Settings");
		defConn.terminate();
		return "mail";
	}

	/**
	 * @return String
	 */
	public String mailChkHome() {
		try {
			DefaultMailboxConnection defConn = new DefaultMailboxConnection();
			defConn.initiate(context, session);
			String result = defConn.login(home);			
			if (result.equals("S")) {
				defConn.getMessagesHome(home, request);
				defConn.logout();
			} // END if "S"
			else if (result.equals("F")) {
				addActionMessage("Please configure Server Settings");
			} // END else if "F"
			else
				addActionMessage("Please configure Email Settings");
			defConn.terminate();			
		} catch (Exception e) {
			logger.error("Exception Caught in Mail Check  Home : " + e);
		}
		return "mailChkHome";
	}

	/**
	 * Mail Description / message
	 * @return String
	 */
	public String description() {		
		try {
			DefaultMailboxConnection defConn = new DefaultMailboxConnection();

			defConn.initiate(context, session);
			String msgNo = request.getParameter("msgNo");
			defConn.login(home);
			String result = defConn.getDesc(home, msgNo, request);
			defConn.terminate();
			if (result.equals("S")) {
				return "mailDesc";
			} // END if "S"
			else {
				addActionMessage("Session Expired, Please Relogin");
				defConn.logout();

			}// END else
		} catch (Exception e) {
			logger.error("Exception Caught in Mail Description : " + e);
		}
		return "mail";
	}

	/**
	 * Delete mail
	 * @return String
	 * @throws Exception
	 */
	public String mailDelete() throws Exception {		
		DefaultMailboxConnection defConn = new DefaultMailboxConnection();
		defConn.initiate(context, session);
		String sss = (String) request.getAttribute("data");		
		String[] Ids = (String[]) request.getParameterValues("pmChkFlag");
		String[] msgFlag = (String[]) request.getParameterValues("msgCode1");		
		int count = 0;
		for (int i = 0; i < Ids.length; i++) {// loop x

			if (Ids[i].equals("Y")) {
				count++;
			}// END if
		}// END loop x		
		int[] msgNos = new int[count];
		count = 0;
		for (int i = 0; i < Ids.length; i++) {// loop y

			if (Ids[i].equals("Y")) {
				msgNos[count] = Integer.parseInt(String.valueOf(msgFlag[i]));
				count++;
			}// END if
		}// END loop y

		for (int i = 0; i < msgNos.length; i++) {// loop a
			logger
					.info("--------------------------- mail numbers="
							+ msgNos[i]);
		}// END loop a

		defConn.login(home);
		// defConn.getDesc(home,msgNo,request);
		defConn.setMultipleDeletedFlags(msgNos);
		defConn.logout();
		defConn.login(home);
		defConn.getMessages(home, request);
		defConn.logout();
		defConn.terminate();
		return "mail";
	}

	/**
	 * Delete mail from homepage
	 * @return String
	 * @throws Exception
	 */
	public String mailDeleteHome() throws Exception {
		DefaultMailboxConnection defConn = new DefaultMailboxConnection();
		defConn.initiate(context, session);
		String sss = (String) request.getAttribute("data");
		String[] Ids = (String[]) request.getParameterValues("pmChkFlag");
		String[] msgFlag = (String[]) request.getParameterValues("msgCode1");
		for (int i = 0; i < msgFlag.length; i++) {// loop x
			logger.info("------------msgId=" + msgFlag[i]);
		}// END loop x
		int count = 0;
		for (int i = 0; i < Ids.length; i++) {// loop y

			if (Ids[i].equals("Y")) {
				count++;
			}// END if
		}// END loop y
		int[] msgNos = new int[count];
		count = 0;
		for (int i = 0; i < Ids.length; i++) {// loop z

			if (Ids[i].equals("Y")) {
				msgNos[count] = Integer.parseInt(String.valueOf(msgFlag[i]));
				count++;
			}// END if
		}// END loop z

		for (int i = 0; i < msgNos.length; i++) {// loop i
			logger
					.info("--------------------------- mail numbers="
							+ msgNos[i]);
		}// END loop i
		defConn.login(home);
		// defConn.getDesc(home,msgNo,request);
		defConn.setMultipleDeletedFlags(msgNos);
		defConn.logout();
		defConn.login(home);
		defConn.getMessagesHome(home, request);
		defConn.logout();
		defConn.terminate();
		return "input";
	}

	/**
	 * Composing Mails
	 * @return String
	 * @throws Exception
	 */
	public String mailCompose() throws Exception {		
		home.setMsgFrom(String.valueOf(home.getEmailUserId()));
		return "compose";
	}

	/**
	 * Sending Mails
	 * @return String
	 * @throws Exception
	 */
	public String sendMail() throws Exception {
		HomePageModel model = new HomePageModel();
		model.initiate(context, session);
		model.sendMail(home, context);
		model.terminate();
		return "compose";
	}

	/**
	 * reply to message
	 * @return String
	 * @throws Exception
	 */
	public String msgReply() throws Exception {
		String msgFrom = request.getParameter("msgFromRep");
		home.setMsgTo(msgFrom);

		home.setMsgFrom(String.valueOf(home.getEmailUserId()));
		return "compose";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/**Method Name : decisionHome()
	 * @purpose Used to provide MyApps Link on Home Page
	 * @return String 
	 * @throws Exception
	 */
	public String decisionHome() throws Exception {
		try {
			DefaultDashletConfigModel model = new DefaultDashletConfigModel();
			model.initiate(context, session);
			String [][] portalD1Obj = model.getD1PortalLink(request,home);
			model.getDashletConfig("35", home.getUserProfileId(), home
					.getUserLoginCode(), request);			
			model.terminate();
			request.setAttribute("portalD1Obj", portalD1Obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "decisionDash";
	}
	

	public String getDecisionOneMenu() throws Exception {
		HomePageModel d1HomeModel=new HomePageModel();
		String menuType=request.getParameter("menuType");
		d1HomeModel.initiate(context, session);
		d1HomeModel.getMenuList(request, home, menuType);
		d1HomeModel.terminate();
		return "decisionOneHomePage";
	}
}
