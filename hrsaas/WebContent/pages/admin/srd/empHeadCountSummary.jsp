<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="EmpCountSummary" method="post" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0"
		cellspacing="0" class="formbg">
		<tr>
			<td width="100%">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Employee Head Count Summary</strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="78%" colspan="1"><input type="button" class="token"
						onclick="return callReport();" value="Generate Summary"  />


					<input type='button' class="reset" theme="simple" value=" Reset" onclick="return callReset();" /></td>
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
					<td colspan="1" width="20%" class="formtext" height="22"><label name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>  <font color="red">*</font> :</td>
					<td colspan="3" width="80%"><s:textfield theme="simple"	name="divName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'ManpowerSnapshot_f9divaction.action');"><s:hidden name='divCode'/></td>
				</tr>
				<tr>
					
					<td width="25%"></td>
					<td width="25%"></td>
					
				</tr>
				
				<tr>
					<td colspan="1" width="25%" class="formtext" height="22"><label name="horizontal.para" id="horizontal.para"
								ondblclick="callShowDiv(this);"><%=label.get("horizontal.para")%></label> <font color="red">*</font> :</td>
					<td colspan="1" width="25%"><s:select theme="simple" name="horizontalPara" cssStyle="width:155"
							list="#{'':'--Select--','DP':'Department','BR':'Branch','ET':'Employee Type','CC':'Cost Center'}" onchange="return checkParameters();" /></td>
					<td colspan="1" width="20%" class="formtext" height="22"><label name="vertical.para" id="vertical.para"
								ondblclick="callShowDiv(this);"><%=label.get("vertical.para")%></label> <font color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:select theme="simple" name="verticalPara" cssStyle="width:155"
							list="#{'':'--Select--','DP':'Department','BR':'Branch','ET':'Employee Type','CC':'Cost Center'}" onchange="return checkParameters();"  /></td>
				</tr>
				
				
				<tr>
					<td colspan="1" width="25%" class="formtext" height="22"><label name="asOn.Date" id="asOn.Date"
								ondblclick="callShowDiv(this);"><%=label.get("asOn.Date")%></label>  <font color="red">*</font> :</td>
					<td colspan="1" width="25%"><s:textfield theme="simple"
						onkeypress="return numbersWithHiphen();" name="asOnDate"
						maxlength="10" size="25" /> <s:a
						href="javascript:NewCal('paraFrm_asOnDate','DDMMYYYY');">
						<img src="../pages/images/recruitment/Date.gif" class="iconImage"
							height="16" align="absmiddle" width="16">
					</s:a></td>
					<td colspan="1" width="20%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font>:</td>
					<td colspan="1" width="30%"><s:select theme="simple" name="reportType" cssStyle="width:155"
							list="#{'':'--Select--','Pdf':'Pdf','Xls':'Xls'}" /></td>
				</tr>
				

				 
			</table>
			

	</table>

</s:form>
<script type="text/javascript">
function checkParameters(){
	var horizontalPara=document.getElementById('paraFrm_horizontalPara').value;
	var verticalPara=document.getElementById('paraFrm_verticalPara').value;
	var horizontalParaLable=document.getElementById('horizontal.para').innerHTML;
	var verticalParaLable=document.getElementById('vertical.para').innerHTML;
	if(horizontalPara!=''){
		if(horizontalPara==verticalPara){
			alert('\"'+horizontalParaLable+'\" and \"'+verticalParaLable+'\" can not be same.');
			document.getElementById('paraFrm_verticalPara').value='';
			document.getElementById('paraFrm_verticalPara').focus();
			return false;
		}
	}
	return true;
}
function callReport(){
	try
	
	{
	var fields=["paraFrm_divName","paraFrm_horizontalPara","paraFrm_verticalPara","paraFrm_asOnDate","paraFrm_reportType"];
	var lables=["division","horizontal.para","vertical.para","asOn.Date","report.type"];
	var flags=["select","select","select","select","select"];
	
	var asOnDate = document.getElementById("paraFrm_asOnDate").value;
   	
   		if(!validateBlank(fields, lables,flags))
		{
  			return false;
  		}
		if(!validateDate('paraFrm_asOnDate', 'asOn.Date'))
		{
  			return false;
  		}	
  	
	if(!checkParameters()) return false;
  	document.getElementById('paraFrm').target='_blank';
	document.getElementById('paraFrm').action='EmpCountSummary_report.action';
	document.getElementById('paraFrm').submit();
	document.getElementById('paraFrm').target="main";
	}
	catch(e)
	{
		alert(e);
	}
	}

	
	function  callReset(){
		document.getElementById('paraFrm_horizontalPara').value='';
		document.getElementById('paraFrm_divCode').value='';
		document.getElementById('paraFrm_divName').value='';
		document.getElementById('paraFrm_verticalPara').value='';
		document.getElementById('paraFrm_asOnDate').value='';
		document.getElementById('paraFrm_reportType').value='';
		
	}

</script>


