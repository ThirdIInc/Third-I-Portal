<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript"
	src="../pages/common/include/javascript/sorttable.js"></script>
<s:form action="GratuityDetails" validate="true" id="paraFrm" theme="simple" target="main">
	<s:hidden name="gratuityId"/>
	
	<%	String statusApp = ""; 
	try {
		statusApp = (String) request.getAttribute("applicationStatus");
	} finally {
		if (statusApp == null) {
			statusApp = "";
		}
	}
		
	%>
	<s:hidden name="statusCheck" value="<%=statusApp%>" id="paraFrm_statusCheck"/>
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td colspan="3" width="100%"><%@ include file="hrmHeader.jsp"%></td>
		</tr>
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Gratuity
					Details </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<s:if test="%{gratuityBean.generalFlag}">
					</s:if> <s:else>
					<input type="button" class="search"
						value="Search " width="18"
						onclick="javascript:callsF9(500,325,'GratuityDetails_f9empaction.action');">
					</s:else></td >
					<td colspan="3" align="right"><font color="red">*</font> Indicates Required</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" class="formbg">
				<tr>
					<td width="22%"><label name="employee" id="employee"
						ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
					:<font color="red">*</font></td>
					<td colspan="3">
					<s:hidden name="empId" />
					<s:textfield name="empToken" theme="simple"
						id="paraFrm_empToken" size="25" readonly="true" /> <s:textfield
						name="empName" id="paraFrm_empName" theme="simple" size="65"
						readonly="true" /></td>
				</tr>
				<tr>
					<td><label name="branch" id="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td><s:textfield name="centerName" size="25"  readonly="true" /></td>
					<td><label name="designation" id="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td><s:textfield name="rankName" readonly="true" size="25"  /></td>
				</tr>
				<tr>
					<td><label name="joiningDt" id="joiningDt"
						ondblclick="callShowDiv(this);"><%=label.get("joiningDt")%></label> :</td>
					<td><s:textfield name="joiningDate" size="25"  readonly="true" /></td>
					<td><label name="leavingDt" id="leavingDt"
						ondblclick="callShowDiv(this);"><%=label.get("leavingDt")%></label>	:</td>
					<td><s:textfield name="leavingDate" readonly="true" size="25"  /></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr class="td_bottom_border">
				<td colspan="4">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
						<tr>
							<td colspan="4">Gratuity Details</td>
						</tr>
						<tr>
							<td width="22%" colspan="1"><label class="set" name="service.tenure"
								id="service.tenure" ondblclick="callShowDiv(this);"><%=label.get("tenure")%></label>:&nbsp;</td>
							<td ><s:property value="serviceTenure"/>&nbsp; Years</td>
							<td width="15%"><label class="set" name="gratAvgSalary"
								id="gratAvgSalary"  ondblclick="callShowDiv(this);"><%=label.get("gratAvgSalary")%></label>:&nbsp;</td>
							<td ><s:textfield size="25" name="gratuityAvgSalary" 
								onkeyup="replaceBlankWithZero(this);" onkeypress="return numbersWithDot();" /></td>
						</tr>
						<tr>
							<td width="22%" ><label class="set" name="gratuityClaimed"
								id="gratuityClaimed" ondblclick="callShowDiv(this);"><%=label.get("gratuityClaimed")%></label>:&nbsp;</td>
							<td ><s:textfield size="25" name="gratuity" onkeypress="return numbersWithDot();" onkeyup="replaceBlankWithZero(this);"/></td>
							<td colspan="2"><input type="button" value="Calculate Gratuity" class="token" onclick="return calcGratuity();" /></td>
						</tr>
					</table>
				</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" class="formbg">
				<tr>
					<td width="22%"><label name="applicationFrom" id="applicationFrom"
						ondblclick="callShowDiv(this);"><%=label.get("applicationFrom")%></label> :</td>
					<td><s:textfield name="applicationFormNo" size="25" /></td>
				</tr>
				<tr>
					<td><label name="calimDt" id="calimDt"
						ondblclick="callShowDiv(this);"><%=label.get("calimDt")%></label>
					:</td>
					<td><s:textfield name="claimDate" readonly="true" size="25"  />
					<s:a href="javascript:NewCal('paraFrm_claimDate','DDMMYYYY');">
							<img src="../pages/images/recruitment/Date.gif" class="iconImage"
								height="18" align="absmiddle" width="18">
						</s:a>
					</td>
				</tr>
				<tr>
					<td><label name="status" id="status"
						ondblclick="callShowDiv(this);"><%=label.get("status")%></label>
					:<font color="red">*</font></td>
					<td>
					<input type="radio" name="appStatus" value="A" <%=statusApp.equals("A")?"checked":"" %> onclick="showHideBlock('A');checkStatus(this);">&nbsp; Approved &nbsp;
					<input type="radio" name="appStatus" value="R" <%=statusApp.equals("R")?"checked":"" %> onclick="showHideBlock('R');checkStatus(this);">&nbsp; Rejected</td>
				</tr>
				<tr id="rejectedBlock" style='display: <%= statusApp.equals("R")?"" :"none" %>;'>
					<td><label name="reason" id="reason"
						ondblclick="callShowDiv(this);"><%=label.get("reason")%></label>
					:<font color="red">*</font></td>
					<td><s:textarea rows="4" cols="60" name="rejectionReason" /></td>
				</tr>
				<tr id="approvedBlock" style='display: <%= statusApp.equals("A")?"" :"none" %>;'>
					<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2">
						<tr>
							<td width="22%"><label name="paybleAmt" id="paybleAmt"
								ondblclick="callShowDiv(this);"><%=label.get("paybleAmt")%></label>
							:<font color="red">*</font></td>
							<td><s:textfield name="amountPayable" onkeypress="return numbersWithDot();" size="25" /></td>
						</tr>
						<tr>
							<td><label name="payMode" id="payMode"
								ondblclick="callShowDiv(this);"><%=label.get("payMode")%></label>
							:<font color="red">*</font></td>
							<td><s:select theme="simple" name="paymentMode"
								cssStyle="width:147" list="#{'':'Select','CH':'Cash','CK':'Cheque','TR':'Transfer'}" /></td>
						</tr>
						<tr style="display: ">
							<td><label name="remark" id="remark"
								ondblclick="callShowDiv(this);"><%=label.get("remark")%></label>
							:<font color="red">*</font></td>
							<td><s:textarea rows="4" cols="60" name="approvalRemark" /></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
					<s:if test="%{gratuityBean.generalFlag}">
					</s:if> <s:else>
					<input type="button" name="saveBtn" class="save" value="Insert " width="18"	onclick="callSave();">
					<input type="button" class="token" value="Update " width="18" onclick="callUpdate()">
					<input type="button" class="token" value="Reset " width="18" onclick="callReset()"></s:else></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	onload();
	function onload(){
	var gratuityId = document.getElementById('paraFrm_gratuityId').value;
		if(gratuityId == ""){
			document.getElementById('saveBtn').disabled=false;
		}else{
			document.getElementById('saveBtn').disabled=true;
		}
	
	}
	function showHideBlock(chkValue){
		var applicableAmt = document.getElementById('paraFrm_gratuity').value;
		if(chkValue == "A"){
				document.getElementById('paraFrm_amountPayable').value=applicableAmt;
				document.getElementById('approvedBlock').style.display="";
				document.getElementById('rejectedBlock').style.display="none";
				document.getElementById('paraFrm_rejectionReason').value="";
			}else{
		    	document.getElementById('approvedBlock').style.display="none";
		    	document.getElementById('rejectedBlock').style.display="";
		    	document.getElementById('paraFrm_amountPayable').value="";
				document.getElementById('paraFrm_approvalRemark').value="";
				document.getElementById('paraFrm_paymentMode').value="";
		    }
 	}
 	
 	function calcGratuity(){
 		
		document.getElementById('paraFrm').action="GratuityDetails_calculateGratuity.action"
		document.getElementById('paraFrm').submit();  
	}
 	function callSave(){
 		if(!validateBlank()){
			return false;
		} else {
			document.getElementById('paraFrm').action="GratuityDetails_save.action"
			document.getElementById('paraFrm').submit();  
		}
	}
 	function callUpdate(){
		if(!validateBlank()){
			return false;
		} else {
			document.getElementById('paraFrm').action="GratuityDetails_save.action"
			document.getElementById('paraFrm').submit();  
		}
	}
 	function callReset(){
		document.getElementById('paraFrm').action="GratuityDetails_reset.action"
		document.getElementById('paraFrm').submit();  
	}
	
	function validateBlank(){
		var amountPayable = document.getElementById('paraFrm_amountPayable').value;
		var payMode = document.getElementById('paraFrm_paymentMode').value;
		var approveRemark = document.getElementById('paraFrm_approvalRemark').value;
		var rejectionRemark = document.getElementById('paraFrm_rejectionReason').value;
		if (document.getElementById('paraFrm_statusCheck').value ==""){
			alert("Please check status Approved/Rejected");
			return false
		}
		if (document.getElementById('paraFrm_statusCheck').value =="A"){
			if(amountPayable == ""){
				alert("Please enter amount payable");
				return false;
			}
			if(payMode == ""){
				alert("Please select mode of payment");
				return false;
			}
			
			if(approveRemark == ""){
				alert("Please enter remark");
				return false;
			}
		}
		if (document.getElementById('paraFrm_statusCheck').value =="R"){
			if(rejectionRemark == ""){
				alert("Please enter reason for rejection");
				return false;
			}
		}
		return true ; 
	}
	function checkStatus(id){
 		document.getElementById('paraFrm_statusCheck').value=id.value;
 	}
</script>