<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="SalaryStatementBank" validate="true" id="paraFrm" validate="true" theme="simple">
<s:hidden name="status" />
<s:hidden name="hiddenMonth" />
<s:hidden name="divisionCode" />
<s:hidden name="bonusAllowanceStatus" />

	<table class="formbg" width="100%">
        <tr>
        	<td colspan="3" width="100%">
        		<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
        			 <tr>
			          <td width="4%" valign="bottom" class="txt"><strong class="formhead">
			          	<img src="../pages/images/recruitment/review_shared.gif" width="25" height="25" /></strong></td>
			          <td width="93%" class="txt"><strong class="text_head">Payment Statement</strong></td>
			          <td width="3%" valign="top" class="txt"><div align="right">
			          	<img src="../pages/images/recruitment/help.gif" width="16" height="16" /></div></td>
			        </tr>
        		</table>
        	</td>
       	</tr>
		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="2" width="80%">
					<s:if test="fromBonusAllowanceFlag">
						<input type="button" class="token" onclick="backToBonusAllowance()" value="Back" />
					</s:if>
					<s:elseif test="linkSource !=''">
						<input type="button" class="token" onclick="backFun()" value="Back" />
					</s:elseif>
					<s:else>
						<input type="button" class="token" onclick="resetFun()" value="  Reset  " />
					</s:else>	
					</td>
					<td width="20%">
					<div align="right"><font color="red">*</font> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="3" width="100%">
				<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" class="formbg">
					<tr>
						<td width="20%"><label class="set" id="earnings"
							name="earnings1" ondblclick="callShowDiv(this);"><%=label.get("earnings")%></label>
						:</td>
						<td colspan="3" >
							<s:if test="fromBonusAllowanceFlag">
								<s:select theme="simple" disabled="true"
								name="earningTypeDisplay" cssStyle="width:150" onchange="checkBonusValidate();"
								list="#{'S':'Salary','M':'Monthly Arrears','P':'Promotional Arrears','B':'Bonus'}" />
							<s:hidden name="earningType" /> 
							</s:if>
							<s:else>
								<s:select theme="simple"
								name="earningType" cssStyle="width:150" onchange="checkBonusValidate();"
								list="#{'S':'Salary','M':'Monthly Arrears','P':'Promotional Arrears','L':'Leave Encashment','B':'Bonus','O':'OT'}" />
							<s:if test="linkSource !=''">
							<input type="text" name='earningTypeDisplay' style="border: none;" readonly="readonly" id='earningTypeDisplay'/>	
							</s:if>
							
							</s:else>
							
						</td>
					</tr>
					<tr>
						<td width="20%"><label class="set" id="division1"
							name="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
						:</td>
						<td colspan="3" ><s:textfield name="divisionName" size="24" readonly="true"/>
							<s:hidden name="earningCode" /> 
							<s:hidden name="linkSource" />
							<s:if test="fromBonusAllowanceFlag">&nbsp;</s:if>
							<s:elseif test="linkSource ==''">
								<img src="../pages/images/search2.gif" class="iconImage"
									height="16" align="absmiddle" width="16"
									onclick="javascript:callsF9(800,525,'SalaryStatementBank_f9earnings.action');" id="ctrlHide">
							</s:elseif>
						</td>
					</tr>
					<tr id="bonus0">
						<td width="20%"><label class="set" id="month"
							name="month1" ondblclick="callShowDiv(this);"><%=label.get("month")%></label>
						:</td>
						<td ><s:textfield name="earningMonth" size="24" readonly="true"/></td>
						<td width="20%"><label class="set" id="year"
							name="year1" ondblclick="callShowDiv(this);"><%=label.get("year")%></label>
						:</td>
						<td ><s:textfield name="earningYear" size="24" readonly="true"/></td>
					</tr>
					
					<tr id="bonus1">
						<td width="20%">From
						:</td>
						<td ><s:textfield name="fromYearning" size="24" readonly="true"/></td>
						<td width="20%">To
						:</td>
						<td ><s:textfield name="toYearning" size="24" readonly="true"/></td>
					</tr>
					
					<tr>
						<td width="20%"><label class="set" id="totRecords"
							name="totRecords1" ondblclick="callShowDiv(this);"><%=label.get("totRecords")%></label>
						:</td>
						<td ><s:property value="totalRecords"/></td>
						<td width="20%"><label class="set" id="totAmt1"
							name="totAmt" ondblclick="callShowDiv(this);"><%=label.get("totAmt")%></label>
						:</td>
						<td ><s:property value="totalAmount"/></td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td colspan="3">
				<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formBg">
				<tr>
					<td width="100%" colspan="4"><strong>Bankwise Statements</strong></label></td>
				</tr>
				<tr>
				<td colspan="4">
					<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
						<tr>
							<td class="formth" width="15%">
								<label class="set" id="bankDesc1" name="bankDesc" ondblclick="callShowDiv(this);"><%=label.get("bankDesc")%></label>
							</td>
							<td class="formth" width="15%">
								<label class="set" id="totRecords2" name="totRecords" ondblclick="callShowDiv(this);"><%=label.get("totRecords")%></label>
							</td>
							<td class="formth" width="10%">
								<label class="set" id="amt1" name="amt" ondblclick="callShowDiv(this);"><%=label.get("amt")%></label>
							</td>
							<td class="formth" width="10%">
								<label class="set" id="cheque1"	name="cheque" ondblclick="callShowDiv(this);"><%=label.get("cheque")%></label>
							</td>
							<td class="formth" width="15%">
								<label class="set" id="chkDate1" name="chkDate" ondblclick="callShowDiv(this);"><%=label.get("chkDate")%></label>
							</td>
							<td class="formth" width="15%">
								<label class="set" id="covering1" name="covering" ondblclick="callShowDiv(this);"><%=label.get("covering")%></label>
							</td>
							<td class="formth" width="15%">&nbsp;</td>
						</tr>
						<% int count=0; %>
					<s:iterator value="bankwiseList">
					<s:if test='%{bankCodeItt =="CS" || bankCodeItt =="CH" || bankCodeItt =="OH" }'>	
					<tr>
						<td class="sortableTD" align="left" colspan="7">&nbsp;</td>
					</tr>
					</s:if>
						<tr>
						
							<td  class="sortableTD" align="left">
								<s:property value="bankNameItt"/></td>
							<td class="sortableTD" align="right">
								<s:property value="totalEmpItt"/></td>
							<td class="sortableTD" align="right">
								<s:property value="totalEmpAmountItt"/></td>
							<s:if test='%{bankCodeItt =="CS" || bankCodeItt =="CH" || bankCodeItt =="OH"}'>	
								<td class="sortableTD" align="left">&nbsp;
									<!-- <input	type="text" id="paraFrm_chequeNoItt<%=count%>" name="chequeNoItt" readonly="true" Style="background-color: #F2F2F2;" /> --></td>
								<td class="sortableTD" align="left">&nbsp;
									<!-- <input	type="text" id="paraFrm_chequeDateItt<%=count%>" name="chequeDateItt" size="10" readonly="true" Style="background-color: #F2F2F2;" />
								 --></td>
							</s:if>
							<s:else>
								<td class="sortableTD" align="center">
									<input	type="text" id="paraFrm_chequeNoItt<%=count%>" name="chequeNoItt" onkeypress="return numbersOnly();" size="10"/></td>
								<td class="sortableTD" align="center" nowrap="nowrap">
									<input	type="text" id="paraFrm_chequeDateItt<%=count%>" name="chequeDateItt" size="10" readonly="true"/>
								<a href="javascript:NewCal('paraFrm_chequeDateItt<%=count%>','DDMMYYYY');">
									<img src="../pages/images/recruitment/Date.gif"	class="iconImage" align="center" title="Select Date"></a>
								</td>
							</s:else>
							<s:if test='%{bankCodeItt =="CS" || bankCodeItt =="CH" || bankCodeItt =="OH"}'>	
								<td class="sortableTD" align="left">&nbsp;
								</td>
							</s:if>
							<s:else> 
								<td nowrap="nowrap" class="sortableTD" align="center">
									<a href="#" style="color:blue;" title="View Covering Letter" onclick="view('L', '<s:property value="bankCodeItt"/>', '<s:property value="totalEmpItt"/>', '<s:property value="totalEmpAmountItt"/>', 'paraFrm_chequeNoItt<%=count%>', 'paraFrm_chequeDateItt<%=count%>', '<s:property value="bankTemplateCodeItt"/>');">Covering Letter</a>  
								</td>
							</s:else>
							<s:if test='%{bankCodeItt =="CS" || bankCodeItt =="CH" || bankCodeItt =="OH"}'>	
							<td align="center" class="sortableTD" nowrap="nowrap">
								<a href="#" style="color:blue;" title="View Bank Statement" onclick="view('S', '<s:property value="bankCodeItt"/>', '<s:property value="totalEmpItt"/>', '<s:property value="totalEmpAmountItt"/>', 'paraFrm_chequeNoItt<%=count%>', 'paraFrm_chequeDateItt<%=count%>','');"  >Bank Statement</a>
							</td></s:if>
							<s:else>	
							<td align="center" class="sortableTD" nowrap="nowrap">
								<a href="#" style="color:blue;" title="View Bank Statement" onclick="view('S', '<s:property value="bankCodeItt"/>', '<s:property value="totalEmpItt"/>', '<s:property value="totalEmpAmountItt"/>', 'paraFrm_chequeNoItt<%=count%>', 'paraFrm_chequeDateItt<%=count%>','');"  >Bank Statement</a>
							</td></s:else>
						</tr>
						<%count++; %>
					</s:iterator>
					<%	if (count > 0) { %>
						<tr align="center" class="formbg">
							<td class="sortableTD"><b>Total</b></td>
							<td class="sortableTD" align="right"><s:property value="totalRecords"/></td>
							<td class="sortableTD" align="right"><s:property value="totalAmount"/></td>
							<td class="sortableTD">&nbsp;</td>
							<td class="sortableTD">&nbsp;</td>
							<td class="sortableTD">&nbsp;</td>
							<td class="sortableTD">&nbsp;</td>
						</tr>
					<% } %>
					<%	if (count == 0) { %>
						<tr align="center">
							<td colspan="8" class="sortableTD" width="100%"><font
								color="red">No records to display</font></td>
						</tr>
						<% } %>
					</table>
				</td>
			</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td width="100%" colspan="3">
				<s:if test="fromBonusAllowanceFlag">
					<input type="button" class="token" onclick="backToBonusAllowance()" value="Back" />
				</s:if>
				<s:elseif test="linkSource !=''">
					<input type="button" class="token" onclick="backFun()" value="Back" />
				</s:elseif>	
				<s:else>
						<input type="button" class="token" onclick="resetFun()" value="  Reset  " />
				</s:else>	
			</td>
		</tr>
		</table>
</s:form>
<script>

function backToBonusAllowance() {
	var bonusCode = document.getElementById('paraFrm_earningCode').value;
	var lockUnlockStatus = document.getElementById('paraFrm_bonusAllowanceStatus').value;
	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='BonusAllowance_callForEdit.action?bonusCode='+bonusCode+'&lockUnlockStatus='+lockUnlockStatus;
	document.getElementById('paraFrm').submit();	
}

		function checkBonusValidate(){
			var value=document.getElementById('paraFrm_earningType').value;
			if(value=='S'||value=='M'||value=='P'||value=='O'||value=='L'){
			document.getElementById('bonus1').style.display='none';
			document.getElementById('bonus0').style.display='';
			}
			else{
			document.getElementById('bonus0').style.display='none';
			document.getElementById('bonus1').style.display='none';
			if(value=='B'){
			document.getElementById('bonus1').style.display='';
			}
			else{
			document.getElementById('bonus0').style.display='';
			document.getElementById('paraFrm_fromYearning').value='';
			document.getElementById('paraFrm_toYearning').value='';
			}
			}
			
		}

	function resetFun(){
		document.getElementById('paraFrm').target = "_self";
		document.getElementById('paraFrm').action="SalaryStatementBank_reset.action";
		document.getElementById('paraFrm').submit();
	}
	
	
	function view(type, bankCode, totalRecords, totalAmount, chequeNo, chequeDate, templateCode){
		var chequeNum = "";
		var chequeDt = "";
		if(!(bankCode=="CH" || bankCode=="CS" || bankCode=="OH")){
		chequeNum = document.getElementById(chequeNo).value;
		chequeDt = document.getElementById(chequeDate).value;
		}
		if(type == 'L'){
			if(!validateFields(bankCode, chequeNo, chequeDate, templateCode)){
				return false;
			}
				document.getElementById('paraFrm').target = 'main';
				document.getElementById('paraFrm').action='SalaryStatementBank_previewCoveringLetter.action?bankCode='+bankCode+'&totalRecords='+totalRecords+'&totalAmount='+totalAmount+'&chequeNum='+chequeNum+'&chequeDt='+chequeDt+'&templateCode='+templateCode;
				document.getElementById('paraFrm').submit();
		}else{
				document.getElementById('paraFrm').target = 'main';
				document.getElementById('paraFrm').action='SalaryStatementBank_fetchBankStatement.action?bankCode='+bankCode+'&totalRecords='+totalRecords+'&totalAmount='+totalAmount+'&chequeNum='+chequeNum+'&chequeDt='+chequeDt;
				document.getElementById('paraFrm').submit();
		}
	}
	

	
	function validateFields(bankCode, chequeNo, chequeDate, templateCode){
		if(!(bankCode=="CH" || bankCode=="CS" || bankCode=="OH")){
		var chequeNum = document.getElementById(chequeNo).value;
		var chequeDt = document.getElementById(chequeDate).value;
			if(templateCode=="0"){
				alert("Template not defined for this bank.");
				return false;
			}
			if(chequeNum == ""){
				document.getElementById(chequeNo).focus();
				alert("Please enter cheque no");
				return false;
			}
			if(chequeDt == ""){
				document.getElementById(chequeDate).focus();
				alert("Please enter cheque date");
				return false;
			}	
		}
		return true;
	}
	callOnload();
	function callOnload(){
	
	checkBonusValidate();
	
	var text=document.getElementById('paraFrm_earningType').options[document.getElementById('paraFrm_earningType').selectedIndex].text;
	var link=document.getElementById('paraFrm_linkSource').value;
	
	if(link!=""){
		document.getElementById('paraFrm_earningType').style.display='none';
		document.getElementById('earningTypeDisplay').value=text;
	}else{
		document.getElementById('paraFrm_earningType').style.display='';
		document.getElementById('earningTypeDisplay').value="";
	}
	
	
	//document.getElementById('paraFrm').submit();
	}
	
	function backFun(){
		var action11=document.getElementById('paraFrm_linkSource').value;
				document.getElementById('paraFrm').action=action11;
				document.getElementById('paraFrm').submit();
	}
	
	function disableFields() {
		var currentMode = 1;
		var cnt = 0;
				
		for (var i = 0; i < document.all.length; i++) {
			if(document.all[i].id == 'ctrlHide') {
				document.all[i].style.display = 'none';
			} 
			else if(document.all[i].type == 'select-one') {
				hideFields(document.all[i], cnt, currentMode);
			}
		}
		document.getElementById('cnt').value = cnt;
	}
	
	
</script>
