
<%@ taglib prefix="s" uri="/struts-tags"%>
 
<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
<script type="text/javascript" src="../pages/common/js/dragable-boxes.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery.min.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery.loader.js"></script>

 <s:form action="HomePage" id="paraFrm" name="HomeForm" theme="simple"></s:form>

<input type="hidden" name="divMovementHidden" id="divMovementHidden" />



<%!	String dashUrls = "";
	String dashHeights = "";
	String dashHeaders = "";
	String dashColumnNos = "";
	String dashFloats = "";
	int  totRows =0 ;
	int totCols =1 ;
	String widths ="50,50" ;
	int totdashs =0 ;
%>


 

<%
	
	String[][] configObj = null;
	try{
		configObj=(String[][]) request.getAttribute("configObj");
		
		totdashs =configObj.length ;
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}


 	
	 
	%>


<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center">


<tr>
			<td valign="bottom" class="txt" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="pageHeader">
				<tr>
					<td style="padding: 3px;"><strong class="text_head"><img
						src="../pages/mypage/images/icons/dashboard24.png"  
						  /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">My
					Dashboard </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	<%
				int counter = 0;
				String str = "defaultText";
			%>
	<%
			for (int p = 0; p < (totdashs / totCols + 1); p++) {
			 
			%>
	<tr>
		<%
				for (int q = 0; q < totCols; q++) {
				%>
		<%
				if (counter < totdashs) {
				%>
		<td width="<%=widths.split(",")[q]%>%" valign="top">

		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0" class="dashborder">
			<tr>
				<td height="25" bgcolor="#f2f2f2" class="bottomborder">
				<table width="98%" border="0" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="17px">
						<!--  configObj[counter][7]  -->
						<img src="../pages/mypage/images/icons/generalInfo.png"
							width="16" height="16" /></td>
						<td width="100%" class="dasheader">&nbsp;&nbsp;<%=configObj[counter][1] %></td>
						<td width="22%">
						<div align="right">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="67%">
								<div align="right"></div>
								</td>
								<td width="33%">
								
								</td>
							</tr>
						</table>
						<a href="javascript:void(0);" class="link"></a></div>
						</td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td height="130px"
					background="../pages/common/css/default/images/grad.jpg"
					valign="top">
				<div id="<%=str+(counter)%>" style="width: 100%; height: 130px;overflow: auto; "></div>

				</td>
			</tr>
			
		</table>
		</td>
	
		<%
		counter++;
		 
							}
				%>
		<%
				}
				%><!-- End of for for <td> -->
	
	</tr>
		
		<tr>
		<td>
	<div style="float: left; width: 100%;">&nbsp;</div>
	</td></tr>				
	
	<%
			}
			%><!-- End of for loop for <tr> -->

</table>

 
 
<script type="text/javascript">
(function($){
$(document).ready(function(){
	
<%
	for(int noofDashlet =0;noofDashlet<totdashs;noofDashlet++)
	{

%>
$("#defaultText<%=noofDashlet%>").addLoader();
$("#defaultText<%=noofDashlet%>").useLoader("<%=request.getContextPath()%><%=configObj[noofDashlet][2]%>?"+Math.random());
		



	<%
	}
%>
	
	
	
	
});
})(jQuery);

 
 
 function show(id)
		{
		
		var wind = window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
		}
		
		
	function callEditPage(){
			  try{
			 //	document.getElementById('leftFrame').style.width='0px';
	//document.getElementById('myframe').style.width='100%';
	document.getElementById('myframe').src="<%=request.getContextPath()%>/common/ConfigurationHome_input.action";
			  }
			  catch(e)
			  {
			  
			  }
	 
	}	
	
	
	
	
	
	function viewRequisition(reqCode) {
		document.getElementById("paraFrm").action = '<%=request.getContextPath()%>/recruit/EmployeeRequi_viewReqDetails.action?reqCode=' +
		reqCode + '&formAction=RecruitmentHome_openReq.action';
	    document.getElementById("paraFrm").submit();
	}
	
	function searchCandidate(reqCode, reqName, positionId, position) {
	 var winRec =window.open('','winRec','width=700,height=300,scrollbars=yes,resizable=yes,top=50,left=100');
	 	document.getElementById("paraFrm").target='winRec';
		document.getElementById("paraFrm").action = '<%=request.getContextPath()%>/recruit/CandidateSearch_searchFrmDashlet.action?dashletFlag=true+&requisitionCode=' + 
		reqCode + '&requisitionName=' + reqName + '&positionCode=' + positionId + '&position=' + position;
	    document.getElementById("paraFrm").submit();
	    document.getElementById("paraFrm").target='main';
	}
	
	
	function referCandidate(reqCode) {
		document.getElementById("paraFrm").action = '<%=request.getContextPath()%>/recruit/PostResume_refercandidate.action?requisitionCode=' + reqCode;
    	document.getElementById("paraFrm").submit();
	}
	
		function showRequisition(reqCode) {
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/recruit/EmployeeRequi_dashLetviewReqDetails.action?reqCode=' +
		reqCode + '&formAction=' + " " + '&statusKey=' + " ";
		document.getElementById('paraFrm').submit();
	}
	
	
	
		function getBellCurve() {	 	
		if(document.getElementById('paraFrm_sAppId').value == "") {
			alert('Please select appraisal code.');
	 	 	return false;
		}
	 	
	 	var url = '<%=request.getContextPath()%>/common/AppraisalBellCurve_drawBellCurveDashlet.action?sAppId=' + 
	 	document.getElementById('paraFrm_sAppId').value + '&sAppCode=' + document.getElementById('paraFrm_sAppCode').value;	  
	  	getReloadURL('1', url);
	}
 
 
 	function viewSubject(id){
		var path='<%=request.getContextPath()%>';
		window.open(path+'/common/HrmHome_showOnSubject.action?suggId='+id,'','width=700,height=300,scrollbars=yes,resizable=yes,top=50,left=100');

}



function detectFlashPlayerPlugin(graphDivId,pluginDivId){
	try{
	var hasReqestedVersion = DetectFlashVer(8, 0, 0);
	if (hasReqestedVersion) {
	//alert("Flash is insalled on your Web browser.");
		document.getElementById(graphDivId).style.display='';
		document.getElementById(pluginDivId).style.display='none';
	}else{
		document.getElementById(graphDivId).style.display='none';
		document.getElementById(pluginDivId).style.display='';
	}
	
	}catch(e){}
	}
	
	function getReloadURL(numericId, value) {
		try{
		dragableBoxesArray[numericId]['dashletURL'] = value;
		reloadRSSData(numericId);
		}
		catch(e)
		{
			alert("Error getReloadURL----"+e);
		}
	}
	
		function viewApplication(action) {
		document.getElementById('paraFrm').target = 'main';
		document.getElementById("paraFrm").action = action;
    	document.getElementById("paraFrm").submit();
    	document.getElementById('paraFrm').target = 'main';
	}
 
</script>
