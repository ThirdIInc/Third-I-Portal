<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<s:form action="TransferMisReport" id="paraFrm" theme="simple">

	<table width="100%" colspan="4" cellpadding="0" cellspacing="0"
		align="right" class="formbg">

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Transfer
					MIS Report </strong></td>
					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		
		<tr align="left">
			<td colspan="4"><s:if test="%{viewFlag}">
				<input type="button" class="token" theme="simple"
					value='Show Report' onclick="call()" />
			</s:if> <s:submit cssClass="reset" action="TransferMisReport_reset"
				theme="simple" value="   Reset  " /></td>
				
				
				
						
		</tr>

		<tr>
			<td colspan="4">
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">
				<tr>
					<td>
					<table width="98%" border="0" align="center" cellpadding="2"
						cellspacing="2" >
						<tr>
							<td colspan="5" class="formhead"><strong
								class="forminnerhead"></strong></td>
						</tr>
						<tr>
							<td colspan="1" width="20%"></td>
							<td colspan="3" width="100%"><s:hidden name="tdsCode" /></td>
						</tr>

						<tr>
							<strong class="forminnerhead"> Select any/all search criteria to generate Transfer MIS report : </strong>
						</tr>

						<tr>
							<td colspan="1" width="20%"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:</td>
							<td colspan="3" width="80%"><s:hidden name="empId" /><s:textfield
								name="empToken" theme="simple" readonly="true" size="15" /><s:textfield
								name="empName" theme="simple" readonly="true" size="74" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'TransferMisReport_f9emp.action');">
							</td>




						</tr>

						<tr>
							<td colspan="1" width="20%"><label  class = "set" name="from.date" id="from.date" ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label> :</td>
							<td colspan="1" width="30%"><s:textfield
								label="%{getText('fromDate')}" theme="simple" name="fromDate"
								size="21" /> <s:a
								href="javascript:NewCal('paraFrm_fromDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" class="imageIcon" border="0" align="absmiddle" />
							</s:a></td>
							<td colspan="1" width="20%"><label  class = "set" name="to.date" id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label> :</td>
							<td colspan="1" width="30%"><s:textfield
								label="%{getText('toDate')}" theme="simple" name="toDate" /> <s:a
								href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
								<img class="iconImage"
									src="../pages/images/recruitment/Date.gif" width="16"
									height="16" class="imageIcon" border="0" align="absmiddle" />
							</s:a></td>
						</tr>


						<tr>
							<td colspan="1" width="20%"><label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden name="divId" /><s:textfield
								name="divName" theme="simple" readonly="true" size="21" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'TransferMisReport_f9div.action');">
							</td>

							<td colspan="1" width="20%"><label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
							<td colspan="1" width="30%"><s:hidden name="deptId" /><s:textfield
								name="deptName" theme="simple" readonly="true" size="20" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'TransferMisReport_f9dept.action');">
							</td>


						</tr>

						<tr>
							<td colspan="1" width="20%"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
							<td colspan="3" width="80%"><s:hidden name="brnId" /><s:textfield
								name="brnName" theme="simple" readonly="true" size="21" /> <img
								src="../pages/images/recruitment/search2.gif" class="iconImage"
								height="16" align="absmiddle" width="15"
								onclick="javascript:callsF9(500,325,'TransferMisReport_f9brn.action');">
							</td>


						</tr>



						<tr>
							<td colspan="1" width="20%"><label  class = "set" name="sts" id="sts" ondblclick="callShowDiv(this);"><%=label.get("sts")%></label> :</td>
							<td colspan="1" width="30%"><s:select name="status"
								theme="simple" headerKey="-1" cssStyle="width:133"
								list="#{'':'--------Select-------','P':'Pending','A':'Approved','R':'Rejected'}" />

							</td>

							<td colspan="1" width="20%"><label  class = "set" name="report.type" id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>:</td>
							<td colspan="1" width="30%"><s:select name="repType"
								theme="simple" headerKey="-1" cssStyle="width:45"
								list="#{'Pdf':'Pdf','Xls':'Xls','Txt':'Txt'}" /></td>



						</tr>







						<tr>
							<td colspan="3"></td>
						</tr>
					</table>
					</td>
				</tr>
			</table>
			</td>
		</tr>

	</table>
	
</s:form>


<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<script>
 function call(){ 
 var fDate=document.getElementById('paraFrm_fromDate').value;
 var tDate=document.getElementById('paraFrm_toDate').value;
 
		if(!validateDate("paraFrm_fromDate","From Date")){
  			return false;
  		}
  	
  	
  		if(!validateDate("paraFrm_toDate","To Date")){
		return false;
	}
		
		if(!dateDifferenceEqual(fDate, tDate, 'paraFrm_toDate', 'From Date', 'To Date')){
	
	return false;
	}
		
  	
  	
  	else {
	        document.getElementById('paraFrm').target="_blank";
			document.getElementById('paraFrm').action='TransferMisReport_report.action';	
			document.getElementById('paraFrm').submit();
			document.getElementById('paraFrm').target="main"; 	 
	
	}
	}
</script>