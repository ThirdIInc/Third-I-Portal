<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<link rel="stylesheet" type="text/css" href="../pages/common/decisionOneDashboard/decisionOneDashboard.css"/>
<script type="text/javascript" src="../pages/common/Ajax.js"></script>
<script type="text/javascript" src="../pages/common/js/ajax.js"></script>
<script type="text/javascript"
	src="../pages/common/js/dragable-boxes.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery.min.js"></script>
<script type="text/javascript" src="../pages/common/js/jquery.loader.js"></script>
</head>


<body>
<!--<s:form action="HomePage" id="paraFrm" name="HomeForm" theme="simple"></s:form>-->

<!-- <input type="hidden" name="divMovementHidden" id="divMovementHidden" />
<s:hidden id="screenWidth" name="screenWidth"></s:hidden> -->
<form class="home" action="../PAS/ApprFormSectionAction.java" method="post" validate="true" id="paraFrm"	theme="simple">
	
	<div class="wrapper">
	<div class = "title">
		Performance Review 2016
	</div>
	<ul>
	<div>
		<img src="../pages/mypage/images/icons/appraisal.png" width="30" height="30">
			<a class="servicelink" href="javascript:void(0);" title="My Appraisal" onclick="callMyAction('/pas/ApprFormStart_input.action'); ">My Appraisal</a>
				<br>
				<span class="smalltext">It is time to review my performance</span>
	</div>

	<div>
		<img src="../pages/mypage/images/icons/teamAppraisal.png" width="30" height="30">
			<a class="servicelink" href="javascript:void(0);" title="Team Appraisal" onclick="callMyAction('/pas/EvaluatorPanel_input.action?src=myservices'); ">Team Appraisal</a>
			<br>
				<span class="smalltext">I can review my teams performance here.</span>
	</div>
	</ul>
	</div>
</form>
<%
			String[][] portalAppsObj = (String[][]) request
			.getAttribute("portalD1Obj");
%>
<%!String dashUrls = "";
	String dashHeights = "";
	String dashHeaders = "";
	String dashColumnNos = "";
	String dashFloats = "";
	int totRows = 0;
	int totCols = 0;
	String widths = "";
	int totdashs = 0;
	String[][] configObj = null;%>


<%
	Object[][] dashletSet = null;
	String dashletmenuCode = "";
	try {
		dashletSet = (Object[][]) request.getAttribute("dashletSet");

		dashletmenuCode = (String) request
		.getAttribute("dashletmenuCode");

		//out.println("dashletmenuCode  "+dashletmenuCode);

		if (dashletSet != null && dashletSet.length > 0) {

			if (dashletmenuCode != null && dashletmenuCode.equals("35")) {

		String[][] configObj1 = {
				{ "", "Announcement","/common/HomePage_getCorpComm.action","", "", "", "", "28.png" },
				{ "", "Corporate Event","/common/HomePage_corporateEvent.action","", "", "", "", "26.png" },
				{ "", "Opinion Poll","/common/HomePage_getPolls.action", "","", "", "", "34.png" },
				{ "", "CEO Connect","/common/HomePage_ceoConnect.action","", "", "", "", "46.png" },
				{ "", "Corporate Information","/common/HomePage_getCorpInfo.action","", "", "", "", "17.png" },
				{ "", "Reward & Recognition","/common/HomePage_rewardData.action","", "", "", "", "02.png" },
				{ "", "Quick Information","/common/HomePage_getGenInfo.action","", "", "", "", "42.png" },
				{ "", "Knowledge Information","/common/HomePage_getKnowInfo.action","", "", "", "", "knowledge.png" },
				{ "", "Quick Download","/common/HomePage_quickDownloadInfoDashlet.action","", "", "", "", "43.png" }
		};
		configObj = configObj1;
		totRows = 3;//Integer.parseInt(String.valueOf(dashletSet[0][1]));
		totCols = 3;
		widths = "33,33,33";
		totdashs = 9;//Integer.parseInt(String.valueOf(dashletSet[0][4]));
			} else {

		totRows = Integer.parseInt(String
				.valueOf(dashletSet[0][1]));
		totCols = Integer.parseInt(String
				.valueOf(dashletSet[0][2]));
		widths = String.valueOf(dashletSet[0][3]);
		totdashs = Integer.parseInt(String
				.valueOf(dashletSet[0][4]));
			}

			// out.println("totRows  "+totRows);
			//  out.println("totCols  "+totCols);
			//  out.println("widths  "+widths);
			//  out.println("totdashs  "+totdashs);

		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>

<%
		try {
		if (configObj == null)
			configObj = (String[][]) request.getAttribute("configObj");
	} catch (Exception e) {
		e.printStackTrace();
	}
%>



<!-- <table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<%
		if (portalAppsObj != null && portalAppsObj.length > 0) {
		%>

		<td width="15%" valign="top"> -->
			<!--  <table width="100%" border="0" cellpadding="0" cellspacing="1">
			<tr>
				<td><img src="../pages/images/icons/portal/myapps.png" /></td>
			</tr>
			<%
						if (portalAppsObj != null && portalAppsObj.length > 0) {
						for (int i = 0; i < portalAppsObj.length; i++) {
			%>
			<tr>
				<td class="portalAppButtons" width="100%"><img
					src="../pages/images/icons/portal/<%= portalAppsObj[i][2]%>";
				align="absmiddle" width="16" height="16" />&nbsp;&nbsp;<a
					href="<%= portalAppsObj[i][3]%>" class="contlink" target="_blank" onclick=""><font
					color="black"><b><%=portalAppsObj[i][1]%></b></font></a></td>
			</tr>
			<%
					}
					}
			%>
			<tr></tr>
		</table>-->
		</td>


		<%
			} else {

			}
		%>



		<!-- <td>
		 <table width="100%" border="0" cellspacing="5px" cellpadding="0"
			align="center">-->
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
		<!-- <td width="<%=widths.split(",")[q]%>%" valign="top"> -->

		<!--<table width="100%" border="0" align="center" cellpadding="0"
					cellspacing="0" class="dashborder">
					<tr>
						<td height="25" bgcolor="#f2f2f2" class="bottomborder">
						<table width="98%" border="0" align="center" cellpadding="0"
							cellspacing="0">
							<tr>
								<td width="17px"><img
									src="../pages/mypage/images/icons/<%=configObj[counter][7]%>"
									width="16" height="16" /></td>
								<td width="100%" class="dasheader">&nbsp;&nbsp;<%=configObj[counter][1]%></td>
								<td width="22%">
								<div align="right">
								<table width="100%" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td width="67%">
										<div align="right"></div>
										</td>
										<td width="33%">
										<div align="right"><a href="javascript:void(0);"
											class="link"><img
											src="../pages/common/css/default/images/update1.gif"
											width="16" height="16" border="0" /></a></div>
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
						<div id="<%=str+(counter)%>"
							style="width: 100%; height: 130px; overflow: auto;"></div>

						</td>
					</tr>

				</table>-->
		<!-- </td> -->

		<%
							counter++;

							}
				%>
		<%
				}
				%><!-- End of for for <td> -->

	</tr>
	<%
			}
			%><!-- End of for loop for <tr> -->

	<!-- </table></td></tr></table> -->
</body>
</html>

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
	 var win =window.open('','win','width=700,height=300,scrollbars=yes,resizable=yes,top=50,left=100');
	 	document.getElementById("paraFrm").target='win';
		document.getElementById("paraFrm").action = '<%=request.getContextPath()%>/recruit/CandidateSearch_searchFrmDashlet.action?dashletFlag=true+&requisitionCode=' + 
		reqCode + '&requisitionName=' + reqName + '&positionCode=' + positionId + '&position=' + position;
	    document.getElementById("paraFrm").submit();
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
		dragableBoxesArray[numericId]['dashletURL'] = value;
		reloadRSSData(numericId);
	}
	
		function viewApplication(action) {
		document.getElementById('paraFrm').target = 'main';
		document.getElementById("paraFrm").action = action;
    	document.getElementById("paraFrm").submit();
    	document.getElementById('paraFrm').target = 'main';
	}
		function callMyAction(actionName)
		{
	 // alert("In my code:"+actionName);
		try{
		document.getElementById('paraFrm').action= '<%=request.getContextPath()%>'+actionName;
		document.getElementById("paraFrm").target="_self";
		document.getElementById('paraFrm').submit();
		}
		catch(e)
		{
			alert("Exception:"+e);
		}
		
	    }

 
 
</script>