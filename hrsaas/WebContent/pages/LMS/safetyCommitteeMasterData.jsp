<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript" src="../pages/common/datetimepicker.js"></script>
<%@include file="/pages/common/labelManagement.jsp"%>


<s:form action="SafetyCommitteeMaster" validate="true" id="paraFrm"
	validate="true" theme="simple">
	
	
	<s:hidden name="safetyCommitteeID"/>
	

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
					<td width="93%" class="txt"><strong class="text_head">Safety Committee Master</strong></td>

					<td width="3%" valign="top" class="txt">
					<div align="right"><img
						src="../pages/images/recruitment/help.gif" width="16" height="16" /></div>
					</td>
				</tr>
			</table>
			</td>
		</tr>

		<tr>
			<td width="100%">
			<table width="100%">
				<tr>

					<td><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"></span><font
						color="red">*</font>Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>




		<tr>
			<td>
			<table width="100%" border="0" cellpadding="2" cellspacing="2"
				class="formbg">


				<tr>
					<td><label class="set" name="type.of.committee"
						id="type.of.committee" ondblclick="callShowDiv(this);"><%=label.get("type.of.committee")%></label><font
						color="red">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						name="committeeType"
						list="#{'H':'Health & Safety ','S':'Sexual Harassment'}" /></td>
				</tr>
				<tr>
						<td width="20%" colspan="1" class="formtext"><label  class = "set" name="member.Name" id="member.Name" ondblclick="callShowDiv(this);"><%=label.get("member.Name")%></label>:
							</td>
									<td width="80%" colspan="3"><s:textfield name="empToken"
										size="10" value="%{empToken}" theme="simple" readonly="true" /><s:textfield
										name="empName" size="72" value="%{empName}" theme="simple"
										readonly="true" /> <s:hidden name="empCode" value="%{empCode}" /><img
										src="../pages/images/recruitment/search2.gif" height="16"
										align="absmiddle" width="16"
										onclick="javascript:callsF9(500,325,'SafetyCommitteeMaster_f9Employee.action');">
									</td>

								</tr>

				<tr>
							<td><label class="set" name="training.received"
								id="training.received" ondblclick="callShowDiv(this);"><%=label.get("training.received")%></label><font
								color="red"></font> :</td>

							<td><s:checkbox
								name="trainingReceivedFlag" onclick="" /> </td>

							

						</tr>
				
				<tr>
					<td><label class="set" name="role"
						id="role" ondblclick="callShowDiv(this);"><%=label.get("role")%></label><font
						color="red">*</font> :</td>

					<td><s:select headerKey="" headerValue="--Select--"
						name="roleType"
						list="#{'O':'Officer','M':'Manager Representative','W':'Worker Representative'}" /></td>
				</tr>
				

			</table>
			</td>
		</tr>


		<tr>
			<td><jsp:include
				page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>
		</tr>


	</table>
	
</s:form>


<script>

  	
  	function saveFun() {
		
		
		var committeeType=document.getElementById('paraFrm_committeeType').value;
  		
  		
  	if(committeeType==""){
			alert("Please select "+document.getElementById('type.of.committee').innerHTML.toLowerCase());
			 document.getElementById('paraFrm_committeeType').focus();
			return false;
	}
	

  		document.getElementById('paraFrm').target = "_self";
  		document.getElementById('paraFrm').action = 'SafetyCommitteeMaster_save.action';
		document.getElementById('paraFrm').submit();
      	
		
  	
  			}
  	
  		function resetFun() {
		
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'SafetyCommitteeMaster_reset.action';
		document.getElementById('paraFrm').submit();
  	}
	
	function backFun(){
	
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action="SafetyCommitteeMaster_back.action";
	  	document.getElementById('paraFrm').submit();  
	}
	
	function deleteFun() 
{
	 var con=confirm('Do you want to delete the record(s) ?');
	 if(con)
	 {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'SafetyCommitteeMaster_delete.action';
		document.getElementById('paraFrm').submit();
	}
	
	
}
		function editFun() {
		document.getElementById('paraFrm').action = 'SafetyCommitteeMaster_edit.action';
		document.getElementById('paraFrm').submit();
		
	}	
  	</script>


