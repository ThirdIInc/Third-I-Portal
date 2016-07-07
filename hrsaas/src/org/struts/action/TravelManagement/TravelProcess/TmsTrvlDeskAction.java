package org.struts.action.TravelManagement.TravelProcess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
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
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;
import org.paradyne.bean.TravelManagement.TravelProcess.TmsTrvlDesk;
import org.paradyne.model.TravelManagement.TravelProcess.TmsTrvlDeskModel;
import org.struts.lib.ParaActionSupport;

import de.laures.cewolf.ChartPostProcessor;
import de.laures.cewolf.DatasetProducer;

/**
 * @author AA0651
 * 
 */
public class TmsTrvlDeskAction extends ParaActionSupport {
	static org.apache.log4j.Logger logger = org.apache.log4j.Logger
			.getLogger(TmsTrvlDeskAction.class);
	TmsTrvlDesk desk;

	public void prepare_local() throws Exception {
		desk = new TmsTrvlDesk();
		desk.setMenuCode(919);

	}

	public Object getModel() {
		return desk;
	}

	public TmsTrvlDesk getDesk() {
		return desk;
	}

	public void setDesk(TmsTrvlDesk desk) {
		this.desk = desk;
	}

	// to call on load to get the list
	public void prepare_withLoginProfileDetails() throws Exception {

		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		model.checkAuth(desk);
		if (desk.getOnLoadFlg().equals("true")) {
			model.callStatus(desk, "P", request, "");
			desk.setStatus("P");
			desk.setDeskStatus("P");
			desk.setPen("true");
			desk.setPenFlg("true");
		} else if (desk.getOnLoadSubSchFlg().equals("true")) {
			logger.info("sub sch");
			model.callStatusSubSchlr(desk, "A", request, "");
			desk.setStatus("A");
			desk.setDeskStatus("A");
			desk.setAssigned("true");
			desk.setAssignedFlg("true");
		} else {
			desk.setNoData(true);
		}
		model.terminate();
	}

	/*
	 * To get records based on status such as Pending Travel list,assigned list
	 * etc.
	 */
	public String callStatus() {
		desk.setTravelList(null);
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		model.checkAuth(desk);
		String status = "" + request.getParameter("status");

		// CONDITION SPECIFIC TO CANCELLATION OF BOKKING DETAILS FROM SCHEDULED
		// TRAVEL LIST
		// AS IN THAT CASE REQUEST WILL BE PARTIALLY PROCESSED IN
		// TravelApplicationAction_updateAppStatus, and
		// would be forwarded to TravelDesk_callStatus for further processing.
		// Thus leading to
		// status parameter as null.
		if (status == null || status.equals("null")) {
			status = "S";
		}

		desk.setDeskStatus(status);
		desk.setStatus(status);
		if (desk.getOnLoadFlg().equals("true")) {
			model.callStatus(desk, status, request, "");
		} else if (desk.getOnLoadSubSchFlg().equals("true")) {
			model.callStatusSubSchlr(desk, status, request, "");
		} else {
			desk.setNoData(true);
		}

		if (status.equals("P")) {
			desk.setPen("true");
			desk.setAssigned("false");
			desk.setBooked("false");
			desk.setRejected("false");
			desk.setSubmited("false");

		} else if (status.equals("A")) {
			desk.setPen("false");
			desk.setAssigned("true");
			desk.setBooked("false");
			desk.setRejected("false");
			desk.setSubmited("false");

		} else if (status.equals("S")) {
			desk.setPen("false");
			desk.setAssigned("false");
			desk.setBooked("true");
			desk.setRejected("false");
			desk.setSubmited("false");
		} else if (status.equals("R")) {
			desk.setPen("false");
			desk.setAssigned("false");
			desk.setBooked("false");
			desk.setRejected("true");
			desk.setSubmited("false");
		} else if (status.equals("M")) {
			desk.setPen("false");
			desk.setAssigned("false");
			desk.setBooked("false");
			desk.setRejected("false");
			desk.setSubmited("true");
		} else if (status.equals("PC")) {
			desk.setPen("false");
			desk.setAssigned("false");
			desk.setBooked("false");
			desk.setRejected("false");
			desk.setSubmited("false");
			desk.setPendingCancel("true");
		} else if (status.equals("CC")) {
			desk.setPen("false");
			desk.setAssigned("false");
			desk.setBooked("false");
			desk.setRejected("false");
			desk.setSubmited("false");
			desk.setCanceled("true");
		}

		model.terminate();

		// /When application is cancelled from scheduled travel list
		// the control is redirected from travel applciation after status
		// updation
		// to travel desk, setting the cancellation-message in the request

		String cancelMsg = (String) request.getAttribute("cancelData");
		if (cancelMsg != null && cancelMsg.length() > 0) {
			if (cancelMsg.equals("true")) {
				addActionMessage("Record cancelled successfully.");
			} else {
				addActionMessage("Unable to cancel record.");
			}
		}

		return "success";
	}

	public String applyFilter() throws Exception {
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		HashMap<Object, Object> myMap = new HashMap<Object, Object>();
		myMap.put("filterEmpName", getMessage("emp.name"));
		myMap.put("filterInitName", getMessage("init.name"));
		myMap.put("filterApplDate", getMessage("trvl.applDate"));
		myMap.put("filterStartDate", getMessage("date.journey"));
		myMap.put("filterTrvlId", getMessage("travel.id"));
		myMap.put("filterTrvlReq", getMessage("trvl.reqName"));
		myMap.put("filterTrvlAsignedTo", getMessage("trvl.assignedTo"));
		myMap.put("filterAccomAsignedTo", getMessage("accom.assignedTo"));
		myMap.put("filterConvAsignedTo", getMessage("conv.assignedTo"));
		request.setAttribute("mine", myMap);
		desk.setApplyFilterFlag(true);
		callStatus();
		desk.setSearchFlag("true");
		desk.setChkSerch("true");

		logger.info(">>>>>>>>desk  Status>>>>>>>>>" + desk.getDeskStatus());
		model.terminate();
		return SUCCESS;
	}

	public String clearFilter() throws Exception {
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		desk.setFilterEmpName("");
		desk.setFilterEmpId("");
		desk.setFilterEmpToken("");
		desk.setFilterInitId("");
		desk.setFilterInitName("");
		desk.setFilterInitToken("");
		desk.setFilterApplDate("");
		desk.setFilterStartDate("");
		desk.setFilterTrvlId("");
		desk.setFilterTrvlReq("");
		desk.setFilterTrvlAsignedTo("");
		desk.setFilterTrvlAsignedToId("");
		desk.setFilterAccomAsignedTo("");
		desk.setFilterAccomAsignedToId("");
		desk.setFilterConvAsignedTo("");
		desk.setFilterConvAsignedToId("");
		desk.setChkSerch("");
		desk.setApplyFilterFlag(false);
		callStatus();
		model.terminate();
		return SUCCESS;
	}

	/*
	 * To get main scheduler and subSchedulers by clicking Assign button.
	 */
	public String callSchdlr() throws Exception {
		String[] deskId = request.getParameterValues("deskId");
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);

		if (!desk.getUserEmpId().equals("")
				&& !desk.getUserEmpId().equals("null")) {
			model.callStatus(desk, "P", request, "A");
			model.getSchdlr(desk);
			desk.setSchViewFlg("true");
		}
		model.terminate();
		return SUCCESS;
	}

	/*
	 * To save the records
	 */
	public String save() throws Exception {
		String[] deskId = request.getParameterValues("deskId");
		String[] chkTypeFlag = request.getParameterValues("chkTypeFlag");
		String[] deskStatus = request.getParameterValues("deskStatus");

		String[] hidTrvlEmpId = request.getParameterValues("hidTrvlEmpId");
		String[] schEmpId = request.getParameterValues("schEmpId");
		String[] schEmpName = request.getParameterValues("schEmpName");
		String[] hidLodgeRadio = request.getParameterValues("hidLodgeRadio");
		String[] hidLoclConvRadio = request
				.getParameterValues("hidLoclConvRadio");
		String[] hidTrvlRadio = request.getParameterValues("hidTrvlRadio");
		String[] trvlRadio = request.getParameterValues("trvlRadio");
		String[] loclConvRadio = request.getParameterValues("loclConvRadio");
		String[] lodgeRadio = request.getParameterValues("lodgeRadio");
		String[] travelIndAppId = request.getParameterValues("travelIndAppId");
		String[] travelAppId = request.getParameterValues("travelAppId");

		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		model.checkAuth(desk);
		boolean result = false;
		if (desk.getHiddenEdit().equals("")) {
			result = model.saveSch(desk, request, deskId, schEmpId, schEmpName,
					hidLodgeRadio, hidLoclConvRadio, hidTrvlRadio, trvlRadio,
					loclConvRadio, lodgeRadio, hidTrvlEmpId, travelIndAppId,
					travelAppId, chkTypeFlag, deskStatus);

			if (result) {
				addActionMessage(getMessage("save"));

				desk.setMyHidden("1");
			} else {
				addActionMessage(getMessage("duplicate"));
			}
			desk.setHiddenEdit("");
			desk.setTravelList(null);
			model.callStatus(desk, "P", request, "");
			desk.setMyHidden("0");

		} else {
			result = model.updateSch(desk, request, deskId, schEmpId,
					schEmpName, hidLodgeRadio, hidLoclConvRadio, hidTrvlRadio,
					hidTrvlEmpId, travelAppId, desk.getStatus());
			if (result) {
				addActionMessage(getMessage("update"));

			} else {
				addActionMessage(getMessage("duplicate"));
			}
			desk.setHiddenEdit("");
			if (desk.getStatus().equals("CC")) {
				model.callStatus(desk, "CC", request, "");
				desk.setCanceled("true");
			} else {
				model.callStatus(desk, "A", request, "");
				desk.setAssigned("true");
			}

		}
		model.terminate();
		desk.setSchViewFlg("false");
		return SUCCESS;
	}

	/*
	 * To edit assigned list
	 */
	public String edit() throws Exception {
		desk.setSchViewFlg("true");
		logger.info("--desk.getEditFlg()----" + desk.getEditFlg());
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		model.checkAuth(desk);
		logger.info("...............status......" + desk.getStatus());
		logger.info(".............desk..status......" + desk.getDeskStatus());
		if (desk.getStatus().equals("CC")) {
			model.callStatus(desk, "CC", request, "");
			desk.setCanceled("true");
			desk.setCancelFlag("true");
		} else {
			model.callStatus(desk, "A", request, "");
			desk.setAssigned("true");
			desk.setAssignedFlg("true");
		}

		model.callEdit(desk);
		model.terminate();

		return SUCCESS;
	}

	/*
	 * start booking
	 */
	public String startBooking() throws Exception {
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		model.startBooking(desk, request);
		model.terminate();
		return "book";
	}

	/*
	 * give option
	 */
	public String giveOption() throws Exception {
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		boolean result = model.giveOption(desk, request);
		if (result) {
			addActionMessage("Record is moved to Pending Travel List Successfully.");
		} else {
			addActionMessage("Record can not be moved.");
		}
		model.callStatus(desk, "M", request, "");
		desk.setPen("false");
		desk.setAssigned("false");
		desk.setBooked("false");
		desk.setRejected("false");
		desk.setSubmited("true");
		model.terminate();
		return SUCCESS;
	}

	/**
	 * for fast booking
	 * 
	 * @return
	 * @throws Exception
	 */

	public String book() throws Exception {
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		String[] JournDtlCode = request.getParameterValues("JournDtlCode");
		String[] JourFrm = request.getParameterValues("jourFrm");
		String[] jourTo = request.getParameterValues("jourTo");
		String[] jourDate = request.getParameterValues("jourDate");
		String[] jourTime = request.getParameterValues("jourTime");
		String[] JournDtlId = request.getParameterValues("JournDtlId");
		String[] jourNo = request.getParameterValues("jourNo");
		String[] ticketNo = request.getParameterValues("ticketNo");
		String[] jourCost = request.getParameterValues("jourCost");
		String[] jourDetails = request.getParameterValues("jourDetails");
		String[] uploadFileName = request.getParameterValues("uploadFileName");
		

		String[] tmsChkTypeFlg = request.getParameterValues("tmsChkTypeFlg");

		String[] lodgCode = request.getParameterValues("lodgCode");
		String[] lodgCity = request.getParameterValues("lodgCity");
		String[] lodgFrmDate = request.getParameterValues("lodgFrmDate");
		String[] lodgToDate = request.getParameterValues("lodgToDate");
		String[] lodgFrmTime = request.getParameterValues("lodgFrmTime");
		String[] lodgToTime = request.getParameterValues("lodgToTime");
		String[] lodgHotel = request.getParameterValues("lodgHotel");
		String[] lodgRoom = request.getParameterValues("lodgRoom");
		String[] lodgPreLoc = request.getParameterValues("lodgPreLoc");
		String[] lodgBookAmt = request.getParameterValues("lodgBookAmt");
		String[] accDetails = request.getParameterValues("accDetails");
		String[] hotelTypeId = request.getParameterValues("hotelTypeId");
		String[] lodgId = request.getParameterValues("lodgId");
		String[] uploadLodgeFileName = request.getParameterValues("uploadLodgeFileName");
		

		String[] locConCode = request.getParameterValues("locConCode");
		// String[] locConSource = request.getParameterValues("locConSource");
		String[] locConCity = request.getParameterValues("locConCity");
		String[] locConFrmDate = request.getParameterValues("locConFrmDate");
		String[] medium = request.getParameterValues("medium");
		String[] locConCost = request.getParameterValues("locConCost");
		String[] locConFrmTime = request.getParameterValues("locConFrmTime");
		String[] locConToTime = request.getParameterValues("locConToTime");
		String[] locDetails = request.getParameterValues("locDetails");
		String[] uploadLocFileName = request.getParameterValues("uploadLocFileName");

		model.initiate(context, session);
		boolean result = false;
		result = model.book(desk, request, JournDtlCode, JourFrm, jourTo,
				jourDate, jourTime, JournDtlId, jourNo, ticketNo, jourCost,
				jourDetails, lodgCode, lodgCity, lodgFrmDate, lodgToDate,
				lodgFrmTime, lodgHotel, lodgRoom, lodgPreLoc, lodgBookAmt,
				accDetails, lodgToTime, lodgId, hotelTypeId, locConCode,
				locConCity, locConFrmDate, medium, locConCost, locConFrmTime,
				locConToTime, locDetails, tmsChkTypeFlg,uploadFileName,uploadLodgeFileName,uploadLocFileName);
		if (result) {
			addActionMessage("Record is booked successfully.");
		} else {
			addActionMessage("Record can not be booked");
		}
		model.callStatus(desk, "M", request, "");
		desk.setPen("false");
		desk.setAssigned("false");
		desk.setBooked("false");
		desk.setRejected("false");
		desk.setSubmited("true");
		model.terminate();

		return SUCCESS;
	}
	/*
	 * To view assigned list by clicking Assigned list button and to view graph
	 * for assigned list.
	 */
	public String callAsgnList() throws Exception {
		System.out.println("Lakkichand.............Entry");
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		final Object[][] data = model.getAssignedList(desk);

		System.out.println("Lakkichand.............Entry2");
		try {
			DatasetProducer categoryData = new DatasetProducer() {
				public Object produceDataset(Map params) {

					DefaultCategoryDataset defaultcategorydataset = new DefaultCategoryDataset();

					for (int i = 0; i < data.length; i++) {
						if (Integer.parseInt(String.valueOf(data[i][3])) != 0
								|| Integer.parseInt(String.valueOf(data[i][4])) != 0
								|| Integer.parseInt(String.valueOf(data[i][5])) != 0) {
							defaultcategorydataset.addValue(Double
									.parseDouble(String.valueOf(data[i][3])),
									"Traval", String.valueOf(data[i][1]));
							defaultcategorydataset.addValue(Double
									.parseDouble(String.valueOf(data[i][4])),
									"Local Conveyance", String
											.valueOf(data[i][1]));
							defaultcategorydataset.addValue(Double
									.parseDouble(String.valueOf(data[i][5])),
									"Lodging", String.valueOf(data[i][1]));
						}
					}

					return defaultcategorydataset;

				}

				public String getProducerId() {
					System.out.println("Lakkichand.............2");
					return "CategoryDataProducer";
				}

				public boolean hasExpired(Map params, Date since) {
					System.out.println("Lakkichand.............3");
					return true;
				}
			};
			request.setAttribute("categoryData", categoryData);
			ChartPostProcessor meterPP = new ChartPostProcessor() {
				public void processChart(Object chart, Map params) {
					CategoryPlot categoryplot = (CategoryPlot) ((JFreeChart) chart)
							.getPlot();
					System.out.println("Lakkichand.............4");
					categoryplot.mapDatasetToRangeAxis(1, 1);
					CategoryAxis categoryaxis = categoryplot.getDomainAxis();
					categoryaxis
							.setCategoryLabelPositions(CategoryLabelPositions.DOWN_45);
					/*
					 * NumberAxis numberaxis = new NumberAxis("");
					 * numberaxis.setAxisLinePaint(Color.MAGENTA);
					 * categoryplot.setRangeAxis(1, numberaxis);
					 */
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
		} catch (Exception e) {
			logger.info(".......Exception");
			e.printStackTrace();
		}
		model.terminate();
		desk.setOnLoadFlg("false");
		return "view";
	}

	public String cancelApp() throws Exception{	
		logger.info("000000000000000000---krish--------");
		
		boolean result=false;
		String reqFrmDesk = "";
		try{
			reqFrmDesk = request.getParameter("reqFrmDesk");
		}catch(Exception e){
			
		}
		
		
		TmsTrvlDeskModel model = new TmsTrvlDeskModel();
		model.initiate(context, session);
		result=model.cancelApp(desk,request);
		if(result){
			addActionMessage("Record cancelled successfully.");
		}else{
			addActionMessage("Record can not be cancelled.");
		}
		model.callStatus(desk, "S", request,"");
		desk.setPen("false");
		desk.setAssigned("false");
		desk.setBooked("true");
		desk.setRejected("false");
		desk.setSubmited("false");
		model.terminate();
		
		//IF cancellation request came from travel application(applicant)
		if(reqFrmDesk!=null && reqFrmDesk.equals("false")){
			return "trvlAppSchldLst";
		}
		
		return SUCCESS;
	}

	public String f9Emp() throws Exception {

		String query = " SELECT  EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME ,EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'"
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";

		String[] headers = { "Employee/Guest Code", "Employee/Guest Name" };

		String[] headerWidth = { "10", "15" };

		String[] fieldNames = { "filterEmpToken", "filterEmpName",
				"filterEmpId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9Initiator() throws Exception {

		String query = " SELECT  EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME ,EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'"
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";

		String[] headers = { "Initiator Code", "Initiator Name" };

		String[] headerWidth = { "10", "15" };

		String[] fieldNames = { "filterInitToken", "filterInitName",
				"filterInitId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9TrvlAssigned() throws Exception {

		String query = " SELECT  EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME ,EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'"
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";

		String[] headers = { "Employee Code", "Employee Name" };

		String[] headerWidth = { "10", "15" };

		String[] fieldNames = { "filterTrvlAsignedToToken",
				"filterTrvlAsignedTo", "filterTrvlAsignedToId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9AccomAssigned() throws Exception {

		String query = " SELECT  EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME ,EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'"
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";

		String[] headers = { "Employee Code", "Employee Name" };

		String[] headerWidth = { "10", "15" };

		String[] fieldNames = { "filterAccomAsignedToToken",
				"filterAccomAsignedTo", "filterAccomAsignedToId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

	public String f9ConvAssigned() throws Exception {

		String query = " SELECT  EMP_TOKEN,EMP_FNAME || ' ' || EMP_MNAME || ' ' ||EMP_LNAME ,EMP_ID"
				+ " FROM HRMS_EMP_OFFC "
				+ " WHERE EMP_STATUS ='S'"
				+ " ORDER BY EMP_FNAME,EMP_MNAME,EMP_LNAME ";

		String[] headers = { "Employee Code", "Employee Name" };

		String[] headerWidth = { "10", "15" };

		String[] fieldNames = { "filterConvAsignedToToken",
				"filterConvAsignedTo", "filterConvAsignedToId" };

		int[] columnIndex = { 0, 1, 2 };

		String submitFlag = "false";

		String submitToMethod = "";

		setF9Window(query, headers, headerWidth, fieldNames, columnIndex,
				submitFlag, submitToMethod);

		return "f9page";

	}

}
