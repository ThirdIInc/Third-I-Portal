<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="AppraisalReminderMail" validate="true" id="paraFrm"
	theme="simple">
	<table width="100%" border="0" align="center" cellpadding="2"
		cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Appraisal Reminder Mail
					  </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="">
				<tr>
					<td width="78%">
            
            
	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  			
	</td>
            <td width="22%"><div align="right"><font color="red">*</font> Indicates Required </div></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<s:hidden name="startDate" />
					<s:hidden name="endDate" />
					<td colspan="1" width="15%"><label name="appr.code"
						id="appr.code" ondblclick="callShowDiv(this);"><%=label.get("appr.code")%></label><font
						color="red">*</font> :</td>
					<td colspan="1" width="30%"><s:textfield size="30" readonly="true"
						name="apprName" /> <s:hidden name="apprCode" />
						<img src="../pages/images/recruitment/search2.gif" width="16"
								class="iconImage" height="15"
								onclick="javascript:callsF9(500,325,'AppraisalReminderMail_f9AppraisalCode.action');" />
					</td>
					
					<!--  <td colspan="1" width="15%"><label  class = "set"  id="phase" name="phase" ondblclick="callShowDiv(this);"><%=label.get("phase")%></label><font
							color="red">*</font> :</td>
							<td colspan="1" width="30%"><s:select name="phaseType" headerKey="" headerValue="--Select--" list="#{'S':'Self','M':'Manager'}" 
				 					size="1" />
							</td>-->
				</tr>
				
			</table>
			</td>
		</tr>
	<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" >
				<tr>
					<td width="78%">
            
            
	<jsp:include page="/pages/ApplicationStudio/navigationPanel.jsp" />
  			
	</td>
            
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>

<script type="text/javascript">
	
	function sendFun(){
		if(document.getElementById('paraFrm_apprCode').value=="")
		{
			alert("Please select "+document.getElementById('appr.code').innerHTML.toLowerCase());
			return false;
		}
		/*if(document.getElementById('paraFrm_phaseType').value=="")
		{
			alert("Please select "+document.getElementById('phase').innerHTML.toLowerCase());
			return false;
		}*/
		document.getElementById('paraFrm').action='AppraisalReminderMail_sendMail.action';
		document.getElementById('paraFrm').submit();
		document.getElementById('paraFrm').target = 'main';
	}
	function resetFun()
	{
		document.getElementById('paraFrm_apprCode').value="";
		document.getElementById('paraFrm_apprName').value="";
		document.getElementById('paraFrm_phaseType').value="";
		//document.getElementById('paraFrm').action='AppraisalReminderMail_reset.action';
		//document.getElementById('paraFrm').submit();
		
	}
</script>