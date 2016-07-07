<!-- Date: 12Dec'2011 Ganesh -->
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="MonthlyEDSummaryReport" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table class="formbg" width="100%">
		<tr>
			<td colspan="4" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td width="4%" valign="bottom" class="txt"><strong
						class="formhead"> <img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Monthly
					Earning/Deduction Summary Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" align="right" width="100%">
			<div align="right"><font color="red">*</font> Indicates
			Required</div>
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
		<!--<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><s:submit cssClass="reset"
						action="MonthlyEDSummaryReport_reset" theme="simple"
						value=" Reset"  /> <input type="button"
						class="token" name="mailReportBtn" onclick="mailReportFun(this);"
						value=" Mail Report " /></td>
					<td align="right">Export as: <img
						src="../pages/images/icons/file-pdf.png" class="iconImage"
						height="20" align="absmiddle" width="20"
						onclick="generateReport('Pdf');" title="PDF"> <img
						src="../pages/images/icons/file-xls.png" class="iconImage"
						height="20" align="absmiddle" width="20"
						onclick="generateReport('Xls');" title="Excel"> <img
						src="../pages/images/icons/file_extension_html.png"
						class="iconImage" height="20" align="absmiddle" width="20"
						onclick="generateReport('Html');" title="HTML"></td>
				</tr>
			</table>
			</td>
		</tr>
	--><tr >
			<td width="100%">
			<table width="100%"   border="0" id="reportBodyTable">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg" >

				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td colspan="6" class="formhead"><strong
								class="forminnerhead">Select Period </strong></td>
						</tr>
						<tr>
							<td colspan="1" width="15%"><label id="month1" name="month"
								ondblclick="callShowDiv(this);"><%=label.get("month")%></label>:<font
								color="red">*</font></td>
							<td colspan="1" ><s:select theme="simple"
								name="month" cssStyle="width:152"
								list="#{'0':'Select --','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" />
							</td>
							<td colspan="1" width="15%"><label class="set" id="year1"
								name="year" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>:<font
								color="red">*</font></td>
							<td ><s:textfield name="year"
								onkeypress="return numbersOnly();" theme="simple" maxlength="4"
								size="25" /></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="1"
				class="formbg">

				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="1"
						cellspacing="2">
						<tr>
							<td class="formhead"><strong
								class="forminnerhead">Select Filters </strong></td>
						</tr>
						<tr>
			<td width="15%"><label class="set" id="division1"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>:<font color="red">*</font></td>
			<td></td>
			<td></td>
			<td colspan="6"><s:hidden name="divCode"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="divName"  /></td>
			<td width="25%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_divName',350,250,'MonthlyEDSummaryReport_f9Div.action',event,'false','no','right')"></td>
		</tr>
		
		<tr>
			<td width="15%"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>:</td>
			<td></td>
			<td></td>
			<td colspan="6"><s:hidden name="branchCode"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="branchName"  /></td>
			<td width="25%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_branchName',350,250,'MonthlyEDSummaryReport_f9Brch.action',event,'false','no','right')"></td>
		</tr>
		
		<tr>
			<td width="15%"><label class="set" id="department"
						name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>:</td>
			<td></td>
			<td></td>
			<td colspan="6"><s:hidden name="deptCode"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="deptName"  /></td>
			<td width="25%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_deptName',350,250,'MonthlyEDSummaryReport_f9Dept.action',event,'false','no','right')"></td>
		</tr>
			
							<!--<td colspan="1" width="25%"><label class="set" id="grade"
										name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
									:<font color="red"></font></td>
									<td colspan="1" width="25%"><s:hidden name="cadreCode" />
									<s:textfield name="cadreName" size="25" readonly="true"
										cssStyle="background-color: #F2F2F2;" /> <img
										class="iconImage"
										src="../pages/images/recruitment/search2.gif" width="16"
										height="15" border="0"
										onclick="javascript:callsF9(500,325,'MonthlyEDSummaryReport_f9gradeaction.action');" />
									</td>


								-->
			<tr>
			<td width="15%"><label class="set" id="pay.bill"
						name="pay.bill" ondblclick="callShowDiv(this);"><%=label.get("pay.bill")%></label>:</td>
			<td></td>
			<td></td>
			<td colspan="6"><s:hidden name="paybillId"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="paybillName"  /></td>
			<td width="25%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_paybillName',350,250,'MonthlyEDSummaryReport_f9PayBill.action',event,'false','no','right')"></td>
		</tr>
				
			<tr>
			<td width="15%"><label class="set" id="grade"
						name="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>:</td>
			<td></td>
			<td></td>
			<td colspan="6"><s:hidden name="cadreCode"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="cadreName"  /></td>
			<td width="25%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_cadreName',350,250,'MonthlyEDSummaryReport_f9gradeaction.action',event,'false','no','right')"></td>
		</tr>
</tr>
				
			<tr>
			<td width="15%"><label class="set" id="cost.center"
						name="cost.center" ondblclick="callShowDiv(this);"><%=label.get("cost.center")%></label>:</td>
			<td></td>
			<td></td>
			<td colspan="6"><s:hidden name="costCenterId"/>
						<s:textarea cols="100" rows="1" theme="simple"	readonly="true" name="costCenterName"  /></td>
			<td width="25%"><img src="../pages/images/search2.gif" class="iconImage" height="16" align="absmiddle" width="16"
						onclick="javascript:callDropdown('paraFrm_costCenterName',350,250,'MonthlyEDSummaryReport_f9CostCenter.action',event,'false','no','right')"></td>
		</tr>
			
						<!--			
							
							
						<tr>
							<td colspan="1" width="20%"><label id="earning.head" name="earning.head" ondblclick="callShowDiv(this);"><%=label.get("earning.head")%></label>:<font color="red">*</font></td>
							<td width="25%"><s:checkbox name="checkEarningHead"
										onclick="return callCheck('checkEarningHead');"></s:checkbox></td>
							
							
							<td colspan="1" width="20%"><label id="deduction.head" name="deduction.head" ondblclick="callShowDiv(this);"><%=label.get("deduction.head")%></label>:<font color="red">*</font></td>
							
							<td width="25%"><s:checkbox name="checkDeductionHead"
										onclick="return callCheck('checkDeductionHead');"></s:checkbox></td>
										
							
								
								<s:hidden name="hiddenChechfrmId" id="hiddenChechfrmId"/>
								
								
						</tr>
						<tr>
							<td colspan="1" width="20%"><label id="consolidated.arrears" name="consolidated.arrears" ondblclick="callShowDiv(this);"><%=label.get("consolidated.arrears")%></label> :</td>
							<td width="25%"><s:checkbox name="checkConsolidatedArrears"
										></s:checkbox></td>
							
						</tr>
						
						
								<tr>

									<td colspan="1" width="25%"><label class="set"
										id="report.type" name="report.type"
										ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
									:<font color="red"></font></td>
									<td colspan="1" width="25%"><s:select theme="simple"
										name="report" cssStyle="width:152"
										list="#{'P':'Pdf','X':'Xls'}" /></td>
								<tr>
							-->
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
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
	<s:hidden name="report" /> <s:hidden name="checkConsolidatedArrears"  value="true"/>
	<s:hidden name="reportAction" value='MonthlyEDSummaryReport_getReport.action'/>

</s:form>
<script>
getYear();
function reset(){
document.getElementById("paraFrm_month").value=="0";
document.getElementById("paraFrm_year").value=="";
document.getElementById("paraFrm_divCode").value=="";
document.getElementById("paraFrm_divName").value=="";
document.getElementById("paraFrm_brnCode").value=="";
document.getElementById("paraFrm_brnName").value=="";

document.getElementById("paraFrm_employeeCode").value=="";
document.getElementById("paraFrm_employeeName").value=="";
document.getElementById("paraFrm_employeeToken").value=="";
document.getElementById("paraFrm_leaveType").value=="";
document.getElementById("paraFrm_LeaveName").value=="";
document.getElementById("paraFrm_leaveTypecode").value=="";

getYear();
}

function isNumber(no){
 	l=no.length;
 	for(i=0;i<l;i++){
 	 	c=no.charAt(i);
 	 	if(!(c>=0 || c<=9)|| c==' ' || c=='.'){return false;}
 	}
 	return true;
}

function callReport(type){
	if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm_report').value=type;
				callReportCommon(type);
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

function generateReport(type)
 {	
	try{
	
	//document.getElementById('paraFrm_report').value=type;
		if(!validateFields()){
				return false;
			} else {
				document.getElementById('paraFrm').target = "_self";
				document.getElementById('paraFrm').action="MonthlyEDSummaryReport_getReport.action";	
				document.getElementById('paraFrm').submit();
			}
		    
		    
	 }catch (e)
	 {
	 	alert(e);
	 }	
	 
	 
}

function reset(){
	document.getElementById("paraFrm_salReg_month").value="0";
	document.getElementById("paraFrm_salReg_year").value="";
	document.getElementById("paraFrm_salReg_report").value="0";
	getYear();
}

function callCheck(type){
try{
		if(type!="checkEarningHead"){
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_checkEarningHead').checked =false;
				document.getElementById('hiddenChechfrmId').value = "D";
			}else{
			document.getElementById('hiddenChechfrmId').value = "";
			}
		}else {
			if(document.getElementById('paraFrm_'+type).checked){
				document.getElementById('paraFrm_checkDeductionHead').checked =false;
				document.getElementById('hiddenChechfrmId').value = "E";
			}else{
			document.getElementById('hiddenChechfrmId').value = "";
			}
		}
		}catch(e){
			alert(e);
		}
}

   function clearCreditField(){
   try {

document.getElementById('paraFrm_creditCode').value = "";
	document.getElementById('paraFrm_creditName').value = "";

} catch(e) {
	///alert(e);
	}
}
function clearDebitField(){ 
	try {
	
	document.getElementById('paraFrm_debitCode').value = "";
document.getElementById('paraFrm_debitName').value = "";
	} catch(e) {
	///alert(e);
	}
}

function validateFields(){
		var name = ['paraFrm_year'];
		var label = ['year'];
		var flag = ["enter"];
		
		if(!validateBlank(name, label, flag)) {
			return false;
		}
		
		var month  =document.getElementById("paraFrm_month").value;
		var mont=document.getElementById('month1').innerHTML.toLowerCase();
		if(month =='0'){
	 	alert("Select "+mont);
	 	return false;
	 }
		var divNm   =document.getElementById("paraFrm_divCode").value;
		if(divNm==""){
	 	alert("Please select the Division");
	 	document.getElementById('paraFrm_divName').focus();
	 	return false;
	 
	 }
		return true;
	}

function mailReportFun(type){
		if(!validateFields()){
			return false;
		} else {
	//	alert(1);
		document.getElementById('paraFrm_report').value=type;
			//callDropdown(obj.name,200,250,'MonthlyEDSummaryReport_mailReport.action','false');
			document.getElementById('paraFrm').action='MonthlyEDSummaryReport_mailReport.action';
			document.getElementById('paraFrm').submit();
			//return true;
		}	
	}
	
	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="MonthlyEDSummaryReport_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
</script>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>