<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="GratuityReport" method="post" id="paraFrm"
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
					<td width="93%" class="txt"><strong class="text_head">Gratuity Report</strong></td>
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
						onclick="return callGratReport();" value="Report"  />

					<input type='button' class="reset" onclick="return callReset();"
						theme="simple" value="Reset" /></td>
					<td width="22%">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td colspan="8">
			<table width="100%" border="0" cellpadding="0" cellspacing="0"
				class="formbg">

				<tr>
					<td width="10%" class="formtext" height="22"><label name="division" id="division"
								ondblclick="callShowDiv(this);"><%=label.get("division")%></label>  <font color="red">*</font> :</td>
					<td colspan="7" width="80%"><s:textfield theme="simple"	name="divName" readonly="true" size="25" /> <img
						src="../pages/images/recruitment/search2.gif" height="18"
						class="iconImage" align="absmiddle" width="18"
						onclick="javascript:callsF9(500,325,'GratuityReport_f9divaction.action');"><s:hidden name='divCode'/></td>
				</tr>
				<tr>
					<td width="10%"><label name="fromMonth" id="fromMonth1"
								ondblclick="callShowDiv(this);"><%=label.get("fromMonth")%></label>  <font color="red">*</font> :</td>
					<td><s:select theme="simple" name="month" cssStyle="width:100"
								list="#{'':'--Select--','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" /> </td>
					<td><label name="fromYear" id="fromYear1"
								ondblclick="callShowDiv(this);"><%=label.get("fromYear")%></label>  <font color="red">*</font> :</td>
					<td><s:textfield theme="simple"	onkeypress="return numbersOnly();" name="year"
						maxlength="4" size="12" /> </td>
					<td><label name="toMonth" id="toMonth1"
								ondblclick="callShowDiv(this);"><%=label.get("toMonth")%></label>  <font color="red">*</font> :</td>
					<td><s:select theme="simple" name="monthTo" cssStyle="width:100"
								list="#{'':'--Select--','01':'January','02':'February','03':'March','04':'April','05':'May','06':'June','07':'July','08':'August','09':'September','10':'October','11':'November','12':'December'}" /> </td>
					<td><label name="toYear" id="toYear1"
								ondblclick="callShowDiv(this);"><%=label.get("toYear")%></label>  <font color="red">*</font> :</td>
					<td><s:textfield theme="simple"	onkeypress="return numbersOnly();" name="yearTo"
						maxlength="4" size="12" /> </td>
				</tr>
				
				
				<tr>
					<td width="10%"><label  class = "set"  id="report.type" name="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> <font color="red">*</font>:</td>
					<td colspan="7"><s:select theme="simple" name="reportType" cssStyle="width:155"
							list="#{'':'--Select--','Pdf':'Pdf','Xls':'Xls'}" /></td>
				</tr>
				

				 
			</table>
			

	</table>

</s:form>
<script type="text/javascript">

function callGratReport(){
	try
	
	{
	var fields=["paraFrm_divName","paraFrm_month","paraFrm_year","paraFrm_monthTo","paraFrm_yearTo","paraFrm_reportType"];
	var lables=["division","fromMonth1","fromYear1","toMonth1","toYear1","report.type"];
	var flags=["select","select","enter","select","enter","select"];
	
   	
   		if(!validateBlank(fields, lables,flags))
		{
  			return false;
  		}
  	
 	 callReport('GratuityReport_getGratReport.action');
	}
	catch(e)
	{
		alert(e);
	}
	}
	
	function  callReset(){
		document.getElementById('paraFrm_divName').value='';
		document.getElementById('paraFrm_divCode').value='';
		document.getElementById('paraFrm_reportType').value='';
		document.getElementById('paraFrm_month').value='';
		document.getElementById('paraFrm_year').value='';
		document.getElementById('paraFrm_monthTo').value='';
		document.getElementById('paraFrm_yearTo').value='';
	}


</script>


