<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
	<script type="text/javascript" src="../pages/common/js/dragable-boxes.js">
	
	</script>

<s:form action="AdminHome" id="paraFrm" theme="simple">

<div id="floatingBoxParentContainer">
	</div>

</s:form>

<script type="text/javascript">
	var numberOfDash = 4;
	var numberOfColumns = 2;	// Number of columns for dragable boxes
window.onload = initDragableBoxes;
function initDragableBoxes()
{
		
		//if(useCookiesToRememberRSSSources)createRSSBoxesFromCookie();	// Create RSS boxes from cookies
		dashletURLs= new Array('<%=request.getContextPath()%>/common/AdminHome_conference.action',
		'<%=request.getContextPath()%>/common/AdminHome_combineGraph.action',
		'<%=request.getContextPath()%>/common/AdminHome_assets.action',
		'<%=request.getContextPath()%>/common/AdminHome_pendingVoucher.action'
		);
		
		dashletWidths= new Array("60","39");
		dashletHeights= new Array("200","200","200","200");
		columnNos= new Array("1","2","1","2");
		dashHeaders= new Array("Conference Booking",
		"Budgeting","Assets Volume","Pending Activities");
		dashletFloats= new Array("left","right","left","right");
		var numberOfColumns = 2;
		
		dashletColspan= new Array();
		dashletRowspan= new Array();
		createColumns(dashletURLs,dashletWidths,dashletHeights,dashletFloats);	// Always the first line of this function
		createHelpObjects(dashletURLs);	// Always the second line of this function
		initEvents();	// Always the third line of this function
		createDefaultBoxes(dashletURLs,columnNos,dashHeaders,numberOfColumns,dashletHeights);	// Create default boxes.


		
	}
	
	
</script>