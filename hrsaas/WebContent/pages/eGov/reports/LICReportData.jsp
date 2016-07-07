
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="LICReport" validate="true" id="paraFrm" validate="true"
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
					<td width="93%" class="txt"><strong class="text_head">LIC
					Report</strong></td>
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
					<td>&nbsp;</td>
					<td>
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
				<tr>
					<td><s:if test="%{viewFlag}">
						<input type="button" class="token"  name = "mailReportBtn" onclick="mailReportFun();" value=" Mail Report "/>
					</s:if> <input type="button" class="reset" value="Reset "
						onclick="return resetField();" /></td>
					<td align="right" >
						Exported as :&nbsp;
						<img src="../pages/images/icons/file-pdf.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="return check('LICReport_report.action','Pdf');" title="PDF">
						<img src="../pages/images/icons/file-xls.png" class="iconImage" height="20" align="absmiddle" width="20"
						onclick="return check('LICReport_report.action','Xls');" title="Excel">
					</td>
				</tr>
			</table>
			<label></label></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>

							<td colspan="1" width="20%"><label id="from.month"
								name="from.month" ondblclick="callShowDiv(this);"><%=label.get("from.month")%></label><font
								color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:select theme="simple"
								name="fromMonth" cssStyle="width:152"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>

							<td colspan="1" width="20%"><label id="to.year"
								name="to.year" ondblclick="callShowDiv(this);"><%=label.get("to.year")%></label><font
								color="red">*</font>:</td>
							<td><s:textfield name="toYear"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
						</tr>

						<tr>

							<td colspan="1" width="20%"><label class="set"
								id="division1" name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							<font color="red">*</font>:</td>
							<td colspan="1" width="30%"><s:hidden name="divId" /> <s:textfield
								name="divName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LICReport_f9div.action');">

							</td>

							<td colspan="1" width="20%"><label class="set" id="branch1"
								name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="brnId" /> <s:textfield
								name="brnName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LICReport_f9branch.action');">
						</tr>



						<tr>
							</td>

							<td colspan="1" width="20%"><label class="set"
								id="department1" name="department"
								ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
							:</td>
							<td><s:hidden name="deptId" /> <s:textfield name="deptName"
								theme="simple" readonly="true" maxlength="50" size="25" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LICReport_f9dept.action');">
							</td>

							<td colspan="1" width="20%"><label class="set"
								id="employee.type" name="employee.type"
								ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="typeId" /> <s:textfield
								name="typeName" theme="simple" readonly="true" maxlength="50"
								size="25" /> <img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LICReport_f9type.action');">

							</td>

						</tr>

 

						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="pay.bill" name="pay.bill"
								ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
							:</td>
							<td><s:hidden name="paybillId" /> <s:textfield name="payBill"
								theme="simple" readonly="true" maxlength="50" size="25" /><img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LICReport_f9payBill.action');"></td>
								
							<td colspan="1" width="20%"><label class="set"
								id="debit.name" name="debit.name"
								ondblclick="callShowDiv(this);"><%=label.get("debit.name")%></label>
							<font color="red">*</font>:</td>
							<td><s:hidden name="debitId" /> <s:textfield name="debit"
								theme="simple" readonly="true" maxlength="50" size="25" /><img src="../pages/images/recruitment/search2.gif"
								class="iconImage" height="18" align="absmiddle" width="18"
								onclick="javascript:callsF9(500,325,'LICReport_f9debit.action');"></td>	
								
						</tr>
						<s:hidden name="repType" />
<!-- 
						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="report.type" name="report.type"
								ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
							<font color="red">*</font>:</td>
							<td colspan="1" width="30%">
							
							<s:select theme="simple"
								name="repType" cssStyle="width:152"
								list="#{'0':'Select --','Pdf':'Pdf','Xls':'Xls'}" />
							</td>
 -->
						</tr>

						

					</table>
					</td>
				</tr>
				
			</table>
			</td>
		</tr>
		<tr>
					<td colspan="3" width="100%" align="left">
					<!-- 
					<input type="button" class="token" onclick="return check();" value=" Generate Report " />
					 -->
					</td>
					</tr>
	</table>
</s:form>



<script><!--

function resetField(){

document.getElementById('paraFrm_fromMonth').value="";
document.getElementById('paraFrm_toYear').value="";
document.getElementById('paraFrm_divId').value="";
document.getElementById('paraFrm_divName').value="";
document.getElementById('paraFrm_brnId').value="";
document.getElementById('paraFrm_brnName').value="";
document.getElementById('paraFrm_deptId').value="";
document.getElementById('paraFrm_deptName').value="";
document.getElementById('paraFrm_typeName').value="";
document.getElementById('paraFrm_typeId').value="";
document.getElementById('paraFrm_payBill').value="";
///document.getElementById('paraFrm_desgId').value="";
document.getElementById('paraFrm_debit').value="";
document.getElementById('paraFrm_repType').value='0';

}


function check(name,type)
{
try{
	var fromMonth = document.getElementById('paraFrm_fromMonth').value;
	var year = document.getElementById('paraFrm_toYear').value;
	var divName = document.getElementById('paraFrm_divName').value;
	var debitName = document.getElementById('paraFrm_debit').value;
	document.getElementById('paraFrm_repType').value = type;
	
	if(fromMonth =="" || fromMonth=='0')
	{
	alert("Please select "+document.getElementById('from.month').innerHTML.toLowerCase());	 
	return false;
	}
	if(year=="" ||year=='null')
	{
	alert("Please enter "+document.getElementById('to.year').innerHTML.toLowerCase());	 
	return false;
	}
	if(divName=="" ||divName=='null')
	{
	alert("Please select "+document.getElementById('division1').innerHTML.toLowerCase());
	return false;
	}
	if(debitName ==" ")
	{
	alert("Please select "+document.getElementById('debit.name').innerHTML.toLowerCase());
	return false;
	}
	document.getElementById('paraFrm').action=name;	
	document.getElementById('paraFrm').submit();
	//callReport('LICReport_report.action');	
}
catch(e)
{
alert(e);
}

 
}
function mailReportFun(){
	
	var fromMonth = document.getElementById('paraFrm_fromMonth').value;
	var year = document.getElementById('paraFrm_toYear').value;
	var divName = document.getElementById('paraFrm_divName').value;
	var debitName = document.getElementById('paraFrm_debit').value;
	var repType = document.getElementById('paraFrm_repType').value;
	
	if(fromMonth =="" || fromMonth=='0'){
		alert("Please select "+document.getElementById('from.month').innerHTML.toLowerCase());	 
		return false;
	}
	if(year=="" ||year=='null'){
		alert("Please enter "+document.getElementById('to.year').innerHTML.toLowerCase());	 
		return false;
	}
	if(divName=="" ||divName=='null'){
		alert("Please select "+document.getElementById('division1').innerHTML.toLowerCase());	 
		return false;
	}
	if(debitName ==""){
		alert("Please select "+document.getElementById('debit.name').innerHTML.toLowerCase());	 
		return false;
	} else {
		callDropdown('mailReportBtn',200,250,'LICReport_f9reportType.action','false');
	}	
}
	
function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_toYear").value;
	if(yr==''){
		document.getElementById("paraFrm_toYear").value =year;
	}
}
   
getYear();

--></script>

