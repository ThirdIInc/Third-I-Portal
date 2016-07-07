
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<style type="text/css">
#marqueecontainer {
	position: relative;
	width: 100%; /*marquee width */
	height: 126px; /*marquee height */
	background-color: white;
	overflow: hidden;
	border: 0px solid orange;
	padding: 2px;
	padding-left: 4px;
}
</style>
<link href="../pages/portal/images/style.css" rel="stylesheet"
	type="text/css" />




<body bgcolor="white">



<script type="text/javascript">

/***********************************************
* Cross browser Marquee II- © Dynamic Drive (www.dynamicdrive.com)
* This notice MUST stay intact for legal use
* Visit http://www.dynamicdrive.com/ for this script and 100s more.
***********************************************/

var delayb4scroll=2000 //Specify initial delay before marquee starts to scroll on page (2000=2 seconds)
var marqueespeed=1 //Specify marquee scroll speed (larger is faster 1-10)
var pauseit=1 //Pause marquee onMousever (0=no. 1=yes)?

////NO NEED TO EDIT BELOW THIS LINE////////////

var copyspeed=marqueespeed
var pausespeed=(pauseit==0)? copyspeed: 0
var actualheight=''

function scrollmarquee(){
if (parseInt(cross_marquee.style.top)>(actualheight*(-1)+8))
cross_marquee.style.top=parseInt(cross_marquee.style.top)-copyspeed+"px"
else
cross_marquee.style.top=parseInt(marqueeheight)+8+"px"
}

function initializemarquee(){
cross_marquee=document.getElementById("vmarquee")
cross_marquee.style.top=0
marqueeheight=document.getElementById("marqueecontainer").offsetHeight
actualheight=cross_marquee.offsetHeight
if (window.opera || navigator.userAgent.indexOf("Netscape/7")!=-1){ //if Opera or Netscape 7x, add scrollbars to scroll and exit
cross_marquee.style.height=marqueeheight+"px"
cross_marquee.style.overflow="scroll"
return
}
setTimeout('lefttime=setInterval("scrollmarquee()",35)', delayb4scroll)
}

if (window.addEventListener)
window.addEventListener("load", initializemarquee, false)
else if (window.attachEvent)
window.attachEvent("onload", initializemarquee)
else if (document.getElementById)
window.onload=initializemarquee


</script>



<script type="text/javascript" src="../pages/common/Ajax.js"></script>

<script type="text/javascript">


var req;
function callPage(actionName){ 
	try{
	document.getElementById('paraFrm').action = actionName;
	document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		alert("Error  "+e);
	}
}
 
function show(id){
		var wind = window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=200,left=100');	 
 }
		
function setPollValue(id){
		document.getElementById('optionValue').value = id.value;
 }
	
 function submitPoll(){
	try{ 
	 	var opt = document.getElementById('optionValue').value;
		if(opt == "") {
			alert('Please select an option');
			return false;
		}
		else{ 			
			retrievePollForEmployeePortal('EmployeePortal_pollSave.action?random='+Math.random()+'&pollCode=' + document.getElementById('pollCode').value +'&optionCode=' + opt, 'employeePortalForm');
        }
	}
	catch(e){
			//alert(e);
	}		
}
</script>
<table width="100%" border="0" cellspacing="0" cellpadding="0"
	align="center">
	<tr>
		<td valign="top" width="235"><%@include
			file="../common/leftEmployeePortalHome.jsp"%>
		</td>
		<td valign="top" width="100%"><s:form action="EmployeePortal"
			id="paraFrm" theme="simple" name="employeePortalForm">
			<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0">

						<tr>
							<td>
							<table width="100%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td align="center" width="10%"><img
										style="cursor: pointer;" title="Glofest"
										onClick="callPage('<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=1');"
										src="../pages/portal/images/glofest.jpg " /></td>

									<td align="center" width="10%"><img
										style="cursor: pointer;" title="GCL"
										onClick="callPage('<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=2');"
										src="../pages/portal/images/gcl.jpg " /></td>

									<td align="center" width="10%"><img
										style="cursor: pointer;" title="GTL"
										onClick="callPage('<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=3');"
										src="../pages/portal/images/care.jpg " /></td>

									<td align="center" width="10%"><img
										style="cursor: pointer;" title="My Idea"
										onClick="callPage('<%=request.getContextPath()%>/pages/portal/myidea.jsp?categoryCode=4');"
										src="../pages/portal/images/idea.jpg " /></td>
									<td align="center" width="10%"><img
										style="cursor: pointer;" title="Connexions"
										onClick="callPage('<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=5');"
										src="../pages/portal/images/connexions.jpg " /></td>
									<td align="center" width="10%"><img
										style="cursor: pointer;" title="Rewards"
										onClick="callPage('<%=request.getContextPath()%>/portal/EventData_getRewardData.action?categoryCode=6');"
										src="../pages/portal/images/rewards.jpg" /></td>
									<td align="center" width="10%"><img
										style="cursor: pointer;" title="Colour Band"
										onClick="callPage('<%=request.getContextPath()%>/pages/portal/employeeband.jsp?categoryCode=7');"
										src="../pages/portal/images/colorband.jpg " /></td>
									<td align="center" width="10%"><img
										style="cursor: pointer;" title="Helpdesk"
										onClick="callPage('<%=request.getContextPath()%>/help/HelpDesk_input.action');"
										src="../pages/portal/images/helpdesk.jpg " /></td>
									<td align="center" width="10%"><img
										style="cursor: pointer;" title="Induction"
										onClick="callPage('<%=request.getContextPath()%>/pages/portal/induction.jsp?categoryCode=9');"
										src="../pages/portal/images/induciton.jpg " /></td>
									<td align="center" width="10%"><img
										style="cursor: pointer;" title="Tips"
										onClick="callPage('<%=request.getContextPath()%>/pages/portal/knoledge.jsp?categoryCode=10');"
										src="../pages/portal/images/knowledge.jpg " /></td>
								</tr>

								<tr>
									<td width="10%" align="center" class="headerDashlet">Retreat</td>
									<td width="10%" align="center" class="headerDashlet">TPL</td>
									<td width="10%" align="center" class="headerDashlet">Third (I) 
									Care</td>
									<td width="10%" align="center" class="headerDashlet">My
									Idea</td>
									<td width="10%" align="center" class="headerDashlet">Connexions</td>
									<td width="10%" align="center" class="headerDashlet">Rewards</td>
									<td width="10%" align="center" class="headerDashlet">Colour
									Band</td>
									<td width="10%" align="center" class="headerDashlet">Helpdesk</td>
									<td width="10%" align="center" class="headerDashlet">Induction</td>
									<td width="10%" align="center" class="headerDashlet">Tips</td>
								</tr>
								<tr>
									<td height="5" colspan="10"></td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						bordercolor="red">
						<tr>
							<td width="35%" valign="top">

							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img align="absmiddle"
												src="../pages/mypage/images/icons/28.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Announcement</td>
											<td width="22%">
											<div align="right">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
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
									<td height="65"
										background="../pages/common/css/default/images/grad.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<%
												try {

												String[][] corpList = (String[][]) request
												.getAttribute("corpList");
										%>
										<%
													if (corpList != null && corpList.length > 0) {
													for (int i = 0; ((i < corpList.length) && i < 5); i++) {
										%>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="javascript:void(0);" class="contlink"
												onclick="show('<%=corpList[i][1] %>');"> <%
 			try {
 			out.print(corpList[i][0].substring(0, 50) + "..");
 		} catch (Exception ex) {
 			out.println(corpList[i][0]);
 		}
 %> </a></td>
										</tr>

										<%
												}
												}
												if (corpList != null && corpList.length > 0) {
													for (int i = 0; i < 5 - corpList.length; i++) {
										%>

										<tr>
											<td height="20" width="3%"></td>
											<td height="20" width="97%"></td>
										</tr>
										<%
												}
												}

											} catch (Exception e) {

											}
										%>
										<tr>
											<td>&nbsp;</td>
											<td height="22" valign="bottom"><label>
											<div align="right"><img
												onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showMoreInfo.action?dashletCode=1');"
												src="../pages/common/css/default/images/more.gif" width="42"
												height="13" border="1"
												style="border-color: #CCCCCC; cursor: pointer;" /></div>
											</label></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</td>
							<td width="35%" valign="top">

							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img align="absmiddle"
												src="../pages/mypage/images/icons/26.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Corporate
											Event</td>
											<td width="22%">
											<div align="right">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
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
									<td height="65"
										background="../pages/common/css/default/images/grad.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<%
												try {

												Object[][] eventDataObj = null;
												eventDataObj = (Object[][]) request
												.getAttribute("eventDataObj");
										%>
										<%
													if (eventDataObj != null && eventDataObj.length > 0) {
													for (int i = 0; ((i < eventDataObj.length) && i < 5); i++) {
										%>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="javascript:void(0);" class="contlink"
												onClick="callPage('<%=request.getContextPath()%>/portal/EventData_homePageGlofest.action?categoryCode=<%=eventDataObj[i][2]%>&eventCode=<%=eventDataObj[i][0]%>&yearValue=<%=eventDataObj[i][3]%>');">
											<%=eventDataObj[i][1]%> </a></td>
										</tr>

										<%
												}
												}
												if (eventDataObj != null && eventDataObj.length > 0) {
													for (int i = 0; i < 5 - eventDataObj.length; i++) {
										%>

										<tr>
											<td height="20" width="3%"></td>
											<td height="20" width="97%"></td>
										</tr>
										<%
												}
												}

											} catch (Exception e) {

											}
										%>
										<tr>
											<td>&nbsp;</td>
											<td height="28" valign="bottom"><label>
											<div align="right"><img
												onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showMoreInfo.action?dashletCode=2');"
												src="../pages/common/css/default/images/more.gif" width="42"
												height="13" border="1"
												style="border-color: #CCCCCC; cursor: pointer;" /></div>
											</label></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>


							</td>
							<td width="35%" valign="top">

							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img
												src="../pages/mypage/images/icons/34.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Opinion
											Poll</td>
											<td width="22%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
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
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<%
											Object[][] optionObj = (Object[][]) request
											.getAttribute("optionObj");
								%>
								<tr>
									<td height="25">
									<%
									if (optionObj != null && optionObj.length > 0) {
									%> <s:hidden name="pollCode" id="pollCode" value="%{pollCode}" />
									<s:property value="quesName" /> <%
 }
 %>
									</td>
								</tr>

								<%
										if (optionObj != null && optionObj.length > 0) {
										int length = (optionObj.length) > 3 ? 3 : optionObj.length;
										for (int k = 0; k < length; k++) {
								%>

								<tr>
									<td height="22" valign="middle">
									<table border="0" cellpadding="0" cellspacing="0" width="100%">
										<tr>
											<td valign="middle" width="2px"><input type="radio"
												value="<%=String.valueOf(optionObj[k][0]) %>"
												name="voteValue" onClick="setPollValue(this);"
												id="paraFrm_voteValue"></td>
											<td valign="middle" align="left"><span class="blacklink">&nbsp;<%=String.valueOf(optionObj[k][1])%>
											</span></td>
										</tr>
									</table>
									</td>
								</tr>
								<%
									}
									}
								%>

								<tr>
									<td height="34" valign="bottom"><input type="hidden"
										name="optionValue" id="optionValue" />

									<div align="right"><img style="cursor: pointer;"
										src="../pages/portal/images/submit.gif" width="51" height="24"
										onclick="submitPoll()" /></div>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="5" colspan="3"></td>
						</tr>
						<tr>
							<td width="35%" valign="top">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img
												src="../pages/mypage/images/icons/46.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Director
											Connect</td>
											<td width="22%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="67%">
													<div align="right"></div>
													</td>
													<td width="33%">
													<div align="right"><a href="javascript:void(0);"
														class="contlink"><img
														src="../pages/common/css/default/images/update1.gif"
														width="16" height="16" border="0" /></a></div>
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<script>
								function callCeo()
								{
									document.getElementById('paraFrm').action='<%=request.getContextPath()%>/pages/portal/ceoconnect.jsp?categoryCode=1&loginEmpCode=<%=request.getAttribute("login_EmpId")%>&isceolink=false';
									document.getElementById('paraFrm').submit();
								}
								</script>
								<tr>
									<td>
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
											<td width="100%" valign="top" height="95"><img
												style="cursor: pointer;" onclick="callCeo();"
												src="<%=request.getContextPath()%>/pages/portal/images/arrow.gif"
												width="226" height="72" /></td>

										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td valign="top" height="37"><a class="contlink"
										href="<%=request.getContextPath()%>/pages/portal/ceoconnect.jsp?categoryCode=1&loginEmpCode=<%=request.getAttribute("login_EmpId")%>&isceolink=true"><br>

									Click here to Connect our Directors</a></td>
								</tr>
							</table>
							</td>
							<td valign="top" width="35%">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img
												src="../pages/mypage/images/icons/22.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;News
											Updates</td>
											<td width="22%">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<tr>
													<td width="67%">
													<div align="right"></div>
													</td>
													<td width="33%">
													<div align="right"><a href="#" class="link"><img
														src="../pages/common/css/default/images/update1.gif"
														width="16" height="16" border="0" /></a></div>
													</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
								<tr>
									<td height="128" bgcolor="#f2f2f2" valign="top"><%@include
										file="../portal/newsUpdate.html"%></td>
								</tr>
							</table>
							</td>
							<td width="35%" valign="top">

							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img align="absmiddle"
												src="../pages/mypage/images/icons/17.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Corporate
											Information</td>
											<td width="22%">
											<div align="right">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
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
									<td height="65"
										background="../pages/common/css/default/images/grad.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<%
												try {

												String[][] corpInfoList = (String[][]) request
												.getAttribute("corpInfoList");
										%>
										<%
													if (corpInfoList != null && corpInfoList.length > 0) {
													for (int i = 0; ((i < corpInfoList.length) && i < 5); i++) {
										%>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="javascript:void(0);" class="contlink"
												onclick="show('<%=corpInfoList[i][1] %>');"> <%
 			try {
 			out.print(corpInfoList[i][0].substring(0, 28)
 			+ "..");
 		} catch (Exception ex) {
 			out.println(corpInfoList[i][0]);
 		}
 %> </a></td>
										</tr>

										<%
												}
												}
												if (corpInfoList != null && corpInfoList.length > 0) {
													for (int i = 0; i < 5 - corpInfoList.length; i++) {
										%>

										<tr>
											<td height="20" width="3%"></td>
											<td height="20" width="97%"></td>
										</tr>
										<%
												}
												}

											} catch (Exception e) {

											}
										%>
										<tr>
											<td>&nbsp;</td>
											<td height="31" valign="bottom"><label>
											<div align="right"></div>
											</label></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td height="5" colspan="3"></td>
						</tr>
						<tr>
							<td width="35%" valign="top">
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img
												src="../pages/mypage/images/icons/42.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Quick
											Information</td>
											<td width="22%">
											<div align="right">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
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
									<td height="65"
										background="../pages/common/css/default/images/grad.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<%
												try {

												String[][] genList = (String[][]) request
												.getAttribute("genList");
										%>
										<%
													if (genList != null && genList.length > 0) {
													for (int i = 0; ((i < genList.length) && i < 5); i++) {
										%>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="javascript:void(0);" class="contlink"
												onclick="setHolidayVal('<%=genList[i][1]%>');callMore('<%=request.getContextPath()%>/portal/EmployeePortal_showGeneralDashletList.action?type=<%=genList[i][1]%>');">
											<%=genList[i][1]%> </a></td>
										</tr>

										<%
												}
												}
												if (genList != null && genList.length > 0) {
													for (int i = 0; i < 5 - genList.length; i++) {
										%>

										<tr>
											<td height="20" width="3%"></td>
											<td height="20" width="97%"></td>
										</tr>
										<%
												}
												}

											} catch (Exception e) {

											}
										%>
										<tr>
											<td>&nbsp;</td>
											<td height="22" valign="bottom"><label>
											<div align="right"></div>
											</label></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</td>
							<td width="35%" valign="top">


							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img
												src="../pages/mypage/images/icons/02.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Reward &
											Recognition</td>
											<td width="22%">
											<div align="right">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
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
									<td height="65"
										background="../pages/common/css/default/images/grad.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="9%" valign="top">
											<%
												Object[][] awardImages = null;
												try {
													awardImages = (Object[][]) request.getAttribute("awardPhoto");

													if (awardImages != null && awardImages.length > 0) {
														for (int i = 0; i < 1; i++) {
													if (String.valueOf(awardImages[i][0]).equals("nophoto")) {
											%> <img src="../pages/portal/images/empimage.gif" width="80"
												height="80" /> <%
 } else {
 %> <img
												src="../pages/images/<%=session.getAttribute("session_pool")%>/award/<%=awardImages[i][0] %>"
												width="80" height="80" /> <%
 			}
 			}
 		}
 	} catch (Exception e) {
 		e.printStackTrace();
 	}
 %>
											</td>
											<td width="91%"
												style="padding-left: 5px; text-align: justify;">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
												<%
												if (awardImages != null && awardImages.length > 0) {
												%>
												<tr>
													<td height="18" class="dasheader"><%=Utility.checkNull(String.valueOf(awardImages[0][4]))%>
													</td>
												</tr>
												<tr>
													<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][1]))%></td>
												</tr>
												<tr>
													<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][3]))%></td>
												</tr>
												<tr>
													<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][2]))%></td>
												</tr>
												<tr>
													<td height="18" class="contlink"><%=Utility.checkNull(String.valueOf(awardImages[0][5]))%></td>
												</tr>
												<%
												}
												%>
											</table>
											</td>
										</tr>
										<tr>
											<td>&nbsp;</td>
											<td height="32" valign="bottom"><label>
											<div align="right"><img
												onClick="callPage('<%=request.getContextPath()%>/portal/EventData_getRewardData.action?categoryCode=6');"
												src="../pages/common/css/default/images/more.gif" width="42"
												height="13" border="1"
												style="border-color: #CCCCCC; cursor: pointer;" /></div>
											</label></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>
							</td>
							<td width="35%" valign="top">

							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="0" class="dashborder">
								<tr>
									<td height="25" bgcolor="#f2f2f2" class="bottomborder">
									<table width="98%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td width="6%"><img
												src="../pages/mypage/images/icons/43.png" width="16"
												height="16" /></td>
											<td width="74%" class="dasheader">&nbsp;&nbsp;Quick
											Downloads</td>
											<td width="22%">
											<div align="right">
											<table width="100%" border="0" cellspacing="0"
												cellpadding="0">
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
									<td height="65"
										background="../pages/common/css/default/images/grad.jpg">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="javascript:void(0);" class="contlink"
												onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_quickDownload.action?downloadCode=1');">
											Logo & Templates </a></td>
										</tr>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="<%=request.getContextPath()%>/pages/portal/corporate fonts.zip"
												class="contlink"> Corporate Fonts </a></td>
										</tr>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="javascript:void(0);" class="contlink"
												onclick="callMore('<%=request.getContextPath()%>/portal/EmployeePortal_quickDownload.action?downloadCode=2');">
											e-Greetings, Desktop Wallpapers </a></td>
										</tr>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href="http://www.glodynetechnoserve.com/newsroom.html"
												class="contlink"> Third(I) in News </a></td>
										</tr>

										<tr>
											<td height="20" width="3%"><img align="absmiddle"
												src="../pages/common/css/default/images/dot.gif" /></td>
											<td height="20" width="97%"><a
												href=" http://www.glodynetechnoserve.com/investor.html"
												class="contlink">Third (I) Financials </a></td>
										</tr>



										<tr>
											<td>&nbsp;</td>
											<td height="22" valign="bottom"><label>
											<div align="right"></div>
											</label></td>
										</tr>

									</table>
									</td>
								</tr>
							</table>




							</td>

						</tr>
					</table>
					</td>
				</tr>
			</table>

			<s:hidden name="typeFeild" id="typeFeild" />

		</s:form></td>
	</tr>
</table>
<%@include file="/pages/common/footer.jsp"%>
</body>
<script>

function setHolidayVal(id){
		document.getElementById('typeFeild').value=id;
}

function callMore(id){
	// window.open(id,'_blank','width=700,height=400,scrollbars=yes,resizable=yes,menubar=no,top=100,left=100');
	if( document.getElementById('typeFeild').value=='Holiday'){
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_holidays.action';
	}
	else if(document.getElementById('typeFeild').value=='Quick Contacts'){
		document.getElementById('paraFrm').action='<%=request.getContextPath()%>/portal/EmployeePortal_getQuickContacts.action';
	}
	else{
		document.getElementById('paraFrm').action=id;
	}
	document.getElementById('paraFrm').submit();	
}
 
</script>






