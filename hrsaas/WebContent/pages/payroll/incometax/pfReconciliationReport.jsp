<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="PFReconciliation" validate="true" id="paraFrm" validate="true" theme="simple">
	<table width="100%" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" class="formbg">
				<tr>
					<td width="3%" valign="bottom" class="txt"><strong
						class="formhead"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">PF Reconciliation Report</strong></td>
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
				<div align="right"><font color="red">*</font> Indicates Required</div>
			</td>
		</tr>
		<tr>
					<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id='topButtonTable'>
							<tr valign="middle">
								<td nowrap="nowrap"><a href="#" onclick="callReset();">
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
			
	
		
		<tr>
			<td colspan="3">
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="2" class="formbg" id="reportBodyTable">
			<tr>
				<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" >
					
					<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Period</strong></td>
						</tr>
					
					<tr>
					<td colspan="1" width="20%"><label class="set" id="frmYear"
						name="frmYear" ondblclick="callShowDiv(this);"><%=label.get("frmYear")%></label><font
						color="red">*</font> :</td>
					<td><s:textfield name="fromYear"
						onkeypress="return numbersOnly();" onblur="add()" theme="simple"
						maxlength="4" size="25" /></td>
					<td colspan="1" width="20%"><label class="set" id="toyear"
						name="toyear" ondblclick="callShowDiv(this);"><%=label.get("toyear")%></label><font
						color="red">*</font> :</td>
					<td><s:textfield name="toYear"
						onkeypress="return numbersOnly();" theme="simple" maxlength="4"
						size="25" /></td>
				</tr>
					
					</table>
				</td>
			</tr>
			<tr>
				<td colspan="5">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="formbg" >
					
					<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead">Select Filter</strong></td>
						</tr>
					
					<tr>
					<td colspan="1" width="20%"><label class="set" id="division"
						name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:hidden name="divId" /><s:hidden name="divisionAbbrevation" /> <s:textfield
						name="divName" theme="simple" readonly="true" maxlength="50"
						size="25" /> <img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'PFReconciliation_f9division.action');">
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					</tr>
					
					</table>
				</td>
			</tr>
				
				
					<!--<td colspan="1" width="20%"><label class="set" id="branch"
						name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
					:</td>
					<td colspan="1" width="30%"><s:hidden name="brnId" /> <s:textfield
						name="brnName" theme="simple" readonly="true" maxlength="50"
						size="25" /> <img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'Form6A_f9brn.action');">
					</td>
				-->
				<!--<tr>
					<td colspan="1" width="20%"><label class="set" id="department"
						name="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
					:</td>
					<td><s:hidden name="deptId" /> <s:textfield name="deptName"
						theme="simple" readonly="true" maxlength="50" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" class="iconImage"
						height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'Form6A_f9dept.action');">
					</td>

					<td colspan="1" width="20%"><label class="set"
						id="employee.type" name="employee.type"
						ondblclick="callShowDiv(this);"><%=label.get("employee.type")%></label>
					:</td>
					<td colspan="1" width="30%"><s:hidden name="typeId" /> <s:textfield
						name="typeName" theme="simple" readonly="true" maxlength="50"
						size="25" /> <img src="../pages/images/recruitment/search2.gif"
						class="iconImage" height="18" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'Form6A_f9type.action');">

					</td>
				</tr>
			--></table>
			</td>
		</tr>
		<tr>
					<td colspan="3">
						<table width="100%" border="0" cellpadding="0" cellspacing="0"
							id='topButtonTable'>
							<tr valign="middle">
								<td nowrap="nowrap"><a href="#" onclick="callReset();">
									<img src="../pages/images/buttonIcons/Refresh.png"
										class="iconImage" align="absmiddle" title="Reset"> Reset
									</a>&nbsp;&nbsp;
								</td>
								<td width="100%"><%@ include
									file="/pages/common/reportButtonPanelBottom.jsp"%>
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
		<s:hidden name="reportType" />
		<s:hidden name="reportAction" value='PFReconciliation_report.action' />
	</table>
</s:form>



<script>
 function callReset(){
 document.getElementById('paraFrm').target="main";
	document.getElementById('paraFrm').action='PFReconciliation_reset.action';
	document.getElementById('paraFrm').submit();
	
 }
 function mailReportFun(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		document.getElementById('paraFrm').action='PFReconciliation_mailReport.action';
		document.getElementById('paraFrm').submit();
	}	
}
 
	function validateFields()
	{
		if(document.getElementById("paraFrm_fromYear").value==""){
 		alert("Please enter the "+document.getElementById('frmYear').innerHTML.toLowerCase());
 		return false;
 		}
 	if(document.getElementById("paraFrm_toYear").value==""){
 		alert("Please enter the "+document.getElementById('toyear').innerHTML.toLowerCase());
 		return false;
 		}
 	if(document.getElementById("paraFrm_divName").value==""){
 		alert("Please select the "+document.getElementById('division').innerHTML.toLowerCase());
 		return false;
 		}
 		return true;
	}
 function callReport(type){
	if(!validateFields()){
		return false;
	} else {
		document.getElementById('paraFrm_reportType').value=type;
		callReportCommon(type);
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
	
	
</script>