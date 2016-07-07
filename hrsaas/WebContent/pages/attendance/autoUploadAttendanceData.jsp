<!--Bhushan Dasare--><!--Feb 25, 2010-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>

<s:form action="AutoUploadAttendance" name="AutoUploadAttendance" id="paraFrm" validate="true" target="main" theme="simple">
	<div id="overlay" align="center" style="z-index:3;position:absolute;width:950px;height:500px;margin:0px;left:0;top:0;background-color:#A7BEE2;background-image:url('images/grad.gif');filter:progid:DXImageTransform.Microsoft.alpha(opacity=15);-moz-opacity:.1;opacity:.1;"></div>
	<div id="progressBar" align="center" style="z-index:3;position:absolute;width:950px;">
		<table width="100%">
			<tr><td height="200"></td></tr>
			<tr><td align="center"><img src="../pages/images/ajax-loader.gif"></td></tr>
			<tr><td align="center"><span style="color:red;font-size:16px;font-weight:bold;z-index:1000px;">Processing ....</span></td></tr>
			<tr>
				<td align="center">
					<span style="color:red;font-size:16px;font-weight:bold;z-index:1000px;">Please do not close the browser and do not click anywhere</span>
				</td>
			</tr>
		</table>
	</div>
	<div id="confirmationDiv" style='position:absolute;z-index:3; 100px; height:150px; visibility: hidden; top: 200px; left: 150px;'></div>

	<s:hidden name="autoUploadID" /><s:hidden name="radioFlag" id="radioFlag" /><s:hidden name="jobScheduled" />
	
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td>
							<strong class="text_head"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Auto Upload Attendance</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="85%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
						<td width="15%" align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%" colspan="4">
							<strong class="forminnerhead">
								<label id="lbServerSett" name="lbServerSett" ondblclick="callShowDiv(this);"><%=label.get("lbServerSett")%></label>
							</strong>
						</td>
						<td></td><td></td><td></td>
					</tr>
					<tr>
						<td width="20%">
							<label id="lbDriver" name="lbDriver" ondblclick="callShowDiv(this);"><%=label.get("lbDriver")%></label> :<font color="red">*</font>
						</td>
						<td width="30%">
							<s:select name="driver" headerKey="" headerValue="--Select--" 
							list="#{'ORACLE' : 'Oracle', 'MSSQL' : 'MS SQL Server', 'MYSQL' : 'MY SQL'}" />
						</td>
						<td width="20%">
							<label id="lbServerName" name="lbServerName" ondblclick="callShowDiv(this);"><%=label.get("lbServerName")%></label> :<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="server" size="40" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="lbUserName" name="lbUserName" ondblclick="callShowDiv(this);"><%=label.get("lbUserName")%></label> :<font color="red">*</font>
						</td>
						<td width="30%">
							<s:textfield name="userName" size="40" />
						</td>
						<td width="20%">
							<label id="lbPassword" name="lbPassword" ondblclick="callShowDiv(this);"><%=label.get("lbPassword")%></label> :<font color="red">*</font>
						</td>
						<td>
							<s:password name="password" size="40" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="lbDatabase" name="lbDatabase" ondblclick="callShowDiv(this);"><%=label.get("lbDatabase")%></label> :<font color="red">*</font>
						</td>
						<td width="30%">
							<s:textfield name="database" size="40" theme="simple" />
						</td>
						<td width="20%">
							<label id="lbPortNo" name="lbPortNo" ondblclick="callShowDiv(this);"><%=label.get("lbPortNo")%></label> :<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="portNo" size="40" theme="simple" maxlength="10" onkeypress="return numbersOnly();" />
						</td>
					</tr>
					<tr>
						<td width="20%" valign="top" nowrap="nowrap">
							<label id="lbTableName" name="lbTableName" ondblclick="callShowDiv(this);"><%=label.get("lbTableName")%></label> :<font color="red">*</font>
						</td>
						<td>
							<s:textfield name="tableName" size="40" theme="simple" maxlength="80" />
						</td>
						<td></td>
						<td></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%">
							<strong class="forminnerhead">
								<label id="lbTblMap" name="lbTblMap" ondblclick="callShowDiv(this);"><%=label.get("lbTblMap")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table width="100%">
								<tr>
									<td width="20%">
										<label id="lbInOutFlag" name="lbInOutFlag" ondblclick="callShowDiv(this);"><%=label.get("lbInOutFlag")%></label> :
									</td>
									<td width="30%">
										<input type="radio" name="chkQl" value="inOutFlag" checked="checked" id="inOutFlag" 
										onclick="chkRadioButton(this);">
									</td>
									<td width="20%">
										<label id="lbOtFlag" name="lbOtFlag" ondblclick="callShowDiv(this);"><%=label.get("lbOtFlag")%></label> :
									</td>
									<td>
										<input type="radio" name="chkQl" value="oneTimeFlag" id="oneTimeFlag" onclick="chkRadioButton(this);">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%">
							<table width="100%">
								<tr>
									<td width="30%" class="formth">Column Type</td>
									<td width="30%" class="formth">Is Available</td>
									<td class="formth">Table Field</td>
								</tr>
								<tr>
									<td width="30%" class="sortableTD">Employee Number</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="empFlag" id="empFlag" onclick="return callEnableText('empFlag','empField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="empField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
								<tr>
									<td width="30%" class="sortableTD">Date</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="dateFlag" id="dateFlag" onclick="return callEnableText('dateFlag','dateField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="dateField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
								<tr>
									<td width="30%" class="sortableTD">Work Hours</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="wrkHoursFlag" id="wrkHoursFlag" onclick="return callEnableText('wrkHoursFlag','wrkHoursField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="wrkHoursField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
								<tr>
									<td width="30%" class="sortableTD">Shift</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="shiftFlag" id="shiftFlag" onclick="return callEnableText('shiftFlag','shiftField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="shiftField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
								<tr id="inOutDiv">
									<td width="30%" class="sortableTD">In Time</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="inTimeFlag" id="inTimeFlag" onclick="return callEnableText('inTimeFlag','inTimeField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="inTimeField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
								<tr id="inOutDiv1">
									<td width="30%" class="sortableTD">Out Time</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="outTimeFlag" id="outTimeFlag" onclick="return callEnableText('outTimeFlag','outTimeField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="outTimeField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
								<tr id="timeDiv">
									<td width="30%" class="sortableTD">Time</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="timeFlag" id="timeFlag" onclick="return callEnableText('timeFlag','timeField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="timeField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
								<tr id="timeDiv1">
									<td width="30%" class="sortableTD">I/O Flag</td>
									<td width="30%" class="sortableTD" align="center">
										<s:checkbox name="ioFlag" id="ioFlag" onclick="return callEnableText('ioFlag','ioField');" />
									</td>
									<td class="sortableTD">
										<s:textfield name="ioField" size="65" maxlength="80" readonly="true" 
										cssStyle="background-color: #F2F2F2;" />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td width="100%" colspan="4" align="center">
							<input type="button" value="Test Connection" class="token" onclick="testConnection();">
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="100%" colspan="5">
							<strong class="forminnerhead">
								<label id="lbSchSett" name="lbSchSett" ondblclick="callShowDiv(this);"><%=label.get("lbSchSett")%></label>
							</strong>
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="lbDuration" name="lbDuration" ondblclick="callShowDiv(this);"><%=label.get("lbDuration")%></label> :<font color="RED">*</font>
						</td>
						<td width="5%">
							<s:select name="jobDuration" headerKey="" headerValue="--Select--" onchange="getJobFields();"
							list="#{'Daily' : 'Daily', 'Weekly' : 'Weekly', 'Monthly' : 'Monthly'}" />
						</td>
						<td width="25%"></td>
						<td id="blank1" width="20%"></td>
						<td id="blank2"></td>
						<td id="jobDayOfWeek1" width="20%" style="display: none;">
							<label id="lbDayOfWeek" name="lbDayOfWeek" ondblclick="callShowDiv(this);"><%=label.get("lbDayOfWeek")%></label> :<font color="RED">*</font>
						</td>
						<td id="jobDayOfWeek2" style="display: none;">
							<s:select name="jobDayOfWeek" headerKey="" headerValue="--Select--" 
							list="#{'Sunday' : 'Sunday', 'Monday' : 'Monday', 'Tuesday' : 'Tuesday', 'Wednesday' : 'Wednesday', 'Thursday' : 'Thursday', 
							'Friday' : 'Friday', 'Saturday' : 'Saturday'}" />
						</td>
						<td id="jobDayOfMonth1" width="20%" style="display: none;">
							<label id="lbDayOfMonth" name="lbDayOfMonth" ondblclick="callShowDiv(this);"><%=label.get("lbDayOfMonth")%></label> :<font color="RED">*</font>
						</td>
						<td id="jobDayOfMonth2" style="display: none;">
							<s:textfield name="jobDayOfMonth" size="8" maxlength="2" cssStyle="text-align: right;" onkeypress="return numbersOnly();" 
							onblur="callValidateMonthDay();" />
						</td>
					</tr>
					<tr>
						<td width="20%">
							<label id="lbStartTime" name="lbStartTime" ondblclick="callShowDiv(this);"><%=label.get("lbStartTime")%></label> :<font color="RED">*</font>
						</td>
						<td width="5%">
							<s:textfield name="jobStartTime" size="9" maxlength="5" onkeypress="return numbersWithColon();" />
						</td>
						<td width="25%">HH24:MI</td>
						<td width="20%"></td>
						<td></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="100%"><jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	displayProcessing('false');

	function callEnableText(chechID, fieldID) {
		document.getElementById('paraFrm_' + fieldID).readOnly = 'true';
		document.getElementById('paraFrm_' + fieldID).style.backgroundColor = '#F2F2F2';
			
		if(document.getElementById(chechID).checked) {
			document.getElementById('paraFrm_' + fieldID).readOnly = '';
			document.getElementById('paraFrm_' + fieldID).style.backgroundColor = 'white';
			document.getElementById('paraFrm_' + fieldID).focus();
		} else {
			document.getElementById('paraFrm_' + fieldID).value = '';
		}
	}
			
	// CALL FUNCTION ONLOAD
	enableDivsOnLoad();
			
	function enableDivsOnLoad() {
		document.getElementById('inOutDiv').style.display = 'none';
		document.getElementById('inOutDiv1').style.display = 'none';
		
		document.getElementById('timeDiv').style.display = 'none';
		document.getElementById('timeDiv1').style.display = 'none';
		
		document.getElementById('inOutDiv').style.display = '';
		document.getElementById('inOutDiv1').style.display = '';
			
		if(document.getElementById('radioFlag').value == 'oneTimeFlag') {
			document.getElementById('oneTimeFlag').checked = true;
			document.getElementById('inOutFlag').checked = false;
			
			document.getElementById('timeDiv').style.display = '';
			document.getElementById('timeDiv1').style.display = '';
			
			document.getElementById('inOutDiv').style.display = 'none';
			document.getElementById('inOutDiv1').style.display = 'none';
		}
		
		enableColumns();
		
		enableJobFields();
	}
	
	function enableColumns() {
		if(document.getElementById('empFlag').checked) {
			document.getElementById('paraFrm_empField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_empField').readOnly = false;
		} else {
			document.getElementById('paraFrm_empField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_empField').readOnly = true;
		}
		
		if(document.getElementById('dateFlag').checked) {
			document.getElementById('paraFrm_dateField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_dateField').readOnly = false;
		} else {
			document.getElementById('paraFrm_dateField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_dateField').readOnly = true;
		}
		
		if(document.getElementById('wrkHoursFlag').checked) {
			document.getElementById('paraFrm_wrkHoursField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_wrkHoursField').readOnly = false;
		} else {
			document.getElementById('paraFrm_wrkHoursField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_wrkHoursField').readOnly = true;
		}
		
		if(document.getElementById('shiftFlag').checked) {
			document.getElementById('paraFrm_shiftField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_shiftField').readOnly = false;
		} else {
			document.getElementById('paraFrm_shiftField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_shiftField').readOnly = true;
		}
		
		if(document.getElementById('inTimeFlag').checked) {
			document.getElementById('paraFrm_inTimeField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_inTimeField').readOnly = false;
		} else {
			document.getElementById('paraFrm_inTimeField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_inTimeField').readOnly = true;
		}
		
		if(document.getElementById('outTimeFlag').checked) {
			document.getElementById('paraFrm_outTimeField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_outTimeField').readOnly = false;
		} else {
			document.getElementById('paraFrm_outTimeField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_outTimeField').readOnly = true;
		}
		
		if(document.getElementById('timeFlag').checked) {
			document.getElementById('paraFrm_timeField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_timeField').readOnly = false;
		} else {
			document.getElementById('paraFrm_timeField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_timeField').readOnly = true;
		}
		
		if(document.getElementById('ioFlag').checked) {
			document.getElementById('paraFrm_ioField').style.backgroundColor = 'white';
			document.getElementById('paraFrm_ioField').readOnly = false;
		} else {
			document.getElementById('paraFrm_ioField').style.backgroundColor = '#F2F2F2';
			document.getElementById('paraFrm_ioField').readOnly = true;
		}
	}

	function chkRadioButton(obj) {
		document.getElementById('inOutDiv').style.display = 'none';
		document.getElementById('timeDiv').style.display = 'none';
		
		document.getElementById('inOutDiv1').style.display = 'none';
		document.getElementById('timeDiv1').style.display = 'none';

		if(obj.value == 'oneTimeFlag') {		
			document.getElementById('timeDiv').style.display = '';
			document.getElementById('timeDiv1').style.display = '';
			document.getElementById('radioFlag').value = 'oneTimeFlag';
		} else { 
			document.getElementById('inOutDiv').style.display = '';
			document.getElementById('inOutDiv1').style.display = '';
			document.getElementById('radioFlag').value = '';
		}
	}

	function saveFun() {
		if(formValidation()) {
			document.getElementById("paraFrm").target = '_self';
			document.getElementById("paraFrm").action = 'AutoUploadAttendance_save.action'; 
	  		document.getElementById("paraFrm").submit();
		}
	}
	
	function validateTableFields() {
		if(document.getElementById('empFlag').checked && document.getElementById('paraFrm_empField').value == '') {
	  		alert('Please enter Table Field for Employee Number');
	  		document.getElementById('paraFrm_empField').focus();
	  		return false;
	  	}
	  		
	  	if(document.getElementById('dateFlag').checked && document.getElementById('paraFrm_dateField').value == '') {
	  		alert('Please enter Table Field for Date');
	  		document.getElementById('paraFrm_dateField').focus();
	  		return false;
	  	}
	  		
	  	if(document.getElementById('wrkHoursFlag').checked && document.getElementById('paraFrm_wrkHoursField').value == '') {
	  		alert('Please enter Table Field for Work Hours');
	  		document.getElementById('paraFrm_wrkHoursField').focus();
	  		return false;
	  	}
	  		
	  	if(document.getElementById('shiftFlag').checked && document.getElementById('paraFrm_shiftField').value == '') {
	  		alert('Please enter Table Field for Shift');
	  		document.getElementById('paraFrm_shiftField').focus();
	  		return false;
	  	}
	  		
	  	if(document.getElementById('inTimeFlag').checked && document.getElementById('paraFrm_inTimeField').value == '') {
	  		alert('Please enter Table Field for In Time');
	  		document.getElementById('paraFrm_inTimeField').focus();
	  		return false;
	  	}
	  		
	  	if(document.getElementById('outTimeFlag').checked && document.getElementById('paraFrm_outTimeField').value == '') {
	  		alert('Please enter Table Field for Out Time');
	  		document.getElementById('paraFrm_outTimeField').focus();
	  		return false;
	  	}
	  		
	  	if(document.getElementById('timeFlag').checked && document.getElementById('paraFrm_timeField').value == '') {
	  		alert('Please enter Table Field for Time');
	  		document.getElementById('paraFrm_timeField').focus();
	  		return false;
	  	}
	  		
	  	if(document.getElementById('ioFlag').checked && document.getElementById('paraFrm_ioField').value == '') {
	  		alert('Please enter Table Field for I/O Flag');
	  		document.getElementById('paraFrm_ioField').focus();
	  		return false;
	  	}
	  	
	  	return true;
	}
	
	function formValidation() {
		var fieldNames = ["paraFrm_driver", "paraFrm_server", "paraFrm_userName", "paraFrm_password", "paraFrm_database", "paraFrm_tableName"];
	  	var lableNames = ["lbDriver", "lbServerName", "lbUserName", "lbPassword", "lbDatabase", "lbTableName"];
	  	var flag = ["select", "enter", "enter", "enter", "enter", "enter"];
	  
	  	if(validateBlank(fieldNames, lableNames, flag) && validateTableFields()) {
	  		var jobDuration = document.getElementById('paraFrm_jobDuration').value;
			
			if(jobDuration == '') {
				alert("Please select the " + document.getElementById("lbDuration").innerHTML);
				document.getElementById('paraFrm_jobDuration').focus();
				return false;
			} else if(jobDuration == 'Weekly') {
				var jobDayOfWeek = document.getElementById('paraFrm_jobDayOfWeek').value;
				
				if(jobDayOfWeek == '') {
					alert("Please select the " + document.getElementById("lbDayOfWeek").innerHTML);
					document.getElementById('paraFrm_jobDayOfWeek').focus();
					return false;
				}
			} else if(jobDuration == 'Monthly') {
				var jobDayOfMonth = document.getElementById('paraFrm_jobDayOfMonth').value;
			
				if(jobDayOfMonth > 31 || jobDayOfMonth == "0" || jobDayOfMonth == '') {
					alert("Please enter the " + document.getElementById("lbDayOfMonth").innerHTML + " between 1 and 31.");
					document.getElementById('paraFrm_jobDayOfMonth').focus();
					return false;
				}
			}
			
			if(trim(document.getElementById('paraFrm_jobStartTime').value) == "") {
				alert("Please enter " + document.getElementById("lbStartTime").innerHTML);
				document.getElementById('paraFrm_jobStartTime').focus();
				return false;
			}
			
			if(trim(document.getElementById('paraFrm_jobStartTime').value) == "00:00") {
				alert(document.getElementById("lbStartTime").innerHTML + " has to be greater than 00:00");
				document.getElementById('paraFrm_jobStartTime').focus();
				return false;
			}
			
			if(!validateTime('paraFrm_jobStartTime', 'lbStartTime')) { return false; }
			
			return true;
		}
	}
	
	function backFun() { 
	  	document.getElementById("paraFrm").action = 'AutoUploadAttendance_input.action'; 
	  	document.getElementById("paraFrm").submit();	  	 
	}
	
	function editFun() {
		var jobScheduled = document.getElementById('paraFrm_jobScheduled').value;

		if(jobScheduled == 'true') {
			alert('Please stop the scheduler and try again.');
			return false;
		} else {
			enableColumns();
			return true;
		}	  	 
	}
	
	function resetFun() {
		document.getElementById("paraFrm").target = '_self';
		document.getElementById("paraFrm").action = 'AutoUploadAttendance_input.action';
	  	document.getElementById("paraFrm").submit();
	}
	
	function deleteFun() { 
		var con = confirm("Do you want to delete record ");
		
		if(con) {
			document.getElementById("paraFrm").action = 'AutoUploadAttendance_delete.action'; 
			document.getElementById("paraFrm").submit();	
		}	  		  	 
	}
	
	function displayProcessing(display) {
		if(display == 'true') {
			document.getElementById("overlay").style.visibility = "visible";
			document.getElementById("overlay").style.display = "block";
			document.getElementById("progressBar").style.visibility = "visible";
			document.getElementById("progressBar").style.display = "block";
		} else {
			document.getElementById("overlay").style.visibility = "hidden";
			document.getElementById("overlay").style.display = "none";
			document.getElementById("progressBar").style.visibility = "hidden";
		}
	}
	
	var reqForConnection;
	
	function testConnection() {
		var fieldNames = ["paraFrm_driver", "paraFrm_server", "paraFrm_userName", "paraFrm_password", "paraFrm_database", "paraFrm_portNo", 
		"paraFrm_tableName"];
	  	var lableNames = ["lbDriver", "lbServerName", "lbUserName", "lbPassword", "lbDatabase", "lbPortNo", "lbTableName"];
	  	var flag = ["select", "enter", "enter", "enter", "enter", "enter", "enter"];
	  
	  	if(validateBlank(fieldNames, lableNames, flag) && validateTableFields()) {
	  		displayProcessing('true');
	  		
	  		try {
				var url = 'AutoUploadAttendance_testConnection.action?' + getFieldValuesForConnetion() + '&' + Math.random();
			} catch(e) {
				alert(e);
			}
			if (window.XMLHttpRequest) { // Non-IE browsers
				reqForConnection = new XMLHttpRequest(); // XMLHttpRequest object is created
			    reqForConnection.onreadystatechange = processStateChangeForConnetion; // XMLHttpRequest object is configured with a callback function
			    try {
			    	/**
			    	 * open("HTTP method", "URL", syn/asyn), for asyn-true
			    	 * if false, send operations are synchronous, browser doesn't accept any input/output
			    	**/
			    	reqForConnection.open("GET", url, true);
			    } catch (e) {
					alert("Problem Communicating with Server\n"+e);
				}
				reqForConnection.send(null);
			} else if (window.ActiveXObject) { // IE 
				reqForConnection = new ActiveXObject("Microsoft.XMLHTTP");
			    if (reqForConnection) {
			    	reqForConnection.onreadystatechange = processStateChangeForConnetion;
			      	reqForConnection.open("GET", url, true);
			       	reqForConnection.send();
			    }
			}
	  	}
	}
	
	function getFieldValuesForConnetion() {
		var returnString = '';
		try {
			var formElements = document.forms['paraFrm'].elements;
			for (var i = 0; i < formElements.length; i++) {
	 			if(formElements[i].name == 'driver' || formElements[i].name == 'server' || formElements[i].name == 'userName' || 
	 			formElements[i].name == 'password' || formElements[i].name == 'database' || formElements[i].name == 'portNo' || 
	 			formElements[i].name == 'tableName' || formElements[i].name == 'empField' || formElements[i].name == 'inTimeField' || 
	 			formElements[i].name == 'outTimeField' || formElements[i].name == 'timeField' || formElements[i].name == 'ioField' || 
	 			formElements[i].name == 'wrkHoursField' || formElements[i].name == 'shiftField' || formElements[i].name == 'dateField') {
	 				returnString = returnString + "&" + escape(formElements[i].name) + "=" + escape(formElements[i].value);
	 			}
	 		}
		} catch(e) {
			alert(e);
		}
		return returnString;
	}
	
	function processStateChangeForConnetion() {
		// 0 = uninitialized, 1 = loading, 2 = loaded, 3 = interactive (some data has been returned)!
		if(reqForConnection.readyState == 4) { // Complete
			if (reqForConnection.status == 200) { // OK response
		    	//responseXML: XML document of data returned from the server
		    	var res = reqForConnection.responseText; // String version of data returned from the server
		    	
		    	displayProcessing('false');
		    	
		    	alert(res);
			}
			parent.frames[2].name = 'main';
		}
	}
	
	function enableJobFields() {
		var jobDuration = document.getElementById('paraFrm_jobDuration').value;

		if(jobDuration == '' || jobDuration == 'Daily') {
			document.getElementById('blank1').style.display = '';
			document.getElementById('blank2').style.display = '';
			document.getElementById('jobDayOfWeek1').style.display = 'none';
			document.getElementById('jobDayOfWeek2').style.display = 'none';
			document.getElementById('jobDayOfMonth1').style.display = 'none';
			document.getElementById('jobDayOfMonth2').style.display = 'none';
		} else if(jobDuration == 'Weekly') {
			document.getElementById('blank1').style.display = 'none';
			document.getElementById('blank2').style.display = 'none';
			document.getElementById('jobDayOfWeek1').style.display = '';
			document.getElementById('jobDayOfWeek2').style.display = '';
			document.getElementById('jobDayOfMonth1').style.display = 'none';
			document.getElementById('jobDayOfMonth2').style.display = 'none';
		} else if(jobDuration == 'Monthly') {
			document.getElementById('blank1').style.display = 'none';
			document.getElementById('blank2').style.display = 'none';
			document.getElementById('jobDayOfWeek1').style.display = 'none';
			document.getElementById('jobDayOfWeek2').style.display = 'none';
			document.getElementById('jobDayOfMonth1').style.display = '';
			document.getElementById('jobDayOfMonth2').style.display = '';
		}
	}
	
	function getJobFields() {
		enableJobFields();
		
		var jobDuration = document.getElementById('paraFrm_jobDuration').value;
		
		if(jobDuration == 'Weekly') {
			document.getElementById('paraFrm_jobDayOfWeek').focus();
		} else if(jobDuration == 'Monthly') {
			document.getElementById('paraFrm_jobDayOfMonth').focus();
		}
	
		document.getElementById('paraFrm_jobDayOfWeek').value = "";
		document.getElementById('paraFrm_jobDayOfMonth').value = "";
	}
	
	function startschedulingFun() {
		document.getElementById('paraFrm').target = '_self';
		document.getElementById('paraFrm').action = 'AutoUploadAttendance_startScheduling.action';
	  	document.getElementById('paraFrm').submit();
	}
	
	function stopschedulingFun() {
		var stopScheduler = confirm('Do you want to stop the scheduler?');
		
		if(stopScheduler) {
			document.getElementById('paraFrm').target = '_self';
			document.getElementById('paraFrm').action = 'AutoUploadAttendance_stopScheduling.action';
	  		document.getElementById('paraFrm').submit();
		}	
	}
</script>