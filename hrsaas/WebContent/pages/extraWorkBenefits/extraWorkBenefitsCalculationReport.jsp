<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<s:form action="ExtraWorkingBenefitCalReport" method="post"
	name="ExtraWorkBenifitCal" id="paraFrm" theme="simple" target="main">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td colspan="6">
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="100%" class="txt"><strong class="text_head">Extra
					Work Benefit Calculation Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr><!--
					<td width="78%" colspan="1">
					 <input type="button" class="token"
						onclick="return callReport();" value="   Report"
						 /> <s:submit cssClass="reset"
						action="ExtraWorkingBenefitCalReport_clear" theme="simple"
						value="    Reset"  />
					</td>
					--><td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
				<tr>
					<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						id='topButtonTable'>
						<tr valign="middle">
							<td nowrap="nowrap"><a href="#" onclick="resetFun();"> <img
								src="../pages/images/buttonIcons/Refresh.png" class="iconImage"
								align="absmiddle" title="Reset"> Reset </a>&nbsp;&nbsp;</td>
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
			</table>
			</td>
		</tr>
		<tr>
		<td colspan="4">
		<table width="100%" border="0" cellpadding="1" cellspacing="1" id="reportBodyTable" >
			<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
			<tr>
				<td colspan="4" class="formhead"><strong
					class="forminnerhead"> <label class="set"
					id="selectFinancialYear" name="selectFinancialYear"
					ondblclick="callShowDiv(this);"><%=label.get("selectFinancialYear")%></label>
				</strong></td>
			</tr>
			<tr>
				<td width="18%">
				<label class="set" id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label>:<font color="red">*</font></td>
				<td width="10%"><s:select name="month" headerKey="0"
					headerValue="--Select--" cssStyle="width:90"
					list="#{'1':'January','2':'February','3':'March','4':'April','5':'May','6':'June','7':'July','8':'August','9':'September','10':'October','11':'November','12':'December'}" />
			  </td>
				<td>&nbsp;</td>
				<td width="18%"><label class="set"
								id="finYearFrm" name="finYearFrm" ondblclick="callShowDiv(this);"><%=label.get("finYearFrm")%></label>:<font color="red">*</font></td>
			  <td width="30%"><s:textfield name="year" size="10"
					cssStyle="text-align: right" maxlength="4"
					onkeypress="return numbersOnly();"
					onblur="return checkYear('paraFrm_year', 'Year');"></s:textfield></td>
			 
			</tr>
			</table>
			</td>
			</tr>
			<tr>
					<td>
					<table width="100%" border="0" align="center" cellpadding="0"
						cellspacing="2" class="formbg">
			<tr>
				<td colspan="4"><b><label class="set"
					id="selectFilterOption" name="selectFilterOption"
					ondblclick="callShowDiv(this);"><%=label.get("selectFilterOption")%></label></b>
				</td>
			</tr>
			<tr>
					<td width="13%">Division :<font color="red">*</font></td>
			  <td width="10%"><s:hidden name="divisionCode" /><s:textarea cols="100" 
						name="divisionName" rows="1" theme="simple" readonly="true" /></td>
					<td width="10%"><img src="../pages/images/search2.gif" class="iconImage" 
						width="16"	height="16" 
						onclick="javascript:callDropdown('paraFrm_divisionName',200,250,'ExtraWorkingBenefitCalReport_f9division.action',event,'false','','right')"
						></td>
					<!--<td width="19%">Report Type</td>
			  <td width="48%"> <s:select theme="simple" name="reportType"
						headerKey="Pdf" headerValue="Pdf"
						list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
		  --></tr>
		  <tr>	
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden name="brnId" /> <s:textarea cols="100"
								name="brnName" rows="1" theme="simple" readonly="true" />
							</td>
							<td>	
								<img src="../pages/images/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_brnName',200,250,'ExtraWorkingBenefitCalReport_f9brn.action',event,'false','','right')">
							</td>	
						</tr>
						<tr>
							<td colspan="1" width="20%"><label  class = "set"  id="department" name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td><s:hidden name="deptId" /> <s:textarea cols="100" name="deptName"
								rows="1" theme="simple" readonly="true"  /> 
							</td>
							<td>	
								<img src="../pages/images/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_deptName',200,250,'ExtraWorkingBenefitCalReport_f9dept.action',event,'false','','right')">
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set"
								id="pay.bill" name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>
							:</td>
							<td><s:hidden name="payBillNo" /> <s:textarea cols="100"
									rows="1" theme="simple" readonly="true" name="payBillName" />
							</td>
							<td>	
								<img src="../pages/images/search2.gif" class="iconImage"
									height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_payBillName',200,250,'ExtraWorkingBenefitCalReport_f9payBill.action',event,'false','','right')">
							</td>
						</tr>
						<tr>
							<td colspan="1" width="20%"><label class="set" id="grade"
								name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
							:</td>
							<td colspan="1" width="30%"><s:hidden name="cadreCode" />
								<s:textarea cols="100" rows="1" theme="simple" readonly="true"
									name="cadreName" /> 
							</td>
							<td>	
								<img src="../pages/images/search2.gif"
									class="iconImage" height="16" align="absmiddle" width="16"
									onclick="javascript:callDropdown('paraFrm_cadreName',200,250,'ExtraWorkingBenefitCalReport_f9grade.action',event,'false','','right')">
							</td>
						</tr>
						</table>
						</td>
						</tr>
		</table>
		</td>
		</tr>
		<tr>
			<td colspan="3">
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
<s:hidden name="processCode" /> 

	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='ExtraWorkingBenefitCalReport_report.action' />
	
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

function validateFields(){

	 var month = document.getElementById('paraFrm_month').value;
	var year =document.getElementById('paraFrm_year').value;
	var div =document.getElementById('paraFrm_divisionCode').value;
	 	
		if(month==0)
		{
		alert("Please select month");
				return false;
		}
		if(year=="")
		{
				alert("Please enter year");
				return false;
		}
		if(div=="")
		{
				alert("Please select division");
				 
				return false;
		}
	return true;		 

	}

function resetFun(){
	document.getElementById('paraFrm').action='ExtraWorkingBenefitCalReport_clear.action';
	document.getElementById('paraFrm').submit();
}


function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='ExtraWorkingBenefitCalReport_reportMail.action';
		document.getElementById('paraFrm').submit();
	}	
}
 
function getYear(){
	var current = new Date();
	var year =current.getFullYear();
	var yr =document.getElementById("paraFrm_year").value;
	if(yr==''){
		document.getElementById("paraFrm_year").value =year;
	}
}

getYear();	
   
function alertsize(pixels){ 
	pixels+=30; 
	//alert('before'+ document.getElementById('myframe').style.height); 
	document.getElementById('reportFrame').style.height=pixels+"px"; 
	//alert('after'+ document.getElementById('myframe').style.height); 
	document.body.style.offsetHeight="0px"; 
}	
</script>