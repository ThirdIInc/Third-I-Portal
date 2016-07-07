<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="CashClaim" target="main" theme="simple" validate="true"
	id="paraFrm">
	<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Cash Claim</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0">
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
				<s:if test="isApprove"></s:if>
					<td width="78%"><s:else><s:if test="%{isSaveButton}"><s:submit cssClass="save" action="CashClaim_save" value="   Save"
						onclick="return callSave();" /></s:if>
						<input type="button" class="search" theme="simple" value="   Search"
						onclick="javascript:callsF9(500,325,'CashClaim_f9viewSavedClaim.action');" />
						<s:if test="claim.viewFlag"><input type="button" class="token"	onclick="callReport()"  value=" Report " /></s:if>
					 <s:submit cssClass="reset"	action="CashClaim_reset" value="   Reset" />
					
					</s:else>
					
					
					
					</td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			<label></label></td>

		</tr>

		<tr>
			<td colspan="4"><img
				src="../pages/common/css/default/images/space.gif" width="5"
				height="4" /></td>
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="2">
						
						<tr>
							<td width="25%"><label  class = "set"  id="employee" name="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label><span class="formtext">
							:</span><font color="red">*</font></td>
							<td height="22" colspan="3"><s:hidden name="empId"
								theme="simple" /><s:hidden name="claimId"
								theme="simple" /><s:hidden name="status"
								theme="simple" /> <s:textfield theme="simple" readonly="true"
								size="10" name="empToken" /> <s:textfield
								label="%{getText('empName')}" theme="simple" size="77"
								readonly="true" name="empName" /> <s:if test="isApprove"></s:if><s:else><s:if test="claim.generalFlag"></s:if>
								<s:else><img
								src="../pages/common/css/default/images/search2.gif" width="16"
								height="15"
								onclick="javascript:callsF9(500,325,'CashClaim_f9actionEmpId.action');"></s:else></s:else>
							</td>
						</tr>

						<tr>
							<td width="25%"><label  class = "set"  id="branch" name="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td width="27%" height="22"><s:textfield theme="simple"
								size="30" readonly="true" name="empCenter" /></td>

							<td width="15%" height="22" class="formtext"><label  class = "set"  id="designation" name="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
							<td width="34%" height="22"><s:textfield theme="simple"
								size="20" readonly="true" name="empRank" /></td>
						</tr>

						<tr>
							<td width="25%"><label  class = "set"  id="date" name="date" ondblclick="callShowDiv(this);"><%=label.get("date")%></label> :</td>
							<td height="22"><s:textfield theme="simple" name="claimDate" onkeypress="return numbersWithHiphen();" size="30" /><s:a href="javascript:NewCal('paraFrm_claimDate','DDMMYYYY');">
								<img src="../pages/images/recruitment/Date.gif"	class="iconImage" height="16" align="absmiddle" width="16">
							</s:a></td>
							<td width="15%" height="22" class="formtext"><label  class = "set"  id="Stat" name="Stat" ondblclick="callShowDiv(this);"><%=label.get("Stat")%></label> :</td>
							<td width="34%" height="22"><s:textfield theme="simple"
								size="20" value="PENDING" readonly="true" name="claimStatus" /></td>

						</tr>

						<tr>
								<td width="25%" colspan="1"><label  class = "set"  id="applForDivision" name="applForDivision" ondblclick="callShowDiv(this);"><%=label.get("applForDivision")%></label> : <font color="red">*</font></td>
								<td width="75%" colspan="3"><s:textfield theme="simple"	size="25" readonly="true" name="applForDivision" />
								<img src="../pages/common/css/default/images/search2.gif" width="16" height="15"
								onclick="javascript:callsF9(500,325,'CashClaim_f9actionApplDivision.action');"><s:hidden name='applForDivisionCode'/></td>
						</tr>
				
						<tr>
							<td width="24%"><label  class = "set"  id="particular" name="particular" ondblclick="callShowDiv(this);"><%=label.get("particular")%></label> :</td>
							<td colspan="3" ><s:textarea theme="simple" cols="90" rows="3"
								name="particulars" /></td>
							<td>&nbsp;</td>
						</tr>
				
						<tr>
							<td align="center" colspan="4">
								<s:if test="%{viewCashClaim}">
								<s:submit cssClass="search" action="CashClaim_view" theme="simple"
								value="    View" onclick="return check();" /></s:if>
							</td>
						</tr>
					
					</table>


					</td>
				</tr>

			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		<s:if test="%{isDataAvailable}">
			<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							<tr>
								<td width="100%" colspan="4"><strong>Credit Head List</strong></td>
							</tr>
							<tr>
								<td width="5%" class="formth"><label  class = "set"  id="particular1" name="particular" ondblclick="callShowDiv(this);"><%=label.get("particular")%></label></td>
								<td width="30%" class="formth"><label  class = "set"  id="credit.name" name="credit.name" ondblclick="callShowDiv(this);"><%=label.get("credit.name")%></label></td>
								<s:if test="statusFlag"></s:if><s:else><td width="15%" class="formth"><label  class = "set"  id="amountBal" name="amountBal" ondblclick="callShowDiv(this);"><%=label.get("amountBal")%></label></td>
								</s:else>
								<td width="20%" class="formth"><label  class = "set"  id="cashAmount" name="cashAmount" ondblclick="callShowDiv(this);"><%=label.get("cashAmount")%></label></td>
								<td width="15%" class="formth"><label  class = "set"  id="attached" name="attached" ondblclick="callShowDiv(this);"><%=label.get("attached")%></label>?</td>
							</tr>
							<%
							int i = 1;
							%>
							<%!int k = 0;%>
							<s:iterator value="claim.att">
								<s:hidden name="creditId" value="%{creditId}" />

								<tr>
									<td width="5%" class="td_bottom_border"><%=i%></td>
									<td width="30%" class="td_bottom_border"><s:property
										value="creditName" /></td>
									<s:if test="claim.statusFlag"></s:if><s:else>	
									<td width="15%" class="td_bottom_border"><input type="text" name="balanceAmt"
										id="balanceAmt<%=i%>" value='<s:property value="balanceAmt"/>'
										maxlength="8" size="20" theme="simple" readonly="true" /></td>
										</s:else>
									<input type="hidden" name="onHoldAmt"
										id="onHoldAmt<%=i%>" value='<s:property value="onHoldAmt"/>'
										maxlength="8" size="20" theme="simple"  readonly="true"	/>
									<td width="20%" class="td_bottom_border"><input type="text" name="claimAmt"
										id="claimAmt<%=i%>" value='<s:property value="claimAmt"/>'
										maxlength="8" size="20" theme="simple" 
										onkeypress="return numbersOnly();" onkeyup="add(<%=i%>)" onblur="add(<%=i%>)" /></td>
									<td width="15%" class="td_bottom_border"><s:select theme="simple" name="hproof"
								 		cssStyle="width:50"
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
							<td width="30%" class="td_bottom_border"><strong><label  class = "set"  id="claimtotal" name="claimtotal" ondblclick="callShowDiv(this);"><%=label.get("claimtotal")%></label> </strong></td>
							<td width="15%" class="td_bottom_border">&nbsp;</td>
							<td width="20%" class="td_bottom_border"><s:textfield theme="simple" readonly="true"	name="totalAmt" id="totalAmt" size="20" /></td>
							<td width="15%" class="td_bottom_border">&nbsp;</td>
							
						</tr>
						</table>
						</td>
					</tr>

				</table>
				</td>
			</tr>
		</s:if>
		<tr>
			<td colspan="3">&nbsp;</td>
		</tr>
		
		
		<tr>
				<td colspan="3">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="formbg">
					<tr>
						<td class="formtext">
						<table width="100%" border="0" cellpadding="1" cellspacing="1">
							
							<tr>
								<td width="100%" colspan="4"><strong><label  class = "set"  id="vchList" name="vchList" ondblclick="callShowDiv(this);"><%=label.get("vchList")%></label></strong></td>
							</tr>
							
							<tr>
								<td width="5%" class="formth"><label  class = "set"  id="sr.no." name="sr.no." ondblclick="callShowDiv(this);"><%=label.get("sr.no.")%></label></td>
								<td width="30%" class="formth"><label  class = "set"  id="vchHead" name="vchHead" ondblclick="callShowDiv(this);"><%=label.get("vchHead")%></label></td>
								<td width="15%" class="formth"><label  class = "set"  id="vchAmt" name="vchAmt" ondblclick="callShowDiv(this);"><%=label.get("vchAmt")%></label></td>
								<td width="20%" class="formth"><label  class = "set"  id="vchProof" name="vchProof" ondblclick="callShowDiv(this);"><%=label.get("vchProof")%></label></td>
							</tr>
							<%
							int m = 1;
							%>
							<%!int n = 0;%>
							<s:iterator value="claim.voucherList">

								<tr>
									<td width="5%" class="td_bottom_border"><%=m%></td>
									<td width="30%" class="td_bottom_border"><s:property
										value="vchName" /><s:hidden name="vchName" /><s:hidden name="vchCode" /></td>
									<td width="15%" class="td_bottom_border"><input type="text" name="vchAmt"
										id="vchAmt<%=m%>" onkeypress="return numbersWithDot();" onkeyup="return addVch(<%=m%>)" onblur="return addVch(<%=m%>)" value='<s:property value="vchAmt"/>'	maxlength="8" size="20" theme="simple"</td>
									<td width="15%" class="td_bottom_border"><s:select theme="simple" name="vchProof"
								 		cssStyle="width:50"
										list="#{'N':'No','Y':'Yes'}" /></td>	
								</tr>

								<%
								m++;
								%>
							</s:iterator>
							<%
							n = m;
							%>
							<tr>
							<td width="5%" class="td_bottom_border">&nbsp;</td>
							<td width="30%" class="td_bottom_border"><strong><label  class = "set"  id="vchTotal" name="vchTotal" ondblclick="callShowDiv(this);"><%=label.get("vchTotal")%></label> </strong></td>
							<td width="20%" class="td_bottom_border"><s:textfield theme="simple" readonly="true"	name="totalVchAmt" size="20" /></td>
							<td width="15%" class="td_bottom_border">&nbsp;</td>
							
						</tr>
						</table>
						</td>
					</tr>

				</table>
				</td>
			</tr>
		
	</table></td></tr></table>

</s:form>

<script>

function add(id)
   {
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
    
    function addVch(id)
   {
   var listLength='<%=n%>';
   //alert(listLength);
   var vchAmt=0;
   for(var i=1;i<listLength;i++){
      var amt =  document.getElementById('vchAmt'+i).value;
      if(amt==""){
      	document.getElementById('vchAmt'+i).value='0';
      }
   }
   
      for(var i=1;i<listLength;i++){
      var amt =  document.getElementById('vchAmt'+i).value;
      //alert(amt);
    vchAmt = eval(vchAmt)+eval((amt*100000000000000000000000)/100000000000000000000000);
    
   }
  // alert(claimAmt1);
  //alert(vchAmt);
     document.getElementById('paraFrm_totalVchAmt').value=vchAmt;
    }
   
function check()
	{
	
	var employee=document.getElementById('employee').innerHTML.toLowerCase();
		var empID = document.getElementById('paraFrm_empId').value;
		
		if(empID==""){
		alert("Please Select "+employee);
		return false; 
		}	
	}   
    
function callReport(){
	var claimAppl = document.getElementById('paraFrm_claimId').value;
		
		if(claimAppl==""){
		alert("Please Select claim application");
		return false; 
		}	
			document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='CashClaim_report.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 	
	

}

function callSave(){
	if(document.getElementById('paraFrm_claimStatus').value!='PENDING'){
		alert("You can't update the application.");
		return false;
	}
	if(document.getElementById('paraFrm_claimDate').value==''){
		alert("Please enter "+document.getElementById('date').innerHTML);
		document.getElementById('paraFrm_claimDate').focus();
		return false;
	}else{
		if(!validateDate("paraFrm_claimDate","date")){
		return false;
		}
	}
	if(document.getElementById('paraFrm_applForDivision').value==''){
		alert('Please select '+document.getElementById('applForDivision').innerHTML);
		document.getElementById('paraFrm_applForDivision').focus();
		return false;
	}
	
}

</script>
