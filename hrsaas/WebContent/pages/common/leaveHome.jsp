<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
	<script type="text/javascript" src="../pages/common/js/dragable-boxes.js">
	
	</script>



<s:form action="LeaveHome" id="paraFrm" theme="simple">

	<div id="floatingBoxParentContainer">
	</div>
</s:form>

<script type="text/javascript">
	var numberOfDash = 2;
	var numberOfColumns = 2;	// Number of columns for dragable boxes
window.onload = initDragableBoxes;
function initDragableBoxes()
{
		//initDragableBoxesScript();
		//if(useCookiesToRememberRSSSources)createRSSBoxesFromCookie();	// Create RSS boxes from cookies
		dashletURLs= new Array('<%=request.getContextPath()%>/common/LeaveHome_getLeaveDetails.action','<%=request.getContextPath()%>/common/LeaveHome_getLeaveinfoDetails.action');
		
		dashletWidths= new Array("50","50");
		dashletHeights= new Array("220","220");
		columnNos= new Array("1","2");
		dashHeaders= new Array("Pending Leave Application","Employee on Leave");
		dashletFloats= new Array("left","right");
		
		dashletColspan= new Array();
		dashletRowspan= new Array();
		createColumns(dashletURLs,dashletWidths,dashletHeights,dashletFloats);	// Always the first line of this function
		createHelpObjects(dashletURLs);	// Always the second line of this function
		initEvents();	// Always the third line of this function
		createDefaultBoxes(dashletURLs,columnNos,dashHeaders,numberOfColumns,dashletHeights);	// Create default boxes.


		
	}
	
	
</script>