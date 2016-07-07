<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%
			Object mytimeCardObj[][] = (Object[][]) request
			.getAttribute("mytimecardData");
%>
<%
			Object mytimeCardTeamObj[][] = (Object[][]) request
			.getAttribute("mytimecardDataTeam");
%>
<%!public String convertProperFormat(String str) {

		String properStr = "";

		if (!str.equals("00:00")) {
			String[] SplitStr = str.split(":");
			if (SplitStr != null && SplitStr.length > 0) {
				if (Integer.parseInt(SplitStr[0]) <= 0) {
					properStr += "0" + SplitStr[0] + ":" + SplitStr[1];
				}
				if (Integer.parseInt(SplitStr[1]) <= 0) {
					properStr += SplitStr[0] + ":" + "0" + SplitStr[1];
				}
			}

		} else {
			properStr = str;
		}

		return properStr;
	}%>

<%@ include file="../common/commonValidations.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">
	<s:hidden name="attendance"/>
	<s:hidden name="report" />
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
							<td><a class="contlink" href="javascript:void(0);"
								style="cursor: hand" id="mytimecard" title="My Timecard"
								onclick=" chkFlag();javascript:setLeaveAttendanceType('mytimecard');">
							My Timecard </a> <span><strong>|</strong></span> <a class="contlink"
								href="javascript:void(0);" title="Attendance Regularization"
								style="cursor: hand" id="attendanceregularization"
								onclick="javascript:setLeaveAttendanceType('attendanceregularization');">
							Attendance Regularization </a> <span><strong>|</strong></span> <a
								class="contlink" title="Annual Attendance" style="cursor: hand"
								href="javascript:void(0);" id="annualattendance"
								onclick="javascript:setLeaveAttendanceType('annualattendance');">
							Annual Attendance </a> <span><strong>|</strong></span> <a
								class="contlink" style="cursor: hand" id="myleaves"
								title="My Leaves" href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('myleaves');"> My
							Leaves </a> <span><strong>|</strong></span> <a title="Holidays"
								class="contlink" style="cursor: hand" id="holidays"
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
							<td valign="top" colspan="8">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="pageHeader">
								<tr>
									<td width="4%"><img
										src="../pages/mypage/images/icons/timecard.png" width="24"
										height="24" /></td>
									<td width="80%" style="margin-left: 5px;" colspan="4">My
									Timecard</td>
									<td nowrap="nowrap">Export:&nbsp;<a href="#"
										onclick="callReport('Pdf');"> <img
										src="../pages/images/buttonIcons/file-pdf.png"
										class="iconImage" align="absmiddle" " title="PDF"><span
										style="padding-left: 5px;">Pdf</span></a>&nbsp;&nbsp;</td>
									<td nowrap="nowrap"><a href="#"
										onclick="callReport('Xls');"> <img
										src="../pages/images/buttonIcons/file-xls.png"
										class="iconImage" align="absmiddle"
										onclick="callReport('Xls');" title="Excel"><span
										style="padding-left: 5px;">Excel</span></a> &nbsp;&nbsp;</td>
								</tr>
							</table>
							</td>
						</tr>
						<tr>
							<td valign="top" colspan="6" height="28">Timecard shows your
							date wise IN OUT time details with attendance status.</td>							
							<td colspan="2"><s:if test="loginAttendanceFlg"><img
								src="../pages/mypage/images/attendance.gif" align="absmiddle"
								height="16" width="16" /> <a href="javascript:void(0)"
								class="contlink" title="DAY IN" onclick="callDayIN();"><b><u>Day
							IN<u></b></a></s:if></td>
						</tr>
						<s:if test="loginAttendanceFlg">
						<tr>
							<td colspan="6"></td>
							<td colspan="2"><img
								src="../pages/mypage/images/attendance.gif" align="absmiddle"
								height="16" width="16" /> <a href="javascript:void(0)"
								class="contlink" title="DAY OUT" onclick="callOut();"><b><u>Day
							Out<u></b></a></td>
						</tr>
						</s:if>
						<tr height="28">
							<td width="16%" colspan="1">Employee&nbsp;&nbsp; <s:hidden
								name="empCodeMyPage" /></td>
							<td colspan="4"><s:hidden name="empTokenMyPage"
								value="%{empTokenMyPage}" /><s:textfield name="empNameMyPage"
								size="50" value="%{empNameMyPage}" theme="simple"
								readonly="true" /><img
								src="../pages/images/recruitment/search2.gif" height="18"
								class="iconImage" align="absmiddle" width="18"
								onclick="callsF9(500,325,'MypageProcessManagerAlerts_f9Team.action');"></td>
							<s:if test="%{bean.generalFlag}">
							</s:if>
							<s:else>
								<!--
								<input style="cursor: hand;" name="Submit3"
								align="absmiddle" type="button" class="button" value="..."
								onclick="Call();" id="ctrlHide" />
								-->
							</s:else>
						</tr>
						<tr height="28">
							<td width="16%">From Date&nbsp;&nbsp;<font color="red">*</font></td>
							<td width="24%"><s:textfield name="fromdate"
								id="paraFrm_fromdate" onkeypress="return numbersWithHiphen();"
								maxlength="10" /><s:a
								href="javascript:NewCal('paraFrm_fromdate','DDMMYYYY');">

								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />

							</s:a></td>
							<td width="10%">Status Filter</td>
							<td width="12%"><s:checkbox name="showalltimecard"
								id="showalltimecard" onclick="return checkShowAll();" />Show
							All</td>
							<td width="12%"><s:checkbox id="presenttimecard"
								name="presenttimecard" onclick="return unCheckShowAll();" />Present</td>
							<td width="12%"><s:checkbox name="latetimecard"
								id="latetimecard" onclick="return unCheckShowAll();" />Late</td>
							<td width="13%">&nbsp;</td>
						</tr>
						<tr height="28">
							<td width="16%">To Date &nbsp;&nbsp;&nbsp;&nbsp;
							<font color="red">*</font></td>
							<td width="24%"><s:textfield name="todate"
								id="paraFrm_todate" onkeypress="return numbersWithHiphen();"
								maxlength="10" /><s:a
								href="javascript:NewCal('paraFrm_todate','DDMMYYYY');">

								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" border="0" align="absmiddle" />

							</s:a></td>
							<td width="10%">&nbsp;</td>
							<td width="12%"><s:checkbox name="halfDaytimecard"
								id="halfDaytimecard" onclick="return unCheckShowAll();" />Half
							Day</td>
							<td width="12%"><s:checkbox name="leavetimecard"
								id="leavetimecard" onclick="return unCheckShowAll();" />Leave</td>
							<td width="15%"><s:checkbox name="regularizedtimecard"
								id="regularizedtimecard" onclick="return unCheckShowAll();" />Regularized</td>
							<td width="10%"><input type="button" name="show"
								class="token" value="Show My TimeCard"
								onclick="return validateSearch();" /></td>
						</tr>
						<tr height="28">
							<td width="16%"></td>
							<td width="24%"></td>
							<td width="10%">&nbsp;</td>
							<td width="12%"><s:checkbox name="traveltimecard"
								id="traveltimecard" onclick="return unCheckShowAll();" />Travel</td>
							<td width="12%"><s:checkbox name="absenttimecard"
								id="absenttimecard" onclick="return unCheckShowAll();" />Absent</td>
							<td width="10%"></td>
							<td width="10%"><input type="button" name="showTeam"
								class="token" value="Show My Team's TimeCard"
								onclick="return validateSearchTeam();" /></td>

						</tr>
					</table>
					</td>
				</tr>

				<%
				if (mytimeCardTeamObj != null && mytimeCardTeamObj.length > 0) {
				%>
				<tr>
					<td valign="top" height="100%">
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="15%" class="mypageTh">Employee Name</td>
							<td width="10%" class="mypageTh" align="left">Date</td>
							<td width="10%" class="mypageTh" align="left">Day</td>
							<td width="10%" class="mypageTh" align="right">In Time</td>
							<td width="10%" class="mypageTh" align="right">Out Time</td>
							<td width="10%" class="mypageTh" align="right" nowrap="nowrap">Work
							Duration</td>
							<td width="15%" class="mypageTh" align="left">Shift</td>
							<td width="20%" class="mypageTh" align="left">Status</td>


						</tr>
						<%
									if (mytimeCardTeamObj != null && mytimeCardTeamObj.length > 0) {
									for (int i = 0; i < mytimeCardTeamObj.length; i++) {
						%>
						<tr>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][0]))%></td>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][1]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][2]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][3]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][4]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][5]))%></td>
							<td class="sortableTD" align="left"><a
								href="javascript:void(0);"
								onClick="callShiftDetailsWindow('MypageProcessManagerAlerts_getShiftDetails.action?shiftId=<%=Utility.checkNull(String.valueOf(mytimeCardTeamObj[i][11]))%>',event)"
								class="contlink"
								title="<%=Utility.checkNull(String
									.valueOf(mytimeCardTeamObj[i][10]))%>"
								target="main"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][10]))%></a></td>
							<td class="sortableTD" align="left" nowrap="nowrap"><%=Utility.checkNull(String
										.valueOf(mytimeCardTeamObj[i][6]))%></td>
						</tr>
						<%
								}
								}
						%>
					</table>
					</td>
				</tr>
				<%
				}
				%>
				<%
				if (mytimeCardObj != null && mytimeCardObj.length > 0) {
				%>
				<tr>
					<td valign="top" height="100%">
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="10%" class="mypageTh" align="left">Date</td>
							<td width="10%" class="mypageTh" align="left">Day</td>
							<td width="10%" class="mypageTh" align="right">In Time</td>
							<td width="10%" class="mypageTh" align="right">Out Time</td>
							<td width="10%" class="mypageTh" align="right" nowrap="nowrap">Work
							Duration</td>
							<td width="15%" class="mypageTh" align="left">Shift</td>
							<td width="20%" class="mypageTh" align="left">Status</td>
							<td width="15%" class="mypageTh">Action</td>

						</tr>
						<%
									if (mytimeCardObj != null && mytimeCardObj.length > 0) {
									for (int i = 0; i < mytimeCardObj.length; i++) {
						%>
						<tr>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
										.valueOf(mytimeCardObj[i][0]))%></td>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
										.valueOf(mytimeCardObj[i][1]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
										.valueOf(mytimeCardObj[i][2]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
										.valueOf(mytimeCardObj[i][3]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
										.valueOf(mytimeCardObj[i][4]))%></td>

							<td class="sortableTD" align="left"><a
								href="javascript:void(0);"
								onClick="callShiftDetailsWindow('MypageProcessManagerAlerts_getShiftDetails.action?shiftId=<%=Utility.checkNull(String.valueOf(mytimeCardObj[i][10]))%>',event)"
								class="contlink"
								title="<%=Utility.checkNull(String
									.valueOf(mytimeCardObj[i][9]))%>"
								target="main"><%=Utility.checkNull(String
										.valueOf(mytimeCardObj[i][9]))%></a></td>
							<td class="sortableTD" align="left" nowrap="nowrap"><%=Utility.checkNull(String
										.valueOf(mytimeCardObj[i][5]))%></td>
							<%
											if ((Utility.checkNull(String
											.valueOf(mytimeCardObj[i][5]))
											.equals("Present"))
											|| Utility.checkNull(
											String.valueOf(mytimeCardObj[i][5]))
											.equals("Leave")
											|| Utility.checkNull(
											String.valueOf(mytimeCardObj[i][5]))
											.equals("Travel")
											|| Utility.checkNull(
											String.valueOf(mytimeCardObj[i][5]))
											.equals("Application processing")
											|| Utility.checkNull(
											String.valueOf(mytimeCardObj[i][5]))
											.equals("Regularized")
											|| Utility.checkNull(
											String.valueOf(mytimeCardObj[i][5]))
											.equals("Half Day Leave")|| Utility.checkNull(
													String.valueOf(mytimeCardObj[i][5]))
													.equals("Half Day Leave"))

									{
							%>
							<td class="sortableTD" align="left">&nbsp;</td>

							<%
									}

									else if (String.valueOf(mytimeCardObj[i][11]).equals(
											"Application processing")) {
							%>
							<td class="sortableTD" align="left" nowrap="nowrap"><B>In
							process </B></td>

							<%
											} else if ((Utility.checkNull(String
											.valueOf(mytimeCardObj[i][5])))
											.equals("Holiday")
											|| Utility.checkNull(
											String.valueOf(mytimeCardObj[i][5]))
											.equals("Weekly Off")
											|| Utility.checkNull(
											String.valueOf(mytimeCardObj[i][5]))
											.equals("")

									) {
							%>
							<!--<td class="sortableTD" align="left" nowrap="nowrap" ><img  align="absmiddle"
								src="../pages/mypage/images/icons/ot.png" style="cursor: pointer;"
								title="Apply for ExtraWork"
								onclick="callMyAction('/extraWorkBenefits/ExtraWorkApplication_addNew.action?src=mytimecard&timeCardDate=<%=String.valueOf(mytimeCardObj[i][0])%>');" />
							</td>
							-->
							<%
							} else {
							%>


							<td class="sortableTD" align="left"><img align="absmiddle"
								src="../pages/mypage/images/icons/mtregularise.png"
								style="cursor: pointer;" title="Apply for Regulation"
								onclick="callMyAction('/leaves/Regularization_apply.action?src=mytimecard&timecardEmp=<%= String.valueOf(mytimeCardObj[i][13])%>&timeCardDate=<%=String.valueOf(mytimeCardObj[i][0])%>&timeCardInTime=<%=Utility.checkNull(String.valueOf(mytimeCardObj[i][2]))%>&timeCardOutTime=<%=Utility.checkNull(String.valueOf(mytimeCardObj[i][3]))%>');" />

							&nbsp;&nbsp; <img src="../pages/mypage/images/icons/mtleave.png"
								align="absmiddle" style="cursor: pointer;"
								title="Apply for Leave"
								onclick="callMyAction('/leaves/LeaveApplication_addNew.action?src=mytimecard&timecardEmp=<%= String.valueOf(mytimeCardObj[i][13])%>&timeCardDate=<%=String.valueOf(mytimeCardObj[i][0])%>');">
							&nbsp;&nbsp; <img src="../pages/mypage/images/icons/mtTravel.png"
								align="absmiddle" style="cursor: pointer;"
								title="Apply for  Travel"
								onclick="callMyAction('/TMS/TravelApplication_addNew.action?src=mytimecard&timecardEmp=<%= String.valueOf(mytimeCardObj[i][13])%>&timeCardDate=<%=String.valueOf(mytimeCardObj[i][0])%>');" />
							</td>
							<%
							}
							%>
						</tr>
						<%
								}
								}
						%>
						<%
						if (mytimeCardObj == null && mytimeCardTeamObj == null) {
						%>
						<tr align="center">
							<td colspan="6" class="sortableTD" width="100%"><font
								color="red">No Data to display</font></td>
						</tr>
						<%
						}
						%>
					</table>
					</td>
				</tr>
				<%
				}
				%>
			</table>
			</td>
		</tr>
	</table>
	</div>
	</div>
</s:form>
<script>
function callShiftDetailsWindow(action,e) {
		callPageDisplay(action,'500','300','false',e);	 
}


function callShiftDetails(){ 
	try{
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getShiftDetails.action';
		document.getElementById('paraFrm').submit();
	}catch(e){
		alert(e);
	}
}
 
function callMyActionLink(actionName){ 
 	//alert(actionName);
	document.getElementById('paraFrm').action ='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_getMenuSearch.action?actionName='+actionName;
	document.getElementById('paraFrm').submit();
}
function callMyAction(actionName){
	// alert("In my code:"+actionName);
	try{
		document.getElementById('paraFrm').action= '<%=request.getContextPath()%>'+actionName;
		document.getElementById("paraFrm").target="_self";
		document.getElementById('paraFrm').submit();
	}
	catch(e){
		alert("Exception:"+e);
	}	
}

function checkShowAll(){	
	if(document.getElementById("showalltimecard").checked){			
			document.getElementById("presenttimecard").checked="true";
			document.getElementById("absenttimecard").checked="true";
			document.getElementById("latetimecard").checked="true";
			document.getElementById("regularizedtimecard").checked="true";
			document.getElementById("leavetimecard").checked="true";
			document.getElementById("traveltimecard").checked="true";
			document.getElementById("halfDaytimecard").checked="true";
	}
	else{
			document.getElementById("presenttimecard").checked=false;
			document.getElementById("absenttimecard").checked=false;
			document.getElementById("latetimecard").checked=false;
			document.getElementById("regularizedtimecard").checked=false;
			document.getElementById("leavetimecard").checked=false;
			document.getElementById("traveltimecard").checked=false;
			document.getElementById("halfDaytimecard").checked=false;
		}
}


function unCheckShowAll(){
		if(document.getElementById("presenttimecard").checked){
			document.getElementById("showalltimecard").checked=false;
		}		
		if(document.getElementById("absenttimecard").checked){
			document.getElementById("showalltimecard").checked=false;
		}
		if(document.getElementById("latetimecard").checked){
			document.getElementById("showalltimecard").checked=false;
		}
		if(document.getElementById("regularizedtimecard").checked){
			document.getElementById("showalltimecard").checked=false;
		}
		if(document.getElementById("leavetimecard").checked){
			document.getElementById("showalltimecard").checked=false;
		}
		if(document.getElementById("traveltimecard").checked){
			document.getElementById("showalltimecard").checked=false;
		}
		if(document.getElementById("halfDaytimecard").checked){
			document.getElementById("showalltimecard").checked=false;
		}
}

function validateSearch(){
	try{
		var fromDate=document.getElementById('paraFrm_fromdate').value;
		var toDate=document.getElementById('paraFrm_todate').value;	
		var employeeCode=document.getElementById('paraFrm_empCodeMyPage').value;
		if(!fromDate==""){
			if(toDate == ""){
				alert("Please select/enter to date");
				return false;
			}
			var check= validateDate('paraFrm_fromdate', 'From Date');
			if(!check){
				return false;
			}
		}
		if(!toDate==""){
			if(fromDate == ""){
				alert("Please select/enter from date");
				return false;
			}
			var check= validateDate('paraFrm_todate', 'To Date');
			if(!check){
				return false;
			}
		}
		if(!fromDate=="" && !toDate==""){
			if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_todate')){
			 			return false;
			}
		}		
		var presentStatus="false";
		var absentStatus="false";
		var regularizedStatus ="false";
		var leaveStatus ="false";
		var traveltimecardStatus ="false";
		var halfDaytimecardStatus ="false";	
		var latetimecardStatus ="false";	 
		if(document.getElementById("presenttimecard").checked){
			presentStatus="true";
		}
		if(document.getElementById("absenttimecard").checked){
			absentStatus="true";
		}
		if(document.getElementById("regularizedtimecard").checked){
			regularizedStatus="true";
		}		
		if(document.getElementById("leavetimecard").checked){
			leaveStatus="true";
		}		
		if(document.getElementById("traveltimecard").checked){
			traveltimecardStatus="true";
		}
		if(document.getElementById("halfDaytimecard").checked){
			halfDaytimecardStatus="true";
		}		
		if(document.getElementById("latetimecard").checked){
			latetimecardStatus="true";
		}		
		var showall = "false";
		if(document.getElementById('showalltimecard').checked){
			showall="true";
		}		
		var t1=fromDate ;
        var t2=toDate ;
        var one_day=1000*60*60*24; 
        var x=t1.split("-");     
        var y=t2.split("-");
        var date1=new Date(x[2],(x[1]-1),x[0]);
        var date2=new Date(y[2],(y[1]-1),y[0])
        var month1=x[1]-1;
        var month2=y[1]-1;
        Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day)); 
		 if(Diff>31){
		 	alert("From date and to date difference should not be greater than 30 days.");
		 	return false ;
		 }
		document.getElementById('paraFrm').target = "_self";
	 	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action?myTeamFlag=false&isFromf9=null&showMyCardFlag=true&myTimecardemployeeCode='+employeeCode+'&myTimecardfromDate='+fromDate+'&myTimecardtoDate='+toDate+'&myTimecardPrstatus='+presentStatus+'&myTimecardAbstatus='+absentStatus+'&showallStr='+showall+'&regularizedStatusstr='+regularizedStatus+'&leaveStatusStr='+leaveStatus+'&traveltimecardStr='+traveltimecardStatus+'&halfDaytimecardStr='+halfDaytimecardStatus+'&latetimecardStatusStr='+latetimecardStatus;
		document.getElementById('paraFrm').submit();		
	}catch(e){
			alert("Error Timecard-----"+e);
	}		
}

function dateDifferenceEqual(fromDate, toDate, fieldName){
	var strDate1 = fromDate.split("-"); 
	var starttime = new Date(strDate1[2],strDate1[1]-1,strDate1[0]); 
	var strDate2 = toDate.split("-"); 
	var endtime = new Date(strDate2[2],strDate2[1]-1,strDate2[0]); 
	if(endtime < starttime) { 
		alert("to date should be greater or equal to  from date");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

function validateDate(fieldName, labName){
	var date = document.getElementById(fieldName).value;
	if(date=='') return true;
	var dateFormat = /^[0-9]{2}[-]?[0-9]{2}[-]?[0-9]{4}$/;
	
	if(!(date.match(dateFormat)) || date.length<10){
		alert(""+labName+" should be in DD-MM-YYYY format");
		document.getElementById(fieldName).focus();
		return false;
	}
	var dateArray = date.split("-");
	var day   = dateArray[0];
	var month = dateArray[1];
	var year  = dateArray[2];
	
	if(day<1 || day>31){
		alert("Day "+day+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(month<1 || month>12){
		alert("Month "+month+" is not a valid month");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if(day>29 && month==2){
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if((month==2 && day==29) && ((year%4!=0) || (year%100==0 && year%400!=0))){
		window.alert("29th of February is not a valid date in "+year);
		document.getElementById(fieldName).focus();
		return false;
	}
	
	if (day>30 && (month == 2 || month==4 || month==6 || month==9 || month==11)) {
		window.alert("Day "+day+" of the month "+month+" is not a valid day");
		document.getElementById(fieldName).focus();
		return false;
	}
	return true;
}

function Call(){
	try{
	//alert("befor action call " );
	callsF9(500,325,'MypageProcessManagerAlerts_f9actionEmp.action');
	//alert("after action call ");
	}
	catch(e){
		alert("Error:::::"+e);
	}
}

 onPageLoad();
	function onPageLoad(){	
		document.getElementById('mytimecard').className='contlink';	 
		//checkShowAll();
	}

function setLeaveAttendanceType(type){
		//document.getElementById('menuoremployee').value=type;		
		//document.getElementById('emp').className='';
		//document.getElementById('menu').className='';
		if(type=="mytimecard"){
			document.getElementById('attendanceregularization').className='';
			document.getElementById('annualattendance').className='';
			document.getElementById('myleaves').className='';
			document.getElementById('holidays').className='';		
			document.getElementById(type).className='contlink';
			document.getElementById('paraFrm').target = "_self";
	 		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action?showMyCardFlag=true';
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

function validateSearchTeam(){
 try{
	var fromDate=document.getElementById('paraFrm_fromdate').value;
	var toDate=document.getElementById('paraFrm_todate').value;	
	var employeeCode=document.getElementById('paraFrm_empCodeMyPage').value;
	document.getElementById('paraFrm_empCodeMyPage').value="";
	document.getElementById('paraFrm_empNameMyPage').value="";
	if(!fromDate==""){
			if(toDate == ""){
				alert("Please select/enter to date");
				return false;
			}
			var check= validateDate('paraFrm_fromdate', 'From Date');
			if(!check){
				return false;
			}
	}
	if(!toDate==""){
			if(fromDate == ""){
				alert("Please select/enter from date");
				return false;
			}
			var check= validateDate('paraFrm_todate', 'To Date');
			if(!check){
				return false;
			}
	}
	if(!fromDate=="" && !toDate==""){
			if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_todate')){
			 			return false;
			}
	}		
	var presentStatus="false";
	var absentStatus="false";
	var regularizedStatus ="false";
	var leaveStatus ="false";
	var traveltimecardStatus ="false";
	var halfDaytimecardStatus ="false";	
	var latetimecardStatus ="false";	 
	if(document.getElementById("presenttimecard").checked){
			presentStatus="true";
	}
	if(document.getElementById("absenttimecard").checked){
			absentStatus="true";
	}
	if(document.getElementById("regularizedtimecard").checked){
			regularizedStatus="true";
	}
	if(document.getElementById("leavetimecard").checked){
			leaveStatus="true";
	}
	if(document.getElementById("traveltimecard").checked){
			traveltimecardStatus="true";
	}
	if(document.getElementById("halfDaytimecard").checked){
			halfDaytimecardStatus="true";
	}
	if(document.getElementById("latetimecard").checked){
			latetimecardStatus="true";
	}
	var showall = "false";
	if(document.getElementById('showalltimecard').checked){
		showall="true";
	}		
	var t1=fromDate ;
    var t2=toDate ;
    var one_day=1000*60*60*24; 
    var x=t1.split("-");     
    var y=t2.split("-");
    var date1=new Date(x[2],(x[1]-1),x[0]);
    var date2=new Date(y[2],(y[1]-1),y[0])
    var month1=x[1]-1;
    var month2=y[1]-1;
    Diff=Math.ceil((date2.getTime()-date1.getTime())/(one_day));          
	if(Diff>7){
		 	alert("From date and to date difference should not be greater than 7 days.");
		 	return false ;
	}
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_mytimeCard.action?myTeamFlag=true&isFromf9=Y&showMyCardFlag=false&myTimecardemployeeCode='+employeeCode+'&myTimecardfromDate='+fromDate+'&myTimecardtoDate='+toDate+'&myTimecardPrstatus='+presentStatus+'&myTimecardAbstatus='+absentStatus+'&showallStr='+showall+'&regularizedStatusstr='+regularizedStatus+'&leaveStatusStr='+leaveStatus+'&traveltimecardStr='+traveltimecardStatus+'&halfDaytimecardStr='+halfDaytimecardStatus+'&latetimecardStatusStr='+latetimecardStatus;
	document.getElementById('paraFrm').submit();
	}catch(e){
			alert("Error Timecard-----"+e);
	}	
}

function callReport(type){
	document.getElementById('paraFrm_report').value=type;
	var fromDate=document.getElementById('paraFrm_fromdate').value;
	var toDate=document.getElementById('paraFrm_todate').value;
	var presentStatus="";
	var absentStatus="";
	var regularizedStatus ="";
	var leaveStatus ="";
	var traveltimecardStatus ="";
	var halfDaytimecardStatus ="";	
	var latetimecardStatus ="";	
	if(fromDate == ""){
			alert("Please select/enter from date");
			return false;
	}
	if(toDate == ""){
			alert("Please select/enter to date");
			return false;
	}	
	if(!fromDate==""){
			var check= validateDate('paraFrm_fromdate', 'From Date');
			if(!check){
				return false;
			}
	}	
	if(!toDate==""){
		var check= validateDate('paraFrm_todate', 'To Date');
		if(!check){
				return false;
		}
	}
	if(!fromDate=="" && !toDate==""){
			if(!dateDifferenceEqual(fromDate,toDate,'paraFrm_todate')){
			 			return false;
			}
	}	
	if(document.getElementById("presenttimecard").checked){
			presentStatus="PR";
	}
	if(document.getElementById("absenttimecard").checked){
			absentStatus="AB";
	}
	if(document.getElementById("regularizedtimecard").checked){
			regularizedStatus="RG";
	}
	if(document.getElementById("leavetimecard").checked){
			leaveStatus="LV";
	}
	if(document.getElementById("traveltimecard").checked){
			traveltimecardStatus="TR";
	}
	if(document.getElementById("halfDaytimecard").checked){
			halfDaytimecardStatus="HD";
	}
	if(document.getElementById("latetimecard").checked){
			latetimecardStatus="LT";
	}
	var showall = "";
	if(document.getElementById('showalltimecard').checked){
		showall="All";
	}			
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_myTimeCardReport.action?myTimecardfromDate='+fromDate+'&myTimecardtoDate='+toDate+'&myTimecardPrstatus='+presentStatus+'&myTimecardAbstatus='+absentStatus+'&showallStr='+showall+'&regularizedStatusstr='+regularizedStatus+'&leaveStatusStr='+leaveStatus+'&traveltimecardStr='+traveltimecardStatus+'&halfDaytimecardStr='+halfDaytimecardStatus+'&latetimecardStatusStr='+latetimecardStatus;
	document.getElementById('paraFrm').submit();
}

function callOut(){

	var date = new Date();
	var curr_day= date.getDate();
	var curr_month= (date.getMonth()+1);
	var curr_year= date.getFullYear(); 
	var currentDate= curr_day + "-" + curr_month + "-" + curr_year;
	
	var prev_date = new Date();
	prev_date.setDate(prev_date.getDate() -1);
	var prev_day = prev_date.getDate();
	var prev_month= (prev_date.getMonth()+1);
	var prev_year= prev_date.getFullYear(); 
	var previousDate= prev_day + "-" + prev_month + "-" + prev_year;
	
	var isAtt= document.getElementById('paraFrm_attendance').value		
	try{
		 if(isAtt=="true"){
				document.getElementById("paraFrm").target="_self";
				document.getElementById("paraFrm").action='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_dayOut.action?showMyCardFlag=true&isDayIn=true'; 
				document.getElementById("paraFrm").submit();
		
		  }else{
				conf=confirm("System could not find 'Day-in' record for today "+currentDate+ ".\nThis Day out will be marked for previous date "+previousDate+".\n Click 'Ok' to proceed. \n Click 'Cancel' to cancel this action.");
 	 			if(conf){
 	 				document.getElementById("paraFrm").target="_self";
					document.getElementById("paraFrm").action='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_dayOut.action?showMyCardFlag=true&isDayIn=false'; 
					document.getElementById("paraFrm").submit();
 	 			}
			}		
		}
		catch(e){
			alert("Error   "+e);
		}
}

function callDayIN(){
		try{		
		document.getElementById("paraFrm").target="_self";
		document.getElementById("paraFrm").action='<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_dayIN.action?showMyCardFlag=true'; 
		document.getElementById("paraFrm").submit();
		}
		catch(e){
			alert("Error   "+e);
		}
}

function chkFlag(){
	document.getElementById("presenttimecard").checked= false;
	document.getElementById("absenttimecard").checked=false;
	document.getElementById("regularizedtimecard").checked=false;
	document.getElementById("leavetimecard").checked=false;
	document.getElementById("traveltimecard").checked=false;
	document.getElementById("halfDaytimecard").checked=false;
	document.getElementById("latetimecard").checked=false;
	document.getElementById('showalltimecard').checked=false;
}
</script>