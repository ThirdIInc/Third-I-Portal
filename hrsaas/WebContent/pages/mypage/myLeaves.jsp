<%@ taglib prefix="s" uri="/struts-tags"%>
<%@page import="org.paradyne.lib.Utility"%>

<%
	Object[][] leaveDataobj = null;
	try {
		leaveDataobj = (Object[][]) request
		.getAttribute("leaveDataobj");
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<s:form action="MypageProcessManagerAlerts" method="post"
	validate="true" id="paraFrm" theme="simple">
	<div style="float: left; width: 100%">
	<div style="float: left; width: 100%"><s:hidden name="report" />
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
							<td><a style="cursor: hand" id="mytimecard" class="contlink"
								title="My Timecard" href="javascript:void(0);"
								onclick="javascript:setLeaveAttendanceType('mytimecard');">
							My Timecard </a> <span><strong>|</strong></span> <a class="contlink"
								style="cursor: hand" id="attendanceregularization"
								href="javascript:void(0);" title="Attendance Regularization"
								onclick="javascript:setLeaveAttendanceType('attendanceregularization');">
							Attendance Regularization </a> <span><strong>|</strong></span> <a
								class="contlink" style="cursor: hand" id="annualattendance"
								href="javascript:void(0);" title="Annual Attendance"
								onclick="javascript:setLeaveAttendanceType('annualattendance');">
							Annual Attendance </a> <span><strong>|</strong></span> <a
								class="contlink" style="cursor: hand" id="myleaves"
								title="My Leaves" href="javascript:void(0);"
								onclick="checkLeaveFlag();javascript:setLeaveAttendanceType('myleaves');">
							My Leaves </a> <span><strong>|</strong></span> <a
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
						cellpadding="0">
						<tr>
							<td valign="top" colspan="7">
							<table width="100%" border="0" cellspacing="0" cellpadding="0"
								class="pageHeader">
								<tr>
									<td width="4%"><img
										src="../pages/mypage/images/icons/date.png" width="24"
										height="24" /></td>
									<td width="80%">Leaves Record</td>
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
							<td valign="top" colspan="7" height="28">
							<table width="98%" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<!--<td nowrap="nowrap">Please select the year to view leave
									records.</td>-->
									<td align="right"><img
										src="../pages/mypage/images/myleaveapplication_icon.gif"
										align="absmiddle" width="16" height="16" /><a
										href="javascript:void(0)" class="contlink" title="Apply Leave"
										onclick="callActionLink('/leaves/LeaveApplication_addNew.action?src=myLeaves');"><b>Apply
									Leave </b></a></td>
								</tr>
							</table>
							</td>
						</tr>


						<tr height="28">
							<td width="16%" colspan="1">Employee : <s:hidden
								name="leaveEmpCode" /></td>
							<td colspan="6"><s:hidden name="leaveEmpToken"
								value="%{leaveEmpToken}" /><s:textfield name="leaveEmpName"
								size="50" value="%{leaveEmpName}" theme="simple" readonly="true" />
							<img src="../pages/images/recruitment/search2.gif" height="18"
								class="iconImage" align="absmiddle" width="18"
								onclick="javascript:callEmpF9();"> <s:if
								test="%{bean.generalFlag}">
							</s:if> <s:else>
								<!--

							<input style="cursor: hand;" name="Submit3" type="button"
								align="absmiddle" class="button" value="..." onclick="Call();"
								id="ctrlHide" />
								
								-->
							</s:else></td>
						</tr>
						<tr height="28">
							<td width="16%"><label id="fromDate">From Date</label><font
								color="red">*</font>:</td>
							<td width="5%"><%-- <s:textfield name="leaveYear" maxlength="4" onkeypress="return numbersOnly();"/> --%>

							<s:textfield size="15" theme="simple" id="paraFrm_fromLeaveDate" name="fromLeaveDate" onkeypress="return numbersWithHiphen();" />
							 <s:a href="javascript:NewCal('paraFrm_fromLeaveDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif" height="16"
									align="absmiddle" width="16" border="0">
							</s:a></td>
							<td width="12%" align="right">Status Filter :</td>
							<td width="13%"><s:checkbox name="showallLeave"
								id="showallLeave" onclick="checkShowAll();" /> Show All</td>
							<td width="13%"><s:checkbox name="appliedLeave"
								id="appliedLeave" onclick="return unCheckShowAll();" />Applied</td>
							<td width="15%"><s:checkbox name="approvedLeave"
								id="approvedLeave" onclick="return unCheckShowAll();" />Approved
							</td>
							<td width="26%"><input type="button" name="showbtn"
								value="Show My Leave" class="token" onclick="showFunction();"></td>
						</tr>
						<tr height="28">
							<td width="16%"><label id="toDate">To Date</label><font
								color="red">*</font>:</td>
							<td width="18%"><s:textfield size="15" theme="simple"
								name="toLeaveDate" id="paraFrm_toLeaveDate" onkeypress="return numbersWithHiphen();"/> <s:a
								href="javascript:NewCal('paraFrm_toLeaveDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif" height="16"
									align="absmiddle" width="16" border="0">
							</s:a></td>
							<td width="12%">&nbsp;</td>
							<td width="12%">&nbsp;</td>
							<td width="12%"><s:checkbox name="rejectedLeave"
								id="rejectedLeave" onclick="return unCheckShowAll();" />
							Rejected</td>
							<td width="14%"><s:checkbox name="cancelledLeave"
								id="cancelledLeave" onclick="return unCheckShowAll();" />Cancelled
							</td>
							<td width="26%"><input type="button" name="showTeamBtn"
								value="Show My Team's Leave" class="token"
								onclick="showTeamFunction();"></td>

						</tr>
					</table>
					</td>
				</tr>
				<tr>
					<td valign="top" height="100%">
					<table width="100%" cellspacing="2" cellpadding="2" border="0">
						<tr>
							<td width="20%" class="formth" nowrap="nowrap">Employee Name</td>
							<td width="10%" class="mypageTh" nowrap="nowrap">From Date</td>
							<td width="10%" class="mypageTh" nowrap="nowrap">To Date</td>
							<td width="10%" class="mypageTh" nowrap="nowrap">Leave Days</td>
							<td width="10%" class="mypageTh" nowrap="nowrap">Leave Type</td>
							<td width="10%" class="mypageTh">Applied Date</td>
							<td width="20%" class="mypageTh" nowrap="nowrap">Authorized
							By</td>
							<td width="10%" class="mypageTh" nowrap="nowrap">Status</td>
						</tr>

						<%
								if (leaveDataobj != null && leaveDataobj.length > 0) {
								for (int i = 0; i < leaveDataobj.length; i++) {
						%>
						<tr>
							<td class="sortableTD" align="left" nowrap="nowrap"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][0]))%></td>
							<td class="sortableTD" align="left" nowrap="nowrap"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][1]))%></td>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][2]))%></td>
							<td class="sortableTD" align="right"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][3]))%></td>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][4]))%></td>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][5]))%></td>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][6]))%></td>
							<td class="sortableTD" align="left"><%=Utility.checkNull(String
									.valueOf(leaveDataobj[i][7]))%></td>
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
	</div>
</s:form>
<script>

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

function callActionLink(actionName){ 
  try{
		document.getElementById('paraFrm').action= '<%=request.getContextPath()%>'+actionName;
		document.getElementById("paraFrm").target="_self";
		document.getElementById('paraFrm').submit();
	}
	catch(e){
		alert("Exception:"+e);
	}
}
	onLoad();
	function onLoad(){
		document.getElementById('myleaves').className='contlink';
		//checkShowAll();
	}

function Call(){
	try{
		callsF9(500,325,'MypageProcessManagerAlerts_f9actionEmpMyLeaves.action');
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

function showFunction(){
	//alert("Show");
	try{	
		var appliedStatus ="false";
	 	var approvedStatus ="false";
	 	var rejectedStatus ="false";
	 	var cancelledStatus ="false";	 	 
	 	var leaveEmpCode = document.getElementById('paraFrm_leaveEmpCode').value;
		//var leaveYear = document.getElementById('paraFrm_leaveYear').value;
	 	var fromleaveYear = document.getElementById('paraFrm_fromLeaveDate').value;
	 	var toleaveYear = document.getElementById('paraFrm_toLeaveDate').value;	 
		/*
		if(trim(document.getElementById('paraFrm_leaveYear').value)==""){
	 		alert("Please Enter Year");
	 		return false;
		}*/
		if(trim(fromleaveYear)==""){
	 		alert("Please select/enter From Date");
	 		return false;
	 	}
	 	if(trim(toleaveYear)==""){
	 		alert("Please select/enter To Date");
	 		return false;
	 	}	 
	 	if(!validateDate('paraFrm_fromLeaveDate', 'From Date')){
			return false;
	 	}	
	 	if(!validateDate('paraFrm_toLeaveDate', 'To Date')){
			return false;
	 	}	 
	 	if(dateDifferenceEqual(fromleaveYear, toleaveYear, 'paraFrm_toLeaveDate', 'fromDate', 'toDate' )){		 	
	 	} else {
	 		return false;
	 	}	 
		if(document.getElementById('appliedLeave').checked){
			appliedStatus="true";
		}	
		if(document.getElementById('approvedLeave').checked){
			approvedStatus="true";
		}	
		if(document.getElementById('rejectedLeave').checked){
			rejectedStatus="true";
		}	
		if(document.getElementById('cancelledLeave').checked){
			cancelledStatus="true";
		}
		var showallLeaveStatus = document.getElementById('showallLeave').checked;
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_myLeaves.action?leaveEmpCodeStr='
		+leaveEmpCode+'&showLeaveMyFlag=true&showLeaveMyTeamFlag=false&fromLeaveDate='
		+fromleaveYear+'&toLeaveDate='+ toleaveYear+'&appliedStatus='+appliedStatus+'&approvedStatus='+approvedStatus+'&rejectedStatus='+rejectedStatus+'&cancelledStatus='+cancelledStatus+'&showallLeaveStatus='+showallLeaveStatus;		
		document.getElementById('paraFrm').submit();
	}
	catch(e){
		 alert("Error leave--"+e);
	} 
 }

function unCheckShowAll(){
		if(document.getElementById("appliedLeave").checked){
			document.getElementById("showallLeave").checked=false;
		}		
		if(document.getElementById("approvedLeave").checked){
			document.getElementById("showallLeave").checked=false;
		}
		if(document.getElementById("rejectedLeave").checked){
			document.getElementById("showallLeave").checked=false;
		}
		if(document.getElementById("cancelledLeave").checked){
			document.getElementById("showallLeave").checked=false;
		}			
 }

function checkShowAll(){
	if(document.getElementById("showallLeave").checked){			
			document.getElementById("appliedLeave").checked="true";
			document.getElementById("approvedLeave").checked="true";
			document.getElementById("rejectedLeave").checked="true";
			document.getElementById("cancelledLeave").checked="true";					
		}
		else{		 
			document.getElementById("appliedLeave").checked=false;
			document.getElementById("approvedLeave").checked=false;
			document.getElementById("rejectedLeave").checked=false;	
			document.getElementById("cancelledLeave").checked=false;				 
		}
}

function showTeamFunction(){
	try{	
	 var appliedStatus ="false";
	 var approvedStatus ="false";
	 var rejectedStatus ="false";
	 var cancelledStatus ="false";	 	 
	 var leaveEmpCode = document.getElementById('paraFrm_leaveEmpCode').value;
	 var fromleaveYear = document.getElementById('paraFrm_fromLeaveDate').value;
	 var toleaveYear = document.getElementById('paraFrm_toLeaveDate').value;	 
	 document.getElementById('paraFrm_leaveEmpCode').value="";
	 document.getElementById('paraFrm_leaveEmpName').value="";
	 if(trim(fromleaveYear)==""){
	 	alert("Please select/enter From Date");
	 	return false;
	 }
	 if(trim(toleaveYear)==""){
	 	alert("Please select/enter To Date");
	 	return false;
	 }	 	 
	 if(!validateDate('paraFrm_fromLeaveDate', 'From Date')){
		return false;
	 }	
	 if(!validateDate('paraFrm_toLeaveDate', 'To Date')){
		return false;
	 }	 
	 if(dateDifferenceEqual(fromleaveYear, toleaveYear, 'paraFrm_toLeaveDate', 'fromDate', 'toDate' )){	 
	 } else {
	 	return false;
	 }	 
	if(document.getElementById('appliedLeave').checked){
		appliedStatus="true";
	}
	if(document.getElementById('approvedLeave').checked){
		approvedStatus="true";
	}
	if(document.getElementById('rejectedLeave').checked){
		rejectedStatus="true";
	}
	if(document.getElementById('cancelledLeave').checked){
		cancelledStatus="true";
	}
	var showallLeaveStatus = document.getElementById('showallLeave').checked;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_myLeaves.action?leaveEmpCodeStr='
		+leaveEmpCode+'&showLeaveMyFlag=false&showLeaveMyTeamFlag=true&isFromf9=Y&myTmLeaveFlg=true&fromLeaveDate='
		+fromleaveYear+'&toLeaveDate='+ toleaveYear+'&appliedStatus='+appliedStatus+'&approvedStatus='+approvedStatus+'&rejectedStatus='
		+rejectedStatus+'&cancelledStatus='+cancelledStatus+'&showallLeaveStatus='+showallLeaveStatus;		
	document.getElementById('paraFrm').submit();
	}
	catch(e){
		 alert("Error leave--"+e);
	}
}

function callReport(type){
	document.getElementById('paraFrm_report').value=type;
	var appliedStatus ="";
	var approvedStatus ="";
	var rejectedStatus ="";
	var cancelledStatus ="";	
	/*var leaveYear=document.getElementById('paraFrm_leaveYear').value;
	if(trim(document.getElementById('paraFrm_leaveYear').value)==""){
	 	alert("Please Enter Year");
	 	return false;
	}
	*/	
	var fromleaveYear = document.getElementById('paraFrm_fromLeaveDate').value;
	var toleaveYear = document.getElementById('paraFrm_toLeaveDate').value;
	 if(trim(fromleaveYear)==""){
	 	alert("Please select/enter From Date");
	 	return false;
	 }
	 if(trim(toleaveYear)==""){
	 	alert("Please select/enter To Date");
	 	return false;
	 }
	 if(!validateDate("paraFrm_fromLeaveDate", "From Date")){
		return false;
	 }
	 if(!validateDate("paraFrm_toLeaveDate", "To Date")){
		return false;
	 }	 
	 if(dateDifferenceEqual(fromleaveYear, toleaveYear, 'paraFrm_toLeaveDate', 'fromDate', 'toDate' )){	 
	 } else {
	 	return false;
	 }	 
	if(document.getElementById('appliedLeave').checked){
		appliedStatus="P";
	}
	if(document.getElementById('approvedLeave').checked){
		approvedStatus="A";
	}
	if(document.getElementById('rejectedLeave').checked){
		rejectedStatus="R";
	}
	if(document.getElementById('cancelledLeave').checked){
		cancelledStatus="N";
	}
	var showallLeaveStatus = document.getElementById('showallLeave').checked;
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/mypage/MypageProcessManagerAlerts_myLeaveReport.action?&fromLeaveDate='
		+fromleaveYear+'&toLeaveDate='+ toleaveYear +'&appliedStatus='+appliedStatus+'&approvedStatus='+approvedStatus+'&rejectedStatus='+rejectedStatus+'&cancelledStatus='+cancelledStatus+'&showallLeaveStatus='+showallLeaveStatus;
	document.getElementById('paraFrm').submit();
 }

function callEmpF9(){
	var appliedStatus ="false";
	var approvedStatus ="false";
	var rejectedStatus ="false";
	var cancelledStatus ="false";	
	var showallLeaveStatus = document.getElementById('showallLeave').checked;
	if(document.getElementById('appliedLeave').checked){
		appliedStatus="true";
	}
	if(document.getElementById('approvedLeave').checked){
		approvedStatus="true";
	}
	if(document.getElementById('rejectedLeave').checked){
		rejectedStatus="true";
	}
	if(document.getElementById('cancelledLeave').checked){
		cancelledStatus="true";
	}
	var url ='MypageProcessManagerAlerts_f9TeamLeave.action?'
			+ 'appliedStatus='+appliedStatus
			+ '&approvedStatus='+approvedStatus
			+ '&rejectedStatus='+rejectedStatus
			+ '&cancelledStatus='+cancelledStatus
			+ '&showallLeaveStatus='+showallLeaveStatus;	 
    callsF9(500,325,url);
 }

function checkLeaveFlag(){
 	document.getElementById('appliedLeave').checked=false;
 	document.getElementById('approvedLeave').checked=false;
 	document.getElementById('rejectedLeave').checked=false;
 	document.getElementById('cancelledLeave').checked=false;
 	document.getElementById('showallLeave').checked=false;
}
</script>