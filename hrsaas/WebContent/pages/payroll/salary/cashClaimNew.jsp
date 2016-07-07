<!-- @author: REEBA JOSEPH 16 NOVEMBER 2010 -->
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CashClaimNew" validate="true" id="paraFrm"
	validate="true" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cash
					Claim</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="20%"><label class="set" id="employee1"
						name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
						<font color="red">*</font> : </td>
					<td height="22" colspan="3"><s:hidden name="empId"
						theme="simple" /><s:hidden name="claimId" theme="simple" /><s:hidden
						name="status" theme="simple" /> <s:textfield theme="simple"
						readonly="true" size="10" name="empToken" /> <s:textfield
						label="%{getText('empName')}" theme="simple" size="77"
						readonly="true" name="empName" /> <s:if test="isApprove"></s:if><s:else>
						<s:if test="claim.generalFlag"></s:if>
						<s:else>
							<img src="../pages/common/css/default/images/search2.gif"
								width="16" height="15" id="ctrlHide"
								onclick="javascript:callsF9(500,325,'CashClaimNew_f9actionEmpId.action');">
						</s:else>
					</s:else></td>
				</tr>

				<tr>
					<td width="20%"><label class="set" id="branch" name="branch"
						ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
					<td width="27%" height="22"><s:textfield theme="simple"
						size="30" readonly="true" name="empCenter" /></td>

					<td width="15%" height="22" class="formtext"><label
						class="set" id="designation" name="designation"
						ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
					:</td>
					<td width="34%" height="22"><s:textfield theme="simple"
						size="20" readonly="true" name="empRank" /></td>
				</tr>

				<tr>
					<td width="20%"><label class="set" id="claim.date4" name="claim.date"
						ondblclick="callShowDiv(this);"><%=label.get("claim.date")%></label><font color="red">*</font> :</td>
					<td height="22"><s:textfield theme="simple" name="claimDate"
						onkeypress="return numbersWithHiphen();" size="30" /><s:a
						href="javascript:NewCal('paraFrm_claimDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16" id="ctrlHide">
					</s:a></td>
					<td width="15%" height="22" class="formtext"><label
						class="set" id="Stat" name="Stat" ondblclick="callShowDiv(this);"><%=label.get("Stat")%></label>
					:</td>
					<td width="34%" height="22"><s:textfield theme="simple"
						size="20" readonly="true" name="claimStatus" /></td>

				</tr>

				<tr>
					<td width="20%"><label class="set" id="particular"
						name="particular" ondblclick="callShowDiv(this);"><%=label.get("particular")%></label>
					:</td>
					<td colspan="3"><s:textarea theme="simple" cols="90" rows="3"
						name="particulars" /></td>
					<td>&nbsp;</td>
				</tr>

				<tr>
					<td align="center" colspan="4">
						<s:submit cssClass="search" action="CashClaimNew_view" theme="simple"
							value="    View" onclick="return check();" id="ctrlHide"/>
					</td>
				</tr>

			</table>
			</td>
		</tr>

		<s:if test="%{isDataAvailable}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="1" cellspacing="1"
					class="formbg">
					<tr>
						<td width="100%" colspan="4"><strong>Credit Head
						List</strong></td>
					</tr>
					<tr>
						<td width="5%" class="formth"><label class="set"
							id="particular1" name="particular"
							ondblclick="callShowDiv(this);"><%=label.get("particular")%></label></td>
						<td width="30%" class="formth"><label class="set"
							id="credit.name" name="credit.name"
							ondblclick="callShowDiv(this);"><%=label.get("credit.name")%></label></td>
						<s:if test="statusFlag"></s:if>
						<s:else>
							<td width="15%" class="formth"><label class="set"
								id="amountBal" name="amountBal" ondblclick="callShowDiv(this);"><%=label.get("amountBal")%></label></td>
						</s:else>
						<td width="20%" class="formth"><label class="set"
							id="cashAmount" name="cashAmount" ondblclick="callShowDiv(this);"><%=label.get("cashAmount")%></label></td>
						<td width="15%" class="formth"><label class="set"
							id="attached" name="attached" ondblclick="callShowDiv(this);"><%=label.get("attached")%></label>?</td>
					</tr>
					<%
						int i = 1;
					%>
					<%!int k = 0;%>
					<s:iterator value="creditList">
						<s:hidden name="creditId" value="%{creditId}" />

						<tr>
							<td width="5%" class="sortableTD"><%=i%></td>
							<td width="30%" class="sortableTD"><s:property
								value="creditName" /></td>
							<s:if test="claim.statusFlag"></s:if>
							<s:else>
								<td width="15%" class="sortableTD"><input type="text"
									name="balanceAmt" id="balanceAmt<%=i%>"
									value='<s:property value="balanceAmt"/>' maxlength="8"
									size="20" theme="simple" readonly="true" /></td>
							</s:else>
							<input type="hidden" name="onHoldAmt" id="onHoldAmt<%=i%>"
								value='<s:property value="onHoldAmt"/>' maxlength="8" size="20"
								theme="simple" readonly="true" />
							<td width="20%" class="sortableTD"><input type="text"
								name="claimAmt" id="claimAmt<%=i%>"
								value='<s:property value="claimAmt"/>' maxlength="8" size="20"
								theme="simple" onkeypress="return numbersOnly();"
								onkeyup="add(<%=i%>)" onblur="add(<%=i%>)" /></td>
							<td width="15%" class="sortableTD"><s:select
								theme="simple" name="hproof" cssStyle="width:50"
								list="#{'N':'No','Y':'Yes'}" /></td>
						</tr>

						<%
							i++;
						%>
					</s:iterator>
					<%
						k = i;
					%>
					
					<tr>
						<td width="5%" class="td_bottom_border">&nbsp;</td>
						<td width="30%" class="td_bottom_border"><strong><label
							class="set" id="claimtotal" name="claimtotal"
							ondblclick="callShowDiv(this);"><%=label.get("claimtotal")%></label>
						</strong></td>
						<td width="15%" class="td_bottom_border">&nbsp;</td>
						<td width="20%" class="td_bottom_border"><s:textfield
							theme="simple" readonly="true" name="totalAmt" id="totalAmt"
							size="20" /></td>
						<td width="15%" class="td_bottom_border">&nbsp;</td>

					</tr>

				</table>
				</td>
			</tr>
		</s:if>

	</table>

</s:form>

<script>

function add(id){
	var value='<%=k%>';
   	//alert(value);
   	var claimAmt1=0;
   	for(var i=1;i<value;i++){
    	var amt =  document.getElementById('claimAmt'+i).value;
      	if(amt==""){
      		document.getElementById('claimAmt'+i).value='0';
      	}
   	}
   	var claimAmt = document.getElementById('claimAmt'+id).value;
    var balAmt = document.getElementById('balanceAmt'+id).value;
    //alert('balAmt---'+balAmt);
    for(var i=1;i<value;i++){
    	var amt =  document.getElementById('claimAmt'+i).value;
      	//alert(amt);
    	claimAmt1 = eval(claimAmt1)+eval((amt*100)/100);
   	}
  	// alert(claimAmt1);
    document.getElementById('totalAmt').value=claimAmt1;
}
    
   
   
function check(){
	var employee=document.getElementById('employee').innerHTML.toLowerCase();
	var empID = document.getElementById('paraFrm_empId').value;
	
	if(empID==""){
		alert("Please Select "+employee);
		return false; 
	}	
}   
    
function reportFun(){
	var claimAppl = document.getElementById('paraFrm_claimId').value;
		
	if(claimAppl==""){
		alert("Please Select claim application");
		return false; 
	}	
	document.getElementById('paraFrm').action='CashClaimNew_report.action';	
	document.getElementById('paraFrm').submit();

}

function saveFun(){
	try{
	if(document.getElementById('paraFrm_claimDate').value==''){
		alert("Please enter "+document.getElementById('claim.date4').innerHTML);
		document.getElementById('paraFrm_claimDate').focus();
		return false;
	}else{
		if(!validateDate("paraFrm_claimDate","claim.date4")){
		return false;
		}
	}
	}catch(e){alert(e);}
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="CashClaimNew_save.action?status=X";	
	document.getElementById('paraFrm').submit();
}

function sendforapprovalFun(){
	try{
	if(document.getElementById('paraFrm_claimDate').value==''){
		alert("Please enter "+document.getElementById('claim.date4').innerHTML);
		document.getElementById('paraFrm_claimDate').focus();
		return false;
	}else{
		if(!validateDate("paraFrm_claimDate","claim.date4")){
		return false;
		}
	}
	}catch(e){alert(e);}
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action="CashClaimNew_save.action?status=P";	
	document.getElementById('paraFrm').submit();
}

function resetFun(){
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action='CashClaimNew_reset.action';	
	document.getElementById('paraFrm').submit();
}

function backFun(){
	document.getElementById('paraFrm').target="_self";
	document.getElementById('paraFrm').action='CashClaimNew_input.action';	
	document.getElementById('paraFrm').submit();
}

</script>
