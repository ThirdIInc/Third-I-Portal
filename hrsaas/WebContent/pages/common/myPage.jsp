<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>


<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
	<script type="text/javascript" src="../pages/common/js/dragable-boxes.js">
	
	</script>



<s:form action="MyPage" id="paraFrm" theme="simple">

	<div id="floatingBoxParentContainer">
	</div>
</s:form>

<script type="text/javascript">
	var numberOfDash = 2;
	var numberOfColumns = 1;	// Number of columns for dragable boxes
window.onload = initDragableBoxes;
function initDragableBoxes()
{
		//initDragableBoxesScript();
		//if(useCookiesToRememberRSSSources)createRSSBoxesFromCookie();	// Create RSS boxes from cookies
		dashletURLs= new Array('<%=request.getContextPath()%>/srd/AlertMessage_input.action',
		'<%=request.getContextPath()%>/common/MyPage_setmyPerformance.action'
		);
		
		dashletWidths= new Array("100","100");
		dashletHeights= new Array("220","200");
		columnNos= new Array("1","1");
		dashHeaders= new Array("Process Manager Alert",
		"My Performance");
		dashletFloats= new Array("left","left");
		
		dashletColspan= new Array();
		dashletRowspan= new Array();
		createColumns(dashletURLs,dashletWidths,dashletHeights,dashletFloats);	// Always the first line of this function
		createHelpObjects(dashletURLs);	// Always the second line of this function
		initEvents();	// Always the third line of this function
		createDefaultBoxes(dashletURLs,columnNos,dashHeaders,numberOfColumns,dashletHeights);	// Create default boxes.


		
	}
	
	
</script>