<%@ taglib uri="/struts-tags" prefix="s" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="DebitMaster" validate="true" id="paraFrm" name="paraFrm"
	theme="simple">
	<s:hidden name="debitMaster.DebitCode" />
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
						<td width="93%" class="txt"><strong class="text_head">Debit Master
						  </strong></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" cellpadding="2" cellspacing="2"
					class="formbg">
					<tr>
						<td colspan="5" class="text_head"><strong
							class="forminnerhead">Debit Master</strong></td>
							<s:hidden name="deditCode" /> 
					</tr>
					<tr>
						<td align="left" width="30%"><label  class = "set" name="debit.name" id="debit.name" ondblclick="callShowDiv(this);"><%=label.get("debit.name")%></label>
						<font color="red">*</font>:
						</td>
						<td colspan="3"><s:textfield maxlength="100" theme="simple" size="50"
							name="debitMaster.DebitName"
							onkeypress="return allCharacters();" /></td>

					</tr>
					<tr>


						<td align="left" width="30%"><label  class = "set" name="debit.abbr" id="debit.abbr" ondblclick="callShowDiv(this);"><%=label.get("debit.abbr")%></label>
						<font color="red">*</font>:</td>
						<td><s:textfield maxlength="10" theme="simple"
							name="debitMaster.DebitAbbr"
							onkeypress="return allCharacters();" /></td>

					</tr>
					
						<tr>
							<td align="left" width="20%"><label  class = "set" name="debit.type" id="debit.type" ondblclick="callShowDiv(this);"><%=label.get("debit.type")%></label>:
							</td>
							<td width="20%"><s:select name="debitType" onchange="callDebitFormulaField();" theme="simple"
												list="#{'SG':'System Generated','SD':'As per Salary Days','FX':'Fixed','FR':'Formula'}" /></td>
							<td colspan="2" width="60%">
							<div id="div1">
							<table width="100%">
								<tr>
									<td colspan="1" width="30%" class="formtext" align="left"><label
										name="debit.formula" id="debit.formula"
										ondblclick="callShowDiv(this);"><%=label.get("debit.formula")%></label>:</td>
									<td colspan="1" width="70%"><s:textfield name="debitFormula" theme="simple" maxlength="50" size="25" />
									 <input type="button" class="token" name="formCalc4" value=">>" id='paraFrm_formCalc4'
									onclick="callFormulaBuilder('paraFrm_debitFormula');"/></td>

								</tr>
							</table>
							</div>
							<!--  <div id="div2">
							<table width="100%">
								<tr>
									<td colspan="1" width="30%" class="formtext" align="left"><label
										name="debit.amount" id="debit.amount" 
										ondblclick="callShowDiv(this);"><%//=//label.get("debit.amount")%></label>:</td>
									<td colspan="1" width="70%"><s:textfield name="debitFixedAmount" theme="simple" maxlength="10" onkeypress="return numbersWithDot();" size="25" /> </td>

								</tr>
							</table>
							</div>-->
							</td>
						</tr>
					
					<tr>
						<td align="left" width="30%"><label  class = "set" name="debit.prior" id="debit.prior" ondblclick="callShowDiv(this);"><%=label.get("debit.prior")%></label>:
						</td>
						<td><s:textfield name="debitPriority" theme="simple"
							maxlength="2" onkeypress="return numbersOnly();" /></td>
					</tr>
					<!--  <tr>
						<td align="left" width="30%"><label  class = "set" name="debit.loanfl" id="debit.loanfl" ondblclick="callShowDiv(this);"><%=label.get("debit.loanfl")%></label>:
						</td>
						<td><s:select name="DebitforLoan" theme="simple"
							headerKey=" " headerValue="--Select--"
							list="#{'Y':'Yes','N':'No'}" /></td>
					</tr>
					<tr>
						<td align="left" width="30%"><label  class = "set" name="debit.carryFwdFlag" id="debit.carryFwdFlag" ondblclick="callShowDiv(this);"><%=label.get("debit.carryFwdFlag")%></label>:
						</td>
						<td><s:select name="DebitBalFlag" theme="simple"
							headerKey=" " headerValue="--Select--"
							list="#{'Y':'Yes','N':'No'}" /></td>
					</tr>-->
					<tr>
						<td align="left" width="30%"><label  class = "set" name="debit.exempttax" id="debit.exempttax" ondblclick="callShowDiv(this);"><%=label.get("debit.exempttax")%></label>:
						<font
							color="red">*</font>:</td>
						<td><s:select name="Debitexempt" theme="simple"
							headerKey=" " headerValue="--Select--"
							list="#{'Y':'Yes','N':'No'}" /></td>
						<td align="left" width="20%"><label  class = "set" name="debit.exemptundersec" id="debit.exemptundersec" ondblclick="callShowDiv(this);"><%=label.get("debit.exemptundersec")%></label>:
						</td>
						<td width="30%"><s:hidden name="taxCode" /> <s:textfield
							name="exemptSectionNo" readonly="true" size="35" /> <img
							src="../pages/images/recruitment/search2.gif" height="16" id ="ctrlHide"
							width="16"
							onclick="javascript:callsF9(500,325,'DebitMaster_f9taxaction.action');">
						</td>
					</tr>
					<tr>
						
					</tr>

					<tr>
						<td align="left" width="30%"><label  class = "set" name="debit.period" id="debit.period" ondblclick="callShowDiv(this);"><%=label.get("debit.period")%></label>:
						</td>
						<td><s:select name="tableRecover" theme="simple"
							list="#{'M':'Monthly','Q':'Quarterly','A':'Annually','H':'Half Yearly'}" />
						</td>
					</tr>

					<tr>
						<td align="left"><label  class = "set" name="debit.payFlag" id="debit.payFlag" ondblclick="callShowDiv(this);"><%=label.get("debit.payFlag")%></label>:
						</td>
						<td><s:select name="debitPayFlag" theme="simple"
							list="#{'Y':'Yes','N':'No'}" /> <s:hidden name="debitpolicy"
							value="1" /></td>

					</tr>
					<tr>
						<td align="left" width="30%"><label  class = "set" name="debit.apparrears" id="debit.apparrears" ondblclick="callShowDiv(this);"><%=label.get("debit.apparrears")%></label>:
						</td>
						<td><s:select name="appArrears" theme="simple"
							list="#{'Y':'Yes','N':'No'}" /></td>
					<tr>
					<tr>
						<td colspan="1" width="30%"><label name="debitRound" id="debitRound" ondblclick="callShowDiv(this);"><%= label.get("debitRound")%></label> :</td>
						<td width="75%" colspan="3"><s:select list="#{'0':'No Round','1':'Round','2':'Floor','3':'Ceil','4':'Round To Next 10'}" name="debitRound"></s:select> </td>
				</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
	function saveFun()
	{	
		var fieldName = ["paraFrm_debitMaster_DebitName","paraFrm_debitMaster_DebitAbbr","paraFrm_debitType","paraFrm_Debitexempt"];
		var lableName = ["debit.name","debit.abbr","debit.type","debit.exempttax"];
		var badFlag = ["enter","enter","select","select"];
		var fieldName1 = ["paraFrm_debitMaster_DebitName","paraFrm_debitMaster_DebitAbbr"];
		
       if(!f9specialchars(fieldName1))
          return false;
        
       if(!validateBlank(fieldName,lableName,badFlag))
          return false;
		
		var debitType = document.getElementById('paraFrm_debitType').value;
		if(debitType == 'FR'){
			if(document.getElementById('paraFrm_debitFormula').value==''){
				alert('Please enter '+document.getElementById('debit.formula').innerHTML);
				document.getElementById('paraFrm_debitFormula').focus();
				return false;
			}
		}
		document.getElementById("paraFrm").target='_self';
		document.getElementById("paraFrm").action='DebitMaster_save.action';
		document.getElementById("paraFrm").submit();
		
		return true;
		
	}
	function editFun()
	{
		return true;
	}
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'DebitMaster_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	function resetFun() {
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = "DebitMaster_reset.action";
		document.getElementById('paraFrm').submit();
  	}
  	function deleteFun() {
		var conf = confirm("Do you really want to delete this record?");
  		if(conf) {
			document.getElementById('paraFrm').target = "_self";
	      	document.getElementById('paraFrm').action = 'DebitMaster_delete.action';
			document.getElementById('paraFrm').submit();
		}
	}
	
	callDebitFormulaField();
	
	function callDebitFormulaField(){
	var debitType= document.getElementById('paraFrm_debitType').value
	//alert(debitType);
		if(debitType=='FX' || debitType=='SD'){
			document.getElementById('div1').style.display='none';
			//document.getElementById('div2').style.display='none';
		}else if(debitType=='FR'){
			document.getElementById('div1').style.display='';
			//document.getElementById('div2').style.display='none';
		}else{
			document.getElementById('div1').style.display='none';
			//document.getElementById('div2').style.display='none';
		}
	}
</script>