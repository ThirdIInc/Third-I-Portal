<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmployeeAnnualEarningReport" validate="true" id="paraFrm" theme="simple">
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg">
<s:hidden name="divisionAbbrevation"/>
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt"><strong class="text_head">Employee Annual Earning Report </strong></td>
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
			<td colspan="4">
			<table width="100%" class="formbg" border="0"  id='reportBodyTable'>
				<tr>
					<td colspan="4">
						<table width="100%" border="0"  class="formbg">
							<tr>
								<td colspan="4">
								<strong class="formhead"><label
									id=selectPeriod name="selectPeriod"
									ondblclick="callShowDiv(this);"><%=label.get("selectPeriod")%></label>
								</strong></td>
							</tr>
							<tr>
								<td width="20%"><label class="set" id="month"
									name="month" ondblclick="callShowDiv(this);"><%=label.get("fromYr")%></label>:<font color="red">*</font></td>
								<td width="25%">
									<s:textfield name="fromYear" theme="simple"
											maxlength="4" onkeypress="return numbersOnly();" onblur="add()"></s:textfield>
								</td>
								<td width="20%"><label class="set" id="toYr"
									name="toYr" ondblclick="callShowDiv(this);"><%=label.get("toYr")%></label>:<font color="red">*</font></td>
								<td width="25%">
									<s:textfield name="toYear" theme="simple" maxlength="4" readonly="true"></s:textfield>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				
				<tr>
					<td colspan="4">
						<table width="100%" border="0"  class="formbg">
							<tr>
								<td colspan="4">
								<strong class="formhead"><label
									id=selectReportFilter name="selectReportFilter"
									ondblclick="callShowDiv(this);"><%=label.get("selectReportFilter")%></label>
								</strong></td>
							</tr>
							<tr>
								<td width="20%"><label class="set" id="division1"
									name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font>
								</td>
								<td colspan="3">
								<s:hidden name="divisionId"/>
								<s:textarea name="divisionName" cols="100" rows="1" readonly="true"/>
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_divisionName',200,250,'EmployeeAnnualEarningReport_f9Division.action',event,'false','no','right')">
								</td>
							</tr>
							<tr>
								<td width="20%"><label class="set" id="branch1"
									name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
								<td colspan="3"><s:hidden name="branchId"/>
								<s:textarea name="branchName" cols="100" rows="1" readonly="true" />
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_branchName',200,250,'EmployeeAnnualEarningReport_f9branch.action',event,'false','no','right')">
								</td>
							</tr>
							<tr>
								<td width="20%"><label class="set" id="department1"
									name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
								<td colspan="3"><s:hidden name="departmentId"/>
								<s:textarea name="departmentName" cols="100" rows="1" readonly="true" />
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_departmentName',200,250,'EmployeeAnnualEarningReport_f9department.action',event,'false','no','right')">
								</td>
							</tr>
							<tr>
								<td width="20%"><label class="set" id="paybill1"
									name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
								<td colspan="3"><s:hidden name="paybillId"/>
								<s:textarea theme="simple" name="paybillName" cols="100" rows="1" readonly="true" />
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_paybillName',200,250,'EmployeeAnnualEarningReport_f9paybill.action',event,'false','no','right')">
								</td>
							</tr>
							<tr>
								<td width="20%"><label class="set" id="employee.type"
									name="employee.type"><%=label.get("employee.type")%></label>:</td>
								<td colspan="3"><s:hidden name="empTypeId"/>
								<s:textarea name="empTypeName"  cols="100" rows="1" readonly="true" />
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_empTypeName',200,250,'EmployeeAnnualEarningReport_f9employeeType.action',event,'false','no','right')">
								</td>
							<tr>
							</tr>
								<td width="20%"><label class="set" id="employee1"
									name="employee"><%=label.get("employee")%></label>:</td>
								<td colspan="3"><s:hidden name="empId"/>
								<s:hidden name="empToken"/>
								<s:textarea name="empName" cols="100" rows="1" readonly="true" />
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_empName',200,250,'EmployeeAnnualEarningReport_f9employee.action',event,'false','no','right')">
								</td>
							</tr>
							<tr>
								<td width="20%"><label class="set" id="earningComponents1"
									name="earningComponents" ondblclick="callShowDiv(this);"><%=label.get("earningComponents")%></label>:<font color="red">*</font>
								</td>
								<td width="25%"><s:hidden name="earningCompId"/>
								<s:textfield name="earningCompName" readonly="true" />
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:clearFields('paraFrm_deductionCompId','paraFrm_deductionCompName');callsF9(800,525,'EmployeeAnnualEarningReport_f9earnings.action');">
								</td>
								<td width="21%"><label class="set" id="deductionComponents1"
									name="deductionComponents" ondblclick="callShowDiv(this);"><%=label.get("deductionComponents")%></label>:<font color="red">*</font></td>
								<td width="25%"><s:hidden name="deductionCompId"/>
								<s:textfield name="deductionCompName" readonly="true" size="22"/>
								<img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:clearFields('paraFrm_earningCompId','paraFrm_earningCompName');callsF9(800,525,'EmployeeAnnualEarningReport_f9deductions.action');">
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						<table width="100%" border="0"  class="formbg">
							<tr>
								<td colspan="4">
								<strong class="formhead"><label
									id="reportOptions" name="reportOptions"
									ondblclick="callShowDiv(this);"><%=label.get("reportOptions")%></label>
								</strong></td>
							</tr>
							<tr>
								<td width="25%">
									<s:checkbox name="salaryCheck"/>&nbsp;
								<label class="set" id="salary1"
									name="salary" ondblclick="callShowDiv(this);"><%=label.get("salary")%></label></td>
								<td width="25%">
								<s:checkbox name="arrearsCheck"/>&nbsp;
								<label class="set" id="arrear1"
									name="arrear"><%=label.get("arrear")%></label></td>
								<td width="25%">
								<s:checkbox name="consolidateCheck"/>&nbsp;
								<label class="set" id="consolidated1"
									name="consolidated"><%=label.get("consolidated")%></label></td>
								
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
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='EmployeeAnnualEarningReport_report.action'/>
</table>
</s:form>
<script>
	formOnLoad();
	function formOnLoad(){
		document.getElementById('paraFrm_salaryCheck').checked=true;
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
			document.getElementById('paraFrm').action='EmployeeAnnualEarningReport_mailReport.action';
			document.getElementById('paraFrm').submit();
		}	
	}
	function reportFun(){
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="EmployeeAnnualEarningReport_report.action";
				document.getElementById('paraFrm').submit();
			}
	}
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="EmployeeAnnualEarningReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
	function validateFields(){
		var earnings = document.getElementById('paraFrm_earningCompId').value;
		var deductions = document.getElementById('paraFrm_deductionCompId').value;
		var division = document.getElementById('paraFrm_divisionId').value;
		var earningYr = document.getElementById('paraFrm_fromYear').value;
				
		if(earningYr==""){
			alert("Please enter from year");
			document.getElementById('paraFrm_earningYear').focus();
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

	function add(){
		var from = document.getElementById('paraFrm_fromYear').value;
	    if(from==""){
			document.getElementById('paraFrm_toYear').value="";
	    } else {
			var x=eval(from) +1;
			document.getElementById('paraFrm_toYear').value=x;
		}
	}

	function clearFields(id, name){
		document.getElementById(id).value="";
		document.getElementById(name).value="";
	}
</script>