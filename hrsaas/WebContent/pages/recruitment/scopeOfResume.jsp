<%@page import="org.paradyne.lib.Utility"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:form action="CandidateJobBoard" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="reqCode" />
	<s:hidden name="reqCodeToApply" />
	<table class="formbg" width="100%">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="formhead">
					<img src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /> </strong></td>
					<td width="93%" class="txt"><strong class="text_head">Source
					of Resume</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" />
					</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" align="left">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="60%"><s:submit cssClass="token"
						action="CandidateJobBoard_applyForJob" theme="simple"
						value="Apply" onclick="return apply()" /> <s:submit
						cssClass="token" theme="simple" value="Cancel"
						onclick="return cancel()" /></td>
					<td width="40%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<s:if test="checkFlag">
		<tr>
			<td align="left"><b>Would you like your CV to grab our attention? </b><br>If so, answer
			this simple question: <br>There is an error in the print Ad, did you spot
			it? Print your answer here... <br>If your answer is correct, your CV will
			be given top priority.</td>
		</tr>
		<tr>
		<td align="left">
		<s:textarea cols="40" rows="3" name="sourceComments" />
		</td>
		</tr>
		
		</s:if>

		<tr>
			<td width="100%" align="left">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">
				<tr>
					<td width="40%">
					<p align="left" class="blue">
					How did you hear about the vacancy?</p>
					</td>
					<td colspan="3"></td>
				</tr>
				<tr>
					<td width="40%">
					<p align="left" class="blue">Select a source:<font color="red">*</font>
					</td>
					<td colspan="3"><s:select theme="simple" name="advtName"
						list="statMap" /></td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td colspan="3" align="left">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="60%"><s:submit cssClass="token"
						action="CandidateJobBoard_applyForJob" theme="simple"
						value="Apply" onclick="return apply()" /> <s:submit
						cssClass="token" theme="simple" value="Cancel"
						onclick="return cancel()" /></td>
					<td width="40%"></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>

function cancel(){

window.close();
}

function apply(){
var advtName = document.getElementById("paraFrm_advtName").value;
if(advtName == 'S'){
	alert("Please select source");
	return false;
} //end of if

var aa=confirm("Are you sure you want to apply ?");
if(aa){
	window.close();
	document.getElementById("paraFrm").target="main";
	document.getElementById('paraFrm').action = "CandidateJobBoard_input.action";
	document.getElementById('paraFrm').submit();
}
else{
	return false;
}

}

</script>
