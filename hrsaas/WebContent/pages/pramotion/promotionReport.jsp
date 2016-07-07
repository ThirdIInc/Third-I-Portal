<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../common/commonValidations.jsp"%>
<%@include file="/pages/common/labelManagement.jsp"%>

<link rel="stylesheet" type="text/css"
	href="../pages/common/tabcontent.css" />
<script type="text/javascript" src="../pages/common/tabcontent.js"></script>


<s:form action="PromotionMisReport" id="paraFrm" theme="simple">
	<table class="formbg" width="100%" cellpadding="2" cellspacing="1">
		

		<tr>
			<td valign="bottom" class="txt">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0" class="formbg">
				<tr>
					<td><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Promotion
					MIS report </strong></td>
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
							<td width="70%" colspan="3"><s:if test="%{viewFlag}">
								<input type="button" class="token"
									onclick="return callReportMis();" value="    Show Report" />
							</s:if> <s:submit cssClass="reset" action="PromotionMisReport_misReset"
								theme="simple" value="    Reset" /></td>

							<td width="22%">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>

				</tr>
				
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="98%" border="0" align="center" cellpadding="0"
								cellspacing="2" >
								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="employee" id="employee"
										ondblclick="callShowDiv(this);"><%=label.get("employee")%></label>
									:</td>
									<td width="85%" colspan="4"><s:textfield name="empToken"
										size="10" value="%{empToken}" readonly="true" /> <s:textfield
										name="empName" size="70" value="%{empName}" readonly="true" />
									<img src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PromotionMisReport_f9emp.action');" />
									<s:hidden name="empCode" value="%{empCode}" /></td>
								</tr>



								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="from.date" id="from.date"
										ondblclick="callShowDiv(this);"><%=label.get("from.date")%></label>
									:</td>
									<td width="35%" colspan="1"><s:textfield
										cssStyle="width:130" size="8" theme="simple" name="frmDate" /><s:a
										href="javascript:NewCal('paraFrm_frmDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
									<td width="10%" colspan="1"><label class="set"
										name="to.date" id="to.date" ondblclick="callShowDiv(this);"><%=label.get("to.date")%></label>:</td>
									<td width="30%" colspan="3"><s:textfield
										cssStyle="width:130" size="8" theme="simple" name="toDate" /><s:a
										href="javascript:NewCal('paraFrm_toDate','DDMMYYYY');">
										<img src="../pages/images/Date.gif" class="iconImage"
											height="16" align="absmiddle" width="16">
									</s:a></td>
								</tr>


								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="branch" id="branch" ondblclick="callShowDiv(this);"><%=label.get("branch")%></label>
									:</td>
									<td width="35%" colspan="1"><s:textfield
										cssStyle="width:130" size="20" theme="simple" name="proBranch"
										value="%{proBranch}" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PromotionMisReport_f9proBrnach.action');">
									<s:hidden name="prBranCode" value="%{prBranCode}" /></td>

									<td width="10%" colspan="1"><label class="set"
										name="department" id="department"
										ondblclick="callShowDiv(this);"><%=label.get("department")%></label>
									:</td>
									<td width="30%" colspan="3"><s:textfield
										cssStyle="width:130" size="20" theme="simple" name="proDept"
										value="%{proDept}" readonly="true" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PromotionMisReport_f9proDept.action');">
									<s:hidden name="prDeptCode" value="%{prDeptCode}" /></td>
								</tr>

								<tr>
									<td width="15%" colspan="1"><label class="set"
										name="designation" id="designation"
										ondblclick="callShowDiv(this);"><%=label.get("designation")%></label>
									:</td>
									<td width="35%" colspan="1"><s:textfield
										cssStyle="width:130" size="20" theme="simple" name="proDesg"
										readonly="true" value="%{proDesg}" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PromotionMisReport_f9proDesg.action');">
									<s:hidden name="prDesgCode" value="%{prDesgCode}" /></td>
									<td width="15%" colspan="1"><label class="set"
										name="division" id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
									:</td>
									<td width="35%" colspan="3"><s:textfield
										cssStyle="width:130" size="20" theme="simple" name="proDiv"
										readonly="true" value="%{proDiv}" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PromotionMisReport_f9proDiv.action');">
									<s:hidden name="prDivCode" value="%{prDivCode}" /></td>

								</tr>

								<tr>

									<td width="15%" colspan="1"><label class="set"
										name="grade" id="grade" ondblclick="callShowDiv(this);"><%=label.get("grade")%></label>
									:</td>
									<td width="85%" colspan="3"><s:textfield
										cssStyle="width:130" size="20" theme="simple" readonly="true"
										name="proGrade" value="%{proGrade}" /> <img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'PromotionMisReport_f9proGrd.action');">
									<s:hidden name="prGrdCode" value="%{prGrdCode}" /></td>
								</tr>
								<tr>
									<td width="20%" colspan="1"><label class="set"
										name="report.type" id="report.type"
										ondblclick="callShowDiv(this);"><%=label.get("report.type")%></label>
									:</td>
									<td width="30%" colspan="3"><s:select name="reportType"
										headerKey="Pdf" headerValue="Pdf"
										list="#{'Xls':'Xls', 'Txt':'Text'}" /></td>
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
<script>
 
 function callReportMis(){
  var frmDate=document.getElementById('paraFrm_frmDate').value;
  	var toDate=document.getElementById('paraFrm_toDate').value;
  	
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
      document.getElementById('paraFrm').action="PromotionMisReport_misReport.action";
	  document.getElementById('paraFrm').submit();  
      document.getElementById('paraFrm').target="main";
  
   	
	
}
 
 
</script>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>