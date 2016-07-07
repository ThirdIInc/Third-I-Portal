<%@ taglib prefix="s" uri="/struts-tags"%>

<link rel="stylesheet" type="text/css" title="default-theme"
	href="../pages/common/css/commonCSS.jsp" />

<s:form action="FormulaBuilder" id="paraFrm" validate="true"
	theme="simple" target="main">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">


		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Formula
					Calculator </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3">
			<table width="100%">
				<tr>
					<td width="50%">&nbsp;</td>
					<td width="50%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
			<s:hidden name="dupformulacode" />
		</tr>

		<tr>
			<td height="5" colspan="3"><img
				src="../pages/images/recruitment/space.gif" width="5" height="7" /></td>

		</tr>

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="1" cellspacing="0"
				class="formbg">
				<tr>
					<% String formula = request.getParameter("id");
				%>
					<td width="75%" colspan="1">Formula <font color="red">*</font>:
					<s:textfield theme="simple" name="frmCalc" value="%{frmCalc}"
						readonly="true" size="20" /></td>
					<s:hidden name="salAbr" />
					<s:hidden name="frmId" />
					<td width="25%" colspan="2"><input type="button" class="token"
						name="Back" value=" Backspace " onclick="callClear();" /></td>

					<td>&nbsp;</td>

				</tr>

				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn7" value=" 7 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn7" value=" 8 " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn8" value=" 9 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn9" value=" / " onclick="setValue(this);" /></td>
				</tr>
				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn4" value=" 4 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn5" value=" 5 " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn6" value=" 6 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn*" value=" * " onclick="setValue(this);" /></td>
				</tr>
				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn1" value=" 1 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn2" value=" 2 " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn3" value=" 3 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn-" value=" - " onclick="setValue(this);" /></td>
				</tr>
				<tr>

					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn0" value=" 0 "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn(" value=" ( " onclick="setValue(this);" /> <input
						type="button" class="token" name="btn)" value=" ) "
						onclick="setValue(this);" /> <input type="button" class="token"
						name="btn+" value=" + " onclick="setValue(this);" /></td>
					<!-- <input
						type="button" class="token" name="btn." value=" . "
						onclick="setValue(this);" /> -->

				</tr>

				<tr>
					<td align="center" width="75%" colspan="1"><input
						type="button" class="token" name="btn." value=" . "
						onclick="setValue(this);" /></td>
				</tr>

				<tr>
					<td align="center" width="75%" colspan="1">Salary Head <s:textfield
						name="salHeadCalc" value="%{salHeadCalc}" size="20"
						readonly="true" /><img
						src="../pages/images/recruitment/search2.gif" height="16"
						align="absmiddle" width="15"
						onclick="javascript:callsF9(500,325,'FormulaBuilder_f9calcaction.action');" />
					<s:hidden name="salCodeCalc" /> <s:hidden name="salAbbrCalc" /><s:hidden
						name="salCode" /><s:hidden name="hiddenSalCode" /> <s:hidden
						name="priorityCodes" id="priorityCodes"></s:hidden></td>
					<td align="center" width="25%" colspan="2"><input
						type="button" class="token" name="salOk" value="Add To Formula"
						onclick="return setText();" /></td>
				</tr>

				<tr>
					<td align="center" width="75%" colspan="1">&nbsp;&nbsp; <input
						type="button" class="token" name="Ok" value=" Calculate "
						onclick="setFormula();" /> <input type="button" class="token"
						name="cancel" value=" Cancel " onclick="callCancel();" /> <input
						type="button" class="token" name="Reset" value=" Reset "
						onclick="callreset();" /></td>
					<s:hidden name="notInList" value="%{notInList}"></s:hidden>

					<td width="25%" colspan="2">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script type="text/javascript">
var textId=document.getElementById('paraFrm_frmId').value;
getFormula();
getCTC();
function callsF9(width,height,action) {
	win=window.open('','diffName','top=260,left=150,width=700,height=350,scrollbars=no,status=yes,resizable=yes');
	document.getElementById("paraFrm").target="diffName";
	document.getElementById("paraFrm").action=action;
	document.getElementById("paraFrm").submit();	
	document.getElementById("paraFrm").target="main";
}
function setText(){

	var salCodeCalc=document.getElementById('paraFrm_salCodeCalc').value;
	document.getElementById('paraFrm_hiddenSalCode').value = salCodeCalc;
	
	var salHead=document.getElementById('paraFrm_salHeadCalc').value;
	
	var priority=document.getElementById('paraFrm_salAbbrCalc').value;
	/* var lucky=document.getElementById('priorityCodes').value;
	if(lucky==''){
	document.getElementById('priorityCodes').value=priority;
	}
	else document.getElementById('priorityCodes').value=document.getElementById('priorityCodes').value+','+priority;
	
	alert(document.getElementById('priorityCodes').value);*/
	if(salHead=="")
	{
	alert('Please Select Salary Head');
	return false
	}
	
	
	if(salHead=="CTC")
	{
	document.getElementById('paraFrm_frmCalc').value+="#"+"CTC"+"#";
	}
	else
	{
		document.getElementById('paraFrm_frmCalc').value+="#C"+salCodeCalc+"#";
	}
	return true;
	//document.getElementById('paraFrm_frmCalc').value+="#"+salAbbrCalc+"#";
}

function setValue(obj){
	//alert("obj.value"+LTrim1(RTrim1(obj.value))+"obj.value");
	document.getElementById('paraFrm_frmCalc').value+=LTrim1(RTrim1(obj.value));
}

function setFormula(){
	//alert(document.getElementById('paraFrm_hiddenSalCode').value);
	try{
	var frmCalc=document.getElementById('paraFrm_frmCalc').value;
	if(!checkNumbersWithDot(frmCalc)){
		alert("Numeric character should not allow  more one decimals ");
		return false;
	}else{
		opener.document.getElementById(textId).value=frmCalc;
	}
	//opener.document.getElementById('lengthp').value=document.getElementById('priorityCodes').value;
	//if(frmCalc=="")
	//{
	//alert('Formula Field Should Not Be Blank');
	//return false;
	//}
	
	}catch(e){
	}
	window.close();
}


function checkNumbersWithDot(frmCalc) {
	var count = 0;
	for(var i = 0; i < frmCalc.length; i++) {
		if(frmCalc.charAt(i) == '.') {
			count = count + 1;
		}
	}
	if(count > 1) {
		return false;
		
	} 
	return true;
}



function getFormula(){
	
	var oldFormula=opener.document.getElementById(textId).value;
	document.getElementById('paraFrm_frmCalc').value=oldFormula;
	
	var len=opener.document.getElementById('length').value;
	
	var lenpr=document.getElementById('paraFrm_salAbbrCalc').value;
	
	
	//opener.document.getElementById('lengthp').value=lenpr;

	for(var i=0;i<len;i++){
	var salcode=opener.document.getElementById('sId'+i).value;
	
	if(i==eval(len)-1){
		document.getElementById('paraFrm_notInList').value+=salcode;
	
	}
	else{
			
		document.getElementById('paraFrm_notInList').value+=salcode+",";
	}
	//alert(opener.document.getElementById('sId'+i).value);
	}
	
	//alert("list"+document.getElementById('paraFrm_notInList').value);

}

function getCTC()
{
	var ctc=document.getElementById('paraFrm_salHeadCalc').value;
	
	if(ctc=="")
	{
	document.getElementById('paraFrm_salHeadCalc').value="CTC";
	}
}
function callClear(){

	var str=document.getElementById('paraFrm_frmCalc').value;

	str=str.substring(0,str.length-1);

	document.getElementById('paraFrm_frmCalc').value=str;
} 

function callCancel(){
window.close();
}

// Trims all spaces to the left of a specific string
function LTrim1(str)
{
        var whitespace = new String(" \t\n\r "); 
        // last space character is not a space, but alt+0160, 
        // another invisible char. 
        var s = new String(str);
        if (whitespace.indexOf(s.charAt(0)) != -1) {
            // We have a string with leading blank(s)...
            var j=0, i = s.length;
            // Iterate from the far left of string until we
            // don't have any more whitespace...
            while (j < i && whitespace.indexOf(s.charAt(j)) != -1)
                j++;
            // Get the substring from the first non-whitespace
            // character to the end of the string...
            s = s.substring(j, i);
        }
        return s;
}
// Trims all spaces to the right of a specific string
function RTrim1(str)
{
        // We don't want to trip JUST spaces, but also tabs,
        // line feeds, etc.  Add anything else you want to
        // "trim" here in whitespace
        var whitespace = new String(" \t\n\r "); 
        // last space character is not a space, but alt+0160,
        // another invisible char. 
        var s = new String(str);
        if (whitespace.indexOf(s.charAt(s.length-1)) != -1) {
            // We have a string with trailing blank(s)...
            var i = s.length - 1;       // Get length of string
            // Iterate from the far right of string until we
            // don't have any more whitespace...
            while (i >= 0 && whitespace.indexOf(s.charAt(i)) != -1)
                i--;
            // Get the substring from the front of the string to
            // where the last non-whitespace character is...
            s = s.substring(0, i+1);
        }
        return s;
}
function callreset()
{

	document.getElementById('paraFrm_frmCalc').value="";
	document.getElementById('paraFrm_salHeadCalc').value="";

}

</script>
