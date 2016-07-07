<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmployeeMonthlyEarningReport" validate="true" id="paraFrm" theme="simple">

<table width="100%" border="0" cellpadding="0" cellspacing="0" >
	<tr>
			<td  width="100%">
				<table width="100%" class="formbg">
					<tr >
						<td width="4%" valign="bottom" class="txt">
							<strong class="formhead"><img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong>
						</td>
						<td width="93%" class="txt" ><strong class="text_head">Employee Monthly Earning Report </strong></td>
						<td width="3%" valign="top" class="txt">
							<div align="right"><img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td align="right" width="100%">
				<div align="right"><font color="red">*</font> Indicates Required</div>
			</td>
			
		</tr>
		<tr>
			<td>
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
		<tr >
			<td width="100%">
			<table width="100%"  class="formbg" border="0" id="reportBodyTable">
			<tr>
			<td><label class="set" id="month"
						name="month" ondblclick="callShowDiv(this);"><%=label.get("month")%></label></td>
			<td>*</td>
			<td>:</td>
			<td><s:select name="earningMonth" cssStyle="width:129" headerKey="" headerValue="-- Select --"
								list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" /></td>
			<td></td>
			<td><label class="set" id="year"
						name="year"><%=label.get("year")%></label></td>
			<td>*</td>
			<td>:</td>
			<td><s:textfield name="earningYear" onkeypress="return numbersOnly();" theme="simple" maxlength="4" />
					</td> 
			<td></td> 
			</tr>
		<tr>
			<td width="15%"><label class="set" id="division1"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label></td>
			<td>*</td>
			<td>:</td>
			<td colspan="6"><s:hidden name="divisionId"/>
					<s:hidden name="divisionAbbrevation" />
					<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="divisionName"  /></td>
			<td width="25%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_divisionName',350,250,'EmployeeMonthlyEarningReport_f9division.action',event,'false','no','right')"></td>
		</tr>
	<tr>
			<td><label class="set" id="branch1"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label></td>
			<td>*</td>
			<td>:</td>
					<td colspan="6"><s:hidden name="branchId" /> <s:textarea
						cols="100" rows="1" name="branchName" readonly="true" /></td>
					<td><img src="../pages/images/search2.gif" class="iconImage"
						height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_branchName',350,250,'EmployeeMonthlyEarningReport_f9branch.action',event,'false','no','right')"></td>
					</td>
				</tr>
			<tr>			
			<td><label class="set" id="department1"
						name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label></td>
			<td>*</td>
			<td>:</td>
			<td colspan="6"><s:hidden name="departmentId"/>
					<s:textarea cols="100" rows="1" name="departmentName" readonly="true" /></td>
			<td align="left"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_departmentName',350,250,'EmployeeMonthlyEarningReport_f9department.action',event,'false','no','right')"></td>
			</tr>
			
			<tr>			
			<td><label class="set" id="paybill1"
						name="paybill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label></td>
			<td>*</td>
			<td>:</td>
			<td colspan="6"><s:hidden name="paybillId"/>
					<s:textarea cols="100" rows="1"  name="paybillName" readonly="true" /></td>
			<td><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_paybillName',350,250,'EmployeeMonthlyEarningReport_f9paybill.action',event,'false','no','right')"></td>
			</tr>
			<tr>			
			<td><label class="set" id="employee.type"
						name="employee.type"><%=label.get("employee.type")%></label></td>
			<td>*</td>
			<td>:</td>
			<td colspan="6"><s:hidden name="empTypeId"/>
					<s:textarea cols="100" rows="1" name="empTypeName"  readonly="true" /></td>
			<td><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_empTypeName',350,250,'EmployeeMonthlyEarningReport_f9employeeType.action',event,'false','no','right')"></td>
			</tr>
			<tr>			
			<td><label class="set" id="employee1"
						name="employee"><%=label.get("employee")%></label></td>
			<td>*</td>
			<td>:</td>
			<td colspan="6"><s:hidden name="empId"/>
					<s:textarea name="empName" rows="1" cols="100" rows="1" readonly="true" /></td>
			<td><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
					onclick="javascript:callDropdown('paraFrm_empName',350,250,'EmployeeMonthlyEarningReport_f9employee.action',event,'false','no','right')"></td>
			</tr>
			<tr>			
			<td><label class="set" id="earningComponents1"
						name="earningComponents" ondblclick="callShowDiv(this);"><%=label.get("earningComponents")%></label></td>
			<td>*</td>
			<td>:</td>
			<td colspan="6"><s:hidden name="earningCompId"/>
					<s:textarea cols="100" rows="1" name="earningCompName"  rows="1" readonly="true" /></td>
			<td><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_earningCompName',200,250,'EmployeeMonthlyEarningReport_f9earnings.action',event,'false','no','right')"></td>
				
			</tr>
			<tr>			
			<td><label class="set" id="deductionComponents1"
							name="deductionComponents" ondblclick="callShowDiv(this);"><%=label.get("deductionComponents")%></label></td>
			<td>*</td>
			<td>:</td>
			<td colspan="6"><s:hidden name="deductionCompId"/>
						<s:textarea name="deductionCompName" cols="100" rows="1" readonly="true" /></td>
			<td><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
				onclick="javascript:callDropdown('paraFrm_deductionCompName',200,250,'EmployeeMonthlyEarningReport_f9deductions.action',event,'false','no','right')"></td>
			</tr>
		
		</table>
		</td>
	</tr>
	<tr>
		<td >
	<div id='bottomButtonTable'>
	
	</div>
		</td>
	</tr>
</table>
<SCRIPT LANGUAGE="JavaScript">

var obj = document.getElementById("topButtonTable").cloneNode(true);
document.getElementById("bottomButtonTable").appendChild(obj);

</SCRIPT>
<s:hidden name="reportType" />
<s:hidden name="reportAction" value='EmployeeMonthlyEarningReport_report.action'/>
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
	function reportFun(){
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="EmployeeMonthlyEarningReport_report.action";
				document.getElementById('paraFrm').submit();
			}
	}
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="EmployeeMonthlyEarningReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
	function validateFields(){
		var earnings = document.getElementById('paraFrm_earningCompId').value;
		var deductions = document.getElementById('paraFrm_deductionCompId').value;
		var division = document.getElementById('paraFrm_divisionId').value;
		var monthMisc = document.getElementById('paraFrm_earningMonth').value;
		var yearMisc = document.getElementById('paraFrm_earningYear').value;
		var report = document.getElementById('paraFrm_reportType').value;
		
		if(monthMisc==""){
			alert("Please select month");
			return false;
		}
		if(yearMisc==""){
			alert("Please enter year");
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
		/*if(report==""){
			alert("Please select report type");
			return false;
		}*/
		return true;
	}
	function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
		//alert(1);
		document.getElementById('paraFrm_reportType').value=type;
			//callDropdown(obj.name,200,250,'EmployeeMonthlyEarningReport_f9reportType.action','false');
			document.getElementById('paraFrm').action='EmployeeMonthlyEarningReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
	function alertsize(pixels){ 
pixels+=30; 
//alert('before'+ document.getElementById('myframe').style.height); 
document.getElementById('reportFrame').style.height=pixels+"px"; 
//alert('after'+ document.getElementById('myframe').style.height); 
document.body.style.offsetHeight="0px"; }
	
	

</script>