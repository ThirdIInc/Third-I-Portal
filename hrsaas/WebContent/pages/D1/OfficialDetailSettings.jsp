<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="/pages/common/labelManagement.jsp"%>
<s:form action="PayrollSettings" validate="true" id="paraFrm"
	theme="simple">
	<s:hidden name="hiddenCode"></s:hidden>
	<s:hidden name="bean.configId" />
	<table width="100%" border="0" cellpadding="2" cellspacing="1" class="formbg">
		<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="2"
				cellspacing="2" class="formbg">
				<tr>
					<td valign="bottom" class="txt"><strong class="text_head"><img
						src="../pages/images/recruitment/review_shared.gif" width="25"
						height="25" /></strong></td>
					<td width="93%" class="txt"><strong class="text_head">Official Details Settings </strong></td>
				</tr>
			</table>
			</td>
		</tr>
		
		<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		<!--<tr>
			<td>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="78%">
					<input type="button" value=" Save " onclick=" return saveFun();"/>
					</td>
					<td width="22%" align="right">
					<div align="right"><font color="red">*</font> Indicates
					Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
		--><tr>
			<td>
			<table class="formbg" width="100%">
				<tr>
					<td width="75%" class="text_head" colspan="3"><strong><label name="officialSet" id="officialSet" ondblclick="callShowDiv(this);"><%= label.get("officialSet")%></label></strong></td>
				</tr>
				<s:hidden name="salDurJoinFlag" id="salDurJoinFlag" />
				<s:hidden name="salDurLeaveFlag" id="salDurLeaveFlag" />
				<!--<tr>
					<td colspan="1" width="35%"><label name="enable.divwise" id="enable.divwise" ondblclick="callShowDiv(this);"><%= label.get("enable.divwise")%></label> <font
						color="red">*</font> :</td>
					<td colspan="3" width="75%"><input type="checkbox"
						name="divD1Flag" disabled="true" id="divD1Flag" checked="checked" /></td>
				</tr>
				-->
					<tr>

							<td width="10%"><label class="set" name="division"
								id="division" ondblclick="callShowDiv(this);"><%=label.get("division")%></label>
							:<font color="red">*</font></td>
							<td colspan="2" width="90%" height="22"><s:hidden
								theme="simple" name="bean.deptDivCode" /> <s:textfield
								label="%{getText('divName')}" readonly="true" theme="simple"
								name="bean.divName" size="50" /> <s:if
								test="bean.viewFlag">
								<img id='ctrlHide'
									src="../pages/common/css/default/images/search2.gif" width="16"
									height="15" 
									onclick="javascript:callsF9(500,325,'OfficialDetailsSettings_f9Divaction.action')"
									align="absmiddle" />
							</s:if></td>
						</tr>
				<tr>
					<td colspan="1" width="25%"><label name="enable.SSN" id="enable.SSN" ondblclick="callShowDiv(this);"><%= label.get("enable.SSN")%></label> :</td>
					<td colspan="3" width="75%"><s:checkbox name="ssnFlag"
						id="ssnFlag" /></td>
				</tr>
				<tr>
					<td colspan="1" width="25%"><label name="enable.SIN" id="enable.SIN" ondblclick="callShowDiv(this);"><%= label.get("enable.SIN")%></label> :</td>
					<td colspan="3" width="75%"><s:checkbox name="sinFlag"
						id="sinFlag" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label name="enable.region" id="enable.region" ondblclick="callShowDiv(this);"><%= label.get("enable.region")%></label> :</td>
					<td colspan="3" width="75%"><s:checkbox name="regionFlag"
						id="regionFlag" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label name="enable.dept" id="enable.dept" ondblclick="callShowDiv(this);"><%= label.get("enable.dept")%></label> :</td>
					<td colspan="3" width="75%"><s:checkbox name="deptNoFlag"
						id="deptNoFlag" /></td>
				</tr>
				
				<tr>
					<td colspan="1" width="25%"><label name="enable.executive" id="enable.executive" ondblclick="callShowDiv(this);"><%= label.get("enable.executive")%></label> :</td>
					<td colspan="3" width="75%"><s:checkbox name="exeFlag"
						id="exeFlag" /></td>
				</tr>
				
				<!--<tr>
					<td colspan="1" width="25%"><label name=emergency.number id="emergency.number" ondblclick="callShowDiv(this);"><%= label.get("emergency.number")%></label> :</td>
					<td colspan="3" width="75%"><s:checkbox name="emergencyFlag"
						id="exeFlag" /></td>
				</tr>
			
			--></table>
			</td>
		</tr><!--
		<tr>
			<td colspan="3" align="left"><input type="button" value=" Save " onclick=" return saveFun();"/></td>
		</tr>
	-->
	<tr>
			<td colspan="3">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="80%"><jsp:include
						page="/pages/ApplicationStudio/navigationPanel.jsp" /></td>

					<td width="20%">
					<div align="right"><span class="style2"><font
						color="red">*</font></span> Indicates Required</div>
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</s:form>
<script>
	
	function saveFun(){
		var divName=document.getElementById("paraFrm_bean_divName").value
		if(divName==""){
				alert("Please select division");
			    return false;
		}
		document.getElementById("paraFrm").target = '_self';
		document.getElementById("paraFrm").action = 'OfficialDetailsSettings_saveApplicationSetting.action';
		document.getElementById("paraFrm").submit();
	}
	
	function backFun() {
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'OfficialDetailsSettings_cancel.action';
		document.getElementById('paraFrm').submit();
	}
	
	function deleteFun() {
	var con=confirm('Do you want to delete the record(s) ?');
	 if(con){
		document.getElementById('paraFrm').target = "_self";
      	document.getElementById('paraFrm').action = 'OfficialDetailsSettings_delete.action';
		document.getElementById('paraFrm').submit();
	}
	}
	
	function editFun() {
		//document.getElementById('paraFrm').target = "_self";
      	//document.getElementById('paraFrm').action = 'DepartmentNumberMaster_edit.action';
		//document.getElementById('paraFrm').submit();
		return true;
	}	
	
	function resetFun() {
		//document.getElementById('paraFrm_show').value = '1';
  	 	document.getElementById('paraFrm').target = "_self";
  	 	document.getElementById('paraFrm').action = 'OfficialDetailsSettings_reset.action';
		document.getElementById('paraFrm').submit();
  	}
</script>