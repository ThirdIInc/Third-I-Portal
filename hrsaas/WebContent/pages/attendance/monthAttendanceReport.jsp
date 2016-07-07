<%--Bhushan--%><%--July 11, 2008--%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ include file="/pages/common/labelManagement.jsp"%>

<s:form action="MonthAttendanceReport" name="MonthAttendanceReport" id="paraFrm" theme="simple">
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
						<td width="4%">
							<strong class="text_head">
								<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" />
							</strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Monthly Attendance Report</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right">
								<img src="../pages/images/recruitment/help.gif" width="16" height="16" />
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
						src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
						align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;</td>
					<td width="100%"><%@ include
						file="/pages/common/reportButtonPanel.jsp"%>
					</td>
				</tr>
			</table>
			</td>
		</tr>
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
				<table width="100%"> 
					<tr>
						<!--<td>
							<input type="button" name="report" value="Show Report" class="token" onclick="return showReport();" />
							<s:submit cssClass="reset" action="MonthAttendanceReport_reset" theme="simple" value=" Reset"  />
						</td>
						--><td>
							<div align="right"><span class="style2"><font color="red">*</font></span>Indicates Required</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" class="formbg" border="0"  id="reportBodyTable">	
					<tr>
						<td width="100%" colspan="4">
							<strong class="forminnerhead">
								<label class="set" id="attendence.report" name="attendence.report" ondblclick="callShowDiv(this);"><%=label.get("attendence.report")%></label>
							</strong>
							<s:hidden name="vCode" value="%{vCode}" />
						</td>
					</tr>
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="month" name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label><font color="red">*</font>:
						</td>
						<td colspan="1" width="30%">
							<s:select name="month" headerKey="1" headerValue="--Select--"
							list="#{'1':'January','2':'Febuary','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
						</td>
						<td colspan="1" width="20%">
							<label class="set" id="year" name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label><font color="red">*</font>:
						</td>
						<td colspan="1" width="30%">
							<s:textfield name="year" size="5" maxlength="4" cssStyle="text-align: right" onkeypress="return numbersOnly();"
							onblur="return checkYear('paraFrm_year', 'Year');" />
						</td>
					</tr>	
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:
						</td>
						<td colspan="1" width="30%">
							<s:textfield name="divName" readonly="true" maxlength="50" size="30" cssStyle="background-color: #F2F2F2;"/>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'MonthAttendanceReport_f9Div.action');">
							<s:hidden name="divCode" />
						</td>
						<td colspan="1" width="20%">
							<label class="set" id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
						</td>
						<td colspan="1" width="30%">
							<s:textfield name="brnName" readonly="true" maxlength="50" size="30" cssStyle="background-color: #F2F2F2;"/>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'MonthAttendanceReport_f9Branch.action');">
							<s:hidden name="brnCode" />
						</td>
					</tr>
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
						</td>
						<td colspan="1" width="30%">
							<s:textfield name="deptName" readonly="true" maxlength="50" size="30" cssStyle="background-color: #F2F2F2;"/>
							<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'MonthAttendanceReport_f9Dept.action');">
							<s:hidden name="deptCode" />
						</td>
						<td colspan="1" width="20%">
							<label class="set" id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:
						</td>
						<td colspan="1" width="30%">
							<s:textfield name="typeName" readonly="true" maxlength="50" size="30" cssStyle="background-color: #F2F2F2;"/> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'MonthAttendanceReport_f9EmpType.action');">
							<s:hidden name="typeCode" />
						</td>
					</tr>
					<tr>
						<td colspan="1" width="20%">
							<label class="set" id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:
						</td>
						<td colspan="1" width="30%">
							<s:textfield name="payBillName" readonly="true" maxlength="50" size="30" cssStyle="background-color: #F2F2F2;"/> <img
							src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
							onclick="callsF9(500,325,'MonthAttendanceReport_f9PayBill.action');">
							<s:hidden name="payBillNo" />
						</td>
						<td colspan="1" width="20%">
							<label class="set" id="att.type" name="att.type" ondblclick="callShowDiv(this);"><%=label.get("att.type")%></label><font color="red">*</font>:
						</td>
						<td colspan="1" width="30%">
							<s:select name="attdnType" headerKey="0" headerValue="--Select--" list="#{'M':'Monthly', 'A':'Annualy'}" />
						</td>
					</tr>
					<!--<tr>
						<td colspan="1" width="20%">
							<label class="set" id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label><font color="red">*</font>:
						</td>
						<td colspan="1" width="30%">
							<s:select name="reportType" headerKey="0" headerValue="--Select--" list="#{'Pdf':'Pdf', 'Xls':'Xls'}" />
						</td>
					</tr> 
				--></table>
			</td>
		</tr>
		<tr>
		<td >
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
				<tr valign="middle">
					<!--  <td>
						<input type="button" class="token"  onclick="resetFun()" value=" Reset "/>
					</td>-->
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" 
						 title="PDF"> Reset </a>&nbsp;&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	
	</table>
	
	<s:hidden name="reportType" />
	
	<s:hidden name="reportAction"
		value='MonthAttendanceReport_getReport.action' />
		
</s:form>

<script type = "text/javascript" src = "../pages/common/datetimepicker.js"></script>

<script type="text/javascript" >

function callReport(type){
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_reportType').value=type;
				callReportCommon(type);
			}
}
function generateReport(type)
 {	
	try{
	//document.getElementById('paraFrm_report').value=type;
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="MonthAttendanceReport_getReport.action";	
				document.getElementById('paraFrm').submit();
			}
	 }catch (e)
	 {
	 	alert(e);
	 }	
}

function validateFields(){
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		
		var name = ['paraFrm_year'];
		var label = ['year'];
		var flag = ["enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		if(document.getElementById('paraFrm_attdnType').selectedIndex == 0) {
			alert("Please select "+document.getElementById('att.type').innerHTML.toLowerCase());
			document.getElementById('paraFrm_attdnType').focus();
			return false;
		}
		
		
		return true;
	}
	
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
			document.getElementById('paraFrm_reportType').value=type;
			document.getElementById('paraFrm').action='MonthAttendanceReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="MonthAttendanceReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	function showReport() {
		if(document.getElementById('paraFrm_month').selectedIndex == 0) {
			alert("Please select "+document.getElementById('month').innerHTML.toLowerCase());
			document.getElementById('paraFrm_month').focus();
			return false;
		}
		
		var name = ['paraFrm_year'];
		var label = ['year'];
		var flag = ["enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		if(document.getElementById('paraFrm_attdnType').selectedIndex == 0) {
			alert("Please select "+document.getElementById('att.type').innerHTML.toLowerCase());
			document.getElementById('paraFrm_attdnType').focus();
			return false;
		}
		
		if(document.getElementById('paraFrm_reportType').selectedIndex == 0) {
			alert("Please select "+document.getElementById('report.type').innerHTML.toLowerCase());
			document.getElementById('paraFrm_reportType').focus();
			return false;
		}
		
		callReport('MonthAttendanceReport_report.action');
	}
</script>