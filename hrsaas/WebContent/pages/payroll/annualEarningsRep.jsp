<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="AnnualEarning" validate="true" id="paraFrm" validate="true" theme="simple">
	<table width="100%" class="formbg" cellpadding="1" cellspacing="1">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg" cellpadding="1" cellspacing="1">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Annual Earnings Report</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="1" cellspacing="1" id='topButtonTable'>
				<tr valign="middle">
					<td nowrap="nowrap">
						<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
					</td>
					<td width="100%">
						<%@ include file="/pages/common/reportButtonPanel.jsp"%>
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
		<tr>
			<td colspan="3">
				<table width="100%" class="formbg" border="0"  id='reportBodyTable' cellpadding="1" cellspacing="1">
					<tr>
						<td>
							<table width="100%" border="0" class="formbg" cellpadding="1" cellspacing="1">
								<tr>
									<td colspan="4">
									<strong class="formhead"><label
										id=selectPeriod name="selectPeriod"
										ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
									</strong></td>
								</tr>
								<tr>
									<td width="20%">
										<label  class = "set"  id="fromYr" name="fromYr" ondblclick="callShowDiv(this);"><%=label.get("fromYr")%></label>:<font	color="red">*</font>
									</td>
									<td width="30%">
										<s:textfield name="fromYear" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="25" onblur="add();"/>
									</td>
									<td width="20%">
										<label  class ="set" id="toYr" name="toYr" ondblclick="callShowDiv(this);"><%=label.get("toYr")%></label>:<font	color="red">*</font>
									</td>
									<td>
										<s:textfield name="toYear" theme="simple" maxlength="4" size="25" readonly="true" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
									<strong class="formhead"><label
										id=selectReportFilter name="selectReportFilter"
										ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
									</strong></td>
								</tr>
								<tr>
									<td>
										<label  class = "set"  id="division1" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font	color="red">*</font>
									</td>
									<td><s:hidden name="divId" />
										<s:textfield name="divName" theme="simple" readonly="true" maxlength="50" size="25" />
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AnnualEarning_f9div.action');">
									</td>
								</tr>
								<tr>
									<td>
										<label  class = "set"  id="branch1" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
									</td>
									<td><s:hidden name="brnId" /> <s:textfield
										name="brnName" theme="simple" readonly="true" maxlength="50"
										size="25" /> <img src="../pages/images/recruitment/search2.gif"
										class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AnnualEarning_f9branch.action');">
									</td>
									<td>
										<label  class = "set"  id="department1" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
									</td>
									<td><s:hidden name="deptId" />
										<s:textfield name="deptName" theme="simple" readonly="true" maxlength="50" size="25"/>
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AnnualEarning_f9dept.action');">
									</td>
								</tr>
								<tr>
									<td>
										<label  class = "set"  id="employee.type" name="employee.type" ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>:
									</td>
									<td><s:hidden name="typeId"/>
										<s:textfield name="typeName" theme="simple" readonly="true" maxlength="50" size="25" />
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AnnualEarning_f9type.action');">
									</td>
									<td>
										<label  class = "set"  id="designation1" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>:
									</td>
									<td><s:hidden name="desgId" />
										<s:textfield name="desgName" theme="simple" readonly="true" maxlength="50" size="25"/> 
										<img src="../pages/images/recruitment/search2.gif" class="iconImage" height="18" align="absmiddle" width="18"
										onclick="javascript:callsF9(500,325,'AnnualEarning_f9desg.action');">
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" id='topButtonTable'>
					<tr valign="middle">
						<td nowrap="nowrap">
							<a href="#" onclick="resetFun();">
								<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle"> Reset </a>&nbsp;
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
<s:hidden name="reportAction" value='AnnualEarning_report.action'/>
</s:form>
<script>
function callReport(type){
	document.getElementById('paraFrm_reportType').value=type;
	if(!validateFields()){
			return false;
	}else{
		callReportCommon(type);
	}
}

function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='AnnualEarning_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}

function reportFun(){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="AnnualEarning_report.action";
		document.getElementById('paraFrm').submit();
	}
}
	
function validateFields(){
	var division = document.getElementById('paraFrm_divId').value;
	if(document.getElementById('paraFrm_fromYear').value==""){
		alert("Please Enter "+document.getElementById('from.year').innerHTML.toLowerCase());
		return false;
	}
	if(!checkYear('paraFrm_fromYear','from.year')){
 		return false;	 
	}
	if(division==""){
		alert("Please select division");
		document.getElementById('paraFrm_divName').focus();
		return false;
	}
	return true;
}

function resetFun(){
	document.getElementById('paraFrm_fromYear').value="";
	document.getElementById('paraFrm_toYear').value="";
	document.getElementById('paraFrm_divId').value="";
	document.getElementById('paraFrm_divName').value="";
	document.getElementById('paraFrm_brnId').value="";
	document.getElementById('paraFrm_brnName').value="";
	document.getElementById('paraFrm_deptId').value="";
	document.getElementById('paraFrm_deptName').value="";
	document.getElementById('paraFrm_typeName').value="";
	document.getElementById('paraFrm_typeId').value="";
	document.getElementById('paraFrm_desgName').value="";
	document.getElementById('paraFrm_desgId').value="";
	document.getElementById('paraFrm_desgName').value="";
	getYear();
}

function add() {
	var from = document.getElementById('paraFrm_fromYear').value;
    if(from=="") {
		document.getElementById('paraFrm_toYear').value="";
    }else{
		var x=eval(from) +1;
		document.getElementById('paraFrm_toYear').value=x;
	}
}

function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_fromYear").value;
	if(yr==''){
	  document.getElementById("paraFrm_fromYear").value =year;
	  add();
	}
}
getYear();	
</script>
