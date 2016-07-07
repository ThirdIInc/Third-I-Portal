<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/pages/common/labelManagement.jsp"%>
<s:form action="CustomerAccountInfo" validate="true" id="paraFrm" theme="simple">
	<table width="100%" border="0" align="right" cellpadding="0" cellspacing="0" class="formbg">
		<tr>
	  		<td colspan="3" width="100%">
			   <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="formbg">
					<tr>
						<td width="4%" valign="bottom" class="txt"><strong class="formhead"><img src="../pages/common/css/default/images/review_shared.gif" width="25" height="25" /></strong></td>
						<td width="93%" class="txt"><strong class="text_head">Daily Performance summary
</strong></td>
						<td width="3%" valign="top" class="otxt"></td>
					</tr>
				</table>
			</td>
	  	</tr>

		<tr>
			<td colspan="3" width="100%">
			<table width="100%" border="0" align="right" cellpadding="0"
				cellspacing="0">
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tr>
								<td width="78%" nowrap="nowrap"><s:hidden name="accountCode"/>
								<s:hidden name="reportCode" />	<s:hidden name="accountID"/>							
										<input type="button" class="save" value="Generate Report11" 
										onclick="callGenerateReport('paraFrm_reportCode','paraFrm_accountCode');">
								</td>
							<td width="22%" nowrap="nowrap">
							<div align="right"><font color="red">*</font> Indicates
							Required</div>
							</td>
						</tr>
					</table>
					<label></label></td>
				</tr>

			<tr>
				<td colspan="3" width="100%">&nbsp;
				</td>
			</tr>
				
				<tr>
					<td colspan="3">
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="formbg">
						<tr>
							<td>
							<table width="100%" border="0" align="center" cellpadding="0"
								cellspacing="2">
								<tr>
									<td nowrap="nowrap">Parameter Name1 :</td>
									<td><s:textfield size="25" name="parameter1"
										maxlength="4" readonly="true"/></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td nowrap="nowrap">Parameter Name2 :</td>
									<td><s:textfield size="25" name="parameter2"
										maxlength="4" readonly="true"/></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td nowrap="nowrap">Parameter Name3 :</td>
									<td><s:textfield size="25" name="parameter3"
										maxlength="4" readonly="true"/></td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								
							</table>
							</td>
						</tr>

					</table>
					</td>
				</tr>

				<tr>
					<td colspan="3"><img
						src="../pages/common/css/default/images/space.gif" width="5"
						height="4" /></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script>

function callGenerateReport(reportcode,accCode){
	var reportcodeValue=document.getElementById(reportcode).value;
	var accCodeValue=document.getElementById(accCode).value;
	reportcodeValue=reportcodeValue+'_'+accCodeValue;
	//alert(reportcodeValue);
	//alert('<%=request.getContextPath()%>/cr/CustomerAccountInfo_report.action?reportcodeValue12='+reportcodeValue);
	document.getElementById('paraFrm').target = "_blank";
	document.getElementById('paraFrm').action = '<%=request.getContextPath()%>/cr/CustomerAccountInfo_report.action?reportcodeValue12='+reportcodeValue;
	document.getElementById('paraFrm').submit();
	}
	
</script>