<%@ taglib prefix="s" uri="/struts-tags"%>
<%@include file="/pages/common/labelManagement.jsp" %>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>

<s:form action="ResignationDetailsMis" method="post" id="paraFrm" theme="simple">


	 
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
					<td width="93%" class="txt"><strong class="text_head">Resignation MIS Report
					</strong></td>
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
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<td width="70%" colspan="3"> 
								<input type="button" class="token"
									onclick="return callReportMis();" value="    Show Report" />
						 <s:submit cssClass="reset" action="ResignationDetailsMis_reset"
								theme="simple" value="    Reset"  /></td>
							<td width="30%">
							<div align="right"><span class="style2"></span></div>
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
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"  
								cellspacing="2">
								<tr>
									<td width="20%" colspan="1" class="formtext"><label  class = "set" name="employee" id="employee" ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>:
									</td>
									<td width="80%" colspan="3"><s:textfield name="empToken"
										size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
										name="empName" size="72" value="%{empName}" theme="simple"
										readonly="true" /> <s:hidden name="empCode" value="%{empCode}" /><img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'ResignationDetailsMis_f9Employee.action');">
									</td>

								</tr>
								<tr>
									<td width="20%" colspan="1"  class="formtext"><label  class = "set" name="fDate" id="fDate" ondblclick="callShowDiv(this);"><%=label.get("fDate")%></label>:</td>
									<td width="30%" colspan="1"><s:textfield name="frmDate" size="20"
										onkeypress="return numbersWithHiphen();" theme="simple"
										value="%{frmDate}" maxlength="10" onblur="return validateDate('frmDate','from')"/> <s:a
										href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
									<td width="20%"  colspan="1" class="formtext"><label  class = "set" name="tDate" id="tDate" ondblclick="callShowDiv(this);"><%=label.get("tDate")%></label>:<s:hidden
										name="today" /></td>
									<td width="30%" colspan="1"><s:textfield name="toDate" size="20"
										onkeypress="return numbersWithHiphen();" theme="simple"
										value="%{toDate}" maxlength="10" onblur="return validateDate('toDate','to')"/> <s:a
										href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
										<img src="../pages/images/recruitment/Date.gif"
											class="iconImage" height="16" align="absmiddle" width="16">
									</s:a></td>
								</tr>

								<tr>
									<td width="20%" colspan="1"><label  class = "set" name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label> :</td>
									<td width="30%" colspan="1"><s:textfield size="20"
										theme="simple" name="resigbranch" value="%{resigbranch}"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'ResignationDetailsMis_f9Brnach.action');">
									<s:hidden name="resigbranchCode" value="%{resigbranchCode}" /></td>

									<td width="20%" colspan="1"><label  class = "set" name="department" id="department" ondblclick="callShowDiv(this);"><%=label.get("department")%></label> :</td>
									<td width="30%" colspan="1"><s:textfield size="20"
										theme="simple" name="resigdept" value="%{resigdept}"
										readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'ResignationDetailsMis_f9Dept.action');">
									<s:hidden name="resigdeptCode" value="%{resigdeptCode}" /></td>
								</tr>

								<tr>
									<td width="20%" colspan="1"><label  class = "set" name="designation" id="designation" ondblclick="callShowDiv(this);"><%=label.get("designation")%></label> :</td>
									<td width="30%" colspan="1"><s:textfield size="20"
										theme="simple" name="resigdesg" readonly="true"
										value="%{resigdesg}" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'ResignationDetailsMis_f9Desg.action');">
									<s:hidden name="resigdesgCode" value="%{resigdesgCode}" /></td>
									<td width="20%" colspan="1"><label  class = "set" name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label> :</td>
									<td width="30%" colspan="1"><s:textfield size="20"
										theme="simple" name="resigdivision" readonly="true"
										value="%{resigdivision}" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'ResignationDetailsMis_f9Div.action');">
									<s:hidden name="resigdivisionCode" value="%{resigdivisionCode}" /></td>

								</tr>
								<tr>
									<td width="20%" colspan="1"><label  class = "set" name="report.type" id="report.type" ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label> :</td>
									<td width="80%" colspan="3"><s:select 
										name="rptType" headerKey="Pdf" headerValue="Pdf" 
										list="#{'Xls':'Xls', 'Txt':'Text'}" theme="simple" /></td>
								</tr>
							</table>
							</td>
						</tr>


					</table>
					</td>
				</tr>
				
			</table>	
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">

function callReportMis(){
  var frmDate=document.getElementById("paraFrm_frmDate").value;
  	var toDate=document.getElementById("paraFrm_toDate").value;
  	
    if((frmDate!="") && (toDate!="")) {
   		 if(!validateDate('paraFrm_frmDate','From Date')){
		             return false;
		     }
	    if(!validateDate('paraFrm_toDate','To Date')){
             return false;	 
             }    
	  	if(!dateDifferenceEqual(frmDate, toDate, 'paraFrm_frmDate', 'From Date', 'To Date'))
		{
		return false;
		}
	}
	  document.getElementById('paraFrm').target="_blank";
      document.getElementById('paraFrm').action="ResignationDetailsMis_report.action";
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
   	
	
}

</script>














