<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EpfMonthlyReconciliation" validate="true" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">EPF
					Monthly Reconciliation Report</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="22%">
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
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="2" id="reportBodyTable" >
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
							<td width="10%" nowrap="nowrap"><label class="set"
								id="months" name="months" ondblclick="callShowDiv(this);"><%=label.get("months")%></label>:<font
								color="red"> *</font></td>
							<td width="15%"><s:select name="month" headerKey="1"
								headerValue="----Select----"
								list="#{'01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td width="10%"><label class="set" id="finYearFrm"
								name="finYearFrm" ondblclick="callShowDiv(this);"><%=label.get("finYearFrm")%></label>:<font
								color="red"> *</font></td>
							<td width="15%"><s:textfield size="6" name="year"
								maxlength="4" onkeypress="return numbersonly(this);" /></td>
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
							<td class="formtext"><label class="set" id="division"
								name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font
								color="red"> *</font></td>
							<td><s:hidden name="divId" /> <s:textarea cols="100" name="divName"
								rows="1" theme="simple" readonly="true" />
							</td>
							<td>	
								<img src="../pages/images/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_divName',200,250,'EpfMonthlyReconciliation_f9division.action',event,'false','','right')">
							</td>
						</tr>
						<tr>	
							<td colspan="1" width="20%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden name="brnId" /> <s:textarea cols="100"
								name="brnName" rows="1" theme="simple" readonly="true" />
							</td>
							<td>	
								<img src="../pages/images/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="16"
								onclick="javascript:callDropdown('paraFrm_brnName',200,250,'EpfMonthlyReconciliation_f9brn.action',event,'false','','right')">
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
								onclick="javascript:callDropdown('paraFrm_deptName',200,250,'EpfMonthlyReconciliation_f9dept.action',event,'false','','right')">
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
									onclick="javascript:callDropdown('paraFrm_payBillName',200,250,'EpfMonthlyReconciliation_f9payBill.action',event,'false','','right')">
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
									onclick="javascript:callDropdown('paraFrm_cadreName',200,250,'EpfMonthlyReconciliation_f9grade.action',event,'false','','right')">
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
	</td>
	</tr>
	</table>

	<s:hidden name="reportType" />
	<s:hidden name="reportAction" value='EpfMonthlyReconciliation_report.action' />
	
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

function validateFields() {
	var from = document.getElementById('paraFrm_year').value;
	if(document.getElementById('paraFrm_divId').value==""){
		alert("Please select "+document.getElementById('division').innerHTML.toLowerCase());
		document.getElementById('paraFrm_divId').focus();
		return false;
	}
   	 
	if(document.getElementById('paraFrm_month').selectedIndex == 0){
		alert("Please select the  "+document.getElementById('months').innerHTML.toLowerCase());
		document.getElementById('paraFrm_month').focus();
		return false;
	}
	  
	if(from=="") {
		alert("Please Enter "+document.getElementById('finYearFrm').innerHTML.toLowerCase());
		return false;
	}
	if(!checkYear('paraFrm_year','finYearFrm')){
		return false;	 
	}
	    
	return true;
}

function numbersonly(myfield) {
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if ((("0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}

function resetFun(){
	document.getElementById('paraFrm').action='EpfMonthlyReconciliation_reset.action';
	document.getElementById('paraFrm').submit();
}
	
function add() {
	var from = document.getElementById('paraFrm_year').value;
	if(from==""){
		document.getElementById('paraFrm_year').value="";
	} else {
		var x=eval(from) +1;
		document.getElementById('paraFrm_year').value=x;
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

function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='EpfMonthlyReconciliation_reportMail.action';
		document.getElementById('paraFrm').submit();
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