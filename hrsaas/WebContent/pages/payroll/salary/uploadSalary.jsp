<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="UploadSalary" validate="true" id="paraFrm" theme="simple">
<s:hidden name="dataPath"/>
<s:hidden name="status"/>
<s:hidden name="uploadedFile"/>
<s:hidden name="monthView"/>
<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Upload Salary</strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table width="100%" border="0" cellpadding="1" cellspacing="1">
					<tr>
						<td nowrap="nowrap">	
							<input type="button" class="token" 	value=" Salary Register " onclick="callSalaryRegister();"/>
							<!--
							<input type="button" class="token" 	value=" Bank Statement " onclick="callBankStatement();" />
							-->
							<input type="button" class="token" 	value=" Reset " onclick="resetFun();"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><strong>Download Template Filters</strong></td>
				</tr>
				<tr>
					<td width="20%">
						<label class="set" id="uploadMonth" name="uploadMonth" ondblclick="callShowDiv(this);"><%=label.get("uploadMonth")%></label>:<font color="red">*</font>
					</td>
					<td>
						<s:select name="month" cssStyle="width:130" headerKey="" headerValue="-- Select --"
						list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
					</td>
					<td width="20%">
						<label class="set" id="uploadYear" ondblclick="callShowDiv(this);" name="uploadYear"><%=label.get("uploadYear")%></label>:<font color="red">*</font>
					</td>
					<td>
						<s:textfield name="year" onkeypress="return numbersOnly();" theme="simple" maxlength="4" size="23"/>
					</td>
				</tr>
				<tr>
					<td>
						<label class="set" id="division1" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font>
					</td>
					<td><s:hidden name="divisionId"/>
						<s:hidden name="divisionAbbrevation" />
						<s:textfield theme="simple"	readonly="true" name="divisionName"  size="23"/>
						<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'UploadSalary_f9division.action');">
					</td>
					<td>
						&nbsp;
					</td>
					<td>
						<input type="button" class="token"  onclick="downloadTemplateFun()" value="       Download Template      " title="Download"/>
					</td>
					<!--<td>
						<label class="set" id="branch1" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:
					</td>
					<td><s:hidden name="branchId"/>
						<s:textfield name="branchName" readonly="true" size="23"/>
						<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callsF9(800,525,'UploadSalary_f9branch.action');">
					</td>-->
				</tr>
				<!--<tr>
					<td colspan="4" align="center">
						<input type="button" class="token"  onclick="downloadtemplateFun()" value="  Download Template  "/>
					</td>
				</tr>-->
				<tr class="formbg">
					<td width="20%">
						<strong class="formhead">
							<label id=selectReportFilter name="selectReportFilter" ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
						</strong>
					</td>
					<td colspan="3">
						<a href="#" style="color:blue;" title="Expand Filter" onclick="expandFilter();">Advanced Filter</a>
					</td>
				</tr>
				<tr id="selectFilterBlock" style="display:none;">
					<td colspan="4">
						<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
							<tr>
								<td width="20%">
									<label class="set" id="paybill1" name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:
								</td>
								<td><s:hidden name="paybillId"/>
									<s:textfield theme="simple" name="paybillName" readonly="true" size="23"/>
									<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callsF9(800,525,'UploadSalary_f9paybill.action');">
								</td>
								<td width="20%">
									<label class="set" id="grade1"	name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
								:</td>
								<td><s:hidden name="gradeId"/>
									<s:textfield theme="simple" readonly="true"  name="gradeName" size="23"/>
									<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callsF9(800,525,'UploadSalary_f9grade.action');">
								</td>
							</tr>
							<!--<tr>
									<td>
										<label class="set" id="department1"	name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:
									</td>
									<td colspan="3"><s:hidden name="departmentId"/>
										<s:textfield name="departmentName" readonly="true" size="23"/>
										<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
										onclick="javascript:callsF9(800,525,'UploadSalary_f9department.action');">
									</td>
								</tr>-->
								<tr>
									<td>
										<label class="set" id="employee1" ondblclick="callShowDiv(this);" name="employee"><%=label.get("employee")%></label>:
									</td>
									<td colspan="3" nowrap="nowrap"><s:hidden name="empId"/>
										<s:textarea name="empName" cols="101" rows="1" readonly="true" />
										<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
										onclick="callSearch('UploadSalary_f9employee.action');">
									</td>
								</tr>
							<!--<tr>
								<td>
									<label class="set" id="earningComponents1" name="earningComponents" ondblclick="callShowDiv(this);"><%=label.get("earningComponents")%></label>:
								</td>
								<td colspan="3"><s:hidden name="earningCompId"/>
									<s:textarea name="earningCompName" cols="100" rows="1" readonly="true" />
									<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callsF9(800,525,'UploadSalary_f9earnings.action');">
								</td>
							</tr>
							<tr>
								<td>
									<label class="set" id="deductionComponents1" name="deductionComponents" ondblclick="callShowDiv(this);"><%=label.get("deductionComponents")%></label>:
								</td>
								<td colspan="3"><s:hidden name="deductionCompId"/>
									<s:textarea name="deductionCompName" cols="100" rows="1" readonly="true" />
									<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callsF9(800,525,'UploadSalary_f9deductions.action');">
								</td>
							</tr>-->
						</table>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">
			<table width="100%" border="0" cellpadding="1" cellspacing="1" class="formbg">
				<tr>
					<td colspan="4"><strong>Upload Data</strong></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" id="selectFile1" ondblclick="callShowDiv(this);"
							name="selectFile"><%=label.get("selectFile")%></label>:<font color="red">*</font></td>
					<td>
						<s:textfield name="uploadFileName" size="70" readonly="true" cssStyle="background-color: #F2F2F2;" />
					</td>
					<td align="right">
						<input type="button" value="   Browse   " class="token" onclick="selectFile('uploadFileName')"/>&nbsp;
					</td>
					<td>	
						<input type="button" class="token" 	value=" Upload File " onclick="callUpload()" />
					</td>
				</tr>
				<s:if test="displayNoteFlag">					
					<s:if test="status == 'Success'">					
						<tr>
							<td width="100%" colspan="4">
								<strong><font color="green"><s:property value="status" /></font></strong>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4" align="center">
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please	click here to view uploaded file.</u></font></a>
							</td>
						</tr>
					</s:if>
					<s:else>
						<tr>
							<td width="100%" colspan="4">
								<strong><font color="red"><s:property value="status" /></font></strong>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4">
								<strong><font color="red">Note : <s:property value="note"/></font></strong>
							</td>
						</tr>
						<tr>
							<td width="100%" colspan="4" align="center">
								<a href="#" onclick="viewUploadedFile();"><font color="blue"><u>Please click here to view uploaded file.</u></font></a>
							</td>
						</tr>
					</s:else>
				</s:if>
				<s:else>
					<tr>
						<td width="100%" colspan="4">
							<strong><FONT color="red">Note : Upload only 50 records at a time.</FONT></strong>
						</td>
					</tr>
				</s:else>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td nowrap="nowrap">	
						<input type="button" class="token" 	value=" Salary Register " onclick="callSalaryRegister();" />
						<!--
							<input type="button" class="token" 	value=" Bank Statement " onclick="callBankStatement();" />
							-->
						<input type="button" class="token" 	value=" Reset " onclick="resetFun();" />
					</td>
				</tr>
			</table>
		</td>
	</tr>	
</table>
</s:form>
<script>
function resetFun(){
	document.getElementById('paraFrm').target = "_self";
	document.getElementById('paraFrm').action="UploadSalary_reset.action";
	document.getElementById('paraFrm').submit();
}

function downloadTemplateFun(){
	if(!validateFields()){
			return false;
		} else {
	  	document.getElementById('paraFrm').target = 'self';
		document.getElementById('paraFrm').action = 'UploadSalary_downloadTemplate.action';
		document.getElementById('paraFrm').submit();
	}
}

function validateFields(){
	var uploadMonth = document.getElementById('paraFrm_month').value;
	var uploadYear = document.getElementById('paraFrm_year').value;
	var division = document.getElementById('paraFrm_divisionId').value;
	//var earnings = document.getElementById('paraFrm_earningCompId').value;
	//var deductions = document.getElementById('paraFrm_deductionCompId').value;
	
	if(uploadMonth==""){
		alert("Please select Month");
		return false;
	}
	if(uploadYear==""){
		alert("Please enter Year");
		document.getElementById('paraFrm_year').focus();
		return false;
	}
	if(division==""){
		alert("Please select division");
		document.getElementById('paraFrm_divisionName').focus();
		return false;
	}
	/*
	if(earnings=="" && deductions==""){
		alert("Please select earning or deduction component");
		return false;
	}*/
	return true;
}

function selectFile(fieldName){
	var dataPath = document.getElementById('paraFrm_dataPath').value;
	window.open('<%=request.getContextPath()%>/pages/DataMigration/uploadMigratedFile.jsp?path=' + dataPath + '&field=' + 
	fieldName, '', 'width=400, height=200, scrollbars=no, resizable=no, top=100, left=150');
}

function callUpload(){
	if(!validateUpload()){
			return false;
	} else {
			document.getElementById('paraFrm').target = "_self";
			document.getElementById('paraFrm').action = "UploadSalary_uploadSalaryDetails.action";
			document.getElementById('paraFrm').submit();
	}
}

function validateUpload(){
	var uploadMonth = document.getElementById('paraFrm_month').value;
	var uploadYear = document.getElementById('paraFrm_year').value;
	var division = document.getElementById('paraFrm_divisionId').value;
	var uploadedFile = document.getElementById('paraFrm_uploadFileName').value;
	if(uploadMonth==""){
		alert("Please select month");
		return false;
	}
	if(uploadYear==""){
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

function viewUploadedFile(){
	document.getElementById('paraFrm').target = '_blank';
	document.getElementById('paraFrm').action = 'UploadSalary_viewUploadedFile.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target = 'main';
}

function expandFilter(){
	if(document.getElementById('selectFilterBlock').style.display=='none'){
		document.getElementById('selectFilterBlock').style.display='';
	}else{
		document.getElementById('selectFilterBlock').style.display='none';
	}
} 

function callSalaryRegister(){
	var divName=document.getElementById('paraFrm_divisionName').value;
	var divNameId=document.getElementById('paraFrm_divisionId').value;
	var linkSource11='UploadSalary_input.action';
	var monthText = document.getElementById('paraFrm_month').options[document.getElementById('paraFrm_month').selectedIndex].text;
	document.getElementById('paraFrm_monthView').value = monthText;
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm').action='SalaryRegister_input.action?divName='+divName+'&divCode='+divNameId+'&linkSource='+linkSource11;
		document.getElementById('paraFrm').submit();
	}
}

function callBankStatement(){
	var linkSource11='UploadSalary_input.action';
	var month=document.getElementById('paraFrm_month').value;
	var year=document.getElementById('paraFrm_year').value;			
	var divName=document.getElementById('paraFrm_divisionName').value;
	var divCode=document.getElementById('paraFrm_divisionId').value;
	var monthText = document.getElementById('paraFrm_month').options[document.getElementById('paraFrm_month').selectedIndex].text;
	document.getElementById('paraFrm_monthView').value = monthText;
	
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm').action='SalaryStatementBank_viewSalaryStatementLink.action?earningType=S&earningTypeDisplay=S&hiddenMonth='+month+'&earningYear='+year+'&divisionName11='+divName+'&linkSource='+linkSource11+'&divisionCode='+divCode;
		document.getElementById('paraFrm').submit();
	}
}

function callSearch(action){
	if(!validateFields()){
		return false;
	} else {
		javascript:callsF9(800, 525, action);
	}
}
</script>