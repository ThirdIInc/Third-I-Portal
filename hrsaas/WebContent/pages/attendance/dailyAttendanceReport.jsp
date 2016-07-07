<%--Bhushan--%>
<%--Apr 1, 2008--%>
<!-- Nilesh Dhandare 15th Feb 2012-->
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="DailyAttendanceReport" name="DailyAttendanceReport"
	id="paraFrm" theme="simple">
	<s:hidden name="typeFlag" />
	<s:hidden name="payBillFlag" />
	<s:hidden name="brnFlag" />
	<s:hidden name="deptFlag" />
	<s:hidden name="divFlag" />
	<table width="100%" class="formbg" align="right">
		<tr>
			<td>
			<table width="100%" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Daily
					Attendance Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<!-- Added by nilesh new for report -->
		<tr>
			<td>
			<table width="100%" border="0" id='topButtonTable'>
				<tr valign="middle">
					<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle"> Reset </a>&nbsp;</td>
					<td width="100%"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>


		<!-- Ended by nilesh new for report -->

		<tr>
			<td>
			<div name="htmlReport" id='reportDiv'
				style="background-color: #FFFFFF; height: 400; width: 100%; display: none; border-top: 1px #cccccc solid;">
			<iframe id="reportFrame" frameborder="0" onload=alertsize();
				style="vertical-align: top; float: left; border: 0px solid;"
				allowtransparency="yes" src="../pages/common/loading.jsp"
				scrolling="auto" marginwidth="0" marginheight="0" vspace="0"
				name="htmlReport" width="100%" height="200"></iframe></div>
			</td>

		</tr>

		<tr>
			<td>
			<table width="100% " class="formbg" id="reportBodyTable" border="0"
				id="reportBodyTable">
				<tr>
					<td class="formhead" colspan="6"><strong class="forminnerhead"><label
						id="attendence.report" name="attendence.report"
						ondblclick="callShowDiv(this);"><%=label.get("attendence.report")%></label></strong><s:hidden
						name="vCode" value="%{vCode}" /></td>
						
				</tr>
				<tr>
					<td>&nbsp;&nbsp;</td>
						
				</tr>
				
				<tr>
					<td width="15%"><label id="frmdate" name="frmdate"
						ondblclick="callShowDiv(this);"><%=label.get("frmdate")%></label><font
						color="red">*</font>:</td>
					<td width="20%"><s:textfield size="10" name="fromDate"
						maxlength="10" onkeypress="return numbersWithHiphen();" /> <s:a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18">
					</s:a></td>

					<td width="15%"><label id="todate" name="todate"
						ondblclick="callShowDiv(this);"><%=label.get("todate")%></label><font
						color="red">*</font>:</td>
					<td width="50%"><s:textfield size="10" name="toDate"
						maxlength="10" onkeypress="return numbersWithHiphen();" /> <s:a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="18" align="absmiddle" width="18">
					</s:a></td>
				</tr>
				<s:if test="%{generalFlag}">
					<tr>
					<td width="15%"><label id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font>:</td>
					<td width="25%"><s:hidden name="divCode" /><s:textfield
							name="divName" size="25" readonly="true" /></td>
					</tr>
					<tr>
						<td width="15%"><label id="employee" name="employee"
							ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
						<td width="45%"><s:hidden name="empCode" /><s:hidden
							name="empToken" /> <s:textfield name="empName" readonly="true"
							size="50" cssStyle="background-color: #F2F2F2;" /></td>
					</tr>
				</s:if>
			</table>
			</td>
		</tr>

		<s:else>
			<tr>
				<td colspan="2">
				<table width="100% " class="formbg" id="reportBodyTable" border="0">
					<tr>
						<td colspan="1" width="15%"><label id="division"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						<font color="red">*</font>:</td>
						<td colspan="1" width="85%"><s:hidden name="divCode" /> <s:textarea
							name="divName" cols="100" rows="1" readonly="true" /> <img
							src="../pages/images/search2.gif" class="iconImage" height="16"
							align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_divName',200,250,'DailyAttendanceReport_f9Div.action',event,'false','','right')">
						</td>
					</tr>
					<tr>
						<td colspan="1" width="15%"><label id="branch" name="branch"
							ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
						<s:hidden name="brnCode" /></td>
						<td colspan="1" width="85%"><s:textarea name="brnName"
							cols="100" rows="1" readonly="true" /> <img
							src="../pages/images/search2.gif" class="iconImage" height="16"
							align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_brnName',200,250,'DailyAttendanceReport_f9Branch.action',event,'false','','right')">
						</td>
					</tr>
					<tr>
						<td width="15%"><label id="department" name="department"
							ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
						:</td>
						<s:hidden name="deptCode" />
						<td width="85%"><s:textarea name="deptName" readonly="true"
							cols="100" rows="1" /> <img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_deptName',200,250,'DailyAttendanceReport_f9Dept.action',event,'false','','right')">
						</td>
					</tr>
					<tr>
						<td colspan="1" width="15%"><label id="employee.type"
							name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
						: <s:hidden name="typeCode" /></td>
						<td colspan="1" width="85%"><s:textarea name="typeName"
							readonly="true" cols="100" rows="1" /><img
							src="../pages/images/search2.gif" class="iconImage" height="16"
							align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_typeName',200,250,'DailyAttendanceReport_f9EmpType.action',event,'false','','right')">

						</td>
					</tr>
					<tr>
						<td colspan="1" width="15%"><label id="pay.bill"
							name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
						: <s:hidden name="payBillNo" /></td>
						<td colspan="1" width="85%"><s:textarea name="payBillName"
							cols="100" rows="1" readonly="true" /><img
							src="../pages/images/search2.gif" class="iconImage" height="16"
							align="absmiddle" width="16"
							onclick="javascript:callDropdown('paraFrm_payBillName',200,250,'DailyAttendanceReport_f9PayBill.action',event,'false','','right')">

						&nbsp;</td>
					</tr>
					<tr>
						<td colspan="1" width="15%"><label id="employee"
							name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						:</td>
						<td colspan="1" width="85%"><s:hidden name="empCode" /><s:hidden
							name="empToken" /> <s:textfield name="empName" readonly="true"
							size="65" /><img src="../pages/images/search2.gif"
							class="iconImage" height="16" align="absmiddle" width="16"
							onclick="callSearch();"></td>
					</tr>
					<tr>
						<td colspan="1" width="15%"><label id="status1"
							name="status1" ondblclick="callShowDiv(this);"><%=label.get("status1")%></label>
						:</td>
						<td colspan="1" width="85%"><s:select name="srchStatus1"
							headerKey="1" headerValue="-------Select--------"
							list="#{'All':'All', 'Present':'Present', 'Absent':'Absent', 'Leave':'Leave', 'Late':'Late', 'Half Day':'Half Day', 'Regularized':'Regularized', 'Travel':'Travel','Half Day Leave':'Half Day Leave'}" />

						</td>
					</tr>
				</table>
				</td>
			</tr>
		</s:else>

		<!-- Added by nilesh for report -->
		<tr>
			<td>
			<table width="100%" id='topButtonTable'>
				<tr valign="middle">
					<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle"> Reset </a>&nbsp;</td>
					<td width="100%" align="left"><%@ include
						file="/pages/common/reportButtonPanelBottom.jsp"%></td>
				</tr>
			</table>
			</td>
		</tr>
		<!-- Ended by nilesh for report -->



		<s:hidden name="reportAction"
			value='DailyAttendanceReport_report.action' />
		<s:hidden name="report" />
	</table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<script type="text/javascript">

function callSearch()
{
	var divCode =document.getElementById('paraFrm_divCode').value;
	
	if(divCode=="")
	{
		alert("Please Select Division.");
		return false;
	}	
	
	else{
	callsF9(500,325,'DailyAttendanceReport_f9Emp.action')

	}
	
	return true ;
}

function callReport(type)
{
  if(!validateFields()){
     return false;
	}
 else 
 {
	document.getElementById('paraFrm_report').value=type;
	callReportCommon(type);
	}
 }
	function mailReportFun(type)
{

try{
		if(!validateFields()){
			return false;
		} 
		else {
			document.getElementById('paraFrm_report').value=type;
			document.getElementById('paraFrm').action='DailyAttendanceReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			}
	}		
	catch (e)
	 {
	 	alert(e);
	 }		
		
	}
	
function validateFields()
 {

	 var fromDateVar = trim(document.getElementById('paraFrm_fromDate').value);
	 var toDateVar = trim(document.getElementById('paraFrm_toDate').value);
			if(fromDateVar == "") {
				alert("Please select/enter "+document.getElementById('frmdate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
			}else { 
				if(!validateDate('paraFrm_fromDate',"frmdate")){
						document.getElementById('paraFrm_fromDate').focus();
						return false;   	
   					}
			}
			
			
			if(toDateVar == "") {
				alert("Please select/enter "+document.getElementById('todate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
			}else {			
				if(!validateDate('paraFrm_toDate',"todate")){
					document.getElementById('paraFrm_toDate').focus();
					return false;   	
   				}		
			}
  
   			if(!dateDifferenceEqual(fromDateVar, toDateVar, 'paraFrm_toDate', 'frmdate', 'todate')){
	      		document.getElementById('paraFrm_toDate').focus();
	      		return false;
   			} 
   			
   		var divCode = document.getElementById('paraFrm_divCode').value;	
   			if(divCode==""){
   			alert("Please select Division");
   			return false;
   			}
   			
   			
   			return true;	

	}	
	
	
	
	
function resetFun(){
	document.getElementById('paraFrm').target = "main";
	document.getElementById('paraFrm').action='DailyAttendanceReport_reset.action';
	document.getElementById('paraFrm').submit();
}

	function showReport()
	{
	
		var fromDateVar = trim(document.getElementById('paraFrm_fromDate').value);
			if(fromDateVar == "") {
				alert("Please enter "+document.getElementById('frmdate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_fromDate').focus();
				return false;
			}else { 
				if(!validateDate('paraFrm_fromDate',"frmdate")){
						document.getElementById('paraFrm_fromDate').focus();
						return false;   	
   					}
			}
			
			var toDateVar = trim(document.getElementById('paraFrm_toDate').value);
			if(toDateVar == "") {
				alert("Please enter "+document.getElementById('todate').innerHTML.toLowerCase());
				document.getElementById('paraFrm_toDate').focus();
				return false;
			}else {			
				if(!validateDate('paraFrm_toDate',"todate")){
					document.getElementById('paraFrm_toDate').focus();
					return false;   	
   				}		
			}
  
   			if(!dateDifferenceEqual(fromDateVar, toDateVar, 'paraFrm_toDate', 'frmdate', 'todate')){
	      		document.getElementById('paraFrm_toDate').focus();
	      		return false;
   			} 
		
		
		callReport('DailyAttendanceReport_report.action');
	}
</script>