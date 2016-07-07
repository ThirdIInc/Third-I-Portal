<!--Bhushan Dasare--><!--June 6, 2009-->

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp" %>

<s:form action="UploadMonthlyAttendance" name="UploadMonthlyAttendance" id="paraFrm" validate="true" target="main" theme="simple">	
    <s:hidden name="recoveryFlag" /> 
	
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
						<td width="92%" class="txt"><strong class="text_head">Upload Monthly Attendance</strong></td>
						<td width="4%" valign="middle" class="txt" align="right">
							<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
				   		<td> 
							<s:submit value=" Reset" cssClass="reset" action="UploadMonthlyAttendance_reset" title="Clear the fields" /> 
						</td>
						<td align="right"><font color="red">*</font> Indicates Required</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="15%">
							<label id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:select name="month" headerKey="0" headerValue="--Select--" title="Select the month"
							list="#{'1':'January','2':'Febuary','3':'March','4':'April','5':'May','6':'June','7':'July',
							'8':'August','9':'September','10':'October','11':'November','12':'December'}" /> 
						 </td>
						<td width="10%"></td>
						<td width="15%">
							<label id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label> :<font color="red">*</font>
						</td>
						<td width="25%">
							<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" title="Enter the year"
							onkeypress="return numbersOnly(event);" onblur="return checkYear('paraFrm_year', 'year');" />
						</td>
					</tr> 
					<tr>
						<td width="15%">
							<s:hidden name="divisionId" /><s:hidden name="divisionFlag" /> 
							<label id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :<font color="red">*</font>
						</td>
						<td width="25%" >
							<s:textfield name="divisionName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />								
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the division" onclick="callsF9(500,325,'UploadMonthlyAttendance_f9Division.action');">
						</td>
						<td width="15%">
							<s:hidden name="branchId" /><s:hidden name="branchFlag" />
							<label id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="branchName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the branch" onclick="callsF9(500,325,'UploadMonthlyAttendance_f9Branch.action');">
						</td>
					</tr> 
					<tr>
						<td width="15%">
							<s:hidden name="departmentId" /> 
							<label id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="departmentName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the department" onclick="callsF9(500,325,'UploadMonthlyAttendance_f9Department.action');">
						</td>
						<td width="15%">
							<s:hidden name="employeeTypeId" /><s:hidden name="employeeTypeFlag" />
							<label id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :
						</td>
						<td width="25%" >
							<s:textfield name="employeeTypeName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the type of an employee" onclick="callsF9(500,325,'UploadMonthlyAttendance_f9EmployeeType.action');">
						</td>
					</tr>   
					<tr>
						<td width="15%">
							<s:hidden name="payBillId" /><s:hidden name="payBillFlag" />
							<label id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="payBillName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
					 	</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select the pay bill group" onclick="callsF9(500,325,'UploadMonthlyAttendance_f9PayBill.action');">
						</td>
						<td width="15%">
							<s:hidden name="empSerachId" /><s:hidden name="empToken" />
							<label id="emp.Name" name="emp.Name" ondblclick="callShowDiv(this);"><%=label.get("emp.Name")%></label> :
						</td>
						<td width="25%">
							<s:textfield name="employeeName" readonly="true" size="40" cssStyle="background-color: #F2F2F2;" />
					 	</td>
						<td width="10%">
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" 
							width="18" title="Select an employee" onclick="callF9Employee();">
						</td>
					</tr>   
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">
					<tr>
						<td width="55%" align="right">
							<label id="downLoad.template" name="downLoad.template" ondblclick="callShowDiv(this);"><%=label.get("downLoad.template")%></label>:
						</td>
						<td>
							<s:submit value="Download" cssClass="token" action="UploadMonthlyAttendance_downLoadFile" 
							title="Download an template" onclick="return callDownLoad();" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg">  
					<tr>
						<td width="100%" align="center">
							<label id="upload.Name" name="upload.Name" ondblclick="callShowDiv(this);"><%=label.get("upload.Name")%></label> :<font color="red">*</font>
							
							<s:textfield name="uploadFileName" size="45" readonly="true" cssStyle="background-color: #F2F2F2;" />&nbsp;&nbsp;&nbsp;
							
							<input type="button" class="token" theme="simple" value="Select XLS File" title="Select XLS file" 
							onclick="uploadFile('uploadFileName');" />
						</td>
					</tr>
					<tr>
						<td width="100%" align="center">
							<s:submit cssClass="token" theme="simple" value="Upload" action="UploadMonthlyAttendance_uploadMonSheet" 
							title="Upload XLS file" onclick="return calAttendance();" /> 
						</td>
					</tr>
				</table>
				<%	String wrongEmp = (String) request.getAttribute("wrongEmptoken"); 
					if(wrongEmp != null && !wrongEmp.equals("")) { 
				%>		<table width="100%" class="formbg"><tr><td width="100%"><font color="red"><%=wrongEmp%></font></td></tr></table>
				<%	}	%>
	    	</td>
		</tr>
		<tr>
			<td>
				<table width="100%">
					<tr>
				   		<td> 
							<s:submit value=" Reset" cssClass="reset" action="UploadMonthlyAttendance_reset" title="Clear the fields" /> 
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</s:form>

<script>
	function callF9Employee() {
		if(document.getElementById('paraFrm_month').value == "0") {
			alert("Please select the " + document.getElementById('month').innerHTML.toLowerCase()); 
			document.getElementById('paraFrm_month').focus(); 
			return false;
		}
		if(document.getElementById('paraFrm_year').value == "") {
			alert("Please enter the " + document.getElementById('year').innerHTML.toLowerCase());
			document.getElementById('paraFrm_year').focus();
			return false;
		}
		callsF9(500,325,'UploadMonthlyAttendance_f9SeachEmployee.action');
	}
 
	function callDownLoad() {
    	if(document.getElementById('paraFrm_month').value == "0") {
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
		return true;
	}

	function uploadFile(fieldName) {
		var path = "images/<%=session.getAttribute("session_pool")%>/attendance";
		window.open('<%=request.getContextPath()%>/pages/common/uploadFile.jsp?path=' + path + '&field=' + 
		fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=50, left=100');
	}
	
 	function calAttendance() {  
		if(document.getElementById('paraFrm_month').value == "0") {
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
		if(document.getElementById('paraFrm_uploadFileName').value == "") {
				alert("Please select the " + document.getElementById('upload').innerHTML.toLowerCase());
				return false;
			}  
		return true;
	}
</script>