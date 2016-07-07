<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri='/WEB-INF/cewolf-1.1.tld' prefix='cewolf'%>

<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
	<script type="text/javascript" src="../pages/common/js/dragable-boxes.js">
	
	</script>

<s:form action="RecruitmentHome" id="paraFrm" theme="simple">

<div id="floatingBoxParentContainer">
	</div>
</s:form>

<script type="text/javascript">
	var numberOfDash = 6;
	var numberOfColumns = 2;	// Number of columns for dragable boxes
window.onload = initDragableBoxes;
function initDragableBoxes()
{
		//initDragableBoxesScript();
		//if(useCookiesToRememberRSSSources)createRSSBoxesFromCookie();	// Create RSS boxes from cookies
		dashletURLs= new Array('<%=request.getContextPath()%>/common/RecruitmentHome_openReq.action',
		'<%=request.getContextPath()%>/common/RecruitmentHome_workFlow.action',
		'<%=request.getContextPath()%>/common/RecruitmentHome_manpowerSt.action',
		'<%=request.getContextPath()%>/common/RecruitmentHome_attritionRate.action',
		'<%=request.getContextPath()%>/common/HrmHome_getmanPower.action',
		'<%=request.getContextPath()%>/common/RecruitmentHome_getInterviewerSchDet.action'
		);
		
		dashletWidths= new Array("60","39");
		dashletHeights= new Array("200","200","200","200","200","200");
		columnNos= new Array("1","2","1","2","1","2");
		dashHeaders= new Array("Open Requisition",
		"Interview Schedule","Work Flow Status","Attrition Rates"
		,"Manpower Status","Interviewer's Schedule");
		dashletFloats= new Array("left","right","left","right","left","right");
		var numberOfColumns = 2;
		dashletColspan= new Array();
		dashletRowspan= new Array();
		createColumns(dashletURLs,dashletWidths,dashletHeights,dashletFloats);	// Always the first line of this function
		createHelpObjects(dashletURLs);	// Always the second line of this function
		initEvents();	// Always the third line of this function
		createDefaultBoxes(dashletURLs,columnNos,dashHeaders,numberOfColumns,dashletHeights);	// Create default boxes.


		
	}
	/* not using any java script functions with in this jsp page...!
	if u want to give java script with in commonDashboard.jsp page.
	*/
	
	
	function viewRequisition(reqCode){
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/recruit/EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction=RecruitmentHome_openReq.action';
	    document.getElementById("paraFrm").submit();
	}
	
	function searchCandidate(reqCode,reqName,positionId,position){
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/recruit/CandidateSearch_searchFrmDashlet.action?requisitionCode='+reqCode+'&requisitionName='+reqName+'&positionCode='+positionId+'&position='+position;
	    document.getElementById("paraFrm").submit();
	}
	
	
	function showRequisition(reqCode,status){
	//alert("user Requision code...!"+reqCode);
	
		//document.getElementById("paraFrm").action='EmployeeRequi_viewReqDetails.action?reqCode='+reqCode+'&formAction='+" "+'&statusKey='+" ";
	 //   document.getElementById("paraFrm").submit();
	// return false;
	}
	
	
	
	
</script>
