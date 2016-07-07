<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>

<s:form action="LoanApplication" id="paraFrm" theme="simple" target="main" validate="true">
<table class="formbg" width="100%">
	        <tr>
	        	<td colspan="3" width="100%">
	        		<table width="100%" border="0" align="center" cellpadding="2" cellspacing="2" class="formbg">
	        			 <tr>
				          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
				          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
				          <td width="93%" class="txt"><strong class="text_head">Calculate EMI</strong></td>
				          <td width="3%" valign="top" class="txt"><div align="right">
				          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
				        </tr>
	        		</table>
	        	</td>
        	</tr>
	
	<tr>
	<td colspan="3" width="100%">
	
	<table width="100%" border="0" align="right" cellpadding="2" cellspacing="1">
	
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2">
					
						<tr>
							<td>
							</td>
							<td width="22%">
								<div align="right"><font color="red">*</font> Indicates Required</div>
							</td>
						</tr>
					
				</table>
			<label></label></td>
		</tr>
		
		
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="1"
					class="formbg">
					<tr>
						<td width="100%" colspan="3">
							<table width="98%" border="0" align="center" cellpadding="2"
								cellspacing="2" >
								
								
								<tr>
									<td colspan="1" width="25%" ><label  class = "set"  id="loanAmnt1" name="loanAmnt" ondblclick="callShowDiv(this);"><%=label.get("loanAmnt")%></label><font color="red">*</font> :</td>
									<td colspan="1" width="25%" nowrap="nowrap">
										<s:textfield onkeypress="return numbersOnly();"  size="25" theme="simple" name="sanctionAmount" /></td>
								</tr>
								<tr>
								<td colspan="1" width="25%"><label class="set"
										name="payDate" id="payDate" ondblclick="callShowDiv(this);"><%=label.get("payDate")%></label>
									<font color="red">*</font> :</td>
									<td colspan="1" width="25%" nowrap="nowrap"><s:textfield
										size="25" theme="simple" name="paymentDate" id="paraFrm_paymentDate" maxlength="10"
										onkeypress="return numbersWithHiphen();" /> <s:a
										href="javascript:NewCal('paraFrm_paymentDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
									</tr>
									<tr>
									<td colspan="1" width="25%"><label class="set"
										id="startDate" name="startDate"
										ondblclick="callShowDiv(this);"><%=label.get("startDate")%></label>
									<font color="red">*</font> :</td>
									<td colspan="1" width="25%" nowrap="nowrap"><s:textfield
										size="25" theme="simple" name="startingDate" id="paraFrm_startingDate" maxlength="10"
										onkeypress="return numbersWithHiphen();" /> <s:a
										href="javascript:NewCal('paraFrm_startingDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
								</tr>
								
								<tr>

									<td colspan="1" width="25%"><label class="set"
										id="intType" name="intType" ondblclick="callShowDiv(this);"><%=label.get("intType")%></label>
									:</td>
									<s:hidden name="interestTypeHidden"></s:hidden>
									<td colspan="1" width="25%"><s:select name="interestType"
										onchange="return disableIntRate();" cssStyle="width:165"
										list="#{'N':'No Interest','F':'Flat Interest','R':'Reducing Principal','I':'Reducing Interest'}" /></td>
									<td colspan="1" width="25%" id='intRateTD'><label class="set"
										id="intrate" name="intrate" ondblclick="callShowDiv(this);"><%=label.get("intrate")%></label>
									:</td>
									<td colspan="1" width="25%" nowrap="nowrap" id='intRateTD1' ><s:textfield size="25" theme="simple" name="interestRate" onkeypress="return numbersWithDot();" /></td>
								</tr>
								
								<tr>
								<td colspan="1" width="25%" nowrap="nowrap"><label class="set" id="calcEMIBy" name="calcEMIBy"
										ondblclick="callShowDiv(this);"><%=label.get("calcEMIBy")%></label> :</td>
									<td colspan="1" width="25%" nowrap="nowrap"><s:radio name='calType' list="#{'I':'No. of Installment','E':'EMI Amount','P':'Principal Amount'}" onclick="callCalculateType(this);"/><s:hidden name='hiddenCalType'/></td>
								</tr>
								
								<tr id='otherTypeTR'><td colspan="1" width="25%" nowrap="nowrap"><label class="set"
										id="number.Installment" name="number.Installment"
										ondblclick="callShowDiv(this);"><%=label.get("number.Installment")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%" ><s:textfield size="25"
										theme="simple" name="installmentNumberFlat" maxlength="3"
										onkeypress="return numbersOnly();" onkeydown="return clearEmi();" onblur="return clearEmiBlur();" /></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
								</tr>
								<tr id='otherTypeTR1'>
									<td colspan="1" width="25%" nowrap="nowrap"><label class="set"
										id="emiAmount" name="emiAmount" 
										ondblclick="callShowDiv(this);"><%=label.get("emiAmount")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="emiAmount" onkeydown="return clearNoOfInstallment();" onblur="return clearNoOfInstallmentBlur();" maxlength="10"
										onkeypress="return numbersWithDot();" /></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
								</tr>
								<tr id='reducingIntTR'><td colspan="1" width="25%"><label class="set"
										id="number.Installment" name="number.Installment"
										ondblclick="callShowDiv(this);"><%=label.get("number.Installment")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%"><s:textfield size="25"
										theme="simple" name="installmentNumber" maxlength="3"
										onkeypress="return numbersOnly();" onkeydown="return clearEmi();" onblur="return clearEmiBlur();" /></td>
									<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
								</tr>
								<tr id='principalTR'>
									<td colspan="1" width="25%"><label class="set"
										id="princAmount" name="princAmount" 
										ondblclick="callShowDiv(this);"><%=label.get("princAmount")%></label><font
										color="red">*</font> :</td>
									<td colspan="1" width="25%"><s:textfield size="25" theme="simple" name="monthlyPrincAmount" maxlength="10"
										onkeypress="return numbersWithDot();" /></td>
               						<td colspan="1" width="25%"></td>
									<td colspan="1" width="25%"></td>
									
								</tr>
								<tr><td colspan="4" align="center"><input type="button" value=" Calculate " class="token" onclick="return validateCal();"/> <input type="button" class="reset" theme="simple" value=" Reset " onclick="return resetFields();"/> <input type="button" class="token" theme="simple" value=" Close " onclick="javascript:window.close();"/> </td> 
								</tr>
								
							</table>
							</td>
					</tr>
				</table>
			</td>
		</tr><s:hidden name="installmentFlag" />
	<s:if test="installmentFlag">
					<tr>
						<td colspan="4">
						
						<table width="100%" border="0" cellpadding="2" cellspacing="0"
							class="formbg">
							<tr>
								<td>
								
								<table width="98%" border="1" align="center" cellpadding="2"
									cellspacing="0">
									<tr>
										<td colspan="5" class="formhead"><strong
											class="forminnerhead"><label class="set"
											id="instaDtl" name="instaDtl" ondblclick="callShowDiv(this);"><%=label.get("instaDtl")%></label></strong></td>
									</tr>
									<tr>
											<td width="5%" class="formth"><label class="set"
												id="sno" name="sno" ondblclick="callShowDiv(this);"><%=label.get("sno")%></label></td>
											<td width="20%" align="left" class="formth"><label ondblclick="callShowDiv(this);" name="instaDate" id="instaDate" class="set">Installment Date</label></td>
											
											<td width="20%" class="formth" align="right">
											<%
												String lable = (String) request.getAttribute("lable");
												if (lable != null) {
													out.println(lable);
												}
											%>
											</td>
											<td width="20%" class="formth" align="right"><label
												class="set" id="instaAmnt" name="instaAmnt"
												ondblclick="callShowDiv(this);"><%=label.get("instaAmnt")%></label></td>
											<td width="20%" class="formth" align="right"><label
												class="set" id="emiAmnt" name="emiAmnt"
												ondblclick="callShowDiv(this);"><%=label.get("emiAmnt")%></label></td>
											

										</tr>
										<tr>
									<td class="formtext" colspan="5" >
									<div class="scrollF9" id="scrollDiv">
									<table width="100%"  border="0" cellpadding="2" cellspacing="2"
										class="sortable">
										<%
										int i = 0;
										%>


										<s:iterator value="installmentList">

											<tr>

												<td class="border2" width="5%" align="center"><%=i + 1%></td>
												<td class="border2" width="20%" align="center"><s:property
													value="monthYear" /><s:hidden name="monthYear" /></td>
												<td class="border2" width="20%" align="center"><s:property
													value="principalAmt" /><s:hidden name="principalAmt" /></td>
												<td class="border2" width="20%" align="center"><s:property
													value="interestAmt" /><s:hidden name="interestAmt" /></td>
												<td class="border2" width="20%" align="center"><s:property
													value="installmentAmt" /><s:hidden name="installmentAmt" /></td>
												
											</tr>


											<%
											i++;
											%>

										</s:iterator>
										<tr>
											
											<td class="border2" width="5%" align="center"><b><label
												class="set" id="total" name="total"
												ondblclick="callShowDiv(this);"><%=label.get("total")%></label></b></td>
											<td class="border2" width="20%" align="center">&nbsp;<s:property
												value="totalPrincipalAmt" /></td>
											<td class="border2" width="20%" align="center">&nbsp;<s:property
												value="totalInterestAmt" /></td>
											<td class="border2" width="20%" align="center">&nbsp;<s:property
												value="totalInstallmenteAmt" /></td>
											
										</tr>

									</table></div>
</td></tr>
								
								</table>
								</td>
							</tr>

						</table>
						</td>
					</tr>
				</s:if>
		
	</table>
	</td></tr></table>
</s:form>

<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>

	
	function callCalculateType(obj){
		var intType =document.getElementById("paraFrm_interestType").value ;
		if(obj.value=='I'){
		
			//document.getElementById('paraFrm_installmentNumberFlat').readOnly='';
			//document.getElementById('paraFrm_emiAmount').readOnly='true';
			if(intType=="I"){
				document.getElementById('otherTypeTR').style.display='';
			document.getElementById('reducingIntTR').style.display='none';
			}else{
			document.getElementById('otherTypeTR').style.display='none';
			document.getElementById('reducingIntTR').style.display='';
			}
			document.getElementById('otherTypeTR1').style.display='none';
			//document.getElementById('paraFrm_emiAmount').value='';
			document.getElementById('principalTR').style.display='none';
		}else if(obj.value=='E'){
			//document.getElementById('paraFrm_installmentNumberFlat').readOnly='true';
			document.getElementById('otherTypeTR').style.display='none';
			document.getElementById('otherTypeTR1').style.display='';
			document.getElementById('principalTR').style.display='none';
			document.getElementById('reducingIntTR').style.display='none';
			
		}else if(obj.value=='P'){
			document.getElementById('principalTR').style.display='';
			document.getElementById('otherTypeTR1').style.display='none';
			document.getElementById('otherTypeTR').style.display='none';
			document.getElementById('reducingIntTR').style.display='none';
			
			//document.getElementById('paraFrm_installmentNumberFlat').value='';
			//document.getElementById('paraFrm_installmentNumber').value='';
			//document.getElementById('paraFrm_emiAmount').value='';
		}
			
			
			document.getElementById('paraFrm_hiddenCalType').value=obj.value;
			//alert(document.getElementById('paraFrm_hiddenCalType').value);
	}
	function disableIntRate(){
	var intType =document.getElementById("paraFrm_interestType").value ;
		if(intType =='N'){
		//document.getElementById("paraFrm_interestRate").readOnly = 'true';
		document.getElementById("paraFrm_interestRate").value ="";
		document.getElementById("intRateTD").style.display='none';
		document.getElementById("intRateTD1").style.display='none';
		}else {
		//document.getElementById("paraFrm_interestRate").readOnly = '';
		document.getElementById("intRateTD").style.display='';
		document.getElementById("intRateTD1").style.display='';
		}
	if(intType!='I'){
	if(document.getElementById('paraFrm_hiddenCalType').value=="E"){
			document.getElementById('otherTypeTR').style.display='none';
			document.getElementById('otherTypeTR1').style.display='';
			document.getElementById('reducingIntTR').style.display ='none';
	}else{
		document.getElementById('paraFrm_hiddenCalType').value='I';
		document.getElementById('otherTypeTR').style.display='';
		document.getElementById('otherTypeTR1').style.display='none';
	}
			
			document.getElementById('principalTR').style.display='none';
			document.getElementById('paraFrm_calTypeP').disabled=true;
			document.getElementById('reducingIntTR').style.display ='none';
			document.getElementById('paraFrm_calTypeE').disabled='';
	}else{
	
		if(document.getElementById('paraFrm_hiddenCalType').value=="P"){
			document.getElementById('reducingIntTR').style.display ='none';
			document.getElementById('principalTR').style.display='';
			//document.getElementById('paraFrm_installmentNumber').value='';
		}else{
			document.getElementById('paraFrm_hiddenCalType').value='I';
			document.getElementById('reducingIntTR').style.display ='';
			document.getElementById('principalTR').style.display='none';
		}
			document.getElementById('otherTypeTR').style.display='none';
			document.getElementById('otherTypeTR1').style.display='none';
			document.getElementById('paraFrm_calTypeE').disabled=true;
			//document.getElementById('paraFrm_installmentNumberFlat').value='';
			//document.getElementById('paraFrm_emiAmount').value='';
			document.getElementById('paraFrm_calTypeP').disabled=false;
			
	}
	var calType=document.getElementById('paraFrm_hiddenCalType').value;
	document.getElementById('paraFrm_calType'+calType).checked=true;
	 
}
	function onloadFun(){
		disableIntRate();
		var intType =document.getElementById("paraFrm_interestType").value ;
		//alert('value=='+document.getElementById('paraFrm_hiddenCalType').value);
		//alert('intType=='+intType);
		if(document.getElementById('paraFrm_hiddenCalType').value!='E'){
		document.getElementById('paraFrm_calTypeI').checked= true;
		//document.getElementById('paraFrm_emiAmount').readOnly='true';
		//document.getElementById('paraFrm_installmentNumberFlat').readOnly='';
		if(intType=='I'){
			document.getElementById('otherTypeTR').style.display='none';
		}else{
			document.getElementById('otherTypeTR').style.display='';
		}
		
		document.getElementById('otherTypeTR1').style.display='none';
		}else{
			document.getElementById('paraFrm_calTypeE').checked= true;
			//document.getElementById('paraFrm_installmentNumberFlat').readOnly='true';
			//document.getElementById('paraFrm_emiAmount').readOnly='';
		}
	}
	onloadFun();
	
	function validateCal(){
		try{
		var intType = document.getElementById("paraFrm_interestType").value;
		var nolabel = document.getElementById('number.Installment').innerHTML;
						
		var fieldname  = ["paraFrm_sanctionAmount","paraFrm_paymentDate","paraFrm_startingDate"];
		var lableName  = ["loanAmnt1","payDate","startDate"];
		var flag       = ["enter","select","select"];
		
	 	if(!validateBlank(fieldname,lableName,flag))
	 	    return false;
	 	    
	 	 var noOfInstall=''; 
	 	 if(intType !='I'){
	 		noOfInstall = document.getElementById("paraFrm_installmentNumberFlat").value;
	 	 }else{
	 	 	noOfInstall = document.getElementById("paraFrm_installmentNumber").value;
	 	 }
	 	 	var emiAmount = document.getElementById("paraFrm_emiAmount").value;
	 	 	var principalAmt = document.getElementById("paraFrm_monthlyPrincAmount").value;
	 	 	
	 	 	var emiLabel = document.getElementById('emiAmount').innerHTML;
	 	 	var principalAmtLabel = document.getElementById('princAmount').innerHTML;
	 	 	
	 	 	if(document.getElementById('paraFrm_calTypeE').checked){
	 	 		if(emiAmount ==""){
	 	 		alert("Please enterrrrr "+emiLabel);
	 	 		document.getElementById("paraFrm_emiAmount").focus();
	 	 		return false;
	 	 	}	
	 	 	}else if(document.getElementById('paraFrm_calTypeP').checked){
	 	 		if(principalAmt ==""){
	 	 		alert("Please enter "+principalAmtLabel);
	 	 		document.getElementById("paraFrm_monthlyPrincAmount").focus();
	 	 		return false;
	 	 	}	
	 	 	}
	 	 	else{
	 	 		if(noOfInstall==""){
	 	 		alert("Please enter "+nolabel);
	 	 		document.getElementById("paraFrm_monthlyPrincAmount").focus();
	 	 		 if(intType !='I'){
	 				document.getElementById("paraFrm_installmentNumberFlat").focus();
	 			 }else{
	 	 			document.getElementById("paraFrm_installmentNumber").focus();
	 	 		}
	 	 		return false;
	 	 	}	
	 	 	}
		
	 	
	 	
	 	if(document.getElementById("paraFrm_interestType").value !='N'){
	 	    if(document.getElementById("paraFrm_interestRate").value ==""){
	 	    alert("Please enter "+document.getElementById('intrate').innerHTML);
	 	    document.getElementById("paraFrm_interestRate").focus();
	 	    return false;
	 	 		}
	 	 }
				
		document.getElementById('paraFrm').target ='win';
		document.getElementById('paraFrm').action='LoanApplication_calculateEMI.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ='win';
		
		}catch(e){alert(e);}
			
	}
	function resetFields(){
		document.getElementById('paraFrm_installmentNumberFlat').value='';
		document.getElementById('paraFrm_installmentNumber').value='';
		document.getElementById('paraFrm_sanctionAmount').value='';
		document.getElementById('paraFrm_emiAmount').value='';
		document.getElementById("paraFrm_interestType").value='N';
		document.getElementById('paraFrm_hiddenCalType').value = 'I';
		document.getElementById('paraFrm_installmentFlag').value=false;
		onloadFun();
		document.getElementById('paraFrm').target ='win';
		document.getElementById('paraFrm').action='LoanApplication_openCalEMI.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target ='win';
		
		
		
	}
	/*function setYear(){
		var year = new Date().getFullYear();
		
		document.getElementById('paraFrm_year').value = year;
	}
setYear();*/
</script>
  
</script>