<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="MiscSalaryUpload" validate="true" id="paraFrm" theme="simple">
<s:hidden name="dataPath" />
<s:hidden name="status"/>
<s:hidden name="uploadedFile"/>
<s:hidden name="managerCode"/>
<s:hidden name="managerDivCode"/>
<s:hidden name="managerPaybillCode"/>
<s:hidden name="managerBranchCode"/>
<s:hidden name="managerDebitCode"/>
<s:hidden name="managerCreditCode"/>
<s:hidden name="reportType" />
<s:hidden name="linkSource" />
<s:hidden name="reportAction" value='MiscSalaryUpload_report.action'/>
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Misc Salary Upload </strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
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
						
						 	<s:if test="linkSource ==''">
						 	<a href="#" onclick="resetFun();">
							<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
							</s:if>
							<s:else>
							<a href="#" onclick="backFun();">
								<img src="../pages/images/buttonIcons/back.png" class="iconImage"  align="absmiddle" title="PDF"> Back </a>&nbsp;&nbsp;
							</s:else>
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
			<td colspan="4"><s:hidden name="monthView"/>
			<table width="100%" class="formbg" border="0" id='reportBodyTable'>
				<tr>
					<td colspan="4">
					<table width="100%" border="0" >
						<tr>
							<td colspan="4"><strong>Download Template Filters</strong></td>
						</tr>
						<tr>
							<td width="20%"><label class="set" id="miscmonth1"
								name="miscmonth" ondblclick="callShowDiv(this);"><%=label.get("miscmonth")%></label>:<font color="red">*</font></td>
							<td>
								<s:if test="linkSource ==''">
						<s:select name="month" cssStyle="width:129" headerKey="" headerValue="-- Select --"
								list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					</s:if>
					<s:else>
					<s:property value="monthView"/><s:hidden name="month"/>
					</s:else>
							</td>
							<td width="16%"><label class="set" id="miscyear1" ondblclick="callShowDiv(this);"
								name="miscyear"><%=label.get("miscyear")%></label>:<font color="red">*</font></td>
							<td>
						<s:if test="linkSource ==''">
					<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" />
					</s:if>
					<s:else>
					<s:textfield name="year" readonly="true" theme="simple" maxlength="4" />
					</s:else>
							</td>
						</tr>
						<tr>
							<td><label class="set" id="division1"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font>
							</td>
							<td><s:hidden name="divisionId"/>
							<s:hidden name="divisionAbbrevation" />
							<s:textfield theme="simple"	readonly="true" name="divisionName"  size="23"/>
							<s:if test="linkSource ==''">
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:clearFields();callsF9(800,525,'MiscSalaryUpload_f9division.action');">
							</s:if>
							</td>
							<td><label class="set" id="branch1"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td><s:hidden name="branchId"/>
							<s:textfield name="branchName" readonly="true" size="23"/>
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(800,525,'MiscSalaryUpload_f9branch.action');">
							</td>
						</tr>
						<tr>
							<td><label class="set" id="paybill1"
								name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
							<td><s:hidden name="paybillId"/>
							<s:textfield theme="simple" name="paybillName" readonly="true" size="23"/>
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(800,525,'MiscSalaryUpload_f9paybill.action');">
							</td>
							<td><label class="set" id="grade1"
								name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td><s:hidden name="gradeId"/>
							<s:textfield theme="simple" readonly="true"  name="gradeName" size="23"/>
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(800,525,'MiscSalaryUpload_f9grade.action');">
							</td>
						</tr>
						<tr>
							<td><label class="set" id="department1"
								name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
							<td colspan="3"><s:hidden name="departmentId"/>
							<s:textfield name="departmentName" readonly="true" size="23"/>
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(800,525,'MiscSalaryUpload_f9department.action');">
							</td>
						</tr>
						<tr>
							<td><label class="set" id="employee1" ondblclick="callShowDiv(this);"
								name="employee"><%=label.get("employee")%></label>:</td>
							<td colspan="3"><s:hidden name="empId"/>
							<s:textarea name="empName" cols="100" rows="1" readonly="true" />
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(800,525,'MiscSalaryUpload_f9employee.action');">
								<!--
								onclick="javascript:callDropdown('paraFrm_empName',200,250,'MiscSalaryUpload_f9employee.action',event,'false','no','right')">
								-->
							</td>
						</tr>
						<tr>
							<td><label class="set" id="earningComponents1"
								name="earningComponents" ondblclick="callShowDiv(this);"><%=label.get("earningComponents")%></label>:
							</td>
							<td colspan="3"><s:hidden name="earningCompId"/>
							<s:textarea name="earningCompName" cols="100" rows="1" readonly="true" />
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(800,525,'MiscSalaryUpload_f9earnings.action');">
								<!--
								onclick="javascript:callDropdown('paraFrm_earningCompName',200,250,'MiscSalaryUpload_f9earnings.action',event,'false','no','right')">
								-->
							</td>
						</tr>
						<tr>
							<td><label class="set" id="deductionComponents1"
								name="deductionComponents" ondblclick="callShowDiv(this);"><%=label.get("deductionComponents")%></label> :</td>
							<td colspan="3"><s:hidden name="deductionCompId"/>
							<s:textarea name="deductionCompName" cols="100" rows="1" readonly="true" />
							<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
								onclick="javascript:callsF9(800,525,'MiscSalaryUpload_f9deductions.action');">
								<!--
								onclick="javascript:callDropdown('paraFrm_deductionCompName',200,250,'MiscSalaryUpload_f9deductions.action',event,'false','no','right')">
								-->
							</td>
						</tr>
						<tr>
							<td colspan="4" align="center">
								<input type="button" class="token"  onclick="downloadtemplateFun()" value="  Download Template  "/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<table width="100%" class="formbg" border="0">
						<tr>
							<td colspan="4">
								<a href="#" style="color:blue;" onclick="callStatistics();">Show Statistics</a>  
							</td>
						</tr>
						<s:if test="showStatisticsFlag">
						<tr>
							<td colspan="2" width="50%" valign="top">
							<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formth">
									<label class="set" id="creditHead"	name="creditHead" ondblclick="callShowDiv(this);"><%=label.get("creditHead")%></label>
								</td>
								<td class="formth">
									<label class="set" id="empCount"	name="empCount" ondblclick="callShowDiv(this);"><%=label.get("empCount")%></label>
								</td>
								<td class="formth">
									<label class="set" id="uploadAmt"	name="uploadAmt" ondblclick="callShowDiv(this);"><%=label.get("uploadAmt")%></label>
								</td>
							</tr>
								<s:iterator value="creditList">
								<tr>
									<td class="sortableTD">
										<s:property value="creditNameItt " />
									</td>
									<td class="sortableTD" align="center">
										<s:property value="creditCountItt " />
									</td>
									<td class="sortableTD" align="center">
										<s:property value="creditAmountItt " />
									</td>
								</tr>
								</s:iterator>
							</table>
							</td>
							<td colspan="2" width="50%" valign="top">
							<table width="100%" border="0" class="formbg">
							<tr>
								<td class="formth">
									<label class="set" id="debitHead"	name="debitHead" ondblclick="callShowDiv(this);"><%=label.get("debitHead")%></label>
								</td>
								<td class="formth">
									<label class="set" id="empCount1"	name="empCount" ondblclick="callShowDiv(this);"><%=label.get("empCount")%></label>
								</td>
								<td class="formth">
									<label class="set" id="uploadAmt1"	name="uploadAmt" ondblclick="callShowDiv(this);"><%=label.get("uploadAmt")%></label>
								</td>
							</tr>
								<s:iterator value="debitList">
								<tr>
									<td class="sortableTD">
										<s:property value="debitNameItt " />
									</td>
									<td class="sortableTD" align="center">
										<s:property value="debitCountItt " />
									</td>
									<td class="sortableTD" align="center">
										<s:property value="debitAmountItt " />
									</td>
								</tr>
								</s:iterator>
							</table>
							</td>
						</tr>
						</s:if>
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="4">
				<table width="100%" border="0">
					<tr>
						<td colspan="4"><strong>Upload Data</strong></td>
					</tr>
					<tr>
						<td width="20%"><label class="set" id="selectFile1" ondblclick="callShowDiv(this);"
								name="selectFile"><%=label.get("selectFile")%></label>:<font color="red">*</font></td>
						<td width="40%">
							<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
						</td>
						<td width="20%" align="right">
							<input type="button" value="   Browse   " class="token" onclick="selectFile('uploadFileName')"/>&nbsp;
						</td>
						<td width="20%">	
							<input type="button" class="token" 	value=" Upload File " onclick="callUpload()" />
						</td>
					</tr>
					<s:if test="displayNoteFlag">					
						<s:if test="status == 'Success'">					
							<tr>
								<td width="100%" colspan="4">
									<FONT color="green"><s:property value="status" /></FONT>
								</td>
							</tr>
							<tr>
								<td width="100%" colspan="4" align="center">
									<a href="#"
										onclick="viewUploadedFile();"><font color="blue"><u>Please
									click here to view uploaded file.</u></font></a>
								</td>
							</tr>
						</s:if><s:else>
							<tr>
								<td width="100%" colspan="4">
									<FONT color="red"><s:property value="status" /></FONT>
								</td>
							</tr>
							<tr>
								<td width="100%" colspan="4">
									<FONT color="red">Note : <s:property value="note"/></FONT>
								</td>
							</tr>
							<tr>
								<td width="100%" colspan="4" align="center">
									<a href="#"
										onclick="viewUploadedFile();"><font color="blue"><u>Please
									click here to view uploaded file.</u></font></a>
								</td>
							</tr>
						</s:else>
					</s:if>
					<!--<tr>
						<td width="25%">
							<s:checkbox name="overwriteChk" onclick="toggleCheck('O');"/>&nbsp;
						<label class="set" id="overwite1"
							name="overwite" ondblclick="callShowDiv(this);"><%=label.get("overwite")%></label>:</td>
						<td width="25%">
						<s:checkbox name="appendChk" onclick="toggleCheck('A');"/>&nbsp;
						<label class="set" id="append1" ondblclick="callShowDiv(this);"
							name="append"><%=label.get("append")%></label>:</td>
					</tr>
					<tr>
						<td width="20%">
						<strong> Note :</strong></td>	
					</tr>
					<tr>
						<td colspan="2">
						<strong>
						<label class="set" id="note1"name="note"><%=label.get("note")%></label></strong></td>	
					</tr>-->
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
					<img src="../pages/images/buttonIcons/Refresh.png" class="iconImage"  align="absmiddle" title="PDF"> Reset </a>&nbsp;&nbsp;
				 	<s:if test="linkSource ==''">
					</s:if>
					<s:else>
					<a href="#" onclick="backFun();">
						<img src="../pages/images/buttonIcons/back.png" class="iconImage"  align="absmiddle" title="PDF"> Back </a>&nbsp;&nbsp;
					</s:else>
			</td>
			<td width="100%">
				<%@ include file="/pages/common/reportButtonPanelBottom.jsp"%>
			</td>
		</tr>
	</table>
	</td>
</tr>	
</table>

</s:form>
<script>
/*
formOnLoad();
function formOnLoad(){
	document.getElementById('paraFrm_overwriteChk').checked=true;
	document.getElementById('paraFrm_appendChk').checked=false;
}*/

function backFun(){
	var action11=document.getElementById('paraFrm_linkSource').value;
	document.getElementById('paraFrm').action=action11;
	document.getElementById('paraFrm').target = 'main';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
function callReport(type){
	document.getElementById('paraFrm_reportType').value=type;
	if(!validateFields()){
			return false;
		} else {
			callReportCommon(type);
		}
}
function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='MiscSalaryUpload_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}
function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="MiscSalaryUpload_reset.action";
	document.getElementById('paraFrm').submit();
}
function downloadtemplateFun() {
	if(!validateFields()){
			return false;
		} else {
	  	document.getElementById('paraFrm').target = 'self';
		document.getElementById('paraFrm').action = 'MiscSalaryUpload_downloadTemplate.action';
		document.getElementById('paraFrm').submit();
	}
}
function validateFields(){
	var earnings = document.getElementById('paraFrm_earningCompId').value;
	var deductions = document.getElementById('paraFrm_deductionCompId').value;
	var division = document.getElementById('paraFrm_divisionId').value;
	var monthMisc = document.getElementById('paraFrm_month').value;
	var yearMisc = document.getElementById('paraFrm_year').value;
	
	if(monthMisc==""){
		alert("Please select month");
		return false;
	}
	if(yearMisc==""){
		alert("Please enter year");
		document.getElementById('paraFrm_year').focus();
		return false;
	}
	if(division==""){
		alert("Please select division");
		document.getElementById('paraFrm_divisionName').focus();
		return false;
	}
	if(earnings=="" && deductions==""){
		alert("Please select earning or deduction component");
		return false;
	}
	return true;
}
function selectFile(fieldName) {
	var dataPath = document.getElementById('paraFrm_dataPath').value;
	window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
	fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
}
function callUpload() {
	if(!validateUpload()){
			return false;
	} else {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "MiscSalaryUpload_uploadMiscSalaryDetails.action";
			document.getElementById('paraFrm').submit();
	}
}
function validateUpload(){
	var monthMisc = document.getElementById('paraFrm_month').value;
	var yearMisc = document.getElementById('paraFrm_year').value;
	var division = document.getElementById('paraFrm_divisionId').value;
	var uploadedFile = document.getElementById('paraFrm_uploadFileName').value;
	if(monthMisc==""){
		alert("Please select month");
		return false;
	}
	if(yearMisc==""){
		alert("Please enter year");
		document.getElementById('paraFrm_year').focus();
		return false;
	}
	if(division==""){
		alert("Please select division");
		document.getElementById('paraFrm_divisionName').focus();
		return false;
	}
	if(uploadedFile == '') {
		alert('Please select a file to upload.');
		return false;
	}
	return true;
}
function viewUploadedFile() {
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action = 'MiscSalaryUpload_viewUploadedFile.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}
function clearFields(){
	document.getElementById('paraFrm_empId').value="";
	document.getElementById('paraFrm_empName').value="";
} 

function toggleCheck(chkStatus){
	if(chkStatus=='O'){
		document.getElementById('paraFrm_appendChk').checked= false;
	}
	if(chkStatus=='A'){
		document.getElementById('paraFrm_overwriteChk').checked = false;
	}	
}
function callStatistics(){
	var monthMisc = document.getElementById('paraFrm_month').value;
	var yearMisc = document.getElementById('paraFrm_year').value;
	var division = document.getElementById('paraFrm_divisionId').value;
	
	if(monthMisc==""){
		alert("Please select month");
		return false;
	}
	if(yearMisc==""){
		alert("Please enter year");
		document.getElementById('paraFrm_year').focus();
		return false;
	}
	if(division==""){
		alert("Please select division");
		document.getElementById('paraFrm_divisionName').focus();
		return false;
	}
	
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action = "MiscSalaryUpload_showStatistics.action";
	document.getElementById('paraFrm').submit();
}
	
</script>