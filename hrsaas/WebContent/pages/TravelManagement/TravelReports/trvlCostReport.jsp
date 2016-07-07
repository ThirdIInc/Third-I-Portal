<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<s:form action="TrvlCostReport" method="post" id="paraFrm"
	validate="true" target="main" theme="simple">
	<table width="100%" border="0" cellpadding="2" cellspacing="1"
		class="formbg">
		<tr>
			<td width="100%" colspan="3">
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Cost
					Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		
		<!-- This is a commnet sent to client on
<%= (new java.util.Date()).toLocaleString() %>
-->

		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td><label class="set" name="fDate" id="fDate"
						ondblclick="callShowDiv(this);"><%=label.get("fDate")%>
					</label><font color="red">*</font> :</td>
					<td><s:textfield name="fromDate" id="paraFrm_fromDate"
						theme="simple" size="14" maxlength="10"
						onkeypress="return numbersWithHiphen();"  /> <a
						href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"></td>
					<td><label class="set" name="tDate" id="tDate"
						ondblclick="callShowDiv(this);"><%=label.get("tDate")%>
					</label><font color="red">*</font> :</td>
					<td nowrap="nowrap"><s:textfield name="toDate" id="paraFrm_toDate"
						theme="simple" size="14" maxlength="10"
						onkeypress="return numbersWithHiphen();"  /> <a
						href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');"> <img
						src="../pages/images/Date.gif" class="iconImage" height="16"
						align="absmiddle" width="16"></td>
				</tr>

				<tr>
					<td colspan="1" ><label class="set" 
						id="currency.type" name="currency.type" ondblclick="callShowDiv(this);"><%=label.get("currency.type")%></label>:<font
						color="red"></font></td>
					<td colspan="1" >
						<s:select name="currencyType"
							 list="%{currencyHashmap}" 
							cssStyle="width:250" />
					</td>
				
					<td><label class="set" id="report.type" name="report.type"
						ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
					:</td>
					<td><s:select theme="simple" name="reportType"
						cssStyle="width:152" list="#{'Xls':'Xls','Pdf':'Pdf'}" /></td>
				</tr>

				<tr>
					<td width="30%">
					<s:a href="#" onclick="javascript:return callReport('employeewiseCostReport');">
					Click to generate Employeewise Report</s:a>
					</td>
					<td width="30%"><s:a href="#" onclick="javascript:return callReport('deptwiseCostReport');">
					Click to generate Departmentwise Report</s:a>
					</td>
					<td width="30%"><s:a href="#" onclick="javascript:return callReport('zonewiseCostReport');">
					Click to generate Zonewise Report</s:a>
					</td>
				</tr>

				<tr>
					<td width="30%"><s:a href="#" onclick="javascript:return callReport('projectwiseCostReport');">
					Click to generate Projectwise Report</s:a>
					</td>
					<td width="30%" colspan="2"><s:a href="#" onclick="javascript:return callReport('cutomerwiseCostReport');">
					Click to generate Customerwise Report</s:a>
					</td>
				</tr>

			</table>
			</td>
		</tr>

	</table>

</s:form>

<script>
function callReport(action){
	if(document.getElementById('paraFrm_fromDate').value==""){
		alert("Please select "+document.getElementById("fDate").innerHTML.toLowerCase());
		return false;
	}
	if(!validateDate("paraFrm_fromDate", "fDate"))
			return false;
	if(document.getElementById('paraFrm_toDate').value==""){
		alert("Please select "+document.getElementById("tDate").innerHTML.toLowerCase());
		return false;
	}
	if(!validateDate("paraFrm_toDate", "tDate"))
			return false;
	var fromDate =document.getElementById('paraFrm_fromDate').value ;
	var toDate = document.getElementById('paraFrm_toDate').value ;
	var datediff = dateDifferenceEqual(fromDate, toDate, 'paraFrm_fromDate', 'fDate', 'tDate');
  	if(!datediff){
		return false;
	}
	document.getElementById('paraFrm').target='_self';
	document.getElementById('paraFrm').action='TrvlCostReport_'+action+'.action';
	document.getElementById('paraFrm').submit();
}
</script>