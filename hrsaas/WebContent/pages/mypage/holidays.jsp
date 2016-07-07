 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>


<%
			Object[][] nationalHolidaysObj = (Object[][]) request
			.getAttribute("nationalHolidaysObj");

	Object[][] locationSpecificHolidaysObj = (Object[][]) request
			.getAttribute("locationSpecificHolidaysObj");
%>
<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">


	<div style="float: left; width: 100%">

	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		height="100%">

		<tr>
			<td height="25" colspan="6">
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				height="100%" class="formbg">
				<tr>
					<td><a style="cursor: hand" id="mytimecard"
						href="javascript:void(0);" class="contlink" title="My Timecard"
						onclick="javascript:setLeaveAttendanceType('mytimecard');"> My
					Timecard </a> <span><strong>|</strong></span> <a class="contlink"
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
					Leaves </a> <span><strong>|</strong></span> <a
						href="javascript:void(0);" class="contlink" style="cursor: hand"
						id="holidays" title="Holidays"
						onclick="javascript:setLeaveAttendanceType('holidays');">
					Holidays </a></td>
				</tr>
			</table>
			</td>
		</tr>
		
		
		

<tr>
			<td>
			<table width="100%" height="40%" border="0" cellspacing="0"
				bordercolor="red" cellpadding="0">
				<tr>
					<td valign="top" colspan="6">
					<table width="100%" border="0" cellspacing="0" cellpadding="0"
						class="pageHeader">
						<tr>
							<td width="4%"><img
								src="../pages/mypage/images/icons/leave.png" width="24"
								height="24" /></td>
							<td width="96%">Holidays</td>
						</tr>
					</table>
					</td>
				</tr>

		<tr>
			<td width="77%" height="28" class="link">Please select the year
			to view the Holiday list.</td>
			<td width="23%" class="link"></td>
		</tr>


		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">

				<tr>
					<td>
					<table width="100%" border="0" cellspacing="2" cellpadding="2">

						<tr>
							<td width="49%" height="20">Year:&nbsp;&nbsp;&nbsp;&nbsp;<s:select
								name="holidayYear" onchange="onChangeYearLocationData();"
								list="holidayMap" cssStyle="width:70;height:2;" /></td>
							<td width="51%" height="20">Location:&nbsp;&nbsp;&nbsp;&nbsp;<s:select
								cssStyle="width:70;height:2;"  name="holidayLocation"
								list="holidayLocationNameList"
								onchange="onChangeYearLocationData();" /></td>
						</tr>
						<tr>
							<td width="49%" height="20" bgcolor="#f8f8f8"><b>Fixed Holidays</b></td>
							<td width="51%" height="20" bgcolor="#f8f8f8"><b>Location
							Specific Holidays</b></td>
						</tr>


						<tr>
							<td height="20" valign="top">
							<table width="100%" border="0" cellpadding="4" cellspacing="2"
								bgcolor="#FFFFFF" class="border">
								<tr>
									<td width="13%" height="25" bgcolor="#FCF7E7" align="right">Sr.No</td>
									<td width="33%" height="25" bgcolor="#FCF7E7">Date</td>
									<td width="21%" height="25" bgcolor="#FCF7E7">Day</td>
									<td width="33%" height="25" bgcolor="#FCF7E7">Description</td>
								</tr>

								<%
										try {
										if (nationalHolidaysObj != null
										&& nationalHolidaysObj.length > 0) {
											for (int i = 0; i < nationalHolidaysObj.length; i++) {
								%>
								<tr>
									<td height="20" class="sortableTD" nowrap="nowrap"
										align="right"><%=Utility.checkNull(String
										.valueOf(nationalHolidaysObj[i][0]))%></td>
									<td height="20" class="sortableTD" nowrap="nowrap" align="left"><%=Utility.checkNull(String
										.valueOf(nationalHolidaysObj[i][1]))%></td>
									<td height="20" class="sortableTD" nowrap="nowrap" align="left"><%=Utility.checkNull(String
										.valueOf(nationalHolidaysObj[i][2]))%></td>
									<td height="20" class="sortableTD" nowrap="nowrap" align="left"><%=Utility.checkNull(String
										.valueOf(nationalHolidaysObj[i][3]))%></td>
								</tr>
								<%
										}
										}
									} catch (Exception e) {
										e.printStackTrace();
									}
								%>


							</table>
							</td>
							<td height="20" valign="top">
							<table width="100%" border="0" cellpadding="4" cellspacing="2"
								bgcolor="#FFFFFF" class="border">
								<tr>
									<td width="13%" height="25" bgcolor="#FCF7E7" align="right">Sr.No</td>
									<td width="33%" height="25" bgcolor="#FCF7E7">Date</td>
									<td width="21%" height="25" bgcolor="#FCF7E7">Day</td>
									<td width="33%" height="25" bgcolor="#FCF7E7">Description</td>
								</tr>
								<%
										try {
										if (locationSpecificHolidaysObj != null
										&& locationSpecificHolidaysObj.length > 0) {
											int a=0;
											for (int j = 0; j < locationSpecificHolidaysObj.length; j++) {
								%>
								<tr>
									<td height="20" class="sortableTD" nowrap="nowrap"
										align="right"><%=++a%></td>
									<td height="20" class="sortableTD" nowrap="nowrap" align="left"><%=Utility
												.checkNull(String
														.valueOf(locationSpecificHolidaysObj[j][0]))%></td>
									<td height="20" class="sortableTD" align="left" nowrap="nowrap"><%=Utility
												.checkNull(String
														.valueOf(locationSpecificHolidaysObj[j][1]))%></td>
									<td height="20" class="sortableTD" align="left"><%=Utility
												.checkNull(String
														.valueOf(locationSpecificHolidaysObj[j][2]))%></td>
								</tr>
								<%
										}
										}
									} catch (Exception e) {
										out.print("Exception >>" + e);
									}
								%>
							</table>
							</td>
						</tr>
					</table>
					</td>
				</tr>
 </table></td></tr></table></td></tr></table></div></s:form>

<script> 


	onLoad();
	function onLoad()
	{
		document.getElementById('holidays').className='contlink';
	}

function Call(){
	try{
		callsF9(500,325,'MypageProcessManagerAlerts_f9actionEmp.action');
	}catch(e){
		alert("Error:::::"+e.description);
	}
}

function setLeaveAttendanceType(type){
 
		if(type=="mytimecard"){
		document.getElementById('attendanceregularization').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="attendanceregularization"){
		document.getElementById('mytimecard').className='';
		document.getElementById('annualattendance').className='';
		document.getElementById('myleaves').className='';
		document.getElementById('holidays').className='';		
		document.getElementById(type).className='contlink';
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_attendanceRegularization.action';
		document.getElementById('paraFrm').submit();
		}
		if(type=="annualattendance"){
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


function onChangeYearLocationData() {
	try{
	var holidayYear=document.getElementById('paraFrm_holidayYear').value;
	//alert(holidayYear);
	var holidaySelectedLocation=document.getElementById('paraFrm_holidayLocation').value;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_holidays.action?holidayYear='+holidayYear+'&holidaySelectedLocation='+holidaySelectedLocation;
	document.getElementById('paraFrm').submit();
	}
	catch(e)
	{
		//alert("Error Holiday--"+e);
	}
}

</script>

 