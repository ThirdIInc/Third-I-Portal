<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CreditMaster" validate="true" id="paraFrm"
	name="paraFrm" theme="simple">
	<s:hidden name="creditMaster.CreditCode" />
	<table width="100%" border="0" align="right" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Credit
					Master </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td colspan="5" class="text_head"><strong
						class="forminnerhead">Credit Master</strong></td>
					<s:hidden name="creditCode" />
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.name" id="credit.name"
						ondblclick="callShowDiv(this);"><%=label.get("credit.name")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield size="30"
						maxlength="55" theme="simple" name="creditMaster.CreditName"
						onkeypress="return allCharacters();" /></td>
				
					<td width="20%" colspan="1"><label class="set"
						name="credit.abbr" id="credit.abbr"
						ondblclick="callShowDiv(this);"><%=label.get("credit.abbr")%></label>
					<font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:textfield maxlength="10"
						size="30" label="%{getText('creditAbbr')}" theme="simple"
						name="creditMaster.CreditAbbr"
						onkeypress="return allCharacters();" /></td>
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.type" id="credit.type"
						ondblclick="callShowDiv(this);"><%=label.get("credit.type")%></label>:
					</td>
					<td width="30%">
						<s:select name="CreditType" cssStyle="widht:175" theme="simple" list="#{'1':'As per Salary Days','2':'Fixed','3':'Formula'}" onchange="showFormulaButton();"/>
					</td>
				</tr>
				
				
				<tr id="showFormulaButtonID">
					<td width="20%" colspan="1"><label class="set"
						name="credit.formula" id="credit.formula"
						ondblclick="callShowDiv(this);"><%=label.get("credit.formula")%></label>:
					</td>
					<td width="30%">
						<s:textfield theme="simple" name="calculatedCreditFormulaValue"  maxlength="50" readonly="true"  />
						<input type="button" class="token" name="calculateFormula" id="paraFrm_calculateFormula" value="Formula" 
							onclick="callFormulaBuilder('paraFrm_calculatedCreditFormulaValue');"/>	
					</td>
					<td width="20%" colspan="1"><label class="set"
						name="credit.creditmaxcap" id="credit.creditmaxcap"
						ondblclick="callShowDiv(this);"><%=label.get("credit.creditmaxcap")%></label>:</td>
					<td width="30%">
						<s:textfield size="30" theme="simple" name="creditmaxcap"  maxlength="10"  onkeypress="return numbersWithDot()"  />
					</td>
					
				</tr>
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.payfl" id="credit.payfl"
						ondblclick="callShowDiv(this);"><%=label.get("credit.payfl")%></label>:
					</td>
					<td colspan="2" width="55%" ><s:select name="Creditpayflag"
						theme="simple" cssStyle="widht:175" list="#{'Y':'YES','N':'NO'}" /></td>
				</tr>
				<tr>
					<td width="20%"><label class="set" name="credit.appFor"
						id="credit.appFor" ondblclick="callShowDiv(this);"><%=label.get("credit.appFor")%></label>:
					</td>
					<td colspan="1">
						<input type="checkbox" name="incomeTaxCheck" id="incomeTaxCheck" onclick="setTaxable();" /> 
							Income Tax<s:hidden name="taxable" />
						<input type="checkbox" name="esicCheck"
						id="esicCheck" onclick="setEsic();" /> ESIC<s:hidden name="esic" />
					</td>
					<td colspan="2"><input type="checkbox" name="proTaxCheck" id="proTaxCheck"
						onclick="setProTax();" /> Professional Tax<s:hidden name="proTax" />
						<input type="checkbox" name="arrearsCheck"
						id="arrearsCheck" onclick="setAppArrears();" /> Arrears<s:hidden
						name="appArrears" />
					</td>
				</tr>
				
				<tr>
					<td></td>
					<td colspan="1"><input type="checkbox" name="lwfCheck"
						id="lwfCheck" onclick="setLWF();" /> LWF<s:hidden name="lwf" />
					</td>
					<td></td>
				</tr>
				<!--  
							<tr>
								<td width="20%" colspan="1"><label  class = "set" name="credit.apar" id="credit.apar" ondblclick="callShowDiv(this);"><%=label.get("credit.apar")%></label>:
								</td>
								<td colspan="2" width="55%"><s:select name="appArrears"
									theme="simple" headerKey=" " headerValue="--Select--"
									list="#{'Y':'YES','N':'NO'}" /></td>
							</tr>
							<tr>
								<td width="20%" colspan="1"><label  class = "set" name="credit.taxb" id="credit.taxb" ondblclick="callShowDiv(this);"><%=label.get("credit.taxb")%></label>:
								</td>
								<td colspan="2" width="55%"><s:select name="taxable"
									theme="simple" headerKey=" " headerValue="--Select--"
									list="#{'Y':'YES','N':'NO'}" /></td>
							</tr>
							-->
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.period" id="credit.period"
						ondblclick="callShowDiv(this);"><%=label.get("credit.period")%></label>:
					</td>
					<td colspan="1" width="30%"><s:select name="crePeriod"
						cssStyle="widht:175" theme="simple"
						list="#{'M':'Monthly','Q':'Quarterly','A':'Annually','H':'Half Yearly'}" /></td>
				
					<td width="20%" colspan="1"><label class="set"
						name="credit.priority" id="credit.priority"
						ondblclick="callShowDiv(this);"><%=label.get("credit.priority")%></label>:
					</td>
					<td colspan="1" width="30%"><s:textfield size="10"
						onkeypress="numbersOnly();" maxlength="10" theme="simple"
						name="CreditPrior" /></td>
				</tr>
				<!--  <tr>
								<td width="20%" colspan="1"><label  class = "set" name="credit.form" id="credit.form" ondblclick="callShowDiv(this);"><%=label.get("credit.form")%></label>:
								</td>
								<td colspan="2" width="55%"><s:textarea theme="simple"
									name="creditFor" rows="3" cols="40" 
									onkeyup="callLength('descCnt');" />
									<img src="../pages/images/zoomin.gif"
									height="12" align="absmiddle" id='ctrlHide' width="12"
									theme="simple"
									onclick="javascript:callWindow('paraFrm_creditFor','credit.form','','200','200');">
								</td>
			
								<td colspan="2" id='ctrlHide'>Remaining chars<s:textfield
									name="descCnt" readonly="true" size="5"></s:textfield></td>
							</tr>	-->


				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.exempttax" id="credit.exempttax"
						ondblclick="callShowDiv(this);"><%=label.get("credit.exempttax")%></label>:
					</td>
					<td colspan="1" width="30%"><s:select name="Creditexempt"
						theme="simple" headerKey=" " headerValue="--Select--" cssStyle="widht:175"
						list="#{'Y':'Yes','N':'No'}" onchange="callClear();" /></td>
				
					<td width="20%" colspan="1"><label class="set"
						name="credit.exemptundersec" id="credit.exemptundersec"
						ondblclick="callShowDiv(this);"><%=label.get("credit.exemptundersec")%></label>:
					</td>
					<td colspan="1" width="30%"><s:textfield
						name="exemptSectionNo" readonly="true" size="35" /> <img
						src="../pages/images/recruitment/search2.gif" height="16"
						id="ctrlHide" width="16"
						onclick="javascript:callsF9(500,325,'CreditMaster_f9taxaction.action');">

					<s:hidden name="taxCode" /></td>
				</tr>
				<!-- 
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.reimb" id="credit.reimb"
						ondblclick="callShowDiv(this);"><%//=label.get("credit.reimb")%></label>:
					</td>
					<td colspan="2" width="55%"><s:select name="creditReimbursement"
						theme="simple" headerKey=" " headerValue="--Select--"
						list="#{'Y':'Yes','N':'No'}"  /></td>
				</tr>ADDED BY DHANASHREE--- -->
				
				<!-- ADDED BY DHANASHREE--- -->
				<tr>
					<td width="20%" colspan="1"><label class="set"
						name="credit.headType" id="credit.headType"
						ondblclick="callShowDiv(this);"><%=label.get("credit.headType")%></label>:
					</td>
					<td colspan="1" width="30%"><s:select name="creditHeadType"
						theme="simple" cssStyle="widht:175"
						list="#{'S':'Salary','V':'Voucher','R':'Reimbursement','P':'Perquisite'}" /></td>
				
					<td width="20%" colspan="1"><label class="set"
						name="credit.isCTCComponent" id="credit.isCTCComponent"
						ondblclick="callShowDiv(this);"><%=label.get("credit.isCTCComponent")%></label>:
					</td>
					<td colspan="1" width="30%"><s:select name="creditIsCTCComponent"
						theme="simple" cssStyle="widht:175"
						list="#{'Y':'Yes','N':'No'}" /></td>
				</tr>
				
	        
			
			</table>
			</td><s:hidden name="hiddencode" />
		</tr>
		<tr>
			<td colspan="3"><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>
	</table>
</s:form>
<script>

function callClear()
{
	
	  	if(document.getElementById('paraFrm_Creditexempt').value=="N")
       	{
       		document.getElementById('paraFrm_taxCode').value="";
       		document.getElementById('paraFrm_exemptSectionNo').value="";
       	
       	}
       	

}
	onload();
	function saveFun()
	{	
		var fieldName = ["paraFrm_creditMaster_CreditName","paraFrm_creditMaster_CreditAbbr"];
		var lableName = ["credit.name","credit.abbr"];
		var badFlag = ["enter","enter"];
		var creditExemptUnderSect = document.getElementById('paraFrm_taxCode').value;
		
		var fieldName1 = ["paraFrm_creditMaster_CreditName","paraFrm_creditMaster_CreditAbbr"];
       if(!validateBlank(fieldName,lableName,badFlag))
          return false;
       if(!f9specialchars(fieldName1))
          return false;
        /*var val=document.getElementById('paraFrm_creditFor').value
       if(eval(val.length)>100) {
       		alert(document.getElementById('credit.form').innerHTML.toLowerCase()+' accepts only 100 ' + 
		            ' characters. Please remove ' + (eval(val.length) - 100) + ' characters.');
		    return false;
       }*/
       	
       	if(document.getElementById('paraFrm_Creditexempt').value=="Y")
       	{
       		if(creditExemptUnderSect=="")
       		{
       				 
      	alert("Please select "+document.getElementById('credit.exemptundersec').innerHTML.toLowerCase()); 	
      		return false ;			
       		}
       	}
       	
     
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='CreditMaster_save.action';
		document.getElementById("paraFrm").submit();
		
		return true;
		
	}
	function editFun()
	{
		
		return true;
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'CreditMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'CreditMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'CreditMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	 /*function callLength(type){ 
		
		 if(type=='descCnt'){
					var cmt =document.getElementById('paraFrm_creditFor').value;
					var remain = 100 - eval(cmt.length);
					document.getElementById('paraFrm_descCnt').value = remain;
					
						if(eval(remain)< 0){
					document.getElementById('paraFrm_creditFor').style.background = '#FFFF99';
					
					}else document.getElementById('paraFrm_creditFor').style.background = '#FFFFFF';
				
				}
	 }  */	
	 function setTaxable(){
	 	if(document.getElementById('incomeTaxCheck').checked==true)
	 		document.getElementById('paraFrm_taxable').value="Y";
	 	else
	 		document.getElementById('paraFrm_taxable').value="N";
	 }
	 function setProTax(){
	 	if(document.getElementById('proTaxCheck').checked==true)
	 		document.getElementById('paraFrm_proTax').value="Y";
	 	else
	 		document.getElementById('paraFrm_proTax').value="N";
	 }
	 function setEsic(){
	 	if(document.getElementById('esicCheck').checked==true)
	 		document.getElementById('paraFrm_esic').value="Y";
	 	else
	 		document.getElementById('paraFrm_esic').value="N";
	 }
	 function setAppArrears(){
	 	if(document.getElementById('arrearsCheck').checked==true)
	 		document.getElementById('paraFrm_appArrears').value="Y";
	 	else
	 		document.getElementById('paraFrm_appArrears').value="N";
	 }
	 
	 function setLWF(){
	 	if(document.getElementById('lwfCheck').checked==true)
	 		document.getElementById('paraFrm_lwf').value="Y";
	 	else
	 		document.getElementById('paraFrm_lwf').value="N";
	 }
	 
	 function onload(){
	 	var creditType = document.getElementById('paraFrm_CreditType').value;
	 	if(creditType=='3') {
	 	 	document.getElementById('showFormulaButtonID').style.display = '';
	 	} else {
	 			document.getElementById('paraFrm_creditmaxcap').value = "0";
	 		 document.getElementById('showFormulaButtonID').style.display = 'none';
	 	} 
	 
	 	if(document.getElementById('paraFrm_taxable').value=="Y")
	 		document.getElementById('incomeTaxCheck').checked=true;
	 	else
	 		document.getElementById('paraFrm_taxable').value="N";
	 	if(document.getElementById('paraFrm_proTax').value=="Y")
	 		document.getElementById('proTaxCheck').checked=true;
	 	else
	 		document.getElementById('paraFrm_proTax').value="N";
	 	if(document.getElementById('paraFrm_esic').value=="Y")
	 		document.getElementById('esicCheck').checked=true;
	 	else
	 		document.getElementById('paraFrm_esic').value="N";
	 	if(document.getElementById('paraFrm_appArrears').value=="Y")
	 		document.getElementById('arrearsCheck').checked=true;
	 	else
	 		document.getElementById('paraFrm_appArrears').value="N";
	 	if(document.getElementById('paraFrm_lwf').value=="Y")
	 		document.getElementById('lwfCheck').checked=true;
	 	else
	 		document.getElementById('paraFrm_lwf').value="N";
	 }
	 
	 function showFormulaButton() {
	 	var creditType = document.getElementById('paraFrm_CreditType').value;
	 	if(creditType=='3') {
	 	 	document.getElementById('showFormulaButtonID').style.display = '';
	 	} else {
	 		document.getElementById('paraFrm_creditmaxcap').value = "0";
	 		 document.getElementById('showFormulaButtonID').style.display = 'none';
	 	} 
	 }
</script>