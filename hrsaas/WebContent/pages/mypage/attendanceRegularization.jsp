<%@ taglib prefix="s" uri="/struts-tags"%>

<%@page import="org.paradyne.lib.Utility"%>

<%
			Object attenRegObj[][] = (Object[][]) request
			.getAttribute("attendRegData");
%>

<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">


	<div style="float: left; width: 100%">


	<div style="float: left; width: 100%">



	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>

			<td width="100%" valign="top">
			<table width="100%" border="0" cellspacing="1" cellpadding="1"
				height="100%">

				<tr>
					<td height="25">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						height="100%" class="formbg">
						<tr>
							<td><a style="cursor: hand" id="mytimecard"
								href="javascript:void(0);" class="contlink" title="My Timecard"
								onclick="javascript:setLeaveAttendanceType('mytimecard');">
							My Timecard </a> <span><strong>|</strong></span> <a class="contlink"
								style="cursor: hand" id="attendanceregularization"
								title="Attendance Regularization" href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('attendanceregularization');">
							Attendance Regularization </a> <span><strong>|</strong></span> <a
								class="contlink" style="cursor: hand" id="annualattendance"
								href="javascript:void(0);" title="Annual Attendance"
								onclick="javascript:setLeaveAttendanceType('annualattendance');">
							Annual Attendance </a> <span><strong>|</strong></span> <a
								class="contlink" style="cursor: hand" id="myleaves"
								title="My Leaves" href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('myleaves');"> My
							Leaves </a> <span><strong>|</strong></span> <a class="contlink"
								style="cursor: hand" id="holidays" title="Holidays"
								href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('holidays');">
							Holidays </a></td>
						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" height="40%" border="0" cellspacing="0"
						cellpadding="0">
						<tr>
							<td valign="top" colspan="8" width="100%">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="pageHeader">
								<tr>
									<td width="4%"><img
										src="../pages/mypage/images/icons/config-date.png" width="24"
										height="24" /></td>
									<td width="96%">Regularization Records</td>
								</tr>
							</table>
							<a href="javascript:void(0)"
								onclick="callMyActionLink('/leaves/Regularization_input.action');"></a></td>
						</tr>
						<tr>
							<td valign="top" colspan="8" height="28">
							<table width="98%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td nowrap="nowrap">Please select the year to view
									regularization records.</td>
									<td align="right"><a href="javascript:void(0)"
										title="Apply Regularization" class="contlink"
										onclick="callMyActionLink('/leaves/Regularization_apply.action?src=attendanceRegularization');"><b>Apply
									Regularization</b></a></td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td width="16%" height="28" colspan="1">Employee : <s:hidden
								name="empCodeAttReg" /></td>
							<td colspan="4"><s:hidden name="empTokenAttReg"
								value="%{empTokenAttReg}" /><s:textfield name="empNameAttReg"
								size="50" value="%{empNameAttReg}" theme="simple"
								readonly="true" />
								
								<s:if test="%{bean.generalFlag}">
								</s:if>
								<s:else><!--
								 <input style="cursor: hand;" name="Submit3"
								align="absmiddle" type="button" class="button" value="..."
								onclick="Call();" id="ctrlHide" />
								--></s:else>
								</td>
						</tr>
						<tr>
							<td width="16%" align="left">Year&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;:</td>
							<td width="20%"><s:select name="regularizationYear"
								id="regularizationYear" list="yearMap"
								cssStyle="width:70;height:2;" /></td>
							<td width="12%">Status Filter</td>
							<td width="12%"><s:checkbox name="showallRegularization" id="showallRegularization"
								onclick="return checkShowAll();" />Show All</td>
							<td width="12%"><s:checkbox name="appliedRegularization"  id="appliedRegularization"  onclick="unCheckShowAll();"/>Applied</td>
							<td width="12%"></td>

						</tr>
						<tr height="28">
							<td width="16%"></td>
							<td width="20%"></td>
							<td width="12%"></td>
							<td width="12%"><s:checkbox name="approvedRegularization" id="approvedRegularization" onclick="unCheckShowAll();"/>Approved</td>
							<td width="12%"><s:checkbox name="rejectedRegularization"  id="rejectedRegularization" onclick="unCheckShowAll();"/>Rejected</td>
							<td width="12%"><input type="button" name="show"
								value="Show" class="token" onclick="return validateSearch();"></td>

						</tr>

					</table>
					</td>
				</tr>
				<tr>
					<td valign="top" height="100%" colspan="6">
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>

							<td width="10%" class="mypageTh" nowrap="nowrap">Date
							Regularized</td>
							<td width="35%" class="mypageTh">Reason</td>
							<td width="10%" class="mypageTh" nowrap="nowrap">Applied
							Date</td>
							<td width="35%" class="mypageTh">Authorized By</td>
							<td width="10%" class="mypageTh" nowrap="nowrap">Status</td>
						</tr>
						<%
								if (attenRegObj != null && attenRegObj.length > 0) {

								for (int i = 0; i < attenRegObj.length; i++) {
						%>
						<tr>
							<td class="sortableTD"><%=Utility.checkNull(String
									.valueOf(attenRegObj[i][0]))%></td>
							<td class="sortableTD"><%=Utility.checkNull(String
									.valueOf(attenRegObj[i][1]))%></td>
							<td class="sortableTD"><%=Utility.checkNull(String
									.valueOf(attenRegObj[i][2]))%></td>
							<td class="sortableTD"><%=Utility.checkNull(String
									.valueOf(attenRegObj[i][3]))%></td>
							<td class="sortableTD"><%=Utility.checkNull(String
									.valueOf(attenRegObj[i][4]))%></td>
						</tr>
						<%
							}
							}
						%>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>


	</div>
</s:form>

<script>



function unCheckShowAll(){

		if(document.getElementById("appliedRegularization").checked){
			document.getElementById("showallRegularization").checked=false;
		}
		
		if(document.getElementById("approvedRegularization").checked){
			document.getElementById("showallRegularization").checked=false;
		}
		if(document.getElementById("rejectedRegularization").checked){
			document.getElementById("showallRegularization").checked=false;
		}
	  
			
}


	onLoad();
	function onLoad()
	{
		document.getElementById('attendanceregularization').className='contlink';
		//checkShowAll();
	}


function checkShowAll()
{
	
	if(document.getElementById("showallRegularization").checked)
		{
			
			document.getElementById("appliedRegularization").checked="true";
			document.getElementById("approvedRegularization").checked="true";
			document.getElementById("rejectedRegularization").checked="true";
			
			
		}
		else
		{
		
			document.getElementById("appliedRegularization").checked=false;
			document.getElementById("approvedRegularization").checked=false;
			document.getElementById("rejectedRegularization").checked=false;	
		}
}
function callMyActionLink(actionName)
{ 
 	//alert(actionName);
	document.getElementById('paraFrm').action ='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getMenuSearch.action?actionName='+actionName;
	document.getElementById('paraFrm').submit();
}

function validateSearch()
{

	try{
	var regularizationYear =document.getElementById('regularizationYear').value;
	var employeeCode=document.getElementById('paraFrm_empCodeAttReg').value;
	var appliedStatus="";
	var approvedStatus="";
	var rejectedStatus="";
	var showallStatus="";
	if(employeeCode=="")
	{
		alert("Please select employee");
		return false;
	}
	
	
		
		if(document.getElementById("appliedRegularization").checked)
		{
			appliedStatus="P";
		}
		if(document.getElementById("approvedRegularization").checked)
		{
			approvedStatus="A";
		}
		if(document.getElementById("rejectedRegularization").checked)
		{
			rejectedStatus="R";
		}
		if(document.getElementById("showallRegularization").checked)
		{
			showallStatus="All";
		}
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_attendanceRegularization.action?attndRegularemployeeCode='+employeeCode+'&myAttendRegulAppliedstatus='+appliedStatus+'&myAttendRegulApprovestatus='+approvedStatus+'&myAttendRegulRejectedstatus='+rejectedStatus+'&regularizationYear='+regularizationYear+'&showallstatus='+showallStatus;
		document.getElementById('paraFrm').submit();
		
		}catch(e)
		{
				alert("Error Attendance regularization "+e);
		}
}

function Call()
{
	try{
	//alert("befor action call " );
	callsF9(500,325,'MypageProcessManagerAlerts_f9actionAttendanceRegularization.action');
	//alert("after action call ");
	}
	catch(e)
	{
		alert("Error:::::"+e.description);
	}

}
	function setLeaveAttendanceType(type){
		//document.getElementById('menuoremployee').value=type;
		
		//document.getElementById('emp').className='';
		//document.getElementById('menu').className='';
		if(type=="mytimecard")
		{
		document.getElementById('attendanceregularization').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="attendanceregularization")
		{
		document.getElementById('mytimecard').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_attendanceRegularization.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="annualattendance")
		{
		document.getElementById('attendanceregularization').className='';
		document.getElementById('mytimecard').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_annualAttendance.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="myleaves"){
		document.getElementById('attendanceregularization').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('mytimecard').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_myLeaves.action';
		document.getElementById('paraFrm').submit();
		}
		
		if(type=="holidays"){
		document.getElementById('attendanceregularization').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('mytimecard').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_holidays.action';
		document.getElementById('paraFrm').submit();
		}
 

}
</script>