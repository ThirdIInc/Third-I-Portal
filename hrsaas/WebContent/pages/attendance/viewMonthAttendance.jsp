<!--Bhushan Dasare--><!--July 3, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>
<%@ include file="/pages/ApplicationStudio/configAuth/authorizationChecking.jsp" %>

<script type="text/javascript" src="../pages/attendance/AttendanceProcessAJAX.js"></script>

<script type="text/javascript">
	var empIds = new Array();
</script>

<s:form action="ViewMonthAttendance" name="ViewMonthAttendance" id="paraFrm" validate="true" target="main" theme="simple">
	<div id="progressBar" style="position:absolute; width:100%; left:0px; top:50px;">
		<table width="100%">
			<tr><td align="center"><img src="../pages/images/ajax-loader.gif"></td></tr>
			<tr>
				<td align="center" style="color:red; font-size:12px; font-weight:bold;">Processing ....</td>
			</tr>
			<tr>
				<td align="center" style="color:red; font-size:12px; font-weight:bold;">
					Please do not close the browser and do not click anywhere
				</td>
			</tr>
		</table>
	</div>
	
	<s:hidden name="deletedRecords" /><s:hidden name="recoveryFlowFlag" /><s:hidden name="deletedNo" /><s:hidden name="lockAttendance" /><s:hidden id="lockFlag" />
	
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="92%" class="txt"><strong class="text_head">View Monthly Attendance</strong></td>
						<td width="4%" valign="middle" align="right" class="txt">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="4%">
							<s:submit action="ViewMonthAttendance_viewAttendance" value=" View" cssClass="search" title="View processed attendance" 
							onclick="return viewAttendance();" />
						</td>
						<s:if test="showLockUnlock">
							<td width="4%">
								<s:if test="lockAttendance">
									<input type="button" id="btnLock" value=" Lock" class="lock" style="display:none;" title="Lock the attendance" 
									onclick="lockUnlock('true');" />
									
									<input type="button" id="btnUnlock" value=" Unlock" class="unlock" title="Unlock the attendance" 
									onclick="lockUnlock('false');" />
								</s:if>
								<s:else>
									<input type="button" id="btnLock" value=" Lock" class="lock" title="Lock the attendance" 
									onclick="lockUnlock('true');" />
									
									<input type="button" id="btnUnlock" value=" Unlock" class="unlock" style="display:none;" 
									title="Unlock the attendance" onclick="lockUnlock('false');" />
								</s:else>
							</td>
						</s:if>
						<td width="4%">
							<s:submit action="ViewMonthAttendance_reset" value=" Reset" cssClass="reset" title="Clear the fields" />
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="15%" nowrap="nowrap">
							<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:select name="month" headerKey="0" headerValue="--Select--" title="Select the month"
							list="#{'1':'January','2':'Febuary','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August',
							'9':'September','10':'October','11':'November','12':'December'}" />
						</td>
						<td width="10%"></td>
						<td width="15%" nowrap="nowrap">
							<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
							onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" />
						</td>
					</tr>		
					<tr>
						<td width="15%" nowrap="nowrap">
							<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:hidden name="divisionId" />
							
							<s:textfield name="divisionName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the division" onclick="callsF9(500,325,'MonthAttendanceProcess_f9Division.action');">
						</td>
						<td width="15%">
							<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
						</td>
						<td width="25%">
							<s:hidden name="branchId" />
							
							<s:textfield name="branchName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the branch" onclick="callsF9(500,325,'MonthAttendanceProcess_f9Branch.action');">
						</td>
					</tr>
					<tr>
						<td width="15%">
							<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :
						</td>
						<td width="25%">
							<s:hidden name="departmentId" />
							
							<s:textfield name="departmentName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the department" onclick="callsF9(500,325,'MonthAttendanceProcess_f9Department.action');">
						</td>
						<td width="15%">
							<label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :
						</td>
						<td width="25%">
							<s:hidden name="employeeTypeId" />
							
							<s:textfield name="employeeTypeName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the type of an employee" onclick="callsF9(500,325,'MonthAttendanceProcess_f9EmployeeType.action');">
						</td>
					</tr>
					<tr>
						<td width="15%">
							<label id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :
						</td>
						<td width="25%">
							<s:hidden name="payBillId" />
							
							<s:textfield name="payBillName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the pay bill group" onclick="callsF9(500,325,'MonthAttendanceProcess_f9PayBill.action');">
						</td>
						<td width="15%">
							<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label> :
						</td>
						<td width="25%">
							<s:hidden name="searchEmpId" /><s:hidden name="searchEmpToken" />
							
							<s:textfield name="searchEmpName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select an employee" onclick="return searchEmployee();">
						</td>
					</tr>
				</table>
				<%!	Object[][] viewAttendance = null;
				%>
				<%	viewAttendance = (Object[][])request.getAttribute("viewAttendance");
					if(viewAttendance != null && viewAttendance.length > 0) {
				%>		<table width="100%" class="formbg">
							<tr>
								<td width="100%" align="center">
									<label id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
									<s:hidden name="newEmpToken" /><s:hidden name="newEmpBranchName" /><s:hidden name="newEmpId" />
									<s:hidden name="newEmpDiv" />
									<s:textfield name="newEmpName" readonly="true" size="33" cssStyle="background-color: #F2F2F2;" />
									<img title="Select an employee" src="../pages/images/recruitment/search2.gif" class="iconImage" 
									height="18" align="absmiddle" width="18" onclick="addEmployee();">
									<input type="button" value="Add" title="Add selected employee" class="add" 
									onclick="addAttendance();" />
								</td>
							</tr>
						</table>
						<table id="tblAttendance" width="100%" class="formbg">
							<tr>
								<td align="center" class="formth" width="8%">
									<b><label id="employee.id" name="employee.id" ondblclick="callShowDiv(this);"><%=label.get("employee.id")%></label></b>
								</td>
								<td align="center" class="formth" width="32%">
									<b><label id="employeeName" name="employeeName" ondblclick="callShowDiv(this);"><%=label.get("employeeName")%></label></b>
								</td>
								<td align="center" class="formth" width="18%">
									<b><label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="attendanceDays" name="attendanceDays" ondblclick="callShowDiv(this);"><%=label.get("attendanceDays")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="weeklyOffs" name="weeklyOffs" ondblclick="callShowDiv(this);"><%=label.get("weeklyOffs")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="holidays" name="holidays" ondblclick="callShowDiv(this);"><%=label.get("holidays")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="lateMarks" name="lateMarks" ondblclick="callShowDiv(this);"><%=label.get("lateMarks")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="halfDays" name="halfDays" ondblclick="callShowDiv(this);"><%=label.get("halfDays")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="paidLeaves" name="paidLeaves" ondblclick="callShowDiv(this);"><%=label.get("paidLeaves")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="unPaidLeaves" name="unPaidLeaves" ondblclick="callShowDiv(this);"><%=label.get("unPaidLeaves")%></label></b>
								</td>
								<td align="center" class="formth" width="8%">
									<b><label id="salaryDays" name="salaryDays" ondblclick="callShowDiv(this);"><%=label.get("salaryDays")%></label></b>
								</td>
								<s:if test="recoveryFlowFlag">
								<td align="center" class="formth" width="8%">
									<b><label id="recoveryDays" name="recoveryDays" ondblclick="callShowDiv(this);"><%=label.get("recoveryDays")%></label></b>
								</td>
								</s:if>
								<td align="center" class="formth" width="8%"><b>Edit</b></td>
								<td align="center" class="formth" width="8%">
									<input type="button" class="token" value="Delete" title="Delete selected record(s)" 
									onclick="deleteAttendance();" /><br>
									
									<input type="checkbox" id="chkAllRecord" style="cursor: hand;" 
									title="Select all records to delete" onclick="selectAllRecords(this);" />
								</td>
							</tr>
				<%			int recordNo = 0;
							for(int i = 0; i < viewAttendance.length; i++) {
				%>				<tr name="trRecord" id="trRecord<%=recordNo%>">
									<td class="sortableTD" nowrap="nowrap">
										<s:hidden name="empId" id='<%="empId" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][0])%>" />
										
										<script type="text/javascript">
											empIds[<%=recordNo%>] = <%=String.valueOf(viewAttendance[i][0])%>
										</script>
										
										<s:hidden name="empToken" id='<%="empToken" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][1])%>" />
										
										<label id="lblEmpToken<%=recordNo%>"><%=String.valueOf(viewAttendance[i][1])%></label>
									</td>
									<td class="sortableTD" nowrap="nowrap">
										<s:hidden name="empName" id='<%="empName" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][2])%>" />
										
										<label id="lblEmpName<%=recordNo%>"><%=String.valueOf(viewAttendance[i][2])%></label>
									</td>
									<td class="sortableTD">
										<s:hidden name="empBranch" id='<%="empBranch" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][3])%>" />
										
										<label id="lblEmpBranch<%=recordNo%>"><%=String.valueOf(viewAttendance[i][3])%></label>
									</td>
									<td class="sortableTD" align="right" nowrap="nowrap">
										<s:hidden name="attendanceDays" id='<%="attendanceDays" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][4])%>" />
										
										<label id="lblAttendanceDays<%=recordNo%>"><%=String.valueOf(viewAttendance[i][4])%></label>
									</td>
									<td class="sortableTD" align="right">
										<s:hidden name="weeklyOffs" id='<%="weeklyOffs" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][5])%>" />
										
										<label id="lblWeeklyOffs<%=recordNo%>"><%=String.valueOf(viewAttendance[i][5])%></label>
									</td>
									<td class="sortableTD" align="right">
										<s:hidden name="holidays" id='<%="holidays" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][6])%>" />
										
										<label id="lblHolidays<%=recordNo%>"><%=String.valueOf(viewAttendance[i][6])%></label>
									</td>
									<td class="sortableTD" align="right" nowrap="nowrap">
										<s:hidden name="lateMarks" id='<%="lateMarks" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][7])%>" />
										
										<label id="lblLateMarks<%=recordNo%>"><%=String.valueOf(viewAttendance[i][7])%></label>
									</td>
									<td class="sortableTD" align="right">
										<s:hidden name="halfDays" id='<%="halfDays" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][8])%>" />
										
										<label id="lblHalfDays<%=recordNo%>"><%=String.valueOf(viewAttendance[i][8])%></label>
									</td>
									<td class="sortableTD" align="right" nowrap="nowrap">
										<s:hidden name="paidLeaves" id='<%="paidLeaves" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][9])%>" />
										
										<label id="lblPaidLeaves<%=recordNo%>"><%=String.valueOf(viewAttendance[i][9])%></label>
									</td>
									<td class="sortableTD" align="right" nowrap="nowrap">
										<s:hidden name="unPaidLeaves" id='<%="unPaidLeaves" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][10])%>" />
										
										<s:hidden name="systemUnPaidLeaves" id='<%="systemUnPaidLeaves" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][11])%>" />
										
										<label id="lblUnPaidLeaves<%=recordNo%>"><%=String.valueOf(viewAttendance[i][10])%></label>
									</td>
									<td class="sortableTD" align="right" nowrap="nowrap">
										<s:hidden name="salaryDays" id='<%="salaryDays" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][12])%>" />
										
										<label id="lblSalaryDays<%=recordNo%>"><%=String.valueOf(viewAttendance[i][12])%></label>
										
										<s:hidden name="totalAttendanceDays" id='<%="totalAttendanceDays" + recordNo%>' 
										value="<%=String.valueOf(viewAttendance[i][13])%>" />
									</td>
									<s:if test="recoveryFlowFlag">
									<td class="sortableTD" align="right" nowrap="nowrap">
										<label id="recoveryDays<%=recordNo%>"><%=String.valueOf(viewAttendance[i][16])%></label>
										
									</td>
									</s:if>
									<td class="sortableTD" valign="top" align="center">
										<img title="Click to edit the record" src="../pages/common/css/icons/edit.png" 
										style="cursor: hand;" onclick="editAttendance('<%=recordNo%>');">
									</td>
									<td class="sortableTD" valign="top" align="center">
										<input type="checkbox" name="chkRecord" id="chkRecord<%=recordNo%>" style="cursor: hand;" 
										title="Select a record to delete" 
										onclick="setDeletedRecords('<%=String.valueOf(viewAttendance[i][0])%>', '<%=recordNo%>', this);" />
									</td>
								</tr>
				<%				recordNo++;
							}
				%>		</table>
						<s:hidden name="attendanceCode" value="<%=String.valueOf(viewAttendance[0][14])%>" />
						<s:hidden name="status" />
						<s:hidden id="recordNo" value="<%=String.valueOf(recordNo-1)%>" />
				<%		viewAttendance = null;
					}
				%>
			</td>
		</tr>
		<tr>
			<td width="100%">
				<table width="100%">
					<tr>
						<td width="4%">
							<s:submit action="ViewMonthAttendance_viewAttendance" value=" View" cssClass="search" title="View processed attendance" 
							onclick="return viewAttendance();" />
						</td>
						<s:if test="showLockUnlock">
							<td width="4%">
								<s:if test="lockAttendance">
									<input type="button" id="btnLock" value=" Lock" class="lock" style="display:none;" title="Lock the attendance" 
									onclick="lockUnlock('true');" />
									<input type="button" id="btnUnlock" value=" Unlock" class="unlock" title="Unlock the attendance" 
									onclick="lockUnlock('false');" />
								</s:if>
								<s:else>
									<input type="button" id="btnLock" value=" Lock" class="lock" title="Lock the attendance" 
									onclick="lockUnlock('true');" />
									<input type="button" id="btnUnlock" value=" Unlock" class="unlock" style="display:none;" 
									title="Unlock the attendance" onclick="lockUnlock('false');" />
								</s:else>
							</td>
						</s:if>
						<td width="4%">
							<s:submit action="ViewMonthAttendance_reset" value=" Reset" cssClass="reset" title="Clear the fields" />
						</td>
						<td align="right"></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	processing('N');

	function processing(view) {
		if(view == 'Y') {
			document.getElementById('progressBar').style.visibility = 'visible';
			document.getElementById('progressBar').style.display = 'block';
		} else {
			document.getElementById('progressBar').style.visibility = 'hidden';
			document.getElementById('progressBar').style.display = 'none';
		}
	}
	
	function addEmployee() {
		var lockAttendance = document.getElementById('paraFrm_lockAttendance').value;
		if(lockAttendance == 'true') {
			alert('Attendance is already locked!');
		} else {
			callsF9(500,325,'ViewMonthAttendance_f9AddEmployee.action');
		}
	}
	
	function selectAllRecords(object) {
		if(object.checked) {
			for(var i = 0; i < empIds.length; i++) {
				if(document.getElementById('trRecord' + i).style.display != 'none') {
					document.getElementById('chkRecord' + i).checked = true;
					document.getElementById('paraFrm_deletedRecords').value += empIds[i] + ',';
					document.getElementById('paraFrm_deletedNo').value += i + ',';
				}
			}
		} else {
			for(var i = 0; i < empIds.length; i++) {
				document.getElementById('chkRecord' + i).checked = false;
			}
			document.getElementById('paraFrm_deletedRecords').value = '';
			document.getElementById('paraFrm_deletedNo').value = '';
		}
	}
	
	function lockUnlock(lockFlag) {
		var isLockAttendnace;
		
		document.getElementById('lockFlag').value = lockFlag;
		
		if(lockFlag == 'false') {
			doAuthorisation('1', 'Attendance', 'U');
		} else {
			isLockAttendnace = confirm('Do you really want to lock the attendance?');
			
			if(isLockAttendnace) {
				doUnlock();
			}
		}
	}
	
	function doUnlock() {
		var lockFlag = document.getElementById('lockFlag').value;
		retrieveLockURL('ViewMonthAttendance_lockAttendance.action?', 'ViewMonthAttendance', lockFlag);
	}
	
	function editAttendance(recordNo) {
		var empId = document.getElementById('empId' + recordNo).value;
		
		document.getElementById('trRecord' + recordNo).style.background = '#FDFBB0';
		
		var attendanceDetailsWindow = window.open('', 'attendanceDetailsWindow', 'width=1000, height=400, top=150, left=0, scrollbars=yes, resizable=yes, menubar=no, toolbar=no, status=yes');	 
		document.getElementById('paraFrm').target = "attendanceDetailsWindow";
		document.getElementById("paraFrm").action = 'ViewMonthAttendance_viewAttendanceDetails.action?empId=' + empId + '&recordNo=' + recordNo;
    	document.getElementById("paraFrm").submit();
    	document.getElementById('paraFrm').target = 'main';
	}
	
	function addAttendance() {
		var newEmpId = document.getElementById('paraFrm_newEmpId').value;
		if(newEmpId == '') {
			alert('Please select an employee');
		} else {
			var recordNo = eval(document.getElementById('recordNo').value) + 1;
			document.getElementById('recordNo').value = recordNo;
			
			empIds[recordNo] = document.getElementById('paraFrm_newEmpId').value;
			
			addRecord('ViewMonthAttendance_addEmployee.action?', 'ViewMonthAttendance');
		}
	}
	
	function setDeletedRecords(empId, recordNo, obj) {
		var deletedIds = document.getElementById('paraFrm_deletedRecords').value;
		var deletedNos = document.getElementById('paraFrm_deletedNo').value;
		
		if(obj.checked) {
			deletedIds += ',' + empId;
			deletedNos += ',' + recordNo;
		} else {
			var deletedRecords = document.getElementById('paraFrm_deletedRecords').value.split(',');
			var deletedNo = document.getElementById('paraFrm_deletedNo').value.split(',');
			deletedIds = '';
			deletedNos = '';
			for(var i = 0; i < deletedRecords.length; i++) {
				if(deletedRecords[i] != '') {
					if(empId != deletedRecords[i]) {
						deletedIds += ',' + deletedRecords[i];
						deletedNos += ',' + deletedNo[i];
					}
				}
			}
		}
		document.getElementById('paraFrm_deletedRecords').value = deletedIds;
		document.getElementById('paraFrm_deletedNo').value = deletedNos;
	}
	
	function deleteAttendance() {
		try {
			var lockAttendance = document.getElementById('paraFrm_lockAttendance').value;
			var deletedNo = document.getElementById('paraFrm_deletedNo').value.split(',');
			if(lockAttendance == 'true') {
				alert('Attendance is already locked!');
			} else {
				var isDeleteRecord = confirm('Do you want to delete the record?');
				if(isDeleteRecord) {
					processing('Y');
				
					var deletedRecords = document.getElementById('paraFrm_deletedRecords').value.split(',');
					
					var recordSelected = 0;
					var deletedIds = '';
					
					for (var i = 0; i < deletedNo.length; i++) {
						if(deletedNo[i] != '') {
							document.getElementById('trRecord' + deletedNo[i]).style.display = 'none';
							document.getElementById('chkRecord' + deletedNo[i]).checked = false;
							recordSelected += 1;
							if(recordSelected == 1) {
								deletedIds += deletedRecords[i];
							} else {
								deletedIds += ',' + deletedRecords[i];
							}
						}
					}
					
					if(recordSelected > 0) {
						document.getElementById('paraFrm_deletedRecords').value = deletedIds;
						deleteRecord('ViewMonthAttendance_deleteAttendance.action?', 'ViewMonthAttendance');
						document.getElementById('paraFrm_deletedRecords').value = '';
						document.getElementById('paraFrm_deletedNo').value = '';
					} else {
						alert('Please select atleast one record to delete');
					}
					processing('N');
				} else {
					document.getElementById('chkAllRecord').checked = false;
					
					for (var i = 0; i < deletedNo.length; i++) {
						if(deletedNo[i] != '') {
							document.getElementById('chkRecord' + deletedNo[i]).checked = false;
						}
					}
				}
			}
		} catch(e) {}
	}
	
	function viewAttendance() {
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		processing('Y');
		
		return true;
	}
	
	function searchEmployee() {
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		if(document.getElementById('paraFrm_divisionId').value == "") {
			alert("Please select the " + document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		callsF9(500,325,'ViewMonthAttendance_f9SearchEmployee.action');
		return true;
	}
</script>