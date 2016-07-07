<%@ taglib prefix="s" uri="/struts-tags"%>
<s:form action="Formula" id="paraFrm" theme="simple" >
<table class="tableBody" width="100%">
  	<tr>
		<td class="pageHeader" colspan="4" width="100%" align="center" ><center>Formula Builder</center></td>
	</tr>
	<tr><td colspan="4">&nbsp;</td></tr>
	<tr><td colspan="2">&nbsp;</td>
		<td align="right" >Select Formula : </td>
		<td><s:textfield name="formulaName" theme="simple" />
			<s:hidden name="formulaCode" />
			<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'Formula_f9Debit.action');">
			</td>
	</tr>
	<tr><td colspan="2" align="right">&nbsp;&nbsp;&nbsp;</td>
		<td align="right" >Select Credit : </td>
		<td ><s:textfield name="creditAbbr" readonly="true" theme="simple" />
			<s:hidden name="creditCode" />
			<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'Formula_f9Credit.action');">
			<input type="button" class="pagebutton" value="Append" name="append" onclick="appendCredit()" />
			</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td>
		<td align="right" >Select Debit : </td>
		<td><s:textfield name="debitAbbr" readonly="true" theme="simple" />
			<s:hidden name="debitCode" />
			<img src="../pages/images/search.gif" class="iconImage" height="18" align="absmiddle"	width="18" onclick="javascript:callsF9(500,325,'Formula_f9Debit.action');">
			<input type="button" class="pagebutton" value="Append" name="append" onclick="appendDebit()" />
			</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td>
		<td align="right" >Select Operator : </td>
		<td><s:select name="operator" cssStyle="width:140" theme="simple" list="#{'1':'Select---','(':'(',')':')','+':'+','*':'*','-':'-','/':'/','{':'{','}':'}','[':'[',']':']','%':'%' }" />
			<input type="button" class="pagebutton" value="Append" name="append" onclick="appendOperator()" />
			</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td>
		<td align="right" >Enter Number : </td>
		<td><s:textfield name="numberValue" theme="simple" onkeypress="return numbersonly(this)" />
			<input type="button" class="pagebutton" value="Append" name="append" onclick="appendNumber()" />
			</td>
	</tr>
	<tr><td colspan="2">&nbsp;</td>
		<td align="right" >Your Formula : </td>
		<td><s:textfield name="formula" readonly="true" size="40" theme="simple" />
			<s:hidden  name="actualFormula" theme="simple" />
			</td>
	</tr>
	
	<tr><td colspan="4" align="center" >		
			<input type="button" class="pagebutton" value=" Save " name="reset" onclick="" />&nbsp;	
			<input type="button" class="pagebutton" value=" Reset " name="reset" onclick="callReset()" />	
			</td>
	</tr>
</table>
</s:form>
<script>
	function appendCredit(){
		if(document.getElementById("paraFrm").creditAbbr.value == ""){
			alert('Select Credit ');
			return false;
		}
		var formula = document.getElementById("paraFrm").formula.value;
		var actualFormula = document.getElementById("paraFrm").actualFormula.value;
		var credit = document.getElementById("paraFrm").creditAbbr.value;
		var creditCode = document.getElementById("paraFrm").creditCode.value;
		var result = formula+credit;
		var actualResult =actualFormula+'#C'+creditCode+'#';
		document.getElementById("paraFrm").formula.value =result;
		document.getElementById("paraFrm").actualFormula.value=actualResult;
		alert('Actual Formula'+actualResult);
		
	}
	function appendDebit(){
		if(document.getElementById("paraFrm").debitAbbr.value == ""){
				alert('Select Debit ');
				return false;
			}
		var formula = document.getElementById("paraFrm").formula.value;
		var actualFormula = document.getElementById("paraFrm").actualFormula.value;
		var debit = document.getElementById("paraFrm").debitAbbr.value;
		var debitCode = document.getElementById("paraFrm").debitCode.value;
		var result = formula+debit;
		var actualResult =actualFormula+'#D'+debitCode+'#';
		document.getElementById("paraFrm").formula.value =result;
		document.getElementById("paraFrm").actualFormula.value=actualResult;
		alert('Actual Formula'+actualResult);
	}
	function appendOperator(){
	//	if(document.getElementById("paraFrm").formula.value ==''){
	//	alert('You can not appened operator first');
	//		return false;
	//	}
		if(document.getElementById("paraFrm").operator.value == '1'){
			alert('Select operator');
			return false;
		}
		var operator = document.getElementById("paraFrm").operator.value;
		var formula = document.getElementById("paraFrm").formula.value;
		if(formula == "" && (operator =='*' || operator =='*' || operator =='+' || operator =='/' || operator =='-' )){
			alert('You can not appened operator first');
			return false;
		}		
		var actualFormula = document.getElementById("paraFrm").actualFormula.value;
		
		var result = formula+operator;
		var actualResult =actualFormula+operator;
		document.getElementById("paraFrm").formula.value =result;
		document.getElementById("paraFrm").actualFormula.value=actualResult;
		alert('Actual Formula'+actualResult);
	}
	function appendNumber(){
		var formula = document.getElementById("paraFrm").formula.value;
		var actualFormula = document.getElementById("paraFrm").actualFormula.value;
		var values = document.getElementById("paraFrm").numberValue.value;
		
		var result = formula+values;
		var actualResult =actualFormula+values;
		document.getElementById("paraFrm").formula.value =result;
		document.getElementById("paraFrm").actualFormula.value=actualResult;
		alert('Actual Formula'+actualResult);
	}
	
	function callReset(){
		document.getElementById("paraFrm").actualFormula.value="";
		document.getElementById("paraFrm").formula.value = "";
		document.getElementById("paraFrm").operator.value ="1"
		document.getElementById("paraFrm").creditAbbr.value ="";
		document.getElementById("paraFrm").debitAbbr.value = "";
		document.getElementById("paraFrm").numberValue.value = "";
	}
	function numbersonly(myfield)
	{
	var key;
	var keychar;
	if (window.event)
		key = window.event.keyCode;
	else
		return true;
		
	keychar = String.fromCharCode(key);	
	if (((".0123456789").indexOf(keychar) > -1))
		return true;	
	else {
		myfield.focus();
		return false;
	}
}
</script>