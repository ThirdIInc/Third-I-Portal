<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="EmployeeWisePerqsReport" validate="true" id="paraFrm" validate="true"
	theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">EmployeeWise Perquisites Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<div align="right"><font color="red">*</font> Indicates
						Required</div>
					</td>
				</tr>
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id='topButtonTable'>
							<tr valign="middle">
								<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;
								</td>
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
								style="background-color: #FFFFFF;  height: 400; width: 100%; display: none;border-top: 1px #cccccc solid;">
						<iframe id="reportFrame" frameborder="0" onload=alertsize();
								style="vertical-align: top; float: left; border: 0px solid;"
								allowtransparency="yes" src="../pages/common/loading.jsp" scrolling="auto"
								marginwidth="0" marginheight="0" vspace="0" name="htmlReport"
								width="100%" height="200"></iframe> </div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" id="reportBodyTable" >
				<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
						<tr>
							<td colspan="4" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectFinancialYear" name="selectFinancialYear" ondblclick="callShowDiv(this);">
								<%=label.get("selectFinancialYear")%></label></b>
							</td>
						</tr>
						<tr> 
										<td colspan="1" width="25%"><label class="set"
												id="FinYrFrm" name="FinYrFrm"
												ondblclick="callShowDiv(this);"><%=label.get("FinYrFrm")%></label>
											:<font color="red">*</font></td>
											<td><s:textfield size="25" name="fromYear" maxlength="4"
												onkeypress="return numbersOnly();" onblur="add()" /></td>

											<td colspan="1" width="25%"><label class="set"
												id="FinYrTo" name="FinYrTo" ondblclick="callShowDiv(this);"><%=label.get("FinYrTo")%></label>
											:<font color="red">*</font></td>
											<td><s:textfield size="25" name="toYear" maxlength="4"
												readonly="true" cssStyle="background-color: #F2F2F2;" /></td>


										</tr>
					</table>
					</td>
				</tr>	
				<tr>
					<td>	
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">	
						<tr>
							<td colspan="4">
								<b><label class="set" id="selectFilterOption" name="selectFilterOption" ondblclick="callShowDiv(this);">
								<%=label.get("selectFilterOption")%></label></b>
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="division" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
							<td colspan="1" width="20%"><s:hidden name="divId" /> <s:textfield
								name="divName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'EmployeeWisePerqsReport_f9div.action');">
							</td>
							
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="20%"><s:hidden name="brnId" /> <s:textfield
								name="brnName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'EmployeeWisePerqsReport_f9brn.action');">
							</td>
						
						</tr>
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td width="20%"><s:hidden name="deptId" /> <s:textfield name="deptName"
								theme="simple" readonly="true" maxlength="50" size="25" /> 
								<img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'EmployeeWisePerqsReport_f9dept.action');">
							</td>
						
							<td colspan="1" width="20%"><label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label> :</td>
							<td colspan="1" width="20%"><s:hidden name="typeId" /> <s:textfield
								name="typeName" theme="simple" readonly="true" maxlength="50"
								size="25" /> 
								<img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'EmployeeWisePerqsReport_f9type.action');">
							</td>
							
						</tr>
						
						<tr>
				<td width="20%"><label id="taxable" name="taxable"
					ondblclick="callShowDiv(this);"><%=label.get("taxable")%></label>
				:</td>
				<td colspan="4"><s:select theme="simple" name="taxableFlag"
					cssStyle="width:152" list="#{'A':'All','Y':'Yes','N':'No'}" />
				</td>
			</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
								id='topButtonTable'>
								<tr valign="middle">
									<td nowrap="nowrap"><a href="#" onclick="resetFun();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;</td>
									<td width="100%"><%@ include
										file="/pages/common/reportButtonPanelBottom.jsp"%>
									</td>
								</tr>
							</table>
			</td>
		</tr>
	</table>

	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='EmployeeWisePerqsReport_report.action' />
	
</s:form>

<script>

function callReport(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
	}	
} 

function resetFun(){
	document.getElementById('paraFrm').action='EmployeeWisePerqsReport_reset.action';
	document.getElementById('paraFrm').submit();
}

	function validateFields(){
	try{
		if(document.getElementById("paraFrm_fromYear").value==""){
			alert("Please enter the "+document.getElementById('FinYrFrm').innerHTML.toLowerCase());
			document.getElementById("paraFrm_fromYear").focus();
			return false;
		}
		
		if(document.getElementById("paraFrm_divName").value==""){
			alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
			return false;
		}
		return true;
		}catch(e){
		///alert(e);
		}	
	} 

function add() {
	var from = document.getElementById('paraFrm_fromYear').value;
	if(from==""){
		document.getElementById('paraFrm_toYear').value="";
	} else {
		var x=eval(from) +1;
		document.getElementById('paraFrm_toYear').value=x;
	}
}
 
function mailReportFun(type){
	if(!validateFields()){ 
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='EmployeeWisePerqsReport_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}
 
function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_fromYear").value;
	if(yr==''){
		document.getElementById("paraFrm_fromYear").value =year;
	}
}

getYear();


function getToYear() {
	var fromMonth =document.getElementById('paraFrm_fromMonth').value;
	var fromYear =document.getElementById('paraFrm_fromYear').value;
	var toMonth =document.getElementById('paraFrm_toMonth').value;
	if(!(fromMonth=="" || toMonth=="" || fromYear=="")) { 
		if(eval(fromMonth) <= eval(toMonth)) {
			document.getElementById('paraFrm_toYear').value=fromYear;
		} else {
			document.getElementById('paraFrm_toYear').value=eval(fromYear)+1;
		}
	} else {
		document.getElementById('paraFrm_toYear').value="";
	}
}
	
</script>